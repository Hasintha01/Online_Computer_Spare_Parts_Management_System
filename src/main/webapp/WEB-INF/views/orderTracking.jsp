<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Tracking</title>
    <c:url var="styleUrl" value="/css/style.css" />
    <link rel="stylesheet" href="${styleUrl}" />
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            color: #333;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        table.order-tracking-table {
            width: 100%;
            max-width: 800px;
            border-collapse: collapse;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 6px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.05);
            margin-top: 20px;
        }
        table.order-tracking-table th, table.order-tracking-table td {
            padding: 12px 15px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
        table.order-tracking-table th {
            background-color: #f1f1f1;
            font-weight: bold;
        }
        table.order-tracking-table tbody tr:hover {
            background-color: #fafafa;
        }
        a {
            color: #ff5e78;
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Your Orders</h1>

    <c:if test="${empty orders}">
        <p>You have no orders yet. <a href="<c:url value='/catalog' />">Shop now</a></p>
    </c:if>

    <c:if test="${not empty orders}">
        <table class="order-tracking-table">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Total Amount</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td><fmt:formatDate value="${order.orderDate}" pattern="dd MMM yyyy" /></td>
                        <td style="color:#ff5e78;">$${order.totalAmount}</td>
                        <td>${order.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
