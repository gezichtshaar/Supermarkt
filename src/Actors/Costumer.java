package Actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interfaces.Actor;
import Models.Product;
import Models.ProductTypes;
import Models.Route;
import Supermarket.Supermarket;

public abstract class Costumer implements Actor {
	protected Route location;
	protected Map<ProductTypes, Integer> wishlist;
	protected List<Product> shoppingCart;
	private BigDecimal balance;

	public Costumer(Route location) {
		this(location, 10);
	}

	public Costumer(Route location, double balance) {
		this.location = location;
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
	}

	protected abstract void doAct(Supermarket supermarket);

	private final void postAct(Supermarket supermarket) {
		if (location == null) {
			supermarket.checkout(this);
		}
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
}
