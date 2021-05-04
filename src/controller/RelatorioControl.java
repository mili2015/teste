package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.Empresa;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioControl
{

	public static Collection geraBD()
	{
		List <Empresa> col = new ArrayList<Empresa>();
//		col.add(new Empresa("Mili","Curitiba"));
//		col.add(new Empresa("Mili Cedi","Curitiba"));
//		col.add(new Empresa("Mili Loja","Curitiba"));
//		col.add(new Empresa("Felipe Alves","Curitiba"));
//		col.add(new Empresa("Mili Tb","Tres Barras"));
//		col.add(new Empresa("Mili Tb1","Tres Barras"));
//		col.add(new Empresa("Mili Tb2","Tres Barras"));
//		col.add(new Empresa("Mili Maceio ","Maceio"));
//		col.add(new Empresa("Mili Maceio Cedi","Maceio"));
		
		return col;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		Collection<Empresa> dados = geraBD();
		JRDataSource jrds  = new JRBeanCollectionDataSource(dados);
		try
		{
			JasperPrint print = JasperFillManager.fillReport("C:\\Documents and Settings\\felipe.alves\\Desktop\\relatorio\\Relatorio_sem_nome_1.jasper", new HashMap(),jrds);
			JasperExportManager.exportReportToPdfFile(print,"C:\\relatorio2.pdf");
		}
		catch (JRException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

//		JasperExportManager.exportReportToPdfFile() 

	}

}
