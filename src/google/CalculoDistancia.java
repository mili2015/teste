package google;

import java.util.Arrays;
import java.util.List;

public class CalculoDistancia
{
	private double lat1, lng1, lat2, lng2;

	public CalculoDistancia(double lat1, double lng1, double lat2, double lng2)
	{
		super();
		this.lat1 = lat1;
		this.lng1 = lng1;
		this.lat2 = lat2;
		this.lng2 = lng2;
	}

	public double calcular()
	{
		//double earthRadius = 3958.75;//miles
	    double earthRadius = 6371;//kilometers
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLng = Math.toRadians(lng2 - lng1);
	    double sindLat = Math.sin(dLat / 2);
	    double sindLng = Math.sin(dLng / 2);
	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
	            * Math.cos(Math.toRadians(lat1))
	            * Math.cos(Math.toRadians(lat2));
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double dist = earthRadius * c;
	 
	    return dist ; //em km
	}

	public static void main(String[] args)
	{
		double lat1 = -9.7831719;
		double lng1 = -36.3471534;
		
		double[][] coordenadas = {{-9.7788092,-36.3577443}, {-9.7831719,-36.3471534}, {-9.901301,-36.2205894}, {-7.2535102,-39.3356624}, {-9.7851768,-36.3527138}};
		
		for (double[] ds : coordenadas)
		{
			CalculoDistancia c = new CalculoDistancia(lat1,lng1, ds[0],ds[1]);
			double distancia = c.calcular();
			
			System.out.println(ds[0] +"," + ds[1] + " | Distancia: " + distancia);	
		}
	}
	
	
}
