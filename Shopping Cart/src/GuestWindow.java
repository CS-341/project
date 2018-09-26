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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.Choice;

public class GuestWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestWindow frame = new GuestWindow();
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
	public GuestWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelomeUest = new JLabel("Welome Guest!");
		lblWelomeUest.setBounds(5, 0, 108, 15);
		contentPane.add(lblWelomeUest);
		
		JButton btnRegsiter = new JButton("Regsiter");
		btnRegsiter.setBounds(552, 14, 94, 25);
		contentPane.add(btnRegsiter);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(5, 22, 39, 15);
		contentPane.add(lblItems);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(142, 22, 49, 15);
		contentPane.add(lblSearch);
		
		textField = new JTextField();
		textField.setBounds(218, 20, 229, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 0, 0);
		contentPane.add(label);
		
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
		ArrayList<String> catList = new ArrayList<>();
		catList.add("phone");
		catList.add("resistor");
		catList.add("laptop");
		
		JList list = new JList(catList.toArray());	
		// choose multiple options
		
		list.setBounds(0, 39, 148, 128);
		contentPane.add(list);
		
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		List selectedVals = list.getSelectedValuesList();		
		JButton btnFind = new JButton("Find");		
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				List values = list.getSelectedValuesList();
				int size = values.size();
				System.out.println(size);
			}
		});
		btnFind.setBounds(22, 171, 117, 25);
		contentPane.add(btnFind);
		
		
	}
}
