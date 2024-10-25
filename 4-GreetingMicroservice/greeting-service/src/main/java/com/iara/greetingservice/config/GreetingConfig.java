package com.iara.greetingservice.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties("greeting-service")
public class GreetingConfig {
    private String greeting;
    private String defaultValue;
}
