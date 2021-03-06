/**
 * @author Williams
 */
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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class PromotionWindow.
 */
public class PromotionWindow extends JFrame {

	/** The content pane. */
	private JPanel contentPane;
	
	/** The text field. */
	private JTextField textField;
	
	/** The text field 1. */
	private JTextField textField_1;
	
	/** The text field 2. */
	private JTextField textField_2;
	
	/** The text field 3. */
	private JTextField textField_3;
	
	/** The text field 4. */
	private JTextField textField_4;
	
	/** The text field 5. */
	private JTextField textField_5;
	
	/** The lbl promo name. */
	private JLabel lblPromoName;
	
	/** The lbl promo type. */
	private JLabel lblPromoType;
	
	/** The lbl new label. */
	private JLabel lblNewLabel;
	
	/** The lbl start date. */
	private JLabel lblStartDate;
	
	/** The lbl end date. */
	private JLabel lblEndDate;
	
	/** The is valid. */
	private boolean isValid;
	
	/** The end err msg. */
	private JLabel nameErrMsg, tagErrMsg, typeErrMsg, beginErrMsg, endErrMsg, doesNotExistMsg;
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromotionWindow frame = new PromotionWindow(new User());
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
	 * @param user the user
	 */
	public PromotionWindow(User user) {
		//create the database
		JdbcSQLiteConnection db = new JdbcSQLiteConnection();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//home button
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go back to ShopWindow
				db.closeConnection();
				new ShopWindow(user, ShopWindow.items).setVisible(true);;
				dispose();
			}
		});
		btnHome.setBounds(12, 243, 117, 29);
		contentPane.add(btnHome);
		//error messages
		nameErrMsg = new JLabel("Invalid Promo Name");
		nameErrMsg.setForeground(Color.RED);
		nameErrMsg.setBounds(300, 34, 250, 26);
		nameErrMsg.setVisible(false);
		contentPane.add(nameErrMsg);

		doesNotExistMsg = new JLabel("Promo Does Not Exist");
		doesNotExistMsg.setForeground(Color.RED);
		doesNotExistMsg.setBounds(300, 34, 250, 26);
		doesNotExistMsg.setVisible(false);
		contentPane.add(doesNotExistMsg);
		
		typeErrMsg = new JLabel("Invalid Promo Type");
		typeErrMsg.setForeground(Color.RED);
		typeErrMsg.setVisible(false);
		typeErrMsg.setBounds(300, 72, 250, 26);
		contentPane.add(typeErrMsg);
		tagErrMsg = new JLabel("Invalid Promo Type");
		tagErrMsg.setForeground(Color.RED);
		tagErrMsg.setBounds(300, 110, 250, 26);
		tagErrMsg.setVisible(false);
		contentPane.add(tagErrMsg);
		beginErrMsg = new JLabel("Invalid begin date");
		beginErrMsg.setForeground(Color.RED);
		beginErrMsg.setBounds(300, 148, 250, 26);
		beginErrMsg.setVisible(false);
		contentPane.add(beginErrMsg);
		endErrMsg = new JLabel("Invalid end date");
		endErrMsg.setForeground(Color.RED);
		endErrMsg.setBounds(300, 186, 250, 26);
		endErrMsg.setVisible(false);
		contentPane.add(endErrMsg);
		
		//textFields
		JLabel lblFillInAll = new JLabel("Fill in all fields to add a valid promotion");
		lblFillInAll.setForeground(Color.WHITE);
		lblFillInAll.setBounds(92, 6, 266, 16);
		contentPane.add(lblFillInAll);
		//promoName
		textField_5 = new JTextField();
		textField_5.setToolTipText("The name of the promotion");
		textField_5.setBounds(160, 34, 130, 26);
		contentPane.add(textField_5);
		//textField_5.setColumns(10);
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
		//contentPane.add(textField_4);
		
		JButton btnEditPromotion = new JButton("Edit Promotion");
		/**
		 * can edit the promotype, tag, begin date and endDate fields but not the name field
		 */
		btnEditPromotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//similar/same logic as add promo button but call updatePromo method if valid
				String promoName = "";
				String promoType = "";
				String promoTag = "";
				String beginDate = "";
				String endDate = "";
				
				
				String oldPromoName = textField_5.getText();
				//make a new textbox for old promo name?
				
				promoName = textField_5.getText();
				promoType = textField.getText();
				promoTag = textField_1.getText();
				beginDate = textField_2.getText();
				endDate = textField_3.getText();
				
				boolean isValid = checkValidity();
				
				if (!db.doesPromotionExist(oldPromoName)) {
					//display error message for not being able to find promotion
					System.out.println("promotion " + oldPromoName + " does not exist");
					isValid = false;
					//display ErrorMessage
					doesNotExistMsg.setVisible(true);
				}
				//if all fields were valid, add promotion
				if(isValid) {
					System.out.println("succesfully edited promotion " + oldPromoName);
					//db.openConnection();
					beginDate = formatDate(beginDate);
					endDate = formatDate(endDate);
					db.updatePromotion(oldPromoName, promoName, promoType, promoTag,
							beginDate, endDate);
					//db.displayPromotions();
					db.closeConnection();
				}
			}
		});
		btnEditPromotion.setBounds(160, 243, 130, 29);
		contentPane.add(btnEditPromotion);
		
		JButton btnAddPromotion = new JButton("Add Promotion");
		btnAddPromotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//submit button was clicked... check each text field
				isValid = true; //if promotion is valid, will stay true 
				String promoName = "";
				String promoType = "";
				String promoTag = "";
				String beginDate = "";
				String endDate = "";
				
				promoName = textField_5.getText();
				promoType = textField.getText();
				promoTag = textField_1.getText();
				beginDate = textField_2.getText();
				endDate = textField_3.getText();
				//check each field and display error message if criteria doesn't fit
				
				String textToCheck;
				textToCheck = promoName;
				doesNotExistMsg.setVisible(false);
				//could just call checkValidity() method instead of this
				if(promoName.equals("")|| promoName.length() <= 2) {
					System.out.println("bad promoname");
					isValid = false;
					nameErrMsg.setVisible(true);
					//errorMsg
					//or make textBox red
				} else {
					nameErrMsg.setVisible(false);
					//hide error msgs
				}
				if(promoType.equals("") || (promoType.charAt(0)!= '%' && promoType.charAt(0)!='$')
						|| (promoType.charAt(1) < '0' && promoType.charAt(1) > '9')) {
					isValid = false;
					typeErrMsg.setVisible(true);
					//display errorMsg
				} else {
					typeErrMsg.setVisible(false);
					//hide error message
				}
				if(promoTag.equals("") || promoTag.length() <= 2) {
					isValid = false;
					tagErrMsg.setVisible(true);
					//display error msg
				} else {
					tagErrMsg.setVisible(false);
					//hide error msg
				}
				if(beginDate.equals("") || beginDate.length() != 10 || !checkDateFormat(beginDate)) {
					isValid = false;
					beginErrMsg.setVisible(true);
					//display error msg
				} else {
					beginErrMsg.setVisible(false);
					//hide error msg
				}
				if(endDate.equals("") || endDate.length() != 10 || !checkDateFormat(endDate)) {
					isValid = false;
					endErrMsg.setVisible(true);
					//display error msg
				} else { //check date format before hiding error message
					//hide error msg
					endErrMsg.setVisible(false);
				}
				//check if the promotion was already applie
				//if all fields were valid, add promotion
				if(isValid) {
					System.out.println("succesfully added promotion");
					//JdbcSQLiteConnection db = new JdbcSQLiteConnection();
					//db.openConnection();
					beginDate = formatDate(beginDate);
					endDate = formatDate(endDate);
					db.insertPromotion(promoName, promoType, promoTag, beginDate, endDate);
					//db.displayPromotions();
					db.closeConnection();
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
	
	/**
	 * Check date format.
	 *
	 * @param date the date
	 * @return true, if successful
	 */
	private boolean checkDateFormat(String date) {
		boolean valid = true;
		if (date.charAt(2) != '/' && date.charAt(2) != '-') {
			valid = false;
		} 
		if(date.charAt(5) != '/' && date.charAt(5) != '-') {
			valid = false;
		}
		for(int i = 0; i < date.length(); i++){
			if (i !=2 && i != 5) {
				if (date.charAt(i) < '0' || date.charAt(i) > '9') {
					valid = false;
				}
			}
		}
		return valid;
	}
	
	/**
	 * Check validity.
	 *
	 * @return true, if successful
	 */
	private boolean checkValidity() {
		isValid = true; //if promotion is valid, will stay true 
		String promoName = "";
		String promoType = "";
		String promoTag = "";
		String beginDate = "";
		String endDate = "";
		
		promoName = textField_5.getText();
		promoType = textField.getText();
		promoTag = textField_1.getText();
		beginDate = textField_2.getText();
		endDate = textField_3.getText();
		//check each field and display error message if criteria doesn't fit
		
		String textToCheck;
		textToCheck = promoName;
		doesNotExistMsg.setVisible(false);
		if(promoName.equals("")|| promoName.length() <= 2) {
			System.out.println("bad promoname");
			isValid = false;
			nameErrMsg.setVisible(true);
			//errorMsg
			//or make textBox red
		} else {
			nameErrMsg.setVisible(false);
			//hide error msg
		}
		if(promoType.equals("") || (promoType.charAt(0)!= '%' && promoType.charAt(0)!='$')
				|| (promoType.charAt(1) < '0' && promoType.charAt(1) > '9')) {
			isValid = false;
			typeErrMsg.setVisible(true);
			//display errorMsg
		} else {
			typeErrMsg.setVisible(false);
			//hide error message
		}
		if(promoTag.equals("") || promoTag.length() <= 2) {
			isValid = false;
			tagErrMsg.setVisible(true);
			//display error msg
		} else {
			tagErrMsg.setVisible(false);
			//hide error msg
		}
		if(beginDate.equals("") || beginDate.length() != 10 || !checkDateFormat(beginDate)) {
			isValid = false;
			beginErrMsg.setVisible(true);
			//display error msg
		} else {
			beginErrMsg.setVisible(false);
			//hide error msg
		}
		if(endDate.equals("") || endDate.length() != 10 || !checkDateFormat(endDate)) {
			isValid = false;
			endErrMsg.setVisible(true);
			//display error msg
		} else { //check date format before hiding error message
			//hide error msg
			endErrMsg.setVisible(false);
		}
		return isValid;
	}
	
	private String formatDate(String date) {
		String temp= "";
		for(int i = 0; i< date.length(); i++) {
			if(i == 2 || i == 5) {
				temp += '-';
			} else {
				temp += date.charAt(i);
			}
		}
		return temp;
	}
	
}
