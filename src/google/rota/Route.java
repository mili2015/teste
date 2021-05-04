package google.rota;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Route
{
	private String copyrights;
	
	private List<Leg> legs = new ArrayList<Leg>();

	
	
	public Route()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the legs
	 */
	public List<Leg> getLegs()
	{
		return legs;
	}

	/**
	 * @param legs the legs to set
	 */
	public void setLegs(List<Leg> legs)
	{
		this.legs = legs;
	}

	/**
	 * @return the copyrights
	 */
	public String getCopyrights()
	{
		return copyrights;
	}

	/**
	 * @param copyrights the copyrights to set
	 */
	public void setCopyrights(String copyrights)
	{
		this.copyrights = copyrights;
	}
}
