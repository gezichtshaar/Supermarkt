package Actors;

import java.util.Random;

import Interfaces.Buyzone;
import Models.Route;
import Supermarkt.Supermarket;

public class NewDutchCostumer extends Costumer {
	private int sleepyTimer;

	public NewDutchCostumer(Route<Buyzone> locatie) {
		super(locatie);
		this.sleepyTimer = getNextSleepAmount();
	}

	@Override
	public void doAct(Supermarket supermarket) {
		if (--sleepyTimer <= 0) {
			location = location.getRandomRoute();
			sleepyTimer = getNextSleepAmount();
		}
	}
	
	private int getNextSleepAmount() {
		return new Random().nextInt(10);
	}
}