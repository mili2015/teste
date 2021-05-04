package model.teste;

import model.Empresa;

public class WsEmpresa extends Empresa implements IDados,IDados2
{

	
	public WsEmpresa(String nome)
	{
		super(nome);
		// TODO Auto-generated constructor stub
	}

	public Object consomeWs(Object _1, String _2, String _3)
	{
		System.out.println("vai consumir o serviço Empresa: "+_1+" - "+_2+" - "+_3);
		Empresa p = new Empresa("");
		return p;
	}

	public String preparaDadosMsg(Object o)
	{
		String xml ="";
		if (o instanceof Empresa)
		{
			Empresa p = (Empresa) o;
			xml = "Pessoa: "+p.getNome()+" - "+p.getDono()+" - "+p.getCidade();
		}
		else
		{
			System.out.println("Erro, nao eh Empresa");
			return null;
		}
		return xml;
	}

}
