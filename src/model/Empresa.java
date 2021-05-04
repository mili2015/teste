package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


public  class Empresa
{
	private String nome;
	private String endereco;
	private String telefone;
	private String cidade;
	private String dono;
	
	private Collection<Pessoa> funcionario = new ArrayList<Pessoa>();
	
	public Empresa()
	{
	};
	
	public Empresa(String nome)
	{
		this();
		this.nome = nome;
	}
	
	public Empresa(String nome, Collection<Pessoa> funcionario)
	{
		super();
		this.nome = nome;
		this.funcionario = funcionario;
	}
	
	public static void obtemNome()
	{
		System.out.println("obtemNome superclasse");
	}
	
	public static void main(String [] args)
	{
		Empresa e = new Empresa("Felipe");
		System.out.println(e.nome);
	}
	
	public Empresa(String nome,String cidade,String dono)
	{
		this.nome = nome;
		this.cidade = cidade;
	}
	

	public Collection<Pessoa> getFuncionario()
	{
		return funcionario;
	}

	public void setFuncionario(Collection<Pessoa> funcionario)
	{
		if (funcionario != null)
			this.funcionario = funcionario;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	/**
	 * @return the endereco
	 */
	public String getEndereco()
	{
		return endereco;
	}
	
	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}
	
	/**
	 * @return the telefone
	 */
	public String getTelefone()
	{
		return telefone;
	}
	
	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone)
	{
		this.telefone = telefone;
	}
	
	/**
	 * @return the cidade
	 */
	public String getCidade()
	{
		return cidade;
	}
	
	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	public String getDono()
	{
		return dono;
	}

	public void setDono(String dono)
	{
		if (dono != null)
			this.dono = dono.toUpperCase().trim();
	}
	
	public String toString()
	{
		return nome;
	}
}
