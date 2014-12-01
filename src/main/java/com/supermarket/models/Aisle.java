package com.supermarket.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.supermarket.actors.Customer;
import com.supermarket.interfaces.BuyZone;
import com.supermarket.main.Supermarket;
import com.supermarket.models.Product.Types;

public class Aisle implements BuyZone {
	private static final int MAX_EMPLOYEES = 5;
	private static final int SHELF_REFILL_PRIORITY = 1;
	
	private final List<Shelf> shelves;
	private int lastUpdatedShelf;
	
	public Aisle(Product.Types[] types) {
		this.shelves = new ArrayList<Shelf>();
		this.lastUpdatedShelf = 0;
		this.createShelves(types);
	}
	
	private void createShelves(Product.Types[] types) {
		for (Product.Types type : types) {
			this.shelves.add(new Shelf(type));
		}
	}

	public boolean inQueue(Customer customer) {
		return false;
	}

	public void registerToQueue(Customer customer) {
	}

	public void doTask(Supermarket supermarket) {
		for (int n = 0; n < shelves.size(); n++) {
			this.lastUpdatedShelf = ++this.lastUpdatedShelf % this.shelves.size();
			if (this.shelves.get(this.lastUpdatedShelf).needsRefill()) {
				this.shelves.get(this.lastUpdatedShelf).fill(supermarket.getStorage());
				n = shelves.size(); // muh hax
			}
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
