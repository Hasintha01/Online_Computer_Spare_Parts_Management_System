package Admin.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inventory.util.DBConnection;

import Admin.model.Request;
import Admin.model.RequestWithImages;

public class requestDAO {
	private String sql1;
	private Connection conn;
	private ResultSet rsRqCount;
	private int rscount;
	private PreparedStatement pstmt;
	private String idCount;
	private ResultSet rs;
	private int imgCount;

	public int AddRequest(Request request, List<InputStream> imageStreams) throws ClassNotFoundException, SQLException {

		try {
			conn = DBConnection.getConnection();

			String getRqID = "select Requests_id from requests order by rq_date desc limit 1";
			PreparedStatement pstmtRqID = conn.prepareStatement(getRqID);
			rsRqCount = pstmtRqID.executeQuery();
			if (rsRqCount.next()) {
				idCount = rsRqCount.getString(1);
			}
			int numIdCount = Integer.parseInt(idCount.replace("RQ-", ""));
			numIdCount++;
			sql1 = "Insert into requests values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, "RQ-0" + numIdCount);
			pstmt.setString(2, request.getUser_id());
			pstmt.setString(3, request.getReq_title());
			pstmt.setString(4, request.getReq_description());
			pstmt.setString(5, "Pending");
			pstmt.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));

			rscount = pstmt.executeUpdate();
			System.out.println("executed " + pstmt);

			String sqlIMG = "INSERT INTO requests_images (rq_id, requests_images) VALUES (?, ?)";
			PreparedStatement pstmtIMG = conn.prepareStatement(sqlIMG);
			for (InputStream imgStream : imageStreams) {
				pstmtIMG.setString(1, "RQ-0" + idCount);
				pstmtIMG.setBinaryStream(2, imgStream);
				pstmtIMG.addBatch();
			}
			System.out.println(pstmtIMG);
			pstmtIMG.executeBatch();
			System.out.println("executed image query");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rsRqCount);

		}

		return rscount;

	}

	public List<Request> getallRequests() throws ClassNotFoundException, SQLException {

		List<Request> rqstList = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			sql1 = "select * from requests order by rq_date desc";
			pstmt = conn.prepareStatement(sql1);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Request rq = new Request();
				rq.setReq_id(rs.getString("Requests_id"));
				rq.setUser_id(rs.getString("user_id"));
				rq.setReq_title(rs.getString("rq_title"));
				rq.setReq_description(rs.getString("rq_description"));
				rq.setReq_status(rs.getString("rq_status"));
				rq.setReq_date(rs.getString("rq_date"));

				rqstList.add(rq);
				System.out.println(rq.getReq_id());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}

		return rqstList;

	}

	public RequestWithImages getRequestDetailsWithImages(String rq_ID) throws ClassNotFoundException, SQLException {
		RequestWithImages rqWithImgs = new RequestWithImages();
		try {
			conn = DBConnection.getConnection();
			sql1 = "SELECT * FROM rq_with_images_and_names where Requests_id=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, rq_ID);
			rs = pstmt.executeQuery();

			Request rq = new Request();
			boolean first = true;
			List<Integer> img_ID = new ArrayList<Integer>();

			while (rs.next()) {
				if (first) {
					rq.setReq_id(rs.getString("Requests_id"));
					rq.setUser_id(rs.getString("first_name"));
					rq.setReq_title(rs.getString("rq_title"));
					rq.setReq_description(rs.getString("rq_description"));
					rq.setReq_status(rs.getString("rq_status"));
					rq.setReq_date(rs.getDate("rq_date").toString());
					first = false;
				}
				// getting images id list

				img_ID.add(rs.getInt("img_id"));
				imgCount++;
			}
			rqWithImgs.setRequest(rq);
			rqWithImgs.setImageList(img_ID);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}

		return rqWithImgs;

	}

	public byte[] getImageById(String imgId) throws SQLException, ClassNotFoundException {
		byte[] imageBytes = null;

		conn = DBConnection.getConnection();
		sql1 = "SELECT requests_images FROM requests_images WHERE img_id = ?";
		pstmt = conn.prepareStatement(sql1);
		pstmt.setString(1, imgId);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			imageBytes = rs.getBytes("requests_images");
		}

		rs.close();
		pstmt.close();
		conn.close();

		return imageBytes;
	}

	public List<Request> getAllRequests_userID(String user_id) {
		List<Request> RqRpList = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			sql1 = "select * from requests where user_id=? order by rq_date desc";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();
			System.out.println("Executed: " + sql1);
			while (rs.next()) {
				Request rqRp = new Request();
				rqRp.setReq_id(rs.getString("Requests_id"));
				rqRp.setReq_title(rs.getString("rq_title"));
				rqRp.setReq_status(rs.getString("rq_status"));
				rqRp.setReq_date(rs.getDate("rq_date").toString());

				RqRpList.add(rqRp);
				System.out.println(rqRp.getReq_id());
			}
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RqRpList;
	}

	public void deleteRequestbyID(String rq_id) throws ClassNotFoundException {

		try {
			conn = DBConnection.getConnection();
			sql1 = "DELETE FROM requests WHERE Requests_id = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, rq_id);
			pstmt.executeUpdate();
			System.out.println("Rqst delete executed from DAO");

			String sql2 = "DELETE FROM requests_images WHERE rq_id = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, rq_id);
			pstmt2.executeUpdate();
			System.out.println("Rqst imgs delete executed from DAO");

			String sql3 = "DELETE FROM rq_responses WHERE rq_ID = ?";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, rq_id);
			pstmt3.executeUpdate();
			System.out.println("Rqst responses delete executed from DAO");

			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Rqst delete error from DAO");
		}
	}

}
