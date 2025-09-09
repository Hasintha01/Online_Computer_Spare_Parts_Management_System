package Admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.DAO.ResponseDAO;
import Admin.model.Response;

/**
 * Servlet implementation class responseServlet
 */
@WebServlet("/responseServlet")
public class responseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public responseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String rsp_id = request.getParameter("res_id");
		String rq_id = request.getParameter("rq_id");

		String rsp_description = request.getParameter("rsp_description");
		System.out.println(rq_id + " + " + rsp_description);
		Response response2 = new Response();
		// response2.setRsp_id(rsp_id);
		response2.setRq_id(rq_id);
		response2.setRsp_description(rsp_description);
		System.out.println(response2);

		try {
			ResponseDAO dao = new ResponseDAO();
			dao.addResponse(response2);
			response.sendRedirect(request.getContextPath() + "/viewRqstList");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
