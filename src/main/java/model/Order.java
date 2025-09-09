package model;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int userId;
    private int partId;
    private int quantity;
    private Timestamp orderDate;

    public Order(int orderId, int userId, int partId, int quantity, Timestamp orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.partId = partId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getPartId() { return partId; }
    public void setPartId(int partId) { this.partId = partId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }
}