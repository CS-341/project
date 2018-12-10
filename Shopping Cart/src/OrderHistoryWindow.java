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
	public JPanel bg = new JPanel();
	public JScrollPane scroller = new JScrollPane();
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
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(100, 100, screenSize.width-100, screenSize.height - 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Order History");
		title.setBounds(screenSize.width/2 -85, 10, 85, 16);
		contentPane.add(title);
		
		JLabel lblDatePurchased = new JLabel("Date Purchased");
		lblDatePurchased.setBounds(0, 28, getWidth()/3, 16);
		lblDatePurchased.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDatePurchased);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(getWidth()/3, 28, getWidth()/3, 16);
		lblItems.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblItems);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds((getWidth()/3)*2, 28, getWidth()/3, 16);
		lblCost.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCost);
		
		ArrayList<OrderHistory> orders = new ArrayList<>();
		//fill orders with orders from database
		JdbcSQLiteConnection db = new JdbcSQLiteConnection();
		orders = db.getOrderHistory("admin");
		
		//show order history on screen
		showHistory(orders);
		db.closeConnection();
	}
	
	private void showHistory(ArrayList<OrderHistory> orders) {
		bg.removeAll();
		bg = new JPanel();
		
		//temporarily store these in a list
		ArrayList<JLabel> datePurchased = new ArrayList<>();
		ArrayList<JLabel> itemsPurchased = new ArrayList<>();
		ArrayList<JLabel> cost = new ArrayList<>();
		
		bg.setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		System.out.println(getWidth() + " " + getHeight());
		
		bg.setBounds(50, 50, screenSize.width-100, screenSize.height-100);
		GridLayout layout = new GridLayout(0,3,0,0);
		System.out.println("numCols: " + layout.getColumns());
		System.out.println("numRows: " + layout.getRows());
		contentPane.add(bg);
		bg.setLayout(layout);
		bg.removeAll();
		scroller.removeAll();
		//temps for the new costa dn labels being added
		JLabel date = new JLabel();
		date.setSize(bg.getWidth()/3-5, bg.getHeight()/(orders.size()-5));
		JLabel items = new JLabel();
		items.setSize(bg.getWidth()/3-5, bg.getHeight()/(orders.size()-5));
		JLabel totalCost = new JLabel();
		totalCost.setSize(bg.getWidth()/3-5, bg.getHeight()/orders.size()-5);
		System.out.println("BG" + (bg.getWidth()) + " " + (bg.getHeight()));
		
		for(int i = 0; i < orders.size(); i++) {
			System.out.println(orders.get(i).items + " i:" + i);
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
		scroller.setBounds(50, 50, screenSize.width - 250, screenSize.height - 150);
		
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroller);
		//contentPane.repaint();
		bg.revalidate();
		bg.repaint();
		bg.setVisible(true);
		scroller.setVisible(true);
	}
}
