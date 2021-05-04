package padraoProjeto.cap8TemplateMethod;

import java.util.Scanner;

public class Cha extends Bebida
{

	@Override
	public void addCondiments()
	{
		System.out.println("adiciona lim�o no ch�");
	}

	@Override
	public void brew()
	{
		System.out.println("adicion o ch� na �gua");
	}
	
	/**
	 * sobrescrevendo o gancho da Bebida 
	 */
	@Override
	public boolean comCondimentos()
	{
		System.out.println("Adicionar condimento no ch� ?");
		Scanner sc = new Scanner(System.in);
		
		String condimentos = sc.next();
		
		if ( condimentos!=null && condimentos.equalsIgnoreCase("S"))
			return true;
		else
			return false;
	}

}
