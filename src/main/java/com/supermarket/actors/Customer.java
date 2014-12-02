package com.supermarket.actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.supermarket.interfaces.Actor;
import com.supermarket.interfaces.BuyZone;
import com.supermarket.main.Supermarket;
import com.supermarket.models.Location;
import com.supermarket.models.Product;

public class Customer implements Actor {
	private static final double MAX_WALLET_SIZE = 20;
	private static final int MAX_PRODUCTTYPE_COUNT = 3;
	private static final int MAX_RPODUCT_COUNT = 10;
	
	private Location location;
	private final Map<Product.Types, Integer> wishList; //needs to be fixed
	private final List<Product> shoppingCart;
	private BigDecimal wallet;
	
	public Customer(Location location, Map<Product.Types, Integer> wishlist, double wallet) {
		this.location = location;
		this.wishList = wishlist;
		this.shoppingCart = new ArrayList<Product>();
		this.wallet = new BigDecimal(wallet);
	}

	public boolean act(Supermarket supermarket) {
		if (this.location == null) {
			return true;
		}
		
		BuyZone buyZone = this.location.getLocation();
		if (buyZone.hasProducts(wishList.keySet())) {
			Product.Types type = wantsBuyZoneProduct(buyZone);
			if (buyZone.hasQueue() && !buyZone.inQueue(this) && type != null) {
				buyZone.registerToQueue(this);
			}else if(!buyZone.hasQueue() && type != null) {
				addProducts(buyZone.takeProduct(type, wishList.get(type)));
			}else{
				this.location = this.location.next(wishList.keySet());
			}
		}else{
			this.location = this.location.next(wishList.keySet());
		}
		return false;
	}
	
	private Product.Types wantsBuyZoneProduct(BuyZone buyZone) {
		for (Product.Types type : wishList.keySet()) {
			if (wishList.get(type).intValue() > 0 && buyZone.hasProduct(type)) {
				return type;
			}
		}
		return null;
	}
	
	public void payed(BigDecimal amount) {
		this.wallet = wallet.subtract(amount);
	}
	
	public boolean hasMoney(BigDecimal amount) {
		return wallet.compareTo(amount) > 0;
	}

	public List<Product> getShoppingCart() {
		return this.shoppingCart;
	}

	public int wantsProductAmount(Product.Types type) {
		if (wishList.containsKey(type)) {
			return wishList.get(type);
		}
		return 0;
	}

	public void addProducts(List<Product> takeProducts) {
		for(Product product : takeProducts) {
			this.wishList.put(product.getType(), new Integer(this.wishList.get(product.getType()) - 1));
			this.shoppingCart.add(product);
		}
	}
	
	public static Customer Create(Location location) {
		Map<Product.Types, Integer> wishlist = new EnumMap<Product.Types, Integer>(Product.Types.class);
		for (int n = new Random().nextInt(MAX_PRODUCTTYPE_COUNT); n > 0; n--) {
			Product.Types type = Product.Types.values()[new Random().nextInt(Product.Types.values().length)];
			if (!wishlist.containsKey(type)) {
				wishlist.put(type , new Random().nextInt(MAX_RPODUCT_COUNT));
			}
		}
		return new Customer(location, wishlist, new Random().nextDouble() * MAX_WALLET_SIZE);
	}

	public BuyZone getBuyZone() {
		return location.getLocation();
	}
}
