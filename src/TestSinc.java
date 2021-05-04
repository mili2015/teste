import java.io.File;

public class TestSinc
{

	public static void main(String[] args)
	{
		String nomeArquivo = "324369.pdf";

		String arquivo = "/usr/eFrotas/eFrotas/" + nomeArquivo;
		
		
		File f = new File(arquivo);
		if(!f.exists())
		{
			System.out.println("Nao existe o arquivo");
		}
		else
		{
			System.out.println("Arquivo "+f);
		}
	}
}
