package padraoProjeto.cap3Decorator.bebida;

public class Expresso extends Beverage
{

	
	
	public Expresso()
	{
		super();
		this.descricao = "Expresso"; 
	}

	@Override
	public double custo()
	{
		// TODO Auto-generated method stub
		return 1.99;
	}

}
