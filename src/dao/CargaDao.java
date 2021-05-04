package dao;

import geral.Teste;
import google.rota.Leg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CargaDao
{
	
	private DAO dao = null;

	private DAO getDAO()
	{
		return (dao == null) ? dao = new DAO() : dao;
	}
	
	public List<String> findCidades(int nrCarga) throws Exception
	{
		List<String> listaCidades = new ArrayList<String>();
		Connection con = getDAO().getConnection();
		
		PreparedStatement st = con
			.prepareStatement(" select 	distinct " +
					"	REPLACE(d.NOME_CIDADE_IBGE, ' ', '%20')|| ',' || a.uf || '|'  cidades " +
					"from " +
					"	clientes a, " +
					"	pedidos b, " +
					"	cargas c, " +
					"	ct_cidade d " +
					"where " +
					"	a.codcliente = b.codcliente " +
					"and a.ctcid_cidade = d.ctcid_cidade " +
					"and a.uf = d.uf " +
					"and b.nrcarga = c.nrcarga " +
					"and c.nrcarga = ? ");
		
		st.setInt(1, nrCarga);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{ 
			listaCidades.add(rs.getString("cidades"));
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();
		
		return listaCidades;
	}
	
	public Leg findOrigemDestino(int nrCarga) throws Exception
	{
		Leg leg = null;
		Connection con = getDAO().getConnection();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("		REPLACE(D.CIDADE, ' ','%20')|| ',' || D.UF ORIGEM, ");	
		sb.append("		DECODE(C.CTCID_CIDADE, NULL, '', C.CTCID_CIDADE ||','|| C.UF) ORIGEM_OPERADOR, ");
		sb.append("		REPLACE(E.NOME_CIDADE_IBGE, ' ','%20') ||','|| A.UF DESTINO ");		
		sb.append("FROM ");
		sb.append("		CARGAS A, ");
		sb.append("		OPERADOR_LOGISTICO B, ");
		sb.append("		EF_TRANSPORTADOR C, ");
		sb.append("		CT_EMPRESA D, ");
		sb.append("		CT_CIDADE E ");
		
		sb.append("	WHERE ");
		sb.append("		A.CODIGO_OPERADOR = B.CODIGO_OPERADOR (+) ");
		sb.append("		AND B.EFT_CODIGO = C.EFT_CODIGO (+) ");
		sb.append("		AND TO_NUMBER( A.LOCAL_CARREGAMENTO) = D.CTEMP_CODIGO ");
		sb.append("		AND A.DESTINO = E.CTCID_CIDADE ");
		sb.append("		AND A.UF = E.UF ");
		sb.append("		AND A.NRCARGA = ?");
		
		
		PreparedStatement st = con.prepareStatement(sb.toString());
		st.setInt(1, nrCarga);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next())
		{ 
			leg = new Leg();
			
			if(rs.getString("ORIGEM_OPERADOR") != null)
				leg.setStartAddress(rs.getString("ORIGEM_OPERADOR"));
			else
				leg.setStartAddress(rs.getString("ORIGEM"));
			
			leg.setEndAddress(rs.getString("DESTINO"));
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();
		
		return leg;
	}
}
