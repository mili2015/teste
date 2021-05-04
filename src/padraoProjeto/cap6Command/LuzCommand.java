package padraoProjeto.cap6Command;

public class LuzCommand implements Command
{
	private Luz luz;
	
	
	
	public LuzCommand(Luz luz)
	{
		super();
		this.luz = luz;
	}



	public void execute()
	{
		luz.acender();
	}

}
