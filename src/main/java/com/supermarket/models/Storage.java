package com.supermarket.models;

import java.util.EnumMap;
import java.util.Stack;

import com.supermarket.models.Product.Types;

public class Storage {
	private static final int MAX_STORAGE_PER_PRODUCT = 1000;

	private final EnumMap<Product.Types, Stack<Product>> productCount;

	public Storage() {
		this.productCount = new EnumMap<Product.Types, Stack<Product>>(Product.Types.class);
		this.initialize();
		this.fill();
	}
	
	private void initialize() {
		for(Product.Types type : Product.Types.values()) {
			this.addProductType(type);
		}
	}
	
	public boolean hasProduct(Product.Types type) {
		return this.productCount.containsKey(type) && this.productCount.get(type).size() > 0;
	}
	
	public void addProductType(Product.Types type) {
		if (!this.productCount.containsKey(type)) {
			this.productCount.put(type, new Stack<Product>());
		}
	}

	public void fill() {
		for (Product.Types type : productCount.keySet()) {
			for (int n = this.productCount.get(type).size(); n < MAX_STORAGE_PER_PRODUCT; n++) {
				this.productCount.get(type).push(new Product(type));
			}
		}
	}

	public Product getProduct(Types type) {
		if (this.productCount.containsKey(type)) {
			return this.productCount.get(type).pop();
		}
		return null;
	}

	public void addProduct(Product product) {
		this.addProductType(product.getType());
		this.productCount.get(product.getType()).add(product);
	}
}
