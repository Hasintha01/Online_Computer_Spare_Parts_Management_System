package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String userRole = ("user");

		try {
			UserDAO userDAO = new UserDAO();
			User user = new User(0, email, password, firstName, lastName, userRole);
			userDAO.addUser(user);
			response.sendRedirect("login.jsp");
		} catch (Exception e) {
			request.setAttribute("error", "Error: " + e.getMessage());
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}
}