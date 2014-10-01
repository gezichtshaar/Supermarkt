package Actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Interfaces.Buyzone;
import Interfaces.Person;
import Models.Route;
import Models.Product;
import Supermarket.Supermarket;

public abstract class Costumer implements Person {
	protected Route<Buyzone> location;
	private List<Product> shoppingCart;
	private BigDecimal balance;

	public Costumer(Route<Buyzone> location) {
		this(location, 10f);
	}

	public Costumer(Route<Buyzone> location, float balance) {
		this.location = location;
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
			supermarket.afrekenen(this);
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
