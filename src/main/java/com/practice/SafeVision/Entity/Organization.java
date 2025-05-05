package com.practice.SafeVision.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Timestamp created_at;
}