<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.inventory.service.InventoryService" %>
<%@ page import="com.inventory.model.Product" %>
<!DOCTYPE html>
<html lang="en">
  <head> 
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Update Item</title>
    <link rel="stylesheet" href="inventory.css" />
    <link rel="shortcut icon" href="./icon.jpg" type="image/x-icon" />
  </head>
  <body onload="openModal()">
    <img src="./backimg.jpg" width="100%" height="100%" />
    
<%
        Product product = (Product) request.getAttribute("product");
        String id = product != null ? String.valueOf(product.getId()) : "";  // Convert int to String
        String name = product != null ? product.getName() : "";
        String category = product != null ? product.getCategory() : "";
        String price = product != null ? String.valueOf(product.getPrice()) : "";  // double to String
        String stock = product != null ? String.valueOf(product.getStock()) : "";  // int to String
        String itemImage = product != null ? product.getImage() : "";  // Use getImage(), not getImageName()
    %>
    
    <!-- Modal for Updating Items -->
    <div id="itemModal" class="modal">
      <div class="modal-content">
        <span class="close-btn" onclick="window.location.href='inventoryIndex.jsp'"
          >&times;</span
        >
        <h1 style="text-align: center">Update Item</h1>
        
        <!-- Display error message if any -->
        <% if(request.getParameter("error") != null) { %>
          <div class="error-message" style="color: red; text-align: center;">
            Error: <%= request.getParameter("error") %>
          </div>
        <% } %>
        
        <form action="FileUpload" method="post" enctype="multipart/form-data">
          <input type="hidden" name="action" value="update" />
          <input type="hidden" name="id" value="<%= id %>" />
          
          <label for="name">Item Name</label>
          <input type="text" id="name" name="name" placeholder="Item Name" value="<%= name %>" required />
          
          <label for="category">Category</label>
          <input type="text" id="category" name="category" placeholder="Category" value="<%= category %>" required />
          
          <label for="price">Price</label>
          <input type="number" id="price" name="price" placeholder="Price" step="0.01" value="<%= price %>" required />
          
          <label for="stock">Stock</label>
          <input type="number" id="stock" name="stock" placeholder="Stock" value="<%= stock %>" required />
          
       <label for="itemImage">Current Image:</label>
                <% if (itemImage != null && !itemImage.isEmpty()) { %>
                    <img src="${pageContext.request.contextPath}/Image?file=${product.image}" alt="Current Image" width="50" height="50" 
                         onerror="${pageContext.request.contextPath}this.src='uploads/default.jpg'" />
                <% } else { %>
                    <p>No image available</p>
                <% } %>
          <label for="itemImage">Upload New Image (leave empty to keep current):</label>
          <input
            type="file"
            id="itemImage"
            name="itemImage"
            accept="image/*"
          />
          
          <button type="submit" class="save-btn">Update</button>
        </form>
      </div>
    </div>
    
    <script>
      function openModal() {
        document.getElementById("itemModal").style.display = "flex";
      }

      function closeModal() {
        document.getElementById("itemModal").style.display = "none";
      }
    </script>
  </body>
</html>