package com.supermarket.actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import com.supermarket.interfaces.Actor;
import com.supermarket.interfaces.BuyZone;
import com.supermarket.main.Supermarket;
import com.supermarket.models.Location;
import com.supermarket.models.Product;

public class Customer implements Actor {
	private Location location;
	private final EnumMap<Product.Types, Integer> wishList;
	private final List<Product> shoppingCart;
	private BigDecimal wallet;
	
	public Customer(Location location) {
		this.location = location;
		this.wishList = new EnumMap<Product.Types, Integer>(Product.Types.class);
		this.shoppingCart = new ArrayList<Product>();
		this.wallet = new BigDecimal(0);
	}

	public boolean act(Supermarket supermarket) {
		if (this.location == null) {
			return true;
		}
		
		if (!this.location.getLocation().inQueue(this) && hasWishlistProduct(this.location.getLocation())) {
			this.location = this.location.next();
		}
		return false;
	}
	
	private boolean hasWishlistProduct(BuyZone buyZone) {
		for (Product.Types type : wishList.keySet()) {
			if (wishList.get(type).intValue() > 0 && buyZone.hasProduct(type)) {
				return true;
			}
		}
		return false;
	}
	
	public void payed(BigDecimal amount) {
		this.wallet = wallet.subtract(amount);
	}
	
	public boolean hasMoney(BigDecimal amount) {
		return wallet.compareTo(amount) > 0;
	}

	public List<Product> getShoppingCart() {
		return shoppingCart;
	}

	public int wantsProductAmount(Product.Types type) {
		if (wishList.containsKey(type)) {
			return wishList.get(type);
		}
		return 0;
	}

	public void addProducts(List<Product> takeProducts) {
		this.shoppingCart.addAll(takeProducts);
	}
}
