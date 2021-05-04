package padraoProjeto.cap4Factory;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza
{
	String name;
	String dough;
	String sauce;
	List<String> toppings = new ArrayList<String>();
	
	void prepare()
	{
		System.out.println("Preparing "+name);
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding toppings...");
		
		for(String t:toppings)
			System.out.println(" \t "+t);
	}
	
	void bake()
	{
		System.out.println("Bake for 25 minutes at 350º");
	}
	
	void cut()
	{
		System.out.println("Cuttin the pizza in diagonal slices");
	}
	
	void box()
	{
		System.out.println("Place pizza in official PizzaStore Box ");
	}
	
	
}
