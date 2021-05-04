package geral;

import java.util.Random;

public class Serial
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] dados = UtilSerial.criaDados(64*1024*1024);
		
		long time = System.nanoTime();
		
		int max = UtilSerial.search(dados, 0, dados.length);
		
		time = System.nanoTime() - time;
		
		System.out.printf("Serial:%fms\n",time/1e6,max);

	}
}

class UtilSerial
{
	public static int[] criaDados(final int size)
	{
		int[] dados = new int[size];
		
		Random md = new Random(0);
		
		for(int a=0;a<dados.length;++a)
			dados[a] = md.nextInt();
		
		return dados;
	}
	
	public static int search (int[] dados,int from, int to)
	{
		int max = Integer.MIN_VALUE;
		
		for(int a=from;a<to;++a)
			if(dados[a]>max)
				max=dados[a];
		
		return max;
	}
}
