package gui;

import dao.ClientInfoDAO;
import model.ClientInfo;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MainMenuFrame() {
		setTitle("NCSC Application Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 500, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// --- TOP RIGHT: ADMIN LOGIN BUTTON ---
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.setBounds(350, 10, 120, 25);
		btnAdminLogin.setFocusPainted(false);
		// LAMBDA: Opens the login frame directly
		btnAdminLogin.addActionListener(e -> new LoginFrame(MainMenuFrame.this).setVisible(true));
		contentPane.add(btnAdminLogin);
		
		// --- TITLE & SUBTITLE ---
		JLabel lblTitle = new JLabel("Senior Citizen Portal", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setBounds(10, 50, 464, 30);
		contentPane.add(lblTitle);
		
		JLabel lblSubtitle = new JLabel("What would you like to do today?", SwingConstants.CENTER);
		lblSubtitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubtitle.setBounds(10, 90, 464, 20);
		contentPane.add(lblSubtitle);
		
		// --- CENTER BUTTONS ---
		JButton btnRegister = new JButton("Register / New Application");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBounds(100, 140, 280, 40);
		// LAMBDA: Opens registration frame
		btnRegister.addActionListener(e -> new RegistrationFrame().setVisible(true));
		contentPane.add(btnRegister);
		
		JButton btnSearch = new JButton("Check Existing Record");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(100, 200, 280, 40);
		btnSearch.addActionListener(e -> {
			String refCode = JOptionPane.showInputDialog(null, "Enter your Reference Code (e.g., REF-2026-001):", "Search Record", JOptionPane.QUESTION_MESSAGE);
			if (refCode != null && !refCode.trim().isEmpty()) {
				ClientInfo client = new ClientInfoDAO().getByReferenceCode(refCode.trim());
				if (client != null) {
					new ViewRecordFrame(refCode.trim()).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "No record found with that Reference Code.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnSearch);
	}
}