package com.javaschool.webservices.model;

import java.io.Serializable;

public class PackageType implements Serializable{

	private static final long serialVersionUID = 8642607176960918131L;
	
	private int id;
	private String description;
	private float price;

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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
