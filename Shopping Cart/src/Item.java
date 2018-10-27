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
	public JLabel price;
	public int amount; /*amount of item that user wants */
	public String filePath;
	public String item;
	Item(String description, String price, String filePath){		
		this.name = description;		
		StringBuilder sb = new StringBuilder(description);
		//sb.insert(0, "    ");
		description = sb.toString();
		this.discription = new JLabel(description);
		this.price = new JLabel(price);
		this.icon = new ImageIcon(Login.class.getResource(filePath));
		this.select = new JButton("select");
		this.amount = 0;
		this.filePath = filePath;
		this.item = description;
	}
	
}
