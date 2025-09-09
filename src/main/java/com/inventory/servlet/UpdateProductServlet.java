
package com.inventory.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.model.Product;
import com.inventory.service.InventoryService;

//@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get parameters from the request
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		try { // ex handling
			double price = Double.parseDouble(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			
			String imageName = (String) request.getAttribute("imageName");
			
			// If no new image is uploaded, retain the existing one
			if (imageName == null || imageName.isEmpty()) {
				List<Product> productDetails = InventoryService.getProduct(id);
				if (!productDetails.isEmpty()) {
					imageName = productDetails.get(0).getImage();
				}
			}
			
			boolean isTrue;
			
			isTrue = InventoryService.updateProduct(id, name, category, price, stock, imageName);
			if (isTrue == true) {
				List<Product> ProDetails = InventoryService.getProduct(id);
				request.setAttribute("ProDetails", ProDetails);
				
				RequestDispatcher dis = request.getRequestDispatcher("inventoryIndex.jsp");
				dis.forward(request, response);
			} else {
				List<Product> ProDetails = InventoryService.getProduct(id);
				request.setAttribute("ProDetails", ProDetails);
				
				RequestDispatcher dis = request.getRequestDispatcher("Unsuccess.jsp");
				dis.forward(request, response);
			}
		} catch(NumberFormatException nfe) {
			System.out.println(nfe);
		}

	}
	
	//get details to inventoryUpdate from db through inventoryService 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String id = request.getParameter("id");
	    List<Product> productDetails = InventoryService.getProduct(id);
	    if (!productDetails.isEmpty()) {
	        request.setAttribute("product", productDetails.get(0));
	    }
	    RequestDispatcher dis = request.getRequestDispatcher("inventoryUpdate.jsp");
	    dis.forward(request, response);
	}
}
