package com.spareparts.controller.customer;

import com.spareparts.dao.ProductDAO;
import com.spareparts.model.Product;
import com.spareparts.model.Cart;
import com.spareparts.model.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        String action = request.getParameter("action");

        if (action == null) {
            // No action specified, treat as add to cart from product page
            handleAddToCart(request, response, cart, session);
            return;
        }

        if (action.startsWith("remove_")) {
            // Remove item action
            int productId = Integer.parseInt(action.substring("remove_".length()));
            cart.removeItem(productId);
            session.setAttribute("cart", cart);
            response.sendRedirect("cart");
            return;
        }

        if ("update".equals(action)) {
            // Update quantities action
            String[] productIds = request.getParameterValues("productIds");
            String[] quantities = request.getParameterValues("quantities");

            if (productIds != null && quantities != null && productIds.length == quantities.length) {
                for (int i = 0; i < productIds.length; i++) {
                    try {
                        int productId = Integer.parseInt(productIds[i]);
                        int quantity = Integer.parseInt(quantities[i]);
                        if (quantity > 0) {
                            cart.updateItemQuantity(productId, quantity);
                        } else {
                            cart.removeItem(productId);
                        }
                    } catch (NumberFormatException e) {
                        // Ignore invalid input and continue
                    }
                }
                session.setAttribute("cart", cart);
            }
            response.sendRedirect("cart");
            return;
        }

        // Unknown action, redirect to catalog
        response.sendRedirect("catalog");
    }

    private void handleAddToCart(HttpServletRequest request, HttpServletResponse response, Cart cart, HttpSession session)
            throws IOException {
        String productIdStr = request.getParameter("productId");
        String quantityStr = request.getParameter("quantity");

        if (productIdStr == null || quantityStr == null) {
            response.sendRedirect("catalog");
            return;
        }

        int productId;
        int quantity;
        try {
            productId = Integer.parseInt(productIdStr);
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                quantity = 1; // Default quantity
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("catalog");
            return;
        }

        try {
            Product product = productDAO.getProductById(productId);
            if (product == null) {
                response.sendRedirect("catalog");
                return;
            }

            cart.addItem(new CartItem(product, quantity));
            session.setAttribute("cart", cart);

            // Debug print
            System.out.println("Cart contents after adding product:");
            for (CartItem item : cart.getItems()) {
                System.out.println(item);
            }

            response.sendRedirect("cart");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("catalog");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }
}
