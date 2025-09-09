package com.inventory.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.service.InventoryService;

//@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get parameters from the request
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		double price = Double.parseDouble(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));

		// Get image name from attribute set by FileUploadServlet
		String imageName = (String) request.getAttribute("imageName");

		boolean isTrue;
		isTrue = InventoryService.CreateInventory(name, category, price, stock, imageName);
		if (isTrue == true) {  //is db connection success?
			RequestDispatcher dis = request.getRequestDispatcher("inventoryIndex.jsp");
			dis.forward(request, response);
		} else {
			RequestDispatcher dis2 = request.getRequestDispatcher("Unsuccess.jsp");
			dis2.forward(request, response);
		}
//			if (imageName == null || imageName.isEmpty()) {
//				imageName = "default.jpg"; // Default image if none provided
//			}

		// Create product object
//			Product product = new Product();
//			product.setName(name);
//			product.setCategory(category);
//			product.setPrice(price);
//			product.setStock(stock);
//			product.setImage(imageName);
//
//			// Save to database (void method)
//			inventoryService.CreateInventory(product);
//
//			// Redirect to success page
//			response.sendRedirect("inventoryIndex.jsp?success=added");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.sendRedirect("inventoryAdd.jsp?error=failed");
//		}

	}
}
