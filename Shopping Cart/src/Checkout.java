import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class Checkout extends JFrame {
	private JPanel contentPane;
	private JTextField textField;

	public Checkout(double totalValue, ArrayList<Item> items, User user) {
		getContentPane().setLayout(null);
		if (user.userType > 0) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 0, screenSize.width / 3, screenSize.height / 3);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.menu);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

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
					User.selectedItems.clear();
					purchaseConfirmation guest = new purchaseConfirmation(user);
					guest.setVisible(true);
					dispose();
				}
			});
			bttUser.setForeground(Color.WHITE);
			bttUser.setBackground(new Color(47, 79, 79));
			bttUser.setBounds(435, 227, 154, 42);
			contentPane.add(bttUser);
//			JButton btnReturnCart = new JButton("return to cart");
//			btnReturnCart.setBounds(636, 58, 300, 23);
//			contentPane.add(btnReturnCart);
//			btnReturnCart.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					new ShoppingCart(null,user).setVisible(true);
//					dispose();
//					
//				}
//				
//			});
			JButton btnEnterPromo = new JButton("Enter Promo");
			btnEnterPromo.setBounds(486, 58, 128, 23);
			//add action listener and do promotion logic
			contentPane.add(btnEnterPromo);
			btnEnterPromo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JdbcSQLiteConnection db = new JdbcSQLiteConnection();
					String enteredPromo = textField.getText();
					if (db.doesPromotionExist(enteredPromo)) { 
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
							if(User.selectedItems.get(i).name.toLowerCase().equalsIgnoreCase(promoItem)) {
								promoDiscount = (percentOffDbl) * Double.parseDouble(items.get(i).price.getText().substring(1, items.get(i).price.getText().length()));
								promoDiscount *= items.get(i).amount;
								System.out.println("fkljdslfjksdlfa");
							}
						}
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
					} else {
						//display promo code error message
					}
				}
			});
		}
		else{ /* Guest User Screen*/
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 0, screenSize.width / 3, screenSize.height / 3);
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
					GuestInfo guest = new GuestInfo(user);
					User.selectedItems.clear();
					guest.setVisible(true);
					dispose();
				}
			});
			JButton btnReturnCart = new JButton("return to cart");
			btnReturnCart.setBounds(194, 300, 300, 23);
			contentPane.add(btnReturnCart);
			btnReturnCart.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new ShoppingCart(null,user).setVisible(true);
					dispose();
					
				}
				
			});
			bttGuest.setForeground(Color.WHITE);
			bttGuest.setBackground(new Color(47, 79, 79));
			bttGuest.setBounds(435, 227, 154, 42);
			contentPane.add(bttGuest);
		}

	}
}
