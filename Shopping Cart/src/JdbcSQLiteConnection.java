import java.sql.*;
import java.util.*;

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
  
  private String SEARCH_ALL_ATTRS = "SELECT * FROM Users";
  
  private String SEARCH_USER_AND_PASS = "SELECT Username, Password FROM Users";
  
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:UsersDb.db";
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
            	//create admin account info
            	
            	JdbcSQLiteConnection db = new JdbcSQLiteConnection();
            	//drop the old table
            	if (db.dropTable == true) {
            		System.out.println("Dropped table");
            		db.dropTable("Users");
            	}
            	//create table
            	db.createTable("Users");
            	System.out.println("created table");
            	//add user to database
            	db.addUserToDatabase(admin);
            	db.displayInfo();
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
    }
    
    public void addUserToDatabase(User newUser) {
    	//MUST HASH PASSWORD
   
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
    }
    
    public void dropTable(String tableName) {
    	try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(DROP_TABLE + " " + tableName);
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
    public void displayInfo(){
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
				rs = st.executeQuery(SEARCH_ALL_ATTRS);
				userName = rs.getString(1);
				userPass = rs.getString(2);
				city = rs.getString(3);
				state = rs.getString(4);
				zip = rs.getString(5);
				credit = rs.getString(6);
				userType = rs.getInt(7);
				System.out.println("Username Password City State Zip Credit UserType");
				System.out.println(userName + " " + userPass + " " + city + " " + state + " " +
									zip + " " + credit + " " + userType);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    }
}


/*private String CREATE = "CREATE TABLE Users (\r\n" + 
	"    Username   TEXT    PRIMARY KEY,\r\n" + 
	"    Password   TEXT,\r\n" + 
	"    City       TEXT,\r\n" + 
	"    State      TEXT,\r\n" + 
	"    Zipcode    TEXT,\r\n" + 
	"    Creditcard TEXT,\r\n" + 
	"    Status     INTEGER\r\n" + 
	");";*/

//private String INSERT = "INSERT INTO Users(Username, Password, Status) VALUES(?,?,?)";
