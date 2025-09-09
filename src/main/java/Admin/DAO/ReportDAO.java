package Admin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inventory.util.DBConnection;

import Admin.model.Report;

public class ReportDAO {
	private String sql1;
	private Connection conn;
	private int rscount;
	private PreparedStatement pstmt;
	private int idCount;
	private ResultSet rs;

	public List<Report> getbuyRecords() throws ClassNotFoundException, SQLException {

		List<Report> rpList = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			sql1 = "SELECT * FROM buyers_table;";
			pstmt = conn.prepareStatement(sql1);
			System.out.println("all list displayed");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Report rp = new Report();
				rp.setFirstName(rs.getString("first_name"));
				rp.setLastName(rs.getString("last_name"));
				rp.setOrderId(rs.getInt("order_id"));
				rp.setOrderDate(rs.getDate("order_date"));
				rp.setProductName(rs.getString("product_name"));
				rp.setUnitPrice(rs.getDouble("unit_price"));
				rp.setQuantity(rs.getInt("quantity"));
				rp.setItemTotal(rs.getDouble("item_total"));

				rpList.add(rp);
				System.out.println(rp.getOrderId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}

		return rpList;

	}

	public List<Report> getbuyRecords(String from_Date, String to_Date) throws SQLException, ClassNotFoundException {
		List<Report> rpList = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			sql1 = "SELECT * FROM buyers_table WHERE order_date BETWEEN ? AND ? ORDER BY order_date DESC";
			pstmt = conn.prepareStatement(sql1);
			try {
				pstmt.setDate(1, java.sql.Date.valueOf(from_Date));
				pstmt.setDate(2, java.sql.Date.valueOf(to_Date));
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid date format passed. Returning all records.");
				return getbuyRecords();
			}
			System.out.println(sql1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Report rp = new Report();
				rp.setFirstName(rs.getString("first_name"));
				rp.setLastName(rs.getString("last_name"));
				rp.setOrderId(rs.getInt("order_id"));
				rp.setOrderDate(rs.getDate("order_date"));
				rp.setProductName(rs.getString("product_name"));
				rp.setUnitPrice(rs.getDouble("unit_price"));
				rp.setQuantity(rs.getInt("quantity"));
				rp.setItemTotal(rs.getDouble("item_total"));

				rpList.add(rp);
				System.out.println("date filtered");
				System.out.println("-------------------------------------------------------------------------------");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("-------------------------------------------------------------------------------");

		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}

		return rpList;
	}
}
