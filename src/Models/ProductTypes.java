package Models;

import java.math.BigDecimal;

public enum ProductTypes {
	BREAD("Brood", 2f);
	
	private final String name;
	private BigDecimal price;
	private BigDecimal discount;

	private ProductTypes(String name, double price) {
		this(name, price, 1);
	}

	private ProductTypes(String name, double price, double discount) {
		this.name = name;
		this.price = new BigDecimal(price);
		this.discount = new BigDecimal(discount);
	}
	
	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public BigDecimal getDiscount() {
		return discount;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return String.format("Product: %s, Prijs: %f", name, price.floatValue());
	}
}
