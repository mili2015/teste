package controller;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author felipe.alves
 *
 */
@XmlRootElement
public class Contato
{
	
	private int id = 1;
	
	private String xmlns;
	
	private String nome;

	private String sexo;

	private int idade;

	private Endereco endereco;

	public Contato()
	{

	}

	public Contato(String nome, String sexo, int idade,	Endereco endereco)
	{

		this.nome = nome;

		this.sexo = sexo;

		this.idade = idade;

		this.endereco = endereco;

	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getSexo()
	{
		return sexo;
	}

	public void setSexo(String sexo)
	{
		this.sexo = sexo;
	}

	public int getIdade()
	{
		return idade;
	}

	public void setIdade(int idade)
	{
		this.idade = idade;
	}

	public Endereco getEndereco()
	{
		return endereco;
	}

	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}
	
	@XmlAttribute
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@XmlAttribute
	public String getXmlns()
	{
		return xmlns;
	}

	public void setXmlns(String xmlns)
	{
		this.xmlns = xmlns;
	}


}
