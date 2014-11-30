package com.supermarket.models;

import java.util.Stack;

public class Shelf {
	private final Product.Types type;
	private final Stack<Product> products;
	
	public Shelf(Product.Types type) {
		this.type = type;
		this.products = new Stack<Product>();
	}

	private boolean addProduct(Product product) {
		if (product.getType() == this.type) {
			this.products.push(product);
			return true;
		}
		return false;
	}
}
