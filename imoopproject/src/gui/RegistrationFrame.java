package gui;

import dao.ClientInfoDAO;
import model.ClientInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;

public class RegistrationFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private boolean isEditMode = false;
	private String currentRefCode = null;
	
	// Main Info Fields
	private JTextField txtName, txtAddress, txtBirthDate, txtBirthPlace;
	private JComboBox<String> cbMaritalStatus, cbSex, cbTravelCapability;
	private JTextField txtContact, txtEmail, txtReligion, txtEthnicity, txtLanguage;
	private JTextField txtGsis, txtTin, txtPhilHealth, txtScAssoc, txtOtherGov;
	private JTextField txtJob, txtPension, txtEducation;
	
	// HR Profile Fields
	private JTextField txtTechSkills, txtCommunityService;
	
	// Relative Inputs & Table
	private JTextField txtRelName, txtRelAge, txtRelRelationship, txtRelOccupation, txtRelIncome;
	private JComboBox<String> cbRelWorking;
	private DefaultTableModel relTableModel;
	private JTable relTable;
	
	private JButton btnSave;

	public RegistrationFrame() {
		initComponents();
		setTitle("Register New Senior Citizen");
	}
	
	public RegistrationFrame(String refCode) {
		initComponents(); 
		this.isEditMode = true;
		this.currentRefCode = refCode;
		setTitle("Update Client Record: " + refCode);
		btnSave.setText("Update Record");
		loadExistingData(); 
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// WIDENED THE FRAME TO 900px
		setSize(900, 800);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);

		// Container to hold all our separate sections, stacking them vertically
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// ==========================================
		// SECTION 1: PERSONAL INFORMATION
		// ==========================================
		JPanel personalPanel = new JPanel(new GridLayout(10, 4, 10, 10)); // 4 columns now!
		personalPanel.setBorder(BorderFactory.createTitledBorder("Personal Information"));

		personalPanel.add(new JLabel("* Full Name:")); txtName = new JTextField(); personalPanel.add(txtName);
		personalPanel.add(new JLabel("* Address:")); txtAddress = new JTextField(); personalPanel.add(txtAddress);
		personalPanel.add(new JLabel("* Birth Date (YYYY-MM-DD):")); txtBirthDate = new JTextField(); personalPanel.add(txtBirthDate);
		personalPanel.add(new JLabel("* Birth Place:")); txtBirthPlace = new JTextField(); personalPanel.add(txtBirthPlace);
		personalPanel.add(new JLabel("* Sex:")); cbSex = new JComboBox<>(new String[]{"Male", "Female"}); personalPanel.add(cbSex);
		personalPanel.add(new JLabel("* Marital Status:")); cbMaritalStatus = new JComboBox<>(new String[]{"Single", "Married", "Widowed"}); personalPanel.add(cbMaritalStatus);
		personalPanel.add(new JLabel("Contact Number:")); txtContact = new JTextField(); personalPanel.add(txtContact);
		personalPanel.add(new JLabel("Email Address:")); txtEmail = new JTextField(); personalPanel.add(txtEmail);
		personalPanel.add(new JLabel("Religion:")); txtReligion = new JTextField(); personalPanel.add(txtReligion);
		personalPanel.add(new JLabel("* Ethnicity:")); txtEthnicity = new JTextField(); personalPanel.add(txtEthnicity);
		personalPanel.add(new JLabel("* Language Spoken:")); txtLanguage = new JTextField(); personalPanel.add(txtLanguage);
		personalPanel.add(new JLabel("GSIS/SSS Number:")); txtGsis = new JTextField(); personalPanel.add(txtGsis);
		personalPanel.add(new JLabel("TIN Number:")); txtTin = new JTextField(); personalPanel.add(txtTin);
		personalPanel.add(new JLabel("PhilHealth Number:")); txtPhilHealth = new JTextField(); personalPanel.add(txtPhilHealth);
		personalPanel.add(new JLabel("* SC Association ID:")); txtScAssoc = new JTextField(); personalPanel.add(txtScAssoc);
		personalPanel.add(new JLabel("Other Gov ID:")); txtOtherGov = new JTextField(); personalPanel.add(txtOtherGov);
		personalPanel.add(new JLabel("* Travel Capable?")); cbTravelCapability = new JComboBox<>(new String[]{"Yes", "No"}); personalPanel.add(cbTravelCapability);
		personalPanel.add(new JLabel("* Highest Education:")); txtEducation = new JTextField(); personalPanel.add(txtEducation);
		personalPanel.add(new JLabel("Job / Occupation:")); txtJob = new JTextField(); personalPanel.add(txtJob);
		personalPanel.add(new JLabel("Current Pension:")); txtPension = new JTextField(); personalPanel.add(txtPension);

		contentContainer.add(personalPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing

		// ==========================================
		// SECTION 2: HR PROFILE
		// ==========================================
		JPanel hrPanel = new JPanel(new GridLayout(1, 4, 10, 10));
		hrPanel.setBorder(BorderFactory.createTitledBorder("HR Profile"));
		hrPanel.add(new JLabel("Technical Skills:")); txtTechSkills = new JTextField(); hrPanel.add(txtTechSkills);
		hrPanel.add(new JLabel("Community Service:")); txtCommunityService = new JTextField(); hrPanel.add(txtCommunityService);
		
		contentContainer.add(hrPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing

		// ==========================================
		// SECTION 3: DYNAMIC RELATIVES
		// ==========================================
		JPanel relativesPanel = new JPanel(new BorderLayout(5, 5));
		relativesPanel.setBorder(BorderFactory.createTitledBorder("Family & Relatives"));
		
		// 3A. The Inputs
		JPanel relInputPanel = new JPanel(new GridLayout(2, 6, 5, 5));
		relInputPanel.add(new JLabel("Name:")); txtRelName = new JTextField(); relInputPanel.add(txtRelName);
		relInputPanel.add(new JLabel("Relationship:")); txtRelRelationship = new JTextField(); relInputPanel.add(txtRelRelationship);
		relInputPanel.add(new JLabel("Age:")); txtRelAge = new JTextField(); relInputPanel.add(txtRelAge);
		relInputPanel.add(new JLabel("Working?")); cbRelWorking = new JComboBox<>(new String[]{"Yes", "No"}); relInputPanel.add(cbRelWorking);
		relInputPanel.add(new JLabel("Occupation:")); txtRelOccupation = new JTextField(); relInputPanel.add(txtRelOccupation);
		relInputPanel.add(new JLabel("Income:")); txtRelIncome = new JTextField(); relInputPanel.add(txtRelIncome);
		
		JButton btnAddRelative = new JButton("Add Relative to List");
		
		JPanel topRelPanel = new JPanel(new BorderLayout());
		topRelPanel.add(relInputPanel, BorderLayout.CENTER);
		topRelPanel.add(btnAddRelative, BorderLayout.SOUTH);
		relativesPanel.add(topRelPanel, BorderLayout.NORTH);
		
		// 3B. The Table
		String[] relColumns = {"Name", "Relationship", "Age", "Working", "Occupation", "Income"};
		relTableModel = new DefaultTableModel(relColumns, 0);
		relTable = new JTable(relTableModel);
		JScrollPane tableScroll = new JScrollPane(relTable);
		tableScroll.setPreferredSize(new Dimension(800, 120)); // Keep it small
		relativesPanel.add(tableScroll, BorderLayout.CENTER);
		
		JButton btnRemoveRelative = new JButton("Remove Selected Row");
		relativesPanel.add(btnRemoveRelative, BorderLayout.SOUTH);

		contentContainer.add(relativesPanel);

		// Wrap the whole container in a Scroll Pane
		JScrollPane mainScroll = new JScrollPane(contentContainer);
		mainScroll.getVerticalScrollBar().setUnitIncrement(16);
		mainScroll.setBorder(null);
		mainPanel.add(mainScroll, BorderLayout.CENTER);

		// ==========================================
		// BOTTOM CONTROL PANEL
		// ==========================================
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
		JButton btnCancel = new JButton("Cancel");
		btnSave = new JButton("Save Record");
		buttonPanel.add(btnCancel);
		buttonPanel.add(btnSave);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		// --- BUTTON ACTIONS ---
		btnCancel.addActionListener(e -> dispose()); 
		
		// Dynamic Table Action: ADD
		btnAddRelative.addActionListener(e -> {
			if(txtRelName.getText().trim().isEmpty() || txtRelRelationship.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Relative Name and Relationship are required!");
				return;
			}
			relTableModel.addRow(new Object[]{
				txtRelName.getText().trim(),
				txtRelRelationship.getText().trim(),
				txtRelAge.getText().trim(),
				cbRelWorking.getSelectedItem(),
				txtRelOccupation.getText().trim(),
				txtRelIncome.getText().trim()
			});
			// Clear boxes after adding
			txtRelName.setText(""); txtRelRelationship.setText(""); txtRelAge.setText(""); 
			txtRelOccupation.setText(""); txtRelIncome.setText("");
		});
		
		// Dynamic Table Action: REMOVE
		btnRemoveRelative.addActionListener(e -> {
			int selectedRow = relTable.getSelectedRow();
			if(selectedRow != -1) relTableModel.removeRow(selectedRow);
		});
		
		btnSave.addActionListener(e -> saveClientRecord());
	}
	
	private void loadExistingData() {
		ClientInfo client = new ClientInfoDAO().getByReferenceCode(currentRefCode);
		if (client != null) {
			txtName.setText(client.getName());
			txtAddress.setText(client.getAddress());
			txtBirthDate.setText(client.getBirthDate().toString());
			txtBirthPlace.setText(client.getBirthPlace());
			cbSex.setSelectedItem(client.getSex());
			
			switch(client.getMaritalStatus()) {
				case "S": cbMaritalStatus.setSelectedItem("Single"); break;
				case "M": cbMaritalStatus.setSelectedItem("Married"); break;
				case "W": cbMaritalStatus.setSelectedItem("Widowed"); break;
			}
			cbTravelCapability.setSelectedItem(client.getTravelCapability().equals("Y") ? "Yes" : "No");
			
			txtContact.setText(client.getContactNumber() == null ? "" : client.getContactNumber());
			txtEmail.setText(client.getEmailAddress() == null ? "" : client.getEmailAddress());
			txtReligion.setText(client.getReligion() == null ? "" : client.getReligion());
			txtEthnicity.setText(client.getEthnicity());
			txtLanguage.setText(client.getLanguageSpoken());
			txtGsis.setText(client.getGsisSssNumber() == null ? "" : client.getGsisSssNumber());
			txtTin.setText(client.getTinNum() == null ? "" : client.getTinNum());
			txtPhilHealth.setText(client.getPhilhealthNum() == null ? "" : client.getPhilhealthNum());
			txtScAssoc.setText(client.getScAssociationId() == null ? "" : client.getScAssociationId());
			txtOtherGov.setText(client.getOtherGovId() == null ? "" : client.getOtherGovId());
			txtEducation.setText(client.getHighestEducationalAttainment());
			txtJob.setText(client.getJob() == null ? "" : client.getJob());
			txtPension.setText(client.getCurrentPension() == null ? "0" : client.getCurrentPension());
			
			// Note: If you want to load existing HR Profiles and Relatives into the table, 
			// you will need to fetch them from the database using their DAOs here!
		}
	}

	private void saveClientRecord() {
		// 1. STRICT NOT NULL VALIDATION
		if (txtName.getText().trim().isEmpty() || txtAddress.getText().trim().isEmpty() || 
			txtBirthDate.getText().trim().isEmpty() || txtBirthPlace.getText().trim().isEmpty() || 
			txtEthnicity.getText().trim().isEmpty() || txtLanguage.getText().trim().isEmpty() || 
			txtEducation.getText().trim().isEmpty() || txtScAssoc.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill out all fields marked with an asterisk (*).", "Missing Requirements", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			ClientInfo newClient = new ClientInfo();
			ClientInfoDAO dao = new ClientInfoDAO(); 

			if (isEditMode) {
				ClientInfo existingClient = dao.getByReferenceCode(currentRefCode);
				if (existingClient != null) newClient.setOscaIdNum(existingClient.getOscaIdNum());
				newClient.setReferenceCode(currentRefCode);
			} else {
				newClient.setOscaIdNum(null); 
			}

			newClient.setName(txtName.getText().trim());
			newClient.setAddress(txtAddress.getText().trim());
			
			// Date Validation
			java.sql.Date dob = java.sql.Date.valueOf(txtBirthDate.getText().trim());
			Calendar cal = Calendar.getInstance();
			cal.setTime(dob);
			if (cal.get(Calendar.YEAR) > 1966) {
				JOptionPane.showMessageDialog(this, "Senior citizens must be born in 1966 or earlier.", "Age Restriction", JOptionPane.ERROR_MESSAGE);
				return;
			}
			newClient.setBirthDate(dob);
			newClient.setBirthPlace(txtBirthPlace.getText().trim());
			newClient.setSex((String) cbSex.getSelectedItem());
			
			String maritalFull = (String) cbMaritalStatus.getSelectedItem();
			newClient.setMaritalStatus(maritalFull.substring(0, 1)); 
			String travelFull = (String) cbTravelCapability.getSelectedItem();
			newClient.setTravelCapability(travelFull.substring(0, 1)); 

			newClient.setContactNumber(txtContact.getText().trim().isEmpty() ? null : txtContact.getText().trim());
			newClient.setEmailAddress(txtEmail.getText().trim().isEmpty() ? null : txtEmail.getText().trim());
			newClient.setReligion(txtReligion.getText().trim().isEmpty() ? null : txtReligion.getText().trim());
			newClient.setEthnicity(txtEthnicity.getText().trim());
			newClient.setLanguageSpoken(txtLanguage.getText().trim());
			newClient.setGsisSssNumber(txtGsis.getText().trim().isEmpty() ? null : txtGsis.getText().trim());
			newClient.setTinNum(txtTin.getText().trim().isEmpty() ? null : txtTin.getText().trim());
			newClient.setPhilhealthNum(txtPhilHealth.getText().trim().isEmpty() ? null : txtPhilHealth.getText().trim());
			newClient.setScAssociationId(txtScAssoc.getText().trim());
			newClient.setOtherGovId(txtOtherGov.getText().trim().isEmpty() ? null : txtOtherGov.getText().trim());
			newClient.setJob(txtJob.getText().trim().isEmpty() ? null : txtJob.getText().trim());
			newClient.setHighestEducationalAttainment(txtEducation.getText().trim());

			String pensionText = txtPension.getText().trim();
			newClient.setCurrentPension(pensionText.isEmpty() ? "0" : pensionText);

			// --- SAVE LOGIC ---
			boolean success;
			if (isEditMode) {
				success = dao.update(newClient);
			} else {
				success = dao.insert(newClient);
			}

			if (success) {
				// HOW TO GET YOUR RELATIVES DATA OUT OF THE TABLE TO SAVE IT:
				// Because your insert() method mutates newClient, we now have the generated Ref Code!
				String savedRefCode = newClient.getReferenceCode();
				
				// System.out.println("Ready to save HR Profile for: " + savedRefCode);
				// String skills = txtTechSkills.getText().trim();
				// String service = txtCommunityService.getText().trim();
				// --> CALL HR DAO HERE
				
				// Loop through the table to get infinite relatives!
				for (int i = 0; i < relTableModel.getRowCount(); i++) {
					String rName = relTableModel.getValueAt(i, 0).toString();
					String rRel = relTableModel.getValueAt(i, 1).toString();
					String rAge = relTableModel.getValueAt(i, 2).toString();
					String rWork = relTableModel.getValueAt(i, 3).toString().substring(0, 1); // Get Y/N
					String rOcc = relTableModel.getValueAt(i, 4).toString();
					String rInc = relTableModel.getValueAt(i, 5).toString();
					
					// System.out.println("Ready to save Relative: " + rName + " for " + savedRefCode);
					// --> CALL RELATIONSHIP DAO HERE
				}

				String msg = isEditMode ? "Record successfully updated!" : "New client successfully registered!";
				JOptionPane.showMessageDialog(this, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
				dispose(); 
			} else {
				JOptionPane.showMessageDialog(this, "Failed to save to database.", "Database Error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, "Please check your date format (YYYY-MM-DD).", "Format Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}