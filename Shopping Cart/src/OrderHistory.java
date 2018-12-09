
public class OrderHistory {

	public String cost, items, quantities, date;
	private String username;
	
	public OrderHistory(String username, String cost, 
			String items, String quantities, String date) {
		this.username = username;
		this.cost = cost;
		this.quantities = quantities;
		this.items = items;
		this.date = date;
	}
	
}
