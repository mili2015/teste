package model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("retConsReciNFe") 
public class RetConsReciNFe
{
	@XStreamAsAttribute
	private String versao;
	private String tpAmb;
	private String verAplic;
	private String nRec;
	private String cStat;
	private String xMotivo;
	private String cUF;
	
	@XStreamImplicit(itemFieldName = "protNFe")
	private List<ProtNFe> protNFe = new ArrayList<ProtNFe>();
	
	public List<ProtNFe> getProtNFe()
	{
		return protNFe;
	}
	public void setProtNFe(List<ProtNFe> protNFe)
	{
		this.protNFe = protNFe;
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
	public String getTpAmb()
	{
		return tpAmb;
	}
	public void setTpAmb(String tpAmb)
	{
		if (tpAmb != null)
			this.tpAmb = tpAmb.toUpperCase().trim();
	}
	public String getVerAplic()
	{
		return verAplic;
	}
	public void setVerAplic(String verAplic)
	{
		if (verAplic != null)
			this.verAplic = verAplic.toUpperCase().trim();
	}
	public String getNRec()
	{
		return nRec;
	}
	public void setNRec(String rec)
	{
		if (rec != null)
			nRec = rec.toUpperCase().trim();
	}
	public String getCStat()
	{
		return cStat;
	}
	public void setCStat(String stat)
	{
		if (stat != null)
			cStat = stat.toUpperCase().trim();
	}
	public String getXMotivo()
	{
		return xMotivo;
	}
	public void setXMotivo(String motivo)
	{
		if (motivo != null)
			xMotivo = motivo.toUpperCase().trim();
	}
	public String getCUF()
	{
		return cUF;
	}
	public void setCUF(String cuf)
	{
		if (cuf != null)
			cUF = cuf.toUpperCase().trim();
	}
	
}
