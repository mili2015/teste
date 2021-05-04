package model.teste;

import model.teste.iFace.IAutomovel;
import model.teste.iFace.ITurbo;
/**
 * Uma classe pode implementar VÁRIAS interfaces, mas só pode herdar UMA classe
 * Autor  : Felipe Alves
 * Arquivo: Palio.java
 * Data   : 27/09/2010
 * Hora   : 11:56:13
 */
public class Palio implements IAutomovel, ITurbo
{

	public Palio()
	{
		System.out.print("Palio ");
	}

	public int qdePortas()
	{
		// TODO Auto-generated method stub
		return 4;
	}
	
	public String meuPalioEh()
	{
		return "do surf";
	}

	public int tamanhoRodas()
	{
		// TODO Auto-generated method stub
		return 17;
	}

	public String tecidoBanco()
	{
		// TODO Auto-generated method stub
		return "Couro";
	}

	public float forcaTurbo()
	{
		// TODO Auto-generated method stub
		return 2.5f;
	}

	public String modelo()
	{
		// TODO Auto-generated method stub
		return "Palio 2007";
	}

}
