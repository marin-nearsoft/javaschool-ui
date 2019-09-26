package com.javaschool.webservices.model;

public enum PackageRabbitMqMessages {

	PACKAGE_TYPE("packageType"), PACKAGE_SIZE("packageSize");
	
	private String packageRabbitMqMessage;

	private PackageRabbitMqMessages(String packageRabbitMqMessage) {
		this.packageRabbitMqMessage = packageRabbitMqMessage;
	}

	public PackageRabbitRPCMessage getPackageRabbitRPCMessage() {
		return new PackageRabbitRPCMessage(packageRabbitMqMessage);
	}
}

class PackageRabbitRPCMessage {

	private String type;
	
	public PackageRabbitRPCMessage(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}