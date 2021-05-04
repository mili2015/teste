package padraoProjeto.Builder;

public class TestBuilder
{
	public static void main(String[] args)
	{
		InformacoesNutricionais info = new InformacoesNutricionais.Builder(200,8)
		.calorias(100)
		.gorduraTotal(30)
		.gorduraSaturada(50).gorduraTrans(100).sodio(35).colesterol(100).build();
		
		
		System.out.println(info.toString());
	}

}
