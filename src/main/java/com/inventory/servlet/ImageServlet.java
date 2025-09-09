package com.inventory.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/ImageServlet")

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "D:\\Eclipse\\Final_3\\src\\main\\webapp\\uploads";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filename = request.getParameter("file");
		if (filename == null || filename.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No file specified");
			return;
		}

		File file = new File(UPLOAD_DIRECTORY + File.separator + filename);
		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
			return;
		}

		response.setContentType("image/jpeg"); // Adjust based on file type if needed
		response.setContentLength((int) file.length());

		try (FileInputStream in = new FileInputStream(file)) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				response.getOutputStream().write(buffer, 0, bytesRead);
			}
		}
	}
}