package com.practice.SafeVision.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private Long organizationId;
    private Long cameraId;
    private String cameraLocation;
    private String eventType;
    private String timeStamp;
    private String imageUrl;
    private Map<String, Object> metadata;
}