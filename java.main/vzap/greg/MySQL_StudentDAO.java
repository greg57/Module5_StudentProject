package vzap.greg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class MySQL_StudentDAO
{
	private MySQL_Connection mySQL_Connection;
	private Connection connection;
	private Statement stmt;
	private ResultSet rs;
	private Vector<Student> listOfStudents;
	
	public MySQL_StudentDAO( )
	{
		mySQL_Connection = new MySQL_Connection();
		connection = mySQL_Connection.getConnection();	
		listOfStudents = new Vector<Student>();
	}
	public int getNextStudentID_Number()
	{
		int lastID = 0;
		try
		{
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select max(studentID) from student;");
			rs.next();
			lastID = rs.getInt(1);
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastID;
	}
	
	public Vector<Student> getAllActiveStudents()
	{
		String query = "select * from student where active like 'y';";
		try
		{
			stmt = connection.createStatement();
			//Remember the result set comes back in a table form.
			//The pointer of the result table is before the first row.
			//the method rs.next() moves the pointer one row down.
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				Student s;
				int studentID = rs.getInt(1);
				String firstname = rs.getString(2);
				String surname = rs.getString(3);
				double salary = rs.getDouble(4);
				int cityID = rs.getInt(5);
				s = new Student(studentID, firstname, surname, salary, cityID);
				listOfStudents.add(s);
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
		return listOfStudents;
	}
	public Vector<Student> getAllStudents( )
	{
		String query = "select * from student;";
		try
		{
			stmt = connection.createStatement();
			//Remember the result set comes back in a table form.
			//The pointer of the result table is before the first row.
			//the method rs.next() moves the pointer one row down.
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				Student s;
				int studentID = rs.getInt(1);
				String firstname = rs.getString(2);
				String surname = rs.getString(3);
				double salary = rs.getDouble(4);
				int cityID = rs.getInt(5);
				s = new Student(studentID, firstname, surname, salary, cityID);
				listOfStudents.add(s);
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
		return listOfStudents;
	}
	
	public Student searchForStudentByStudentID(int studentID )
	{
		String query = "select * from student where studentID = " + studentID + ";";
		System.out.println("query = " + query);
		Student s = null;
		try
		{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			int stdID = rs.getInt(1);
			String firstname = rs.getString(2);
			String surname = rs.getString(3);
			double salary = rs.getDouble(4);
			int cityID = rs.getInt(5);
			s = new Student(stdID, firstname, surname, salary, cityID);
			stmt.close();
			rs.close();
			return s;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}		
	}
	public boolean deleteStudentByStudentID(int studentID)
	{
		return false;
	}
	public boolean addNewStudent(Student newStudent)
	{
		Student temp = this.searchForStudentByStudentID(newStudent.getStudentID());
		if(temp != null)
		{
			return false;
		}
		String update = "insert into student values(" + newStudent.getStudentID() + 
				", '" + newStudent.getFirstName() + "', '" + newStudent.getSurname() + "'," +
				newStudent.getSalary() + ", " + newStudent.getStudentID() + ", 'y');";
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
	public boolean editStudentDetails( Student editStudent)
	{
		
		String update = "update student set firstName = '" +  editStudent.getFirstName() + 
				"', surname = '" +	editStudent.getSurname() + "',salary = " +
				editStudent.getSalary() + ", cityID = " + editStudent.getCityID() + 
				", active = " + editStudent.getActive() + ";";
		System.out.println("update = " + update);
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
}
