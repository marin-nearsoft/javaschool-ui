package com.javaschool.webservices.model;

public enum ShippingRabbitMessages {

	PACKAGE_TYPE(new ShippingRabbitRPCMessage("packageType")), PACKAGE_SIZE(new ShippingRabbitRPCMessage("packageSize"));

	private ShippingRabbitRPCMessage messageQueue;

	private ShippingRabbitMessages(ShippingRabbitRPCMessage messageQueue) {
		this.messageQueue = messageQueue;
	}

	public ShippingRabbitRPCMessage getMessageQueue() {
		return messageQueue;
	}
}