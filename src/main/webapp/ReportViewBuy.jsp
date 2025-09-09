<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Admin.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buyers Report</title>

<style>
body {
    margin: 0;
    font-family: Arial, sans-serif;
}
.main-content {
    padding: 30px;
}
.filter-group {
    margin-bottom: 20px;
    display: flex;
    gap: 15px;
    align-items: center;
    flex-wrap: wrap;
}
.filter-group label {
    font-weight: bold;
}
.report-table {
    width: 100%;
    border-collapse: collapse;
    background: #fff;
}
.report-table th, .report-table td {
    border: 1px solid #ddd;
    padding: 8px 12px;
    text-align: left;
}
.report-table th {
    background: #f4f4f4;
}
</style>

</head>
<body>

<div class="main-content">

    <div class="filter-group">
        <label for="fromDate">From:</label>
        <input type="date" id="from_Date" name="fromDate" value="<%= request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "" %>">

        <label for="toDate">To:</label>
        <input type="date" id="to_Date" name="toDate" value="<%= request.getParameter("toDate") != null ? request.getParameter("toDate") : "" %>">

        <button id="loadBtn">Load</button>
    </div>

    <table class="report-table">
        <tr>
            <th>Name</th>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Product Name</th>
            <th>Unit Price</th>
            <th>Quantity</th>
            <th>Item Total</th>
        </tr>
        
        <%
            List<Report> rpList = (List<Report>) request.getAttribute("rpListAtr");
            if (rpList != null && !rpList.isEmpty()) {
                for (Report rp : rpList) {
        %>
            <tr>
                <td><%= rp.getFirstName() + " " + rp.getLastName() %></td>
                <td><%= rp.getOrderId() %></td>
                <td><%= rp.getOrderDate() %></td>
                <td><%= rp.getProductName() %></td>
                <td><%= String.format("%.2f", rp.getUnitPrice()) %></td>
                <td><%= rp.getQuantity() %></td>
                <td><%= String.format("%.2f", rp.getItemTotal()) %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="7">No records found.</td></tr>
        <%
            }
        %>
    </table>

</div>

<script>
  document.getElementById("loadBtn").addEventListener("click", function () {
      const fromDate = document.getElementById("from_Date").value;
      const toDate = document.getElementById("to_Date").value;

      console.log(fromDate);
      console.log(toDate);

      if (!fromDate || !toDate) {
          alert("Please set both From and To dates.");
          return;
      }

      const url = `<%= request.getContextPath() %>/ReportViewBuy?fromDate=${fromDate}&toDate=${toDate}`;
      window.location.href = url;
  });
</script>


</body>
</html>
