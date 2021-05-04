package model;

public class EsisavAcesso {
	private int id;
	private int codVendedor;
	private int codVendedorSistema;
	private int codSubVendedor;
	private String idInternet;
	private String senha;
	private String perfil;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodVendedor() {
		return codVendedor;
	}

	public void setCodVendedor(int codVendedor) {
		this.codVendedor = codVendedor;
	}

	public int getCodVendedorSistema() {
		return codVendedorSistema;
	}

	public void setCodVendedorSistema(int codVendedorSistema) {
		this.codVendedorSistema = codVendedorSistema;
	}

	public int getCodSubVendedor() {
		return codSubVendedor;
	}

	public void setCodSubVendedor(int codSubVendedor) {
		this.codSubVendedor = codSubVendedor;
	}

	public String getIdInternet() {
		return idInternet;
	}

	public void setIdInternet(String idInternet) {
		this.idInternet = idInternet;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}
