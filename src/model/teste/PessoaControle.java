package model.teste;

import model.Empresa;
import model.Pessoa;

public class PessoaControle
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		Pessoa p = new Pessoa();
		p.setCpf("046");
		p.setIdade(23);
		p.setNome("felipe");
		
		IDados iPessoa = new WsPessoa();
		String prep = iPessoa.preparaDadosMsg(p);
		iPessoa.consomeWs(p,prep,  "3");
		
		
		Empresa e = new Empresa("");
		e.setNome("ipa");
		e.setDono("Felipe");
		e.setCidade("Ipanema");
		
		String prepE = iPessoa.preparaDadosMsg(e);
		iPessoa.consomeWs(e,prepE, "3E");
	}

}
