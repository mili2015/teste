package model.teste;

public class Zookeeper
{
		public static void main(String[]args)
		{
			new Zookeeper().go();
		}
		
		void go()
		{
			Mammal m = new Zebra();
			System.out.println(m.name+m.makeNoise());
		}
}
