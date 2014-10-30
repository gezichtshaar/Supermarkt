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
	
	private static final List<Types> discountProducts = new ArrayList<Types>();
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
		
		public static Types GetRandomDiscountProductType() {
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

	public void clear() {
	}
}
