package padraoProjeto.cap8TemplateMethod;

public class Cafe extends Bebida
{

	@Override
	public void addCondiments()
	{
		System.out.println("adiciona açucar no café");
		
	}

	@Override
	public void brew()
	{
		System.out.println("coloca o pó de café na água");
		
	}

}
