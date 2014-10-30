package Models;

import java.math.BigDecimal;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import Actors.Costumer;
import Interfaces.Task;
import Supermarket.Options;
import Supermarket.Supermarket;

public class CashRegister implements Task {
	private Queue<Costumer> queue;
	private BigDecimal balance;

	public CashRegister() {
		this.queue = new ConcurrentLinkedQueue<Costumer>();
		this.balance = new BigDecimal(0);
	}
	
	public void addCostumer(Costumer costumer) {
		this.queue.add(costumer);
	}

	public void update(Supermarket supermarket) {
		Costumer costumer = queue.poll();
		if (costumer != null) {
			for (Product product : costumer.getShoppingCart()) {
				if (costumer.getBalance().compareTo(product.getPrice()) > 0) {
					balance = balance.add(product.getPrice());
					costumer.takeFromBalance(product.getPrice());
					product.clear();
				} else {
					supermarket.getStorage().addProduct(product);
				}
			}
		}
		costumer.clear();
	}

	@Override
	public int getPriority() {
		return queue.size() * Options.CASHREGISTER_PRIORITY;
	}

	@Override
	public int getMaxEmployeeCount() {
		return 1;
	}
}
