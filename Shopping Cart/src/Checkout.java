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

			JButton btnEnterPromo = new JButton("Enter Promo");
			btnEnterPromo.setBounds(486, 58, 128, 23);
			contentPane.add(btnEnterPromo);

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
					purchaseConfirmation guest = new purchaseConfirmation(user);
					guest.setVisible(true);
					dispose();
				}
			});
			bttUser.setForeground(Color.WHITE);
			bttUser.setBackground(new Color(47, 79, 79));
			bttUser.setBounds(435, 227, 154, 42);
			contentPane.add(bttUser);
		
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
					guest.setVisible(true);
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
