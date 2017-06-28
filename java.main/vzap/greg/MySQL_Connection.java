package vzap.greg;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class MySQL_Connection
{
	private String username;
	private String password;
	private String databaseName;
	private String ipAddress;
	private String portNumber;
	private Connection connection;
	private String url;

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
	 * @return the user
	 */
	public String getUser()
	{
		return username;
	}

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName()
	{
		return databaseName;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection()
	{
		return connection;
	}

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

	public static void main(String[] args)
	{
		MySQL_Connection mySqlConn = new MySQL_Connection();
		mySqlConn.closeConnection();
	}

}
