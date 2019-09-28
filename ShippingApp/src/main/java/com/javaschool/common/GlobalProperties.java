package com.javaschool.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("config-rabbit")
@Getter
@Setter
public class GlobalProperties {

    private String queueShipping;
    private String exchangeShipping;
    private String routingKeyShipping;
}
