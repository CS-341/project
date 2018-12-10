/*
 * 
 */
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
// TODO: Auto-generated Javadoc

/**
 * The Class Item.
 */
// gonna be used as a object then as a templete for store items
public class Item {
	
	/** The name. */
	public String name;
	
	/** The icon. */
	public ImageIcon icon;
	
	/** The select. */
	public JButton select;
	
	/** The discription. */
	public JLabel discription;
	
	/** The price. */
	public JLabel price;
	
	/** The amount. */
	public int amount; /*amount of item that user wants */
	
	/** The file path. */
	public String filePath;
	
	/** The item. */
	public String item;
	
	/**
	 * Instantiates a new item.
	 *
	 * @param description the description
	 * @param price the price
	 * @param filePath the file path
	 */
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
