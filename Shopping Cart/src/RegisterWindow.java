import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class RegisterWindow extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField password;
	private JPasswordField retypePw;
	private JTextField streetAddress;
	private JTextField city;
	private JTextField state;
	private JTextField zipCode;
	private JTextField creditCard;
	private JLabel lblRetypePassword;
	private JLabel lblShippingAddress;
	private JLabel lblCreditCardNumber;
	private JLabel lblPasswordsDo;
	private JLabel lblPleaseEnter_2;
	private JLabel lblPleaseEnter_6;
	private JLabel lblPleaseOnly;
	private JLabel lblXxxxxxxxxxxxxxxx;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow frame = new RegisterWindow();
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
	public RegisterWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		userName = new JTextField();
		userName.setBounds(160, 20, 190, 19);
		contentPane.add(userName);
		userName.setColumns(10);

		password = new JPasswordField();
		password.setColumns(10);
		password.setBounds(160, 70, 190, 19);
		contentPane.add(password);

		retypePw = new JPasswordField();
		retypePw.setColumns(10);
		retypePw.setBounds(160, 120, 190, 19);
		contentPane.add(retypePw);

		streetAddress = new JTextField();
		streetAddress.setColumns(10);
		streetAddress.setBounds(160, 170, 190, 19);
		contentPane.add(streetAddress);

		city = new JTextField();
		city.setColumns(10);
		city.setBounds(160, 370, 190, 19);
		contentPane.add(city);

		state = new JTextField();
		state.setBounds(160, 220, 190, 19);
		contentPane.add(state);
		state.setColumns(10);
		
		zipCode = new JTextField();
		zipCode.setBounds(160, 270, 190, 19);
		contentPane.add(zipCode);
		zipCode.setColumns(10);
		
		creditCard = new JTextField();
		creditCard.setBounds(160, 324, 190, 19);
		contentPane.add(creditCard);
		creditCard.setColumns(10);
		
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setBounds(12, 22, 160, 15);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 72, 149, 15);
		contentPane.add(lblPassword);

		lblRetypePassword = new JLabel("Re-type Password:");
		lblRetypePassword.setBounds(12, 122, 160, 15);
		contentPane.add(lblRetypePassword);

		lblShippingAddress = new JLabel("Street Address:");
		lblShippingAddress.setBounds(12, 172, 149, 15);
		contentPane.add(lblShippingAddress);

		lblCreditCardNumber = new JLabel("Credit Card Number:");
		lblCreditCardNumber.setBounds(12, 370, 154, 15);
		contentPane.add(lblCreditCardNumber);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 433, 114, 25);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login cancelled = new Login();
				cancelled.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblpleaseEnterA = new JLabel("* Please enter a username");
		lblpleaseEnterA.setForeground(Color.RED);
		lblpleaseEnterA.setBounds(160, 0, 190, 15);
		contentPane.add(lblpleaseEnterA);
		lblpleaseEnterA.setVisible(false);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(373, 433, 114, 25);
		contentPane.add(btnRegister);
		
		JLabel lblPleaseEnter = new JLabel("* Please enter a password");
		lblPleaseEnter.setForeground(Color.RED);
		lblPleaseEnter.setBounds(160, 51, 190, 15);
		contentPane.add(lblPleaseEnter);
		lblPleaseEnter.setVisible(false);
		
		JLabel lblPassword_1 = new JLabel("* Please retype your password");
		lblPassword_1.setForeground(Color.RED);
		lblPassword_1.setBounds(160, 101, 247, 15);
		contentPane.add(lblPassword_1);
		lblPassword_1.setVisible(false);
		
		JLabel lblPleaseEnter_1 = new JLabel("* Please enter your 16-digit number");
		lblPleaseEnter_1.setForeground(Color.RED);
		lblPleaseEnter_1.setBounds(160, 350, 339, 15);
		contentPane.add(lblPleaseEnter_1);
		lblPleaseEnter_1.setVisible(false);
		
		lblPasswordsDo = new JLabel("* Passwords do not match");
		lblPasswordsDo.setForeground(Color.RED);
		lblPasswordsDo.setBounds(160, 101, 213, 15);
		contentPane.add(lblPasswordsDo);
		lblPasswordsDo.setVisible(false);
		
		lblPleaseEnter_2 = new JLabel("* Please enter you street address");
		lblPleaseEnter_2.setForeground(Color.RED);
		lblPleaseEnter_2.setBounds(160, 151, 258, 15);
		contentPane.add(lblPleaseEnter_2);
		lblPleaseEnter_2.setVisible(false);
		
		JLabel lblPleaseEnter_3 = new JLabel("* Please enter your city");
		lblPleaseEnter_3.setForeground(Color.RED);
		lblPleaseEnter_3.setBounds(160, 201, 190, 15);
		contentPane.add(lblPleaseEnter_3);
		lblPleaseEnter_3.setVisible(false);
		
		JLabel lblPleaseEnter_4 = new JLabel("* Please enter your state");
		lblPleaseEnter_4.setForeground(Color.RED);
		lblPleaseEnter_4.setBounds(160, 251, 190, 15);
		contentPane.add(lblPleaseEnter_4);
		lblPleaseEnter_4.setVisible(false);
		
		JLabel lblPleaseEnter_5 = new JLabel("* Please enter your 5-digit zipcode");
		lblPleaseEnter_5.setForeground(Color.RED);
		lblPleaseEnter_5.setBounds(160, 301, 247, 15);
		contentPane.add(lblPleaseEnter_5);
		lblPleaseEnter_5.setVisible(false);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(12, 222, 66, 15);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(12, 272, 66, 15);
		contentPane.add(lblState);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setBounds(12, 322, 66, 15);
		contentPane.add(lblZipCode);
		
		lblPleaseEnter_6 = new JLabel("* Please enter only 5 digit integer values");
		lblPleaseEnter_6.setForeground(Color.RED);
		lblPleaseEnter_6.setBounds(160, 301, 300, 15);
		contentPane.add(lblPleaseEnter_6);
		lblPleaseEnter_6.setVisible(false);
		
		lblPleaseOnly = new JLabel("* Please enter only integer values and '-'");
		lblPleaseOnly.setForeground(Color.RED);
		lblPleaseOnly.setBounds(160, 350, 327, 15);
		contentPane.add(lblPleaseOnly);
		
		lblXxxxxxxxxxxxxxxx = new JLabel("(XXXX-XXXX-XXXX-XXXX)");
		lblXxxxxxxxxxxxxxxx.setBounds(160, 389, 190, 15);
		contentPane.add(lblXxxxxxxxxxxxxxxx);
		lblPleaseOnly.setVisible(false);
		
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean errorExists = false;
//				if(User name is already in database) {
					JdbcSQLiteConnection j = new JdbcSQLiteConnection();
					// Search the data base
					// If something is returned then throw error 'Username already exists'
//				}
				// If there is no username entered show message
				if(userName.getText().length() == 0) {
					lblpleaseEnterA.setVisible(true);
					errorExists =  true;

				}
				//else remove the message
				else {
					lblpleaseEnterA.setVisible(false);
				}
				
				
				if(password.getText().length() == 0) {
					lblPleaseEnter.setVisible(true);
					lblPassword_1.setVisible(false);
					lblPasswordsDo.setVisible(false);
					errorExists = true;
					
				}
				//******************* STILL NEED TO MAKE IT SO ONLY INTEGER VALUES SHOW UP IN CREDIT CARD
				else if(retypePw.getText().length()==0) {
					lblPassword_1.setVisible(true);
					lblPleaseEnter.setVisible(false);
					lblPasswordsDo.setVisible(false);
					errorExists = true;
				}
				else if (!password.getText().equals(retypePw.getText())) {
					lblPasswordsDo.setVisible(true);	
					lblPassword_1.setVisible(false);
					lblPleaseEnter.setVisible(false);
					errorExists = true;
				}
				else {
					lblPasswordsDo.setVisible(false);	
					lblPassword_1.setVisible(false);
					lblPleaseEnter.setVisible(false);
				}
				if(lblShippingAddress.getText().length() == 0) {
					lblPleaseEnter_2.setVisible(true);
					errorExists = true;
				}
				else {
					lblPleaseEnter_2.setVisible(false);
				}
				String s1 = city.getText().replaceAll("-", "");
				if (s1.length() != 16) {
					lblPleaseEnter_1.setVisible(true);
					errorExists = true;
				}
				else {
					boolean allDigits = true;
					for(int i = 0; i < s1.length(); i++) {
						if(s1.charAt(i) - 48 > 9) {
							allDigits = false;
							errorExists = true;
							break;
						}
					}
					lblPleaseOnly.setVisible(!allDigits);
					lblPleaseEnter_1.setVisible(false);
					if(!allDigits) {
						errorExists = true;
					}
				}
				if(streetAddress.getText().length() == 0) {
					lblPleaseEnter_2.setVisible(true);
					errorExists = true;
				}
				else {
					lblPleaseEnter_2.setVisible(false);
				}
				if(state.getText().length() == 0) {
					lblPleaseEnter_3.setVisible(true);
					errorExists = true;
				}
				else {
					lblPleaseEnter_3.setVisible(false);
				}
				if(zipCode.getText().length() == 0) {
					lblPleaseEnter_4.setVisible(true);
					errorExists = true;
				}
				else {
					lblPleaseEnter_4.setVisible(false);
				}
				if(creditCard.getText().length() == 5) {
					boolean allDigits = true;
					for(int i = 0; i < 5; i++) {
						if(creditCard.getText().charAt(i) - 48 > 9) {
							allDigits = false;
							errorExists = true;
							break;
						}
					}
					lblPleaseEnter_5.setVisible(false);
					lblPleaseEnter_6.setVisible(!allDigits);
				}
				else{
					lblPleaseEnter_6.setVisible(false);
					errorExists = true;
				}

				if (!errorExists) {
					//add user to data base!!!!!!!!!!!!!!!
					JdbcSQLiteConnection dataBase = new JdbcSQLiteConnection();
					User newUser = new User(userName.getText(), password.getText(), city.getText(), state.getText(), zipCode.getText(), creditCard.getText());
					//dataBase.addUserToDatabase(newUser);
					ShopWindow user = new ShopWindow(newUser);
					user.setVisible(true);
					dispose();
				}


			}
		});
	}
}
