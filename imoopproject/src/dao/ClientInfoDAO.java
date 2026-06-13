package dao;

import model.ClientInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientInfoDAO {
	
	private ClientInfo mapRow(ResultSet rs) throws SQLException {
	    ClientInfo client = new ClientInfo();
	    client.setReferenceCode(rs.getString("reference_code"));
	    client.setName(rs.getString("name"));
	    client.setAddress(rs.getString("address"));
	    client.setBirthDate(rs.getDate("birth_date"));
	    client.setBirthPlace(rs.getString("birth_place"));
	    client.setMaritalStatus(rs.getString("marital_status"));
	    client.setSex(rs.getString("sex"));
	    client.setContactNumber(rs.getString("contact_number"));
	    client.setEmailAddress(rs.getString("email_address"));
	    client.setReligion(rs.getString("religion"));
	    client.setEthnicity(rs.getString("ethnicity"));
	    client.setLanguageSpoken(rs.getString("language_spoken"));
	    client.setOscaIdNum(rs.getString("osca_id_num"));
	    client.setGsisSssNumber(rs.getString("gsis_sss_number"));
	    client.setTinNum(rs.getString("tin_num"));
	    client.setPhilhealthNum(rs.getString("philhealth_num"));
	    client.setScAssociationId(rs.getString("sc_association_id"));
	    client.setOtherGovId(rs.getString("other_gov_id"));
	    client.setTravelCapability(rs.getString("travel_capability"));
	    client.setJob(rs.getString("job"));
	    client.setCurrentPension(rs.getString("current_pension"));
	    client.setHighestEducationalAttainment(rs.getString("highest_educational_attainment"));
	    return client;
	}
	
	private void setStatementParameters(PreparedStatement stmt, ClientInfo client, boolean isUpdate) throws SQLException {
	    int index = 1;
	    
	    // Insert needs the reference code at the very beginning
	    if (!isUpdate) {
	        stmt.setString(index++, client.getReferenceCode());
	    }
	    
	    stmt.setString(index++, client.getName());
	    stmt.setString(index++, client.getAddress());
	    stmt.setDate(index++, client.getBirthDate());
	    stmt.setString(index++, client.getBirthPlace());
	    stmt.setString(index++, client.getMaritalStatus());
	    stmt.setString(index++, client.getSex());
	    stmt.setString(index++, client.getContactNumber());
	    stmt.setString(index++, client.getEmailAddress());
	    stmt.setString(index++, client.getReligion());
	    stmt.setString(index++, client.getEthnicity());
	    stmt.setString(index++, client.getLanguageSpoken());
	    stmt.setString(index++, client.getOscaIdNum());
	    stmt.setString(index++, client.getGsisSssNumber());
	    stmt.setString(index++, client.getTinNum());
	    stmt.setString(index++, client.getPhilhealthNum());
	    stmt.setString(index++, client.getScAssociationId());
	    stmt.setString(index++, client.getOtherGovId());
	    stmt.setString(index++, client.getTravelCapability());
	    stmt.setString(index++, client.getJob());
	    stmt.setString(index++, client.getCurrentPension());
	    stmt.setString(index++, client.getHighestEducationalAttainment());
	    
	    // Update needs the reference code at the very end for the WHERE clause
	    if (isUpdate) {
	        stmt.setString(index++, client.getReferenceCode());
	    }
	}
	
	// Get all Client's Info
	public List<ClientInfo> getAll() {
	    List<ClientInfo> list = new ArrayList<>();
	    String sql = "SELECT * FROM client_info";
	    try (Connection conn = DBConnection.connect();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) {
	            list.add(mapRow(rs));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	// Search a specific info
	public ClientInfo getByReferenceCode(String referenceCode) {
	    String sql = "SELECT * FROM client_info WHERE reference_code = ?";
	    try (Connection conn = DBConnection.connect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, referenceCode);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) return mapRow(rs);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public boolean insert(ClientInfo client) {
	    String sql = "INSERT INTO client_info (reference_code, name, address, birth_date, birth_place, "
	               + "marital_status, sex, contact_number, email_address, religion, ethnicity, "
	               + "language_spoken, osca_id_num, gsis_sss_number, tin_num, philhealth_num, "
	               + "sc_association_id, other_gov_id, travel_capability, job, current_pension, "
	               + "highest_educational_attainment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	               
	    try (Connection conn = DBConnection.connect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        // Use the helper method (isUpdate = false)
	        setStatementParameters(stmt, client, false);
	        
	        return stmt.executeUpdate() > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	// Update an existing client
	public boolean update(ClientInfo client) {
	    String sql = "UPDATE client_info SET name = ?, address = ?, birth_date = ?, birth_place = ?, "
	               + "marital_status = ?, sex = ?, contact_number = ?, email_address = ?, religion = ?, "
	               + "ethnicity = ?, language_spoken = ?, osca_id_num = ?, gsis_sss_number = ?, "
	               + "tin_num = ?, philhealth_num = ?, sc_association_id = ?, other_gov_id = ?, "
	               + "travel_capability = ?, job = ?, current_pension = ?, highest_educational_attainment = ? "
	               + "WHERE reference_code = ?";
	               
	    try (Connection conn = DBConnection.connect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        // Use the helper method (isUpdate = true)
	        setStatementParameters(stmt, client, true);
	        
	        return stmt.executeUpdate() > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	// Delete a client by reference code
	public boolean delete(String referenceCode) {
	    // Delete doesn't need the helper since it only takes one parameter
	    String sql = "DELETE FROM client_info WHERE reference_code = ?";
	    
	    try (Connection conn = DBConnection.connect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, referenceCode);
	        return stmt.executeUpdate() > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
}