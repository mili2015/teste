package padraoProjeto.cap8TemplateMethod;

import java.util.Scanner;

public class Cha extends Bebida
{

	@Override
	public void addCondiments()
	{
		System.out.println("adiciona limão no chá");
	}

	@Override
	public void brew()
	{
		System.out.println("adicion o chá na água");
	}
	
	/**
	 * sobrescrevendo o gancho da Bebida 
	 */
	@Override
	public boolean comCondimentos()
	{
		System.out.println("Adicionar condimento no chá ?");
		Scanner sc = new Scanner(System.in);
		
		String condimentos = sc.next();
		
		if ( condimentos!=null && condimentos.equalsIgnoreCase("S"))
			return true;
		else
			return false;
	}

}
