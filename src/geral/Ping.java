package geral;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ping
{
	 public static void main(String args[]) throws Exception {
		 
	        String[] hostList6 = { 
	        		"http://172.10.1.6:456/portal", 
	        		"http://172.10.1.6:456/ServicosMili/produtos",
	        		"http://172.10.1.6:456/medidorquimicows/",
	        		"http://172.10.1.6:456/portalfaces/",
	        		"http://172.10.1.6:456/portalfaces2/",
	        		"http://172.10.1.6:456/portalflex",
	        		"http://172.10.1.6:456/portalmobile/",
	        		"http://172.10.1.6:456/eFrotasws/javadoc/",
	        		"http://172.10.1.6:456/e-projetos/"};
	        
			String[] hostList8 = { 			
	        		"http://172.10.1.8:456/portal", 
	        		"http://172.10.1.8:456/ServicosMili/produtos",
	        		"http://172.10.1.8:456/medidorquimicows/",
	        		"http://172.10.1.8:456/portalfaces/",
	        		"http://172.10.1.8:456/portalfaces2/",
	        		"http://172.10.1.8:456/portalflex",
	        		"http://172.10.1.8:456/portalmobile/",
	        		"http://172.10.1.8:456/eFrotasws/javadoc/",
	        		"http://172.10.1.8:456/e-projetos/"};
	        
			String[] hostList9 = { 						
	        		"http://172.10.1.9:456/portal", 
	        		"http://172.10.1.9:456/ServicosMili/produtos",
	        		"http://172.10.1.9:456/medidorquimicows/",
	        		"http://172.10.1.9:456/portalfaces/",
	        		"http://172.10.1.9:456/portalfaces2/",
	        		"http://172.10.1.9:456/portalflex",
	        		"http://172.10.1.9:456/portalmobile/",
	        		"http://172.10.1.9:456/eFrotasws/javadoc/",
	        		"http://172.10.1.9:456/e-projetos/"};
	 
	 
			while (true)
			{
				System.out.printf("---------->> Ping: %1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS:%1$tL \n\n", java.util.Calendar.getInstance());
				pingar(hostList6);
				pingar(hostList8);
				pingar(hostList9);
				
				try{
				Thread.sleep(2 * 60 * 1000);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
	 
	    }
	 
		public static void pingar(String [] hostList) throws IOException
		{
			int size = 40;
			int err = 0;
			 for (int i = 0; i < hostList.length; i++) {
	 
	            String url = hostList[i];
	            String status = getStatus(url);
				
				if (!status.startsWith("200"))
					err ++;
					
				for(int t = url.length(); t <= size; t++)
				{
					url += " ";
				}
	            System.err.println(url + "\t\t" + status);
	        }
			
			if ( err == hostList.length)			
				System.err.println("\n---------->> SERVIDOR FORA DO AR <<----------\n");
			else
				System.out.println("-----------------------------------------------------------------\n");
		}
	 
	    public static String getStatus(String url) throws IOException {
	 
	        String result = "";
	        try {
	            URL siteURL = new URL(url);
	            HttpURLConnection connection = (HttpURLConnection) siteURL
	                    .openConnection();
	            connection.setRequestMethod("GET");
	            connection.connect();
	 
	            int code = connection.getResponseCode();
	            if (code == 200) 
	                result = code +" Green";
	            else if(code == 404)
	            	result = code + " ->Red<-";
	            else 
	            	result = code + " ->Red<-";
	        } catch (Exception e) {
	            result = "->Red<-";
	        }
	        return result;
	    }
}
