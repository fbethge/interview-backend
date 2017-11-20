package de.bringmeister.product;

import java.math.BigDecimal;

public class Product {
	
	private String id;
	private String name;
	private String description;
	private String sku;
	private String unit;
	private BigDecimal price;
	private String currency;

	public Product(String id, String name, String description, String sku, String unit, BigDecimal price, String currency) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.sku = sku;
		this.unit = unit;
		this.price = price;
		this.currency = currency;
	}
	public Product(String id, String name, String description, String sku) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.sku = sku;
	}

	public String getUnit() {
		return unit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public Product() {

	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", sku=" + sku + ", unit="
				+ unit + ", price=" + price + ", currency=" + currency + "]";
	}
}
