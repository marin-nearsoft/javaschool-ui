package com.javaschool.webservices.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

	private final RabbitMQProperties rabbitMQProperties;

	public RabbitMQConfig(RabbitMQProperties rabbitMQProperties) {
		this.rabbitMQProperties = rabbitMQProperties;
	}

	@Bean
	Queue shippingQueue() {
		return QueueBuilder.nonDurable(rabbitMQProperties.getShippingQueueName()).build();
	}

	@Bean
	Exchange shippingExchange() {
		return ExchangeBuilder.topicExchange(rabbitMQProperties.getExchange()).build();
	}

	@Bean
	Binding shippingBinding(Queue shippingQueue, TopicExchange shippingExchange) {
		return BindingBuilder.bind(shippingQueue).to(shippingExchange).with(rabbitMQProperties.getRoutingKey());
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
