package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Part;
import util.DBConnection;

public class PartDAO {
    public void addPart(Part part) throws SQLException {
        String sql = "INSERT INTO Parts (name, price, stock) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, part.getName());
            stmt.setDouble(2, part.getPrice());
            stmt.setInt(3, part.getStock());
            stmt.executeUpdate();
        }
    }

    public List<Part> getAllParts() throws SQLException {
        List<Part> parts = new ArrayList<>();
        String sql = "SELECT * FROM Parts ORDER BY name ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                parts.add(new Part(
                    rs.getInt("part_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        }
        return parts;
    }
}