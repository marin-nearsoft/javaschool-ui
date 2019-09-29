package com.javaschool.webservices.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class PackageTransport implements Serializable{

	private static final long serialVersionUID = 8539127243710282153L;
	
	@Getter @Setter private int id;
	@Getter @Setter private String description;
	@Getter @Setter private double pricePerMile;
}
