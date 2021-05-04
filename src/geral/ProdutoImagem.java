package geral;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;

import dao.DAO;

public class ProdutoImagem
{
//	private final String path = "F:\\Olap\\Produtos\\PNG";
	private final String path = "C:\\doc\\export";
	private File[] aFiles;
	
	private DAO dao = null;

	private DAO getDAO()
	{
		return (dao == null) ? dao = new DAO() : dao;
	}
	
	public void delete(int codProduto, String tipoImagem) throws Exception
	{
	
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE CT_IMAGEM_PRODUTO ")
				.append(" WHERE  IDSETOR = 'ACAB' ")
				.append(" AND CODLINHA = 1 " )
				.append(" AND CODAREA = 1 "  )
				.append(" AND CODPRODUTO =? "  )
				.append(" AND TIPO_IMAGEM =? "  );		
			
		PreparedStatement st = getDAO().getPreparedStatement(sb.toString());
			
		//CLOB clob = getCLOB(xmlData, getDAO().getConnection());
			
		st.setInt(1, codProduto);
		st.setString(2, tipoImagem);
		
		st.executeUpdate();
		getDAO().fecharConexao();
	}
	
	public void insert(int codProduto, String tipoImagem, String arquivo, String arquivoPath, byte[] imagem) throws Exception
	{
	
		delete(codProduto, tipoImagem);
		
		String query = "INSERT INTO CT_IMAGEM_PRODUTO " +
				" ( IDSETOR," +
				" CODAREA, " +
				" CODLINHA, " +
				" CODPRODUTO, " +
				" TIPO_IMAGEM, " +
				" NOME_ARQUIVO, " +
				" NOME_ARQUIVO_PATH, " +
				" TAMANHO_IMAGEM, " +
				" IMAGEM ) " +
				" VALUES ('ACAB', 1,1,?,?,?,?,?,?)";
			
		PreparedStatement st = getDAO().getPreparedStatement(query);
			
		//CLOB clob = getCLOB(xmlData, getDAO().getConnection());
			
		st.setInt(1, codProduto);
		st.setString(2, tipoImagem);
		st.setString(3, arquivo);
		st.setString(4, arquivoPath);
		st.setInt(5, imagem.length);
		
		InputStream in = new ByteArrayInputStream(imagem);
		st.setBinaryStream(6, in, imagem.length);
		
		st.executeUpdate();
		getDAO().fecharConexao();
	}
	
	public void saveImages() throws Exception
	{
		aFiles = new File(path).listFiles();
		
		for (int i = 0; i < aFiles.length; i++)
		{
			int codProduto = getCodProduto(aFiles[i].getName());
			System.out.println(aFiles[i].getName() + " prod: " + codProduto );
			
			if (codProduto == 0)
				continue;

			Path path = Paths.get(aFiles[i].getAbsolutePath());
			byte[] image = Files.readAllBytes(path);
			System.out.println("Size: "+image.length);
//			BAIXA RESOLUÇÃO
//			ALTA RESOLUÇÃO
			
			String [] formato = aFiles[i].getName().split("\\.");
			
			
//			Teste.redimensionaImagem2(150, aFiles[i].getAbsolutePath(), "C:\\doc\\export\\" + aFiles[i].getName(), formato[formato.length - 1]);
			
//			byte[] img = Teste.redimensionaImagem(aFiles[i].getAbsolutePath(), 0.5F, formato[formato.length - 1], image.length);
//			Teste.gravar("C:\\doc\\export2\\" + aFiles[i].getName(), img);
			insert(codProduto, "THUMBNAIL", aFiles[i].getName(), aFiles[i].getAbsolutePath(), image);
		}
	}
	
	private int getCodProduto(String fileName)
	{
		int codProduto = 0;
		String [] names = fileName.split("-");
		
		if (names.length > 1)
			codProduto = Integer.parseInt(names[0].trim());
		
		return codProduto;
		
	}
	
	public static void main(String[] args)
	{
		ProdutoImagem p = new ProdutoImagem();
		try
		{
			p.saveImages();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
