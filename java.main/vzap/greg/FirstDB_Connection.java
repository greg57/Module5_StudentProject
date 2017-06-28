package vzap.greg;

import java.sql.*;

public class FirstDB_Connection
{

	public static void main(String[] args)
	{
		// Register the Driver class with the DriverManager
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver registered with driver manager..>>");

			String username = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/standardbankstudents";
			Connection conn = DriverManager.getConnection(url, username,
					password);
			System.out.println("Connected to database");

			// talk to the standardbankstudents database.
			// I want to get some information from the student table
			String query = " select studentID, firstName, surname, salary, cityName from " +
							"student, cityTable where student.cityID = citytable.cityID;";
			System.out.println("query = " + query);

			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery(query);
			// when you get back a ResultSet the pointer sits BEFORE the first
			// row
			while (rs.next())
			{
				System.out.println("Student ID: " + rs.getInt(1));
				System.out.println("First Name: " + rs.getString("firstname"));
				System.out.println("Surname: " + rs.getString(3));
				System.out.println("Salary: " + rs.getDouble(4));
				System.out.println("City Name: " + rs.getString(5));
				System.out.println("--------------------------------------");
			}
			
			rs.absolute(3);
			System.out.println("Row 3 ");
			System.out.println("Student ID: " + rs.getInt(1));
			System.out.println("First Name: " + rs.getString("firstname"));
			
			rs.last();
			System.out.println("index = " + rs.getRow());
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			System.out.println("\n\nname of col 1 = " + rsmd.getColumnName(1));
			System.out.println("data type of col 1 = " + rsmd.getColumnTypeName(1));
			
//			String insertSQL = "insert into citytable values(5, 'Alberton');";
//			int rowsAffected = stmt.executeUpdate(insertSQL);
//			System.out.println("Rows affected in update = " + rowsAffected);
			
			conn.close();
			System.out.println("Database connection closed..>>>");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
