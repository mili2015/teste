package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class Ftp
{

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		FTPClient ftp = new FTPClient();

		try
		{
			// Fazendo a conexão
			//ftp.connect("200.203.189.196");
			ftp.connect("172.10.1.8");
//			ftp.connect("172.2.1.58");
			System.out.println("conetou: " + ftp.isConnected());
			// Efetuando o Login
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode()))
			{
//				System.out.println("logado: "+ftp.login("aa0001", "qwmi138"));
//				System.out.println("logado: "+ftp.login("sysrep2", "KYW12ZQZ99UNDO"));
				System.out.println("logado: "+ftp.login("johndoe", "teste123"));
			}
			else
			{
				// erro ao se conectar
				ftp.disconnect();
				System.out.println("Conexão recusada");
				System.exit(1);
			}

			// Mundando o diretório de trabalho

			ftp.changeWorkingDirectory("/home/INFORMATIVOS_IPD/INFORMATIVOS_IPD");
			System.out.println(ftp.printWorkingDirectory());
			ftp.setFileType( FTPClient.BINARY_FILE_TYPE );   
			
			// Fazendo o upload do arquivo artigoFTP.doc para o ftp com um novo
			// nome (meuarquivo.doc)

//			 FileInputStream arqEnviar = new
//			 FileInputStream("C:\\TABELA_SUPERMINAS-2009.xls");
//			 if (ftp.storeFile("TABELA_SUPERMINAS-2009.xls", arqEnviar))
//			 System.out.println("Arquivo armazenado com sucesso!");
//			 else
//			 System.out.println("Erro ao armazenar o arquivo.");


			// Adiquirindo o nome dos arquivos / diretórios existentes

			
			 String[] arq = ftp.listNames(); 
			 System.out.println("Listando arquivos: \n");
			 
			 for (String f : arq) {
			 
			 System.out.println(f);
			 
			 }
			// Fazendo o download do arquivo RD-4410-00412[192017].log para
				// meulog.log

				FileOutputStream fos =	new FileOutputStream("C:\\TABELA_SUPERMINAS-20092.xls");

//				if (ftp.retrieveFile("TABELA_SUPERMINAS-2009.xls", fos))
//					System.out.println("Download efetuado com sucesso!");
//				else
//					System.out.println("Erro ao efetuar download do arquivo.");

				InputStream is = ftp.retrieveFileStream("TABELA_SUPERMINAS-2009.xls");

				int i;
				while((i = is.read()) !=-1)
				{
					fos.write(i);	
					System.out.println("i: "+i);
				}
				
				if (fos != null)
				{
					fos.flush();
					fos.close();
				}
				if (is != null)
				{
					is.close();
				}
		}
		catch (SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{

			if (ftp.isConnected())
			{
				try
				{
					ftp.logout();
					ftp.disconnect();
				}
				catch (IOException ioe)
				{
					// do nothing
				}
			}
		}

	}
	
	

}
