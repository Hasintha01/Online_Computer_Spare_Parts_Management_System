package com.spareparts.model;

import java.util.Date;

public class Order {
    // OOP Concept: Encapsulation 
    private int orderId;
    private int userId;  
    private Date orderDate;
    private double totalAmount;

    // New fields for guest checkout customer info
    private String customerName;
    private String customerEmail;
    private String shippingAddress;

    // No-argument constructor
    public Order() {}

    // Parameterized constructor including new fields
    public Order(int orderId, int userId, Date orderDate, double totalAmount,
                 String customerName, String customerEmail, String shippingAddress) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.shippingAddress = shippingAddress;
    }

    // Getters and setters - OOP Concept: Encapsulation 

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // New getters and setters for customer info

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "Order{" +
               "orderId=" + orderId +
               ", userId=" + userId +
               ", orderDate=" + orderDate +
               ", totalAmount=" + totalAmount +
               ", customerName='" + customerName + '\'' +
               ", customerEmail='" + customerEmail + '\'' +
               ", shippingAddress='" + shippingAddress + '\'' +
               '}';
    }
}
