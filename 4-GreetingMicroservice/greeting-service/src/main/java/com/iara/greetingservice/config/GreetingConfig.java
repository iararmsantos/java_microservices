package com.iara.greetingservice.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@RefreshScope //to refresh everytime we change configurations in github config-server-sample
@ConfigurationProperties("greeting-service")
public class GreetingConfig {
    private String greeting;
    private String defaultValue;
}
