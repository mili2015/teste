package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.EsisavAcesso;

public class RmDao
{

	private DAO dao = null;

	private DAO getDAO()
	{
		return (dao == null) ? dao = new DAO() : dao;
	}
	
	public void findUsuarios() throws Exception
	{
		List<EsisavAcesso> acessos = new ArrayList<EsisavAcesso>();
		Connection con = getDAO().getConnection();
		PreparedStatement st = con
			.prepareStatement("Select  chapa from pfunc@dblrm where chapa = 2902 ");

		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{ 
			System.out.println("Chapa: "+rs.getString("chapa"));
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();
	}
	
	
	public StringBuilder findPonto(String chapa) throws Exception
	{
		List<String> registros = new ArrayList<String>();
		Connection con = getDAO().getConnection();
		
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append("	SELECT							");
		sb.append("		INICIOMENSAL,						");
		sb.append("		FIMMENSAL, 						");
		sb.append("		MESCOMP,						");
		sb.append("		ANOCOMP,						");
		sb.append("		DESCRICAOMENSAL ,						");
		sb.append("		AAF.DATA,						");
		sb.append("		TO_CHAR(AAF.DATA, 'DY', 'NLS_DATE_LANGUAGE= ''BRAZILIAN PORTUGUESE''') DIA,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(SUM(ENT1)),1,10) BAT1,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(SUM(SAI1)),1,10) BAT2,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(SUM(ENT2)),1,10) BAT3,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(SUM(SAI2)),1,10) BAT4,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(SUM(ENT3)),1,10) BAT5,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(SUM(SAI3)),1,10) BAT6,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(HTRAB),1,10) HORAS_TRAB,   						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(EXTRAAUTORIZADO),1,10) HORAS_EXTRA,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(ADICIONAL),1,10) ADICIONAL,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(ATRASO),1,10) ATRASO,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(FALTA),1,10) FALT,  						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(ABONO),1,10) ABONO,						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(COMPENSADO ),1,10) COMPENSADO,   						");
		sb.append("		SUBSTR(MINUTO_TO_HORA(DESCANSO  ),1,10) DESCANSO						");
		sb.append("	FROM							");
		sb.append("		(SELECT 						");
		sb.append("			CODCOLIGADA,					");
		sb.append("			CHAPA, 					");
		sb.append("			  DATA,					");
		sb.append("			  CASE WHEN SEQUENCIA = 1 THEN BATIDA ELSE 0 END ENT1,					");
		sb.append("			  CASE WHEN SEQUENCIA = 2 THEN BATIDA ELSE 0 END SAI1,					");
		sb.append("			  CASE WHEN SEQUENCIA = 3 THEN BATIDA ELSE 0 END ENT2,					");
		sb.append("			  CASE WHEN SEQUENCIA = 4 THEN BATIDA ELSE 0 END SAI2,					");
		sb.append("			  CASE WHEN SEQUENCIA = 5 THEN BATIDA ELSE 0 END ENT3,					");
		sb.append("			  CASE WHEN SEQUENCIA = 6 THEN BATIDA ELSE 0 END SAI3					");
		sb.append("		 FROM (SELECT ROW_NUMBER() OVER(PARTITION BY 						");
		sb.append("							ABT.CHAPA, 	");
		sb.append("							 ABT.DATA 	");
		sb.append("					 	ORDER BY 		");
		sb.append("							ABT.CHAPA, 	");
		sb.append("							ABT.DATA, 	");
		sb.append("							ABT.BATIDA) SEQUENCIA,	");
		sb.append("				ABT.CODCOLIGADA,				");
		sb.append("				ABT.CHAPA, 				");
		sb.append("				ABT.DATA, 				");
		sb.append("				ABT.BATIDA				");
		sb.append("			 FROM 					");
		sb.append("				V_ABATFUN@DBLRM ABT 				");
		sb.append("			WHERE 					");
		sb.append("				ABT.DATA >= '01-jan-2014'				");
		sb.append("				AND CHAPA = ? )				");
		sb.append("		) BAT,						");
		sb.append("		V_AAFHTFUN@DBLRM AAF,						");
		sb.append("		APERIODO@DBLRM PER						");
		sb.append("	WHERE							");
		sb.append("		BAT.CHAPA (+) = AAF.CHAPA						");
		sb.append("		AND BAT.DATA (+)  = AAF.DATA						");
		sb.append("		AND BAT.CODCOLIGADA (+) = AAF.CODCOLIGADA						");
		sb.append("		AND AAF.DATA BETWEEN INICIOMENSAL AND FIMMENSAL      						");
		sb.append("		AND AAF.DATA >= '01-jan-2014'						");
		sb.append("		AND AAF.CHAPA = ?						");
		sb.append("	GROUP BY							");
		sb.append("		AAF.CHAPA,						");
		sb.append("		INICIOMENSAL,						");
		sb.append("		FIMMENSAL, 						");
		sb.append("		MESCOMP,						");
		sb.append("		ANOCOMP,						");
		sb.append("		DESCRICAOMENSAL,						");
		sb.append("		AAF.DATA,						");
		sb.append("		ATRASO ,        						");
		sb.append("		FALTA ,         						");
		sb.append("		HTRAB ,         						");
		sb.append("		EXTRAAUTORIZADO,						");
		sb.append("		ADICIONAL ,     						");
		sb.append("		ABONO ,         						");
		sb.append("		COMPENSADO ,    						");
		sb.append("		DESCANSO 						");
		sb.append("	ORDER BY							");
		sb.append("		AAF.CHAPA,						");
		sb.append("		AAF.DATA						");

		PreparedStatement st = con.prepareStatement(sb.toString());
		st.setString(1, chapa);
		st.setString(2, chapa);
		ResultSet rs = st.executeQuery();
		
		StringBuilder registro = new StringBuilder();
		registro.append("Ini Mês;Fim Mês;Mês;Ano;Descrição;Data;Dia;Bat1;Bat2;Bat3;Bat4;Bat5;Bat6;Horas Trab; Horas Extras; Ad. Noturno; Atraso; Falta; Abono; Compensado; Descanso;\r");
		
		while(rs.next())
		{ 
			for(int i = 1; i <= 21; i++)
			{
				//System.out.print(rs.getString(i)+";");
				registro.append(rs.getString(i) != null ? rs.getString(i) +";" : "" +";");
			}
			registro.append("\r");
			//System.out.println("");
		}

		rs.close();
		st.close();
		st = null;
		getDAO().fecharConexao();
		
		return registro;
	}
	
	public static void main(String[] args)
	{
		RmDao rm = new RmDao();
		try
		{
			//rm.findUsuarios();
			StringBuilder registro = rm.findPonto("02902");
			
			File f = new File("C:\\arquivosXML\\ponto\\ponto.csv");
			
			FileOutputStream fos;
			try
			{
				fos = new FileOutputStream(f);
				fos.write(registro.toString().getBytes());
				
				fos.flush();
				fos.close();
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
