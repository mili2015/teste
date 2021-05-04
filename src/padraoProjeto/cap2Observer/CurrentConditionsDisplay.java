package padraoProjeto.cap2Observer;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay implements Observer
{
	Observable observable;
	
	public CurrentConditionsDisplay(Observable o)
	{
		super();
		this.observable = o;
		o.addObserver(this);
	}

	public void update(Observable o, Object arg)
	{
		if (o instanceof WeatherData)
		{
			WeatherData wd = (WeatherData) o;
			
			System.out.println(" CurrentConditionsDisplay "+wd.getTemperature()+" - "+wd.getTemperature());
			
		}
			
	}

}
