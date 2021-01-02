package com.poc.purchaseOrder1.models;

public class Product {
	
	private int productid;
	private String name;
	private String description;
	private String manufacturer;
	private int price;
	
	public Product() {
		super();
	}

	public Product(int productid, String name, String description, String manufacturer, int price) {
		super();
		this.productid = productid;
		this.name = name;
		this.description = description;
		this.manufacturer = manufacturer;
		this.price = price;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}



