package model.nfs;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("EnviarLoteRpsResposta")
public class EnviarLoteRpsResposta
{
	@XStreamAsAttribute
	private String NumeroLote;

	private String DataRecebimento;

	private String Protocolo;

	@XStreamImplicit(itemFieldName = "ListaMensagemRetorno")
	private List<ListaMensagemRetorno> listaMensagemRetorno = new ArrayList<ListaMensagemRetorno>();

	public String getNumeroLote()
	{
		return NumeroLote;
	}

	public void setNumeroLote(String numeroLote)
	{
		this.NumeroLote = numeroLote;
	}

	public String getDataRecebimento()
	{
		return DataRecebimento;
	}

	public void setDataRecebimento(String dataRecebimento)
	{
		this.DataRecebimento = dataRecebimento;
	}

	public String getProtocolo()
	{
		return Protocolo;
	}

	public void setProtocolo(String protocolo)
	{
		Protocolo = protocolo;
	}

	public List<ListaMensagemRetorno> getListaMensagemRetorno()
	{
		return listaMensagemRetorno;
	}

	public void setListaMensagemRetorno(
			List<ListaMensagemRetorno> listaMensagemRetorno)
	{
		this.listaMensagemRetorno = listaMensagemRetorno;
	}

	

}
