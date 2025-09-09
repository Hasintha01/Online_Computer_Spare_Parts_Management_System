package com.spareparts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.spareparts.model.Order;
import com.spareparts.utils.DBConnectionUtil;

public class OrderDAO {

	// Retrieve all orders for a specific user
	public List<Order> getOrdersByUserId(int userId) {
		List<Order> orders = new ArrayList<>();
		String sql = "SELECT order_id, user_id, order_date, total_amount FROM orders WHERE user_id = ?";

		try (Connection conn = DBConnectionUtil.getDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setOrderDate(rs.getTimestamp("order_date"));
					order.setTotalAmount(rs.getDouble("total_amount"));
					orders.add(order);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	// CRUD
	// Insert a new order into the database
	public boolean insertOrder(Order order) {
		String sql = "INSERT INTO orders (user_id, order_date, total_amount) VALUES (?, ?, ?)";

		try (Connection conn = DBConnectionUtil.getDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, order.getUserId());
			ps.setTimestamp(2, new java.sql.Timestamp(order.getOrderDate().getTime()));
			ps.setDouble(3, order.getTotalAmount());

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Update an existing order
	public boolean updateOrder(Order order) {
		String sql = "UPDATE orders SET user_id = ?, order_date = ?, total_amount = ? WHERE order_id = ?";

		try (Connection conn = DBConnectionUtil.getDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, order.getUserId());
			ps.setTimestamp(2, new java.sql.Timestamp(order.getOrderDate().getTime()));
			ps.setDouble(3, order.getTotalAmount());
			ps.setInt(4, order.getOrderId());

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Delete an order by its ID
	public boolean deleteOrder(int orderId) {
		String sql = "DELETE FROM orders WHERE order_id = ?";

		try (Connection conn = DBConnectionUtil.getDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, orderId);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Order> getOrdersByEmail(String trim) {
		// TODO Auto-generated method stub
		return null;
	}
}
