package com.practice.SafeVision.Configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfig {
    @Value("${SafeVision.patterns.dateTimePattern}")
    private String dateTimePattern;
}