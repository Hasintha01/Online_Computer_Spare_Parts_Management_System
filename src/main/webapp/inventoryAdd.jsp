<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.inventory.model.Product" %>
<%@ page import="com.inventory.service.InventoryService" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Add Item</title>
    <link rel="stylesheet" href="inventory.css" />
    <link rel="shortcut icon" href="./icon.jpg" type="image/x-icon" />
  </head>
  <body onload="openModal()">
    <img src="./backimg.jpg" width="100%" height="100%" />

    <div id="itemModal" class="modal">
      <div class="modal-content">
        <span class="close-btn" onclick="window.location.href='inventoryIndex.jsp'"
          >&times;</span
        >
        <h1 style="text-align: center">Add Item</h1>
        
        <!-- Display error message if any -->
        <% if(request.getParameter("error") != null) { %>
          <div class="error-message" style="color: red; text-align: center;">
            Error: <%= request.getParameter("error") %>
          </div>
        <% } %>
        
        <form action="FileUpload" method="post" enctype="multipart/form-data">
          <input type="hidden" name="action" value="add" />
          
          <label for="name">Item Name</label>
          <input type="text" id="name" name="name" placeholder="Item Name" required />
          
          <label for="category">Category</label>
          <input type="text" id="category" name="category" placeholder="Category" required />
          
          <label for="price">Price</label>
          <input type="number" id="price" name="price" placeholder="Price" step="0.01" required />
          
          <label for="stock">Stock</label>
          <input type="number" id="stock" name="stock" placeholder="Stock" required />
          
          <label for="itemImage">Upload Image:</label>
          <input
            type="file"
            id="itemImage"
            name="itemImage"
            accept="image/*"
            required
          />
         
          <button type="submit" class="save-btn" onclick = "inventoryIndex.jsp">Save</button>
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