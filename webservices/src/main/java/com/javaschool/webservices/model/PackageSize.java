package com.javaschool.webservices.model;

import java.io.Serializable;

public class PackageSize implements Serializable {

	private static final long serialVersionUID = 865846279570747807L;
	
	private int id;
	private String description;
	private double priceFactor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPriceFactor() {
		return priceFactor;
	}

	public void setPriceFactor(double priceFactor) {
		this.priceFactor = priceFactor;
	}

}
