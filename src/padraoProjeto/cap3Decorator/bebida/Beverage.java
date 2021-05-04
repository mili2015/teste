package padraoProjeto.cap3Decorator.bebida;

public abstract class Beverage
{
	String descricao = "desconhecido";

	public String getDescricao()
	{
		return descricao;
	}

	public abstract double custo();
}
