package com.practice.SafeVision.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;
    private Long cameraId;
    private String eventType;
    private Float confidence;
    private LocalDateTime timestamp;
    private String frame_url;
    private Map<String, Object> metadata;
}