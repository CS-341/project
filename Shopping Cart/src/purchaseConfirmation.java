import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class purchaseConfirmation extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public purchaseConfirmation(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thank you for your purchase!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(72, 91, 184, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(222, 176, 89, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.selectedItems.clear();
				ShopWindow shop = new ShopWindow(user, null);
				shop.setVisible(true);
				dispose();
				//TODO REMOVE ITEMS FROM CART!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}});
	}

}
