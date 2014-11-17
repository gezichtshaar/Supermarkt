package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Supermarket.Options;

class Shelf {
	private Product.Types productType;
	private Stack<Product> products;

	public Shelf(Product.Types productType) {
		this.productType = productType;
		this.products = new Stack<Product>();
	}
	
	public Product takeProduct() {
		if (!products.isEmpty()) {
			return products.pop();
		}
		return null;
	}
	
	public boolean addProduct(Product product) {
		if (product.getType() == this.productType) {
			products.push(product);
			return true;
		}
		return false;
	}
	
	public List<Product> addProducts(List<Product> productList) {
		for (Product product : productList) {
			if (product.getType() == productType && productCount() < Options.SHELF_MAX_PRODUCTS) {
				products.push(product);
			}
		}
		productList.removeAll(products);
		return productList;
	}
	
	public boolean hasProduct(Product.Types productType) {
		return this.productType == productType && !products.isEmpty();
	}
	
	public int productCount() {
		return products.size();
	}

	public List<Product> takeProducts(int amount) {
		List<Product> giveProducts = new ArrayList<Product>();
		for(int n = 0; n < amount && n < productCount(); n++) {
			giveProducts.add(products.pop());
		}
		return giveProducts;
	}

	public Product.Types getProductType() {
		return productType;
	}
}
