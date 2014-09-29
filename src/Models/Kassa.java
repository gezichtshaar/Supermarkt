package Models;

import Actors.Klant;
import Interfaces.Task;
import Supermarkt.Supermarkt;

public class Kassa implements Task {

	public Kassa() {
		// TODO Auto-generated constructor stub
	}
	
	public void update(Supermarkt supermarkt) {
		Klant klant = supermarkt.getKassaQueue().poll();
		if (klant != null) {
			for(Product product : klant.getWinkelwagen()) {
				if (klant.getSaldo().compareTo(product.getPrijs()) > 0) {
					klant.takeFromSaldo(product.getPrijs());
				}else{
					supermarkt.getMagazijn().addProduct(product);
				}
			}
		}
	}
}
