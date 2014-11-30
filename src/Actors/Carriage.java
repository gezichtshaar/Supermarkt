package Actors;

import Interfaces.Actor;
import Models.Product;
import Models.Storage;
import Supermarket.Options;
import Supermarket.Supermarket;

public class Carriage implements Actor {
	private int waitTime;
	
	public Carriage() {
		this.waitTime = Options.CARRIAGE_WAIT_TIME;
	}

	@Override
	public void act(Supermarket supermarket) {
		if (waitTime++ >= Options.CARRIAGE_WAIT_TIME) {
			fillStorage(supermarket.getStorage());
			this.waitTime = 0;
		}
	}
	
	private void fillStorage(Storage storage) {
		for(int n = 0; n < Options.CARRIAGE_MAX_PRODUCT_TYPES; n++) {
			for(int x = 0; x < Options.CARRIAGE_MAX_PRODUCTS; x++) {
				storage.addProduct(new Product(Product.Types.getRandomProductType()));
			}
		}
	}
}
