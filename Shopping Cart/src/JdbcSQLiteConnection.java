import java.sql.*;
import java.util.*;

import org.mindrot.jbcrypt.BCrypt;

/**
 * This program demonstrates making JDBC connection to a SQLite database.
 * @author www.codejava.net
 *
 */
public class JdbcSQLiteConnection {
	
  private static User admin = new User("admin", "admin", "lax", "wi", "54601", 
			"12345678912345678");
	
  private static String dbURL = "jdbc:sqlite:UsersDb.db";
  private static Connection conn;

  private String CREATE_TABLE = "CREATE TABLE Users ("
  		+						"Username TEXT PRIMARY KEY,"
  		+ 						"Password TEXT,"
  		+ 						"City TEXT,"
  		+ 						"State TEXT,"
  		+ 						"Zipcode TEXT,"
  		+ 						"Creditcard TEXT,"
  		+ 						"Status INTEGER);";
  

  private String DROP_TABLE = "DROP TABLE IF EXISTS ";
  
  private String INSERT_INTO = "INSERT INTO Users (\r\n" + 
  		"                        Username,\r\n" + 
  		"                        Password,\r\n" + 
  		"                        City,\r\n" + 
  		"                        State,\r\n" + 
  		"                        Zipcode,\r\n" + 
  		"                        Creditcard,\r\n" + 
  		"                        Status\r\n" + 
  		"                    )";
  
  private String SEARCH_USERNAMES = "SELECT Username FROM Users";
  
  private String SEARCH_ALL_ATTRS = "SELECT * FROM";
  
  private String SEARCH_USER_AND_PASS = "SELECT Username, Password FROM Users";
  
    public static void main(String[] args) {
    	//below code block used for testing and reset of database contents
       /*
    	try {
            Class.forName("org.sqlite.JDBC");
            //String dbURL = "jdbc:sqlite:UsersDb.db";
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
            	//create admin account info
            	
            	JdbcSQLiteConnection db = new JdbcSQLiteConnection();
            	//drop the old table
            	if (db.dropTable == true) {
            		db.dropTable("Users");
            	}
            	//create table
            	//SQLiteException may occur here if Users is already created - not a worry
            	db.createTable("Users");
            	
            	Statement st = conn.createStatement();
            	st.executeUpdate("DELETE FROM Users WHERE Username = 'admin'");
            	
            	//add user to database
            	if(!db.searchUserNames("admin")) {
            		db.addUserToDatabase(admin);
            	}
            	//calls method to display all info currently held in the database
            	db.displayInfo("Users");
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
     * method used to check stored hashed passwords in the database
     * @param username the username to search for in the database
     * @param givenPass the password as entered by the user when logging in
     * @return
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
				temp = rs.getString(1);
				if (username.equalsIgnoreCase(temp)) {
					//grab the hashed password from the database
					hashedPass = rs.getString(2);
					//may need a null check here
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return BCrypt.checkpw(givenPass, hashedPass);
    }
    
    public void addUserToDatabase(User newUser) {
    	//MUST HASH PASSWORD
      String hashedPass = BCrypt.hashpw(newUser.password, BCrypt.gensalt());
      
      //TESTING -- can delete
      //will want to store the hashed version of the password in the database
      
      //System.out.println("user pass is  : " + newUser.password);
      //System.out.println("hashed pass is: " + hashedPass);
      //System.out.println("checking if passwords match for the user: " + 
      BCrypt.checkpw(newUser.password, hashedPass);
      
      String userName = newUser.userName;
      String userPass = newUser.password;
      String city = newUser.city;
      String state = newUser.state;
      String zip = newUser.zipCode;
      String credit = newUser.creditCard;
      int userType = newUser.userType;
      try {
    	String temp = INSERT_INTO + " VALUES ('" + userName + "', '" + userPass + "', '" + city + "', '" + state +"', '" + zip + 
    			"', '" + credit + "', '" + userType + "');";
    	//remove conn line below -- call open connection immediately after creating DB
    	conn = DriverManager.getConnection(dbURL);
    	openConnection();
    	Statement statement = conn.createStatement();
      	statement.executeUpdate(temp);
      } catch (SQLException e) {
        e.printStackTrace();
      }
     
    }
    
    private boolean dropTable = false;
    //if dropTable == true
    //statement.executeUpdate(DROP_TABLE); should be uncommented 
    // OR 
    // the dropTable method should be called upon creation of the JDBC object.
    public JdbcSQLiteConnection () {
    	//dropTable == true on first run to clear the database/tables
    	//dropTable == false to keep existing info in database
    	dropTable = false;
    	openConnection();
    }
    
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
    
    public void createTable(String tableName) {
    	try {
			Statement statement = conn.createStatement();
			if (dropTable == true) {
				dropTable(tableName);
			}
			statement.executeUpdate(CREATE_TABLE);
			System.out.println("created table " + tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * call this after creating the database
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
     * call this when done using the database/close program
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
     * 
     * @param username the name to search for in the database
     * @return
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
     * 
     * @param username the username to search the database for
     * @return a user object w/ all the data in
     */
    public User getUserInfo(String username) {
    	User user = null;
    	ResultSet  rs = null;
    	String name = "";
        String userPass = "";
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
				city = rs.getString(3);
				state = rs.getString(4);
				zip = rs.getString(5);
				credit = rs.getString(6);
				userType = rs.getInt(7);
				user = new User(name, userPass, city, state, zip, credit);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	} 
    	//else user will be null and return null
    	return user;
    }
    /**
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
     * displays the contents of the table given the table name
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
     *  of the "Users" Database
     */
    
    private void createPromotionTable() {
    	String promoTable = "CREATE TABLE Promotions ("
    		  		+						"PromotionId Int PRIMARY KEY AUTO_INCREMENT, "
    		  		+ 						"PromoName TEXT NOT NULL, "
    		  		+						"PromoType TEXT, "
    		  		+						"beginDate TEXT, "
    		  		+ 						"endDate TEXT);";
    	Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(promoTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 
     * @param promotion the string name of the promotion to search for
     * @return the endDate as text 
     */
    private String getPromotion(String promotion) {
    	String search = "SELECT beginDate FROM Promotions WHERE promoName = '" + promotion +
    			"'";
    	Statement st;
    	ResultSet rs;
    	String result = "";
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(search);
			result = rs.getString(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
}


