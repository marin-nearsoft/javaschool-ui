package com.javaschool.webservices.model;

import java.io.Serializable;

public class PackageSize implements Serializable {

	private static final long serialVersionUID = 865846279570747807L;
	
	public int id;
	public String description;
	public float priceFactor;

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

	public float getPriceFactor() {
		return priceFactor;
	}

	public void setPriceFactor(float priceFactor) {
		this.priceFactor = priceFactor;
	}

}
