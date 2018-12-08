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
	private JLabel userNameError;
	private JLabel noPasswordError;
	private JLabel passwordRetypeError;
	private JLabel CCLengthEror;
	private JLabel passwordMatchingError;
	private JLabel streetAddressError;
	private JLabel cityError;
	private JLabel stateError;
	private JLabel zipcodeLengthError;
	private JLabel zipCodeIntegerError;
	private JLabel ccIntegerValuesError;
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
		city.setBounds(160, 220, 190, 19);
		contentPane.add(city);

		state = new JTextField();
		state.setBounds(160, 270, 190, 19);
		contentPane.add(state);
		state.setColumns(10);
		
		zipCode = new JTextField();
		zipCode.setBounds(160, 320, 190, 19);
		contentPane.add(zipCode);
		zipCode.setColumns(10);
		
		creditCard = new JTextField();
		creditCard.setBounds(160, 368, 190, 19);
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
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(12, 222, 66, 15);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(12, 272, 66, 15);
		contentPane.add(lblState);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setBounds(12, 322, 66, 15);
		contentPane.add(lblZipCode);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 433, 114, 25);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login cancelled = new Login(ShopWindow.items);
				cancelled.setVisible(true);
				dispose();
			}
		});
		
		JLabel userNameError = new JLabel("* Please enter a username");
		userNameError.setForeground(Color.RED);
		userNameError.setBounds(160, 0, 190, 15);
		contentPane.add(userNameError);
		userNameError.setVisible(false);
		
		JLabel noPasswordError = new JLabel("* Please enter a password");
		noPasswordError.setForeground(Color.RED);
		noPasswordError.setBounds(160, 51, 190, 15);
		contentPane.add(noPasswordError);
		noPasswordError.setVisible(false);
		
		JLabel passwordRetypeError = new JLabel("* Please retype your password");
		passwordRetypeError.setForeground(Color.RED);
		passwordRetypeError.setBounds(160, 101, 247, 15);
		contentPane.add(passwordRetypeError);
		passwordRetypeError.setVisible(false);
		
		JLabel CCLengthEror = new JLabel("* Please enter your 16-digit number");
		CCLengthEror.setForeground(Color.RED);
		CCLengthEror.setBounds(160, 350, 339, 15);
		contentPane.add(CCLengthEror);
		CCLengthEror.setVisible(false);
		
		passwordMatchingError = new JLabel("* Passwords do not match");
		passwordMatchingError.setForeground(Color.RED);
		passwordMatchingError.setBounds(160, 101, 213, 15);
		contentPane.add(passwordMatchingError);
		passwordMatchingError.setVisible(false);
		
		streetAddressError = new JLabel("* Please enter you street address");
		streetAddressError.setForeground(Color.RED);
		streetAddressError.setBounds(160, 151, 258, 15);
		contentPane.add(streetAddressError);
		streetAddressError.setVisible(false);
		
		JLabel cityError = new JLabel("* Please enter your city");
		cityError.setForeground(Color.RED);
		cityError.setBounds(160, 201, 190, 15);
		contentPane.add(cityError);
		cityError.setVisible(false);
		
		JLabel stateError = new JLabel("* Please enter your state");
		stateError.setForeground(Color.RED);
		stateError.setBounds(160, 251, 190, 15);
		contentPane.add(stateError);
		stateError.setVisible(false);
		
		JLabel zipcodeLengthError = new JLabel("* Please enter your 5-digit zipcode");
		zipcodeLengthError.setForeground(Color.RED);
		zipcodeLengthError.setBounds(160, 301, 247, 15);
		contentPane.add(zipcodeLengthError);
		zipcodeLengthError.setVisible(false);
		
		zipCodeIntegerError = new JLabel("* Please enter only 5 digit integer values");
		zipCodeIntegerError.setForeground(Color.RED);
		zipCodeIntegerError.setBounds(160, 301, 300, 15);
		contentPane.add(zipCodeIntegerError);
		zipCodeIntegerError.setVisible(false);
		
		ccIntegerValuesError = new JLabel("* Please enter only integer values and '-'");
		ccIntegerValuesError.setForeground(Color.RED);
		ccIntegerValuesError.setBounds(160, 350, 327, 15);
		contentPane.add(ccIntegerValuesError);
		ccIntegerValuesError.setVisible(false);
		
		JLabel userNameTakenError = new JLabel("* Username is already taken");
		userNameTakenError.setForeground(Color.RED);
		userNameTakenError.setBounds(160, 0, 247, 15);
		contentPane.add(userNameTakenError);
		userNameTakenError.setVisible(false);

		lblXxxxxxxxxxxxxxxx = new JLabel("(XXXX-XXXX-XXXX-XXXX)");
		lblXxxxxxxxxxxxxxxx.setBounds(160, 389, 190, 15);
		contentPane.add(lblXxxxxxxxxxxxxxxx);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(373, 433, 114, 25);
		contentPane.add(btnRegister);
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean errorExists = false;
				
				JdbcSQLiteConnection j = new JdbcSQLiteConnection();
				if(j.searchUserNames(userName.getText())) {
					userNameTakenError.setVisible(true);
				}
				else {
					userNameTakenError.setVisible(false);
				}
				j.closeConnection();
				// If there is no username entered show message
				if(userName.getText().length() == 0) {
					userNameError.setVisible(true);
					errorExists =  true;

				}
				//else remove the message
				else {
					userNameError.setVisible(false);
				}
				
				
				if(password.getText().length() == 0) {
					noPasswordError.setVisible(true);
					passwordRetypeError.setVisible(false);
					passwordMatchingError.setVisible(false);
					errorExists = true;
					
				}
				//******************* STILL NEED TO MAKE IT SO ONLY INTEGER VALUES SHOW UP IN CREDIT CARD
				else if(retypePw.getText().length()==0) {
					passwordRetypeError.setVisible(true);
					noPasswordError.setVisible(false);
					passwordMatchingError.setVisible(false);
					errorExists = true;
				}
				else if (!password.getText().equals(retypePw.getText())) {
					passwordMatchingError.setVisible(true);	
					passwordRetypeError.setVisible(false);
					noPasswordError.setVisible(false);
					errorExists = true;
				}
				else {
					passwordMatchingError.setVisible(false);	
					passwordRetypeError.setVisible(false);
					noPasswordError.setVisible(false);
				}
				if(streetAddress.getText().length() == 0) {
					streetAddressError.setVisible(true);
					errorExists = true;
				}
				else {
					streetAddressError.setVisible(false);
				}
				String s1 = creditCard.getText().replaceAll("-", "");
				if (s1.length() != 16) {
					CCLengthEror.setVisible(true);
					ccIntegerValuesError.setVisible(false);
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
					ccIntegerValuesError.setVisible(!allDigits);
					CCLengthEror.setVisible(false);
					if(!allDigits) {
						errorExists = true;
					}
				}
				if(state.getText().length() == 0) {
					stateError.setVisible(true);
					errorExists = true;
				}
				else {
					stateError.setVisible(false);
				}
				if(city.getText().length() == 0) {
					cityError.setVisible(true);
					errorExists = true;
				}
				else {
					cityError.setVisible(false);
				}

				if(zipCode.getText().length() == 5) {
					boolean allDigits = true;
					for(int i = 0; i < 5; i++) {
						if(zipCode.getText().charAt(i) - 48 > 9) {
							allDigits = false;
							errorExists = true;
							break;
						}
					}
					zipcodeLengthError.setVisible(false);
					zipCodeIntegerError.setVisible(!allDigits);
				}
				else{
					zipCodeIntegerError.setVisible(false);
					zipcodeLengthError.setVisible(true);
					errorExists = true;
				}

				if (!errorExists) {
					//add user to data base!!!!!!!!!!!!!!!
					JdbcSQLiteConnection dataBase = new JdbcSQLiteConnection();
					User newUser = new User(userName.getText(), password.getText(), streetAddress.getText(), city.getText(), state.getText(), zipCode.getText(), creditCard.getText());
					dataBase.addUserToDatabase(newUser);
					ShopWindow user = new ShopWindow(newUser,ShopWindow.items);
					user.setVisible(true);
					dispose();
				}


			}
		});
	}
}
