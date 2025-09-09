//
//package com.inventory.servlet;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.inventory.model.Product;
//import com.inventory.service.InventoryService;
//
////@WebServlet("/ProductServlet")
//public class ProductServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("Servlet started: Fetching products");
//
//		// Check if an ID parameter is provided for fetching a specific product
//		String idParam = request.getParameter("id"); // update jsp eke id eka
//
//		try {
//			if (idParam != null && !idParam.isEmpty()) {
//
//				List<Product> ProDetails = InventoryService.getProduct(idParam);
//
//				if (!ProDetails.isEmpty()) {
//					request.setAttribute("ProDetails", ProDetails);
//					RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryIndex.jsp");
//					dispatcher.forward(request, response);
//				} else {
//					response.sendRedirect("inventoryHome.jsp?error=productNotFound");
//				}
//			} else {
//				// Get all products
//				List<Product> ProDetails = InventoryService.getAllProducts();
//				request.setAttribute("ProDetails", ProDetails);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryIndex.jsp");
//				dispatcher.forward(request, response);
//				
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.sendRedirect("inventoryIndex.jsp?error=serverError");
//		}
//
//	}
//
//	/**
//	 * Handles POST requests by redirecting to doGet().
//	 */
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//}


//package com.inventory.servlet;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.inventory.model.Product;
//import com.inventory.service.InventoryService;
//
////@WebServlet("/Products") // Uncomment and adjust if needed
//public class ProductServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        System.out.println("Servlet started: Fetching products");
//
//        // Check if an ID parameter is provided
//        String idParam = request.getParameter("id");
//
//        try {
//            if (idParam != null && !idParam.isEmpty()) {
//                // Admin view: specific product
//                List<Product> ProDetails = InventoryService.getProduct(idParam);
//                if (!ProDetails.isEmpty()) {
//                    request.setAttribute("ProDetails", ProDetails);
//                    RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryIndex.jsp");
//                    dispatcher.forward(request, response);
//                } else {
//                    response.sendRedirect("inventoryHome.jsp?error=productNotFound");
//                }
//            } else {
//                // Customer view: all products
//                List<Product> ProDetails = InventoryService.getAllProducts();
//                request.setAttribute("ProDetails", ProDetails);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryHome.jsp");
//                dispatcher.forward(request, response);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("inventoryHome.jsp?error=serverError");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Handle form submissions (e.g., Refresh/Get Inventory)
//        String submit = request.getParameter("submit");
//        try {
//            if ("Refresh".equals(submit)) {
//                // Admin view: all products
//                List<Product> ProDetails = InventoryService.getAllProducts();
//                request.setAttribute("ProDetails", ProDetails);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryIndex.jsp");
//                dispatcher.forward(request, response);
//            } else if ("Get Inventory".equals(submit)) {
//                // Admin view: specific product
//                String id = request.getParameter("id");
//                if (id != null && !id.isEmpty()) {
//                    List<Product> ProDetails = InventoryService.getProduct(id);
//                    if (!ProDetails.isEmpty()) {
//                        request.setAttribute("ProDetails", ProDetails);
//                        RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryIndex.jsp");
//                        dispatcher.forward(request, response);
//                    } else {
//                        response.sendRedirect("inventoryHome.jsp?error=productNotFound");
//                    }
//                } else {
//                    response.sendRedirect("inventoryHome.jsp?error=invalidId");
//                }
//            } else {
//                // Default to doGet
//                doGet(request, response);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("inventoryHome.jsp?error=serverError");
//        }
//    }
//}

package com.inventory.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.model.Product;
import com.inventory.service.InventoryService;

//@WebServlet({"/Products", "/inventoryHome"}) // Map both URLs
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet started: Fetching products");

        // Check if an ID parameter is provided
        String idParam = request.getParameter("id");

        try { // ex handling
            if (idParam != null && !idParam.isEmpty()) {
                // Admin view: specific product in update product page
                List<Product> ProDetails = InventoryService.getProduct(idParam);
                if (!ProDetails.isEmpty()) {
                    request.setAttribute("ProDetails", ProDetails);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryIndex.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("inventoryIndex.jsp?error=productNotFound");
                }
            } else {
                // Customer view: all products in home page
                List<Product> ProDetails = InventoryService.getAllProducts();
                System.out.println("ProDetails size: " + (ProDetails != null ? ProDetails.size() : "null"));
                request.setAttribute("ProDetails", ProDetails);
                RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryHome.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("inventoryHome.jsp?error=serverError");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle form submissions /get inventory or get product
        String submit = request.getParameter("submit");
        try { // ex handling
            if ("Get Inventory".equals(submit)) {
                // Admin view all products
                List<Product> ProDetails = InventoryService.getAllProducts();
                request.setAttribute("ProDetails", ProDetails);
                RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryIndex.jsp");
                dispatcher.forward(request, response);
            } else if ("Get Product".equals(submit)) {
                // Admin view specific product
                String id = request.getParameter("id");
                if (id != null && !id.isEmpty()) {
                    List<Product> ProDetails = InventoryService.getProduct(id);
                    if (!ProDetails.isEmpty()) {
                        request.setAttribute("ProDetails", ProDetails);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryIndex.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        response.sendRedirect("inventoryIndex.jsp?error=productNotFound");
                    }
                } else {
                    response.sendRedirect("inventoryIndex.jsp?error=invalidId");
                }
            } else {
                // Default to doGet
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("inventoryIndex.jsp?error=serverError");
        }
    }
}
