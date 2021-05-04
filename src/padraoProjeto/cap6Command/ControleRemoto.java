package padraoProjeto.cap6Command;

public class ControleRemoto
{
	
	private Command comando;

	public ControleRemoto(Command comando)
	{
		super();
		this.comando = comando;
	}
	
	public void clicou()
	{
		comando.execute();
	}
}
