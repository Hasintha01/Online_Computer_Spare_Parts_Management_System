package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.PartDAO;
import model.Part;

@WebServlet("/parts")
public class PartsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PartDAO partDAO = new PartDAO();
            List<Part> parts = partDAO.getAllParts();
            request.setAttribute("parts", parts);
            request.getRequestDispatcher("/parts.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error fetching parts: " + e.getMessage());
            request.getRequestDispatcher("/parts.jsp").forward(request, response);
        }
    }
}