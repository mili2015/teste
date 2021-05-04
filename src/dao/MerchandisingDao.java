package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.language.MatchRatingApproachEncoder;

public class MerchandisingDao
{
	private MatchRatingApproachEncoder match = new MatchRatingApproachEncoder();
	private DAO dao = null;

	private DAO getDAO()
	{
		return (dao == null) ? dao = new DAO() : dao;
	}
	
	public List<Promotor> findPromotoresByAgencia(int idAgencia) throws Exception
	{
		List<Promotor> lista = new ArrayList<Promotor>();
		Connection con = getDAO().getConnection();
		
		PreparedStatement st = con
			.prepareStatement(" select 	MCDPD_CODIGO, NOME FROM mcd_promotor_demonstrador WHERE MCDA_CODIGO = ? ");
		
		st.setInt(1, idAgencia);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{ 
			lista.add(new Promotor(rs.getInt("MCDPD_CODIGO"), rs.getString("NOME")));
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();
		
		return lista;
	}
	
	public List<String> getPromotorFile(String path) throws IOException
	{
		List<String> lista = new ArrayList<String>();
		
		File f = new File(path);
		
		BufferedReader reader = new BufferedReader(new FileReader(f));
		
		String nome = null;
		String [] array = null;
		while(reader.ready())
		{
			nome = reader.readLine();
			array = nome.split(";");
			
			lista.add(array[1]);
		}
		
		return lista;
	}
	
	public boolean matchPromotor(String name1, String name2){
		
		name1 = name1.replaceAll(" DE ", " ").replaceAll(" DA ", " ").replaceAll(" DOS ", " ");
		name2 = name2.replaceAll(" DE ", " ").replaceAll(" DA ", " ").replaceAll(" DOS ", " ");
		
		String [] names1 = name1.split(" ");
		String [] names2 = name2.split(" ");

		int goal = 75;
		float length = names1.length;
		int totalMatch = 0;

		for (int i = 0; i < names1.length; i++)
		{
			for (int j = 0; j < names2.length; j++)
			{
				if(match.isEncodeEquals(names1[i], names2[j]))
				{
					names2[j] = "";
					totalMatch ++;
					break;
				}
			}
		}

		float perc = (totalMatch / length) * 100;

		if(length == 2)
			goal = 50;
		
		return perc >= goal;
	}
	
	private class Promotor
	{
		int id;
		String nome;
		/**
		 * @param id
		 * @param nome
		 */
		public Promotor(int id, String nome)
		{
			super();
			this.id = id;
			this.nome = nome;
		}
	}
	
	public static void main(String[] args)
	{
		MerchandisingDao dao = new MerchandisingDao();
		
		try
		{
			List<String> lista1 = dao.getPromotorFile("C:\\doc\\Promotores_596.csv");
			List<Promotor> lista2 = dao.findPromotoresByAgencia(596);
			
			for (String nome : lista1)
			{
				System.out.println("Nome CSV: " + nome);
				for (Promotor promotor : lista2)
				{
					if (dao.matchPromotor(nome, promotor.nome))
						System.out.println("------> Nome Promotor: " + promotor.id + " " + promotor.nome);		
				}
			}
			
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
