package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import danfe.Nfe;
import oracle.jdbc.OracleResultSet;
import oracle.xdb.XMLType;

public class NfeDao
{

	private DAO dao = null;

	public DAO getDAO()
	{
		return (dao == null) ? dao = new DAO() : dao;
	}
	
	public List<Nfe> findNfe(String periodo) throws Exception
	{
		List<Nfe> listCte = new ArrayList<Nfe>();
		Connection con = getDAO().getConnection();
		
		StringBuilder sb = new StringBuilder();
		/*sb.append("  SELECT ");
		sb.append("  	A.NRNFISCAL, ");
		sb.append("  	A.SERIE, ");
		sb.append("  	A.NFE_CHAVE_ACESSO CHAVE_ACESSO ");
		sb.append("  FROM ");
		sb.append("  	NFE_CONTROLE A, ");
		sb.append("  	ES_NFE_FRETE_NF_REF B ");
		sb.append("  WHERE ");
		sb.append("  	B.CHAVE_NFE_REF = A.NFE_CHAVE_ACESSO ");
		sb.append("  	AND TRUNC(B.DATA_ENTRADA,'MON') = TRUNC(TO_DATE(?,'MM-YYYY'), 'MON')  ");
		sb.append("  	AND B.IMPRESSO IS NULL ");*/
		
		
		/*sb.append(" SELECT NFE_CHAVE_ACESSO, NFE_XML ");
		sb.append(" FROM ");
		sb.append("	NFE.NFE_XML ");
		sb.append(" WHERE  ");
		sb.append(" NFE_CHAVE_ACESSO IN   ");
		sb.append(" ( SELECT CHAVE_NFE_REF FROM ES_NFE_FRETE_NF_REF WHERE NVL(IMPRESSO,'N') != 'S' AND TRUNC(DATA_ENTRADA, 'MON') = TRUNC(TO_DATE(?,'MM-YYYY'), 'MON') AND CHAVE_NFE_REF IS NOT NULL ) ");		
		sb.append(" UNION ALL ");		
		sb.append(" SELECT NFE_CHAVE_ACESSO, NFE_XML ");
		sb.append(" FROM ");
		sb.append("	NFE_XML ");
		sb.append(" WHERE  ");
		sb.append(" NFE_CHAVE_ACESSO IN   ");
		sb.append(" ( SELECT CHAVE_NFE_REF FROM ES_NFE_FRETE_NF_REF WHERE NVL(IMPRESSO,'N') != 'S' AND TRUNC(DATA_ENTRADA, 'MON') = TRUNC(TO_DATE(?,'MM-YYYY'), 'MON') AND CHAVE_NFE_REF IS NOT NULL ) ");
		
		sb.append(" UNION ALL ");
		
		sb.append(" SELECT NFE_CHAVE_ACESSO, NFE_XML ");
		sb.append(" FROM ");
		sb.append("	NFE_XML_ENTRADA ");
		sb.append(" WHERE  ");
		sb.append(" NFE_CHAVE_ACESSO IN   ");
		sb.append(" ( SELECT CHAVE_NFE_REF FROM ES_NFE_FRETE_NF_REF WHERE NVL(IMPRESSO,'N') != 'S' AND TRUNC(DATA_ENTRADA, 'MON') = TRUNC(TO_DATE(?,'MM-YYYY'), 'MON') AND CHAVE_NFE_REF IS NOT NULL ) ");
		sb.append(" UNION ALL ");
		sb.append(" SELECT NFE_CHAVE_ACESSO, NFE_XML ");
		sb.append(" FROM ");
		sb.append("	NFE.NFE_XML_ENTRADA ");
		sb.append(" WHERE  ");
		sb.append(" NFE_CHAVE_ACESSO IN   ");
		sb.append(" ( SELECT CHAVE_NFE_REF FROM ES_NFE_FRETE_NF_REF WHERE NVL(IMPRESSO,'N') != 'S' AND TRUNC(DATA_ENTRADA, 'MON') = TRUNC(TO_DATE(?,'MM-YYYY'), 'MON') AND CHAVE_NFE_REF IS NOT NULL ) ");
		*/
		
		sb.append("		SELECT NFE_CHAVE_ACESSO, NFE_XML FROM NFE.NFE_XML WHERE NFE_CHAVE_ACESSO IN ( ");
		sb.append("		'35130444589885000181550010000555841741939712', ");
		sb.append("		'42130678908266000205550010001403231517043177', ");
		sb.append("		'35131055834774000614550010000089121000089120', ");
		sb.append("		'41131178908266000124550010000355121163857158', ");
		sb.append("		'35130601744586000161550010000207741085855625', ");
		sb.append("		'42130778908266000205550010001523751578196782', ");
		sb.append("		'41131178908266000477550010007801171002255650') ");
		
		
		
		PreparedStatement st = con.prepareStatement(sb.toString());

		/*st.setString(1, periodo);
		st.setString(2, periodo);
		st.setString(3, periodo);
		st.setString(4, periodo);*/
		
		ResultSet rs = st.executeQuery();
		
		OracleResultSet orset = (OracleResultSet) rs;	
		Nfe nfe = null;
		while(orset.next())
		{ 
			nfe = new Nfe();
//			
			XMLType poxml = XMLType.createXML(orset.getOPAQUE("NFE_XML"));
			String sXml = ((XMLType) poxml).getStringVal();
//			
			nfe.setXml(sXml);
			
			nfe.setChaveAcesso(rs.getString("NFE_CHAVE_ACESSO"));
//			nfe.setNrNfiscal(rs.getInt("NRNFISCAL"));
//			nfe.setSerie(rs.getString("SERIE"));
			
			listCte.add(nfe);
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
			PreparedStatement st = con.prepareStatement("UPDATE ES_NFE_FRETE_NF_REF SET IMPRESSO=?, DATA_IMPRESSAO = SYSDATE WHERE CHAVE_NFE_REF =?");
			
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
	
}
