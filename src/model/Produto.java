package model;

public class Produto
{
	String nrNfiscal = "123456";   
	String serie = "NFE-4";   
	String chaveAcesso = "123456789456123564987456123";   
	String codProduto = "aca.1.1.12";   
	String descProduto = "Pacu";   
	String valor = "1.90";   
	String obs = "por hoje é só";
	public String getNrNfiscal()
	{
		return nrNfiscal;
	}
	public void setNrNfiscal(String nrNfiscal)
	{
		this.nrNfiscal = nrNfiscal;
	}
	public String getSerie()
	{
		return serie;
	}
	public void setSerie(String serie)
	{
		this.serie = serie;
	}
	public String getChaveAcesso()
	{
		return chaveAcesso;
	}
	public void setChaveAcesso(String chaveAcesso)
	{
		this.chaveAcesso = chaveAcesso;
	}
	public String getCodProduto()
	{
		return codProduto;
	}
	public void setCodProduto(String codProduto)
	{
		this.codProduto = codProduto;
	}
	public String getDescProduto()
	{
		return descProduto;
	}
	public void setDescProduto(String descProduto)
	{
		this.descProduto = descProduto;
	}
	public String getValor()
	{
		return valor;
	}
	public void setValor(String valor)
	{
		this.valor = valor;
	}
	public String getObs()
	{
		return obs;
	}
	public void setObs(String obs)
	{
		this.obs = obs;
	}   
}
