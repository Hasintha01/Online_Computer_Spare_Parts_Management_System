package com.inventory.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private static final String UPLOAD_DIRECTORY = "uploads";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Use a fixed, persistent directory
		String uploadPath = "D:\\Eclipse\\Final_3\\src\\main\\webapp\\uploads";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			boolean created = uploadDir.mkdirs(); // Use mkdirs() to create parent directories if needed
			System.out.println("Uploads directory: " + uploadPath + " | Created: " + created);
		} else {
			System.out.println("Uploads directory exists: " + uploadPath);
		}

		Part filePart = request.getPart("itemImage");
		if (filePart == null || filePart.getSize() == 0) { // validation
			System.out.println("No file uploaded or filePart is null");
			request.setAttribute("imageName", "");
		} else {
			String fileName = getFileName(filePart);
			String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
			String fullPath = uploadPath + File.separator + uniqueFileName;
			System.out.println("Attempting to write file to: " + fullPath);
			try { // ex handling
				filePart.write(fullPath);
				System.out.println("File written successfully: " + fullPath);
				request.setAttribute("imageName", uniqueFileName);
			} catch (IOException e) {
				System.out.println("Failed to write file: " + e.getMessage());
				e.printStackTrace();
				request.setAttribute("imageName", "");
			}
		}

		String action = request.getParameter("action");
		if ("add".equals(action)) {
			request.getRequestDispatcher("AddProduct").forward(request, response);
		} else if ("update".equals(action)) {
			request.getRequestDispatcher("UpdateProduct").forward(request, response);
		}
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// Create upload directory if it doesn't exist
//		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//		File uploadDir = new File(uploadPath);
//		if (!uploadDir.exists()) {
//			uploadDir.mkdir();
//		}
//
//		// Handle file upload
//		Part filePart = request.getPart("itemImage");
//		String fileName = getFileName(filePart);
//
//		// Generate unique filename to prevent overwriting
//		String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
//		
//		System.out.println("Saving file to: " + uploadPath + File.separator + uniqueFileName);
//
//		// Save file to uploads directory
//		filePart.write(uploadPath + File.separator + uniqueFileName);
//
//		// Forward to ProductServlet with the image filename
//		request.setAttribute("imageName", uniqueFileName);
//		
//		System.out.println("Image name set: " + uniqueFileName);
//
//		// Process the action based on form parameter
//		String action = request.getParameter("action");
//
//		if ("add".equals(action)) {
//			request.getRequestDispatcher("AddProduct").forward(request, response);
//		} else if ("update".equals(action)) {
//			request.getRequestDispatcher("UpdateProduct").forward(request, response);
//		}
//	}

	// Helper method to extract file name from HTTP header content-disposition
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");

		for (String item : items) {
			if (item.trim().startsWith("filename")) {
				return item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return "";
	}
}
