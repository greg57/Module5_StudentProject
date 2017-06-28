package vzap.greg;

public class City
{
	private int cityID;
	private String cityName;
	
	public City(int cityID, String cityName)
	{
		this.cityID = cityID;
		this.cityName = cityName;
	}

	/**
	 * @return the cityID
	 */
	public int getCityID()
	{
		return cityID;
	}

	/**
	 * @param cityID the cityID to set
	 */
	public void setCityID(int cityID)
	{
		this.cityID = cityID;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName()
	{
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "City [cityID=" + cityID + ", cityName=" + cityName + "]";
	}
}
