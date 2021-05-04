package padraoProjeto.cap7Adapter;

public class PeruAdapter implements Duck
{

	Turkey turkey= null;
	
	public Turkey getTurkey()
	{
		return turkey;
	}

	public void setTurkey(Turkey turkey)
	{
		this.turkey = turkey;
	}

	public PeruAdapter(Turkey turkey)
	{
		super();
		this.turkey = turkey;
	}

	public void fly()
	{
		turkey.fly();

	}

	public void quack()
	{
		turkey.gobble();
	}

}
