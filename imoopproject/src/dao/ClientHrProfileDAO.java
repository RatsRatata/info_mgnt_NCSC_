package dao;

import model.ClientHrProfile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientHrProfileDAO {
	
	// Data Mapper, holds the data when giving out read instructions. This solely exists so we don't 
	// have to write 4 attribute names for every CRUD Operation.
	private ClientHrProfile mapRow(ResultSet rs) throws SQLException {
	    ClientHrProfile profile = new ClientHrProfile();
	    profile.setSkillId(rs.getInt("skill_id"));
	    profile.setReferenceCode(rs.getString("reference_code"));
	    profile.setTechnicalSkills(rs.getString("technical_skills"));
	    profile.setCommunityService(rs.getString("community_service"));
	    return profile;
	}
	
	// Reverse of mapRow. Used for creation, deletion, and updating of data. This solely exists so we don't
	// have to write 3 attribute names for every CRUD operation.
	private void setParams(PreparedStatement stmt, ClientHrProfile profile, boolean isUpdate) throws SQLException {
	    int index = 1;
	    stmt.setString(index++, profile.getReferenceCode());
	    stmt.setString(index++, profile.getTechnicalSkills());
	    stmt.setString(index++, profile.getCommunityService());
	    if (isUpdate) stmt.setInt(index++, profile.getSkillId());
	}
	
	// CRUD OPERATIONS
	
	// Create or Insert Operation
	public boolean insert(ClientHrProfile profile) {
	    String sql = "INSERT INTO client_hr_profile (reference_code, technical_skills, community_service) VALUES (?, ?, ?)";
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        setParams(stmt, profile, false);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) { e.printStackTrace(); return false; }
	}
	
	// Get All or Read Operation
	public List<ClientHrProfile> getAll() {
	    List<ClientHrProfile> list = new ArrayList<>();
	    String sql = "SELECT * FROM client_hr_profile";
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) list.add(mapRow(rs));
	    } catch (Exception e) { e.printStackTrace(); }
	    return list;
	}
	
	// Get by Reference Code
	public List<ClientHrProfile> getByReferenceCode(String referenceCode) {
	    List<ClientHrProfile> list = new ArrayList<>();
	    String sql = "SELECT * FROM client_hr_profile WHERE reference_code = ?";
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, referenceCode);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) list.add(mapRow(rs));
	        }
	    } catch (Exception e) { e.printStackTrace(); }
	    return list;
	}
	
	// Update Operation
	public boolean update(ClientHrProfile profile) {
	    String sql = "UPDATE client_hr_profile SET reference_code = ?, technical_skills = ?, community_service = ? WHERE skill_id = ?";
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        setParams(stmt, profile, true);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) { e.printStackTrace(); return false; }
	}
	
	// Delete Operation
	public boolean delete(int skillId) {
	    String sql = "DELETE FROM client_hr_profile WHERE skill_id = ?";
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, skillId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) { e.printStackTrace(); return false; }
	}

	// Delete by Reference Code
	public boolean deleteByReferenceCode(String referenceCode) {
	    String sql = "DELETE FROM client_hr_profile WHERE reference_code = ?"; 
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, referenceCode);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) { e.printStackTrace(); return false; }
	}
		
}