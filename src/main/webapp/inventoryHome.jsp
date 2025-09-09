<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Our Products</title>
    <link rel="stylesheet" href="inventory.css?v=<%= System.currentTimeMillis() %>" />
    <link rel="shortcut icon" href="./icon.jpg" type="image/x-icon" />
    <style>
        /* Additional CSS for inventoryHome.jsp */
       /* Additional CSS for inventoryHome.jsp */
body {
    font-family: "Arial", sans-serif;
    margin: 0;
    padding: 20px;
    background: #f8f9fa;
}

.container {
    max-width: 1200px;
    margin: 0 auto;
}

.inventory-header {
    text-align: center;
    margin-bottom: 30px;
}

.inventory-header h1 {
    font-size: 32px;
    color: #333;
    margin: 0;
}

.product-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr); /* 2 columns */
    gap: 20px;
    padding: 10px;
}

.product {
    background: #fff;
    border: 1px solid #ddd;
    border-radius: 12px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    transition: transform 0.3s ease;
}

.product:hover {
    transform: translateY(-5px);
}

.product img {
    width: 100%;
    height: 200px;
    object-fit: cover;
    display: block;
}

.details {
    padding: 15px;
    text-align: left;
}

.details h3 {
    margin: 0 0 8px;
    font-size: 18px;
    color: #333;
}

.details .id {
    font-size: 14px;
    color: #717171;
    margin-bottom: 8px;
}

.details .price {
    font-size: 16px;
    font-weight: bold;
    color: #ff5e78;
}

.error-message {
    text-align: center;
    color: #721c24;
    background: #f8d7da;
    padding: 10px;
    border-radius: 5px;
    margin-bottom: 20px;
}
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
        <a href="${pageContext.request.contextPath}/viewRqstList">Requests</a>
    </div> 
<% User user = (User) session.getAttribute("user"); %>
    <% if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    } %>
    <div class="container">
        <div class="inventory-header">
            <h1>Our Products</h1>
<a href="<%= request.getContextPath() %>/account?userId=<%= user.getUserId() %>" class="btn-login">
    Logged as <%= user.getFirstName() %>
</a>
        </div>
       <div class="sub-btn">
    <form action="Products" method="post">
        <input type="submit" name="submit" value="Show Products" class="btn">
    </form>
</div>


        <!-- Error Messages -->
        <c:if test="${not empty param.error}">
            <div class="error-message">
                <c:choose>
                    <c:when test="${param.error == 'productNotFound'}">Product not found.</c:when>
                    <c:when test="${param.error == 'serverError'}">An error occurred. Please try again.</c:when>
                    <c:when test="${param.error == 'invalidId'}">Invalid product ID.</c:when>
                    <c:otherwise>Unknown error.</c:otherwise>
                </c:choose>
            </div>
        </c:if>

        <!-- Inventory Card Grid -->
        <div class="product-grid">
            <c:choose>
                <c:when test="${not empty ProDetails}">
                   <c:forEach items="${ProDetails}" var="product">
                        <div class="product">
                            <img src="${pageContext.request.contextPath}/Image?file=${product.image}" 
                                 alt="${product.name}" 
                                 onerror="this.src='${pageContext.request.contextPath}/Uploads/default.jpg'">
                            <div class="details">
                                <h3>${product.name}</h3>
                                <div class="id">ID: ${product.id}</div>
                                <div class="price">$${product.price}</div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="error-message">No products available at the moment.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>