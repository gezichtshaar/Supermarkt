package com.supermarket.models;

import java.math.BigDecimal;

public class Product {
	private final Types type;

	public Product(Types type) {
		this.type = type;
	}
	
	public BigDecimal getPrice() {
		return type.getPrice().multiply(type.discount);
	}
	
	public Types getType() {
		return type;
	}
	
	public enum Types {
		BREAD(1, 1);
		
		private BigDecimal price;
		private BigDecimal discount;
		private Types(double price, double discount) {
			this.price = new BigDecimal(price);
			this.discount = new BigDecimal(discount);
		}
		
		public BigDecimal getPrice() {
			return price;
		}
		
		public void setPrice(double price) {
			this.price = new BigDecimal(price);
		}
		
		public BigDecimal getDiscount() {
			return discount;
		}
		
		public void setDiscount(double discount) {
			this.discount = new BigDecimal(discount);
		}
	}
}
