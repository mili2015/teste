package model.nfs;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("MensagemRetorno")
public class MensagemRetorno {
	
	private String Codigo;
	private String Mensagem;
	private String Correcao;
	
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getMensagem() {
		return Mensagem;
	}
	public void setMensagem(String mensagem) {
		Mensagem = mensagem;
	}
	public String getCorrecao() {
		return Correcao;
	}
	public void setCorrecao(String correcao) {
		Correcao = correcao;
	}

}
