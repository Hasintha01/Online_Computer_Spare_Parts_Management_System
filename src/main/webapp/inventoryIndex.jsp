<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.inventory.model.Product" %>
<%@ page import="com.inventory.service.InventoryService" %>
<%@ page import="model.User" %>

<!DOCTYPE html> 
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Inventory Management</title>
    <link rel="stylesheet" href="inventory.css?v=<%= System.currentTimeMillis() %>" />
    <link rel="shortcut icon" href="./icon.jpg" type="image/x-icon" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    <style type="text/css">
    .btn-login {
    position: fixed;     
    top: 15px;           
        right: 15px;       
    padding: 8px 20px;
    background-color: #ff385c;
    color: white;
    text-decoration: none;
    border-radius: 10px;
    font-weight: 600;
    font-size: 14px;
    cursor: pointer;
    box-shadow: 0 3px 6px rgba(255, 56, 92, 0.4);
    transition: background-color 0.3s ease;
    z-index: 1000;      
}

.btn-login:hover {
    background-color: #e03250;
    box-shadow: 0 5px 15px rgba(224, 50, 80, 0.6);
}
    </style>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <h2>Dashboard</h2>
        <a href="inventoryHome.jsp">Home</a>
        <a href="inventoryIndex.jsp">Inventory</a>
        <a href="${pageContext.request.contextPath}/ReportViewBuy">Sales Report</a>
        <a href="${pageContext.request.contextPath}/ViewRqstList">Requests</a>
    </div>
    
<%
    User user = (User) session.getAttribute("user");
    if (user != null) {
        String username = user.getFirstName(); 
%>

    <!-- Main Content -->
    <div class="main-content">
        <header>
            <h1>Inventory Management</h1>
     
        </header>

        <!-- Status Messages -->
        <c:if test="${not empty message}">
            <div class="status-message success" style="background-color: #d4edda; color: #155724; padding: 10px; margin: 10px 0; border-radius: 5px;">
                ${message}
            </div>
        </c:if>
        
        <c:if test="${not empty error}">
            <div class="status-message error" style="background-color: #f8d7da; color: #721c24; padding: 10px; margin: 10px 0; border-radius: 5px;">
                Error: ${error}
            </div>
        </c:if>

        <!-- Dashboard Widgets -->
        <div class="dashboard">
            <div class="widget">Total Items: <span id="totalItems">${not empty ProDetails ? ProDetails.size() : 0}</span></div>
            <div class="widget">Total Sales: $50,000</div>
            <div class="widget">
                Low Stock: <span id="lowStockCount">
                    <c:set var="lowStockCount" value="0" />
                   <c:forEach items="${ProDetails}" var="product">
                        <c:if test="${product.stock < 10}">
                          <c:set var="lowStockCount" value="${lowStockCount + 1}" />
                        </c:if>
                    </c:forEach>
                    ${lowStockCount}
                </span> Items
            </div>
        </div>

        <!-- add item button -->
        <div class="add">
            <button class="add-btn" onclick="window.location.href='inventoryAdd.jsp'">
                <i class="fas fa-plus"></i> Add Item
            </button>
        </div>
        
        <div class="sub-btn">
    <form action="Products" method="post">
        <input type="submit" name="submit" value="Get Inventory" class="btn">
    </form>

    <form action="Products" method="post">
        <label class="white-text" for="productId">Product ID</label>
        <input type="text" name="id" id="productId"><br>
        <input type="submit" name="submit" value="Get Product" class="btn">
    </form>
</div>


        <!-- Inventory Table -->
        <div class="inventory">
            <c:choose>
                <c:when test="${not empty ProDetails}">
                    <table>
                        <thead>
                            <tr>
                                <th>Image</th>
                                <th>Product ID</th>
                                <th>Item Name</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Stock</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ProDetails}" var="product">
                                <tr>
                                    <td>
                                        <img src="${pageContext.request.contextPath}/Image?file=${product.image}" alt="${product.name}" width="50" height="50" 
     onerror="this.src='${pageContext.request.contextPath}/uploads/default.jpg'">
                                    </td>
                                    <td>${product.id }</td>
                                    <td>${product.name}</td>
                                    <td>${product.category}</td>
                                    <td>$${product.price}</td>
                                    <td class="${product.stock < 10 ? 'low-stock' : ''}">${product.stock}</td>
                                    <td>
                                        <button class="edit-btn" onclick="window.location.href='UpdateProduct?id=${product.id}'">
                                            <i class="fas fa-edit"></i> Edit
                                        </button>
                                        <button class="delete-btn" onclick="deleteItem(${product.id})">
                                            <i class="fas fa-trash"></i> Delete
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="alert alert-warning text-center">No products found in inventory.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
<% } %>
    <script>
        // Delete item function
        function deleteItem(itemId) {
            if (confirm("Are you sure you want to delete this item?")) {
                fetch('DeleteProduct?id=' + itemId, {
                    method: 'POST'
                })
                .then(response => {
                    if (response.ok) {
                        window.location.href = 'inventoryIndex.jsp?message=Product deleted successfully';
                    } else {
                        throw new Error('Failed to delete item');
                    }
                })
                .catch(error => {
                    console.error("Error deleting item:", error);
                    window.location.href = 'inventoryIndex.jsp?error=Failed to delete product';
                });
            }
        }

        // Highlight low stock items
        document.addEventListener('DOMContentLoaded', function() {
            document.querySelectorAll('.low-stock').forEach(cell => {
                cell.style.color = 'red';
                cell.style.fontWeight = 'bold';
            });
        });
    </script>
</body>
</html>