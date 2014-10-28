package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import Supermarket.Options;

@Entity
@Table(name = "SHELF")
class Shelf {
	@Column(name = "ProductType")
	private ProductTypes productType;
	
	@Column(name = "Products")
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
				productList.remove(product);
			}
		}
		return productList;
	}
	
	public boolean hasProduct(ProductTypes productType) {
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

	public ProductTypes getProductType() {
		return productType;
	}
}
