
// TODO: Auto-generated Javadoc
/**
 * The Class OrderHistory.
 */
public class OrderHistory {

	/** The date. */
	public String cost, items, quantities, date;
	
	/** The username. */
	private String username;
	
	/**
	 * Instantiates a new order history.
	 *
	 * @param username the username of the user
	 * @param cost the total cost of the purchase
	 * @param items the string of items that the user has bought
	 * @param quantities the quantities of items the user has bought 
	 * @param date the date the items were purchased
	 */
	public OrderHistory(String username, String cost, 
			String items, String quantities, String date) {
		this.username = username;
		this.cost = cost;
		this.quantities = quantities;
		this.items = items;
		this.date = date;
	}
	
}
