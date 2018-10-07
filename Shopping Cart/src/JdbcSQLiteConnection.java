
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
  private String insert = "INSERT INTO UsersDb(Username, Password, Status) VALUES(?,?,?)";
  
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:UsersDb.db";
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
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
    	conn = DriverManager.getConnection(dbURL);
    	Statement statement = conn.createStatement();
      	statement.executeUpdate(insert);
        statement.executeUpdate("INSERT INTO UsersDb " + "VALUES ("+ newUser.userName + ", " + newUser.password + ", " + newUser.userType + ")");
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
     
    }
    
}
