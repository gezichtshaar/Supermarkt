package Models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Product {
	private Types productType;

	public Product(Types productType) {
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

	public Types getType() {
		return productType;
	}
	
	private static final List<Types> ALL_PRODUCTS = new ArrayList<Types>();
	private static final List<Types> DISCOUNT_PRODUCTS = new ArrayList<Types>();
	public enum Types {
		BREAD("Brood", 2f);
		
		private final String name;
		private BigDecimal price;
		private BigDecimal discount;

		private Types(String name, double price) {
			this(name, price, 1);
		}

		private Types(String name, double price, double discount) {
			this.name = name;
			this.price = new BigDecimal(price);
			this.setDiscount(new BigDecimal(discount));
			
			ALL_PRODUCTS.add(this);
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
				DISCOUNT_PRODUCTS.remove(this);
			}else{
				DISCOUNT_PRODUCTS.add(this);
			}
		}
		
		public boolean hasDiscount() {
			return !this.discount.equals(new BigDecimal(1));
		}
		
		public static Types GetRandomDiscountProductType() {
			if (DISCOUNT_PRODUCTS.size() > 0) {
				return DISCOUNT_PRODUCTS.get(new Random().nextInt(DISCOUNT_PRODUCTS.size()));
			}
			return null;
		}

		public static boolean HasDiscountProducts() {
			return DISCOUNT_PRODUCTS.size() > 0;
		}
		
		public static Types getRandomProductType() {
			if (ALL_PRODUCTS.size() > 0) {
				return ALL_PRODUCTS.get(new Random().nextInt(ALL_PRODUCTS.size()));
			}
			return null;
		}
		
		@Override
		public String toString() {
			return String.format("Product: %s, Prijs: %f", name, price.floatValue());
		}
	}

	public void clear() {
	}
}
