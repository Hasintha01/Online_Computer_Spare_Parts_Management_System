
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

//@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		boolean isTrue;

		isTrue = InventoryService.deleteProduct(id);
		if (isTrue == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryIndex.jsp");
			dispatcher.forward(request, response);
		} else {

			List<Product> Products = InventoryService.getAllProducts();
			request.setAttribute("Products", Products);

			RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryIndex.jsp");
			dispatcher.forward(request, response);
		}

	}
}
