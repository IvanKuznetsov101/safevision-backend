package com.practice.SafeVision.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.SafeVision.Entity.Camera;
import com.practice.SafeVision.Entity.Event;
import com.practice.SafeVision.Entity.Notification;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class EventRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public void save(Event event) {
        String sql = """
                    INSERT INTO event (camera_id, event_type, confidence, timestamp, frame_url, metadata)
                    VALUES (?, ?, ?, ?, ?, ?::jsonb)
                """;

        String metadataJson = null;
        try {
            if (event.getMetadata() != null) {
                metadataJson = objectMapper.writeValueAsString(event.getMetadata());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize metadata", e);
        }

        jdbcTemplate.update(sql,
                event.getCameraId(),
                event.getEventType(),
                event.getConfidence(),
                event.getTimestamp(),
                event.getFrame_url(),
                metadataJson
        );
    }

    public List<Notification> getEventsByOrganizationId(Long id) {
        String sql = """
                 SELECT o.id AS organization_id, c.id AS camera_id, c.location,
                             e.event_type, e.timestamp, e.frame_url, e.metadata
                 FROM organization o
                 JOIN camera c ON o.id = c.organization_id
                 JOIN event e ON e.camera_id = c.id
                 WHERE o.id = ?;
            """;
        return jdbcTemplate.query(sql, new NotificationRowMapper(), id);
    }
}