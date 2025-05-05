package com.practice.SafeVision.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Camera {
    private Long id;
    private Long organization_id;
    private String name;
    private String rtsp_url;
    private String Location;
    private Boolean is_active;
    private Timestamp created_at;
}