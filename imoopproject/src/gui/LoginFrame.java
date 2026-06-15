package gui;

import dao.AdminDAO;

import java.awt.Font;
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
	private JFrame parentMenu; 

	public LoginFrame(JFrame parentMenu) {
		this.parentMenu = parentMenu;
			
		setTitle("Admin Verification");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 350, 250);
		setResizable(false);
		setLocationRelativeTo(parentMenu); 
		
		contentPane = new JPanel(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Authorized Personnel Only", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(10, 20, 314, 20);
		contentPane.add(lblTitle);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(40, 70, 80, 14);
		contentPane.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(120, 67, 160, 20);
		contentPane.add(usernameField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(40, 110, 80, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 107, 160, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(120, 150, 160, 30);
		btnLogin.addActionListener(e -> {
			String user = usernameField.getText();
			String pass = new String(passwordField.getPassword());
			
			if (new AdminDAO().validateLogin(user, pass)) {
				new DatabaseManagementFrame().setVisible(true);
				dispose(); 
				if (parentMenu != null) parentMenu.dispose(); 
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		contentPane.add(btnLogin);
	}
}