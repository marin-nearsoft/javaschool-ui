package com.javaschool.webservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public enum PackageRabbitMqMessages {

	PACKAGE_TYPE("packageType"), PACKAGE_SIZE("packageSize"), PACKAGE_TRANSPORT("transportType");
	
	private String packageRabbitMqMessage;

	private PackageRabbitMqMessages(String packageRabbitMqMessage) {
		this.packageRabbitMqMessage = packageRabbitMqMessage;
	}

	public PackageRabbitRPCMessage createPackageRabbitRPCMessage() {
		return new PackageRabbitRPCMessage(packageRabbitMqMessage);
	}
}

@AllArgsConstructor
class PackageRabbitRPCMessage {

	@Getter @Setter private String type;
	
}