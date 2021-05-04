package model;

import javax.xml.bind.annotation.XmlAnyAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("protNFe") 
public class ProtNFe
{
	@XStreamAsAttribute
	private String versao;
	private String Signature;

	@XStreamAlias("infProf") 
	private InfProt infProt = new InfProt();
	
	public String getSignature()
	{
		return Signature;
	}

	public void setSignature(String signature)
	{
		if (signature != null)
			Signature = signature.toUpperCase().trim();
	}

	public String getVersao()
	{
		return versao;
	}

	public void setVersao(String versao)
	{
		if (versao != null)
			this.versao = versao.toUpperCase().trim();
	}

	public InfProt getInfProt()
	{
		return infProt;
	}

	public void setInfProt(InfProt infProt)
	{
		this.infProt = infProt;
	}


	
	
}
