package model.teste;

public class ComingThru
{
	static String s="-";

	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		A[] aa = new A[2];
		B[] ba = new B[2];
		sifter(aa);
		sifter(2);
		
		System.out.println(s);
	}
	
	static void sifter(A[]...a2)
	{
		s+="1";
	}
	
	static void sifter(Object a2)
	{
		s+="4";
	}

}
