package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("infProf") 
public class InfProt
{
	@XStreamAsAttribute
	private String id="1";
	@XStreamAsAttribute
	private String tpAmb="2";

	private String verAplic="1.2";
	private String cStat="pronto";
	private String xMotivo="ok";
	private String cUF="pr";
	private String chNFe="12334556";
	private String dhRecbto="12/12/000";
	private String nProt="123123123";
	private String digVal="123423412431342";
	
	
	
	public String getTpAmb()
	{
		return tpAmb;
	}

	public void setTpAmb(String tpAmb)
	{
		if (tpAmb != null)
			this.tpAmb = tpAmb.toUpperCase().trim();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		if (id != null)
			this.id = id.toUpperCase().trim();
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

	public String getChNFe()
	{
		return chNFe;
	}

	public void setChNFe(String chNFe)
	{
		if (chNFe != null)
			this.chNFe = chNFe.toUpperCase().trim();
	}

	public String getDhRecbto()
	{
		return dhRecbto;
	}

	public void setDhRecbto(String dhRecbto)
	{
		if (dhRecbto != null)
			this.dhRecbto = dhRecbto.toUpperCase().trim();
	}

	public String getNProt()
	{
		return nProt;
	}

	public void setNProt(String prot)
	{
		if (prot != null)
			nProt = prot.toUpperCase().trim();
	}

	public String getDigVal()
	{
		return digVal;
	}

	public void setDigVal(String digVal)
	{
		if (digVal != null)
			this.digVal = digVal.toUpperCase().trim();
	}

}
