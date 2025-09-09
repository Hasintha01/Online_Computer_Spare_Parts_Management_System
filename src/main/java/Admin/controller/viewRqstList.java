package Admin.controller;

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
 * Servlet implementation class viewRqstList
 */
@WebServlet("/viewRqstList")
public class viewRqstList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private requestDAO dao = new requestDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public viewRqstList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Request> rqstList = dao.getallRequests();
			System.out.println("rqst size " + rqstList.size());
			request.setAttribute("rqstListAtr", rqstList);
			RequestDispatcher dsp = request.getRequestDispatcher("viewRqstList.jsp");
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
