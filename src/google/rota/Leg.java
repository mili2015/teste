package google.rota;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Leg
{
	@SerializedName("start_address")
	private String startAddress;
	
	@SerializedName("end_address")
	private String endAddress;
	
	private Distance distance;
	
	/**
	 * @return the startAddress
	 */
	public String getStartAddress()
	{
		return startAddress;
	}

	/**
	 * @param startAddress the startAddress to set
	 */
	public void setStartAddress(String startAddress)
	{
		this.startAddress = startAddress;
	}

	/**
	 * @return the endAddress
	 */
	public String getEndAddress()
	{
		return endAddress;
	}

	/**
	 * @param endAddress the endAddress to set
	 */
	public void setEndAddress(String endAddress)
	{
		this.endAddress = endAddress;
	}

	/**
	 * @return the distance
	 */
	public Distance getDistance()
	{
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Distance distance)
	{
		this.distance = distance;
	}

}
