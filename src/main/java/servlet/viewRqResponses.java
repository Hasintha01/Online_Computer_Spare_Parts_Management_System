package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RequestResponseDAO;
import model.RequestResponse;

/**
 * Servlet implementation class viewRqResponses
 */
@WebServlet("/viewRqResponses")
public class viewRqResponses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestResponseDAO dao = new RequestResponseDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public viewRqResponses() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Req_ID = request.getParameter("req_id");
		System.out.println(Req_ID + "'s response list");
		try {
			List<RequestResponse> rqstRspListAtr = dao.getAllResponses_ReqID(Req_ID);
			System.out.println("rqst size " + rqstRspListAtr.size());
			request.setAttribute("rqstRspListAtr", rqstRspListAtr);
			RequestDispatcher dsp = request.getRequestDispatcher("viewRqResponses.jsp");
			dsp.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
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
