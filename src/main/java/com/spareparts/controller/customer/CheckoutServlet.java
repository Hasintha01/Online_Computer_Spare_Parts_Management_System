package com.spareparts.controller.customer;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spareparts.dao.OrderDAO;
import com.spareparts.model.Cart;
import com.spareparts.model.Order;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private OrderDAO orderDAO;

	@Override
	public void init() {
		orderDAO = new OrderDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		Cart cart = (session != null) ? (Cart) session.getAttribute("cart") : null;
		if (cart == null || cart.getItems().isEmpty()) {
			request.setAttribute("message", "Your cart is empty. Please add items before checkout.");
//            request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
//            return;
			request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
			return;

		}

		request.setAttribute("cartItems", cart.getItems());
		request.setAttribute("totalAmount",
				cart.getItems().stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum());

		request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		Cart cart = (session != null) ? (Cart) session.getAttribute("cart") : null;
		if (cart == null || cart.getItems().isEmpty()) {
			request.setAttribute("message", "Your cart is empty.");
			request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
			return;
		}

		try {
			// Collect customer info from form
			String fullName = request.getParameter("fullName");
			String email = request.getParameter("email");
			String address = request.getParameter("address");

			if (fullName == null || email == null || address == null || fullName.isEmpty() || email.isEmpty()
					|| address.isEmpty()) {
				request.setAttribute("message", "Please fill in all required customer information.");
				doGet(request, response); // Show checkout page again with message
				return;
			}

			// Calculate total amount
			double totalAmount = cart.getItems().stream()
					.mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

			// Create new order
			Order order = new Order();

			// Since no login required, set userId to 0 or null
			order.setUserId(0); // or null if your model supports it

			order.setOrderDate(new Date());
			order.setTotalAmount(totalAmount);

			// Set customer info in order (make sure your Order model supports these fields)
			order.setCustomerName(fullName);
			order.setCustomerEmail(email);
			order.setShippingAddress(address);

			boolean orderCreated = orderDAO.insertOrder(order);

			if (orderCreated) {
				// Clear cart after successful order
				if (session != null) {
					session.removeAttribute("cart");
				}
				// Redirect to catalog page after successful order
				// response.sendRedirect(request.getContextPath() + "/catalog");
				request.getRequestDispatcher("/feedback.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("message", "Failed to place order. Please try again.");
				request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Checkout failed");
		}
	}
}
