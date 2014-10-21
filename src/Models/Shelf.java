package Models;

import java.util.List;
import java.util.Stack;

class Shelf {
	private ProductTypes productType;
	private Stack<Product> products;

	public Shelf(ProductTypes productType) {
		this.productType = productType;
		this.products = new Stack<Product>();
	}
	
	public Product takeProduct() {
		if (!products.isEmpty()) {
			return products.pop();
		}
		return null;
	}
	
	public boolean addProducts(List<Product> product) {
		return product.addAll(product);
	}
	
	public boolean hasProduct(ProductTypes productType) {
		return this.productType == productType && !products.isEmpty();
	}
}
