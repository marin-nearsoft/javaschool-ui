package com.javaschool.webservices.model;

public class ShippingRabbitRPCMessage {

	private String type;
	
	public ShippingRabbitRPCMessage(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
