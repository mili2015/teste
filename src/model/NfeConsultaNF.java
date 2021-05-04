package model;

public abstract class NfeConsultaNF
{
	private String versao;
	private String dados;
	
	
	
	public String getDados()
	{
		return dados;
	}
	public void setDados(String dados)
	{
		if (dados != null)
			this.dados = dados.toUpperCase().trim();
	}
	public String getVersao()
	{
		return versao;
	}
	public void setVersao(String versao)
	{
		if (versao != null)
			this.versao = versao.toUpperCase().trim();
	}
}
