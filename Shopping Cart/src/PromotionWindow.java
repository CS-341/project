import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Color;

public class PromotionWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblPromoName;
	private JLabel lblPromoType;
	private JLabel lblNewLabel;
	private JLabel lblStartDate;
	private JLabel lblEndDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromotionWindow frame = new PromotionWindow();
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
	public PromotionWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFillInAll = new JLabel("Fill in all fields to add a valid promotion");
		lblFillInAll.setForeground(Color.WHITE);
		lblFillInAll.setBounds(92, 6, 266, 16);
		contentPane.add(lblFillInAll);
		//promoType
		textField = new JTextField();
		textField.setToolTipText("20% off = \"%20\", $5 off = \"$5\"");
		textField.setBounds(160, 72, 130, 26);
		contentPane.add(textField);

		//promoTag
		textField_1 = new JTextField();
		textField_1.setToolTipText("Enter the name of the item the promotion applies to");
		textField_1.setBounds(160, 110, 130, 26);
		contentPane.add(textField_1);

		//startDate
		textField_2 = new JTextField();
		textField_2.setToolTipText("dd/mm/yyyy");
		textField_2.setBounds(160, 148, 130, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		//endDate
		textField_3 = new JTextField();
		textField_3.setToolTipText("dd/mm/yyyy");
		textField_3.setBounds(160, 186, 130, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		//extra textbox, delete or use for admin password
		textField_4 = new JTextField();
		textField_4.setBounds(160, 224, 130, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		//promoName
		textField_5 = new JTextField();
		textField_5.setToolTipText("The name of the promotion");
		textField_5.setBounds(160, 34, 130, 26);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnAddPromotion = new JButton("Add Promotion");
		btnAddPromotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//submit button was clicked... check each text field
				String promoName = "";
				String promoType = "";
				String promoTag = "";
				String beginDate = "";
				String endDate = "";
				JLabel nameErrMsg = new JLabel("Invalid Promo Name");
				nameErrMsg.setForeground(Color.RED);
				nameErrMsg.setVisible(false);
				contentPane.add(nameErrMsg);
				JLabel typeErrMsg = new JLabel("Invalid Promo Type");
				typeErrMsg.setForeground(Color.RED);
				typeErrMsg.setVisible(false);
				contentPane.add(typeErrMsg);
				JLabel tagErrMsg = new JLabel("Invalid Promo Type");
				tagErrMsg.setForeground(Color.RED);
				tagErrMsg.setVisible(false);
				contentPane.add(tagErrMsg);
				JLabel beginErrMsg = new JLabel("Invalid begin date");
				beginErrMsg.setForeground(Color.RED);
				beginErrMsg.setVisible(false);
				contentPane.add(beginErrMsg);
				JLabel endErrMsg = new JLabel("Invalid end date");
				endErrMsg.setForeground(Color.RED);
				endErrMsg.setVisible(false);
				contentPane.add(endErrMsg);
				promoName = textField_5.getText();
				promoType = textField.getText();
				promoTag = textField_1.getText();
				beginDate = textField_2.getText();
				endDate = textField_3.getText();
				//check each field and display error message if criteria doesn't fit
				
				String textToCheck;
				textToCheck = promoName;
				if(promoName == "" || promoName.length() <= 2) {
					//errorMsg
					//or make textBox red
				} else {
					//hide error msg
				}
				if(promoType == "" || (promoType.charAt(0)!= '%' && promoType.charAt(0)!='$')
						|| (promoType.charAt(1) <= '0' && promoType.charAt(1) > '9')) {
					//display errorMsg
				} else {
					//hide error message
				}
				if(promoTag == "" || promoTag.length() <= 2) {
					//display error msg
				} else {
					//hide error msg
				}
				if(beginDate == "" || beginDate.length() != 10) {
					//display error msg
				} else {
					//hide error msg
				}
				if(endDate == "" || endDate.length() != 10) {
					//display error msg
				} else {
					//hide error msg
				}
			}
		});
		btnAddPromotion.setBounds(302, 243, 130, 29);
		contentPane.add(btnAddPromotion);
		
		lblPromoName = new JLabel("Promo Name");
		lblPromoName.setForeground(Color.WHITE);
		lblPromoName.setBounds(68, 39, 80, 16);
		contentPane.add(lblPromoName);
		
		lblPromoType = new JLabel("Promo Type");
		lblPromoType.setForeground(Color.WHITE);
		lblPromoType.setBounds(68, 77, 80, 16);
		contentPane.add(lblPromoType);
		
		lblNewLabel = new JLabel("Promo Item");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(68, 115, 80, 16);
		contentPane.add(lblNewLabel);
		
		lblStartDate = new JLabel("Start Date");
		lblStartDate.setForeground(Color.WHITE);
		lblStartDate.setBounds(68, 153, 80, 16);
		contentPane.add(lblStartDate);
		
		lblEndDate = new JLabel("End Date");
		lblEndDate.setForeground(Color.WHITE);
		lblEndDate.setBounds(68, 191, 61, 16);
		contentPane.add(lblEndDate);
		
		JLabel lblBgpic = new JLabel("bgpic");
		lblBgpic.setBounds(0, 0, 456, 306);
		ImageIcon img = new ImageIcon(PromotionWindow.class.getResource("/images/shopbgpic.jpeg"));
		Image img2 = img.getImage();
		Image sizedImg = img2.getScaledInstance(lblBgpic.getWidth(), lblBgpic.getHeight(), img2.SCALE_SMOOTH);
		ImageIcon newImg = new ImageIcon(sizedImg);
		lblBgpic.setIcon(newImg);
		
		contentPane.add(lblBgpic);
	}
}
