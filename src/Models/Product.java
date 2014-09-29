package Models;

import java.math.BigDecimal;

public class Product {
	private BigDecimal prijs;
	private BigDecimal korting;

	public Product(BigDecimal prijs) {
		this(prijs, new BigDecimal(1));
	}
	
	public Product(BigDecimal prijs, BigDecimal korting) {
		this.prijs = prijs;
		this.korting = korting;
	}
	
	public BigDecimal getPrijs() {
		return prijs.multiply(korting);
	}
}
