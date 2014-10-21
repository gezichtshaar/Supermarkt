package Models;

import Interfaces.Buyzone;
import Interfaces.Task;
import Supermarket.Supermarket;

public class Department implements Task, Buyzone {
	private Shelf shelf;

	public Department(ProductTypes productType) {
		this.shelf = new Shelf(productType);
	}

	@Override
	public void update(Supermarket supermarket) {
		// TODO Auto-generated method stub
	}

	@Override
	public Product takeProduct(ProductTypes productType) {
		if (shelf.hasProduct(productType)) {
			return shelf.takeProduct();
		}
		return null;
	}

	@Override
	public boolean hasProduct(ProductTypes productType) {
		return shelf.hasProduct(productType);
	}
}
