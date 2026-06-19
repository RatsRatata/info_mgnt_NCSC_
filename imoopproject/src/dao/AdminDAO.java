// This is for admin login.

package dao;

import java.sql.*;

public class AdminDAO {
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT 1 FROM admin_credentials WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) { return rs.next(); }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}