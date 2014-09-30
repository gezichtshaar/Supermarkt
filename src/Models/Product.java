package Models;

import java.math.BigDecimal;

public class Product {
	private String naam;
	private BigDecimal prijs;
	private BigDecimal korting;

	public Product(String naam, BigDecimal prijs) {
		this(naam, prijs, new BigDecimal(1));
	}
	
	public Product(String naam, BigDecimal prijs, BigDecimal korting) {
		this.naam = naam;
		this.prijs = prijs;
		this.korting = korting;
	}
	
	public BigDecimal getPrijs() {
		return prijs.multiply(korting);
	}
	
	@Override
	public String toString() {
		return String.format("Product: %s, Prijs: %f", naam, prijs.floatValue());
	}
}
