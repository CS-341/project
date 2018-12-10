
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
	 * @param username the username
	 * @param cost the cost
	 * @param items the items
	 * @param quantities the quantities
	 * @param date the date
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
