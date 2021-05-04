package padraoProjeto.Builder;

public class InformacoesNutricionais
{
	private final int tamanhoPorcao;
	private final int porcaoTotalDaEmbalagem;
	private final int calorias;
	private final int gorduraTotal;
	private final int gorduraSaturada;
	private final int gorduraTrans;
	private final int sodio;
	private final int colesterol;
	
	
	/**
	 * 
	 */
	public InformacoesNutricionais(Builder builder)
	{
		super();
		tamanhoPorcao = builder.tamanhoPorcao;
		porcaoTotalDaEmbalagem = builder.porcaoTotalDaEmbalagem;
		calorias = builder.calorias;
		gorduraTotal = builder.gorduraTotal;
		gorduraSaturada = builder.gorduraSaturada;
		gorduraTrans = builder.gorduraTrans;
		sodio = builder.sodio;
		colesterol = builder.colesterol;
	}


	public String toString()
	{
		return "info: tamanhoPorcao "+tamanhoPorcao;
	}


	public static class Builder {
		//Parametros Obrigatórios
		private final int tamanhoPorcao;
		private final int porcaoTotalDaEmbalagem; 

		//Parametros Opcionais
		private int calorias;
		private int gorduraTotal;
		private int gorduraSaturada;
		private int gorduraTrans;
		private int sodio;
		private int colesterol;

		public Builder(int tamanhoPorcao, int porcaoTotalDaEmbalagem) {
			this.tamanhoPorcao = tamanhoPorcao;
			this.porcaoTotalDaEmbalagem = porcaoTotalDaEmbalagem;
		}
		
		public Builder calorias(int val) {
			calorias = val;
			return this;
		}
		
		public Builder gorduraTotal(int val) {
			gorduraTotal = val;
			return this;
		}

		public Builder gorduraSaturada(int val) {
			gorduraSaturada = val;
			return this;
		}

		public Builder gorduraTrans(int val) {
			gorduraTrans = val;
			return this;
		}

		public Builder sodio(int val) {
			sodio = val;
			return this;
		}

		public Builder colesterol(int val) {
			colesterol = val;
			return this;
		}
		
		public InformacoesNutricionais build()
		{
			return new InformacoesNutricionais(this);
		}
	}
}
