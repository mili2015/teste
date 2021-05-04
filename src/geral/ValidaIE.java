package geral;

import java.util.Locale;
import java.util.ResourceBundle;

import org.brazilutils.br.uf.ie.InscricaoEstadual;
import org.brazilutils.br.uf.ie.InscricaoEstadualAC;
import org.brazilutils.br.uf.ie.InscricaoEstadualAL;
import org.brazilutils.br.uf.ie.InscricaoEstadualAM;
import org.brazilutils.br.uf.ie.InscricaoEstadualAP;
import org.brazilutils.br.uf.ie.InscricaoEstadualBA;
import org.brazilutils.br.uf.ie.InscricaoEstadualCE;
import org.brazilutils.br.uf.ie.InscricaoEstadualDF;
import org.brazilutils.br.uf.ie.InscricaoEstadualES;
import org.brazilutils.br.uf.ie.InscricaoEstadualGO;
import org.brazilutils.br.uf.ie.InscricaoEstadualMA;
import org.brazilutils.br.uf.ie.InscricaoEstadualMG;
import org.brazilutils.br.uf.ie.InscricaoEstadualMS;
import org.brazilutils.br.uf.ie.InscricaoEstadualMT;
import org.brazilutils.br.uf.ie.InscricaoEstadualPA;
import org.brazilutils.br.uf.ie.InscricaoEstadualPB;
import org.brazilutils.br.uf.ie.InscricaoEstadualPE;
import org.brazilutils.br.uf.ie.InscricaoEstadualPI;
import org.brazilutils.br.uf.ie.InscricaoEstadualPR;
import org.brazilutils.br.uf.ie.InscricaoEstadualRJ;
import org.brazilutils.br.uf.ie.InscricaoEstadualRN;
import org.brazilutils.br.uf.ie.InscricaoEstadualRO;
import org.brazilutils.br.uf.ie.InscricaoEstadualRR;
import org.brazilutils.br.uf.ie.InscricaoEstadualRS;
import org.brazilutils.br.uf.ie.InscricaoEstadualSC;
import org.brazilutils.br.uf.ie.InscricaoEstadualSE;
import org.brazilutils.br.uf.ie.InscricaoEstadualSP;
import org.brazilutils.br.uf.ie.InscricaoEstadualTO;

import br.com.caelum.stella.MessageProducer;
import br.com.caelum.stella.ResourceBundleMessageProducer;
import br.com.caelum.stella.type.Estado;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaIE
{

	public void validate(String ie) throws Exception
	{
		boolean valido = false;

		if(!ie.toUpperCase().equals("ISENTO"))
		{
			if(ie.contains("000000000"))
			{
				valido = false;
			}
			else
			{
				InscricaoEstadual ac = new InscricaoEstadualAC();
				InscricaoEstadual al= new InscricaoEstadualAL(); 
				InscricaoEstadual am= new InscricaoEstadualAM(); 
				InscricaoEstadual ap= new InscricaoEstadualAP ();
				InscricaoEstadual ba= new InscricaoEstadualBA ();
				InscricaoEstadual ce= new InscricaoEstadualCE ();
				InscricaoEstadual df= new InscricaoEstadualDF ();
				InscricaoEstadual es= new InscricaoEstadualES ();
				InscricaoEstadual go= new InscricaoEstadualGO ();
				InscricaoEstadual ma= new InscricaoEstadualMA ();
				InscricaoEstadual mg= new InscricaoEstadualMG ();
				InscricaoEstadual ms= new InscricaoEstadualMS ();
				InscricaoEstadual mt= new InscricaoEstadualMT ();
				InscricaoEstadual pa= new InscricaoEstadualPA ();
				InscricaoEstadual pb= new InscricaoEstadualPB ();
				InscricaoEstadual pe= new InscricaoEstadualPE ();
				InscricaoEstadual pi= new InscricaoEstadualPI ();
				InscricaoEstadual pr= new InscricaoEstadualPR ();
				InscricaoEstadual rj= new InscricaoEstadualRJ ();
				InscricaoEstadual rn= new InscricaoEstadualRN ();
				InscricaoEstadual ro= new InscricaoEstadualRO ();
				InscricaoEstadual rr= new InscricaoEstadualRR ();
				InscricaoEstadual rs= new InscricaoEstadualRS ();
				InscricaoEstadual sc= new InscricaoEstadualSC ();
				InscricaoEstadual se= new InscricaoEstadualSE ();
				InscricaoEstadual sp= new InscricaoEstadualSP ();
				InscricaoEstadual to= new InscricaoEstadualTO ();


				pr.addValidator(sp);
				sp.addValidator(mg);
				mg.addValidator(rs);
				rs.addValidator(sc);
				sc.addValidator(ba);
				ba.addValidator(rj);
				rj.addValidator(es);
				es.addValidator(go);
				go.addValidator(pe);
				pe.addValidator(mt);
				mt.addValidator(al);
				al.addValidator(df);
				df.addValidator(ro);
				ro.addValidator(to);
				to.addValidator(se);
				se.addValidator(pi);
				pi.addValidator(pa);
				pa.addValidator(ce);
				ce.addValidator(rn);
				rn.addValidator(ma);
				ma.addValidator(pb);
				pb.addValidator(ac);
				ac.addValidator(am);
				am.addValidator(ap);
				ap.addValidator(rr);

				try
				{
					valido = pr.validate(ie); 
				}
				catch(StackOverflowError e)
				{
					valido = false;
				}
				
				if(!valido)
				{
					ResourceBundle resourceBundle = ResourceBundle.getBundle("StellaValidationMessages", new Locale("pt", "BR"));
					MessageProducer messageProducer = new ResourceBundleMessageProducer(resourceBundle);

					boolean isFormatted = false;
					String ieValida=null;
					Estado []estados=Estado.values();

					for (Estado e : estados)
					{
						try
						{
							//System.out.println(e.toString());
							//				814044026 es
							e.getIEValidator(messageProducer, isFormatted).assertValid(ie);
							ieValida = e.name();
							valido = true;
							break;
						}
						catch (InvalidStateException ex){}
					}
					System.out.println(ieValida+" "+valido);
				}
			}
		}

			System.out.println("valido "+valido);
			if(!valido)
			{
				throw new Exception("IE inválida.");
			}
	}
	
	public static void main(String[] args)
	{
		ValidaIE v = new ValidaIE();
		try
		{
			v.validate("072895445");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
