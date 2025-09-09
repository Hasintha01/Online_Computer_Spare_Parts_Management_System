package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.DAO.requestDAO;
import Admin.model.Request;

/**
 * Servlet implementation class RqListById
 */
@WebServlet("/RqListById")
public class RqListById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private requestDAO dao = new requestDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RqListById() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		System.out.println(user_id + " 's request list");

		if (user_id == null || user_id.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "no user id");
			return;
		}
		try {
			List<Request> rqstList = dao.getAllRequests_userID(user_id);
			System.out.println("rqst size " + rqstList.size());
			request.setAttribute("rqstListAtr", rqstList);
			RequestDispatcher dsp = request.getRequestDispatcher("RqListById.jsp");
			dsp.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in view request lsit servlet");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
