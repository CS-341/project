import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;

public class SignInWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInWindow frame = new SignInWindow();
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
	public SignInWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("Username: ");
		lblUserName.setBounds(60, 75, 93, 15);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(60, 140, 82, 15);
		contentPane.add(lblPassword);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(140, 73, 223, 19);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		

		JLabel lblUsernameNotFound = new JLabel("*Username not found");
		lblUsernameNotFound.setForeground(Color.RED);
		lblUsernameNotFound.setBounds(140, 52, 223, 15);
		contentPane.add(lblUsernameNotFound);
		lblUsernameNotFound.setVisible(false);
		
		JLabel lblincorrectPassword = new JLabel("*Incorrect Password");
		lblincorrectPassword.setForeground(Color.RED);
		lblincorrectPassword.setBounds(140, 113, 223, 15);
		contentPane.add(lblincorrectPassword);
		lblincorrectPassword.setVisible(false);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(324, 233, 114, 25);
		contentPane.add(btnSignIn);
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JdbcSQLiteConnection db = new JdbcSQLiteConnection();
				db.openConnection();
			if(db.searchUserNames(txtUserName.getText())) {
				lblUsernameNotFound.setVisible(false);
				//call this to return a boolean to check if the passwords match
				//db.passwordsMatch(txtUserName.getText(), txtPassword.getText());
				if(db.getPassword(txtUserName.getText()).equals(txtPassword.getText())) {
					
					lblincorrectPassword.setVisible(false);
					ShopWindow sw = new ShopWindow(db.getUserInfo(txtUserName.getText()),null);
					sw.setVisible(true);
					dispose();
				}
				else {
					lblincorrectPassword.setVisible(true);
				}
			
			}
			else {
				lblUsernameNotFound.setVisible(true);
			}
		// if(password.equals(passwordReturend){
//				good	
//			}
//			else {
//				send error 'Password is incorrect'
//			}
			}});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 233, 114, 25);
		contentPane.add(btnCancel);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(140, 138, 223, 19);
		contentPane.add(txtPassword);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login cancelled = new Login();
				cancelled.setVisible(true);
				dispose();
			}
		});
		
		
	}
}
