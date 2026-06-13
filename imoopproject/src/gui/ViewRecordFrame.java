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

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		// Main Client Info
		ClientInfo client = new ClientInfoDAO().getByReferenceCode(refCode);
		
		String[] infoColumns = {"Field", "Data"};
		DefaultTableModel infoModel = new DefaultTableModel(infoColumns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { return false; } // Read-only
		};
		
		if (client != null) {
			infoModel.addRow(new Object[]{"Reference Code", client.getReferenceCode()});
			infoModel.addRow(new Object[]{"Name", client.getName()});
			infoModel.addRow(new Object[]{"Address", client.getAddress()});
			infoModel.addRow(new Object[]{"Birth Date", client.getBirthDate()});
			infoModel.addRow(new Object[]{"Birth Place", client.getBirthPlace()});
			infoModel.addRow(new Object[]{"Marital Status", client.getMaritalStatus()});
			infoModel.addRow(new Object[]{"Sex", client.getSex()});
			infoModel.addRow(new Object[]{"Contact Number", client.getContactNumber()});
			infoModel.addRow(new Object[]{"Email", client.getEmailAddress()});
			infoModel.addRow(new Object[]{"Religion", client.getReligion()});
			infoModel.addRow(new Object[]{"Ethnicity", client.getEthnicity()});
			infoModel.addRow(new Object[]{"Language Spoken", client.getLanguageSpoken()});
			infoModel.addRow(new Object[]{"OSCA ID", client.getOscaIdNum()});
			infoModel.addRow(new Object[]{"GSIS/SSS Number", client.getGsisSssNumber()});
			infoModel.addRow(new Object[]{"TIN Number", client.getTinNum()});
			infoModel.addRow(new Object[]{"PhilHealth Number", client.getPhilhealthNum()});
			infoModel.addRow(new Object[]{"SC Association ID", client.getScAssociationId()});
			infoModel.addRow(new Object[]{"Other Gov ID", client.getOtherGovId()});
			infoModel.addRow(new Object[]{"Travel Capability", client.getTravelCapability()});
			infoModel.addRow(new Object[]{"Education", client.getHighestEducationalAttainment()});
			infoModel.addRow(new Object[]{"Job", client.getJob()});
			infoModel.addRow(new Object[]{"Current Pension", client.getCurrentPension()});
		}
		
		JTable infoTable = new JTable(infoModel);
		gui.UIUtils.applyModernTableStyle(infoTable);
		
		infoTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		infoTable.getColumnModel().getColumn(0).setMaxWidth(200);
		infoTable.getColumnModel().getColumn(1).setPreferredWidth(550);
		
		tabbedPane.addTab("Personal Details", null, new JScrollPane(infoTable), null);

		// HR Profile
		List<ClientHrProfile> profiles = new ClientHrProfileDAO().getByReferenceCode(refCode);
		String[] hrColumns = {"Technical Skills", "Community Service"};
		DefaultTableModel hrModel = new DefaultTableModel(hrColumns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { return false; } 
		};
		
		for (ClientHrProfile profile : profiles) {
			hrModel.addRow(new Object[]{profile.getTechnicalSkills(), profile.getCommunityService()});
		}
		
		JTable hrTable = new JTable(hrModel);
		gui.UIUtils.applyModernTableStyle(hrTable);
		
		tabbedPane.addTab("HR Profile", null, new JScrollPane(hrTable), null);

		// Relationships
		List<ClientRelationship> relatives = new ClientRelationshipDAO().getByReferenceCode(refCode);
		String[] relColumns = {"Name", "Relationship", "Age", "Working Status", "Occupation", "Income"};
		DefaultTableModel relModel = new DefaultTableModel(relColumns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { return false; }
		};
		
		for (ClientRelationship rel : relatives) {
			relModel.addRow(new Object[]{rel.getRelativeName(), rel.getRelationship(), rel.getRelativeAge(), 
										 rel.getWorkingStatus(), rel.getOccupation(), rel.getIncome()});
		}
		
		JTable relTable = new JTable(relModel);
		gui.UIUtils.applyModernTableStyle(relTable);
		
		tabbedPane.addTab("Relationships", null, new JScrollPane(relTable), null);
	}
}