package gnre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GnreService
{

	public static void main(String[] args)
	{
		String carga = "556369.txt";
		String filename = "C:\\arquivosXML\\gnre\\" + carga;
		String newfilename = "C:\\arquivosXML\\gnre\\new\\" + carga;
		
		Map<String, String> mapGuia = new HashMap<>();
		mapGuia.put("27180478908266000809550010000923921586723244", "000000000036330");
		mapGuia.put("27180478908266000809550010000923911912259900", "000000000004166");
		mapGuia.put("27180478908266000809550010000923891832005963", "000000000000593");
		mapGuia.put("27180478908266000809550010000923871997177359", "000000000002137");
		mapGuia.put("27180478908266000809550010000923821803711773", "000000000003456");
		
		try
		{
			
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			String newLine = "";
			String line = null;
			String lineAux = null;
			String chave = null;
			while (reader.ready())
			{
				line = reader.readLine();
			
				for (Entry<String, String> entry : mapGuia.entrySet())
				{
					
					if (line.contains(entry.getKey()))
					{
						System.out.println(entry.getKey() + " - " + entry.getValue());
						
						System.out.println("Before " + line);
						
						lineAux = line.substring(0, 918) + entry.getValue() + line.substring(933);
					
						line = lineAux;
						
						System.out.println("after  " + line);
					}
				}
				
				newLine += line +"\r";
				
				
			}
			
			reader.close();
			
			File f = new File(newfilename);
			FileWriter fw = new FileWriter(f);
//			
//			
			fw.write(newLine);
			fw.flush();
			fw.close();
			
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
