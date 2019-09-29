package com.javaschool.webservices.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class PackageTime implements Serializable {
	
	private static final long serialVersionUID = -3515366569175801552L;
	
	@Getter @Setter private int id;
	@Getter @Setter private String description;
	@Getter @Setter private double priceFactor;

}
