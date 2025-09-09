package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Feedback;
import util.DBConnection;

public class FeedbackDAO {

    public void addFeedback(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO Feedback (user_id, no_of_stars, description) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getUserId());
            stmt.setInt(2, feedback.getNoOfStars());
            stmt.setString(3, feedback.getDescription());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Inserted feedback, rows affected: " + rowsAffected);
        }
    }

    public List<Feedback> getAllFeedback() throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT f.*, u.first_name AS username " +
                    "FROM Feedback f " +
                    "JOIN Users u ON f.user_id = u.user_id " +
                    "ORDER BY f.created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Feedback feedback = new Feedback(
                    rs.getInt("feedback_id"),
                    rs.getInt("user_id"),
                    rs.getInt("no_of_stars"),
                    rs.getString("description"),
                    rs.getTimestamp("created_at"),
                    rs.getString("username")
                );
                feedbacks.add(feedback);
                System.out.println("Fetched feedback ID: " + feedback.getFeedbackId() + ", Username: " + feedback.getUsername());
            }
        }
        return feedbacks;
    }

    public Feedback getFeedbackById(int feedbackId) throws SQLException {
        String sql = "SELECT f.*, u.first_name AS username " +
                    "FROM Feedback f " +
                    "JOIN Users u ON f.user_id = u.user_id " +
                    "WHERE f.feedback_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedbackId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Feedback feedback = new Feedback(
                        rs.getInt("feedback_id"),
                        rs.getInt("user_id"),
                        rs.getInt("no_of_stars"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"),
                        rs.getString("username")
                    );
                    System.out.println("Fetched feedback by ID: " + feedbackId + ", Username: " + feedback.getUsername());
                    return feedback;
                }
            }
        }
        return null;
    }

    public void updateFeedback(Feedback feedback) throws SQLException {
        String sql = "UPDATE Feedback SET no_of_stars = ?, description = ? WHERE feedback_id = ? AND user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getNoOfStars());
            stmt.setString(2, feedback.getDescription());
            stmt.setInt(3, feedback.getFeedbackId());
            stmt.setInt(4, feedback.getUserId());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Updated feedback ID: " + feedback.getFeedbackId() + ", rows affected: " + rowsAffected);
        }
    }

    public void deleteFeedback(int feedbackId, int userId) throws SQLException {
        String sql = "DELETE FROM Feedback WHERE feedback_id = ? AND user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedbackId);
            stmt.setInt(2, userId);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Deleted feedback ID: " + feedbackId + ", rows affected: " + rowsAffected);
        }
    }
}