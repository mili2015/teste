package model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Pessoa")
public class Pessoa implements Serializable
{
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int idade;
	private String nome;
	private String cpf;

	

	/**
	 * 
	 */
	public Pessoa()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param nome
	 */
	public Pessoa(String nome)
	{
		super();
		this.nome = nome;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		if (nome != null)
			this.nome = nome.toUpperCase().trim();
	}
	public String getCpf()
	{
		return cpf;
	}
	public void setCpf(String cpf)
	{
		if (cpf != null)
			this.cpf = cpf.toUpperCase().trim();
	}
	public int getIdade()
	{
		return idade;
	}
	public void setIdade(int idade)
	{
		this.idade = idade;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null) 
			return false;
		
		if (o == this)
			return true;
		
		if (!(o instanceof Pessoa))
			return false;
		
		Pessoa p = (Pessoa) o;
			
		return p.getNome().equals(nome);
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		
		return result;
	}
	
	@Override
	public String toString()
	{
		return nome;
	}
}
