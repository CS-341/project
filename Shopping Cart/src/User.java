/*
 * 
 */
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {
	
	/** The user name. */
	String userName;
	
	/** The password. */
	String password;
	
	/** The street. */
	String street;
	
	/** The city. */
	String city;
	
	/** The state. */
	String state;
	
	/** The zip code. */
	String zipCode;
	
	/** The credit card. */
	String creditCard;
	
	/** The user type. */
	int userType;//0 = guest, 1 = registered customer, 2 = admin?
	
	/** The selected items. */
	public static ArrayList<Item> selectedItems = new ArrayList<Item>();
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
		this.userType = 0;
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param street the street
	 * @param city the city
	 * @param state the state
	 * @param zipcode the zipcode
	 * @param creditCard the credit card
	 */
	public User(String userName, String password, String street, String city, String state, String zipcode, String creditCard) {
		// Add cart in future
		this.userName = userName;
		this.password = password;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipcode;
		this.creditCard = creditCard;
		userType = 1;
	}
}
