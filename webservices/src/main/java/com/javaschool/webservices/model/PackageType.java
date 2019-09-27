package com.javaschool.webservices.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class PackageType implements Serializable{

	private static final long serialVersionUID = 8642607176960918131L;
	
	@Getter @Setter private int id;
	@Getter @Setter private String description;
	@Getter @Setter private float price;

}
