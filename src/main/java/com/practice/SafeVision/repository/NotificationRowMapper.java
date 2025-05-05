package com.practice.SafeVision.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.SafeVision.Entity.Notification;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class NotificationRowMapper implements RowMapper<Notification> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
        PGobject pgMetadata = (PGobject) rs.getObject("metadata");
        Map<String, Object> metadata = null;
        try {
            if (pgMetadata != null && pgMetadata.getValue() != null) {
                metadata = objectMapper.readValue(pgMetadata.getValue(), Map.class);
            }
        } catch (Exception e) {
            throw new SQLException("Ошибка парсинга metadata JSON", e);
        }

        return Notification.builder()
                .organizationId(rs.getLong("organization_id")) // alias можно поменять
                .cameraId(rs.getLong("camera_id"))
                .cameraLocation(rs.getString("location"))
                .eventType(rs.getString("event_type"))
                .timeStamp(rs.getString("timestamp"))
                .imageUrl(rs.getString("frame_url"))
                .metadata(metadata)
                .build();
    }
}