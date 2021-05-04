package bomTransporte;

import com.google.gson.Gson;

public class TestCaminhao
{

	public static void main(String[] args)
	{
		ContainerCaminhao container = new ContainerCaminhao();
		
		Caminhao c = new Caminhao();
		c.setAltura1M(0d);
		c.setAltura2M(0d);
		c.setAno("ano");
		c.setAparenciaCabine("aparencia");
		c.setAparenciaExtCarroceria("aparenciaExtCarroceria");
		c.setAparenciaGeralVeiculo("AparenciaGeralVeiculo");
		c.setAparenciaIntCarroceria("aparenciaIntCarroceria");
		c.setAparenciaLimpeza("aparenciaLimpeza");
		c.setChassi("chassi");
		c.setCodCaminhao(0);
		c.setCodigoANTT("codigoANTT");
		c.setCodTransp(9);
		c.setComprimento1M(0d);
		c.setComprimento2M(0);
		c.setCor("cor");
		c.setLargura1M(0);
		c.setLargura2M(0);
		c.setMarca("marca");
		c.setMunicipio("municipio");
		c.setNumeroEixo(0);
		c.setObservacao("observacao");
		c.setPeso(0);
		c.setPlaca("ABC-0000");
		c.setPlacaCarreta("ABC-0000");
		c.setProprietario("proprietario");
		c.setQtMaxCarga(0);
		c.setRenavam("renavam");
		c.setTipo("tipo");
		c.setTipoCarroceria("tipoCarroceria");
		c.setUfPlaca("ufPlaca");
		
		Motorista m = new Motorista();
		m.setBairro("bairro");
		m.setCelular("celular");
		m.setCep("cep");
		m.setCidade("cidade");
		m.setCnh("cnh");
		m.setCodMotorista(0);
		m.setCpf("cpf");
		m.setDtNascimento("DD/MM/YYYY");
		m.setEndereco("endereco");
		m.setNomeCompleto("nomeCompleto");
		m.setRg("RG");
		m.setTelefone("telefone");
		m.setUf("uf");

		c.setMotorista(m);
		
		
		container.getCaminhoes().add(c);
		container.getCaminhoes().add(c);
		container.getCaminhoes().add(c);
		
		Gson gson = new Gson();
		
		
		String json = gson.toJson(container);
		
		ContainerCaminhao obj = gson.fromJson(json, ContainerCaminhao.class);
		
		System.out.println(json);
		System.out.println(obj.getCaminhoes().size());
		
	}
	
}
