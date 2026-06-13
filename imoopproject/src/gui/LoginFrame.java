package gui;

import dao.AdminDAO;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JFrame parentMenu; // Stores the Main Menu so we can close it later

	/**
	 * Constructor accepts the parent frame so it can be closed upon successful login.
	 */
	public LoginFrame(JFrame parentMenu) {
		this.parentMenu = parentMenu;
		
		setTitle("Admin Verification");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only closes this small window
		setBounds(100, 100, 350, 250);
		setLocationRelativeTo(parentMenu); // Centers the popup directly over the main menu
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Authorized Personnel Only");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(10, 20, 314, 20);
		contentPane.add(lblTitle);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(40, 70, 80, 14);
		contentPane.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(120, 67, 160, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(40, 110, 80, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 107, 160, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = usernameField.getText();
				String pass = new String(passwordField.getPassword());
				
				AdminDAO adminDao = new AdminDAO();
				
				if(adminDao.validateLogin(user, pass)) {
					// 1. Open the main dashboard
					DatabaseManagementFrame dbWindow = new DatabaseManagementFrame();
					dbWindow.setVisible(true);
					
					// 2. Close this login window
					dispose(); 
					
					// 3. Close the public main menu
					if (parentMenu != null) {
						parentMenu.dispose(); 
					}
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(120, 150, 160, 30);
		contentPane.add(btnLogin);
	}
}