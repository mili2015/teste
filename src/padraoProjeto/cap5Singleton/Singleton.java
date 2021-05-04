package padraoProjeto.cap5Singleton;

public class Singleton
{
	private static Singleton uniqueInstance = new Singleton();
	
	private Singleton(){}
	
	public static Singleton getInstance(String valor)
	{
		System.out.println("new Singleton "+valor+"\t"+uniqueInstance);
		
		return uniqueInstance;
	}
	
}
