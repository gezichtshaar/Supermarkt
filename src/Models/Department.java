package Models;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import Actors.Costumer;
import Interfaces.Buyzone;
import Interfaces.Task;
import Supermarket.Options;
import Supermarket.Supermarket;

public class Department implements Task, Buyzone {
	private Shelf shelf;
	private Queue<Costumer> costumerQueue;

	public Department(ProductTypes productType) {
		this.shelf = new Shelf(productType);
		this.costumerQueue = new ConcurrentLinkedQueue<Costumer>();
	}

	@Override
	public void update(Supermarket supermarket) {
		if (shelf.productCount() < Options.SHELF_FILL_THRESHOLD) {
			fillDepartment(supermarket.getStorage());
		}else if (costumerQueue.size() > 0){
			handleQueue();
		}
	}
	
	private void fillDepartment(Storage storage) {
		shelf.addProducts(storage.takeProducts(shelf.getProductType(), Options.SHELF_MAX_PRODUCTS - shelf.productCount()));
	}
	
	private void handleQueue() {
		while(costumerQueue.size() > 0 && costumerQueue.peek().getRoute().getLocation() != this) {}
		
		if (costumerQueue.size() > 0) {
			costumerQueue.peek().update(this);
		}
	}

	@Override
	public boolean hasProduct(ProductTypes productType) {
		return shelf.hasProduct(productType);
	}

	@Override
	public boolean inQueue(Costumer costumer) {
		return costumerQueue.contains(costumer);
	}

	@Override
	public void registerQueue(Costumer costumer) {
		if (!inQueue(costumer)) {
			costumerQueue.add(costumer);
		}
	}

	@Override
	public List<Product> takeProducts(Costumer costumer,
			ProductTypes productType, int amount) {
		if (costumer == costumerQueue.peek()) {
			costumerQueue.poll();
			return shelf.takeProducts(amount);
		}
		return null;
	}
}
