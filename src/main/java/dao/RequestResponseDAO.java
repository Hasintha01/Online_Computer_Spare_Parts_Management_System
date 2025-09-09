package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.inventory.util.DBConnection;

import model.RequestResponse;

public class RequestResponseDAO {

	private String sql1;
	private Connection conn;
	private ResultSet rsRqCount;
	private int rscount;
	private PreparedStatement pstmt;
	private int idCount;
	private ResultSet rs;
	private int imgCount;

	public List<RequestResponse> getAllResponses_ReqID(String req_id) {
		List<RequestResponse> RqRpList = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			sql1 = "SELECT * FROM request_response where Requests_id=? order by rsp_date desc";
			pstmt = conn.prepareStatement(sql1);

			pstmt.setString(1, req_id);
			rs = pstmt.executeQuery();
			System.out.println("Executed: " + sql1);
			while (rs.next()) {
				RequestResponse rqRp = new RequestResponse();
				rqRp.setRequestsId(rs.getString("Requests_id"));
				rqRp.setUserId(rs.getString("user_id"));
				rqRp.setRqTitle(rs.getString("rq_title"));
				rqRp.setRqDescription(rs.getString("rq_description"));
				rqRp.setRqDate(rs.getDate("rq_date"));
				rqRp.setRspId(rs.getString("rsp_id"));
				rqRp.setRspDescription(rs.getString("rsp_description"));
				rqRp.setRspDate(rs.getDate("rsp_date"));

				RqRpList.add(rqRp);
				System.out.println(rqRp.getRequestsId() + " : " + rqRp.getRspId());
			}

			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RqRpList;
	}
}
