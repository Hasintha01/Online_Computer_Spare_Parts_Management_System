package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String adminChkBx = request.getParameter("adminChkBx");
		System.out.println(email + " : " + password + " : " + adminChkBx);
		try {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.getUserByEmail(email);

			if (user != null && user.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				if ("Admin".equals(user.getUserRole())) {
					response.sendRedirect("inventoryHome.jsp");
					System.out.println("logged as Admin");
				} else {
					response.sendRedirect(request.getContextPath() + "/catalog");

					System.out.println("logged as user");

				}
//				if ("on".equals(adminChkBx)) {
//					response.sendRedirect("inventoryHome.jsp");
//				} else {
//					RequestDispatcher dsp = request.getRequestDispatcher("/WEB-INF/views/catalog.jsp");
//					dsp.forward(request, response);
//				}
			} else {
				request.setAttribute("error", "Invalid email or password");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", "Error: " + e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}