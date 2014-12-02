package com.supermarket.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.supermarket.actors.Customer;
import com.supermarket.interfaces.BuyZone;
import com.supermarket.main.Supermarket;
import com.supermarket.models.Product.Types;

public class Department implements BuyZone {
	private static final int MAX_EMPLOYEES = 1;
	
	private final Shelf shelf;
	private final Queue<Customer> customers;

	public Department(Product.Types type) {
		this.shelf = new Shelf(type);
		this.customers = new ConcurrentLinkedQueue<Customer>();
	}

	public boolean inQueue(Customer customer) {
		return customers.contains(customer);
	}

	public void registerToQueue(Customer customer) {
		if(!inQueue(customer)) {
			customers.add(customer);
		}
	}

	public void doTask(Supermarket supermarket) {
		if (customers.size() > 0 && !shelf.isEmpty()) {
			Customer customer = this.customers.poll();
			if (customer.getBuyZone() == this) {
				customer.addProducts(this.shelf.takeProducts(customer.wantsProductAmount(this.shelf.getType())));
			}
		}else{
			shelf.fill(supermarket.getStorage());
		}
	}

	public int getPriority() {
		return (shelf.needsRefill() ? shelf.MIN_PRODUCT_COUNT - shelf.getProductCount() : 0);
	}

	public boolean hasProduct(Types type) {
		return shelf.getType() == type && !shelf.isEmpty();
	}

	public int getMaxEmployees() {
		return MAX_EMPLOYEES;
	}

	public boolean hasQueue() {
		return true;
	}

	public List<Product> takeProduct(Types type, int amount) {
		return new ArrayList<Product>();
	}

	public boolean hasProducts(Set<Types> types) {
		return types.contains(shelf.getType()) && !shelf.isEmpty();
	}
}
