/*
 * 
 */
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class ShopWindow.
 */
public class ShopWindow extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6022393559091857909L;

	/** The content pane. */
	private JPanel contentPane;
	
	/** The txt search. */
	private JTextField txtSearch;
	
	/** The panel. */
	public JPanel panel = new JPanel();
	
	/** The pane. */
	public JScrollPane pane = new JScrollPane();
	
	/** The handler. */
	public EventHandling handler;
	
	/** The new user. */
	public User newUser;
	
	/** The items. */
	public static ArrayList<Item> items;
	
	/** The standard size. */
	public int standardSize = 0; /* gives initial size of list */

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ShopWindow frame = new ShopWindow(new User(), null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *
	 * @param newUser the new user
	 * @param items the items
	 */

	public ShopWindow(User newUser, ArrayList<Item> items) {
		setVisible(true);
		this.newUser = newUser;
		this.items = ShopWindow.items;
		setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, screenSize.width, screenSize.height);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		if (items == null) {
			items = new ArrayList<Item>();
			items.add(new Item("iPhone", "$1000", "/images/rsz_1rsz_iphone-x.jpg"));
			items.add(new Item("acer monitor", "$399", "/images/acer-monitor_60x60.jpeg"));
			items.add(new Item("corisair keyboard", "$99", "/images/corisair-key_60x60.jpeg"));
			items.add(new Item("hp mouse", "$15", "/images/hp-mouse_60x60.jpeg"));
			items.add(new Item("ipad", "$499", "/images/ipad_60x60.jpeg"));
			 items.add(new Item("kogan keyboard", "$59", "/images/kogan-key_60x60.jpeg"));
			 items.add(new Item("Logitech keyboard", "$99",
			 "/images/logitech-key_60x60.jpeg"));
			 items.add(new Item("Razer Mouse", "$79",
			 "/images/logitech-mouse_60x60.jpeg"));
			items.add(new Item("ram", "$29", "/images/ram_60x60.jpeg"));
			items.add(new Item("Razer Headset", "$49", "/images/razer-headset_60x60.jpeg"));
			items.add(new Item("Alexa", "$49", "/images/alexa.jpeg"));
			items.add(new Item("Battery", "$9", "/images/bat.jpeg"));
			items.add(new Item("DMM", "$5", "/images/dmm.jpeg"));
			items.add(new Item("Google Home", "$99", "/images/google-home.jpeg"));
			items.add(new Item("GPU", "$500", "/images/gpu.jpeg"));
			items.add(new Item("JBL Speaker", "$79", "/images/jbl-speaker.jpeg"));
//			items.add(new Item("PSU", "$49", "/images/psu.jpeg"));
//			items.add(new Item("TV", "$1000", "/images/tv.jpeg"));
//			items.add(new Item("Flash Drive", "$5", "/images/flashe-drive.jpeg"));
//			items.add(new Item("Ear Buds", "$10", "/images/earbuds.jpeg"));
//			items.add(new Item("laser", "$2", "/images/laser.jpeg"));
//			items.add(new Item("Macbook", "$1000", "/images/macbook.jpeg"));
//			items.add(new Item("Over Ear Headphones", "$300", "/images/overear.jpeg"));
//			items.add(new Item("Playstation 4", "$699", "/images/ps4.jpeg"));
//			items.add(new Item("Subwoofer", "$200", "/images/sub.jpeg"));
//			items.add(new Item("VR Headset", "$100", "/images/vr.jpeg"));
			// items.add(new Item("Xbox One", "$500", "/images/xbox.jpeg"));
			standardSize = items.size();
		} else {

			JButton bttnResetList = new JButton("Refresh");
			bttnResetList.setBounds(0, 30, 100, 25);
			contentPane.add(bttnResetList);
			bttnResetList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ShopWindow(newUser, null).setVisible(true);
					dispose();
				}
			});

		}

		// Give welcome mesg if guest
		if (newUser.userType == 0) {
			JLabel lblWelomeUest = new JLabel("Welome Guest!");
			lblWelomeUest.setBounds(5, 0, 108, 15);
			contentPane.add(lblWelomeUest);
		}
		// TODO: insert user name
		else if (newUser.userType > 0) {

			JLabel lblWelomeUest1 = new JLabel("Welome " + newUser.userName + "!");
			lblWelomeUest1.setBounds(5, 0, 200, 15);

			contentPane.add(lblWelomeUest1);
			JButton btnSignOut = new JButton("Sign Out");
//			btnCart.setBounds(1055, 5, 150, 25);

			btnSignOut.setBounds(1205, 5, 150, 25);
			contentPane.add(btnSignOut);
			
			btnSignOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newUser.userType = 0;
				
					Login login = new Login(ShopWindow.items);
					login.setVisible(true);
					dispose();
				}
			});
						JButton btnEditProfile= new JButton("Edit Profile");
//			btnCart.setBounds(1055, 5, 150, 25);

			btnEditProfile.setBounds(1355, 5, 150, 25);
			contentPane.add(btnEditProfile);
			
			btnEditProfile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EditProfile ep = new EditProfile(newUser);
					ep.setVisible(true);
					dispose();
				}
			});
			JButton btnViewOrderHistory = new JButton("Purchase History");
			btnViewOrderHistory.setBounds(707,5,135,25);
			contentPane.add(btnViewOrderHistory);
			
			btnViewOrderHistory.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("button pressed");
					new OrderHistoryWindow(newUser, false).setVisible(true);
					dispose();
				}
			});
		}

		contentPane.setLayout(null);

		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(5, 10, 39, 15);
		contentPane.add(lblItems);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(162, 10, 49, 15);
		//contentPane.add(lblSearch);

		ArrayList<String> catList = new ArrayList<>();
		catList.add("phone");
		catList.add("resistor");
		catList.add("laptop");
		JList list = new JList(catList.toArray());
		list.setBounds(5, 35, 62, 173);
		//contentPane.add(list);

		JButton btnCart = new JButton("Shopping Cart");
		btnCart.setBounds(1055, 5, 150, 25);
		contentPane.add(btnCart);
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User.selectedItems = handler.getSelected();
				ShoppingCart window = new ShoppingCart(null, newUser);
				window.setVisible(true);
				dispose();
			}
		});

		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		List selectedVals = list.getSelectedValuesList();
		JButton btnFind = new JButton("Find");
		btnFind.setBounds(5, 210, 64, 25);
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List values = list.getSelectedValuesList();
				int size = values.size();
				System.out.println(size);
			}
		});
		
		showList(items, panel, pane);
		txtSearch = new JTextField();
		txtSearch.setBounds(229, 10, 245, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		//contentPane.add(btnFind);
		JButton btnEnter = new JButton("Enter");

		final ArrayList<Item> tmp = (ArrayList<Item>) items.clone();
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = txtSearch.getText();
				System.out.println(search);
				ArrayList<Item> arr = new ArrayList();
				for (int i = 0; i < tmp.size(); i++) {
					if (search.equals(tmp.get(i).item)) {
						arr.add(tmp.get(i));
					}
				}
				new ShopWindow(newUser, arr).setVisible(true);
				dispose();
			}

		});
		if (newUser.userType == 2) {
			JButton btnAddItem = new JButton("Add Item");
			btnAddItem.setBounds(605, 5, 100, 25);
			contentPane.add(btnAddItem);
			final ArrayList<Item> tmp1 = items;
			btnAddItem.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					ArrayList<Item> item1 = new ArrayList<Item>();
					for(int i = 0; i < tmp1.size(); i++) {
						item1.add(tmp1.get(i));				
						
					}
					AddItemWindow itemWindow = new AddItemWindow(newUser,item1);
					itemWindow.setVisible(true);
					dispose();
				}
			});
		}
		ShopWindow.items = (ArrayList<Item>) items.clone();
		
		btnEnter.setBounds(497, 5, 100, 25);
		contentPane.add(btnEnter);

		// TODO: sign in page
		if (newUser.userType == 0) {
			JButton btnSignIn = new JButton("Sign In");
			btnSignIn.setBounds(612, 5, 100, 25);
			btnSignIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SignInWindow signIn = new SignInWindow();
					signIn.setVisible(true);
					dispose();
				}
			});
			contentPane.add(btnSignIn);
		}

		// register if user is guest
		// TODO: insert register page
		if (newUser.userType == 0) {
			JButton button = new JButton("Register");
			button.setBounds(727, 5, 100, 25);
			// action event to sign in page
			contentPane.add(button);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RegisterWindow signIn = new RegisterWindow();
					signIn.setVisible(true);
					dispose();
				}
			});
		}
		// promotion button if user is an admin
		if (newUser.userType == 2) {
			JButton promoBtn = new JButton("Add/Edit Promotion");
			promoBtn.setBounds(842, 5, 210, 25);
			promoBtn.setVisible(true);
			// action event to PromotionWindow
			contentPane.add(promoBtn);
			promoBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PromotionWindow promoWin = new PromotionWindow(newUser);
					promoWin.setVisible(true);
					dispose();
				}
			});
		}

	}

	/**
	 * Show list.
	 *
	 * @param items1 the items 1
	 * @param panel1 the panel 1
	 * @param pane the pane
	 */
	public void showList(ArrayList<Item> items1, JPanel panel1, JScrollPane pane) {
		panel1.removeAll();

		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// setBounds(100,100,screenSize.width, screenSize.height);
		panel1.setBounds(0, 0, screenSize.width + 100, screenSize.height - 100);
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
			Jarray.add(items1.get(i).select);
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
		pane.setBounds(100, 40, screenSize.width - 200, screenSize.height - 100);
		contentPane.add(pane);
		panel1.revalidate();
		panel1.repaint();
		pane.setVisible(true);

		// Adding ActionListener
		handler = new EventHandling(Jarray, JlablArry, priceArry, picArry, this.newUser, items1);

		for (JButton buttons : Jarray) {

			buttons.addActionListener(handler);
			dispose();
		}

	}

	/**
	 * The Class EventHandling.
	 */
	class EventHandling implements ActionListener {

		/** The select buttons. */

		private ArrayList<JButton> selectButtons;
		
		/** The selected items. */
		private ArrayList<Item> selectedItems;
		
		/** The Jlabel arry. */
		private ArrayList<JLabel> JlabelArry;
		
		/** The price arry. */
		private ArrayList<JLabel> priceArry;
		
		/** The pic arry. */
		private ArrayList<JLabel> picArry;
		
		/** The quantity. */
		private ArrayList<JLabel> quantity;
		
		/** The current user. */
		public User currentUser;
		
		/** The item. */
		public ArrayList<Item> item;

		/**
		 * Instantiates a new event handling.
		 *
		 * @param selectButtons the select buttons
		 * @param JlabelArry the jlabel arry
		 * @param priceArry the price arry
		 * @param picArry the pic arry
		 * @param currentUser the current user
		 * @param item the item
		 */
		public EventHandling(ArrayList<JButton> selectButtons, ArrayList<JLabel> JlabelArry,
				ArrayList<JLabel> priceArry, ArrayList<JLabel> picArry, User currentUser, ArrayList<Item> item) {

			this.currentUser = currentUser;
			this.selectButtons = selectButtons;
			selectedItems = new ArrayList<Item>();
			this.JlabelArry = JlabelArry;
			this.priceArry = priceArry;
			this.picArry = picArry;
			this.item = item;
		}

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event) {

			for (int i = 0; i < selectButtons.size(); i++)
				if (event.getSource() == selectButtons.get(i)) {
					Item temp = new Item(JlabelArry.get(i).getText(), priceArry.get(i).getText(),
							picArry.get(i).getText());
					// System.out.println(temp.name);
					temp.icon = item.get(i).icon;
					// System.out.println(temp.filePath);
					DescriptionWindow discription = new DescriptionWindow(temp, currentUser, item);
					dispose();
					discription.setVisible(true);
					break;
				}

		}
	

		/**
		 * Gets the selected.
		 *
		 * @return the selected
		 */
		public ArrayList<Item> getSelected() {
			return User.selectedItems;
		}

	}
}
