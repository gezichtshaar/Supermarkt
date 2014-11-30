package com.supermarket.actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import com.supermarket.interfaces.Actor;
import com.supermarket.main.Supermarket;
import com.supermarket.models.Location;
import com.supermarket.models.Product;

public class Costumer implements Actor {
	private Location location;
	private final EnumMap<Product.Types, Integer> wishList;
	private final List<Product> shoppingCart;
	private BigDecimal wallet;
	
	public Costumer(Location location) {
		this.location = location;
		this.wishList = new EnumMap<Product.Types, Integer>(Product.Types.class);
		this.shoppingCart = new ArrayList<Product>();
		this.wallet = new BigDecimal(0);
	}

	public void act(Supermarket supermarket) {
		
	}
}
