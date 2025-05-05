package com.practice.SafeVision.dto;

import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class EventDto {
    private String cameraId;
    private String eventType; //abandoned bogage, aggressive behavior
    private Float confidence;
    private String timestamp;
    private String imageUrl;
    private Map<String, Object> metadata;
}