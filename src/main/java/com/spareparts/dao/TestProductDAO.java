package com.spareparts.dao;

import com.spareparts.model.Product;

import java.util.List;

public class TestProductDAO {

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        // Test getAllProducts()
        System.out.println("Testing getAllProducts():");
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product p : products) {
                System.out.println(p.getProductId() + ": " + p.getName() + " - $" + p.getPrice());
            }
        }

        // Test getProductById()
        System.out.println("\nTesting getProductById(1):");
        Product product = productDAO.getProductById(1);
        if (product != null) {
            System.out.println(product.getProductId() + ": " + product.getName() + " - $" + product.getPrice());
        } else {
            System.out.println("Product with ID 1 not found.");
        }

        // Optional: Test insertProduct()
        /*
        Product newProduct = new Product();
        newProduct.setName("Test Product");
        newProduct.setDescription("Test Description");
        newProduct.setPrice(99.99);
        newProduct.setStock(50);
        newProduct.setImageUrl("test.jpg");
        boolean inserted = productDAO.insertProduct(newProduct);
        System.out.println("Insert product: " + (inserted ? "Success" : "Failed"));
        */

        // Optional: Test updateProduct() and deleteProduct() similarly
    }
}
