package com.javaschool.webservices.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.webservices.model.PackageType;
import com.javaschool.webservices.model.ShippingRabbitMessages;
import com.javaschool.webservices.service.ShippingService;

@Service
public class ShippingRabbitRPCServiceImpl implements ShippingService {

	private final RabbitTemplate rabbitTemplate;

	private final ObjectMapper objectMapper;

	private final String SHIPPING_EXCHANGE;

	private final String SHIPPING_ROUTING_KEY;

	public ShippingRabbitRPCServiceImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper,
			@Value("${rabbitmq.exchange}") String exchange, @Value("${rabbitmq.routing-key}") String routingKey) {
		this.rabbitTemplate = rabbitTemplate;
		this.objectMapper = objectMapper;
		this.SHIPPING_EXCHANGE = exchange;
		this.SHIPPING_ROUTING_KEY = routingKey;
	}

	@Override
	public List<PackageType> getPackageTypes() {
		try {
			String message = objectMapper.writeValueAsString(ShippingRabbitMessages.PACKAGE_TYPE.getMessageQueue());
			Object object = rabbitTemplate.convertSendAndReceive(SHIPPING_EXCHANGE, SHIPPING_ROUTING_KEY, message);
			return objectMapper.readValue((String)object, new TypeReference<List<PackageType>>(){});
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
}