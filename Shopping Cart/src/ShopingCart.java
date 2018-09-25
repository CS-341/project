import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class ShopingCart extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopingCart frame = new ShopingCart();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopingCart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		// username LABEL
		JLabel lblUserName = new JLabel("User Name");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 2;
		gbc_lblUserName.gridy = 2;
		contentPane.add(lblUserName, gbc_lblUserName);
		// username field
		txtUsername = new JTextField();
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 4;
		gbc_txtUsername.gridy = 2;
		contentPane.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		// password LABEL
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 3;
		contentPane.add(lblPassword, gbc_lblPassword);
		// password field
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 4;
		gbc_passwordField.gridy = 3;
		contentPane.add(passwordField, gbc_passwordField);
		// Log in button
		JButton bbtLogIn = new JButton("Log In");
		GridBagConstraints gbc_bbtLogIn = new GridBagConstraints();
		gbc_bbtLogIn.insets = new Insets(0, 0, 5, 0);
		gbc_bbtLogIn.gridx = 4;
		gbc_bbtLogIn.gridy = 4;
		contentPane.add(bbtLogIn, gbc_bbtLogIn);
		// Continue as guest button
		JButton btnContinueAsGuest = new JButton("Continue as guest");
		GridBagConstraints gbc_btnContinueAsGuest = new GridBagConstraints();
		gbc_btnContinueAsGuest.insets = new Insets(0, 0, 5, 0);
		gbc_btnContinueAsGuest.gridx = 4;
		gbc_btnContinueAsGuest.gridy = 5;
		contentPane.add(btnContinueAsGuest, gbc_btnContinueAsGuest);
	}

}
