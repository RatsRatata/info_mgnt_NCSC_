package gui;

import dao.ClientInfoDAO;
import dao.ClientHrProfileDAO;
import dao.ClientRelationshipDAO;
import model.ClientInfo;
import model.ClientHrProfile;
import model.ClientRelationship;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewRecordFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ViewRecordFrame(String refCode) {
		setTitle("Client Record: " + refCode);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setLocationRelativeTo(null); 
		setResizable(false);

		contentPane = new JPanel(new BorderLayout(0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		// --- 1. Main Client Info (Cleaned up with a 2D Array) ---
		ClientInfo client = new ClientInfoDAO().getByReferenceCode(refCode);
		String[] infoColumns = {"Field", "Data"};
		
		Object[][] infoData = new Object[0][0];
		if (client != null) {
			infoData = new Object[][] {
				{"Reference Code", client.getReferenceCode()}, {"Name", client.getName()},
				{"Address", client.getAddress()}, {"Birth Date", client.getBirthDate()},
				{"Birth Place", client.getBirthPlace()}, {"Marital Status", client.getMaritalStatus()},
				{"Sex", client.getSex()}, {"Contact Number", client.getContactNumber()},
				{"Email", client.getEmailAddress()}, {"Religion", client.getReligion()},
				{"Ethnicity", client.getEthnicity()}, {"Language Spoken", client.getLanguageSpoken()},
				{"OSCA ID", client.getOscaIdNum()}, {"GSIS/SSS Number", client.getGsisSssNumber()},
				{"TIN Number", client.getTinNum()}, {"PhilHealth Number", client.getPhilhealthNum()},
				{"SC Association ID", client.getScAssociationId()}, {"Other Gov ID", client.getOtherGovId()},
				{"Travel Capability", client.getTravelCapability()}, {"Education", client.getHighestEducationalAttainment()},
				{"Job", client.getJob()}, {"Current Pension", client.getCurrentPension()}
			};
		}
		
		DefaultTableModel infoModel = new DefaultTableModel(infoData, infoColumns) {
			@Override public boolean isCellEditable(int row, int column) { return false; } 
		};
		
		JTable infoTable = new JTable(infoModel);
		gui.UIUtils.applyModernTableStyle(infoTable);
		infoTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		infoTable.getColumnModel().getColumn(0).setMaxWidth(200);
		infoTable.getColumnModel().getColumn(1).setPreferredWidth(550);
		tabbedPane.addTab("Personal Details", new JScrollPane(infoTable));

		// --- 2. HR Profile ---
		List<ClientHrProfile> profiles = new ClientHrProfileDAO().getByReferenceCode(refCode);
		DefaultTableModel hrModel = new DefaultTableModel(new String[]{"Technical Skills", "Community Service"}, 0) {
			@Override public boolean isCellEditable(int row, int column) { return false; } 
		};
		for (ClientHrProfile p : profiles) hrModel.addRow(new Object[]{p.getTechnicalSkills(), p.getCommunityService()});
		
		JTable hrTable = new JTable(hrModel);
		gui.UIUtils.applyModernTableStyle(hrTable);
		tabbedPane.addTab("HR Profile", new JScrollPane(hrTable));

		// --- 3. Relationships ---
		List<ClientRelationship> relatives = new ClientRelationshipDAO().getByReferenceCode(refCode);
		DefaultTableModel relModel = new DefaultTableModel(new String[]{"Name", "Relationship", "Age", "Working Status", "Occupation", "Income"}, 0) {
			@Override public boolean isCellEditable(int row, int column) { return false; }
		};
		for (ClientRelationship r : relatives) relModel.addRow(new Object[]{r.getRelativeName(), r.getRelationship(), r.getRelativeAge(), r.getWorkingStatus(), r.getOccupation(), r.getIncome()});
		
		JTable relTable = new JTable(relModel);
		gui.UIUtils.applyModernTableStyle(relTable);
		tabbedPane.addTab("Relationships", new JScrollPane(relTable));
	}
}