package com.javaschool.webservices.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.webservices.configuration.RabbitMQProperties;
import com.javaschool.webservices.model.PackageSize;
import com.javaschool.webservices.model.PackageType;
import com.javaschool.webservices.model.ShippingRabbitMessages;
import com.javaschool.webservices.service.ShippingService;

@Service
public class ShippingRabbitRPCServiceImpl implements ShippingService {

	private final RabbitTemplate rabbitTemplate;

	private final ObjectMapper objectMapper;
	
	private final RabbitMQProperties rabbitMQProperties;

	public ShippingRabbitRPCServiceImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper,
			RabbitMQProperties rabbitMQProperties) {
		this.rabbitTemplate = rabbitTemplate;
		this.objectMapper = objectMapper;
		this.rabbitMQProperties = rabbitMQProperties;
	}

	@Override
	public List<PackageType> getPackageTypes() {
		try {
			String message = objectMapper.writeValueAsString(ShippingRabbitMessages.PACKAGE_TYPE.getMessageQueue());
			Object object = rabbitTemplate.convertSendAndReceive(rabbitMQProperties.getExchange(), rabbitMQProperties.getRoutingKey(), message);
			return objectMapper.readValue((String)object, new TypeReference<List<PackageType>>(){});
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<PackageSize> getPackageSizes() {
		try {
			String message = objectMapper.writeValueAsString(ShippingRabbitMessages.PACKAGE_SIZE.getMessageQueue());
			Object object = rabbitTemplate.convertSendAndReceive(rabbitMQProperties.getExchange(), rabbitMQProperties.getRoutingKey(), message);
			return objectMapper.readValue((String)object, new TypeReference<List<PackageSize>>(){});
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
}