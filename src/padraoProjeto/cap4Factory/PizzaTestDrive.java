package padraoProjeto.cap4Factory;

public class PizzaTestDrive
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PizzaStore ny = new NYPizzaStore();
		Pizza p = ny.orderPizza("cheese");
		System.out.println("vou comer pizza de "+p.name);
		System.out.println("\n ================= \n");
		
		PizzaStore chicago = new ChicagoPizzaStore();
		Pizza p2 = chicago.orderPizza("cheese");
		System.out.println("Ele comer pizza de "+p2.name);

	}

}
