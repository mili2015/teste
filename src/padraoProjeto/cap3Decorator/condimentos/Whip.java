package padraoProjeto.cap3Decorator.condimentos;

import padraoProjeto.cap3Decorator.bebida.Beverage;

public class Whip extends CondimentDecorator
{
	Beverage beverage;
	
	public Whip(Beverage beverage)
	{
		super();
		this.beverage = beverage;
	}

	@Override
	public String getDescricao()
	{
		// TODO Auto-generated method stub
		return this.beverage.getDescricao()+" + Whip ";
	}

	@Override
	public double custo()
	{
		return this.beverage.custo()+0.10;
	}

}
