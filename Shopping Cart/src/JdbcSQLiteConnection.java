import java.sql.*;
import java.util.*;

/**
 * This program demonstrates making JDBC connection to a SQLite database.
 * @author www.codejava.net
 *
 */
public class JdbcSQLiteConnection {
	private static String dbURL = "jdbc:sqlite:UsersDb.db";
  private static Connection conn;
  //private String INSERT = "INSERT INTO Users(Username, Password, Status) VALUES(?,?,?)";
  private String CREATE_TABLE = "CREATE TABLE Users ("
  		+						"Username TEXT PRIMARY KEY,"
  		+ 						"Password TEXT,"
  		+ 						"City TEXT,"
  		+ 						"State TEXT,"
  		+ 						"Zipcode TEXT,"
  		+ 						"Creditcard TEXT,"
  		+ 						"Status INTEGER);";
  
  /*private String CREATE = "CREATE TABLE Users (\r\n" + 
  		"    Username   TEXT    PRIMARY KEY,\r\n" + 
  		"    Password   TEXT,\r\n" + 
  		"    City       TEXT,\r\n" + 
  		"    State      TEXT,\r\n" + 
  		"    Zipcode    TEXT,\r\n" + 
  		"    Creditcard TEXT,\r\n" + 
  		"    Status     INTEGER\r\n" + 
  		");";*/
  private String DROP_TABLE = "DROP TABLE IF EXISTS Users";
  
  private String INSERT_INTO = "INSERT INTO Users (\r\n" + 
  		"                        Username,\r\n" + 
  		"                        Password,\r\n" + 
  		"                        City,\r\n" + 
  		"                        State,\r\n" + 
  		"                        Zipcode,\r\n" + 
  		"                        Creditcard,\r\n" + 
  		"                        Status\r\n" + 
  		"                    )";
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:UsersDb.db";
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
            	//create admin account info
            	User admin = new User("admin", "admin", "lax", "wi", "54601", 
            			"12345678912345678");
            	JdbcSQLiteConnection db = new JdbcSQLiteConnection();
            	//drop the old table
            	if (db.dropTable == true) {
            		System.out.println("Dropped table");
            		db.dropTable();
            	}
            	//create table
            	db.createTable();
            	System.out.println("created table");
            	//add user to database
            	db.addUserToDatabase(admin);
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
    void addUserToDatabase(User newUser) {
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
    			"', '" + credit + "', '" + userType + ",');";
    	conn = DriverManager.getConnection(dbURL);
    	Statement statement = conn.createStatement();
      	statement.executeUpdate(temp);
        //statement.executeUpdate("INSERT INTO Users " + "VALUES ("+ newUser.userName + ", " + newUser.password + ", " + newUser.userType + ")");
        //conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
     
    }
    
    private boolean dropTable;
    //if dropTable == true
    //statement.executeUpdate(DROP_TABLE); should be uncommented 
    // OR 
    // the dropTable method should be called upon creation of the JDBC object.
    public JdbcSQLiteConnection () {
    	//dropTable == true on first run to clear the database/tables
    	//dropTable == false to keep existing info in database
    	dropTable = true;
    }
    
    public void dropTable() {
    	try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(DROP_TABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void createTable() {
    	try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(CREATE_TABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
