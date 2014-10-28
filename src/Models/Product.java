package Models;

import java.math.BigDecimal;

public class Product {
	private ProductTypes productType;

	public Product(ProductTypes productType) {
		this.productType = productType;
	}
	
	public String getName() {
		return productType.getName();
	}
	
	public BigDecimal getPrice() {
		return productType.getPrice();
	}
	
	public BigDecimal getRealPrice() {
		return productType.getPrice().multiply(productType.getDiscount());
	}

	public ProductTypes getType() {
		return productType;
	}
}
