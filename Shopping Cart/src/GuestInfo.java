import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class GuestInfo extends JFrame {

	private JPanel contentPane;
	private JTextField stAddressTextField;
	private JTextField zipCodeTextField;
	private JTextField ccTextField;


	/**
	 * Create the frame.
	 */
	public GuestInfo(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStreetAddress = new JLabel("Street Address:");
		lblStreetAddress.setBounds(57, 97, 89, 14);
		contentPane.add(lblStreetAddress);
		
		stAddressTextField = new JTextField();
		stAddressTextField.setBounds(156, 94, 128, 20);
		contentPane.add(stAddressTextField);
		stAddressTextField.setColumns(10);
		
		JLabel labelZipCode = new JLabel("Zipcode:");
		labelZipCode.setBounds(89, 128, 54, 14);
		contentPane.add(labelZipCode);
		
		zipCodeTextField = new JTextField();
		zipCodeTextField.setColumns(10);
		zipCodeTextField.setBounds(156, 125, 128, 20);
		contentPane.add(zipCodeTextField);
		
		JLabel lblCreditcard = new JLabel("Credit Card :");
		lblCreditcard.setBounds(71, 163, 75, 14);
		contentPane.add(lblCreditcard);
		
		ccTextField = new JTextField();
		ccTextField.setColumns(10);
		ccTextField.setBounds(156, 160, 128, 20);
		contentPane.add(ccTextField);
		

		
		JLabel lblNewLabel = new JLabel("Guest Purchase");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(156, 30, 128, 20);
		contentPane.add(lblNewLabel);
		
		JLabel ccErrorLabel = new JLabel("*Please Enter a Valid Number");
		ccErrorLabel.setForeground(Color.RED);
		ccErrorLabel.setBounds(156, 145, 178, 14);
		contentPane.add(ccErrorLabel);
		ccErrorLabel.setVisible(false);
		
		JLabel lblxxxxxxxxxxxxxxxx = new JLabel("(XXXX-XXXX-XXXX-XXXX)");
		lblxxxxxxxxxxxxxxxx.setBounds(156, 182, 156, 14);
		contentPane.add(lblxxxxxxxxxxxxxxxx);
		
		JLabel lblpleaseEnterValid = new JLabel("*Please Enter Valid ZipCode");
		lblpleaseEnterValid.setForeground(Color.RED);
		lblpleaseEnterValid.setBounds(155, 112, 157, 14);
		contentPane.add(lblpleaseEnterValid);
		lblpleaseEnterValid.setVisible(false);
		
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setBounds(335, 227, 89, 23);
		contentPane.add(btnPurchase);
		
		JLabel addressErrorLbl = new JLabel("*Please Enter an Address");
		addressErrorLbl.setForeground(Color.RED);
		addressErrorLbl.setBounds(156, 78, 156, 14);
		contentPane.add(addressErrorLbl);
		addressErrorLbl.setVisible(false);
		
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean errorExists;
				if(stAddressTextField.getText().length() == 0) {
					addressErrorLbl.setVisible(true);
				}
				else {
					addressErrorLbl.setVisible(false);
				}
				String s1 = ccTextField.getText().replaceAll("-", "");
				if (s1.length() != 16) {
					ccErrorLabel.setVisible(true);

				}
				else{
					boolean allDigits = true;
					for(int i = 0; i < s1.length(); i++) {
						if(s1.charAt(i) - 48 > 9) {
							allDigits = false;
							break;
						}
					}
					ccErrorLabel.setVisible(!allDigits);
				}
			

				if(zipCodeTextField.getText().length() == 5) {
					boolean allDigits = true;
					for(int i = 0; i < 5; i++) {
						if(zipCodeTextField.getText().charAt(i) - 48 > 9) {
							allDigits = false;
							break;
						}
					}
					lblpleaseEnterValid.setVisible(!allDigits);
					
				}
				else{
					lblpleaseEnterValid.setVisible(true);
				}
				if(!lblpleaseEnterValid.isVisible() && !ccErrorLabel.isVisible() && !addressErrorLbl.isVisible()){
					purchaseConfirmation p = new purchaseConfirmation(user);
					p.setVisible(true);
					dispose();
					
				}

			}
		});
	}

}
