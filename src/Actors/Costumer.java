package Actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Interfaces.Buyzone;
import Interfaces.Person;
import Models.Route;
import Models.Product;
import Supermarkt.Supermarket;

public abstract class Costumer implements Person{
	protected Route<Buyzone> location;
	private List<Product> shoppingCart;
	private BigDecimal balance;
	
	public Costumer(Route<Buyzone> locatie) {
		this.location = locatie;
		shoppingCart = new ArrayList<Product>();
		balance = new BigDecimal(10);//Needs fix
	}
	
	public final void act(Supermarket supermarket) {
		preAct(supermarket);
		act(supermarket);
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
