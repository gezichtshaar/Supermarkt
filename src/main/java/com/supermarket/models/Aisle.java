package com.supermarket.models;

import java.util.ArrayList;
import java.util.List;

import com.supermarket.interfaces.BuyZone;

public class Aisle implements BuyZone {
	private final List<Shelf> shelves;
	
	public Aisle() {
		this.shelves = new ArrayList<Shelf>();
	}

	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
}
