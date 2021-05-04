package reflection;

import java.lang.reflect.Method;

public class ReflectionTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			Class c = Class.forName("java.lang.String");
			Method[] ms = c.getMethods();
			for(Method m:ms)
			{
				System.out.println(m.toString());
			}
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
