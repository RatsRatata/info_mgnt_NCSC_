package dao;

import model.ClientRelationship;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRelationshipDAO {
	
	private ClientRelationship mapRow(ResultSet rs) throws SQLException {
	    ClientRelationship rel = new ClientRelationship();
	    rel.setRelativeId(rs.getInt("relative_id"));
	    rel.setReferenceCode(rs.getString("reference_code"));
	    rel.setRelativeName(rs.getString("relative_name"));
	    rel.setRelationship(rs.getString("relationship"));
	    rel.setRelativeAge(rs.getInt("relative_age"));
	    rel.setWorkingStatus(rs.getString("working_status"));
	    rel.setOccupation(rs.getString("occupation"));
	    rel.setIncome(rs.getLong("income"));
	    return rel;
	}
	
	private void setParams(PreparedStatement stmt, ClientRelationship rel, boolean isUpdate) throws SQLException {
	    int index = 1;
	    stmt.setString(index++, rel.getReferenceCode());
	    stmt.setString(index++, rel.getRelativeName());
	    stmt.setString(index++, rel.getRelationship());
	    stmt.setInt(index++, rel.getRelativeAge());
	    stmt.setString(index++, rel.getWorkingStatus());
	    stmt.setString(index++, rel.getOccupation());
	    stmt.setLong(index++, rel.getIncome());
	    if (isUpdate) stmt.setInt(index++, rel.getRelativeId());
	}

	public boolean insert(ClientRelationship rel) {
	    String sql = "INSERT INTO client_relationship (reference_code, relative_name, relationship, relative_age, working_status, occupation, income) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        setParams(stmt, rel, false);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) { e.printStackTrace(); return false; }
	}

	public boolean update(ClientRelationship rel) {
	    String sql = "UPDATE client_relationship SET reference_code = ?, relative_name = ?, relationship = ?, relative_age = ?, working_status = ?, occupation = ?, income = ? WHERE relative_id = ?";
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        setParams(stmt, rel, true);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) { e.printStackTrace(); return false; }
	}

	public boolean delete(int relativeId) {
	    String sql = "DELETE FROM client_relationship WHERE relative_id = ?";
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, relativeId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) { e.printStackTrace(); return false; }
	}
	
	public boolean deleteByReferenceCode(String referenceCode) {
	    String sql = "DELETE FROM client_relationship WHERE reference_code = ?"; 
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, referenceCode);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) { e.printStackTrace(); return false; }
	}

	public List<ClientRelationship> getAll() {
	    List<ClientRelationship> list = new ArrayList<>();
	    String sql = "SELECT * FROM client_relationship";
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) list.add(mapRow(rs));
	    } catch (Exception e) { e.printStackTrace(); }
	    return list;
	}
	
	public List<ClientRelationship> getByReferenceCode(String referenceCode) {
	    List<ClientRelationship> list = new ArrayList<>();
	    String sql = "SELECT * FROM client_relationship WHERE reference_code = ?";
	    try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, referenceCode);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) list.add(mapRow(rs));
	        }
	    } catch (Exception e) { e.printStackTrace(); }
	    return list;
	}
}