package padraoProjeto.cap2Observer;

import java.util.Observable;

public class WeatherData extends Observable
{

	private float temperature;
	private float pressure;
	
	
	public WeatherData()
	{
	}

	public void meassurementsChanged()
	{
		
		setChanged();//tem que chamar antes para indicar que o status alterou
		notifyObservers();
	}
	
	public void setMeasurements(float temperature,float pressure)
	{
		this.temperature = temperature;
		this.pressure = pressure;
		meassurementsChanged();
	}

	public float getTemperature()
	{
		return temperature;
	}

	public void setTemperature(float temperature)
	{
		this.temperature = temperature;
	}

	public float getPressure()
	{
		return pressure;
	}

	public void setPressure(float pressure)
	{
		this.pressure = pressure;
	}

	
}
