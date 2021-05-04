package danfe;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;

import cte.Cte;
import dao.NfeDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class NfeDanfe
{
	private static String PAHT_ROOT = "G:\\arquivosXML";
	private static String PAHT_DANFE = PAHT_ROOT + "\\Danfe2.0";
	private NfeDao dao = new NfeDao();
	
	private List<Nfe> consultarNfe(String periodo)
	{
		
		try
		{
			return dao.findNfe(periodo);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
		
	private void updateStatus(String chaveAcesso, String impresso)
	{
		dao.updateStatus(chaveAcesso, impresso);
	}
	
	public void gerarDanfe(Nfe nfe, String periodo) throws JRException
	{

		URL caminho = getClass().getResource("/resource/danfe2/");
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("SUBREPORT_DIR", caminho.getFile());
		parametros.put("NRNFISCAL", nfe.getNrNfiscal());
		parametros.put("SERIE", nfe.getSerie());



		
		URL arquivo = getClass().getResource("/resource/danfe2/danfe_layout_2.jasper");
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arquivo);  
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parametros,dao.getDAO().getConnection());  
	    /* Exporta para o formato PDF */
		JasperExportManager.exportReportToPdfFile(jasperPrint, PAHT_DANFE + File.separator + periodo + File.separator + nfe.getChaveAcesso() + ".pdf");
	}
	
	
	public void gerarDanfeXml(Nfe nfe, String periodo) throws JRException
	{

		URL caminho = getClass().getResource("/danfeXml2/");
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("SUBREPORT_DIR", caminho);

		// Caminho do arquivo Boletim.jasper (bytecode gerado/compilado do
		// nosso relatorio Boletim.jrxml)
		// String relatorio = System.getProperty("user.dir") +
		// "/src/com/blogspot/javadevilopers/relatorio/Boletim.jasper";

		URL arquivo = getClass().getResource("/danfeXml2/danfe_layout_2.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arquivo);

		// Configurando a classe JRXmlDataSource que apontara o caminho do
		// nosso XML de dados e sua pesquisa XPath geral
		InputStream stream = new ByteArrayInputStream(nfe.getXml().getBytes(StandardCharsets.UTF_8));
		JRXmlDataSource xml = new JRXmlDataSource(stream, "/nfeProc");

		/*
		 * Gerando o relatorio (Filling) informando o caminho do relatorio, os
		 * parametros
		 * 
		 * (neste caso nenhum paramentro esta sendo passado ao relatorio, por
		 * isso o HashMap esta vazio)
		 * 
		 * e o objeto JRXmlDataSource configurado)
		 */

		JasperPrint jp = JasperFillManager.fillReport(jasperReport, parametros, xml);

		// Utilizando o JasperView, uma classe desktop do jasper para
		// visualização dos relatorios

		// JasperViewer.viewReport(jp,false);
		JasperExportManager.exportReportToPdfFile(jp, PAHT_DANFE + File.separator + periodo + File.separator + nfe.getChaveAcesso() + ".pdf");

	}
	
	public void initDanfe(String... periodos)
	{
		List<Nfe> listCte = Collections.emptyList();		
//		String[] periodos = {"03-2013", "04-2013", "05-2013", "06-2013", "07-2013", "08-2013", "09-2013", "10-2013", "11-2013", "12-2013"}; 
//				"01-2014", "02-2014", "03-2014", "04-2014", "05-2014", "06-2014", "07-2014", "08-2014", "09-2014", "10-2014", "11-2014", "12-2014"};
		
//		String[] periodos = {"04-2013"};
		
		for (String periodo : periodos)
		{
			listCte = consultarNfe(periodo);
			
			System.out.print("DANFE Periodo: " + periodo + " list " + listCte.size() +" \t....");
			
			for (Nfe nfe : listCte)
			{
//				System.out.println(nfe.getChaveAcesso());
				try
				{
					gerarDanfeXml(nfe, periodo);
					updateStatus(nfe.getChaveAcesso(), "S");
				}
				catch (JRException e)
				{
					updateStatus(nfe.getChaveAcesso(), "N");
					e.printStackTrace();
				}
			}
			
			System.out.println("OK! ");
			
		}
	}
	
	public void listDanfe(String[] periodos)
	{				
		File dirFile = null;

		int _1kb = 1 * 1000;
		
		for (String periodo : periodos)
		{
			dirFile = new File(PAHT_DANFE + File.separator + periodo);
			
			for (File f : dirFile.listFiles())
			{
				if (f.length() < _1kb)
				{
					String [] chave = f.getName().split("\\.");
					updateStatus( chave[0], "N");
					System.out.println( f.getName() + " ----- " + f.length());
				}
			}
			initDanfe(periodo);
		}
	}
	
	public void createDirectory(String ...periodos)
	{
		
		File root = new File(PAHT_ROOT + File.separator);
		System.out.print("createDirectory " + root.getPath() + "...");
		
		if (!root.isDirectory())
			root.mkdir();
		
		
		File root2 = new File(PAHT_DANFE + File.separator);
		System.out.print( root2.getPath() + "...");
		
		if (!root2.isDirectory())
			root2.mkdir();
		
		
		
		File file;
		for (String periodo : periodos)
		{
			file = new File(PAHT_DANFE + File.separator + periodo );
			
			if (!file.isDirectory())
				file.mkdir();
			
		}
	}
	
	public static void main(String[] args)
	{
//		String[] periodos = {"08-2017"};
		String[] periodos = args;
		
		NfeDanfe nfe = new NfeDanfe();
		nfe.createDirectory(periodos);
		nfe.initDanfe(periodos);
//		nfe.listDanfe(periodos);
	}
}
