package de.bringmeister.price;

public class Price {
	
	private String value;
	private String currency;
	
	public Price() {
	
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Price [value=" + value + ", currency=" + currency + "]";
	}
	
}
