import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

public class OrderHistoryWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtOrderHistory;
	public JPanel panel = new JPanel();
	public JScrollPane scrollPane = new JScrollPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderHistoryWindow frame = new OrderHistoryWindow(null, false);
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
	public OrderHistoryWindow(User user, boolean onlyShowLastOrder) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Order History");
		title.setBounds(182, 10, 85, 16);
		contentPane.add(title);
		
		JLabel lblDatePurchased = new JLabel("Date Purchased");
		lblDatePurchased.setBounds(0, 28, 450/3, 16);
		lblDatePurchased.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDatePurchased);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(450/3, 28, 450/3, 16);
		lblItems.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblItems);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds((450/3)*2, 28, 450/3, 16);
		lblCost.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCost);
		
		ArrayList<OrderHistory> orders = new ArrayList<>();
		//fill orders with orders from database
		JdbcSQLiteConnection db = new JdbcSQLiteConnection();
		orders = db.getOrderHistory("admin");
		
		//show order history on screen
		showHistory(orders, scrollPane, panel);
		db.closeConnection();
	}
	
	private void showHistory(ArrayList<OrderHistory> orders, JScrollPane scroller, JPanel bg) {
		
		bg.removeAll();
		bg = new JPanel();
		
		//temporarily store these in a list
		ArrayList<JLabel> datePurchased = new ArrayList<>();
		ArrayList<JLabel> itemsPurchased = new ArrayList<>();
		ArrayList<JLabel> cost = new ArrayList<>();
		
		bg.setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//contentPane.getSize();
		
		bg.setBounds(0, 0, contentPane.getWidth()/4, contentPane.getHeight()/5);
		contentPane.add(bg);
		
		bg.setLayout(new GridLayout(orders.size(), 3, 1, 1));

		//temps for the new costa dn labels being added
		JLabel date = new JLabel();
		JLabel items = new JLabel();
		JLabel totalCost = new JLabel();
		
		for(int i = 0; i < orders.size(); i++) {
			System.out.println(orders.get(i).items);
			date.setText(orders.get(i).date);
			GridBagConstraints dateConstraint = new GridBagConstraints();
			dateConstraint.anchor = GridBagConstraints.WEST;
			dateConstraint.insets = new Insets(0, 0, 5, 5);
			dateConstraint.gridx = 0;
			dateConstraint.gridy = i;
			datePurchased.add(date);
			bg.add(datePurchased.get(i), dateConstraint);
			
			items.setText(orders.get(i).items);
			GridBagConstraints itemsConstraint = new GridBagConstraints();
			itemsConstraint.anchor = GridBagConstraints.CENTER;
			itemsConstraint.insets = new Insets(0, 0, 5, 5);
			itemsConstraint.gridx = 1;
			itemsConstraint.gridy = i;
			System.out.println(orders.get(i).date);
			itemsPurchased.add(items);
			bg.add(itemsPurchased.get(i), itemsConstraint);
			
			totalCost.setText(orders.get(i).cost);
			GridBagConstraints costConstraint = new GridBagConstraints();
			costConstraint.anchor = GridBagConstraints.EAST;
			costConstraint.insets = new Insets(0, 0, 5, 5);
			costConstraint.gridx = 2;
			costConstraint.gridy = i;
			cost.add(totalCost);
			bg.add(cost.get(i), costConstraint);
		}
		
		scroller = new JScrollPane(bg);
		scroller.setLocation(200, 0);
		scroller.setBounds(0, 50, screenSize.width/3, screenSize.height/4);
		contentPane.add(scroller);
		contentPane.repaint();
		bg.revalidate();
		bg.repaint();
		bg.setVisible(true);
		scroller.setVisible(true);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
}
