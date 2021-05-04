package cte;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import dao.CteDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class CteDacte
{
	private static String PAHT_ROOT = "G:\\arquivosXML";
	private static String PAHT_DACTE = PAHT_ROOT + "\\dacte1.4";
	
	private CteDao dao = new CteDao();
	
	private List<Cte> consultarCte(String periodo)
	{
		
		try
		{
			return dao.findChaveCte(periodo);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	private void testResource()
	{
		URL caminho = getClass().getResource("/resource/dacte1.4/");
		
		System.out.println("Resource: "+caminho);
	}
	
	private void updateStatus(String chaveAcesso, String impresso)
	{
		dao.updateStatus(chaveAcesso, impresso);
	}
	
	public void gerarDacte(Cte cte, String periodo) throws JRException
	{

		URL caminho = getClass().getResource("/dacte1.4/");
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("SUBREPORT_DIR", caminho);

		//System.out.println("caminho: " + caminho);
		//System.out.println(System.getProperty("user.dir") );
		
		// Caminho do arquivo Boletim.jasper (bytecode gerado/compilado do
		// nosso relatorio Boletim.jrxml)
		// String relatorio = System.getProperty("user.dir") +
		// "/src/com/blogspot/javadevilopers/relatorio/Boletim.jasper";

		URL arquivo = getClass().getResource("/dacte1.4/dacte_retrato.jasper");
		//System.out.println("arquivo dacte_retrato.jasper: " + arquivo);

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arquivo);

		// Configurando a classe JRXmlDataSource que apontara o caminho do
		// nosso XML de dados e sua pesquisa XPath geral
		InputStream stream = new ByteArrayInputStream(cte.getXml().getBytes(StandardCharsets.UTF_8));
		JRXmlDataSource xml = new JRXmlDataSource(stream, "/cteProc/CTe/infCte/ide");

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
		JasperExportManager.exportReportToPdfFile(jp, PAHT_DACTE + File.separator + periodo + File.separator + cte.getChaveAcesso() + ".pdf");

	}
	
	public void initDacte(String ...periodos)
	{
		List<Cte> listCte = Collections.emptyList();		
				
		
		for (String periodo : periodos)
		{
			listCte = consultarCte(periodo);
			
			System.out.print("DACTE Periodo: " + periodo + " list " + listCte.size() +" \t....");
			
			for (Cte cte : listCte)
			{
				//System.out.println(cte);
				try
				{
					gerarDacte(cte, periodo);
					updateStatus(cte.getChaveAcesso(), "S");
				}
				catch (JRException e)
				{
					updateStatus(cte.getChaveAcesso(), "N");
					e.printStackTrace();
				}
			}
			
			System.out.println("OK! ");
			
		}
	}
	
	public void listFile()
	{
		String[] periodos = { "04-2013", "05-2013", "06-2013", "07-2013", "08-2013", "09-2013", "10-2013", "11-2013", "12-2013", 
				"01-2014","02-2014","03-2014","04-2014", "05-2014", "06-2014", "07-2014", "08-2014", "09-2014", "10-2014", "11-2014", "12-2014"};
		
		File file = null;
		for (String periodo : periodos)
		{
			file = new File(PAHT_DACTE + File.separator + periodo );
			
			System.out.println("Periodo "+ periodo + " - " + file.listFiles().length + " files");
			
			for (File f : file.listFiles())
			{
				String [] chave = f.getName().split("\\.");
				dao.insert(chave[0], periodo);
			}
		}
		
	}
	
	public void createDirectory(String ...periodos)
	{
		File root = new File(PAHT_ROOT + File.separator);
		System.out.print("createDirectory " + root.getPath() + "...");
		
		if (!root.isDirectory())
			root.mkdir();
		
		
		File root2 = new File(PAHT_DACTE + File.separator);
		System.out.print( root2.getPath() + "...");
		
		if (!root2.isDirectory())
			root2.mkdir();
		
		System.out.println("ARQUIVO XML " + root.isDirectory());
		
		File file;
		for (String periodo : periodos)
		{
			file = new File(PAHT_DACTE + File.separator + periodo );
			
			System.out.print("createDirectory " + file.getPath() +" ... ");
			
			if (!file.isDirectory())
				file.mkdir();
			
			System.out.println(file.isDirectory());
			
		}
	}
	
	public static void main(String[] args)
	{
		CteDacte c = new CteDacte();
		
		String[] periodos = {
//				"01-2015","02-2015","03-2015","04-2015", "05-2015", "06-2015", "07-2015", "08-2015", "09-2015", "10-2015", "11-2015", "12-2015"};
//				"01-2016","02-2016","03-2016","04-2016", "05-2016", "06-2016", "07-2016", "08-2016", "09-2016", "10-2016", "11-2016", "12-2016"};
				"01-2017","02-2017","03-2017","04-2017", "05-2017", "06-2017", "07-2017", "08-2017", "09-2017", "10-2017", "11-2017", "12-2017"};
		
		c.createDirectory(periodos);
		c.initDacte(periodos);
//		c.listFile();
	}
}
