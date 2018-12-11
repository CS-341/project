/*
 * 
 */
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class Checkout.
 */
public class Checkout extends JFrame {
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The text field. */
	private JTextField textField;

	/**
	 * Instantiates a new checkout.
	 *
	 * @param totalValue the total value
	 * @param items the items
	 * @param user the user
	 */
	public Checkout(double totalValue, ArrayList<Item> items, User user) {
		getContentPane().setLayout(null);
		//create the database
		JdbcSQLiteConnection db = new JdbcSQLiteConnection();
		if (user.userType > 0) { //user is not a guest
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 0, screenSize.width- screenSize.width/4, screenSize.height / 2);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.menu);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			//error messages
			JLabel promoDoesNotExist = new JLabel("Promotion does not exist");
			promoDoesNotExist.setForeground(Color.RED);
			promoDoesNotExist.setBounds(300, 34, 250, 26);
			promoDoesNotExist.setVisible(false);
			contentPane.add(promoDoesNotExist);
			//promotion exists, date valid, or been used already
			JLabel promoDateInvalid = new JLabel("Promotion has expired");
			promoDateInvalid.setForeground(Color.RED);
			promoDateInvalid.setBounds(300, 60, 350, 26);
			promoDateInvalid.setVisible(false);
			contentPane.add(promoDateInvalid);
			
			JLabel promoAlreadyUsed = new JLabel("Promotion cannot be applied more than once");
			promoAlreadyUsed.setForeground(Color.RED);
			promoAlreadyUsed.setBounds(300, 86, 300, 26);
			
			promoAlreadyUsed.setVisible(false);
			contentPane.add(promoAlreadyUsed);
			
			JLabel labelSubtotal = new JLabel(Double.toString(totalValue) + "$");
			labelSubtotal.setFont(new Font("Tahoma", Font.BOLD, 15));
			labelSubtotal.setBounds(274, 128, 154, 42);
			contentPane.add(labelSubtotal);

			JLabel SubtotalLabel = new JLabel("Subtotal:");
			SubtotalLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			SubtotalLabel.setBounds(194, 128, 70, 42);
			contentPane.add(SubtotalLabel);

			textField = new JTextField();
			textField.setBounds(486, 35, 128, 20);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JLabel lblenterPromoCode = new JLabel("*Enter Promo Here");
			lblenterPromoCode.setBounds(500, 21, 144, 14);
			contentPane.add(lblenterPromoCode);
			
			JLabel lblTotal = new JLabel("Total:");
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTotal.setBounds(217, 222, 70, 42);
			contentPane.add(lblTotal);

			JLabel lblPromo = new JLabel("Promo:");
			lblPromo.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblPromo.setBounds(206, 158, 58, 42);
			contentPane.add(lblPromo);

			JLabel labelPromo = new JLabel();
			labelPromo.setFont(new Font("Tahoma", Font.BOLD, 15));
			labelPromo.setBounds(274, 158, 154, 42);
			contentPane.add(labelPromo);

			JLabel lblTax = new JLabel("Tax:");
			lblTax.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTax.setBounds(227, 191, 53, 42);
			contentPane.add(lblTax);

			JLabel labelTax = new JLabel("5.6%");
			labelTax.setFont(new Font("Tahoma", Font.BOLD, 15));
			labelTax.setBounds(274, 191, 154, 42);
			contentPane.add(labelTax);

			JLabel labelTotal;
			labelTotal = new JLabel(
					Double.toString(Double.parseDouble(labelTax.getText().substring(0, labelTax.getText().length() - 1))
							/ 100 * totalValue + totalValue) + "$");

			labelTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
			labelTotal.setBounds(274, 222, 154, 42);
			contentPane.add(labelTotal);
			setVisible(true);
			
			Button bttUser = new Button("Purchase");
			bttUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//add order to order history under this user's ID or username
					String[] itemsArr = new String[User.selectedItems.size()];
					Item[] arr = new Item [user.selectedItems.size()];
					arr = user.selectedItems.toArray(arr);
					for(int k = 0; k < arr.length; k++) {
						itemsArr[k] = arr[k].name;
					}
					String quantities = storeQuantitiesAsString(User.selectedItems);
					//add order history to db
					//JdbcSQLiteConnection db = new JdbcSQLiteConnection();
					Date date = new Date();
					db.insertOrderHistory(user.userName, labelTotal.getText(), itemsArr, quantities, date.toGMTString());
					//db.closeConnection();
					
					User.selectedItems.clear();
					purchaseConfirmation guest = new purchaseConfirmation(user);
					guest.setVisible(true);
					db.closeConnection();
					dispose();
				}
			});
			bttUser.setForeground(Color.BLACK);
			bttUser.setBackground(new Color(47, 79, 79));
			bttUser.setBounds(435, 227, 154, 42);
			contentPane.add(bttUser);
			JButton btnReturnCart = new JButton("return to cart");
			btnReturnCart.setBounds(636, 58, 300, 23);
			contentPane.add(btnReturnCart);
			btnReturnCart.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new ShoppingCart(null,user).setVisible(true);
					dispose();
					
				}
				
			});
			JButton btnEnterPromo = new JButton("Enter Promo");
			btnEnterPromo.setBounds(486, 58, 128, 23);
			//add action listener and do promotion logic
			contentPane.add(btnEnterPromo);
			ArrayList<String> usedPromos = new ArrayList<String>();

			btnEnterPromo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//JdbcSQLiteConnection db = new JdbcSQLiteConnection();
					String enteredPromo = textField.getText();
					boolean promoExists = db.doesPromotionExist(enteredPromo);
					boolean promoDateValid = db.checkPromoDate(enteredPromo);
					
					promoDoesNotExist.setVisible(false);
					promoDateInvalid.setVisible(false);
					promoAlreadyUsed.setVisible(false);
					
					boolean wasUsed = false;
					boolean isValid = true;
					if (promoExists && promoDateValid) { 
						int j = 0;
						//check if the promo was already used at checkout
						while(j < usedPromos.size()) {
							if (checkIfPromoUsed(usedPromos, enteredPromo)) {
								wasUsed = true;
								isValid = false;
								promoAlreadyUsed.setVisible(true);
							}
							j++;
						}
						usedPromos.add(enteredPromo);
						//there is a valid promotion
						String percentOff = db.getPromotionType(enteredPromo);
						double percentOffDbl = 0;
						percentOffDbl = (percentOff.charAt(1) - '0') * 10;
						percentOffDbl += percentOff.charAt(2) - '0';
						percentOffDbl = percentOffDbl / 100;
						System.out.println("percent off is: " + percentOffDbl);
						//go through items and any name that matches the promoTag 
						String promoItem = db.getPromoTag(enteredPromo);
						double promoDiscount = 0;
						//calculate promotion amount
						for(int i = 0; i < items.size(); i++) {
							if(User.selectedItems.get(i).name.toLowerCase().equalsIgnoreCase(promoItem)
									&& isValid) {
								promoDiscount = (percentOffDbl) * Double.parseDouble(items.get(i).price.getText().substring(1, items.get(i).price.getText().length()));
								promoDiscount *= items.get(i).amount;
								//subtract promotion amount from total and apply it 
								String totalBeforeDiscount = labelTotal.getText();
								//String subTotalBeforeDiscount = SubtotalLabel.getText().substring(9, SubtotalLabel.getText().length());
								Double totalBeforeDiscountDbl = Double.parseDouble(totalBeforeDiscount.substring(0, totalBeforeDiscount.length()-1));
								Double totalAfterDiscount = totalBeforeDiscountDbl - promoDiscount;
								Double subTotalAfterDiscount = totalValue - promoDiscount;
								String totalAfterDiscountString = Double.toString(totalAfterDiscount);
								labelTotal.setText(totalAfterDiscountString);
								labelSubtotal.setText(Double.toString(subTotalAfterDiscount) + "$");
								contentPane.repaint();
							}
						}
					} else {//promo did not exist or promo date was invalid -- check
						System.out.println("did not find promo");
						if(!promoExists) {
							promoDoesNotExist.setVisible(true);
						} else if(!promoDateValid) {
							promoDateInvalid.setVisible(true);
						}
					}
					//db.closeConnection();
				}
			});
		}
		else{ /* Guest User Screen*/
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 0, screenSize.width - screenSize.width/4, screenSize.height / 2);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.menu);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel labelSubtotal = new JLabel(Double.toString(totalValue) + "$");
			labelSubtotal.setFont(new Font("Tahoma", Font.BOLD, 15));
			labelSubtotal.setBounds(274, 158, 154, 42);
			contentPane.add(labelSubtotal);

			JLabel SubtotalLabel = new JLabel("Subtotal:");
			SubtotalLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			SubtotalLabel.setBounds(194, 158, 154, 42);
			contentPane.add(SubtotalLabel);


			JLabel lblTotal = new JLabel("Total:");
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTotal.setBounds(217, 222, 70, 42);
			contentPane.add(lblTotal);


			JLabel lblTax = new JLabel("Tax:");
			lblTax.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTax.setBounds(227, 191, 53, 42);
			contentPane.add(lblTax);

			JLabel labelTax = new JLabel("5.6%");
			labelTax.setFont(new Font("Tahoma", Font.BOLD, 15));
			labelTax.setBounds(274, 191, 154, 42);
			contentPane.add(labelTax);

			JLabel labelTotal;
			labelTotal = new JLabel(
					Double.toString(Double.parseDouble(labelTax.getText().substring(0, labelTax.getText().length() - 1))
							/ 100 * totalValue + totalValue) + "$");

			labelTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
			labelTotal.setBounds(274, 222, 154, 42);
			contentPane.add(labelTotal);
			setVisible(true);
			
			Button bttGuest = new Button("Purchase as Guest");
			bttGuest.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//add order to order history before items cleared
					GuestInfo guest = new GuestInfo(user);
					String[] itemsArr = new String[User.selectedItems.size()];
					Item[] arr = new Item [user.selectedItems.size()];
					arr = user.selectedItems.toArray(arr);
					for(int k = 0; k < arr.length; k++) {
						itemsArr[k] = arr[k].name;
					}
					String quantities = storeQuantitiesAsString(User.selectedItems);
					//add order history to db
					//JdbcSQLiteConnection db = new JdbcSQLiteConnection();
					Date date = new Date();
					//db.insertOrderHistory(user.userName, labelTotal.getText(), itemsArr, quantities, date.toGMTString());
					//db.closeConnection();
					User.selectedItems.clear();
					guest.setVisible(true);
					dispose();
					//db.closeConnection();
				}
			});
			JButton btnReturnCart = new JButton("return to cart");
			btnReturnCart.setBounds(194, 300, 300, 23);
			contentPane.add(btnReturnCart);
			btnReturnCart.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new ShoppingCart(null,user).setVisible(true);
					db.closeConnection();
					dispose();
					
				}
				
			});
			bttGuest.setForeground(Color.BLACK);
			bttGuest.setBackground(new Color(47, 79, 79));
			bttGuest.setBounds(435, 227, 154, 42);
			contentPane.add(bttGuest);
		}

	}
	
	/**
	 * Check if promo used.
	 *
	 * @param usedPromos the used promos
	 * @param promoName the promo name
	 * @return true, if successful
	 */
	private boolean checkIfPromoUsed(ArrayList<String> usedPromos, String promoName) {
		boolean wasUsed = false;
		for(int i = 0; i < usedPromos.size(); i++) {
			if(promoName.equals(usedPromos.get(i))) {
				wasUsed = true;
			}
		}
		
		return wasUsed;
	}
	
	/**
	 * Store quantities as string.
	 *
	 * @param quantities the quantities
	 * @return the string
	 */
	private String storeQuantitiesAsString(ArrayList<Item> quantities ) {
		String quantitiesString = "";
		int tempDigit;
		char tempChar;
		for(int i = 0; i < quantities.size(); i++) {
			 tempDigit = quantities.get(i).amount;
			 tempChar = Character.forDigit(tempDigit, 10);
			 if (i < quantities.size()-1) {
				 quantitiesString += tempChar + ", ";
			 } else {
				 quantitiesString += tempChar;
			 }
			 
		}
		
		return quantitiesString;
	}
	
}
