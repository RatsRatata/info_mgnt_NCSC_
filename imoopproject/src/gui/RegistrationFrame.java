package gui;

import dao.ClientInfoDAO;
import dao.ClientHrProfileDAO;
import dao.ClientRelationshipDAO;
import model.ClientInfo;
import model.ClientHrProfile;
import model.ClientRelationship;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;
import java.util.List;

public class RegistrationFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private boolean isEditMode = false;
	private String currentRefCode = null;
	
	private JTextField txtName, txtAddress, txtBirthDate, txtBirthPlace;
	private JComboBox<String> cbMaritalStatus, cbSex, cbTravelCapability;
	private JTextField txtContact, txtEmail, txtReligion, txtEthnicity, txtLanguage;
	private JTextField txtGsis, txtTin, txtPhilHealth, txtScAssoc, txtOtherGov;
	private JTextField txtJob, txtPension, txtEducation;
	
	private JTextField txtTechSkills, txtCommunityService;
	
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
		setSize(900, 800);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);

		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Client Info
		JPanel personalPanel = new JPanel(new GridLayout(10, 4, 10, 10)); 
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
		personalPanel.add(new JLabel("* SC Assoc / OSCA ID:")); txtScAssoc = new JTextField(); personalPanel.add(txtScAssoc); // Updated Label
		personalPanel.add(new JLabel("Other Gov ID:")); txtOtherGov = new JTextField(); personalPanel.add(txtOtherGov);
		personalPanel.add(new JLabel("* Travel Capable?")); cbTravelCapability = new JComboBox<>(new String[]{"Yes", "No"}); personalPanel.add(cbTravelCapability);
		personalPanel.add(new JLabel("* Highest Education:")); txtEducation = new JTextField(); personalPanel.add(txtEducation);
		personalPanel.add(new JLabel("Job / Occupation:")); txtJob = new JTextField(); personalPanel.add(txtJob);
		personalPanel.add(new JLabel("Current Pension:")); txtPension = new JTextField(); personalPanel.add(txtPension);

		contentContainer.add(personalPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10))); 

		// HR Profile
		JPanel hrPanel = new JPanel(new GridLayout(1, 4, 10, 10));
		hrPanel.setBorder(BorderFactory.createTitledBorder("HR Profile"));
		hrPanel.add(new JLabel("Technical Skills:")); txtTechSkills = new JTextField(); hrPanel.add(txtTechSkills);
		hrPanel.add(new JLabel("Community Service:")); txtCommunityService = new JTextField(); hrPanel.add(txtCommunityService);
		
		contentContainer.add(hrPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10))); 

		// Relationships
		JPanel relativesPanel = new JPanel(new BorderLayout(5, 5));
		relativesPanel.setBorder(BorderFactory.createTitledBorder("Family & Relatives"));
		
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
		
		String[] relColumns = {"Name", "Relationship", "Age", "Working", "Occupation", "Income"};
		relTableModel = new DefaultTableModel(relColumns, 0);
		relTable = new JTable(relTableModel);
		JScrollPane tableScroll = new JScrollPane(relTable);
		tableScroll.setPreferredSize(new Dimension(800, 120)); 
		relativesPanel.add(tableScroll, BorderLayout.CENTER);
		
		JButton btnRemoveRelative = new JButton("Remove Selected Row");
		relativesPanel.add(btnRemoveRelative, BorderLayout.SOUTH);

		contentContainer.add(relativesPanel);

		JScrollPane mainScroll = new JScrollPane(contentContainer);
		mainScroll.getVerticalScrollBar().setUnitIncrement(16);
		mainScroll.setBorder(null);
		mainPanel.add(mainScroll, BorderLayout.CENTER);

		// Buttons
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
		JButton btnCancel = new JButton("Cancel");
		btnSave = new JButton("Save Record");
		buttonPanel.add(btnCancel);
		buttonPanel.add(btnSave);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		btnCancel.addActionListener(e -> dispose()); 
		
		btnAddRelative.addActionListener(e -> {

			relTableModel.addRow(new Object[]{
				txtRelName.getText().trim(), txtRelRelationship.getText().trim(),
				txtRelAge.getText().trim(), cbRelWorking.getSelectedItem(),
				txtRelOccupation.getText().trim(), txtRelIncome.getText().trim()
			});
			txtRelName.setText(""); txtRelRelationship.setText(""); txtRelAge.setText(""); 
			txtRelOccupation.setText(""); txtRelIncome.setText("");
		});
		
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
			txtScAssoc.setText(client.getScAssociationId() == null ? "" : client.getScAssociationId()); // Also handles OSCA ID visually
			txtOtherGov.setText(client.getOtherGovId() == null ? "" : client.getOtherGovId());
			txtEducation.setText(client.getHighestEducationalAttainment());
			txtJob.setText(client.getJob() == null ? "" : client.getJob());
			txtPension.setText(client.getCurrentPension() == null ? "0" : client.getCurrentPension());
			
			List<ClientHrProfile> hrList = new ClientHrProfileDAO().getByReferenceCode(currentRefCode);
			if (hrList != null && !hrList.isEmpty()) {
				ClientHrProfile hr = hrList.get(0); 
				txtTechSkills.setText(hr.getTechnicalSkills() == null ? "" : hr.getTechnicalSkills());
				txtCommunityService.setText(hr.getCommunityService() == null ? "" : hr.getCommunityService());
			}
			
			List<ClientRelationship> relList = new ClientRelationshipDAO().getByReferenceCode(currentRefCode);
			if (relList != null) {
				for (ClientRelationship rel : relList) {
					relTableModel.addRow(new Object[]{
						rel.getRelativeName(), rel.getRelationship(), String.valueOf(rel.getRelativeAge()),
						rel.getWorkingStatus().equals("Y") ? "Yes" : "No", rel.getOccupation(), String.valueOf(rel.getIncome())
					});
				}
			}
		}
	}

	private void saveClientRecord() {

		if (txtName.getText().trim().isEmpty() || txtAddress.getText().trim().isEmpty() || 
			txtBirthDate.getText().trim().isEmpty() || txtBirthPlace.getText().trim().isEmpty() || 
			txtEthnicity.getText().trim().isEmpty() || txtLanguage.getText().trim().isEmpty() || 
			txtEducation.getText().trim().isEmpty() || txtScAssoc.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill out all fields marked with an asterisk (*).", "Missing Requirements", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (txtName.getText().trim().length() > 50) {
	        JOptionPane.showMessageDialog(this, "Name cannot exceed 50 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtAddress.getText().trim().length() > 50) {
	        JOptionPane.showMessageDialog(this, "Address cannot exceed 50 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtBirthPlace.getText().trim().length() > 50) {
	        JOptionPane.showMessageDialog(this, "Birth Place cannot exceed 50 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtContact.getText().trim().length() > 15) {
	        JOptionPane.showMessageDialog(this, "Contact Number cannot exceed 15 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtEmail.getText().trim().length() > 50) {
	        JOptionPane.showMessageDialog(this, "Email cannot exceed 50 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtReligion.getText().trim().length() > 15) {
	        JOptionPane.showMessageDialog(this, "Religion cannot exceed 15 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtEthnicity.getText().trim().length() > 15) {
	        JOptionPane.showMessageDialog(this, "Ethnicity cannot exceed 15 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtLanguage.getText().trim().length() > 15) {
	        JOptionPane.showMessageDialog(this, "Language Spoken cannot exceed 15 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtScAssoc.getText().trim().length() > 15) {
	        JOptionPane.showMessageDialog(this, "SC Assoc / OSCA ID cannot exceed 15 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtGsis.getText().trim().length() > 18) {
	        JOptionPane.showMessageDialog(this, "GSIS/SSS Number cannot exceed 18 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtTin.getText().trim().length() > 17) {
	        JOptionPane.showMessageDialog(this, "TIN Number cannot exceed 17 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtPhilHealth.getText().trim().length() > 14) {
	        JOptionPane.showMessageDialog(this, "PhilHealth Number cannot exceed 14 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtOtherGov.getText().trim().length() > 30) {
	        JOptionPane.showMessageDialog(this, "Other Gov ID cannot exceed 30 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtJob.getText().trim().length() > 20) {
	        JOptionPane.showMessageDialog(this, "Job cannot exceed 20 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtPension.getText().trim().length() > 10) {
	        JOptionPane.showMessageDialog(this, "Pension cannot exceed 10 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtEducation.getText().trim().length() > 20) {
	        JOptionPane.showMessageDialog(this, "Education cannot exceed 20 characters.", "Length Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
		try {
			ClientInfo newClient = new ClientInfo();
			ClientInfoDAO dao = new ClientInfoDAO(); 

			if (isEditMode) {
				newClient.setReferenceCode(currentRefCode);
			}

			newClient.setName(txtName.getText().trim());
			newClient.setAddress(txtAddress.getText().trim());
			
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

			newClient.setContactNumber(getVal(txtContact));
			newClient.setEmailAddress(getVal(txtEmail));
			newClient.setReligion(getVal(txtReligion));
			newClient.setEthnicity(txtEthnicity.getText().trim());
			newClient.setLanguageSpoken(txtLanguage.getText().trim());
			
			String sharedId = txtScAssoc.getText().trim();
			newClient.setScAssociationId(sharedId);
			newClient.setOscaIdNum(sharedId);
			
			newClient.setGsisSssNumber(getVal(txtGsis));
			newClient.setTinNum(getVal(txtTin));
			newClient.setPhilhealthNum(getVal(txtPhilHealth));
			newClient.setOtherGovId(getVal(txtOtherGov));
			newClient.setJob(getVal(txtJob));
			newClient.setHighestEducationalAttainment(txtEducation.getText().trim());

			String pensionText = txtPension.getText().trim();
			newClient.setCurrentPension(pensionText.isEmpty() ? "0" : pensionText);

			boolean success = isEditMode ? dao.update(newClient) : dao.insert(newClient);

			if (success) {
				String savedRefCode = newClient.getReferenceCode();
				ClientHrProfileDAO hrDao = new ClientHrProfileDAO();
				ClientRelationshipDAO relDao = new ClientRelationshipDAO();
				
				if (isEditMode) {
					hrDao.deleteByReferenceCode(savedRefCode);
					relDao.deleteByReferenceCode(savedRefCode);
				}
				
				String skills = txtTechSkills.getText().trim();
				String service = txtCommunityService.getText().trim();
				if (!skills.isEmpty() || !service.isEmpty()) {
					ClientHrProfile hrProfile = new ClientHrProfile();
					hrProfile.setReferenceCode(savedRefCode);
					hrProfile.setTechnicalSkills(skills);
					hrProfile.setCommunityService(service);
					hrDao.insert(hrProfile);
				}
				
				for (int i = 0; i < relTableModel.getRowCount(); i++) {
					ClientRelationship relative = new ClientRelationship();
					relative.setReferenceCode(savedRefCode);
					relative.setRelativeName(relTableModel.getValueAt(i, 0).toString());
					relative.setRelationship(relTableModel.getValueAt(i, 1).toString());
					
					try { relative.setRelativeAge(Integer.parseInt(relTableModel.getValueAt(i, 2).toString())); } 
					catch (NumberFormatException e) { relative.setRelativeAge(0); }
					
					relative.setWorkingStatus(relTableModel.getValueAt(i, 3).toString().substring(0, 1)); 
					relative.setOccupation(relTableModel.getValueAt(i, 4).toString());
					
					try { relative.setIncome(Long.parseLong(relTableModel.getValueAt(i, 5).toString())); } 
					catch (NumberFormatException e) { relative.setIncome(0L); }
					
					relDao.insert(relative);
				}

				String msg;
				if (isEditMode) {
					msg = "Record successfully updated!\nReference Code: " + savedRefCode;
				} else {
					msg = "New client successfully registered!\nReference Code: " + savedRefCode + "\n\nPlease write this down or provide it to the client.";
				}
				
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
	
	private String getVal(JTextField field) {
		String text = field.getText().trim();
		return text.isEmpty() ? null : text;
	}
}