package Actors;

import Models.Route;
import Supermarket.Supermarket;

public class NonBinaryGenderCostumer extends Costumer {

	public NonBinaryGenderCostumer(Route location) {
		super(location, 5);
	}

	@Override
	public void doAct(Supermarket supermarket) {
		if (!route.getLocation().inQueue(this)){
			this.route = route.getRandomRoute();
		}
	}
}
