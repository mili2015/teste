package padraoProjeto.cap7Adapter;

public class Principal
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Duck p = new Pato();
		testDuck(p);
		
		Turkey peru = new Peru();
		peru.fly();
		peru.gobble();
		
		PeruAdapter pa = new PeruAdapter(peru);
		Turkey t = pa.getTurkey();
		t.fly();
		
		testDuck(pa);
	}
	
	static void testDuck(Duck d)
	{
		d.fly();
		d.quack();
	}

}
