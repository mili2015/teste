package padraoProjeto.cap1;

public abstract class Duck
{
	protected FlyBehavior flyBehavior;
	protected QuackBehavior quackBehavior;
	
	public Duck()
	{
		
	}
	
	public abstract void display();
	
	public void performFly()
	{
		flyBehavior.fly();
	}
	
	public void performQuack()
	{
		quackBehavior.quack();
	}
	
	public void swim()
	{
		System.out.println("All duck float,even decoys!");
	}

	public FlyBehavior getFlyBehavior()
	{
		return flyBehavior;
	}

	public void setFlyBehavior(FlyBehavior flyBehavior)
	{
		this.flyBehavior = flyBehavior;
	}

	public QuackBehavior getQuackBehavior()
	{
		return quackBehavior;
	}

	public void setQuackBehavior(QuackBehavior quackBehavior)
	{
		this.quackBehavior = quackBehavior;
	}
}
