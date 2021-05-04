package model.teste;

import model.Empresa;
import model.Pessoa;

public class WsPessoa extends Empresa implements IDados
{

	public Object consomeWs(Object _1, String _2, String _3)
	{
		if (_1 instanceof Pessoa)
		{
			System.out.println("vai consumir o WSPessoa : "+_1+" - "+_2+" - "+_3);
		}
		else
			System.out.println("nao conumiu WSPessoa");
		
		Pessoa p = new Pessoa();
		return p;
	}

	public static void obtemNome()
	{
		System.out.println("obtemNome subclasse");
	}
	
	public String preparaDadosMsg(Object o)
	{
		String xml ="";
		if (o instanceof Pessoa)
		{
			Pessoa p = (Pessoa) o;
			xml = "Pessoa: "+p.getNome()+" - "+p.getCpf()+" - "+p.getIdade();
		}
		else
		{
			System.out.println("Erro nao eh pessoa");
			return null;
		}
		return xml;
	}
	
	public static void main(String[] args)
	{
		Empresa [] e = {new Empresa(),new WsPessoa()};
		
		for(Empresa em:e)
			em.obtemNome();
		
	}

}
