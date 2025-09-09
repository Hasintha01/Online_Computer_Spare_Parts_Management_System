package com.spareparts.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

	// Database URL with parameters to avoid SSL and timezone issues
	private static final String URL = "jdbc:mysql://localhost:3306/admin?useSSL=false&serverTimezone=UTC";

	private static final String USER = "root";
	private static final String PASSWORD = "123456789";

	// Private constructor to prevent instantiation
	private DBConnectionUtil() {
	}

	public static Connection getDBConnection() throws SQLException {
		try {
			// Explicitly load MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SQLException("MySQL JDBC Driver not found.", e);
		}
		// Now get the connection
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
