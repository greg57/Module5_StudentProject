package vzap.greg;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class MySQL_CityDAO
{
	private MySQL_Connection mySQL_Connection;
	private Connection connection;
	private Statement stmt;
	private ResultSet rs;
	private Vector<City> listOfCities;
	
	public MySQL_CityDAO( )
	{
		mySQL_Connection = new MySQL_Connection();
		connection = mySQL_Connection.getConnection();	
		listOfCities = new Vector<City>();
	}
	
	public int getNextCityID_Number()
	{
		int lastID = 0;
		try
		{
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select max(cityID) from citytable;");
			rs.next();
			lastID = rs.getInt(1);
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastID + 1;
	}
	
	public Vector<City> getCities( )
	{
		String query = "select * from citytable;";
		try
		{
			stmt = connection.createStatement();
			//Remember the result set comes back in a table form.
			//The pointer of the result table is before the first row.
			//the method rs.next() moves the pointer one row down.
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				City c;
				int cityID = rs.getInt(1);
				String cityName = rs.getString(2);
				c = new City(cityID, cityName);
				listOfCities.add(c);
			}
			stmt.close();
			rs.close();			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return listOfCities;
	}
	
	
	
	public City searchForCityByCityID(int cityID )
	{
		String query = "select * from citytable where cityID = " + cityID + ";";
		System.out.println("query = " + query);
		City c = null;
		try
		{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			int cityID1 = rs.getInt(1);
			String cityName = rs.getString(2);
			c = new City(cityID1, cityName);
			
			stmt.close();
			rs.close();
			return c;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}		
	}
	public boolean deleteCityByCityID(int cityID)
	{
		return false;
	}
	public boolean addNewCity(City newCity)
	{
		City temp = this.searchForCityByCityID(newCity.getCityID());
		if(temp != null)
		{
			return false;
		}
		String update = "insert into citytable values(" + newCity.getCityID() + 
				", '" + newCity.getCityName() + "');";
		try
		{
			stmt = connection.createStatement();
			int rowsInserted = stmt.executeUpdate(update);
			if(rowsInserted > 0)
			{
				stmt.close();
				return true;
			}
			else
			{
				stmt.close();
				return false;
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		
	} 
	public boolean editCityDetails()
	{
		return false;
	}
}
