package bomTransporte;

import java.util.Date;

public class Caminhao
{
	private Motorista motorista = new Motorista();
	
	private long codCaminhao;

	private long codTransp ;

	private String marca;

	private String tipo;

	private String ano;

	private String cor;

	private String chassi;

	private String placa;

	private String placaCarreta;

	private double qtMaxCarga;

	private String observacao;

	private String proprietario;

	private String municipio;

	private double largura1M;

	private double largura2M;

	private double comprimento1M;

	private double comprimento2M;

	private double altura1M;

	private double altura2M;

	private String renavam;

	private String tipoCarroceria;

	private Integer numeroEixo;

	private String codigoANTT;

	private String aparenciaCabine;

	private String aparenciaExtCarroceria;

	private String aparenciaIntCarroceria;

	private String aparenciaGeralVeiculo;

	private String aparenciaLimpeza;

	private String ufPlaca;
	
	private double peso;

	/**
	 * @return the motorista
	 */
	public Motorista getMotorista()
	{
		return motorista;
	}

	/**
	 * @param motorista the motorista to set
	 */
	public void setMotorista(Motorista motorista)
	{
		this.motorista = motorista;
	}

	/**
	 * @return the codCaminhao
	 */
	public long getCodCaminhao()
	{
		return codCaminhao;
	}

	/**
	 * @param codCaminhao the codCaminhao to set
	 */
	public void setCodCaminhao(long codCaminhao)
	{
		this.codCaminhao = codCaminhao;
	}

	/**
	 * @return the codTransp
	 */
	public long getCodTransp()
	{
		return codTransp;
	}

	/**
	 * @param codTransp the codTransp to set
	 */
	public void setCodTransp(long codTransp)
	{
		this.codTransp = codTransp;
	}

	/**
	 * @return the marca
	 */
	public String getMarca()
	{
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca)
	{
		this.marca = marca;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo()
	{
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	/**
	 * @return the ano
	 */
	public String getAno()
	{
		return ano;
	}

	/**
	 * @param ano the ano to set
	 */
	public void setAno(String ano)
	{
		this.ano = ano;
	}

	/**
	 * @return the cor
	 */
	public String getCor()
	{
		return cor;
	}

	/**
	 * @param cor the cor to set
	 */
	public void setCor(String cor)
	{
		this.cor = cor;
	}

	/**
	 * @return the chassi
	 */
	public String getChassi()
	{
		return chassi;
	}

	/**
	 * @param chassi the chassi to set
	 */
	public void setChassi(String chassi)
	{
		this.chassi = chassi;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca()
	{
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa)
	{
		this.placa = placa;
	}

	/**
	 * @return the placaCarreta
	 */
	public String getPlacaCarreta()
	{
		return placaCarreta;
	}

	/**
	 * @param placaCarreta the placaCarreta to set
	 */
	public void setPlacaCarreta(String placaCarreta)
	{
		this.placaCarreta = placaCarreta;
	}

	/**
	 * @return the qtMaxCarga
	 */
	public double getQtMaxCarga()
	{
		return qtMaxCarga;
	}

	/**
	 * @param qtMaxCarga the qtMaxCarga to set
	 */
	public void setQtMaxCarga(double qtMaxCarga)
	{
		this.qtMaxCarga = qtMaxCarga;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao()
	{
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao)
	{
		this.observacao = observacao;
	}

	/**
	 * @return the proprietario
	 */
	public String getProprietario()
	{
		return proprietario;
	}

	/**
	 * @param proprietario the proprietario to set
	 */
	public void setProprietario(String proprietario)
	{
		this.proprietario = proprietario;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio()
	{
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio)
	{
		this.municipio = municipio;
	}

	/**
	 * @return the largura1M
	 */
	public double getLargura1M()
	{
		return largura1M;
	}

	/**
	 * @param largura1m the largura1M to set
	 */
	public void setLargura1M(double largura1m)
	{
		largura1M = largura1m;
	}

	/**
	 * @return the largura2M
	 */
	public double getLargura2M()
	{
		return largura2M;
	}

	/**
	 * @param largura2m the largura2M to set
	 */
	public void setLargura2M(double largura2m)
	{
		largura2M = largura2m;
	}

	/**
	 * @return the comprimento1M
	 */
	public double getComprimento1M()
	{
		return comprimento1M;
	}

	/**
	 * @param comprimento1m the comprimento1M to set
	 */
	public void setComprimento1M(double comprimento1m)
	{
		comprimento1M = comprimento1m;
	}

	/**
	 * @return the comprimento2M
	 */
	public double getComprimento2M()
	{
		return comprimento2M;
	}

	/**
	 * @param comprimento2m the comprimento2M to set
	 */
	public void setComprimento2M(double comprimento2m)
	{
		comprimento2M = comprimento2m;
	}

	/**
	 * @return the altura1M
	 */
	public double getAltura1M()
	{
		return altura1M;
	}

	/**
	 * @param altura1m the altura1M to set
	 */
	public void setAltura1M(double altura1m)
	{
		altura1M = altura1m;
	}

	/**
	 * @return the altura2M
	 */
	public double getAltura2M()
	{
		return altura2M;
	}

	/**
	 * @param altura2m the altura2M to set
	 */
	public void setAltura2M(double altura2m)
	{
		altura2M = altura2m;
	}

	/**
	 * @return the renavam
	 */
	public String getRenavam()
	{
		return renavam;
	}

	/**
	 * @param renavam the renavam to set
	 */
	public void setRenavam(String renavam)
	{
		this.renavam = renavam;
	}

	/**
	 * @return the tipoCarroceria
	 */
	public String getTipoCarroceria()
	{
		return tipoCarroceria;
	}

	/**
	 * @param tipoCarroceria the tipoCarroceria to set
	 */
	public void setTipoCarroceria(String tipoCarroceria)
	{
		this.tipoCarroceria = tipoCarroceria;
	}

	/**
	 * @return the numeroEixo
	 */
	public Integer getNumeroEixo()
	{
		return numeroEixo;
	}

	/**
	 * @param numeroEixo the numeroEixo to set
	 */
	public void setNumeroEixo(Integer numeroEixo)
	{
		this.numeroEixo = numeroEixo;
	}


	/**
	 * @return the codigoANTT
	 */
	public String getCodigoANTT()
	{
		return codigoANTT;
	}

	/**
	 * @param codigoANTT the codigoANTT to set
	 */
	public void setCodigoANTT(String codigoANTT)
	{
		this.codigoANTT = codigoANTT;
	}

	/**
	 * @return the aparenciaCabine
	 */
	public String getAparenciaCabine()
	{
		return aparenciaCabine;
	}

	/**
	 * @param aparenciaCabine the aparenciaCabine to set
	 */
	public void setAparenciaCabine(String aparenciaCabine)
	{
		this.aparenciaCabine = aparenciaCabine;
	}

	/**
	 * @return the aparenciaExtCarroceria
	 */
	public String getAparenciaExtCarroceria()
	{
		return aparenciaExtCarroceria;
	}

	/**
	 * @param aparenciaExtCarroceria the aparenciaExtCarroceria to set
	 */
	public void setAparenciaExtCarroceria(String aparenciaExtCarroceria)
	{
		this.aparenciaExtCarroceria = aparenciaExtCarroceria;
	}

	/**
	 * @return the aparenciaIntCarroceria
	 */
	public String getAparenciaIntCarroceria()
	{
		return aparenciaIntCarroceria;
	}

	/**
	 * @param aparenciaIntCarroceria the aparenciaIntCarroceria to set
	 */
	public void setAparenciaIntCarroceria(String aparenciaIntCarroceria)
	{
		this.aparenciaIntCarroceria = aparenciaIntCarroceria;
	}

	/**
	 * @return the aparenciaGeralVeiculo
	 */
	public String getAparenciaGeralVeiculo()
	{
		return aparenciaGeralVeiculo;
	}

	/**
	 * @param aparenciaGeralVeiculo the aparenciaGeralVeiculo to set
	 */
	public void setAparenciaGeralVeiculo(String aparenciaGeralVeiculo)
	{
		this.aparenciaGeralVeiculo = aparenciaGeralVeiculo;
	}

	/**
	 * @return the aparenciaLimpeza
	 */
	public String getAparenciaLimpeza()
	{
		return aparenciaLimpeza;
	}

	/**
	 * @param aparenciaLimpeza the aparenciaLimpeza to set
	 */
	public void setAparenciaLimpeza(String aparenciaLimpeza)
	{
		this.aparenciaLimpeza = aparenciaLimpeza;
	}

	/**
	 * @return the ufPlaca
	 */
	public String getUfPlaca()
	{
		return ufPlaca;
	}

	/**
	 * @param ufPlaca the ufPlaca to set
	 */
	public void setUfPlaca(String ufPlaca)
	{
		this.ufPlaca = ufPlaca;
	}

	/**
	 * @return the peso
	 */
	public double getPeso()
	{
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso)
	{
		this.peso = peso;
	}
	
	
	
}
