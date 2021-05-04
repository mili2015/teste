package geral;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Paralelo
{
	private static final Executor exec = Executors.newFixedThreadPool(2);
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		final int[] data = UtilSerial.criaDados(64*1024*1024);
		
		CompletionService<Integer> compl = new ExecutorCompletionService<Integer>(exec);
		
		final long start = System.nanoTime();
		
		final int half = data.length/2;
		final Future<Integer> f1 = compl.submit(new Callable<Integer>(){
			public Integer call()
			{
				return UtilSerial.search(data, half, data.length);
			}
		});
		
		final Future<Integer> f2 = compl.submit(new Callable<Integer>(){
			public Integer call()
			{
				return UtilSerial.search(data, half, data.length);
			}
		});
		
		
		try
		{
			final int ret = Math.max(f1.get(), f2.get());
			
			final long time = System.nanoTime() - start;
			
			System.out.printf("Paralelo: %fms\n",time/1e6,ret);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
