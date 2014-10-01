package Models;

import Actors.Costumer;
import Interfaces.Task;
import Supermarkt.Supermarket;

public class CashRegister implements Task {

	public CashRegister() {
		// TODO Auto-generated constructor stub
	}
	
	public void update(Supermarket supermarket) {
		Costumer costumer = supermarket.getKassaQueue().poll();
		if (costumer != null) {
			for(Product product : costumer.getShoppingCart()) {
				if (costumer.getBalance().compareTo(product.getPrijs()) > 0) {
					costumer.takeFromBalance(product.getPrijs());
				}else{
					supermarket.getMagazijn().addProduct(product);
				}
			}
		}
	}
}
