package google;

import google.rota.ContainerRoute;
import google.rota.Distance;
import google.rota.Leg;
import google.rota.Route;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dao.CargaDao;



public class TestGoogleDirection
{

	private double obterDistancia(String address)
	{
		Gson gson = new Gson();
		double distanciaTotal = 0;
		try
		{
			URL url = new URL(address);
			InputStreamReader reader = new InputStreamReader(url.openStream());
			
			ContainerRoute cr = new ContainerRoute();
			
			cr = gson.fromJson(reader, ContainerRoute.class);
			
			System.out.println("Legs: "+cr.getRoutes().size()+" "+cr.getStatus());
			
			
			
			for (Route r : cr.getRoutes())
			{
				System.out.println(r.getCopyrights());
				for (Leg leg : r.getLegs())
				{
					System.out.println("De "+leg.getStartAddress()+" para "+leg.getEndAddress()+" -> "+leg.getDistance().getText());
					distanciaTotal += leg.getDistance().valueToDouble();
				}
			}
			
			System.out.println("Total: "+distanciaTotal);
			
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return distanciaTotal;
	}
	
	/**
	 * 
	 */
	private static void testGson()
	{
		Gson gson = new Gson();
		
		Route r = new Route();
		r.setCopyrights("Felipe Alves");
		
		Leg l = new Leg();
		l.setEndAddress("Endereco fim");
		l.setStartAddress("Endereco Inicio");
		
		Distance d = new Distance();
		d.setText("30,1 km");
//		d.setValue(30);
		
		l.setDistance(d);
		
		r.getLegs().add(l);
		
		List<Route> lista = new ArrayList<Route>();
		lista.add(r);
//		lista.add(r);
		
		ContainerRoute cr = new ContainerRoute();
		cr.setRoutes(lista);
		
		String json = gson.toJson(cr);
		
		System.out.println(json);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		CargaDao cargaDao = new CargaDao();
		try
		{
			int nrCarga = 337807;
			
			List<String> cidades = cargaDao.findCidades(nrCarga);
			StringBuilder wayPoints = new StringBuilder();
			
			for (int i = 0; i < cidades.size(); i++)
			{
				String cid = cidades.get(i);
				
				if(i == (cidades.size() - 1))
					cid = cid.replace("|", "");
				
				wayPoints.append(cid);
			}
			
			Leg leg = cargaDao.findOrigemDestino(nrCarga);
			
			StringBuilder url = new StringBuilder();
			url.append("http://maps.googleapis.com/maps/api/directions/json?");
			url.append("origin=");
			url.append(leg.getStartAddress());
			url.append("&destination=");
			url.append(leg.getEndAddress());
			if(cidades.size() > 1)
			{
				url.append("&waypoints=optimize:true|");
				url.append(wayPoints.toString());
			}
			url.append("&sensor=false");
			
//		String url = "http://maps.googleapis.com/maps/api/directions/json?origin=Vitoria,es&destination=Cariacica,es&waypoints=optimize:true|Carapina,es|Viana,es|Jucu,es|Laranjeiras,es&sensor=false";
			
			System.out.println(url.toString().length()+" "+url.toString());
			
			TestGoogleDirection t = new TestGoogleDirection();
			double distancia = t.obterDistancia(url.toString());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

}
