package com.javaschool.webservices.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("rabbitmq")
public class RabbitMQProperties {

	private String exchange;
	private String routingKey;
	private String shippingQueueName;

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String getShippingQueueName() {
		return shippingQueueName;
	}

	public void setShippingQueueName(String shippingQueueName) {
		this.shippingQueueName = shippingQueueName;
	}

}
