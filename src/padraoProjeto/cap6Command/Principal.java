package padraoProjeto.cap6Command;

public class Principal
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ControleRemoto cr = new ControleRemoto(new LuzCommand(new Luz()));
		cr.clicou();
		
		cr =new ControleRemoto(new AbrirPortaCommand());
		cr.clicou();

	}

}
