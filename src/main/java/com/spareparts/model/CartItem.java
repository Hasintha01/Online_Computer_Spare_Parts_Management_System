package com.spareparts.model;

public class CartItem {
	// OOP Concept: Encapsulation
	private int cartItemId;
	private int cartId;
	private int productId;
	private int quantity;

	// For holding the full Product object
	private Product product;

	// Default constructor
	public CartItem() {
	}

	// Existing parameterized constructor
	public CartItem(int cartItemId, int cartId, int productId, int quantity) {
		this.cartItemId = cartItemId;
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
	}

	// New constructor matching servlet usage
	public CartItem(Product product, int quantity) {
		this.product = product;
		this.productId = product.getProductId(); // keep productId in sync
		this.quantity = quantity;
	}

	// Getters and setters - OOP Concept: Encapsulation

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		if (product != null) {
			this.productId = product.getProductId();
		}
	}

	// toString method for debugging and logging
	@Override
	public String toString() {
		return "CartItem{" + "cartItemId=" + cartItemId + ", cartId=" + cartId + ", productId=" + productId
				+ ", quantity=" + quantity + ", product=" + product + '}';
	}

	public void setUserId(int int1) {
		// TODO Auto-generated method stub

	}

	public int getUserId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
