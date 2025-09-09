package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import util.DBConnection;

public class UserDAO {
	public void addUser(User user) throws SQLException {
		String sql = "INSERT INTO Users (email, password, first_name, last_name) VALUES (?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE email=VALUES(email), password=VALUES(password), "
				+ "first_name=VALUES(first_name), last_name=VALUES(last_name)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.executeUpdate();
		}
	}

	public User getUserByEmail(String email) throws SQLException {
		String sql = "SELECT * FROM Users WHERE email = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"),
							rs.getString("first_name"), rs.getString("last_name"), rs.getString("user_role"));
				}
			}
		}
		return null;
	}

	public void deleteUser(int userId) throws SQLException {
		String sql = "DELETE FROM Users WHERE user_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Deleted user ID: " + userId + ", rows affected: " + rowsAffected);
		}
	}
}