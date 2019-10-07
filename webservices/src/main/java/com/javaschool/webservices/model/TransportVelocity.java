package com.javaschool.webservices.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransportVelocity implements Serializable {
	
	private static final long serialVersionUID = -3515366569175801552L;
	
	private int id;
	private String description;
	private double priceFactor;

}
