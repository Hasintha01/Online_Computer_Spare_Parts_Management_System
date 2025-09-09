package Admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Admin.DAO.requestDAO;
import Admin.model.Request;

/**
 * Servlet implementation class requestServlet
 */
@WebServlet("/AddRequest")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 5, // 5 MB
		maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class requestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private requestDAO rqDAO = new requestDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public requestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		RequestDispatcher disp1 = request.getRequestDispatcher("Request.jsp");
		disp1.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String rq_ID = request.getParameter("Req_id");
		String user_ID = request.getParameter("user_id");
		String rq_title = request.getParameter("Req_title");
		String rq_desc = request.getParameter("Req_description");

		Request rq_svlt = new Request();
		// rq_svlt.setReq_id(rq_ID);
		rq_svlt.setUser_id(user_ID);
		rq_svlt.setReq_title(rq_title);
		rq_svlt.setReq_description(rq_desc);
		System.out.println(rq_svlt);

		Collection<Part> parts = request.getParts();
		List<InputStream> imageStreams = new ArrayList<>();
		int count = 0;
		for (Part part : parts) {
			if ("images".equals(part.getName()) && part.getSize() > 0) {
				if (count++ >= 3)
					break;
				imageStreams.add(part.getInputStream());
			}
		}
		try {
			requestDAO dao = new requestDAO();
			dao.AddRequest(rq_svlt, imageStreams);
			response.sendRedirect("rqAddSuccessed.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

}
