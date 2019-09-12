package com.javaschool.webservices.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

	private final String SHIPPING_QUEUE_NAME;

	private final String SHIPPING_EXCHANGE;

	private final String SHIPPING_ROUTING_KEY;

	public RabbitMQConfig(@Value("${rabbitmq.shippingQueueName}") String shippingQueueName,
			@Value("${rabbitmq.exchange}") String exchange,
			@Value("${rabbitmq.routing-key}") String routingKey) {
		this.SHIPPING_QUEUE_NAME = shippingQueueName;
		this.SHIPPING_EXCHANGE = exchange;
		this.SHIPPING_ROUTING_KEY = routingKey;
	}

	@Bean
	Queue shippingQueue() {
		return QueueBuilder.nonDurable(SHIPPING_QUEUE_NAME).build();
	}

	@Bean
	Exchange shippingExchange() {
		return ExchangeBuilder.topicExchange(SHIPPING_EXCHANGE).build();
	}

	@Bean
	Binding shippingBinding(Queue shippingQueue, TopicExchange shippingExchange) {
		return BindingBuilder.bind(shippingQueue).to(shippingExchange).with(SHIPPING_ROUTING_KEY);
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
