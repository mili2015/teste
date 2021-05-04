package dao;

import geral.Teste;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.EsisavAcesso;

public class UsuarioIPedidos {

	private DAO dao = null;

	private DAO getDAO()
	{
		return (dao == null) ? dao = new DAO() : dao;
	}
	
	public List<EsisavAcesso> findUsuarios() throws Exception
	{
		List<EsisavAcesso> acessos = new ArrayList<EsisavAcesso>();
		Connection con = getDAO().getConnection();
		PreparedStatement st = con
			.prepareStatement("select SISAV_ID  , " +
							"	CODVENDEDOR       , " +
							"	CODVENDEDOR_SISTEMA, " +
							"	CODSUBVENDEDOR      , " +
							"	ID_INTERNET          , " +
							"	SENHA_INTERNET        , " +
							"	PERFIL_INTERNET         " +
							" from esisav_acesso " +
							" where " +
							"	CODVENDEDOR IN (221,388,400,351,406,381,405,410,302,415,373,391,396,412,386,389,402,304," +
							"374,378,404,89,222,19,63,397,408,354,395,284,332,414,242,243,384,345,295," +
							"329,34,253,369,204,341,311,194,50,262,157,362,338,380,148) ");
		
		ResultSet rs = st.executeQuery();
		EsisavAcesso acesso= null;
		while(rs.next())
		{ 
			acesso = new EsisavAcesso();
			acesso.setId(rs.getInt("SISAV_ID"));
			acesso.setCodVendedor(rs.getInt("CODVENDEDOR"));
			acesso.setPerfil("Vendedor");
			acesso.setIdInternet("rep"+acesso.getCodVendedor());
			//acesso.setSenha(Teste.gerarSenha(4));
			acesso.setSenha(rs.getString("SENHA_INTERNET"));
			acessos.add(acesso);
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();
		
		return acessos;
	}
	
	
	public List<String> findEmails() throws Exception
	{
		List<String> listaEmail = new ArrayList<String>();
		Connection con = getDAO().getConnection();
		PreparedStatement st = con
			.prepareStatement("select codcliente,codvendedor cod_rep, " +
					"	CASE " +
					"		   WHEN EMAIL IS NOT NULL THEN EMAIL " +
					"		   WHEN EMAIL_CONTATO_FINANCEIRO IS NOT NULL THEN EMAIL_CONTATO_FINANCEIRO " +
					"		   WHEN EMAIL_NFE IS NOT NULL THEN EMAIL_NFE " +
					"		   ELSE " +
					"				'SEM EMAIL' " +
					"	END email " +
					" from clientes where (email is not null OR EMAIL_CONTATO_FINANCEIRO IS NOT NULL OR EMAIL_NFE IS NOT NULL) " +
					" and (upper(email) not in (select upper(email) from informativo_email_remover) " +
					" and upper(EMAIL_CONTATO_FINANCEIRO) not in (select upper(email) from informativo_email_remover) " +
					" and upper(EMAIL_NFE) not in (select upper(email) from informativo_email_remover))");
		
		ResultSet rs = st.executeQuery();
		String email = null;
		String[] emails = null;
		while(rs.next())
		{ 
			email = rs.getString("email");
			
			if(email == null || email.equals("SEM EMAIL"))
				continue;
			
			emails = email.split(";");
			
			for (String e : emails)
			{
				if(Teste.validaEmail(e))
				{
					listaEmail.add(e+";"+rs.getInt("codcliente")+";"+rs.getInt("cod_rep"));				
				}
			}
			
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();
		
		return listaEmail;
	}
	
	public List<String> findEmailsRemovidos() throws Exception
	{
		List<String> listaEmails = new ArrayList<String>();
		Connection con = getDAO().getConnection();
		PreparedStatement st = con.prepareStatement("select upper(email) EMAIL from informativo_email_remover");
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
			listaEmails.add( rs.getString("EMAIL"));
		}
		
		return listaEmails;
	}
	
	public List<String> findEmails(List<String> listaEmail, String tipo) throws Exception
	{
		Connection con = getDAO().getConnection();
		PreparedStatement st = null;
		//codcliente,codvendedor cod_rep,
		st = con.prepareStatement("select  distinct " +
							"		upper("+tipo+")    EMAIL " +
							" from clientes where "+tipo+" is not null  " +
							" and upper("+tipo+") not in (select upper(email) from informativo_email_remover) ");
		
		ResultSet rs = st.executeQuery();
		
		List<String> removidos = findEmailsRemovidos();
		String email = null;
		String[] emails = null;
		boolean isRemovido = false;
		while(rs.next())
		{ 
			email = rs.getString("EMAIL");
			
			if(email == null || email.equals("SEM EMAIL"))
				continue;
			
			emails = email.split(";");
			
			for (String e : emails)
			{
				e = e.trim();
				if(Teste.validaEmail(e))
				{
					
					for (String removido : removidos)
					{
						if(e.equals(removido))
						{
							isRemovido = true;
							break;
						}
					}
					
					if(!isRemovido)
						listaEmail.add(e);
//						listaEmail.add(e+";"+rs.getInt("codcliente")+";"+rs.getInt("cod_rep"));
					
					isRemovido = false;
				}
			}
			
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();

		if(tipo.equals("EMAIL"))
			findEmails(listaEmail, "EMAIL_CONTATO_FINANCEIRO");
		else if(tipo.equals("EMAIL_CONTATO_FINANCEIRO"))
			findEmails(listaEmail, "EMAIL_NFE");
		
		return listaEmail;
	}
	
	public List<String> findEmailsRep() throws Exception
	{
		List<String> listaEmail = new ArrayList<String>();
		Connection con = getDAO().getConnection();
		PreparedStatement st = con
			.prepareStatement(" select EMAIL  from vendedor where nvl(ativo,'S') != 'N' AND EMAIL LIKE '%@%'");
		
		ResultSet rs = st.executeQuery();
		String email = null;
		String[] emails = null;
		while(rs.next())
		{ 
			email = rs.getString("EMAIL");
			
			if(email == null || email.equals("SEM EMAIL"))
				continue;
			
			emails = email.split(";");
			
			for (String e : emails)
			{
				if(Teste.validaEmail(e))
				{
					listaEmail.add(e);				
				}
			}
			
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();
		
		return listaEmail;
	}
	
	
	public List<String> findTodosEmails() throws Exception
	{
		List<String> acessos = new ArrayList<String>();
		Connection con = getDAO().getConnection();
		PreparedStatement st = con
			.prepareStatement("select 	codcliente,codvendedor cod_rep, " +
					"	email || ';'|| EMAIL_CONTATO_FINANCEIRO || ';' || EMAIL_NFE EMAIL " +
					"from clientes where (email is not null OR EMAIL_CONTATO_FINANCEIRO IS NOT NULL OR EMAIL_NFE IS NOT NULL)");
		
		ResultSet rs = st.executeQuery();
		String email = null;
		String [] emails = null;
		
		while(rs.next())
		{ 
			email = rs.getString("email");
			
			emails = email.split(";");
			
			for(String e : emails)
			{
				if(Teste.validaEmail(e))
				{
					acessos.add(e+";"+rs.getInt("codcliente")+";"+rs.getInt("cod_rep"));				
				}
				else 
				{
					if(e != null && !e.isEmpty())
						System.out.println("ERRO "+e);
					
				}
			}
			
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();
		
		return acessos;
	}
	
	public void updateUsuarios(List<EsisavAcesso> acessos) throws Exception
	{
		Connection con = getDAO().getConnection();
		PreparedStatement st = con
			.prepareStatement("UPDATE esisav_acesso SET " +
							"	ID_INTERNET = ?, " +
							"	SENHA_INTERNET =?, " +
							"	PERFIL_INTERNET =? " +
							" WHERE " +
							"	SISAV_ID =? ");
		
		for(EsisavAcesso e: acessos)
		{
			st.setString(1, e.getIdInternet());
			st.setString(2, e.getSenha());
			st.setString(3, e.getPerfil());
			st.setInt(4, e.getId());
			
			st.executeUpdate();
			st.clearParameters();
		}

		st.close();
		st = null;
		getDAO().fecharConexao();
	}
	
	
	public void testSession() throws Exception
	{
		Connection con = getDAO().getConnection();
		
		con.setAutoCommit(false);
		
		System.out.println("con.isClosed() : "+con.isClosed());
		System.out.println("con.isValid(30) : "+con.isValid(30));
		
		PreparedStatement st = con
			.prepareStatement("INSERT INTO CAMINHAO_FELIPE (CODCAMINHAO,CODTRANSP) VALUES(11833,127) ");

		st.executeUpdate();
		st.close();
		
		System.out.println("con.isClosed() : "+con.isClosed());
		System.out.println("con.isValid(30) : "+con.isValid(30));
		
		
		PreparedStatement st2 = con
				.prepareStatement("update CAMINHAO_FELIPE set marca = 'felipe' where codcaminhao = 11833 ");
		
		st2.executeUpdate();
		st2.close();
		
		con.rollback();
		
		con.close();
		
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		UsuarioIPedidos u = new UsuarioIPedidos();
		try 
		{
			
//			u.testSession();
			
//			List<String> emails = u.findEmails();
			
			List<String> emails = new ArrayList<String>();
			u.findEmails(emails, "EMAIL");
			
			
			
			
			
			File f = new File("C:\\arquivosXML\\contatos-clientes-mili.txt");
			FileWriter fw = new FileWriter(f);
//			
			StringBuilder sb = new StringBuilder();
			
			for (String e : emails)
			{
				sb.append(e);
				sb.append(";\r\n");
			}
			
//			
			fw.write(sb.toString());
			fw.flush();
			fw.close();
			System.out.println("Total "+emails.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
