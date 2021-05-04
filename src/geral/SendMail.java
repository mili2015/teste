package geral;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

 


public class SendMail extends Authenticator
{
//	private static final String smtpHost = "172.2.1.8";
	private static final String smtpHost = "172.10.1.4";
//	private static final String smtpHost = "mili.local";
	private static final String mailer = "JavaMail API"; 
	private static Session session = null;

	  @Override   
	    public PasswordAuthentication getPasswordAuthentication() {   
	        String username = "caxxxxxxxxxxxxxx@yahoo.com.br";   
	        String password = "xxxxxxxxx";   
	        return new PasswordAuthentication(username, password);   
	    }   
	
	
	  public static void sendMail(String cabecalho, String corpo, String rodape,String messageSubject, String[] messageTo,String caminhoAnexo) 
		{
			Properties p = new Properties();
			p.put("mail.host", "172.10.1.4");
			// p.put("mail.user", "e-projetos@mili.com.br");

			if (session == null) {
				session = Session.getDefaultInstance(p, null);
			}
			session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);

			try {

				Multipart multipart = new MimeMultipart();

				// PLAIN TEXT
				BodyPart messageBodyPart = new MimeBodyPart();
				// messageBodyPart.setText("Here is your plain text message");
				// multipart.addBodyPart(messageBodyPart);

				// HTML TEXT
				messageBodyPart = new MimeBodyPart();

				String htmlText = corpo;
				messageBodyPart.setContent(htmlText, "text/html");
				multipart.addBodyPart(messageBodyPart);

				// msg.setText(messageText);
				msg.setSubject(messageSubject);
				
				//de
				Address fromAddr = new InternetAddress("falecom@mili.com.br");
				msg.setFrom(fromAddr);

				//para
				Address toAddr = null;
				for (int i = 0; i < messageTo.length; i++) {
					toAddr = new InternetAddress(messageTo[i]);
					msg.addRecipient(Message.RecipientType.BCC, toAddr);
				}
				
				if(caminhoAnexo!=null && !caminhoAnexo.trim().equals(""))
				{
					// cria a segunda parte da mensage
					MimeBodyPart bodyPart2 = new MimeBodyPart();
		
					// anexa o arquivo na mensagem
					FileDataSource fds = new FileDataSource(caminhoAnexo);
					bodyPart2.setDataHandler(new DataHandler(fds));
					bodyPart2.setFileName(fds.getName());
					multipart.addBodyPart(bodyPart2);
				}	

				msg.setContent(multipart);

				Transport.send(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public static void main(String []args)
	{
		String msg ="<html><body>" +
				"<div>Prezado cliente,<br/>Este é um informativo elaborado pela Mili S.A. para mantê-lo informado de suas novidades em produtos, ações sociais e outros acontecimentos do nosso segmento.<br/>" +
				"Para acessar as novidades desta semana, como o lançamento dos amaciantes com 5 litros, clique no link abaixo:<br/>" +
				" <a href='http://www.mili.com.br/imagens/informativo.png' >http://www.mili.com.br/imagens/informativo.png</a> </div>" +
				"<div><img src='http://www.mili.com.br/imagens/informativo.png'/></div>" +				
				" </body></html>";
		
		
		
//		SendMail.sendMail("Teste com anexo", msg, "", "Teste Informativo", new String[]{"elianesouza@mili.com.br","edvaldo@mili.com.br","jackson@mili.com.br","cinthia@mili.com.br","felipe.alves@mili.com.br"},null);
		SendMail.sendMail("Informativo Mili S.A. - 27/08/09", msg, "", "Informativo Mili S.A. - 27/08/09", new String[]{"vieira_felipe_alves@hotmail.com","felipe.alves@mili.com.br"},null);
		
	}
}
