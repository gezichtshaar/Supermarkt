package com.supermarket.models;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.supermarket.actors.Customer;
import com.supermarket.interfaces.BuyZone;
import com.supermarket.models.Product.Types;

public class Department implements BuyZone {
	private static final int MAX_EMPLOYEES = 5;
	
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

	public void doTask() {
		if (customers.size() > 0) {
			Customer customer = this.customers.poll();
			customer.addProducts(this.shelf.takeProducts(customer.wantsProductAmount(this.shelf.getType())));
		}else{
			shelf.fill();
		}
	}

	public int getPriority() {
		return 0;
	}

	public boolean hasProduct(Types type) {
		return shelf.getType() == type && !shelf.isEmpty();
	}

	public int getMaxEmployees() {
		return MAX_EMPLOYEES;
	}
}
