package com.supermarket.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.supermarket.actors.Customer;
import com.supermarket.interfaces.BuyZone;
import com.supermarket.models.Product.Types;

public class Aisle implements BuyZone {
	private static final int MAX_EMPLOYEES = 5;
	private static final int SHELF_REFILL_PRIORITY = 1;
	
	private final List<Shelf> shelves;
	
	public Aisle(Product.Types[] types) {
		this.shelves = new ArrayList<Shelf>();
		this.createShelves(types);
	}
	
	private void createShelves(Product.Types[] types) {
		for (Product.Types type : types) {
			Shelf shelf = new Shelf(type);
			shelf.fill();
			this.shelves.add(shelf);
		}
	}

	public boolean inQueue(Customer customer) {
		return false;
	}

	public void registerToQueue(Customer customer) {
	}

	public void doTask() {
		for (Shelf shelf : shelves) {
			
		}
	}
	
	public int getPriority() {
		int priority = 0;
		for(Shelf shelf : shelves) {
			if (shelf.needsRefill()) {
				priority += SHELF_REFILL_PRIORITY;
			}
		}
		return priority;
	}

	public boolean hasProduct(Product.Types type) {
		for (Shelf shelf : shelves) {
			if (shelf.getType() == type && !shelf.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public int getMaxEmployees() {
		return MAX_EMPLOYEES;
	}
}
