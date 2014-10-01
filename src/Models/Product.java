package Models;

import java.math.BigDecimal;

public class Product {
	private String name;
	private BigDecimal price;
	private BigDecimal discount;

	public Product(String name, BigDecimal price) {
		this(name, price, new BigDecimal(1));
	}
	
	public Product(String name, BigDecimal price, BigDecimal discount) {
		this.name = name;
		this.price = price;
		this.discount = discount;
	}
	
	public BigDecimal getPrijs() {
		return price.multiply(discount);
	}
	
	@Override
	public String toString() {
		return String.format("Product: %s, Prijs: %f", name, price.floatValue());
	}
}
