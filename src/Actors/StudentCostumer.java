package Actors;

import Models.Product;
import Models.Route;
import Supermarket.Supermarket;

public class StudentCostumer extends Costumer {

	public StudentCostumer(Route location) {
		super(location, 20);
		this.wishlist.put(Product.Types.BREAD, 3);
	}

	@Override
	protected void doAct(Supermarket supermarket) {
		if (!route.getLocation().inQueue(this)){
			this.route = route.getRandomRoute();
		}
	}
}
