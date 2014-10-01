package Models;

import java.math.BigDecimal;

import Actors.Costumer;
import Interfaces.Task;
import Supermarket.Supermarket;

public class CashRegister implements Task {
	private BigDecimal balance;

	public CashRegister() {
		this.balance = new BigDecimal(0);
	}

	public void update(Supermarket supermarket) {
		Costumer costumer = supermarket.getKassaQueue().poll();
		if (costumer != null) {
			for (Product product : costumer.getShoppingCart()) {
				if (costumer.getBalance().compareTo(product.getPrijs()) > 0) {
					balance = balance.add(product.getPrijs());
					costumer.takeFromBalance(product.getPrijs());
				} else {
					supermarket.getMagazijn().addProduct(product);
				}
			}
		}
	}
}
