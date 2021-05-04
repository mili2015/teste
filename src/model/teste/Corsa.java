package model.teste;

import model.teste.iFace.IAutomovel;

public class Corsa extends Carro implements IAutomovel
{

	public Corsa()
	{
		System.out.print("Corsa ");
	}

	public Integer algumMetodo(String modelo)
	{
		return new Integer("1");
	}
	
	public int qdePortas()
	{
		// TODO Auto-generated method stub
		return 2;
	}

	public String caracteristica()
	{
		return "riscado";
	}

	public int tamanhoRodas()
	{
		// TODO Auto-generated method stub
		return 14;
	}

	public String tecidoBanco()
	{
		// TODO Auto-generated method stub
		return "Pano Velho";
	}

	public String modelo()
	{
		// TODO Auto-generated method stub
		return "Corsa 95";
	}
}
