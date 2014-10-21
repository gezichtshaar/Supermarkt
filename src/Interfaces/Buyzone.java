package Interfaces;

import Models.Product;
import Models.ProductTypes;

public interface Buyzone {
	public boolean hasProduct(ProductTypes productType);
	public Product takeProduct(ProductTypes productType);
}
