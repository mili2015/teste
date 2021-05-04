package geral;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gdata.util.common.util.Base64;

public class UploadTest
{

	private static final String URL_UPLOAD = "http://172.2.1.60:8080/servicosmili/ipedidos/upload/query";
	private static final String PARAM = "?codvendedor=4&codsistema=1";
	private final static String CHARSET = "UTF-8";
	
	public void post2() throws MalformedURLException, IOException
	{
		String paramToSend = "4";
		File fileToUpload = new File("C:\\doc\\fachada2.png");
		String boundary = Long.toHexString(System.currentTimeMillis()); 
		String userCredentials = "rep4:MILI44";
		
		String basicAuth = "Basic " + new String(Base64.encode(userCredentials.getBytes()));
		
		HttpURLConnection  connection = (HttpURLConnection) new URL(URL_UPLOAD + PARAM ).openConnection();
		connection.setDoOutput(true); // This sets request method to POST.
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty ("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		connection.setRequestProperty ("Accept-Encoding", "gzip, deflate");
		connection.setRequestProperty ("Authorization", basicAuth);
		connection.setRequestProperty ("Connection", "keep-alive");
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Length", "282295");
		connection.setRequestProperty("Content-Type","multipart/form-data; boundary=" + boundary);
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36");
		
		
		
		DataOutputStream  output = null;
		try
		{
			output = new DataOutputStream (connection.getOutputStream());
			output.writeBytes("--" + boundary+"\r\n");
			output.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"fachada2.png\""+"\r\n");
			output.writeBytes("Content-Type: image/png"+"\r\n");
			output.writeBytes("\r\n");

			byte[] anexo = convertInputstreamToBytes(fileToUpload);
			output.write(anexo);
			
			output.writeBytes("--" + boundary+"\r\n");
			output.writeBytes("Content-Disposition: form-data; name=\"codvendedor\""+"\r\n");
//			output.writeBytes("Content-Type: text/plain; charset=UTF-8"+"\r\n");
			output.writeBytes(paramToSend+"\r\n");
			
			output.writeBytes("--" + boundary + "--" + "\r\n");
		}
		finally
		{
			if (output != null)
			{
				output.flush();
				output.close();
			}
		}
		
		
		int responseCode = ((HttpURLConnection) connection).getResponseCode();
		System.out.println(responseCode); 
	}
	
	

	/**
	 * @param fileToUpload
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private byte[] convertInputstreamToBytes(File fileToUpload) throws FileNotFoundException, IOException
	{
		InputStream is = new FileInputStream(fileToUpload);
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		while ((nRead = is.read(data, 0, data.length)) != -1) {
		  buffer.write(data, 0, nRead);
		}

		buffer.flush();

		return buffer.toByteArray();
	}
	
	public static void main(String[] args)
	{
		UploadTest test = new UploadTest();
		try
		{
			test.post2();
//			test.post3();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
