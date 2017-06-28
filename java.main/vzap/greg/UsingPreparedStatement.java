package vzap.greg;

import java.sql.*;

public class UsingPreparedStatement
{
	private MySQL_Connection mysqlConn;
	private Connection conn;
	private PreparedStatement prpStmt, prpStmt2;
	private ResultSet rs;
	
	public UsingPreparedStatement()
	{
		mysqlConn = new MySQL_Connection();
		conn = mysqlConn.getConnection();
		String update = "insert into citytable values(?, ?);";
		String query = "select cityname as 'City Name' from citytable where cityID = ?;";
		try
		{
			prpStmt = conn.prepareStatement(update);
			prpStmt2 = conn.prepareStatement(query);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public City findCityByCityID(int cityID)
	{
		try
		{
			prpStmt2.setInt(1, cityID);
			rs = prpStmt2.executeQuery();
			rs.next();
			String cityName = rs.getString(1);
			City c = new City(cityID, cityName);
			return c;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public boolean addCity(City newCity)
	{
		int cityID = newCity.getCityID();
		String cityName = newCity.getCityName();
		try
		{
			prpStmt.setInt(1, cityID);
			prpStmt.setString(2, cityName);
			
			int rowsInserted = prpStmt.executeUpdate();
			if(rowsInserted > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	
	public void closeConnection()
	{
		mysqlConn.closeConnection();
	}
	public static void main(String[] args)
	{
		UsingPreparedStatement ups = new UsingPreparedStatement();
		City cityFound = ups.findCityByCityID(16);
		System.out.println("City Found: city ID = " + cityFound.getCityID() + 
				" city name = " + cityFound.getCityName());
//		City c1 = new City(14, "Musina");
//		City c2 = new City(15, "Richards Bay");
//		City c3 = new City(16, "Hazeyview");
//		boolean addedCity = ups.addCity(c1);
//		
//		if(addedCity == true)
//		{
//			System.out.println("New City Added...>>>");
//		}
//		else
//		{
//			System.out.println("New City NOT added...>>>>");
//		}
//		ups.addCity(c2);
//		ups.addCity(c3);
		
		
		
		ups.closeConnection();
	}
}
