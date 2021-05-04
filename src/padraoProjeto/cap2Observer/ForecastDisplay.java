package padraoProjeto.cap2Observer;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer
{
	Observable obs;
	
	public ForecastDisplay(Observable obs)
	{
		super();
		this.obs = obs;
		this.obs.addObserver(this);
	}

	public void update(Observable o, Object arg)
	{
		if (o instanceof WeatherData)
		{
			WeatherData wd = (WeatherData) o;
			System.out.println("ForecastDisplay "+wd.getTemperature() +" - "+wd.getPressure());
		}
		
	}

}
