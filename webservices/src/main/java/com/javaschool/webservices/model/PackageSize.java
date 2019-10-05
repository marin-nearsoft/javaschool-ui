package com.javaschool.webservices.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PackageSize implements Serializable {

	private static final long serialVersionUID = 865846279570747807L;
	
	private int id;
	private String description;
	private double priceFactor;

}
