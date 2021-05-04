package geral;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XlsReader
{
	private static final String MY_FILE = "C:\\arquivosXML\\roteiro-mili.xlsx";

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		Workbook workbook = WorkbookFactory.create(new File(MY_FILE));
		
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets");
		
		Sheet sheet01 = workbook.getSheetAt(0);
		
		System.out.println("Sheet 01 " + sheet01.getSheetName());
		
		Sheet sheet02 = workbook.getSheetAt(1);
		
		System.out.println("Sheet 02 " + sheet02.getSheetName());
		
		
		DataFormatter dataFormatter = new DataFormatter();
		
		for (Row row : sheet01)
		{
			//System.out.println("Row " + row.getRowNum());
			
			Cell cell01 = row.getCell(0);
			String cellValue01 = dataFormatter.formatCellValue(cell01);
			
			System.out.println(cellValue01);
			for (Cell cell : row)
			{
				
				
				String cellValue = dataFormatter.formatCellValue(cell);
				
				System.out.print(cellValue + "\t\t\t");
			}
			System.out.println("");
		}
	}
}
