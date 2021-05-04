package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cte.Cte;
import oracle.jdbc.OracleResultSet;
import oracle.xdb.XMLType;

public class CteDao
{

	private DAO dao = null;

	private DAO getDAO()
	{
		return (dao == null) ? dao = new DAO() : dao;
	}
	
	public List<Cte> findChaveCte(String periodo) throws Exception
	{
		List<Cte> listCte = new ArrayList<Cte>();
		Connection con = getDAO().getConnection();
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT NFE_CHAVE_ACESSO, NFE_XML ");
		sb.append(" FROM ");
		sb.append("	NFE.NFE_XML_ENTRADA ");
		sb.append(" WHERE  ");
		sb.append(" NFE_CHAVE_ACESSO IN   ");
		sb.append(" ( SELECT CTE_CHAVE_ACESSO FROM ES_CTE_FRETE_NF_REF WHERE NVL(IMPRESSO,'N') != 'S' AND TRUNC(DATA_ENTRADA, 'MON') = TRUNC(TO_DATE(?,'MM-YYYY'), 'MON') AND CTE_CHAVE_ACESSO IS NOT NULL ) ");
		
		sb.append(" UNION ALL ");
		
		sb.append(" SELECT NFE_CHAVE_ACESSO, NFE_XML ");
		sb.append(" FROM ");
		sb.append("	NFE_XML_ENTRADA ");
		sb.append(" WHERE  ");
		sb.append(" NFE_CHAVE_ACESSO IN   ");
		sb.append(" ( SELECT CTE_CHAVE_ACESSO FROM ES_CTE_FRETE_NF_REF WHERE NVL(IMPRESSO,'N') != 'S' AND TRUNC(DATA_ENTRADA, 'MON') = TRUNC(TO_DATE(?,'MM-YYYY'), 'MON') AND CTE_CHAVE_ACESSO IS NOT NULL ) ");
			
		
		System.out.println("1- findChaveCte "+ periodo);
		PreparedStatement st = con.prepareStatement(sb.toString());

		st.setString(1, periodo);
		st.setString(2, periodo);
		
		ResultSet rs = st.executeQuery();
		
		//System.out.println("2- findChaveCte "+ periodo);
		OracleResultSet orset = (OracleResultSet) rs;	
		Cte cte = null;
		while(orset.next())
		{
			//System.out.println("3- findChaveCte ");
			cte = new Cte();
			
			XMLType poxml = XMLType.createXML(orset.getOPAQUE("NFE_XML"));
			String sXml = ((XMLType) poxml).getStringVal();
			
			cte.setChaveAcesso(rs.getString("NFE_CHAVE_ACESSO"));
			cte.setXml(sXml);
			
			listCte.add(cte);
			//System.out.println("4- findChaveCte " + listCte.size() + "  - " + cte.getChaveAcesso());
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();
		
		return listCte;
	}
	
	public void updateStatus(String chaveAcesso, String impresso )
	{
		Connection con = getDAO().getConnection();
		try
		{
			//PreparedStatement st = con.prepareStatement("UPDATE CTE_CHAVE_ACESSO_PDF SET IMPRESSO=?, DATA_IMPRESSAO = SYSDATE WHERE CHAVE_ACESSO =?");
			PreparedStatement st = con.prepareStatement("UPDATE ES_CTE_FRETE_NF_REF SET IMPRESSO=?, DATA_IMPRESSAO = SYSDATE WHERE CTE_CHAVE_ACESSO =?");
			
			st.setString(1, impresso);
			st.setString(2, chaveAcesso);
			
			st.executeUpdate();
			getDAO().fecharConexao();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(String chaveAcesso, String periodo)
	{
		Connection con = getDAO().getConnection();
		try
		{
			//PreparedStatement st = con.prepareStatement("UPDATE CTE_CHAVE_ACESSO_PDF SET IMPRESSO=?, DATA_IMPRESSAO = SYSDATE WHERE CHAVE_ACESSO =?");
			PreparedStatement st = con.prepareStatement("INSERT INTO CTE_CHAVE_ACESSO_PDF (CHAVE_ACESSO, periodo ) VALUES (?, ?) ");
			
			st.setString(1, chaveAcesso);
			st.setString(2, periodo);
			
			st.executeUpdate();
			
		}
		catch (SQLException e)
		{
			System.out.println("Erro chave " + chaveAcesso);
			e.printStackTrace();
		}
		finally {
			getDAO().fecharConexao();
		}
	}
	
}
