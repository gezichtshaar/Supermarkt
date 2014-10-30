package Models;

import java.util.ArrayList;
import java.util.List;

import Supermarket.Options;
import Actors.Costumer;
import Interfaces.Buyzone;
import Interfaces.Task;
import Supermarket.Supermarket;

public class Aisle implements Task, Buyzone {
	protected List<Shelf> shelves;
	private int lastUpdatedShelf;
	
	public Aisle () {
		this(new Product.Types[] {});
	}

	public Aisle(Product.Types[] productTypes) {
		this.shelves = new ArrayList<Shelf>();
		this.lastUpdatedShelf = 0;
		
		for(Product.Types productType : productTypes) {
			addShelf(new Shelf(productType));
		}
	}
	
	public void addShelf(Shelf shelf) {
		shelves.add(shelf);
	}

	@Override
	public void update(Supermarket supermarket) {
		int currentShelf;
		for (int n = 0; n < shelves.size(); n++) {
			currentShelf = (n + lastUpdatedShelf) % shelves.size();
			if (shelves.get(currentShelf).productCount() < Options.SHELF_FILL_THRESHOLD) {
				
				lastUpdatedShelf = currentShelf + 1;
			}
		}
	}

	@Override
	public boolean hasProduct(Product.Types productType) {
		for(Shelf shelf : shelves) {
			if (shelf.hasProduct(productType)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean inQueue(Costumer costumer) {
		return false;
	}

	@Override
	public void registerQueue(Costumer costumer) {
		costumer.update(this);
	}

	@Override
	public List<Product> takeProducts(Costumer costumer,
			Product.Types productType, int amount) {
		return null;
	}

	@Override
	public int getPriority() {
		int priority = 0;
		for (Shelf shelf : shelves) {
			if (shelf.productCount() < Options.SHELF_FILL_THRESHOLD) {
				priority += Options.SHELF_FILL_PRIORITY;
			}
		}
		return priority;
	}
	
	@Override
	public String toString() {
		return String.format("Aisle: %d", getPriority());
	}
}
