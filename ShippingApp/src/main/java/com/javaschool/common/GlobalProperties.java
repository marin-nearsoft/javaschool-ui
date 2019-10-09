package com.javaschool.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("config-rabbit")
public class GlobalProperties {

    private String queueShipping;
    private String exchangeShipping;
    private String routingKeyShipping;

    public String getQueueShipping() {
        return queueShipping;
    }

    public void setQueueShipping(String queueShipping) {
        this.queueShipping = queueShipping;
    }

    public String getExchangeShipping() {
        return exchangeShipping;
    }

    public void setExchangeShipping(String exchangeShipping) {
        this.exchangeShipping = exchangeShipping;
    }

    public String getRoutingKeyShipping() { return routingKeyShipping; }

    public void setRoutingKeyShipping(String routingKeyShipping) {
        this.routingKeyShipping = routingKeyShipping;
    }
}
