package Admin.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Admin.DAO.requestDAO;
import Admin.model.Request;
import Admin.model.RequestWithImages;

/**
 * Servlet implementation class RqWithImagesServlet
 */
@WebServlet("/RqWithImagesServlet")
public class RqWithImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RqWithImagesServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String req_id = request.getParameter("req_id");
		String imgID = request.getParameter("img_id_parameter");

		if (imgID != null) {
			try {
				// int imageId = Integer.parseInt(imgID);
				requestDAO rqDAO = new requestDAO();
				byte[] imageData = rqDAO.getImageById(imgID);

				if (imageData != null) {
					response.setContentType("image/jpeg"); // or image/png — set the correct MIME
					response.setContentLength(imageData.length);
					OutputStream out = response.getOutputStream();
					out.write(imageData);
					out.flush();
					return;
				} else {
					response.sendError(HttpServletResponse.SC_NOT_FOUND, "no images.");
				}
				return;
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}

		if (req_id == null || req_id.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing req_id");
			return;
		} else {
			System.out.println(req_id);
		}

		try {
			if (req_id != null) {
				servletReqJson(req_id, response);
			} else if (imgID != null) {
				serveImage(imgID, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong");
		}
	}

	private void servletReqJson(String reqID, HttpServletResponse response)
			throws IOException, ClassNotFoundException, SQLException {
		requestDAO dao = new requestDAO();
		RequestWithImages reqWithImgs = dao.getRequestDetailsWithImages(reqID);

		if (reqWithImgs == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Request not found");
			return;
		}

		Request rq = reqWithImgs.getRequest();
		Gson gson = new Gson();

		JsonObject json = new JsonObject();
		json.addProperty("req_id", rq.getReq_id());
		json.addProperty("user_id", rq.getUser_id());
		json.addProperty("req_title", rq.getReq_title());
		json.addProperty("req_description", rq.getReq_description());
		json.addProperty("req_status", rq.getReq_status());
		json.addProperty("req_date", rq.getReq_date());

		json.add("imageIds", gson.toJsonTree(reqWithImgs.getImageList()));

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(json));

	}

	private void serveImage(String imgId, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException {

		requestDAO dao = new requestDAO();
		byte[] imageBytes = dao.getImageById(imgId); // Must return byte[]

		if (imageBytes == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
			return;
		}

		response.setContentType("image/jpeg"); // or detect from DB if stored
		response.setContentLength(imageBytes.length);
		OutputStream out = response.getOutputStream();
		out.write(imageBytes);
		out.flush();
		out.close();
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
