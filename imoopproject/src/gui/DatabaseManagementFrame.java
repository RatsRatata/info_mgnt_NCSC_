package gui;

import dao.ClientInfoDAO;
import dao.ClientHrProfileDAO;
import dao.ClientRelationshipDAO;
import model.ClientInfo;
import model.ClientHrProfile;
import model.ClientRelationship;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class DatabaseManagementFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable clientInfoTable;
	private JTable hrProfileTable;
	private JTable relationshipTable;
	
	private TableRowSorter<DefaultTableModel> infoSorter;
	private TableRowSorter<DefaultTableModel> hrSorter;
	private TableRowSorter<DefaultTableModel> relSorter;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new DatabaseManagementFrame().setVisible(true);
			} catch (Exception e) { e.printStackTrace(); }
		});
	}

	public DatabaseManagementFrame() {
		setTitle("NCSC Database Management System - Admin Dashboard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setResizable(false);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				for (java.awt.Window window : java.awt.Window.getWindows()) {
					if (window instanceof RegistrationFrame) window.dispose();
				}
			}
			@Override
			public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				new MainMenuFrame().setVisible(true);
			}
		});
		setBounds(100, 100, 950, 650); 
		setLocationRelativeTo(null); 
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0)); 
		setContentPane(contentPane);

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(searchPanel, BorderLayout.NORTH);
		
		searchPanel.add(new JLabel("Search Records: "));
		JTextField searchField = new JTextField(30);
		searchPanel.add(searchField);
		
		JButton btnReload = new JButton("Reload Data");
		btnReload.addActionListener(e -> {
			loadClientInfoData(); loadHrProfileData(); loadRelationshipData();
			JOptionPane.showMessageDialog(this, "Dashboard refreshed!");
		});
		searchPanel.add(btnReload);
		
		searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterTables(searchField.getText()); }
            public void removeUpdate(DocumentEvent e) { filterTables(searchField.getText()); }
            public void changedUpdate(DocumentEvent e) { filterTables(searchField.getText()); }
        });

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		clientInfoTable = new JTable(); clientInfoTable.getTableHeader().setReorderingAllowed(false);
		hrProfileTable = new JTable(); hrProfileTable.getTableHeader().setReorderingAllowed(false);
		relationshipTable = new JTable(); relationshipTable.getTableHeader().setReorderingAllowed(false);
		
		tabbedPane.addTab("Client Information", new JScrollPane(clientInfoTable));
		tabbedPane.addTab("HR Profile", new JScrollPane(hrProfileTable));
		tabbedPane.addTab("Relationships", new JScrollPane(relationshipTable));
		
		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add Record");
		JButton btnEdit = new JButton("Update Selected");
		JButton btnDelete = new JButton("Delete Selected");
		
		controlPanel.add(btnAdd); controlPanel.add(btnEdit); controlPanel.add(btnDelete);
		
		btnAdd.addActionListener(e -> {
			RegistrationFrame addFrame = new RegistrationFrame();
			addFrame.setVisible(true);
		});
		
		btnEdit.addActionListener(e -> {
			if (tabbedPane.getSelectedIndex() != 0) {
				JOptionPane.showMessageDialog(null, "Please select the Client Information tab to edit a profile.");
				return;
			}
			int selectedRow = clientInfoTable.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Please select a client to edit.");
				return;
			}
			String refCode = clientInfoTable.getModel().getValueAt(clientInfoTable.convertRowIndexToModel(selectedRow), 0).toString();
			new RegistrationFrame(refCode).setVisible(true);
		});
		
		btnDelete.addActionListener(e -> {
			int tab = tabbedPane.getSelectedIndex();
			JTable targetTable = tab == 0 ? clientInfoTable : (tab == 1 ? hrProfileTable : relationshipTable);
			
			int row = targetTable.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Please select a record to delete.");
				return;
			}
			
			String msg = tab == 0 ? "WARNING: This deletes the client AND all their profiles/relatives. Continue?" : "Delete this record?";
			if (JOptionPane.showConfirmDialog(null, msg, "Confirm Delete", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;

			int modelRow = targetTable.convertRowIndexToModel(row);
			
			if (tab == 0) {
				String refCode = targetTable.getModel().getValueAt(modelRow, 0).toString();
				new ClientHrProfileDAO().deleteByReferenceCode(refCode);
				new ClientRelationshipDAO().deleteByReferenceCode(refCode);
				if (new ClientInfoDAO().delete(refCode)) JOptionPane.showMessageDialog(null, "Client completely removed.");
			} else if (tab == 1) {
				int skillId = Integer.parseInt(targetTable.getModel().getValueAt(modelRow, 0).toString());
				new ClientHrProfileDAO().delete(skillId);
			} else if (tab == 2) {
				int relativeId = Integer.parseInt(targetTable.getModel().getValueAt(modelRow, 0).toString());
				new ClientRelationshipDAO().delete(relativeId);
			}
			
			loadClientInfoData(); loadHrProfileData(); loadRelationshipData();
		});

		loadClientInfoData(); loadHrProfileData(); loadRelationshipData();
	}

	private void filterTables(String text) {
	    if (text.trim().isEmpty()) {
	        infoSorter.setRowFilter(null); hrSorter.setRowFilter(null); relSorter.setRowFilter(null);
	    } else {
	        infoSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	        hrSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	        relSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	    }
	}

	private void loadClientInfoData() {
	    List<ClientInfo> clients = new ClientInfoDAO().getAll();
	    String[] columnNames = {
	        "Reference Code", "Name", "Address", "Birth Date", "Birth Place", 
	        "Marital Status", "Sex", "Contact Number", "Email", "Religion", 
	        "Ethnicity", "Language", "OSCA ID", "GSIS/SSS", "TIN", 
	        "PhilHealth", "SC Assoc ID", "Other Gov ID", "Travel Capable", 
	        "Education", "Job", "Pension"
	    };
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
	        @Override public boolean isCellEditable(int row, int column) { return false; }
	    };

	    for (ClientInfo client : clients) {
	        model.addRow(new Object[]{ 
	            client.getReferenceCode(), client.getName(), client.getAddress(), client.getBirthDate(), 
	            client.getBirthPlace(), client.getMaritalStatus(), client.getSex(), client.getContactNumber(), 
	            client.getEmailAddress(), client.getReligion(), client.getEthnicity(), client.getLanguageSpoken(), 
	            client.getOscaIdNum(), client.getGsisSssNumber(), client.getTinNum(), client.getPhilhealthNum(), 
	            client.getScAssociationId(), client.getOtherGovId(), client.getTravelCapability(), 
	            client.getHighestEducationalAttainment(), client.getJob(), client.getCurrentPension(), 
	        });
	    }
	    
	    clientInfoTable.setModel(model);
	    clientInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    for (int i = 0; i < clientInfoTable.getColumnCount(); i++) clientInfoTable.getColumnModel().getColumn(i).setPreferredWidth(150);
	    
	    infoSorter = new TableRowSorter<>(model);
	    clientInfoTable.setRowSorter(infoSorter);
	}
	
	private void loadHrProfileData() {
	    List<ClientHrProfile> profiles = new ClientHrProfileDAO().getAll();
	    String[] columns = {"Skill ID", "Reference Code", "Technical Skills", "Community Service"};
	    DefaultTableModel model = new DefaultTableModel(columns, 0) {
	        @Override public boolean isCellEditable(int row, int column) { return false; }
	    };

	    for (ClientHrProfile profile : profiles) {
	        model.addRow(new Object[]{ profile.getSkillId(), profile.getReferenceCode(), profile.getTechnicalSkills(), profile.getCommunityService() });
	    }
	    hrProfileTable.setModel(model);
	    hrSorter = new TableRowSorter<>(model);
	    hrProfileTable.setRowSorter(hrSorter);
	}
	
	private void loadRelationshipData() {
	    List<ClientRelationship> relatives = new ClientRelationshipDAO().getAll();
	    String[] columns = {"Relative ID", "Reference Code", "Name", "Relationship", "Age", "Working Status", "Occupation", "Income"};
	    DefaultTableModel model = new DefaultTableModel(columns, 0) {
	        @Override public boolean isCellEditable(int row, int column) { return false; }
	    };

	    for (ClientRelationship rel : relatives) {
	        model.addRow(new Object[]{ rel.getRelativeId(), rel.getReferenceCode(), rel.getRelativeName(), 
	        		rel.getRelationship(), rel.getRelativeAge(), rel.getWorkingStatus(), rel.getOccupation(), rel.getIncome() });
	    }
	    relationshipTable.setModel(model);
	    relSorter = new TableRowSorter<>(model);
	    relationshipTable.setRowSorter(relSorter);
	}
}