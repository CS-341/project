import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setUndecorated(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 469);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		panel.setBounds(0, -15, 683, 131);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 260, 131);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/iphone.jpeg")));
		panel.add(lblNewLabel);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the team4 electroincs store!");
		lblWelcomeToThe.setBounds(315, 28, 321, 15);
		panel.add(lblWelcomeToThe);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(0, 0, 212, 109);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/images/iPhone.jpeg")));
		
		JLabel lblBuyOneIphone = new JLabel("Buy one iPhone get the 2nd one free!");
		lblBuyOneIphone.setBounds(321, 68, 332, 15);
		panel.add(lblBuyOneIphone);
	
		// TODO: sign in page
		Button buttSignIn = new Button("Sign In");
		buttSignIn.setForeground(Color.WHITE);
		buttSignIn.setFont(UIManager.getFont("MenuBar.font"));
		buttSignIn.setBackground(new Color(47, 79, 79));
		buttSignIn.setBounds(516, 298, 139, 39);
		contentPane.add(buttSignIn);
		buttSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignInWindow signIn = new SignInWindow();
				signIn.setVisible(true);
				dispose();
			}
		});
		
		// TODO: register page link
		Button bttRegister = new Button("Register");
		bttRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bttRegister.setVisible(false);

				RegisterWindow guest = new RegisterWindow();
				guest.setVisible(true);
				dispose();


			}
		});
		bttRegister.setBackground(new Color(47, 79, 79));
		bttRegister.setForeground(Color.WHITE);
		bttRegister.setBounds(516, 228, 139, 39);
		contentPane.add(bttRegister);
		
		Button bttGuest = new Button("Continue as Guest");
		bttGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopWindow guest = new ShopWindow(new User());
				guest.setVisible(true);
				dispose();
			}
		});
		bttGuest.setForeground(Color.WHITE);
		bttGuest.setBackground(new Color(47, 79, 79));
		bttGuest.setBounds(516, 373, 139, 39);
		contentPane.add(bttGuest);
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/images/iPhone.jpeg")));
		lblNewLabel_3.setBounds(0, 114, 266, 177);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/images/laptop.jpeg")));
		lblNewLabel_4.setBounds(0, 283, 258, 172);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/images/simple.v1.jpg")));
		lblNewLabel_1.setBounds(-31, 99, 714, 356);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
