package gui;

import dao.ClientInfoDAO;
import model.ClientInfo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		setTitle("NCSC - Public Portal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Shuts down app if closed
		setBounds(100, 100, 500, 350);
		setLocationRelativeTo(null); 
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// --- TOP RIGHT: ADMIN LOGIN BUTTON ---
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.setBounds(350, 10, 120, 25);
		btnAdminLogin.setFocusPainted(false);
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the LoginFrame and pass this main menu to it
				LoginFrame login = new LoginFrame(MainMenuFrame.this);
				login.setVisible(true);
			}
		});
		contentPane.add(btnAdminLogin);
		
		// --- TITLE & SUBTITLE ---
		JLabel lblTitle = new JLabel("Senior Citizen Portal");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setBounds(10, 50, 464, 30);
		contentPane.add(lblTitle);
		
		JLabel lblSubtitle = new JLabel("What would you like to do today?");
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubtitle.setBounds(10, 90, 464, 20);
		contentPane.add(lblSubtitle);
		
		// --- CENTER BUTTONS ---
		JButton btnRegister = new JButton("Register / New Application");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Registration Form coming soon!");
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBounds(100, 140, 280, 40);
		contentPane.add(btnRegister);
		
		JButton btnSearch = new JButton("Check Existing Record");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String refCode = JOptionPane.showInputDialog(null, "Enter your Reference Code (e.g., REF-2026-001):", "Search Record", JOptionPane.QUESTION_MESSAGE);
				
				if (refCode != null && !refCode.trim().isEmpty()) {
					// Check if the record actually exists first
					ClientInfoDAO dao = new ClientInfoDAO();
					ClientInfo client = dao.getByReferenceCode(refCode.trim());
					
					if (client != null) {
						// Open the new full-screen viewer
						ViewRecordFrame viewWindow = new ViewRecordFrame(refCode.trim());
						viewWindow.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "No record found with that Reference Code.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(100, 200, 280, 40);
		contentPane.add(btnSearch);
	}
}