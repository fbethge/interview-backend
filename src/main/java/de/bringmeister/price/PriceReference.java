package de.bringmeister.price;

public class PriceReference {
	
	private String id;
	private Price price;
	private String unit;
	
	public PriceReference() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "ProductReference [id=" + id + ", price=" + price + ", unit=" + unit + "]";
	}
}
