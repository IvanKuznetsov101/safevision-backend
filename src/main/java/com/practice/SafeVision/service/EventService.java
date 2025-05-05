package com.practice.SafeVision.service;

import com.practice.SafeVision.Configuration.AppConfig;
import com.practice.SafeVision.Entity.Camera;
import com.practice.SafeVision.Entity.Event;
import com.practice.SafeVision.Entity.Notification;
import com.practice.SafeVision.Entity.Organization;
import com.practice.SafeVision.WebSocket.NotificationHandler;
import com.practice.SafeVision.dto.EventDto;
import com.practice.SafeVision.repository.CameraRepository;
import com.practice.SafeVision.repository.EventRepository;
import com.practice.SafeVision.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.Not;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private CameraRepository cameraRepository;
    private OrganizationRepository organizationRepository;
    private AppConfig appConfig;
    private NotificationHandler notificationHandler;

    @Transactional
    public void processEvent(EventDto eventDto) {
        Camera camera = cameraRepository.findById(Long.parseLong(eventDto.getCameraId()));
        if (camera == null){
            throw new RuntimeException("Camera not found");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(appConfig.getDateTimePattern());
        LocalDateTime dateTime = LocalDateTime.parse(eventDto.getTimestamp(), formatter);
        Event event = Event.builder()
                .cameraId(camera.getId())
                .eventType(eventDto.getEventType())
                .confidence(eventDto.getConfidence())
                .timestamp(dateTime)
                .frame_url(eventDto.getImageUrl())
                .metadata(eventDto.getMetadata())
                .build();
        eventRepository.save(event);
        Notification notification = Notification.builder()
                        .organizationId(camera.getOrganization_id())
                .cameraId(camera.getId())
                .cameraLocation(camera.getLocation())
                .eventType(event.getEventType())
                .timeStamp(event.getTimestamp().toString())
                .imageUrl(event.getFrame_url())
                .metadata(event.getMetadata())
                .build();
        notificationHandler.sendToOrganization(notification);
    }
    public List<Notification> getNotificationsByOrganizationId(Long id){
        Organization organization = organizationRepository.findById(id);
        if (organization == null){
            throw new RuntimeException("Camera not found");
        }
        return eventRepository.getEventsByOrganizationId(id);
    }
}
