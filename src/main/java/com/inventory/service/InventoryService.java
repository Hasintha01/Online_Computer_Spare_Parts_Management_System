package com.inventory.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import javax.swing.JOptionPane;

import com.inventory.model.Product;
import com.inventory.util.DBConnection;

public class InventoryService {
	
	//private static fields not directly accesible
	private static boolean isSuccess;
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;

	// Create Product  //parameters(add product servlet eke)
	//public methods use to provide controll access to db operations
	public static boolean CreateInventory(String name, String category, double price, int stock, String image) {
		boolean isSuccess = false;

		try {
			conn = DBConnection.getConnection();
			System.out.println("got connected");
			stmt = conn.createStatement();
			String query = "INSERT INTO inventory VALUES (0,'" + name + "','" + category + "','" + price + "','" + stock
					+ "','" + image + "')";

			int rs = stmt.executeUpdate(query);

			if (rs > 0) {
				isSuccess = true;
			} else {
				isSuccess = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	// Get Product by ID for update product
	public static List<Product> getProduct(String Id) {
		int convertedID = Integer.parseInt(Id);
		ArrayList<Product> products = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("got connected");
			stmt = conn.createStatement();
			String query = "SELECT * FROM inventory WHERE id ='" + convertedID + "'";
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String category = rs.getString(3);
				Double price = rs.getDouble(4);
				int stock = rs.getInt(5);
				String image = rs.getString(6);
				Product p = new Product(id, name, category, price, stock, image);
				products.add(p);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	// Get All Products
	public static List<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("got connected");
			stmt = conn.createStatement();
			String query = "SELECT * FROM inventory";

			rs = stmt.executeQuery(query); // create result set to run query
			System.out.println("executed");

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String category = rs.getString(3);
				Double price = rs.getDouble(4);
				int stock = rs.getInt(5);
				String image = rs.getString(6);
				Product product = new Product(id, name, category, price, stock, image);
				products.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace(); // print error
		}
		return products;
	}

	// Update Product
	public static boolean updateProduct(String id, String name, String category, double price, int stock,
			String image) {

		try {
			conn = DBConnection.getConnection();
			System.out.println("got connected");
			stmt = conn.createStatement();
			String query = "UPDATE inventory SET name ='" + name + "', category ='" + category + "',price= '" + price
					+ "',stock= '" + stock + "', image = '" + image + "' WHERE id = '" + id + "'";
			int rs = stmt.executeUpdate(query);

			if (rs > 0) {
				isSuccess = true;
			} else {
				isSuccess = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;

	}

	// Delete Product
	public static boolean deleteProduct(String id) {
		int proId = Integer.parseInt(id);

		try {
			conn = DBConnection.getConnection();
			System.out.println("got connected");
			stmt = conn.createStatement();
			String query = "DELETE FROM inventory WHERE id = '" + proId + "'";
			int r = stmt.executeUpdate(query);

			if (r > 0) {
				isSuccess = true;
			} else {
				isSuccess = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
}