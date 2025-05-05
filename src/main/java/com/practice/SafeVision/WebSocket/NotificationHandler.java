package com.practice.SafeVision.WebSocket;

import com.practice.SafeVision.Entity.Camera;
import com.practice.SafeVision.Entity.Event;
import com.practice.SafeVision.Entity.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationHandler {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendToOrganization(Notification notification) {
        messagingTemplate.convertAndSend(
                "/org/" + notification.getOrganizationId(),
                Map.of(
                        "cameraId", notification.getCameraId(),
                        "cameraName", notification.getCameraId(),
                        "cameraLocation", notification.getCameraLocation(),
                        "eventType", notification.getEventType(),
                        "timestamp", notification.getTimeStamp(),
                        "imageUrl", notification.getImageUrl(),
                        "metadata", notification.getMetadata()
                )
        );
    }
}