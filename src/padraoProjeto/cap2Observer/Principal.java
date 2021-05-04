package padraoProjeto.cap2Observer;

public class Principal
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		WeatherData wd = new WeatherData();
		
		java.util.Observer ccd = new CurrentConditionsDisplay(wd);
		java.util.Observer fd = new ForecastDisplay(wd);
		
		wd.setMeasurements(28.5f, 29.7f);
//		wd.setMeasurements(22.5f, 25.7f);
	}

}
