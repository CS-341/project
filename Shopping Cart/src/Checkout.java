import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ShoppingCart extends JFrame {
	private JPanel contentPane;
	public User currentUser;
	public ArrayList<Item> items;
	public JPanel panel = new JPanel();
	public JScrollPane pane = new JScrollPane();
	private JTextField promotion;
	private Label proLabel;
	private Label cartTotal;
	private JButton promoCheck;
	public EventHandling handler;

	public ShoppingCart(Item newItem, User currentUser) {
		this.currentUser = currentUser;
		items = User.selectedItems;
		/**
		 * if (newItem != null) { addItem(newItem); }
		 **/
		setVisible(true);
		setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, screenSize.width, screenSize.height);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		showList(items, panel, pane);
		// for promotion handling
		proLabel = new Label("Enter Promotion:");
		proLabel.setBounds(500, 10, 95, 19);
		contentPane.add(proLabel);
		promotion = new JTextField();
		promotion.setBounds(600, 10, 100, 19);
		contentPane.add(promotion);
		promotion.setColumns(10);
		promoCheck = new JButton("Check");
		promoCheck.setBounds(705, 10, 30, 19);
		contentPane.add(promoCheck);
		promoCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(260, 5, 100, 25);
		contentPane.add(btnCheckout);
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Checkout checkWindow = new Checkout(getTotalPrice(items), items, currentUser); // temp int
				dispose();
			}
		});

		JButton btnShop = new JButton("Back to Shop");
		btnShop.setBounds(100, 5, 150, 25);
		contentPane.add(btnShop);
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopWindow window = new ShopWindow(currentUser, null);
				window.setVisible(true);
				dispose();
			}
		});
	}

	
	private double getTotalPrice(ArrayList<Item> itemList){
		double totalPrice = 0;
		for(int i = 0; i < itemList.size(); i++){
			String price =itemList.get(i).price.getText().substring(1);
			totalPrice += itemList.get(i).amount *  Double.parseDouble(price);
		}
		return totalPrice;
		
	}
	// add item to cart
	private void addItem(Item newItem) {
		Item temp = newItem;
		System.out.println(newItem.filePath);
		if (items.size() > 0 && items.contains(temp)) {

		} else {
			items.add(temp);
		}
		for (int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).item);
			System.out.println("here");
		}
	}

	public void showList(ArrayList<Item> items1, JPanel panel1, JScrollPane pane) {
		panel1.removeAll();

		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// setBounds(100,100,screenSize.width, screenSize.height);
		panel1.setBounds(0, 0, screenSize.width + 100, screenSize.height - 100);
		contentPane.add(panel1);
		panel1.setLayout(new GridLayout(items1.size(), 6, 0, 0));
		panel1.removeAll();
		pane.removeAll();
		ArrayList<JButton> Jarray = new ArrayList();
		ArrayList<JLabel> JlablArry = new ArrayList();
		ArrayList<JLabel> picArry = new ArrayList();
		ArrayList<JLabel> priceArry = new ArrayList();
		ArrayList<JLabel> quantityArry = new ArrayList();
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
			// add quantity of item
			quantityArry.add(new JLabel(Integer.toString(items1.get(i).amount)));
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 3;
			gbc_lblNewLabel_3.gridy = i;
			panel1.add(quantityArry.get(i), gbc_lblNewLabel_3);
			// add picture of item
			picArry.add(new JLabel(""));
			picArry.get(i).setIcon((items1.get(i).icon));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 4;
			gbc_lblNewLabel.gridy = i;
			panel1.add(picArry.get(i), gbc_lblNewLabel);

		}

		pane = new JScrollPane(panel1);
		pane.setLocation(200, 0);
		pane.setBounds(100, 40, screenSize.width - 200, screenSize.height - 100);
		contentPane.add(pane);
		panel1.revalidate();
		panel1.repaint();
		pane.setVisible(true);

		// Adding ActionListener
		handler = new EventHandling(Jarray, JlablArry, priceArry, picArry, this.currentUser, items1);

		for (JButton buttons : Jarray) {

			buttons.addActionListener(handler);
			dispose();
		}

	}

	// gets string representatin of keystroke
	public String getStringRepresentation(ArrayList<Character> list) {

		StringBuilder builder = new StringBuilder(list.size());
		for (Character ch : list) {
			builder.append(ch);
		}
		return builder.toString();
	}

	class EventHandling implements ActionListener {

		/**
		* 
		*/

		private ArrayList<JButton> selectButtons;
		//private ArrayList<Item> selectedItems;
		private ArrayList<JLabel> JlabelArry;
		private ArrayList<JLabel> priceArry;
		private ArrayList<JLabel> picArry;
		public User currentUser;
		public ArrayList<Item> item;

		public EventHandling(ArrayList<JButton> selectButtons, ArrayList<JLabel> JlabelArry,
				ArrayList<JLabel> priceArry, ArrayList<JLabel> picArry, User currentUser, ArrayList<Item> item) {

			this.currentUser = currentUser;
			this.selectButtons = selectButtons;
			//selectedItems = new ArrayList<Item>();
			this.JlabelArry = JlabelArry;
			this.priceArry = priceArry;
			this.picArry = picArry;
			this.item = item;
		}

		public void actionPerformed(ActionEvent event) {

			for (int i = 0; i < selectButtons.size(); i++)
				if (event.getSource() == selectButtons.get(i)) {
					Item temp = item.get(i);
					//System.out.println(temp.name);
					temp.icon = item.get(i).icon;
					//System.out.println(temp.filePath);
					DescriptionWindow discription = new DescriptionWindow(temp, currentUser, true);
					dispose();
					discription.setVisible(true);
					//System.out.println(temp.amount);
					break;
				}

		}
	}
}
