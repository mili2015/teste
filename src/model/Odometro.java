package model;

import java.util.Date;

public class Odometro
{
	private int idMili;
	
	private int codVendedor;
	private String data1;
	private String data2;
	private long km1;
	private long km2;
	private long total;
	private String contentType1;
	private String contentType2;
	
	private String nomeImagem1;
	private String nomeImagem2;
	
	private Integer length1;
	private Integer length2;
	
	private byte[] imagem1;
	private byte[] imagem2;
	
	private String dtCadastro;

	/**
	 * @return the idMili
	 */
	public int getIdMili()
	{
		return idMili;
	}

	/**
	 * @param idMili the idMili to set
	 */
	public void setIdMili(int idMili)
	{
		this.idMili = idMili;
	
	}

	/**
	 * @return the data1
	 */
	public String getData1()
	{
		return data1;
	}

	/**
	 * @param data1 the data1 to set
	 */
	public void setData1(String data1)
	{
		this.data1 = data1;
	
	}

	/**
	 * @return the data2
	 */
	public String getData2()
	{
		return data2;
	}

	/**
	 * @param data2 the data2 to set
	 */
	public void setData2(String data2)
	{
		this.data2 = data2;
	
	}

	/**
	 * @return the km1
	 */
	public long getKm1()
	{
		return km1;
	}

	/**
	 * @param km1 the km1 to set
	 */
	public void setKm1(long km1)
	{
		this.km1 = km1;
	
	}

	/**
	 * @return the km2
	 */
	public long getKm2()
	{
		return km2;
	}

	/**
	 * @param km2 the km2 to set
	 */
	public void setKm2(long km2)
	{
		this.km2 = km2;
	
	}

	/**
	 * @return the contentType1
	 */
	public String getContentType1()
	{
		return contentType1;
	}

	/**
	 * @param contentType1 the contentType1 to set
	 */
	public void setContentType1(String contentType1)
	{
		this.contentType1 = contentType1;
	
	}

	/**
	 * @return the contentType2
	 */
	public String getContentType2()
	{
		return contentType2;
	}

	/**
	 * @param contentType2 the contentType2 to set
	 */
	public void setContentType2(String contentType2)
	{
		this.contentType2 = contentType2;
	
	}

	/**
	 * @return the nomeImagem1
	 */
	public String getNomeImagem1()
	{
		return nomeImagem1;
	}

	/**
	 * @param nomeImagem1 the nomeImagem1 to set
	 */
	public void setNomeImagem1(String nomeImagem1)
	{
		this.nomeImagem1 = nomeImagem1;
	
	}

	/**
	 * @return the nomeImagem2
	 */
	public String getNomeImagem2()
	{
		return nomeImagem2;
	}

	/**
	 * @param nomeImagem2 the nomeImagem2 to set
	 */
	public void setNomeImagem2(String nomeImagem2)
	{
		this.nomeImagem2 = nomeImagem2;
	
	}

	/**
	 * @return the length1
	 */
	public Integer getLength1()
	{
		return length1;
	}

	/**
	 * @param length1 the length1 to set
	 */
	public void setLength1(Integer length1)
	{
		this.length1 = length1;
	
	}

	/**
	 * @return the length2
	 */
	public Integer getLength2()
	{
		return length2;
	}

	/**
	 * @param length2 the length2 to set
	 */
	public void setLength2(Integer length2)
	{
		this.length2 = length2;
	
	}

	/**
	 * @return the dtCadastro
	 */
	public String getDtCadastro()
	{
		return dtCadastro;
	}

	/**
	 * @param dtCadastro the dtCadastro to set
	 */
	public void setDtCadastro(String dtCadastro)
	{
		this.dtCadastro = dtCadastro;
	
	}

	/**
	 * @return the total
	 */
	public long getTotal()
	{
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(long total)
	{
		this.total = total;
	
	}
	
	public String getWarning()
	{
		if(total > 300 )
			return " color: #FF0000;";
		return "";
	}

	/**
	 * @return the codVendedor
	 */
	public int getCodVendedor()
	{
		return codVendedor;
	}

	/**
	 * @param codVendedor the codVendedor to set
	 */
	public void setCodVendedor(int codVendedor)
	{
		this.codVendedor = codVendedor;
	}

	/**
	 * @return the imagem1
	 */
	public byte[] getImagem1()
	{
		return imagem1;
	}

	/**
	 * @param imagem1 the imagem1 to set
	 */
	public void setImagem1(byte[] imagem1)
	{
		this.imagem1 = imagem1;
	}

	/**
	 * @return the imagem2
	 */
	public byte[] getImagem2()
	{
		return imagem2;
	}

	/**
	 * @param imagem2 the imagem2 to set
	 */
	public void setImagem2(byte[] imagem2)
	{
		this.imagem2 = imagem2;
	}
	
}
