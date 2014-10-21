package Models;

import java.util.ArrayList;
import java.util.List;

import Interfaces.Buyzone;
import Interfaces.Task;
import Supermarket.Supermarket;

public class Aisle implements Task, Buyzone {
	private List<Shelf> shelves;
	
	public Aisle () {
		this(new ProductTypes[] {});
	}

	public Aisle(ProductTypes[] productTypes) {
		this.shelves = new ArrayList<Shelf>();
		
		for(ProductTypes productType : productTypes) {
			addShelf(new Shelf(productType));
		}
	}
	
	public void addShelf(Shelf shelf) {
		shelves.add(shelf);
	}

	@Override
	public void update(Supermarket supermarket) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product takeProduct(ProductTypes productType) {
		for(Shelf shelf : shelves) {
			if (shelf.hasProduct(productType)) {
				return shelf.takeProduct();
			}
		}
		return null;
	}

	@Override
	public boolean hasProduct(ProductTypes productType) {
		for(Shelf shelf : shelves) {
			if (shelf.hasProduct(productType)) {
				return true;
			}
		}
		return false;
	}
}
