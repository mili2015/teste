package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public final class DAO
{
	/**
	 * Esta classe � respons�vel por criar conexao com o banco de dados
	 */
	private Connection conexao; //objeto que ir� guardar a conexao com banco de dados 
	private Statement st; //objeto que ir� executar as instru��es sql.

	 /**
	  * M�todo que abre a conex�o com o banco de dados
	  *
	  */
	 private void criarConexao()
	 {
		 try
		 {
			if(conexao==null || conexao.isClosed())
			{	
				//String url = "jdbc:oracle:thin:@172.2.1.12:1521:oracle"; //homologa��o
				String url = "jdbc:oracle:thin:@(DESCRIPTION = " +
						"(ADDRESS = " +
						"	(PROTOCOL = TCP) " +
						"	(HOST = mili-scan.mili.local)(PORT = 1521)) " +
						"(LOAD_BALANCE = ON) " +
						"(FAILOVER = ON) " +
						"(CONNECT_DATA = " +
						"	(SERVER = DEDICATED) " +
						"	(SERVICE_NAME = mili) " +
						"	(FAILOVER_MODE = " +
						"		(TYPE = select) " +
						"		(METHOD = basic) " +
						"		(RETRIES = 64) " +
						"		(DELAY = 4))))"; 
				
	    		//String usr = "webservices"; //usu�rio do banco
	    		//String pwd = "wbp6th"; //senha do banco
				String usr = "master"; //usu�rio do banco
	    		String pwd = "retsam"; //senha do banco
	    		Class.forName("oracle.jdbc.driver.OracleDriver"); // Driver JDBC de conexao
	    		conexao = DriverManager.getConnection(url, usr, pwd.trim()); // abre a conexao com o banco
			}
			/*if(conexao==null || conexao.isClosed())
			{
				InitialContext ctx = new InitialContext();
				DataSource db = (DataSource)ctx.lookup("java:comp/env/jdbc/db2");
				conexao = db.getConnection();
		    }*/
		    st=conexao.createStatement();
		}
	    catch(Exception e)
		{
	    	System.out.println("Erro ao criar Conexao :"+e);
	    	e.printStackTrace();
		}
	 }
	 
	 public Connection getConnection()
	 {
		 if(this.conexao==null)
			 criarConexao();
		 
		 return this.conexao;
	 }
	 
	 public PreparedStatement getPreparedStatement(String sql) throws SQLException
	 {
		criarConexao();
		return conexao.prepareStatement(sql);
	 }

	 /**
	  * M�todo respons�vel por fechar a conex�o com o banco de dados.<br>
	  * Este m�todo pode ser chamado a qualquer momento, e j� est� programado para ser executado na finaliza��o da classe
	  */
	 public void fecharConexao()
	 {
	 	try
		{
	 		st.close();// fecha o statement
	 		conexao.close(); // fecha a conex�o com o bdst
	 		st=null;
	 		conexao=null;
		}
	 	catch(Exception e)
		{
	 		System.out.println("Erro ao fechar conex�o Oracle");
		}
	 }
	 	 
	 /**
	  * M�todo respons�vel por fazer todas as consultas no banco de dados.
	  * @param select <code>String</code> Neste par�metro � passada a instr��o sql de select j� pronta.
	  * @return ResultSet Retorna um ResultSet com todos os registros resultantes da consulta
	 * @throws SQLException 
	  */
	 public ResultSet select(String select) throws SQLException
	 {	
 		criarConexao();
 		return st.executeQuery(select); //executa o select
	 }
	 
	 /**
	  * M�todo respons�vel por fazer todas as inser��es no banco de dados.
	  * @param select <code>String</code> Recebe neste par�metro a instru��o sql de insert j� pronta
	  * @return boolean Retorna falso caso o insert d� erro
	  */	 
	 public boolean insert(String insert) throws SQLException
	 {	
 		criarConexao();
	 	boolean retorno=st.execute(insert);//executa o insert
	 	st.close();
	 	return retorno;
	 }

	 
	 /**
	  * M�todo respons�vel por fazer todas as exclus�es no banco de dados.
	  * @param delet <code>String</code> Recebe neste par�metro a instru��o sql de delete j� pronta
	  * @return boolean Retorna falso caso o delete d� erro
	  */
	 public boolean delete(String delete) throws SQLException
	 {	
		criarConexao();
		boolean retorno= st.execute(delete);
		st.close();
	 	return retorno;
	}
	 
	 /**
	  * M�todo respons�vel por fazer todas as altera��es no banco de dados.
	  * @param update <code>String</code> Recebe neste par�metro a instru��o sql de update j� pronta
	  * @return boolean Retorna falso caso o update d� erro
	  */
	 public boolean update(String update) throws SQLException
	 {	
		criarConexao();
		boolean retorno= st.execute(update);
		st.close();
	 	return retorno;
	}
	 
	/* public static void main(String[] args)
	 {
		 DAO dao = new DAO();
		 
		 try
		 {
			ResultSet rs = dao.select("select * from sgt.caces");
			while(rs.next())
			{
				System.out.println(rs.getString("DESCRICAO"));
				
			}
		} 
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }*/
}