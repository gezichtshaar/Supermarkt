package com.supermarket.interfaces;

import java.util.List;

import com.supermarket.actors.Customer;
import com.supermarket.models.Product;

public interface BuyZone extends Task {
	public boolean hasQueue();
	public boolean inQueue(Customer customer);
	public void registerToQueue(Customer customer);
	public List<Product> takeProduct(Product.Types type, int amount);
	
	public boolean hasProduct(Product.Types type);
}
