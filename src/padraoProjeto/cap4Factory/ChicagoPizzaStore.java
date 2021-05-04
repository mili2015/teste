package padraoProjeto.cap4Factory;

public class ChicagoPizzaStore extends PizzaStore
{
	
	
	@Override
	public Pizza createPizza(String tipo)
	{
		if(tipo.equals("cheese"))
			return new ChicagoStyleCheesePizza();
		return null;
	}

}
