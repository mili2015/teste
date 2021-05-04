package bomTransporte;

import java.util.ArrayList;
import java.util.List;

public class ContainerCaminhao
{
	private List<Caminhao> caminhoes = new ArrayList<Caminhao>();

	/**
	 * @return the caminhoes
	 */
	public List<Caminhao> getCaminhoes()
	{
		return caminhoes;
	}

	/**
	 * @param caminhoes the caminhoes to set
	 */
	public void setCaminhoes(List<Caminhao> caminhoes)
	{
		this.caminhoes = caminhoes;
	}
	
	
}
