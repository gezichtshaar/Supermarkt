package Models;

import java.math.BigDecimal;
import java.util.Queue;

import Actors.Costumer;
import Interfaces.Task;
import Supermarket.Supermarket;

public class CashRegister implements Task {
	private Queue<Costumer> queue;
	private BigDecimal balance;

	public CashRegister(Queue<Costumer> queue) {
		this.queue = queue;
		this.balance = new BigDecimal(0);
	}

	public void update(Supermarket supermarket) {
		Costumer costumer = queue.poll();
		if (costumer != null) {
			for (Product product : costumer.getShoppingCart()) {
				if (costumer.getBalance().compareTo(product.getPrice()) > 0) {
					balance = balance.add(product.getPrice());
					costumer.takeFromBalance(product.getPrice());
				} else {
					supermarket.getStorage().addProduct(product);
				}
			}
		}
	}
}
