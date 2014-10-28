package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Storage {
	private Map<ProductTypes, Stack<Product>> products;

	public Storage() {
		this.products = new HashMap<ProductTypes, Stack<Product>>();
	}
	
	public void productType(ProductTypes productType) {
		if (!products.containsKey(productType)) {
			products.put(productType, new Stack<Product>());
		}
	}

	public void addProduct(Product product) {
		if (products.containsKey(product.getType())) {
			products.get(product.getType()).add(product);
		}
	}
	
	public List<Product> takeProducts(ProductTypes productType, int amount) {
		List<Product> productList = new ArrayList<Product>();
		for (int n = 0; n < amount && products.containsKey(productType); n++) {
			productList.add(this.products.get(productType).pop());
		}
		return productList;
	}
}
