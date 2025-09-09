package Admin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.inventory.util.DBConnection;

import Admin.model.Response;

public class ResponseDAO {
	private String sql1;
	private Connection conn;
	private ResultSet rsRqCount;
	private int rscount;
	private PreparedStatement pstmt;
	private int idCount;

	public int addResponse(Response response) throws ClassNotFoundException {

		try {
			conn = DBConnection.getConnection();

			String getRspID = "SELECT count(rsp_id) FROM rq_responses";
			PreparedStatement pstmtRqID = conn.prepareStatement(getRspID);
			rsRqCount = pstmtRqID.executeQuery();
			if (rsRqCount.next()) {
				idCount = rsRqCount.getInt(1);
			}
			response.rsp_id_generate(idCount + 1);

			sql1 = "INSERT INTO rq_responses values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, response.getRsp_id());
			pstmt.setString(2, response.getRq_id());
			pstmt.setString(3, response.getRsp_description());
			pstmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
			System.out.println(pstmt);

			String sql2 = "UPDATE requests SET rq_status = 'responded' WHERE Requests_id = ? ";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, response.getRq_id());

			rscount = pstmt.executeUpdate();
			System.out.println("insert query was executed");
			int u = pstmt2.executeUpdate();
			System.out.println("update query was executed");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return rscount;

	}
}
