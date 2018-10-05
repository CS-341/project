import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.Choice;
import javax.swing.JTextArea;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;

public class ShopWindow extends JFrame {

	private JPanel contentPane;
	public static ArrayList<Item> items;
	private JTextField txtSearch;
public	JPanel panel = new JPanel();
	public JScrollPane pane = new JScrollPane();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					int defualtType = 0;
					ShopWindow frame = new ShopWindow(defualtType);
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
	public ShopWindow(int userType) {
		items = new ArrayList();
		items.add(new Item("iPhone", "/images/rsz_1rsz_iphone-x.jpg"));
		items.add(new Item("Note 9", "/images/note-9.jpg"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));
		items.add(new Item("Laptop", "/images/laptop.png"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// Give welcome mesg if guest
		if (userType == 0) {
			JLabel lblWelomeUest = new JLabel("Welome Guest!");
			lblWelomeUest.setBounds(5, 0, 108, 15);
			contentPane.add(lblWelomeUest);

		}
		// TODO: insert user name
		else if (userType > 0) {
			JLabel lblWelomeUest = new JLabel("Welome insertUserHere!");
			lblWelomeUest.setBounds(5, 0, 108, 15);
			contentPane.add(lblWelomeUest);

		}
		contentPane.setLayout(null);

		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(5, 10, 39, 15);
		contentPane.add(lblItems);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(162, 10, 49, 15);
		contentPane.add(lblSearch);

		ArrayList<String> catList = new ArrayList<>();
		catList.add("phone");
		catList.add("resistor");
		catList.add("laptop");
		JList list = new JList(catList.toArray());
		list.setBounds(5, 35, 152, 345);
		contentPane.add(list);

		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		List selectedVals = list.getSelectedValuesList();
		JButton btnFind = new JButton("Find");
		btnFind.setBounds(5, 385, 152, 25);
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List values = list.getSelectedValuesList();
				int size = values.size();
				System.out.println(size);
			}
		});
		showList(items,panel,pane);
		txtSearch = new JTextField();
		txtSearch.setBounds(229, 10, 245, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		contentPane.add(btnFind);
		JButton btnEnter = new JButton("enter");
		ArrayList<Character> keyListen = new ArrayList<>();
		txtSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				keyListen.add(key);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = getStringRepresentation(keyListen);
				
				System.out.println(search);
				keyListen.clear();				
				ArrayList<Item> newItems = new ArrayList<>();
				for (int i = 0; i < items.size(); i++) {
					if (!items.get(i).name.contains(search)) {
						newItems.add(items.get(i));
					}
				}				
				items = newItems;
				
					panel.removeAll();				
					pane.removeAll();								
					showList(items, panel, pane);
					pane.revalidate();
					pane.repaint();
					panel.revalidate();
					panel.repaint();	
				
			}
			
		});

		btnEnter.setBounds(497, 5, 111, 25);
		contentPane.add(btnEnter);

		// TODO: sign in page
		if (userType == 0) {
			JButton btnSignIn = new JButton("Sign In");
			btnSignIn.setBounds(0, 420, 95, 25);
			btnSignIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Login signOut = new Login();
					signOut.setVisible(true);
					dispose();
				}
			});
			contentPane.add(btnSignIn);
		}

		// register if user is guest
		// TODO: insert register page
		if (userType == 0) {
			JButton button = new JButton("Regsiter");
			button.setBounds(0, 385, 95, 25);
			// action event to sign in page
			contentPane.add(button);
		}
	}

	public void showList(ArrayList<Item> items1, JPanel panel1, JScrollPane pane) {
		panel1.removeAll();
		
		 panel1 = new JPanel();
		
		panel1.setBounds(1, 134, 190, 279);
		contentPane.add(panel1);
		panel1.setLayout(new GridLayout(items1.size(), 3, 0, 0));
		panel1.removeAll();
		pane.removeAll();
		ArrayList<JButton> Jarray = new ArrayList();
		ArrayList<JLabel> JlablArry = new ArrayList();
		ArrayList<JLabel> picArry = new ArrayList();
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
			gbc_lblNewLabel_1.gridx = 2;
			gbc_lblNewLabel_1.gridy = i;
			panel1.add(JlablArry.get(i), gbc_lblNewLabel_1);

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
		pane.setBounds(162, 30, 486, 415);
		contentPane.add(pane);
		panel1.revalidate();
		panel1.repaint();
		pane.setVisible(true);

	}
	// gets string representatin of keystroke
	public String getStringRepresentation(ArrayList<Character> list) {
	    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}
}