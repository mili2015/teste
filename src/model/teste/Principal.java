package model.teste;

import java.util.ArrayList;
import java.util.List;

import model.teste.iFace.IAutomovel;

public class Principal
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		Carro corsa = null;
		
		corsa =  new Corsa();
		
		//Carro carro = new Corsa();
		
//		Carro b = new Corsa();
//		System.out.println(b.algumMetodo("mpfi"));
//		
		if (corsa instanceof Corsa)
		{
			Corsa new_name = (Corsa) corsa;
			System.out.println("Carro é um corsa");
		}
		else 
		{
			System.out.println("Carro nao é um corsa");
		}
		
		//new Principal().go();
		
//		Palio a = (Palio) b;
//		System.out.println(" 2: "+a.qdePortas());

	}
	
	void go()
	{
		Mammal m = new Zebra();
		System.out.println(m.name+m.makeNoise());
		
		Carro b = new Corsa();
		System.out.println(b.algumMetodo("blz"));
	}
}
