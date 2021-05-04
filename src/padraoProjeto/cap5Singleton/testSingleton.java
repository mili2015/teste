package padraoProjeto.cap5Singleton;

public class testSingleton
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		for(int a=0;a<15;a++)
		{
			int random = (int) (Math.random()*10);
			
			if(random%2==0)
				Singleton.getInstance("par");
			else
				Singleton.getInstance("impar");
			
			System.out.println(random+" - "+random%2);
			
		}

	}

}
