package Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import Supermarket.Options;
import Actors.Costumer;
import Interfaces.Buyzone;
import Interfaces.Task;
import Supermarket.Supermarket;

@Entity
@Table(name = "AISLE")
public class Aisle implements Task, Buyzone {
	@Column(name = "SHELVES")
	protected List<Shelf> shelves;
	private int lastUpdatedShelf;
	
	public Aisle () {
		this(new ProductTypes[] {});
	}

	public Aisle(ProductTypes[] productTypes) {
		this.shelves = new ArrayList<Shelf>();
		this.lastUpdatedShelf = 0;
		
		for(ProductTypes productType : productTypes) {
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
	public boolean hasProduct(ProductTypes productType) {
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
			ProductTypes productType, int amount) {
		return null;
	}
}
