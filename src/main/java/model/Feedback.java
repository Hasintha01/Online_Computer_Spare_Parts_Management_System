package model;

import java.sql.Timestamp;

public class Feedback {
    private int feedbackId;
    private int userId;
    private int noOfStars;
    private String description;
    private Timestamp createdAt;
    private String username;

    public Feedback(int feedbackId, int userId, int noOfStars, String description, Timestamp createdAt, String username) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.noOfStars = noOfStars;
        this.description = description;
        this.createdAt = createdAt;
        this.username = username;
    }

    public int getFeedbackId() { return feedbackId; }
    public void setFeedbackId(int feedbackId) { this.feedbackId = feedbackId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getNoOfStars() { return noOfStars; }
    public void setNoOfStars(int noOfStars) { this.noOfStars = noOfStars; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}