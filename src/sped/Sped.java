package sped;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Sped
{
	public static final int BLOCO = 1;
	public static final int CHAVE_ACESSO = 10;
	public static final int TIPO_CTE = 13;
	public static final int CHV_CTE_REF = 14;
	
	private String bloco;
	private String chaveAcesso;
	private String tipoCte;
	private String chvCteRef;
	
	@SuppressWarnings("unused")
	private List<String> lerArquivo(String caminho)
	{
		File f = new File(caminho);
		String xml = "";
		List<String> listaBlocoD = new ArrayList<String>();
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(f));
			System.out.println("Lendo arquivo...");
			while (in.ready())
			{
				xml = in.readLine();
				if(xml.startsWith("|D100"))
					listaBlocoD.add(xml);
			}
			in.close();
		}
		catch (Exception e)
		{
			System.out.println("Erro lerXmlGerado: " + e);
		}
		return listaBlocoD;
	}
	
	public List<Sped> getSped(List<String> blocos)
	{
		List<Sped> listaSped = new ArrayList<Sped>();
		String [] parse;
		Sped sped = null;
		
		for (String bloco : blocos)
		{
//			System.out.println(bloco);
			parse = bloco.split(Pattern.quote("|"));
			
			
			sped = new Sped();
			sped.bloco = parse[BLOCO];
			sped.chaveAcesso = parse[CHAVE_ACESSO];
			
			sped.tipoCte = parse[TIPO_CTE];
			sped.chvCteRef = parse[CHV_CTE_REF];
			
			listaSped.add(sped);
		}
		
		return listaSped;
	}
	
	public static void main(String[] args)
	{
		Sped sped = new Sped();
		List<String> lista = sped.lerArquivo("C:\\doc\\SpedEFD.txt");
		
		List<Sped> listaSped = sped.getSped(lista);
		for (Sped sped2 : listaSped)
		{
			System.out.println(sped2.bloco+" "+ sped2.chaveAcesso);
		}
		
		System.out.println(lista.size() +" "+listaSped.size());
	}
	
	
}
