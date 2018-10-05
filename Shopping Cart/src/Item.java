import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
// gonna be used as a object then as a templete for store items
public class Item {
	public String name;
	public ImageIcon icon;
	public JButton select;
	public JLabel discription;
	Item(String description, String filePath){
		this.name = description;
		this.discription = new JLabel(description);
		this.icon = new ImageIcon(Login.class.getResource(filePath));
		this.select = new JButton("select");
	}
	
}
