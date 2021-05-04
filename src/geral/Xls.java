package geral;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Produto;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class Xls
{

	public void generate(List<Produto> lista) throws IOException
	{   
		String[] titles = {"Cód. Produto","Produto","CFOP",	"CEAN","NCM",	"Unidade",	"Quantidade","Vlr Unitário","Vlr Contabil","Vlr Mercadoria",
				"Vlr Base ICMS", "Aliq ICMS","Vlr ICMS","Aliq IPI", "Vlr IPI","Vlr BCST","Vlr ICMS Retido","Vlr Base Subst. Dupl *","Vlr ICMS Retido Dupl *",
				"Vlr Desc. Sufr. Unit","Vlr Desc. Sufr. Unit2","CST"};
		
		HSSFWorkbook wb = new HSSFWorkbook();   
		Sheet sheet = wb.createSheet("NFe");
		
		//turn off gridlines
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);

        //the following three statements are required only for HSSF
        sheet.setAutobreaks(true);
        printSetup.setFitHeight((short)10);
        printSetup.setFitWidth((short)50);

        Map<String, CellStyle> styles = createStyles(wb);
		Cell cell = null;
		
		Row rowNFe = sheet.createRow(0);
		rowNFe.setHeightInPoints(12.5f);
		cell = rowNFe.createCell(0);
		cell.setCellValue("NFe:");
		cell.setCellStyle(styles.get("cell_bg"));
		cell = rowNFe.createCell(1);
		cell.setCellValue("123456");
		cell.setCellStyle(styles.get("cell_normal"));
		
		cell = rowNFe.createCell(2);
		cell.setCellValue("Serie:");
		cell.setCellStyle(styles.get("cell_bg"));
		cell = rowNFe.createCell(3);
		cell.setCellValue("NFE-4");
		cell.setCellStyle(styles.get("cell_normal"));
		
		cell = rowNFe.createCell(4);
		cell.setCellValue("Chave de Acesso:");
		cell.setCellStyle(styles.get("cell_bg"));
		cell = rowNFe.createCell(5);
		cell.setCellValue("132456789123456789123456789123456789123456");
		CellRangeAddress cra = new CellRangeAddress(0, 0, 5, 10);
		sheet.addMergedRegion(cra);
//		cell.setCellStyle(styles.get("cell_normal"));
		
		//cabecalho
		Row headerRow = sheet.createRow(2);
		headerRow.setHeightInPoints(12.5f);
		
		for(int a=0;a<titles.length;a++)
		{
			cell = headerRow.createCell(a);
			cell.setCellValue(titles[a]);
			cell.setCellStyle(styles.get("header"));
		}
		
		  //freeze the first row
        sheet.createFreezePane(0, 1);
		
		for(int a =0;a<22;a++)
		{
			 sheet.autoSizeColumn(a);
			// Create a row and put some cells in it. Rows are 0 based.   
			Row row = sheet.createRow(a+3);   
			Produto p = lista.get(0);
			
			row.createCell(0).setCellValue(p.getNrNfiscal());   
			row.createCell((short)1).setCellValue(p.getSerie());   
			row.createCell((short)2).setCellValue(p.getChaveAcesso());   
			row.createCell((short)3).setCellValue(p.getCodProduto());
			row.createCell((short)4).setCellValue(p.getDescProduto());   
			row.createCell((short)5).setCellValue(p.getValor());   
			row.createCell((short)6).setCellValue(p.getObs());  
			
			//sheet.setColumnWidth(a+1, 256*20);
		}

	 	Row rowMsg = sheet.createRow(22+5);
	 
	 	cell = rowMsg.createCell(0);
		cell.setCellValue("* Valores cobrados na duplicata. Estes dados aparecem nas informações adicionais da DANFE com o título: ICMS rec. antecip. em nome do dest.");
		cell.setCellStyle(styles.get("cell_blue"));

		cra = new CellRangeAddress(22+5, 22+5, 0, 10);
		sheet.addMergedRegion(cra);
		
		rowMsg = sheet.createRow(22+7);
	 	cell = rowMsg.createCell(0);
		cell.setCellValue("Cálculo de ressarcimento ICMS Dif. de Aliquota ");
		cell.setCellStyle(styles.get("header"));
		rowMsg = sheet.createRow(22+8);
	 	cell = rowMsg.createCell(0);
		cell.setCellValue("(vlr total NF) * 17%(Aliq. Interna) = x;");
		rowMsg = sheet.createRow(22+9);
	 	cell = rowMsg.createCell(0);
		cell.setCellValue("x - (vlr ICMS destacado na NF) = valor ressarcimento ICMS Dif. De Aliquota");

		cra = new CellRangeAddress(22+7, 22+7, 0, 3);
		sheet.addMergedRegion(cra);
		
		// Write the output to a file   
		FileOutputStream fileOut = new FileOutputStream("c:\\arquivosXML\\workbook_new.xls");   
		wb.write(fileOut);   
		fileOut.close();   
	}   
	
	
	  /**
     * create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        DataFormat df = wb.createDataFormat();

       /* CellStyle style;
        Font headerFont = wb.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = createBorderedStyle(wb);
        
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("header", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(headerFont);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("header_date", style);

        Font font1 = wb.createFont();
        font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font1);
        styles.put("cell_b", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font1);
        styles.put("cell_b_centered", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setFont(font1);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_b_date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_g", style);

        Font font2 = wb.createFont();
        font2.setColor(IndexedColors.BLUE.getIndex());
        font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font2);
        styles.put("cell_bb", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_bg", style);

        Font font3 = wb.createFont();
        font3.setFontHeightInPoints((short)14);
        font3.setColor(IndexedColors.DARK_BLUE.getIndex());
        font3.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font3);
        style.setWrapText(true);
        styles.put("cell_h", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setWrapText(true);
        styles.put("cell_normal", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        styles.put("cell_normal_centered", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setWrapText(true);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_normal_date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setIndention((short)1);
        style.setWrapText(true);
        styles.put("cell_indented", style);

        style = createBorderedStyle(wb);
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);        
        styles.put("cell_blue", style);*/

        return styles;
    }
    
    private static CellStyle createBorderedStyle(Workbook wb){
        CellStyle style = wb.createCellStyle();
//        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
//        style.setBorderBottom(CellStyle.BORDER_THIN);
//        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
////        style.setBorderLeft(CellStyle.BORDER_THIN);
//        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
////        style.setBorderTop(CellStyle.BORDER_THIN);
//        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		List<Produto> lista = new ArrayList<Produto>();
		lista.add(new Produto());
		lista.add(new Produto());
		lista.add(new Produto());
		lista.add(new Produto());
		lista.add(new Produto());
		lista.add(new Produto());
		
		
		Xls xls = new Xls();
		try
		{
			xls.generate(lista);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
