package com.supermarket.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javassist.expr.NewArray;

import com.supermarket.actors.Customer;
import com.supermarket.interfaces.BuyZone;
import com.supermarket.main.Supermarket;
import com.supermarket.models.Product.Types;

public class CashRegister implements BuyZone {
	private static final int PRIORITY_MODIFIER = 5;
	private static final int MAX_EMPLOYEES = 1;
	
	private final Queue<Customer> customers;
	private BigDecimal balance;

	public CashRegister() {
		this.customers = new ConcurrentLinkedQueue<Customer>();
		this.balance = new BigDecimal(0.0);
	}

	public boolean inQueue(Customer customer) {
		return this.customers.contains(customer);
	}

	public void registerToQueue(Customer customer) {
		if(!inQueue(customer)) {
			customers.add(customer);
		}
	}

	public void doTask(Supermarket supermarket) {
		if (this.customers.size() > 0) {
			List<Product> products = customers.peek().getShoppingCart();
			for(Product product : products) {
				if (customers.peek().hasMoney(product.getPrice())) {
					this.balance = this.balance.add(product.getPrice());
					supermarket.getDatabase().saveObject(new Transaction(product.getPrice().intValue(), product.getType().toString()));
					products.remove(product);
				}else{
					supermarket.getStorage().addProduct(product);
				}
			}
		}
	}


	public int getPriority() {
		return customers.size() * PRIORITY_MODIFIER;
	}

	public boolean hasProduct(Types type) {
		return true;
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
}
