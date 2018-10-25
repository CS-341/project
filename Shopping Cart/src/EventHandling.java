import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

/** 
 * 
 * Handles the action listener and event handling for the selection of items
 * added to the shopping cart. 
 *
 */
public class EventHandling implements ActionListener {

     private ArrayList<JButton> selectButtons;
     private ArrayList<Item> selectedItems;
     private ArrayList<JLabel> JlabelArry;
     private ArrayList<JLabel> priceArry;
     private ArrayList<JLabel> picArry;

     public EventHandling(ArrayList<JButton> selectButtons, ArrayList<JLabel> JlabelArry, ArrayList<JLabel> priceArry, ArrayList<JLabel> picArry) {

        this.selectButtons = selectButtons;
        selectedItems = new ArrayList<Item>();
        this.JlabelArry = JlabelArry;
        this.priceArry = priceArry;
        this.picArry = picArry;
    }

    public void actionPerformed(ActionEvent event) {

    	for(int i = 0; i < selectButtons.size(); i++)
        if (event.getSource() == selectButtons.get(i)) {
        	Item temp = new Item(JlabelArry.get(i).getText(), priceArry.get(i).getText(), picArry.get(i).getText());
            System.out.println(temp.name);
            if(selectedItems.size() == 0) {
            	selectedItems.add(temp);
            } else if(!selectedItems.contains(temp)) {
            	selectedItems.add(temp);
            }
            break;
        }

     }
    
    public ArrayList<Item> getSelected(){
    	return selectedItems;
    }

}
