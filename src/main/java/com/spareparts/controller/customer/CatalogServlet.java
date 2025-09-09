package com.spareparts.controller.customer;

import com.spareparts.dao.ProductDAO;
import com.spareparts.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


 // Controller in MVC.

 @WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {

    //Unique identifier for Serializable class.
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Product> products = productDAO.getAllProducts();
            request.setAttribute("products", products);
            // Forward request to JSP view
            request.getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally, forward to an error page or set error message
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load products");
        }
    }
}
