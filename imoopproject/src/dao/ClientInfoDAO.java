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
	
	
}