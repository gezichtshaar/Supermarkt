package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Storage {
	private Map<Product.Types, Stack<Product>> products;

	public Storage() {
		this.products = new HashMap<Product.Types, Stack<Product>>();
	}
	
	private boolean hasProductType(Product.Types productType) {
		return this.products.containsKey(productType) && this.products.get(productType).size() > 0;
	}
	
	public void addProductType(Product.Types productType) {
		if (!products.containsKey(productType)) {
			products.put(productType, new Stack<Product>());
		}
	}

	public void addProduct(Product product) {
		addProductType(product.getType());
		products.get(product.getType()).add(product);
	}
	
	public List<Product> takeProducts(Product.Types productType, int amount) {
		List<Product> productList = new ArrayList<Product>();
		for (int n = 0; n < amount && hasProductType(productType); n++) {
			productList.add(this.products.get(productType).pop());
		}
		return productList;
	}
}
