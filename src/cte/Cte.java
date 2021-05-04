package cte;

public class Cte
{
	private String chaveAcesso;
	private String xml;
	/**
	 * @return the chaveAcesso
	 */
	public String getChaveAcesso()
	{
		return chaveAcesso;
	}
	/**
	 * @param chaveAcesso the chaveAcesso to set
	 */
	public void setChaveAcesso(String chaveAcesso)
	{
		this.chaveAcesso = chaveAcesso;
	}
	/**
	 * @return the xml
	 */
	public String getXml()
	{
		return xml;
	}
	/**
	 * @param xml the xml to set
	 */
	public void setXml(String xml)
	{
		this.xml = xml;
	}
	
	@Override
	public String toString()
	{
		return chaveAcesso;
	}
}
