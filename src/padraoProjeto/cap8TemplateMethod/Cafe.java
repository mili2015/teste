package padraoProjeto.cap8TemplateMethod;

public class Cafe extends Bebida
{

	@Override
	public void addCondiments()
	{
		System.out.println("adiciona a�ucar no caf�");
		
	}

	@Override
	public void brew()
	{
		System.out.println("coloca o p� de caf� na �gua");
		
	}

}
