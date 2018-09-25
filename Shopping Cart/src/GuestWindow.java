import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
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

public class GuestWindow extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblWelomeUest = new JLabel("Welome Guest!");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWelomeUest, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWelomeUest, 0, SpringLayout.WEST, contentPane);
		contentPane.add(lblWelomeUest);
		
		JLabel lblItems = new JLabel("Items");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblItems, 6, SpringLayout.SOUTH, lblWelomeUest);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblItems, 0, SpringLayout.WEST, contentPane);
		contentPane.add(lblItems);
		
		JComboBox comboBox = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBox, -5, SpringLayout.NORTH, lblItems);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBox, 6, SpringLayout.EAST, lblItems);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBox, 165, SpringLayout.WEST, contentPane);
		contentPane.add(comboBox);
	
		
	}
}
