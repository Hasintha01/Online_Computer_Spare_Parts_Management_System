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

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        request.getRequestDispatcher("/account.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        User user = (User) session.getAttribute("user");
        int userId = Integer.parseInt(request.getParameter("userId"));

        try {
            UserDAO userDAO = new UserDAO();
            if ("update".equals(action)) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");

                if (password != null && !password.isEmpty() && !password.equals(confirmPassword)) {
                    request.setAttribute("error", "Passwords do not match!");
                    request.getRequestDispatcher("/account.jsp").forward(request, response);
                    return;
                }

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                if (password != null && !password.isEmpty()) {
                    user.setPassword(password); // Update password if provided
                }

                // Update user in database (simplified; add actual update logic in UserDAO if needed)
                userDAO.addUser(user); // Overwrite existing user with new details
                session.setAttribute("user", user);
                response.sendRedirect("account");
            } else if ("delete".equals(action)) {
                if (user.getUserId() == userId) {
                    userDAO.deleteUser(userId); // Implement deleteUser in UserDAO
                    session.invalidate();
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("error", "Invalid user ID for deletion!");
                    request.getRequestDispatcher("/account.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error processing request: " + e.getMessage());
            request.getRequestDispatcher("/account.jsp").forward(request, response);
        }
    }
}