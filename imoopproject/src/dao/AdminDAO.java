package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    /**
     * Validates admin credentials against the database.
     * Uses PreparedStatement to prevent SQL Injection attacks.
     */
    public boolean validateLogin(String username, String password) {
        // Query looking for a matching username and password combination
        String sql = "SELECT * FROM admin_credentials WHERE username = ? AND password = ?";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set the parameters from the login form
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                // If rs.next() is true, it means a matching record was found!
                return rs.next();
            }
            
        } catch (SQLException e) {
            System.out.println("Error verifying admin login credentials:");
            e.printStackTrace();
            return false;
        }
    }
}