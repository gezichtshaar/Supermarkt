package Models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ProductTypes")
public enum ProductTypes {
	BREAD("Brood", 2f);
	
	private static final List<ProductTypes> discountProducts = new ArrayList<ProductTypes>();
	
	@Column(name = "Name")
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
		if (!this.hasDiscount()) {
			discountProducts.remove(this);
		}else{
			discountProducts.add(this);
		}
	}
	
	public boolean hasDiscount() {
		return !this.discount.equals(new BigDecimal(1));
	}
	
	public static ProductTypes GetRandomDiscountProductType() {
		if (discountProducts.size() > 0) {
			return discountProducts.get(new Random().nextInt(discountProducts.size()));
		}
		return null;
	}

	public static boolean HasDiscountProducts() {
		return discountProducts.size() > 0;
	}
	
	@Override
	public String toString() {
		return String.format("Product: %s, Prijs: %f", name, price.floatValue());
	}
}
