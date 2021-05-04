package danfe;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import dao.NfeDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class TestNfeDanfe
{
	private static String PAHT_ROOT = "C:\\arquivosXML";
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
		try
		{
			createDanfe(nfe);
			return;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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

//		 JasperViewer.viewReport(jp,false);
		 String pdf = PAHT_DANFE + File.separator + periodo + File.separator + nfe.getChaveAcesso() + ".pdf";
		JasperExportManager.exportReportToPdfFile(jp, pdf);
		
		//openPdf(pdf);

	}
	
	private final String rootResource = "";
	protected String resource = "/danfeXml2/";
	protected String jasperFile = "danfe_layout_2.jasper";
	protected String rootTag = "nfeProc";
	
	private File createDanfe(Nfe nfe) throws Exception
	{

		try
		{
			URL pathResource = getClass().getResource(rootResource + resource);
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("SUBREPORT_DIR", pathResource.getFile());
	
	
			URL layoutFile = getClass().getResource(rootResource + resource + jasperFile);
	
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(layoutFile);
	
			InputStream stream = new ByteArrayInputStream(nfe.getXml().getBytes(StandardCharsets.UTF_8));
			JRXmlDataSource xml = new JRXmlDataSource(stream, rootTag);
	
			
			JasperPrint jp = JasperFillManager.fillReport(jasperReport, parametros, xml);
			JasperViewer.viewReport(jp,false);
	
//			File file = createTempFile(nfe);
//			JasperExportManager.exportReportToPdfFile(jp, "C:\\doc\\" + nfe.getChaveAcesso()+ ".pdf");
//			JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(file));
			
			return null;
		}
		catch (JRException e) 
		{
			throw new Exception("Erro ao criar relatorio jasper " + nfe.getChaveAcesso());
		}
		catch (Exception e)
		{
			throw new Exception(e);
		}
	}
	
	private void openPdf(String path)
	{
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File(path);
		        Desktop.getDesktop().print(myFile);
		    } catch (IOException ex) {
		    }
		}
	}


	public void initDanfe(String... periodos)
	{
		List<Nfe> listCte = Collections.emptyList();		
		
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
		String[] periodos = {"08-2017"};
//		String[] periodos = args;
		
		TestNfeDanfe nfe = new TestNfeDanfe();
		nfe.createDirectory(periodos);
		nfe.initDanfe(periodos);
//		nfe.listDanfe(periodos);
	}
}
