package Admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.DAO.ReportDAO;
import Admin.model.Report;

/**
 * Servlet implementation class ReportViewBuy
 */
@WebServlet("/ReportViewBuy")
public class ReportViewBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportDAO dao = new ReportDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportViewBuy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String from_Date = request.getParameter("from_Date");
		String to_Date = request.getParameter("to_Date");
		System.out.println(from_Date + " , " + to_Date);
		try {
			List<Report> rpList = dao.getbuyRecords();

			if (from_Date != null && !from_Date.isEmpty() && to_Date != null && !to_Date.isEmpty()) {
				rpList = dao.getbuyRecords(from_Date, to_Date);
			} else {
				rpList = dao.getbuyRecords();
			}
			System.out.println(rpList.size() + "rplist size");
			System.out.println("rp list dao created");
			request.setAttribute("rpListAtr", rpList);
			RequestDispatcher dsp = request.getRequestDispatcher("ReportViewBuy.jsp");
			dsp.forward(request, response);
			System.out.println("-------------------------------------------------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error from report servlet");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println("-------------------------------------------------------------------------------");

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
