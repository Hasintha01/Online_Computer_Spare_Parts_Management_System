package Admin.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Report {
	private String firstName;
	private String lastName;
	private int orderId;
	private Date orderDate;
	private int userId;
	private String productName;
	private Double unitPrice;
	private int quantity;
	private Double itemTotal;
	private BigDecimal orderTotal;

	public Report(String firstName, String lastName, int orderId, Date orderDate, int userId, String productName,
			Double unitPrice, int quantity, Double itemTotal, BigDecimal orderTotal) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.userId = userId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.itemTotal = itemTotal;
		this.orderTotal = orderTotal;
	}

	public Report() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date date) {
		this.orderDate = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(Double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}
}
