package com.supermarket.models;

import javax.persistence.*;

@Entity
@Table(name = "Transactions")
public class Transaction {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;
	@Column(name = "Price")
	private int price;
	@Column(name = "ProductId")
	private int productId;

	// Maybe a transaction DateTime?
	public Transaction() {
	}

	public Transaction(int price, int productId) {
		this.price = price;
		this.productId = productId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getProductIds() {
		return productId;
	}

	public void setProductIds(int productId) {
		this.productId = productId;
	}
}