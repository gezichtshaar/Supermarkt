package Actors;

import java.util.Random;

import Models.Route;
import Supermarket.Supermarket;
import Models.Product;

public class NewDutchCostumer extends Costumer {
	private int sleepyTimer;

	public NewDutchCostumer(Route location) {
		super(location, 19);
		wishlist.put(Product.Types.BREAD, 2);
		this.sleepyTimer = getNextSleepAmount();
	}

	@Override
	public void doAct(Supermarket supermarket) {
		if (--sleepyTimer <= 0) {
			route = route.getRandomRoute();
			sleepyTimer = getNextSleepAmount();
		}
	}

	private int getNextSleepAmount() {
		return new Random().nextInt(10);
	}
}
