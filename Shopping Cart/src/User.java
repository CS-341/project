
public class User {
	String userName;
	String password;
	String city;
	String state;
	String zipCode;
	String creditCard;
	int userType;
	
	
	public User() {
		this.userType = 0;
	}
	
	public User(String userName, String password, String city, String state, String zipcode, String creditCard) {
		// Add cart in future
		this.userName = userName;
		this.password = password;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.creditCard = creditCard;
		userType = 1;
		
	}
}
