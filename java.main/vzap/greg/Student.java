package vzap.greg;

// TODO: Auto-generated Javadoc
/**
 * The Class Student.
 */
public class Student
{
	
	/** The student ID. */
	private int studentID;
	
	/** The first name. */
	private String firstName;
	
	/** The surname. */
	private String surname;
	
	/** The salary. */
	private double salary;
	
	/** The city ID. */
	private int cityID;
	
	/** The active. */
	private char active;
	
	/**
	 * Instantiates a new student.
	 *
	 * @param studentID the student ID
	 * @param firstName the first name
	 * @param surname the surname
	 * @param salary the salary
	 * @param cityID the city ID
	 */
	public Student(int studentID, String firstName, String surname,
			double salary, int cityID)
	{
		super();
		this.studentID = studentID;
		this.firstName = firstName;
		this.surname = surname;
		this.salary = salary;
		this.cityID = cityID;
		this.active = 'y';
	}
	
	/**
	 * Gets the student ID.
	 *
	 * @return the studentID
	 */
	public int getStudentID()
	{
		return studentID;
	}
	
	/**
	 * Sets the student ID.
	 *
	 * @param studentID the studentID to set
	 */
	public void setStudentID(int studentID)
	{
		this.studentID = studentID;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname()
	{
		return surname;
	}
	
	/**
	 * Sets the surname.
	 *
	 * @param surname the surname to set
	 */
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	/**
	 * Gets the salary.
	 *
	 * @return the salary
	 */
	public double getSalary()
	{
		return salary;
	}
	
	/**
	 * Sets the salary.
	 *
	 * @param salary the salary to set
	 */
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	
	/**
	 * Gets the city ID.
	 *
	 * @return the cityID
	 */
	public int getCityID()
	{
		return cityID;
	}
	
	/**
	 * Sets the city ID.
	 *
	 * @param cityID the cityID to set
	 */
	public void setCityID(int cityID)
	{
		this.cityID = cityID;
	}
	
	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public char getActive()
	{
		return active;
	}
	
	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(char active)
	{
		this.active = active;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StandardBankStudent [studentID=" + studentID + ", firstName="
				+ firstName + ", surname=" + surname + ", salary=" + salary
				+ ", cityID=" + cityID + "]";
	}
	
}
