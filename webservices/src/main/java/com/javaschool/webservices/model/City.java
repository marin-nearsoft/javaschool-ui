package com.javaschool.webservices.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class City implements Serializable {

	private static final long serialVersionUID = 1515141730624467517L;
	
	@Getter @Setter private int id;
	@Getter @Setter private String name;
	@Getter @Setter	private double tax;
	@Getter @Setter private boolean seaPort;
	@Getter @Setter private boolean airPort;
}
