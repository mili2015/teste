package padraoProjeto.cap4Factory;

public abstract class PizzaStore extends Pizza
{

	public Pizza orderPizza(String tipo)
	{
		Pizza p = createPizza(tipo);
		
		p.prepare();
		p.bake();
		p.cut();
		p.box();
		
		return p;
	}
	
	public abstract Pizza createPizza(String tipo);
}
