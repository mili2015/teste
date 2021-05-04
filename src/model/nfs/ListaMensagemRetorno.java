package model.nfs;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ListaMensagemRetorno")
public class ListaMensagemRetorno
{
	@XStreamImplicit(itemFieldName="MensagemRetorno")
	private List<MensagemRetorno> mensagemRetorno;

	public List<MensagemRetorno> getMensagemRetorno()
	{
		return mensagemRetorno;
	}

	public void setMensagemRetorno(List<MensagemRetorno> mensagemRetorno)
	{
		this.mensagemRetorno = mensagemRetorno;
	}

	
	
}
