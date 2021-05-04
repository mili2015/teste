package padraoProjeto.cap4Factory;

public class NYPizzaStore extends PizzaStore
{
	
	
	@Override
	public Pizza createPizza(String tipo)
	{
		if(tipo.equals("cheese"))
			return new NYStyleCheesePizza();
		return null;
	}

}
