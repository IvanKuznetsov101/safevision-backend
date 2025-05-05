package com.practice.SafeVision.controller;

import com.practice.SafeVision.Entity.Notification;
import com.practice.SafeVision.dto.EventDto;
import com.practice.SafeVision.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;
    @PostMapping
    public ResponseEntity<Void> receiveEvent(@RequestBody EventDto eventDto) {
        eventService.processEvent(eventDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/organization/{id}")
    public ResponseEntity<List<Notification>> getNotificationsById(@PathVariable(value = "id") Long id) {
        List<Notification> notifications = eventService.getNotificationsByOrganizationId(id);
        return ResponseEntity.status(HttpStatus.OK).body(notifications);
    }
}
