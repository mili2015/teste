package padraoProjeto.cap1;

public class CacadorPato
{
	QuackBehavior quackBehavior;

	
	
	public CacadorPato()
	{
		super();
		quackBehavior = new Quack();
	}

	public QuackBehavior getQuackBehavior()
	{
		return quackBehavior;
	}

	public void setQuackBehavior(QuackBehavior quackBehavior)
	{
		this.quackBehavior = quackBehavior;
	}
	
	public void imitaQuack()
	{
		quackBehavior.quack();
	}
}
