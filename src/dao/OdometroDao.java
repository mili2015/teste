package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Odometro;

public class OdometroDao
{
	private DAO dao = null;

	private DAO getDAO()
	{
		return (dao == null) ? dao = new DAO() : dao;
	}
	
	public List<Odometro> findOdometro(int codVendedor)
	{
		List<Odometro> lista = new ArrayList<Odometro>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("		ID,      ");     
		sb.append("		CODODOMETRO    ,");
		sb.append("		CODVENDEDOR    ,");
		sb.append("		CODSISTEMA     ,");
		sb.append("		TO_CHAR(DATA1, 'YYYY_MM_DD') DATA1, ");
		sb.append("		TO_CHAR(DATA2, 'YYYY_MM_DD') DATA2, ");
		sb.append("		KM1            ,");
		sb.append("		KM2            ,");
		sb.append("		CONTENT_TYPE1,");
		sb.append("		CONTENT_TYPE2,");
		sb.append("		NOME_IMAGEM1  , ");
		sb.append("		NOME_IMAGEM2   ,");
		sb.append("		LENGTH1        ,");
		sb.append("		LENGTH2        ,");
		sb.append("		IMAGEM1        ,");
		sb.append("		IMAGEM2        ,");
		sb.append("		DATA_CADASTRO ");
		sb.append("	FROM POCKET_ODOMETRO ");
		sb.append("		WHERE CODVENDEDOR =? ");
		sb.append("		ORDER BY DATA_CADASTRO");
		
		Connection con = getDAO().getConnection();	
		
		try
		{
			PreparedStatement st = con.prepareStatement(sb.toString());
			st.setInt(1, codVendedor);
			
			ResultSet rs = st.executeQuery();
			Odometro o;
			Blob lob1 = null;
			Blob lob2 = null;
			while (rs.next())
			{
				o = new Odometro();
				o.setData1(rs.getString("DATA1"));
				o.setData2(rs.getString("DATA2"));
				o.setContentType1(rs.getString("CONTENT_TYPE1"));
				o.setContentType2(rs.getString("CONTENT_TYPE2"));
				o.setNomeImagem1(rs.getString("NOME_IMAGEM1"));
				o.setNomeImagem2(rs.getString("NOME_IMAGEM2"));
				o.setLength1(rs.getInt("LENGTH1"));
				o.setLength2(rs.getInt("LENGTH2"));
				
				InputStream is1 = rs.getBinaryStream("IMAGEM1");
				if (is1 != null)
					saveImage(o.getData1()+"_1.jpg", o.getLength1(), is1);
				
				InputStream is2 = rs.getBinaryStream("IMAGEM2");
				if (is2 != null)
					saveImage(o.getData2()+"_2.jpg", o.getLength2(), is2);
				
				
			}
		}
		catch (SQLException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	private void saveImage(String nome, int length, InputStream is) throws IOException
	{
		 File image = new File("C:\\doc\\880\\" + nome);
	      FileOutputStream fos = new FileOutputStream(image);

	      byte[] buffer = new byte[length];
	      
	      while (is.read(buffer) > 0) {
	        fos.write(buffer);
	      }
	      fos.close();
	}
	
	public static void main(String[] args)
	{
		new OdometroDao().findOdometro(880);
	}
	
}
