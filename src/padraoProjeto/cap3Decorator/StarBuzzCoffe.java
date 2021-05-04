package padraoProjeto.cap3Decorator;

import padraoProjeto.cap3Decorator.bebida.Beverage;
import padraoProjeto.cap3Decorator.bebida.Expresso;
import padraoProjeto.cap3Decorator.bebida.HouseBlend;
import padraoProjeto.cap3Decorator.condimentos.Mocha;
import padraoProjeto.cap3Decorator.condimentos.Whip;

public class StarBuzzCoffe
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		Beverage expresso = new Expresso();
		expresso = new Mocha(expresso);
		System.out.println(expresso.getDescricao()+" - "+expresso.custo());
		
		Beverage house = new HouseBlend();
		house = new Mocha(house);
		house = new Mocha(house);
		house = new Whip(house);
		
		
		System.out.println(house.getDescricao()+" R$ "+house.custo());
	}

}
