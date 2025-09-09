package com.inventory.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private static final String url = "jdbc:mysql://localhost:3306/admin";
	private static final String username = "root";
	private static final String password = "123456789";

	public static Connection getConnection() {
		try {
			System.out.println("load MySQL JDBC Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Loaded ");

			System.out.println("Connecting to the database");
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected");
			return conn;
		} catch (SQLException e) {
			System.out.println("Database connection failed: " + e.getMessage());
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver not found! Make sure you have added MySQL Connector JAR.");
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("connection closed");
		}
	}

	public static void close(PreparedStatement pstmt) throws SQLException {
		if (pstmt != null) {
			pstmt.close();
			System.out.println("prepared statement closed");
		}
	}

	public static void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
			System.out.println("resultset closed");
		}
	}
//	public static void main(String args[]) {
//		getConnection(); // Test connection
//	}
}
