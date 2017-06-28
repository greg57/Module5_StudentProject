package vzap.greg;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class MySQL_Connection.
 */
public class MySQL_Connection
{
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The database name. */
	private String databaseName;
	
	/** The ip address. */
	private String ipAddress;
	
	/** The port number. */
	private String portNumber;
	
	/** The connection. */
	private Connection connection;
	
	/** The url. */
	private String url;

	/**
	 * Instantiates a new my SQ L connection.
	 */
	public MySQL_Connection()// constructor
	{
		Properties dbProperties = new Properties();
		try
		{
			FileInputStream input = new FileInputStream("./resources/dbConfig.properties");

			// load a properties file
			dbProperties.load(input);
			input.close();

			this.username = dbProperties.getProperty("username");
			this.password = dbProperties.getProperty("password");
			this.databaseName = dbProperties.getProperty("databasename");
			this.ipAddress = dbProperties.getProperty("ipAddress");
			this.portNumber = dbProperties.getProperty("portnumber");
			url = "jdbc:mysql://" + this.ipAddress + ":" + this.portNumber + "/" + this.databaseName;
			System.out.println("url = " + url);

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver class found..>>>>>");

			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Made Connection...>>>>>");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser()
	{
		return username;
	}
	
	/**
	 * Gets the database name.
	 *
	 * @return the database name
	 */
	public String getDatabaseName()
	{
		return databaseName;
	}
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection()
	{
		return connection;
	}

	/**
	 * Close connection.
	 *
	 * @return true, if successful
	 */
	public boolean closeConnection()
	{
		if (connection != null)
		{
			try
			{
				connection.close();
				System.out.println("Connection closed..>>>");
				return true;
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		MySQL_Connection mySqlConn = new MySQL_Connection();
		mySqlConn.closeConnection();
	}

}
