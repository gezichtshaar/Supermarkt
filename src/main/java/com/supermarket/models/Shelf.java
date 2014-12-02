package com.supermarket.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Shelf {
	public static final int MIN_PRODUCT_COUNT = 5;
	public static final int MAX_PRODUCT_COUNT = 100;
	
	private final Product.Types type;
	private final Stack<Product> products;
	
	public Shelf(Product.Types type) {
		this.type = type;
		this.products = new Stack<Product>();
	}

	public boolean addProduct(Product product) {
		if (product.getType() == this.type) {
			this.products.push(product);
			return true;
		}
		return false;
	}
	
	public int getProductCount() {
		return products.size();
	}
	
	public Product.Types getType() {
		return this.type;
	}

	public boolean isEmpty() {
		return this.products.size() <= 0;
	}

	public List<Product> takeProducts(int wantsProductAmount) {
		List<Product> takenProducts = new ArrayList<Product>();
		while (wantsProductAmount --> 0 && !isEmpty()) {
			takenProducts.add(products.pop());
		}
		return takenProducts;
	}

	public void fill(Storage storage) {
		while(storage.hasProduct(type) && products.size() < MAX_PRODUCT_COUNT) {
			this.products.push(storage.getProduct(type));
		}
	}

	public boolean needsRefill() {
		return products.size() < MIN_PRODUCT_COUNT;
	}
}
