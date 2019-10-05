package com.javaschool.webservices.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class PackageSize implements Serializable {

	private static final long serialVersionUID = 865846279570747807L;
	
	@Getter @Setter private int id;
	@Getter @Setter private String description;
	@Getter @Setter private double priceFactor;

}
