package com.supermarket.interfaces;

import com.supermarket.actors.Customer;
import com.supermarket.models.Product;

public interface BuyZone extends Task {
	public boolean inQueue(Customer customer);
	public void registerToQueue(Customer customer);
	
	public boolean hasProduct(Product.Types type);
}
