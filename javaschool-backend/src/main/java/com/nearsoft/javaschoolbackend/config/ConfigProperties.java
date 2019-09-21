package com.nearsoft.javaschoolbackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Data
public class ConfigProperties {

    private String exchange;

    private String queue;

    private String routingKey;

}
