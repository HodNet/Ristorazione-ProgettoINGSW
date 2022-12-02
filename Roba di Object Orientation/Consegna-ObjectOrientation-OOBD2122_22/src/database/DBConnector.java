package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 
 * Singleton
 */
public class DBConnector {
	private static DBConnector istanza = null;
	private static Connection connection = null;
	private final static String driver = "org.postgresql.Driver";
	
	private DBConnector(String jdbcURL, String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		
		connection = DriverManager.getConnection(jdbcURL, username, password);
		System.out.println("Successfully connected to PostgreSQL server");
	}
	
	public static DBConnector getIstance(String jdbcURL, String username, String password) throws ClassNotFoundException, SQLException {
		if (istanza == null)
			istanza = new DBConnector(jdbcURL, username, password);
		return istanza;
	}

	public static Connection getConnection() {
		return connection;
	}

	public static String getDriver() {
		return driver;
	}
}
