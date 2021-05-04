package model.teste.iFace;

/**
 * Uma interfaces pode herdar varias interfaces, mas não pode implementar nada
 * Autor  : Felipe Alves
 * Arquivo: IAutomovel.java
 * Data   : 27/09/2010
 * Hora   : 11:55:24
 */
public interface IAutomovel extends IRodas, IBanco
{
	public abstract int qdePortas();
	public abstract String modelo();
}
