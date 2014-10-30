package Actors;

import Models.Route;
import Supermarket.Supermarket;

public class StudentCostumer extends Costumer {

	public StudentCostumer(Route location) {
		super(location);
	}

	@Override
	protected void doAct(Supermarket supermarket) {
		this.route = route.getRandomRoute();
	}
}
