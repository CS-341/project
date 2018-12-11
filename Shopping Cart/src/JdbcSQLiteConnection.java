/*
 * 
 */
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.mindrot.jbcrypt.BCrypt;

// TODO: Auto-generated Javadoc
/**
 * This program demonstrates making JDBC connection to a SQLite database.
 * @author www.codejava.net
 *
 */
public class JdbcSQLiteConnection {
	/*
	 * Global private variables for sql statements
	 */
  /** The create order history table. */
  private static String CREATE_ORDER_HISTORY_TABLE ="CREATE TABLE OrderHistory (" 
		  + 						"Username TEXT PRIMARY KEY, "
		  + 						"Price TEXT, "
		  +							"Items TEXT, " //As CSV
		  +							"Quantities TEXT);"; //As CSV
	
  /** The create promotable. */
  private String CREATE_PROMOTABLE = "CREATE TABLE Promotions ("
	  		+						"PromotionId INTEGER PRIMARY KEY AUTOINCREMENT, "
	  		+ 						"PromoName TEXT NOT NULL, "
	  		+						"PromoType TEXT, "
	  		+						"PromoTag TEXT, " //name of the item the promo applies to
	  		+						"beginDate TEXT, "
	  		+ 						"endDate TEXT);";
  
  /** The admin user account credentials. */
  private static User admin = new User("admin", "admin", "lacrosse st", "lax", "wi", "54601", 
			"12345678912345678");
	
  /** The db URL. */
  private static String dbURL = "jdbc:sqlite:UsersDb.db";
  
  /** The connection for the database */
  private static Connection conn;

  /** The create userstable. */
  private String CREATE_USERSTABLE = "CREATE TABLE Users ("
  		+						"Username TEXT PRIMARY KEY,"
  		+ 						"Password TEXT,"
  		+ 						"City TEXT,"
  		+ 						"State TEXT,"
  		+ 						"Zipcode TEXT,"
  		+ 						"Creditcard TEXT,"
  		+ 						"Status INTEGER);";
  

  /** The drop table. */
  private String DROP_TABLE = "DROP TABLE IF EXISTS ";
  
  /** The insert users. */
  private String INSERT_USERS = "INSERT INTO Users (\r\n" + 
  		"                        Username,\r\n" + 
  		"                        Password,\r\n" +
  		"                        Street,\r\n" +			
  		"                        City,\r\n" + 
  		"                        State,\r\n" + 
  		"                        Zipcode,\r\n" + 
  		"                        Creditcard,\r\n" + 
  		"                        Status\r\n" + 
  		"                    )";
  
  /** The insert promo. */
  private String INSERT_PROMO = "INSERT INTO Promotions (\r\n" + 
  		"                        promoName,\r\n" + 
  		"                        promoType,\r\n" + 
  		"                        promoTag,\r\n" + 
  		"                        beginDate,\r\n" + 
  		"                        endDate\r\n" + 
  		"                    )";
  
  /** The insert order history. */
  private String INSERT_ORDER_HISTORY = "INSERT INTO OrderHistory (\r\n" + 
  		"                        Username,\r\n" + 
		"						 Price, \r\n"   +				
  		"                        Items,\r\n" + 
  		"                        Quantities,\r\n"+ 
  		"                        Date\r\n" + 
  		"                    )";
  
  /** The search usernames sql stmt. */
  private String SEARCH_USERNAMES = "SELECT Username FROM Users";
  
  /** The search all attrs. */
  private String SEARCH_ALL_ATTRS = "SELECT * FROM";
  
  /** The search user and pass. */
  private String SEARCH_USER_AND_PASS = "SELECT Username, Password FROM Users";
  
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
    	
    	//below code block used for testing and reset of database contents
       /*
    	try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:UsersDb.db";
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
            	//create admin account info
            	
            	JdbcSQLiteConnection db = new JdbcSQLiteConnection();
            	//drop the old table
            	if (db.dropTable == true) {
            		db.dropTable("Users");
            		db.dropTable("Promotions");
            	}
            	
            	//create table
            	//SQLiteException may occur here if Users is already created - not a worry
            	db.dropTable("Users");
            	db.createTable("Users");
            	//db.dropTable("OrderHistory");
            	//db.dropTable("Promotions");
            	//db.createPromotionTable();
            	
            	
            	Statement st = conn.createStatement();
            	//st.executeUpdate("DELETE FROM Users WHERE Username = 'admin'");
            	
            	//add user to database
            	if(!db.searchUserNames("admin")) {
            		db.addUserToDatabase(admin);
            	}
            	st.executeUpdate("UPDATE Users SET Status = 2 WHERE Username = 'admin'");
            	
            	//st.executeUpdate(CREATE_ORDER_HISTORY_TABLE);
            	
            	//if(!db.searchDatabaseForTag("20% off iPhone", "iPhone").equals("iPhone")) {
            	//	db.insertPromotion("20% off iPhone", "20%", "iPhone", "01-11-2018", "30-11-2018");
            	//}
            	//System.out.println(db.checkPromoDate("20% off iPhone"));
           
            	//db.insertPromotion("30% off macbook", "%30", "macbook", "01-12-2018", "30-12-2018");
            	System.out.println(db.checkPromoDate("30% off macbook"));
            	
            	//calls method to display all info currently held in the database
            	db.displayInfo("Users");
            	db.displayPromotions();
            	
                System.out.println("Connected to the database");
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                conn.close();
            }
          
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       */
    }
    
    /**
     * method used to check stored hashed passwords in the database.
     *
     * @param username the username to search for in the database
     * @param givenPass the password as entered by the user when logging in
     * @return true if passwords match, false otherwise
     */
    public boolean passwordsMatch(String username, String givenPass) {
    	ResultSet rs = null;
    	String hashedPass = "";
    	try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(SEARCH_USER_AND_PASS);
			String temp = "";
			while(rs.next()) {
				//column 1 in the database is the username field
				temp = rs.getString(1); //temp = the username in the database
				if (username.equalsIgnoreCase(temp)) {
					//grab the hashed password from the database
					hashedPass = rs.getString(2); //password is the 2nd field
					//may need a null check here
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return BCrypt.checkpw(givenPass, hashedPass);
    }
    
    /**
     * Adds the user to database.
     *
     * @param newUser the new user
     */
    public void addUserToDatabase(User newUser) {
    	//MUST HASH PASSWORD
      String hashedPass = BCrypt.hashpw(newUser.password, BCrypt.gensalt());
      
      //TESTING -- can delete

      if (BCrypt.checkpw(newUser.password, hashedPass)) {
    	  newUser.password = hashedPass;
      }
      
      //Encrypt userPass' here
      String userName = newUser.userName;
      String userPass = newUser.password;
      String city = newUser.city;
      String street = newUser.street;
      String state = newUser.state;
      String zip = newUser.zipCode;
      String credit = newUser.creditCard;
      int userType = newUser.userType;
      try {
    	String temp = INSERT_USERS + " VALUES ('" + userName + "', '" + userPass + "', '" + street + "', '" + city + "', '" + state +"', '" + zip + 
    			"', '" + credit + "', '" + userType + "');";
    	//remove conn line below -- call open connection immediately after creating DB
    	//conn = DriverManager.getConnection(dbURL);
    	//openConnection();
    	Statement statement = conn.createStatement();
      	statement.executeUpdate(temp);
      } catch (SQLException e) {
        e.printStackTrace();
      }
     
    }
    
    /** The drop table. */
    private boolean dropTable = false;
    //if dropTable == true
    //statement.executeUpdate(DROP_TABLE); should be uncommented 
    // OR 
    /**
     * Instantiates a new jdbc SQ lite connection.
     */
    // the dropTable method should be called upon creation of the JDBC object.
    public JdbcSQLiteConnection () {
    	//dropTable == true on first run to clear the database/tables
    	//dropTable == false to keep existing info in database
    	dropTable = false;
    	openConnection();
    }
    
    /**
     * Drop table.
     *
     * @param tableName the table name
     */
    public void dropTable(String tableName) {
    	try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(DROP_TABLE + " " + tableName);
    		System.out.println("Dropped table " + tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Creates the table.
     *
     * @param tableName the table name
     */
    public void createTable(String tableName) {
    	try {
			Statement statement = conn.createStatement();
			if (dropTable == true) {
				dropTable(tableName);
			}
			statement.executeUpdate(CREATE_USERSTABLE);
			System.out.println("created table " + tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * call this after creating the database.
     */
    public void openConnection() {
    	try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
    		//establish a connection to the database
			conn = DriverManager.getConnection(dbURL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * call this when done using the database/close program.
     */
    public void closeConnection() {
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Search user names.
     *
     * @param username the name to search for in the database
     * @return true, if successful
     */
    public boolean searchUserNames(String username) {
    	ResultSet rs = null;
    	boolean userInDb = false;
    	try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(SEARCH_USERNAMES);
			String temp = "";
			while(rs.next()) {
				//column 1 in the database is the username field
				temp = rs.getString(1);
				if (username.equalsIgnoreCase(temp)) {
					userInDb = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return userInDb;
    }
    
    /**
     * Gets the user info.
     *
     * @param username the username to search the database for
     * @return a user object w/ all the data in
     */
    public User getUserInfo(String username) {
    	User user = null;
    	ResultSet  rs = null;
    	String name = "";
        String userPass = "";
        String street ="";
        //need to add street column into user
        String city ="";
        String state = "";
        String zip = "";
        String credit = "";
        int userType;
    	if (searchUserNames(username) == true) { //found the user so return their info
    		Statement st;
			try {
				st = conn.createStatement();
				rs = st.executeQuery("SELECT * FROM Users WHERE Username = '" + username + "'");
				int i = 0; //integer for counting 
				name = rs.getString(1);
				userPass = rs.getString(2);
				street = rs.getString(3);
				city = rs.getString(4);
				state = rs.getString(5);
				zip = rs.getString(6);
				credit = rs.getString(7);
				userType = rs.getInt(8);
				user = new User(name, userPass, street, city, state, zip, credit);
				if(userType == 2) { //if the user is an admin, set the returned user's info to admin status
					user.userType = 2;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	} 
    	//else user will be null and return null
    	return user;
    }
    
    /**
     * Gets the password.
     *
     * @param username the username to search for
     * @return return the password as a string
     */
    public String getPassword(String username) {
    	String password = "";
    	ResultSet rs = null;
    	
    	try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(SEARCH_USER_AND_PASS);
			String temp = "";
			while(rs.next()) {
				//column 1 in the database is the username field
				temp = rs.getString(1);
				if (username.equalsIgnoreCase(temp)) {
					password = rs.getString(2);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return password;
    }
    
    /**
     * displays the contents of the table given the table name.
     *
     * @param tableName the name of the table to display contents for
     */
    public void displayInfo(String tableName){
    	ResultSet  rs = null;
    	String userName = "";
        String userPass = "";
        String city ="";
        String state = "";
        String zip = "";
        String credit = "";
        int userType;
    		Statement st;
			try {
				st = conn.createStatement();
				rs = st.executeQuery("SELECT * FROM " + tableName);
				System.out.println("Username Password City State Zip Credit UserType");
				while (rs.next()) {
					userName = rs.getString(1);
					userPass = rs.getString(2);
					city = rs.getString(3);
					state = rs.getString(4);
					zip = rs.getString(5);
					credit = rs.getString(6);
					userType = rs.getInt(7);
					
					System.out.println(userName + " " + userPass + " " + city + " " + state + " " +
										zip + " " + credit + " " + userType);
				
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    }
    
    /**
     *  method for creating the promotion table 
     *  only need to call this once to initially create the promotion table inside
     *  of the "Users" Database.
     */
    
    public void createPromotionTable() {	
    	Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(CREATE_PROMOTABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Gets the promo tag.
     *
     * @param promoName = the string name of the promotion to search for
     * @return the tag of the item the promotion applies to
     */
    public String getPromoTag(String promoName) {
    	String search = "SELECT promoTag FROM Promotions WHERE promoName = '" + promoName +
    			"'";
    	Statement st;
    	ResultSet rs;
    	String result = "";
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(search);
			if (!rs.isClosed()) {
				result = rs.getString(1); //promotag is the the only thing returned 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     * Check promo date.
     *
     * @param promoName the promo name
     * @return true if the current date is within the start and end date of the promotion
     */
    public boolean checkPromoDate(String promoName) {
    	boolean result = false;
    	String search = "SELECT beginDate, endDate FROM Promotions WHERE promoName = '" + promoName +
    			"'";
    	Statement st;
    	ResultSet rs;
    	String currentStringDate = "";
    	String beginDate = "";
    	String endDate = "";
    	
    	//IMPORTANT NOTE
    	//dates of promotions are assumed to be stored in this format********
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd-MM-yyyy" );
    	
    	/*
    	 * depends how we store the date in the table to see if we want
    	 */
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(search);
			//while rs.next() loop through all results in case promotions have matching names?
			
			beginDate = rs.getString(1); //1st attribute  
			endDate = rs.getString(2); //2nd attribute 
			
			//grab the start and end dates in objects to compare against each other
			LocalDate startDate = LocalDate.parse(beginDate, formatter); 
			LocalDate endOfPromo = LocalDate.parse(endDate, formatter);
			LocalDate currentDate = java.time.LocalDate.now();
			
			//TEST
			currentStringDate = currentDate.format(formatter).toString();
			System.out.println("current date is: " + currentStringDate);
			//DELETE TEST
			
			//check if the promo date is valid
			if(currentDate.isBefore(endOfPromo) && currentDate.isAfter(startDate) || (currentDate.isEqual(startDate))) {
				System.out.println("DEBUG: Date for promotion is valid");
				result = true;
			}
			else {
				if(!currentDate.isBefore(endOfPromo)) {
					System.out.println("DEBUG: current date is after end of promotion");
				}
				else {
					System.out.println("DEBUG: current date is before start of promotion");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     * Insert promotion.
     *
     * @param promoName name of promotion
     * @param promoType 20%, 5%, 10%, $5, etc.
     * @param promoTag name of the item the promotion applies to
     * @param promoBeginDate start date for the promotion
     * @param promoEndDate end date for the promotion
     * @return true, if successful
     */
    public boolean insertPromotion(String promoName, String promoType, 
    		String promoTag, String promoBeginDate, String promoEndDate) {
    	boolean inserted = false; 
    	//first column is promoId
    	Statement st;
    	ResultSet rs;
    	
    	String insert = "";
      	try {
      			insert = INSERT_PROMO + " VALUES ('" + promoName + "', '" + promoType + "', '" + 
      					promoTag + "', '" + promoBeginDate +"', '" + promoEndDate + "');";
    			st = conn.createStatement();
    			st.executeUpdate(insert);
    			inserted = true;
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	
    	return inserted; 
    }
    
    /**
     * method for printing out countents of promotion table.
     */
	public void displayPromotions() {
		ResultSet rs = null;
		String promoName = "";
		String promoType = "";
		String promoTag = "";
		String beginDate = "";
		String endDate = "";
		Statement st;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Promotions");
			while (rs.next()) {
				promoName = rs.getString(2);
				promoType = rs.getString(3);
				promoTag = rs.getString(4);
				beginDate = rs.getString(5);
				endDate = rs.getString(6);

				System.out.println(promoName + " " + promoType + " " + promoTag + " " + beginDate + " " + endDate);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    /**
     * Gets the promotion type.
     *
     * @param promoName the name of the promotion to search for
     * @return the String representation (type) of what the promotion offers
     */
    public String getPromotionType(String promoName) {
    	String search = "SELECT promoType FROM Promotions WHERE promoName = '" + promoName +
    			"'";
    	Statement st;
    	ResultSet rs;
    	String result = "";
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(search);
			if (!rs.isClosed()) {
				result = rs.getString(1); //promoType is the the only thing returned 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     * Does promotion exist.
     *
     * @param promoName the promo name
     * @return true, if successful
     */
    public boolean doesPromotionExist(String promoName) {
    	boolean exists = true;
    	String search = "SELECT * FROM Promotions WHERE promoName = '" + promoName +
    			"'";
    	JdbcSQLiteConnection db = new JdbcSQLiteConnection();
    	db.displayPromotions();
    	Statement st;
    	ResultSet rs;
 
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(search);
			
			if (!rs.isClosed()) {
				exists = true;
			} else {
				exists = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return exists;
    }
    
    /**
     * Update promotion.
     *
     * @param oldPromoName the old promo name
     * @param promoName the promo name
     * @param promoType the promo type
     * @param promoTag the promo tag
     * @param promoBeginDate the promo begin date
     * @param promoEndDate the promo end date
     */
    public void updatePromotion(String oldPromoName, String promoName, String promoType, 
    		String promoTag, String promoBeginDate, String promoEndDate) {
    	//sql statement format for updating promotions
    	String update = "UPDATE Promotions SET PromoName = ?, PromoType = ?, PromoTag = ?,"
    			+ "beginDate = ?, endDate = ? WHERE PromoName = '" + oldPromoName + "'";
    	try {
			PreparedStatement stmt = conn.prepareStatement(update);
			stmt.setString(1, promoName);
			stmt.setString(2, promoType);
			stmt.setString(3, promoTag);
			stmt.setString(4, promoBeginDate);
			stmt.setString(5, promoEndDate);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Creates the new table.
     *
     * @param statement the CREATE TABLE statement that is in sql form
     */
    public void createNewTable(String statement) {	
    	Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Insert order history.
     *
     * @param username the username of the customer who issued the order
     * @param price the price
     * @param items array of quantities, not in CSV format
     * @param quantities the array of quantities already in CSV format
     * @param date the date
     * @return true, if successful
     */
    public boolean insertOrderHistory(String username, String price, String[] items, String quantities, String date) {
    	boolean inserted = false; 
    	//first column is promoId
    	//insert the data collected?
    	Statement st;
    	ResultSet rs;
    	
    	String itemsCsv = convertArrayToCsv(items);
    	System.out.println("adding order history for the following: ");
    	System.out.println("username: " + username);
    	System.out.println("price: " + price);
    	System.out.println("itemsCSV = " + itemsCsv);
    	System.out.println("quantities = " + quantities);
    	
    	String insert = "";
      	try {
      			insert = INSERT_ORDER_HISTORY + " VALUES ('" + username + "', '" + price + "', '" + itemsCsv + "', '" + 
      					quantities+ "', '" + date + "');";
    			st = conn.createStatement();
    			st.executeUpdate(insert);
    			inserted = true;
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	
    	return inserted; 
    }
    
    /**
     * Convert array to csv.
     *
     * @param arr the array to convert to comma separated values
     * @return the string of values in comma separated form
     */
    public String convertArrayToCsv(String[] arr) {
    	String csv = "";
    	
    	for(int i = 0; i < arr.length; i++) {
    		if (i < arr.length-1) {
    			csv += arr[i] + ", ";
    		} else {
    			csv += arr[i];
    		}
    	}
    	
    	return csv;
    }
    
    
    /**
     * Gets the order history.
     *
     * @param userName the user name
     * @return the order history
     */
    public ArrayList<OrderHistory> getOrderHistory(String userName) {
    	String search = "SELECT * FROM OrderHistory WHERE Username = '" + userName +
    			"'";
    	Statement st;
    	ResultSet rs;
    	ArrayList<OrderHistory> result = new ArrayList<>();
    	String username="", cost="", items="", date="", quantities="";
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(search);
			if (!rs.isClosed()) {
				while(rs.next()) {
					cost = rs.getString(2); 
					items = rs.getString(3);
					quantities = rs.getString(4);
					date = rs.getString(5);
					OrderHistory temp = new OrderHistory(username, cost, items, quantities, date);
					result.add(temp);
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
}


