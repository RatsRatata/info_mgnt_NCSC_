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
	
	// Sorters for the search function
	private TableRowSorter<DefaultTableModel> infoSorter;
	private TableRowSorter<DefaultTableModel> hrSorter;
	private TableRowSorter<DefaultTableModel> relSorter;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseManagementFrame frame = new DatabaseManagementFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DatabaseManagementFrame() {
		setTitle("NCSC Database Management System - Admin Dashboard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		// Add a listener to detect when the window is closing
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				// Reopen the public Main Menu when the Admin Dashboard closes
				MainMenuFrame mainMenu = new MainMenuFrame();
				mainMenu.setVisible(true);
			}
		});
		setBounds(100, 100, 950, 650); 
		setLocationRelativeTo(null); 
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0)); 
		setContentPane(contentPane);

		// --- TOP: Search Bar Panel ---
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(searchPanel, BorderLayout.NORTH);
		
		JLabel lblSearch = new JLabel("Search Records: ");
		JTextField searchField = new JTextField(30);
		searchPanel.add(lblSearch);
		searchPanel.add(searchField);
		
		// Add real-time typing listener for the search bar
		searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterTables(searchField.getText()); }
            public void removeUpdate(DocumentEvent e) { filterTables(searchField.getText()); }
            public void changedUpdate(DocumentEvent e) { filterTables(searchField.getText()); }
        });

		// --- CENTER: Tabbed Data Views ---
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		clientInfoTable = new JTable();
		tabbedPane.addTab("Client Information", null, new JScrollPane(clientInfoTable), null);
		
		hrProfileTable = new JTable();
		tabbedPane.addTab("HR Profile", null, new JScrollPane(hrProfileTable), null);

		relationshipTable = new JTable();
		tabbedPane.addTab("Relationships", null, new JScrollPane(relationshipTable), null);
		
		// --- BOTTOM: Control Panel ---
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add Record");
		JButton btnEdit = new JButton("Edit Selected");
		JButton btnDelete = new JButton("Delete Selected");
		
		controlPanel.add(btnAdd);
		controlPanel.add(btnEdit);
		controlPanel.add(btnDelete);

		// --- MASTER DELETE LOGIC ---
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int selectedTab = tabbedPane.getSelectedIndex();
						
					if (selectedTab == 0) {
						// MASTER DELETE: If on the main tab, wipe everything for this client
						int row = clientInfoTable.getSelectedRow();
						if (row == -1) {
							javax.swing.JOptionPane.showMessageDialog(null, "Please select a client to delete.");
							return;
						}
							
						// Get the ref code based on the visual model (in case they are searching)
						int modelRow = clientInfoTable.convertRowIndexToModel(row);
							String refCode = clientInfoTable.getModel().getValueAt(modelRow, 0).toString();
							
							int confirm = javax.swing.JOptionPane.showConfirmDialog(null, 
									"WARNING: This will permanently delete the client, their HR profile, and all their relationships. Continue?", 
									"Confirm Master Delete", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
							
							if (confirm == javax.swing.JOptionPane.YES_OPTION) {
								// Delete dependents first, then the main record
								new ClientHrProfileDAO().deleteByReferenceCode(refCode);
								new ClientRelationshipDAO().deleteByReferenceCode(refCode);
								
								if (new ClientInfoDAO().delete(refCode)) {
									// Refresh all tables to show the wiped data
									loadClientInfoData();
									loadHrProfileData();
									loadRelationshipData();
									javax.swing.JOptionPane.showMessageDialog(null, "Client completely removed from database.");
								}
							}
						} else if (selectedTab == 1) {
							// HR Profile standard delete
							int row = hrProfileTable.getSelectedRow();
							if (row == -1) {
								javax.swing.JOptionPane.showMessageDialog(null, "Please select an HR profile to delete.");
								return;
							}
							
							// Add confirmation dialog
							int confirm = javax.swing.JOptionPane.showConfirmDialog(null, 
									"Are you sure you want to delete this HR Profile record?", 
									"Confirm Delete", javax.swing.JOptionPane.YES_NO_OPTION);
									
							if (confirm == javax.swing.JOptionPane.YES_OPTION) {
								int modelRow = hrProfileTable.convertRowIndexToModel(row);
								int skillId = Integer.parseInt(hrProfileTable.getModel().getValueAt(modelRow, 0).toString());
								
								if (new ClientHrProfileDAO().delete(skillId)) loadHrProfileData();
							}
							
						} else if (selectedTab == 2) {
							// Relationship standard delete
							int row = relationshipTable.getSelectedRow();
							if (row == -1) {
								javax.swing.JOptionPane.showMessageDialog(null, "Please select a relative to delete.");
								return;
							}
							
							// Add confirmation dialog
							int confirm = javax.swing.JOptionPane.showConfirmDialog(null, 
									"Are you sure you want to delete this Relationship record?", 
									"Confirm Delete", javax.swing.JOptionPane.YES_NO_OPTION);
									
							if (confirm == javax.swing.JOptionPane.YES_OPTION) {
								int modelRow = relationshipTable.convertRowIndexToModel(row);
								int relativeId = Integer.parseInt(relationshipTable.getModel().getValueAt(modelRow, 0).toString());
								
								if (new ClientRelationshipDAO().delete(relativeId)) loadRelationshipData();
							}
						}
					}
				});

		// Load data initially
		loadClientInfoData();
		loadHrProfileData();
		loadRelationshipData();
	}

	// --- FILTER LOGIC ---
	private void filterTables(String text) {
		// (?i) makes the search case-insensitive
	    if (text.trim().length() == 0) {
	        infoSorter.setRowFilter(null);
	        hrSorter.setRowFilter(null);
	        relSorter.setRowFilter(null);
	    } else {
	        infoSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	        hrSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	        relSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	    }
	}

	// --- DATA LOADING METHODS (Updated for Sorters) ---
	private void loadClientInfoData() {
	    List<ClientInfo> clients = new ClientInfoDAO().getAll();
	    String[] columnNames = {"Reference Code", "Name", "Birth Date", "Sex", "Contact Number", "Job", "Education"};
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

	    for (ClientInfo client : clients) {
	        model.addRow(new Object[]{ client.getReferenceCode(), client.getName(), client.getBirthDate(), 
	        		client.getSex(), client.getContactNumber(), client.getJob(), client.getHighestEducationalAttainment() });
	    }
	    clientInfoTable.setModel(model);
	    
	    // Attach the sorter
	    infoSorter = new TableRowSorter<>(model);
	    clientInfoTable.setRowSorter(infoSorter);
	}
	
	private void loadHrProfileData() {
	    List<ClientHrProfile> profiles = new ClientHrProfileDAO().getAll();
	    String[] columns = {"Skill ID", "Reference Code", "Technical Skills", "Community Service"};
	    DefaultTableModel model = new DefaultTableModel(columns, 0);

	    for (ClientHrProfile profile : profiles) {
	        model.addRow(new Object[]{ profile.getSkillId(), profile.getReferenceCode(), 
	        		profile.getTechnicalSkills(), profile.getCommunityService() });
	    }
	    hrProfileTable.setModel(model);
	    
	    // Attach the sorter
	    hrSorter = new TableRowSorter<>(model);
	    hrProfileTable.setRowSorter(hrSorter);
	}
	
	private void loadRelationshipData() {
	    List<ClientRelationship> relatives = new ClientRelationshipDAO().getAll();
	    String[] columns = {"Relative ID", "Reference Code", "Name", "Relationship", "Age", "Working Status", "Occupation", "Income"};
	    DefaultTableModel model = new DefaultTableModel(columns, 0);

	    for (ClientRelationship rel : relatives) {
	        model.addRow(new Object[]{ rel.getRelativeId(), rel.getReferenceCode(), rel.getRelativeName(), 
	        		rel.getRelationship(), rel.getRelativeAge(), rel.getWorkingStatus(), rel.getOccupation(), rel.getIncome() });
	    }
	    relationshipTable.setModel(model);
	    
	    // Attach the sorter
	    relSorter = new TableRowSorter<>(model);
	    relationshipTable.setRowSorter(relSorter);
	}
}