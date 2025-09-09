package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.FeedbackDAO;
import model.Feedback;
import model.User;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        try {
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            List<Feedback> feedbacks = feedbackDAO.getAllFeedback();
            System.out.println("Fetched " + (feedbacks != null ? feedbacks.size() : 0) + " feedbacks from database");
            request.setAttribute("feedbacks", feedbacks);

            String editIdStr = request.getParameter("edit_id");
            if (editIdStr != null) {
                int editId = Integer.parseInt(editIdStr);
                Feedback editFeedback = feedbackDAO.getFeedbackById(editId);
                if (editFeedback != null && editFeedback.getUserId() == user.getUserId()) {
                    System.out.println("Editing feedback ID: " + editId);
                    request.setAttribute("editFeedback", editFeedback);
                } else {
                    System.out.println("User " + user.getUserId() + " cannot edit feedback ID: " + editId);
                    response.sendRedirect("feedback");
                    return;
                }
            }

            request.getRequestDispatcher("/feedback.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error in FeedbackServlet doGet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Error fetching feedback: " + e.getMessage());
            request.getRequestDispatcher("/feedback.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String action = request.getParameter("action");

        try {
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            if ("add".equals(action)) {
                int noOfStars = Integer.parseInt(request.getParameter("no_of_stars"));
                String description = request.getParameter("description");
                Feedback feedback = new Feedback(0, user.getUserId(), noOfStars, description, null, null);
                feedbackDAO.addFeedback(feedback);
                System.out.println("Added feedback by user " + user.getUserId());
            } else if ("update".equals(action)) {
                int feedbackId = Integer.parseInt(request.getParameter("feedback_id"));
                int noOfStars = Integer.parseInt(request.getParameter("no_of_stars"));
                String description = request.getParameter("description");
                Feedback feedback = new Feedback(feedbackId, user.getUserId(), noOfStars, description, null, null);
                feedbackDAO.updateFeedback(feedback);
                System.out.println("Updated feedback ID: " + feedbackId);
            } else if ("delete".equals(action)) {
                int feedbackId = Integer.parseInt(request.getParameter("feedback_id"));
                feedbackDAO.deleteFeedback(feedbackId, user.getUserId());
                System.out.println("Deleted feedback ID: " + feedbackId);
            }

            response.sendRedirect("feedback");
        } catch (Exception e) {
            System.out.println("Error in FeedbackServlet doPost: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Error processing feedback: " + e.getMessage());
            doGet(request, response);
        }
    }
}