import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class DescriptionWindow extends JFrame {
public static Item item;
	private JPanel contentPane;
	private JTextField quantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DescriptionWindow frame = new DescriptionWindow(item, new User());
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

	public DescriptionWindow(Item item, User currentUser) {
		this.item = item;
		System.out.println(item.filePath);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setBounds(0,0,screenSize.width/3, screenSize.height/3);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDescription = (item.discription);
		lblDescription.setBounds(162, 28, 288, 29);
		
		JLabel lblPrice =  (item.price);
		lblPrice.setBounds(162, 63, 108, 15);
		
		quantity = new JTextField();
		quantity.setBounds(17, 96, 114, 19);
		quantity.setColumns(10);
		JLabel lblNewLabel = new JLabel("");
		System.out.println(item.filePath + "d");
		lblNewLabel.setIcon(item.icon);
		lblNewLabel.setBounds(12, 12, 130, 77);		
		contentPane.add(lblNewLabel);
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(149, 96, 61, 25);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantityValue =  Integer.parseInt(quantity.getText());
				item.amount += quantityValue; /* add amount typed to quantity */
				ShoppingCart shop = new ShoppingCart(item,currentUser);
				shop.setVisible(true);
				/** you should allow just an item into the shopping cart then 
				 * have a array list speficilly for shopping cart and add 
				 * item to the list right when shopping cart opens then 
				 * display the contents of the cart.
				 */
				// go to shopping cart
				System.out.println(item.amount);
				dispose();
			}
		});
		JButton btnReturnToStore = new JButton("return to Store");
		btnReturnToStore.setBounds(17, 139, 141, 25);
		btnReturnToStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShopWindow(currentUser,null).setVisible(true);
				
				dispose();
			}
		});
		contentPane.setLayout(null);
		
		contentPane.add(lblDescription);
		contentPane.add(lblPrice);
		contentPane.add(quantity);
		contentPane.add(btnAdd);
		contentPane.add(btnReturnToStore);
		
		
	}
}
