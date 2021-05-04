package padraoProjeto.cap8TemplateMethod;

public abstract class Bebida
{

	public final void prepararBebida()
	{
		boilWater();
		
		brew();
		
		pourInCup();
		
		if(comCondimentos())
			addCondiments();
	}

	public abstract void addCondiments();
	public abstract void brew();

	private void pourInCup()
	{
		System.out.println("coloca a bebida na xícara");
	}

	private void boilWater()
	{
		System.out.println("ferve a água");
	}

	/**
	 * Gancho
	 * @return
	 */
	public boolean comCondimentos()
	{
		return true;
	}
	
}
