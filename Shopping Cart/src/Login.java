import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtSignIn;
	private JPasswordField passwordField;

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
		panel.setBackground(new Color(119, 136, 153));
		panel.setBounds(0, -15, 683, 131);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 291, 131);
		lblNewLabel.setIcon(new ImageIcon("/home/alex/project/Shopping Cart/pics/iphone.jpeg"));
		panel.add(lblNewLabel);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the team4 electroincs store!");
		lblWelcomeToThe.setBounds(315, 28, 321, 15);
		panel.add(lblWelcomeToThe);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Buy one iPhone get the 2nd half off!");
		lblNewJgoodiesLabel.setBounds(325, 56, 321, 15);
		panel.add(lblNewJgoodiesLabel);
		
		Button buttSignIn = new Button("Sign In");
		buttSignIn.setForeground(Color.WHITE);
		buttSignIn.setFont(UIManager.getFont("MenuBar.font"));
		buttSignIn.setBackground(new Color(47, 79, 79));
		buttSignIn.setBounds(281, 302, 170, 46);
		contentPane.add(buttSignIn);
		
		txtSignIn = new JTextField();
		txtSignIn.setBounds(281, 219, 170, 19);
		contentPane.add(txtSignIn);
		txtSignIn.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(281, 261, 170, 19);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Kalimati", Font.PLAIN, 12));
		lblPassword.setBounds(190, 263, 70, 15);
		contentPane.add(lblPassword);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Kalimati", Font.PLAIN, 12));
		lblUserName.setBounds(190, 221, 98, 15);
		contentPane.add(lblUserName);
		
		JLabel lblNewToOur = new JLabel("New to our Store?");
		lblNewToOur.setBounds(12, 344, 139, 15);
		contentPane.add(lblNewToOur);
		
		Button bttRegister = new Button("Register");
		bttRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bttRegister.setBackground(new Color(47, 79, 79));
		bttRegister.setForeground(Color.WHITE);
		bttRegister.setBounds(12, 373, 139, 39);
		contentPane.add(bttRegister);
		
		Button bttGuest = new Button("Continue as Guest");
		bttGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuestWindow guest = new GuestWindow();
				guest.setVisible(true);
				dispose();
			}
		});
		bttGuest.setForeground(Color.WHITE);
		bttGuest.setBackground(new Color(47, 79, 79));
		bttGuest.setBounds(516, 373, 139, 39);
		contentPane.add(bttGuest);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("/home/alex/project/Shopping Cart/pics/simple.v1.jpg"));
		lblNewLabel_1.setBounds(-216, 111, 989, 352);
		contentPane.add(lblNewLabel_1);
	}
}
