import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class AddItemWindow extends JFrame {
	
	private JPanel contentPane;
	private JTextField textFieldDescription;
	public  ArrayList<Item> items;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItemWindow frame = new AddItemWindow(new User(),null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param items 
	 */
	public AddItemWindow(User user, ArrayList<Item> items) {
		this.items = items;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(15, 17, 124, 15);
		contentPane.add(textFieldDescription);
		textFieldDescription.setColumns(10);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(145, 17, 100, 15);
		contentPane.add(lblDescription);

		JTextField textFieldPrice = new JTextField();
		textFieldPrice.setBounds(15, 45, 124, 15);
		contentPane.add(textFieldPrice);
		textFieldPrice.setColumns(10);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(145, 45, 100, 15);
		contentPane.add(lblPrice);
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(15, 70, 100, 25);
		contentPane.add(btnAddItem);
	
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldDescription.getText().length() > 0 && textFieldPrice.getText().length() > 0) {
					
					if (textFieldDescription.getText().equals("hp mouse")) {
						Item item = new Item(textFieldDescription.getText(), textFieldPrice.getText(),
								"/images/hp-mouse_60x60.jpeg");
						items.add(item);
						
					} else if (textFieldDescription.getText().equals("ipad")) {
						Item item = new Item(textFieldDescription.getText(), textFieldPrice.getText(),
								"/images/ipad_60x60.jpeg");
						items.add(item);
					} else {
						Item item = new Item(textFieldDescription.getText(), textFieldPrice.getText(),
								"/images/default.jpeg");
						items.add(item);
					}
					//ShopWindow.items = (ArrayList<Item>) itemss.clone();
					new ShopWindow(user, items).setVisible(true);
					dispose();
				}
			}
		});
		
	}
	
	
}
