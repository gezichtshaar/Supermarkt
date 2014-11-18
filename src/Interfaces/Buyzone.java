package Interfaces;

import java.util.List;

import Actors.Costumer;
import Models.Product;

public interface Buyzone {
	public boolean hasProduct(Product.Types productType);
	
	public boolean inQueue(Costumer costumer);
	public void registerQueue(Costumer costumer);

	public List<Product> takeProducts(Costumer costumer, Product.Types productType, int amount);
}
