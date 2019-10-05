package com.javaschool.webservices.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PackageTransport implements Serializable{

	private static final long serialVersionUID = 8539127243710282153L;
	
	private int id;
	private String description;
	private double pricePerMile;
}
