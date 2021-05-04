package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.nfs.ConsultarSituacaoLoteRpsResposta;
import model.nfs.EnviarLoteRpsResposta;
import model.nfs.ListaMensagemRetorno;
import model.nfs.MensagemRetorno;

import com.thoughtworks.xstream.XStream;

public class NfsStream
{
	public String lerArquivo(String arquivo) throws IOException
	{
		FileReader in = new FileReader("C:" + File.separator +"arquivosXML" + File.separator + arquivo);
		BufferedReader br = new BufferedReader(in);
		String xml ="";
		while(br.ready())
		{
			xml += br.readLine();
			//System.out.println(xml);
		}
		return xml;
	}
	
	public static void main(String[] args)
	{
		NfsStream nf = new NfsStream();
		try
		{
			String retorno = nf.lerArquivo("erroNFS_2.txt");
			System.out.println(retorno);
			
			XStream x = new XStream();
			
			Class [] classes = {ConsultarSituacaoLoteRpsResposta.class,ListaMensagemRetorno.class,MensagemRetorno.class};
			x.processAnnotations(classes);
			ConsultarSituacaoLoteRpsResposta consReciNFe = (ConsultarSituacaoLoteRpsResposta) x.fromXML(retorno);
			System.out.println("foi "+consReciNFe.getListaMensagemRetorno().getMensagemRetorno().size());
			
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
