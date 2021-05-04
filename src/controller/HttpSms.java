package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpSms {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
//		enviarSms(); 
		consultarCreditos();
	}

	/**
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 */
	private static void enviarSms() throws UnsupportedEncodingException, MalformedURLException, IOException, ProtocolException
	{
		String urlParameters = "user=felipe.alves" +   
                "&password=77777" +   
                "&destinatario=4188491590;4199275241;4191546241" +  
                "&msg=" + URLEncoder.encode("mensagem multipleSend de teste do sistema Facilita Movel.", "UTF-8");;  
          
        String servico = "multipleSend.ft";
		conectaSms(servico, urlParameters);
	}
	
	private static void consultarCreditos() throws UnsupportedEncodingException, MalformedURLException, IOException, ProtocolException
	{
		String urlParameters = "user=felipe.alves" +
                "&password=77777" ;  
          
        String servico = "checkCredit.ft";
		conectaSms(servico, urlParameters);
	}

	/**
	 * @param urlParameters
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 */
	private static void conectaSms(String servico,String urlParameters) throws MalformedURLException, IOException, ProtocolException
	{
		URL url = new URL("http://www.facilitamovel.com/api/"+servico+"?");   
          
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();             
        connection.setDoOutput(true);  
        connection.setDoInput(true);  
        connection.setInstanceFollowRedirects(false);   
        connection.setRequestMethod("POST");   
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");   
        connection.setRequestProperty("charset", "utf-8");  
        connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));  
        connection.setUseCaches (false);  
  
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());  
        wr.write(urlParameters);  
        wr.flush();  
          
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
        StringBuffer strRet = new StringBuffer();  
        String line;  
        while ((line = rd.readLine()) != null) {  
            strRet.append(line);  
        }  
        wr.close();  
        rd.close();  
          
        System.out.println("Retorno da Chamada HTTP:"+ strRet);
	}

}
