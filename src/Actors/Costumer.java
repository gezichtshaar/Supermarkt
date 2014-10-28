package Actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interfaces.Actor;
import Interfaces.Buyzone;
import Models.Product;
import Models.ProductTypes;
import Models.Route;
import Supermarket.Supermarket;

public abstract class Costumer implements Actor {
	protected Route route;
	protected Map<ProductTypes, Integer> wishlist;
	protected List<Product> shoppingCart;
	private BigDecimal balance;

	public Costumer(Route location) {
		this(location, 10);
	}

	public Costumer(Route location, double balance) {
		this.route = location;
		this.wishlist = new HashMap<ProductTypes, Integer>();
		this.shoppingCart = new ArrayList<Product>();
		this.balance = new BigDecimal(balance);
	}

	public final void act(Supermarket supermarket) {
		preAct(supermarket);
		doAct(supermarket);
		postAct(supermarket);
	}

	private final void preAct(Supermarket supermarket) {
		if (!this.route.getLocation().inQueue(this) && this.wantsProductFromBuyzone(this.route.getLocation())) {
			this.route.getLocation().registerQueue(this);
		}
	}

	protected abstract void doAct(Supermarket supermarket);

	private final void postAct(Supermarket supermarket) {
		if (this.route == null) {
			supermarket.checkout(this);
		}
	}
	
	private boolean wantsProductFromBuyzone(Buyzone buyzone) {
		for(ProductTypes productType : this.wishlist.keySet()) {
			if (buyzone.hasProduct(productType)) {
				return true;
			}
		}
		return false;
	}
	
	public final void update(Buyzone buyzone) {
		for(ProductTypes productType : this.wishlist.keySet()) {
			if (buyzone.hasProduct(productType)) {
				this.addProducts(buyzone.takeProducts(this, productType, wishlist.get(productType)));
			}
		}
	}
	
	public void addProducts(List<Product> products) {
		shoppingCart.addAll(products);
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void takeFromBalance(BigDecimal amount) {
		this.balance = this.balance.subtract(amount);
	}

	public List<Product> getShoppingCart() {
		return shoppingCart;
	}
	
	public Route getRoute() {
		return route;
	}
}
