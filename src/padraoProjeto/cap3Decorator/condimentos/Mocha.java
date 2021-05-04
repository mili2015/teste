package padraoProjeto.cap3Decorator.condimentos;

import padraoProjeto.cap3Decorator.bebida.Beverage;

public class Mocha extends CondimentDecorator
{
	Beverage beverage;
	
	public Mocha(Beverage beverage)
	{
		this.beverage = beverage;
	}

	@Override
	public String getDescricao()
	{
		// TODO Auto-generated method stub
		return this.beverage.getDescricao()+" + Mocha ";
	}

	@Override
	public double custo()
	{		
		return this.beverage.custo()+0.20;
	}

}
