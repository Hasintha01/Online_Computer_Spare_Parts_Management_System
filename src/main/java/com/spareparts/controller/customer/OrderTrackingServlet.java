package com.spareparts.controller.customer;

import com.spareparts.dao.OrderDAO;
import com.spareparts.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderTracking")
public class OrderTrackingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderDAO orderDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Removed login check to allow guest access

        try {
            List<Order> orders = null;

            // Optionally, get email from request parameter to filter orders
            String email = request.getParameter("email");

            if (email != null && !email.trim().isEmpty()) {
                // Fetch orders by customer email (guest or registered)
                orders = orderDAO.getOrdersByEmail(email.trim());
            } else {
                // If no email provided, optionally fetch all orders or none
                // For security, better to show no orders unless email is provided
                orders = List.of(); // empty list
            }

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/WEB-INF/views/orderTracking.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load order tracking information");
        }
    }
}
