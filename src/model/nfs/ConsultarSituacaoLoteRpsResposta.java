package model.nfs;

//package model.nfs;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ConsultarSituacaoLoteRpsResposta")
public class ConsultarSituacaoLoteRpsResposta
{
	@XStreamAsAttribute
	private String NumeroLote;

	private String Situacao;

	@XStreamAlias("ListaMensagemRetorno")
	private ListaMensagemRetorno listaMensagemRetorno;

	public String getNumeroLote()
	{
		return NumeroLote;
	}

	public void setNumeroLote(String numeroLote)
	{
		this.NumeroLote = numeroLote;
	}

	public String getSituacao()
	{
		return Situacao;
	}

	public void setSituacao(String situacao)
	{
		this.Situacao = situacao;
	}

	public ListaMensagemRetorno getListaMensagemRetorno()
	{
		return listaMensagemRetorno;
	}

	public void setListaMensagemRetorno(ListaMensagemRetorno listaMensagemRetorno)
	{
		this.listaMensagemRetorno = listaMensagemRetorno;
	}

	
	

}
