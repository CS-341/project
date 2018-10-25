import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ShoppingCart extends JFrame {
	private JPanel contentPane;
	public ArrayList<Item> items;
	public JPanel panel = new JPanel();
	public JScrollPane pane = new JScrollPane();
	
	
	public ShoppingCart(ArrayList<Item> items, User currentUser) {
		setVisible(true);
		setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,screenSize.width, screenSize.height);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.items = items;
		showList(items, panel, pane);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(260, 5, 100, 25);
		contentPane.add(btnCheckout);
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton btnShop = new JButton("Back to Shop");
		btnShop.setBounds(100, 5, 150, 25);
		contentPane.add(btnShop);
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopWindow window = new ShopWindow(currentUser);
				dispose();
			}
		});
	}
	
	public void showList(ArrayList<Item> items1, JPanel panel1, JScrollPane pane) {
		panel1.removeAll();

		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// setBounds(100,100,screenSize.width, screenSize.height);
		panel1. setBounds(0,0,screenSize.width +100, screenSize.height-100);
		contentPane.add(panel1);
		panel1.setLayout(new GridLayout(items1.size(), 5, 0, 0));
		panel1.removeAll();
		pane.removeAll();
		ArrayList<JButton> Jarray = new ArrayList();
		ArrayList<JLabel> JlablArry = new ArrayList();
		ArrayList<JLabel> picArry = new ArrayList();
		ArrayList<JLabel> priceArry = new ArrayList();
		for (int i = 0; i < items1.size(); i++) {
			// ADD LABEL FOR ITEM
			Jarray.add(items1.get(i).select = new JButton("remove"));
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.anchor = GridBagConstraints.WEST;
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 0;
			gbc_btnNewButton.gridy = i;
			panel1.add(Jarray.get(i), gbc_btnNewButton);
			// ADD DISCRIPTION OF ITEM
			JlablArry.add(items1.get(i).discription);
			
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = i;
			panel1.add(JlablArry.get(i), gbc_lblNewLabel_1);
			// add price of item
			priceArry.add(items1.get(i).price);			
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 2;
			gbc_lblNewLabel_2.gridy = i;
			panel1.add(priceArry.get(i), gbc_lblNewLabel_2);
			// add picture of item
			picArry.add(new JLabel(""));
			picArry.get(i).setIcon((items1.get(i).icon));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 3;
			gbc_lblNewLabel.gridy = i;
			panel1.add(picArry.get(i), gbc_lblNewLabel);

		}

		pane = new JScrollPane(panel1);
		pane.setLocation(200, 0);
		pane. setBounds(100,40,screenSize.width -200, screenSize.height -100);
		contentPane.add(pane);
		panel1.revalidate();
		panel1.repaint();
		pane.setVisible(true);

	}

	// gets string representatin of keystroke
	public String getStringRepresentation(ArrayList<Character> list) {

		StringBuilder builder = new StringBuilder(list.size());
		for (Character ch : list) {
			builder.append(ch);
		}
		return builder.toString();
	}
}

