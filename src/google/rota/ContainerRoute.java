package google.rota;

import java.util.ArrayList;
import java.util.List;

public class ContainerRoute
{
	private String status;
	List<Route> routes = new ArrayList<Route>();

	/**
	 * @return the routes
	 */
	public List<Route> getRoutes()
	{
		return routes;
	}

	/**
	 * @param routes the routes to set
	 */
	public void setRoutes(List<Route> routes)
	{
		this.routes = routes;
	}

	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	
}
