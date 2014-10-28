package Interfaces;

import java.util.List;

import Actors.Costumer;
import Models.Product;
import Models.ProductTypes;

public interface Buyzone {
	public boolean hasProduct(ProductTypes productType);
	
	public boolean inQueue(Costumer costumer);
	public void registerQueue(Costumer costumer);

	public List<Product> takeProducts(Costumer costumer, ProductTypes productType, int amount);
}
