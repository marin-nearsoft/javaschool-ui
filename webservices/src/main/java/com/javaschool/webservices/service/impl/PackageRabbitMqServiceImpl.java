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
import com.javaschool.webservices.model.PackageRabbitMqMessages;
import com.javaschool.webservices.service.PackageRabbitMqService;

@Service
public class PackageRabbitMqServiceImpl implements PackageRabbitMqService {

	private final RabbitTemplate rabbitTemplate;

	private final ObjectMapper objectMapper;
	
	private final RabbitMQProperties rabbitMQProperties;

	public PackageRabbitMqServiceImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper,
			RabbitMQProperties rabbitMQProperties) {
		this.rabbitTemplate = rabbitTemplate;
		this.objectMapper = objectMapper;
		this.rabbitMQProperties = rabbitMQProperties;
	}

	@Override
	public List<PackageType> getPackageTypes() {
		try {
			String message = objectMapper.writeValueAsString(PackageRabbitMqMessages.PACKAGE_TYPE.createPackageRabbitRPCMessage());
			Object object = rabbitTemplate.convertSendAndReceive(rabbitMQProperties.getExchange(), rabbitMQProperties.getRoutingKey(), message);
			return objectMapper.readValue((String)object, new TypeReference<List<PackageType>>(){});
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<PackageSize> getPackageSizes() {
		try {
			String message = objectMapper.writeValueAsString(PackageRabbitMqMessages.PACKAGE_SIZE.createPackageRabbitRPCMessage());
			Object object = rabbitTemplate.convertSendAndReceive(rabbitMQProperties.getExchange(), rabbitMQProperties.getRoutingKey(), message);
			return objectMapper.readValue((String)object, new TypeReference<List<PackageSize>>(){});
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
}