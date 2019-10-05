package com.javaschool.webservices.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("rabbitmq")
public class RabbitMQProperties {

	@Getter @Setter private String exchange;
	@Getter @Setter private String routingKey;
	@Getter @Setter private String shippingQueueName;
	
}
