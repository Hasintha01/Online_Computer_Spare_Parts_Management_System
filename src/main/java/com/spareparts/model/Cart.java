package com.spareparts.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    /**
     * Add a CartItem to the cart.
     * If the product already exists, update its quantity.
     */
    public void addItem(CartItem newItem) {
        for (CartItem item : items) {
            if (item.getProductId() == newItem.getProductId()) {
                // Product exists, update quantity
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        // Product not in cart, add new item
        items.add(newItem);
    }

    /**
     * Update quantity of a product in the cart.
     * If quantity is zero or less, remove the item.
     * @param productId Product ID to update
     * @param quantity New quantity
     * @return true if updated or removed, false if product not found
     */
    public boolean updateItemQuantity(int productId, int quantity) {
        for (CartItem item : items) {
            if (item.getProductId() == productId) {
                if (quantity <= 0) {
                    items.remove(item);
                } else {
                    item.setQuantity(quantity);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Remove an item from the cart by product ID.
     * @param productId Product ID to remove
     * @return true if item was removed, false if not found
     */
    public boolean removeItem(int productId) {
        return items.removeIf(item -> item.getProductId() == productId);
    }

    /**
     * Clear all items from the cart.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Calculate total price of all items in the cart.
     * @return total price
     */
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
