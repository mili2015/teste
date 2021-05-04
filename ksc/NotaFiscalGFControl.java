package com.kscbrasil.notaFiscalGF.control;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.security.Security;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.print.PrintService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.SSLClientAxisEngineConfig;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.velocity.context.Context;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.EnumAceite;

import br.com.adilson.util.PrinterMatrix;

import br.com.e_governeapps2.www.EnviarLoteRpsResposta;
import br.com.e_governeapps2.www.ListaMensagemRetorno;
import br.com.e_governeapps2.www.Nfse;
import br.com.e_governeapps2.www.NfseLocator;
import br.com.e_governeapps2.www.NfseSoap;
import br.com.e_governeapps2.www.TcMensagemRetorno;


import com.kscbrasil.cFOP.model.CFOP;
import com.kscbrasil.cFOP.service.CFOPService;
import com.kscbrasil.cFOP.service.ICFOPService;
import com.kscbrasil.cidade.model.Cidade;
import com.kscbrasil.cidade.service.CidadeService;
import com.kscbrasil.cidade.service.ICidadeService;
import com.kscbrasil.cliente.model.Cliente;
import com.kscbrasil.cliente.service.ClienteService;
import com.kscbrasil.cliente.service.IClienteService;
import com.kscbrasil.comandoBoleto.model.ComandoBoleto;
import com.kscbrasil.comandoBoleto.service.ComandoBoletoService;
import com.kscbrasil.constante.KscConstante;
import com.kscbrasil.contaCorrente.model.ContaCorrente;
import com.kscbrasil.contaCorrente.service.ContaCorrenteService;
import com.kscbrasil.contaCorrente.service.IContaCorrenteService;
import com.kscbrasil.empresa.model.Empresa;
import com.kscbrasil.empresa.service.EmpresaService;
import com.kscbrasil.empresa.service.IEmpresaService;
import com.kscbrasil.enderecoCliente.model.EnderecoCliente;
import com.kscbrasil.enderecoCliente.service.EnderecoClienteService;
import com.kscbrasil.enderecoEmpresa.model.EnderecoEmpresa;
import com.kscbrasil.enderecoEmpresa.service.EnderecoEmpresaService;
import com.kscbrasil.estado.model.Estado;
import com.kscbrasil.estado.service.EstadoService;
import com.kscbrasil.estado.service.IEstadoService;
import com.kscbrasil.funcionario.model.Funcionario;
import com.kscbrasil.funcionario.service.FuncionarioService;
import com.kscbrasil.funcionario.service.IFuncionarioService;
import com.kscbrasil.impressora.control.ImpressoraControl;
import com.kscbrasil.impressora.model.Impressora;
import com.kscbrasil.impressora.service.ImpressoraService;
import com.kscbrasil.itemNotaFiscalGF.model.ItemNotaFiscalGF;
import com.kscbrasil.itemNotaFiscalGF.service.IItemNotaFiscalGFService;
import com.kscbrasil.itemNotaFiscalGF.service.ItemNotaFiscalGFService;
import com.kscbrasil.lancamentoComissoesPagar.model.LancamentoComissoesPagar;
import com.kscbrasil.lancamentoComissoesPagar.service.ILancamentoComissoesPagarService;
import com.kscbrasil.lancamentoComissoesPagar.service.LancamentoComissoesPagarService;
import com.kscbrasil.lancamentoContaReceber.model.LancamentoContaReceber;
import com.kscbrasil.lancamentoContaReceber.persistence.LancamentoContaReceberPersistence;
import com.kscbrasil.lancamentoContaReceber.service.ILancamentoContaReceberService;
import com.kscbrasil.lancamentoContaReceber.service.LancamentoContaReceberService;
import com.kscbrasil.lancamentoContaReceberParcela.model.LancamentoContaReceberParcela;
import com.kscbrasil.lancamentoContaReceberParcela.service.ILancamentoContaReceberParcelaService;
import com.kscbrasil.lancamentoContaReceberParcela.service.LancamentoContaReceberParcelaService;
import com.kscbrasil.lib.database.KscConnection;
import com.kscbrasil.lib.exception.KscException;
import com.kscbrasil.lib.type.KscBoolean;
import com.kscbrasil.lib.type.KscDate;
import com.kscbrasil.lib.type.KscDateTime;
import com.kscbrasil.lib.type.KscDouble;
import com.kscbrasil.lib.type.KscInteger;
import com.kscbrasil.lib.type.KscLong;
import com.kscbrasil.lib.type.KscOperator;
import com.kscbrasil.lib.type.KscString;
import com.kscbrasil.lib.util.Extenso;
import com.kscbrasil.loteRpsDinamo.model.LoteRpsDinamo;
import com.kscbrasil.loteRpsDinamo.service.ILoteRpsDinamoService;
import com.kscbrasil.loteRpsDinamo.service.LoteRpsDinamoService;
import com.kscbrasil.mensagemLote.model.MensagemLote;
import com.kscbrasil.mensagemLote.service.MensagemLoteService;
import com.kscbrasil.notaFiscalGF.model.LoteRps;
import com.kscbrasil.notaFiscalGF.model.MensagemRetorno;
import com.kscbrasil.notaFiscalGF.model.NotaFiscalGF;
import com.kscbrasil.notaFiscalGF.persistence.NotaFiscalGFPersistence;
import com.kscbrasil.notaFiscalGF.service.AssinarNfe;
import com.kscbrasil.notaFiscalGF.service.INotaFiscalGFService;
import com.kscbrasil.notaFiscalGF.service.NotaFiscalGFService;
import com.kscbrasil.notaxOrdem.model.NotaxOrdem;
import com.kscbrasil.notaxOrdem.service.INotaxOrdemService;
import com.kscbrasil.notaxOrdem.service.NotaxOrdemService;
import com.kscbrasil.notaxlote.model.Notaxlote;
import com.kscbrasil.notaxlote.service.NotaxloteService;
import com.kscbrasil.ordemServico.control.OrdemServicoControl;
import com.kscbrasil.ordemServico.model.OrdemServico;
import com.kscbrasil.ordemServico.persistence.OrdemServicoPersistence;
import com.kscbrasil.ordemServico.service.IOrdemServicoService;
import com.kscbrasil.ordemServico.service.OrdemServicoService;
import com.kscbrasil.parametrosFaturamento.model.ParametrosFaturamento;
import com.kscbrasil.parametrosFaturamento.service.ParametrosFaturamentoService;
import com.kscbrasil.parcelas.model.Parcelas;
import com.kscbrasil.parcelas.service.IParcelasService;
import com.kscbrasil.parcelas.service.ParcelasService;
import com.kscbrasil.servicoNotaFiscalGF.model.ServicoNotaFiscalGF;
import com.kscbrasil.servicoNotaFiscalGF.service.ServicoNotaFiscalGFService;
import com.kscbrasil.statusOrdemServico.service.IStatusOrdemServicoService;
import com.kscbrasil.statusOrdemServico.service.StatusOrdemServicoService;
import com.kscbrasil.tipoLogradouro.model.TipoLogradouro;
import com.kscbrasil.tipoLogradouro.service.ITipoLogradouroService;
import com.kscbrasil.tipoLogradouro.service.TipoLogradouroService;
import com.kscbrasil.transportadora.model.Transportadora;
import com.kscbrasil.transportadora.service.ITransportadoraService;
import com.kscbrasil.transportadora.service.TransportadoraService;
import com.kscbrasil.usuario.model.Usuario;
import com.kscbrasil.util.CriptografiaHexadecimal;
import com.kscbrasil.validapr.Tipo10;
import com.kscbrasil.validapr.Tipo11;
import com.kscbrasil.validapr.Tipo50;
import com.kscbrasil.validapr.Tipo54;
import com.kscbrasil.validapr.Tipo75;
import com.kscbrasil.validapr.Tipo90;
import com.kscbrasil.validapr.TotalTipo;
import com.sun.istack.internal.ByteArrayDataSource;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
* Esta classe representa a classe que trata as regras de negócio da entidade NotaFiscalGF<br>
* @autor Kisner, Fabio<br>
* @version 1.0 <br>
* Last Update: 02/04/2009<br>
*/
public class NotaFiscalGFControl 
{

	/** Parametro request do servlet <br>*/
	/** <code>Domínio: </code> {HttpServletRequest} */
	private HttpServletRequest request;
	
	/** Parametro response do servlet <br>*/
	/** <code>Domínio: </code> {HttpServletResponse} */
	private HttpServletResponse response;
	
	/** Parametro context do velocity servlet <br>*/
	/** <code>Domínio: </code> {Context} */
	private Context context;

	/** 
	* Construtor da classe NotaFiscalGF<br>
	* Last Update: 02/04/2009<br>
	* @autor Kisner, Fabio<br>
	* @param <code> javax.servlet.http.HttpServletRequest</code> request - <br>
	* @param <code> javax.servlet.http.HttpServletResponse</code> response - <br>
	* @param <code> org.apache.velocity.context.Context</code> context - <br>
	*/
	public NotaFiscalGFControl(HttpServletRequest request, HttpServletResponse response, Context context)
	{
		this.context=context;
		this.request=request;
		this.response=response;
	}


	
	/** 
	* Método que inclui um(a) notaFiscalGF<br>
	* Last Update: 02/04/2009<br>
	* @autor Kisner, Fabio<br>
	* @return <code> java.lang.String</code> Nome do arquivo .vm - <br>
	*/
	public final String incluirNotaFiscalGF() throws Exception
	{
		try
		{
			INotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();
			NotaFiscalGF notaFiscalGF = new NotaFiscalGF();

			notaFiscalGF.setNumero(new KscString(request.getParameter("iNumero")));
			
			notaFiscalGF.setIdCFOP(new KscString(request.getParameter("iIdCFOP")));

			notaFiscalGF.setEntradaSaida(new KscBoolean(request.getParameter("iEntradaSaida")));

			notaFiscalGF.setIdCliente(new KscString(request.getParameter("iIdCliente")));

			notaFiscalGF.setDataEmissao(new KscDateTime(request.getParameter("iDataEmissao")));

			notaFiscalGF.setIss(new KscDouble(request.getParameter("iIss")));

			notaFiscalGF.setValorIss(new KscDouble(request.getParameter("iValorIss")));

			notaFiscalGF.setValorServicos(new KscDouble(request.getParameter("iValorServicos")));

			notaFiscalGF.setValorTotalNota(new KscDouble(request.getParameter("iValorTotalNota")));
			
			notaFiscalGF.setValorFrete(new KscDouble(request.getParameter("iValorFrete")));

			notaFiscalGF.setIdTransportadora(new KscString(request.getParameter("iIdTransportadora")));

			notaFiscalGF.setObservacao(new KscString(request.getParameter("iObservacao")));

			notaFiscalGF.setCancelada(new KscBoolean(request.getParameter("iCancelada")));
			
			notaFiscalGF.setEmitenteDestinatario(new KscBoolean(request.getParameter("iEmitenteDestinatario")));
			
			notaFiscalGF.setVencimento(new KscString(request.getParameter("iVencimento")));
			
			notaFiscalGF.setNomeCliente(new KscString(request.getParameter("iNomeCliente")));
			
			notaFiscalGF.setCnpjCliente(new KscString(request.getParameter("iCnpjCliente")));

			notaFiscalGF.setEnderecoCliente(new KscString(request.getParameter("iEnderecoCliente")));
			
			notaFiscalGF.setIdCidade(new KscString(request.getParameter("iIdCidade")));
			
			notaFiscalGF.setInscricaoEstadual(new KscString(request.getParameter("iInscricaoEstadual")));
			
			notaFiscalGF.setEmail(new KscString(request.getParameter("iEmail")));
			
			if (request.getParameter("iCodigoOrdemServico") != null)
			{
				if (!request.getParameter("iCodigoOrdemServico").equals(""))
				{
					OrdemServico os = new OrdemServico();
					os.setCodigo(new KscLong(request.getParameter("iCodigoOrdemServico")));
					
					ArrayList al = new OrdemServicoService().consultarOrdemServicoCompleto(os, "");
					if(al.size()==1)
						throw new KscException("Não existe este codigo");
					else
						os=(OrdemServico)al.get(1);
					notaFiscalGF.setIdOrdemServico(os.getIdOrdemServico());
								
				}
			}

			//notaFiscalGF.setIdOrdemServico(new KscString(request.getParameter("iIdOrdemServico")));

			notaFiscalGFService.incluirNotaFiscalGF(notaFiscalGF);
			return consultarNotaFiscalGF();
		}
		catch(KscException ke)
		{
			System.err.print(ke.getMessage());
			if(ke.getMessage().indexOf("Duplicate entry") > 0)
			{	
				if (ke.getMessage().indexOf("key 2") > 0)
				{
					context.put("erro", "Erro de Duplicidade de registro");					
				}
				
			}
			else 
			{

				context.put("erro", ke.getMessage());
			}
			return "erro.vm";	
		}
		
	}

	/** 
	* Método que altera uma notaFiscalGF<br>
	* Last Update: 02/04/2009<br>
	* @autor Kisner, Fabio<br>
	* @return <code> java.lang.String</code> Nome do arquivo .vm<br>
	*/
	public final String alterarNotaFiscalGF() throws Exception
	{

		try
		{
			INotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();
			NotaFiscalGF notaFiscalGF = new NotaFiscalGF();
			NotaFiscalGF criterio = new NotaFiscalGF();

			notaFiscalGF.setNumero(new KscString(request.getParameter("iNumero")));

			notaFiscalGF.setIdCFOP(new KscString(request.getParameter("iIdCFOP")));

			notaFiscalGF.setEntradaSaida(new KscBoolean(request.getParameter("iEntradaSaida")));

			notaFiscalGF.setIdCliente(new KscString(request.getParameter("iIdCliente")));

			notaFiscalGF.setDataEmissao(new KscDateTime(request.getParameter("iDataEmissao")));

			notaFiscalGF.setIss(new KscDouble(request.getParameter("iIss")));

			notaFiscalGF.setValorIss(new KscDouble(request.getParameter("iValorIss")));

			notaFiscalGF.setValorServicos(new KscDouble(request.getParameter("iValorServicos")));

			notaFiscalGF.setValorTotalNota(new KscDouble(request.getParameter("iValorTotalNota")));
			
			notaFiscalGF.setValorFrete(new KscDouble(request.getParameter("iValorFrete")));

			notaFiscalGF.setIdTransportadora(new KscString(request.getParameter("iIdTransportadora")));

			notaFiscalGF.setObservacao(new KscString(request.getParameter("iObservacao")));
			
			notaFiscalGF.setEmitenteDestinatario(new KscBoolean(request.getParameter("iEmitenteDestinatario")));

			notaFiscalGF.setCancelada(new KscBoolean(request.getParameter("iCancelada")));
			
			notaFiscalGF.setVencimento(new KscString(request.getParameter("iVencimento")));
			
			notaFiscalGF.setNomeCliente(new KscString(request.getParameter("iNomeCliente")));
			
			notaFiscalGF.setCnpjCliente(new KscString(request.getParameter("iCnpjCliente")));

			notaFiscalGF.setEnderecoCliente(new KscString(request.getParameter("iEnderecoCliente")));
			
			notaFiscalGF.setIdCidade(new KscString(request.getParameter("iIdCidade")));
			
			notaFiscalGF.setInscricaoEstadual(new KscString(request.getParameter("iInscricaoEstadual")));
			
			notaFiscalGF.setEmail(new KscString(request.getParameter("iEmail")));
			
			if (request.getParameter("iCodigoOrdemServico") != null)
			{
				if (!request.getParameter("iCodigoOrdemServico").equals(""))
				{
					OrdemServico os = new OrdemServico();
					os.setCodigo(new KscLong(request.getParameter("iCodigoOrdemServico")));
					
					ArrayList al = new OrdemServicoService().consultarOrdemServicoCompleto(os, "");
					if(al.size()==1)
						throw new KscException("Não existe este codigo");
					else
						os=(OrdemServico)al.get(1);
					notaFiscalGF.setIdOrdemServico(os.getIdOrdemServico());
								
				}
			}
			//notaFiscalGF.setIdOrdemServico(new KscString(request.getParameter("iIdOrdemServico")));

			criterio.setIdNotaFiscalGF(new KscString(request.getParameter("iIdNotaFiscalGF")));

			notaFiscalGFService.alterarNotaFiscalGF(notaFiscalGF, criterio);
			return consultarNotaFiscalGF();
		}
		catch(KscException ke)
		{
			System.err.print(ke.getMessage());
			if(ke.getMessage().indexOf("Duplicate entry") > 0)
			{	
				if (ke.getMessage().indexOf("key 2") > 0)
				{
					context.put("erro", "Erro de Duplicidade de registro");					
				}
				
			}
			else 
			{

				context.put("erro", ke.getMessage());
			}
			return "erro.vm";	
		}
		
	}


	/** 
	* Método que exclui várias notaFiscalGFs<br>
	* Last Update: 02/04/2009<br>
	* @autor Kisner, Fabio<br>
	* @return <code>java.lang.String</code>Nome do arquivo .vm<br>
	*/

	public final String excluirNotaFiscalGF() throws Exception
	{
		try
		{

			INotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();
			String[] listaNotaFiscalGF = request.getParameterValues("iListaNotaFiscalGF");

			if(listaNotaFiscalGF != null)
			{
				for (int i=0; i < listaNotaFiscalGF.length; i++)
				{

					NotaFiscalGF criterio = new NotaFiscalGF();
					criterio.setIdNotaFiscalGF(new KscString(listaNotaFiscalGF[i]));

					try
					{
						notaFiscalGFService.excluirNotaFiscalGF(criterio);
					
					}
					catch(KscException ke)
					{
						System.err.print(ke.getMessage());
						context.put("erro", ke.getMessage());
						return "erro.vm";	
					}
				
				}

			}
			
			return consultarNotaFiscalGF();
		}
		catch(KscException ke)
		{
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			return "erro.vm";	
		}
	}

	/** 
	* Método que retorna todas as notaFiscalGFs cadastradas<br>
	* Last Update: 02/04/2009<br>
	* @autor Kisner, Fabio<br>
	* @return <code>java.lang.String</code>Nome do arquivo .vm<br>
	*/
	public final String consultarNotaFiscalGF() throws Exception
	{
		KscConstante.carregarConstante();
		String view = "";
		Connection con = null;
		
	//	LoteRpsDinamoService lrds = new LoteRpsDinamoService();
		
		try {

			try {
				con = KscConnection.getInstance();
				con.setAutoCommit(false);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new KscException(sqle);
			}
		
			
			NotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();
			ArrayList listaNotaFiscalGF = new ArrayList();
			boolean temCriterio = false;

			NotaFiscalGF criterio = new NotaFiscalGF();

			if (request.getParameter("iIdNotaFiscalGFPesquisa") != null)
			{
				if (!request.getParameter("iIdNotaFiscalGFPesquisa").equals(""))
				{
					criterio.setIdNotaFiscalGF(new KscString(request.getParameter("iIdNotaFiscalGFPesquisa")));
					context.put("idNotaFiscalGFPesquisa", request.getParameter("iIdNotaFiscalGFPesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iIdEmpresa") != null)
			{
				if (!request.getParameter("iIdEmpresa").equals(""))
				{
					criterio.setIdEmpresa(new KscString(request.getParameter("iIdEmpresa")));
					context.put("iIdEmpresa", request.getParameter("iIdEmpresa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iNumeroNfse") != null)
			{
				if (!request.getParameter("iNumeroNfse").equals(""))
				{
					criterio.setNumeroNfse(new KscString(request.getParameter("iNumeroNfse")));
					context.put("iNumeroNfse", request.getParameter("iNumeroNfse"));
					temCriterio = true;
				}
			}
		/*	if (request.getParameter("iDataEmissao") != null)
			{
				if (!request.getParameter("iDataEmissao").equals(""))
				{
					criterio.setDataEmissao(new KscDateTime(request.getParameter("iDataEmissao")+"00:00:00"));
					context.put("dataEmissao", request.getParameter("iDataEmissao"));
					temCriterio = true;
				}
			} */
			if (request.getParameter("iValorTotalNota") != null)
			{
				if (!request.getParameter("iValorTotalNota").equals(""))
				{
					criterio.setValorTotalNota(new KscDouble(request.getParameter("iValorTotalNota")));
					context.put("valorTotalNota", request.getParameter("iValorTotalNota"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iNumeroPesquisa") != null)
			{
				if (!request.getParameter("iNumeroPesquisa").equals(""))
				{
					criterio.setNumero(new KscString(request.getParameter("iNumeroPesquisa")));
					context.put("numeroPesquisa", request.getParameter("iNumeroPesquisa"));
					temCriterio = true;
				}
			}

			if (request.getParameter("iIdClientePesquisa") != null)
			{
				if (!request.getParameter("iIdClientePesquisa").equals(""))
				{
					criterio.setIdCliente(new KscString(request.getParameter("iIdClientePesquisa")));
					context.put("idClientePesquisa", request.getParameter("iIdClientePesquisa"));
					temCriterio = true;
				}
			}
			
		
			if (request.getParameter("iDataEmissaoPesquisa") != null)
			{
				if (!request.getParameter("iDataEmissaoPesquisa").equals(""))
				{
					criterio.setDataEmissao(new KscDateTime(request.getParameter("iDataEmissaoPesquisa")));
					context.put("dataEmissaoPesquisa", request.getParameter("iDataEmissaoPesquisa"));
					temCriterio = true;
				}
			}

			if (request.getParameter("iValorServicosPesquisa") != null)
			{
				if (!request.getParameter("iValorServicosPesquisa").equals(""))
				{
					criterio.setValorServicos(new KscDouble(request.getParameter("iValorServicosPesquisa")));
					context.put("valorServicosPesquisa", request.getParameter("iValorServicosPesquisa"));
					temCriterio = true;
				}
			}
			
			

			if (request.getParameter("iObservacaoPesquisa") != null)
			{
				if (!request.getParameter("iObservacaoPesquisa").equals(""))
				{
					criterio.setObservacao(new KscString(request.getParameter("iObservacaoPesquisa")));
					context.put("observacaoPesquisa", request.getParameter("iObservacaoPesquisa"));
					temCriterio = true;
				}
			}

			if (request.getParameter("iCanceladaPesquisa") != null)
			{
				if (!request.getParameter("iCanceladaPesquisa").equals(""))
				{
					criterio.setCancelada(new KscBoolean(request.getParameter("iCanceladaPesquisa")));
					context.put("canceladaPesquisa", request.getParameter("iCanceladaPesquisa"));
					temCriterio = true;
				}
			}
			
			if (request.getParameter("iVencimento") != null)
			{
				if (!request.getParameter("iVencimento").equals(""))
				{
					criterio.setVencimento(new KscString(request.getParameter("iVencimento")));
					context.put("vencimento", request.getParameter("iVencimento"));
					temCriterio = true;
				}
			}

			if (request.getParameter("iNomeClientePesquisa") != null)
			{
				if (!request.getParameter("iNomeClientePesquisa").equals(""))
				{
					criterio.setNomeCliente(new KscString(request.getParameter("iNomeClientePesquisa")));
					context.put("nomeClientePesquisa", request.getParameter("iNomeClientePesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iCnpjClientePesquisa") != null)
			{
				if (!request.getParameter("iCnpjClientePesquisa").equals(""))
				{
					criterio.setCnpjCliente(new KscString(request.getParameter("iCnpjClientePesquisa")));
					context.put("cnpjClientePesquisa", request.getParameter("iCnpjClientePesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iEnderecoClientePesquisa") != null)
			{
				if (!request.getParameter("iEnderecoClientePesquisa").equals(""))
				{
					criterio.setEnderecoCliente(new KscString(request.getParameter("iEnderecoClientePesquisa")));
					context.put("enderecoClientePesquisa", request.getParameter("iEnderecoClientePesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iIdCidadePesquisa") != null)
			{
				if (!request.getParameter("iIdCidadePesquisa").equals(""))
				{
					criterio.setIdCidade(new KscString(request.getParameter("iIdCidadePesquisa")));
					context.put("idCidadePesquisa", request.getParameter("iIdCidadePesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iInscricaoEstadualPesquisa") != null)
			{
				if (!request.getParameter("iInscricaoEstadualPesquisa").equals(""))
				{
					criterio.setInscricaoEstadual(new KscString(request.getParameter("iInscricaoEstadualPesquisa")));
					context.put("inscricaoEstadualPesquisa", request.getParameter("iInscricaoEstadualPesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iNumeroEnderecoPesquisa") != null)
			{
				if (!request.getParameter("iNumeroEnderecoPesquisa").equals(""))
				{
					criterio.setNumeroEndereco(new KscString(request.getParameter("iNumeroEnderecoPesquisa")));
					context.put("numeroEnderecoPesquisa", request.getParameter("iNumeroEnderecoPesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iComplementoPesquisa") != null)
			{
				if (!request.getParameter("iComplementoPesquisa").equals(""))
				{
					criterio.setComplemento(new KscString(request.getParameter("iComplementoPesquisa")));
					context.put("complementoPesquisa", request.getParameter("iComplementoPesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iBairroPesquisa") != null)
			{
				if (!request.getParameter("iBairroPesquisa").equals(""))
				{
					criterio.setBairro(new KscString(request.getParameter("iBairroPesquisa")));
					context.put("bairroPesquisa", request.getParameter("iBairroPesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iCepPesquisa") != null)
			{
				if (!request.getParameter("iCepPesquisa").equals(""))
				{
					criterio.setCep(new KscString(request.getParameter("iCepPesquisa")));
					context.put("cepPesquisa", request.getParameter("iCepPesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iDescricaoServicoPesquisa") != null)
			{
				if (!request.getParameter("iDescricaoServicoPesquisa").equals(""))
				{
					criterio.setDescricaoServico(new KscString(request.getParameter("iDescricaoServicoPesquisa")));
					context.put("descricaoServicoPesquisa", request.getParameter("iDescricaoServicoPesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iMotivoPesquisa") != null)
			{
				if (!request.getParameter("iMotivoPesquisa").equals(""))
				{
					criterio.setMotivo(new KscString(request.getParameter("iMotivoPesquisa")));
					context.put("motivoPesquisa", request.getParameter("iMotivoPesquisa"));
					temCriterio = true;
				}
			}
		
			if (request.getParameter("iProducaoEmpenhoPesquisa") != null)
			{
				if (!request.getParameter("iProducaoEmpenhoPesquisa").equals(""))
				{
					criterio.setProducaoEmpenho(new KscString(request.getParameter("iProducaoEmpenhoPesquisa")));
					context.put("producaoEmpenhoPesquisa", request.getParameter("iProducaoEmpenhoPesquisa"));
					temCriterio = true;
				}
			}
			if (request.getParameter("iEmailPesquisa") != null)
			{
				if (!request.getParameter("iEmailPesquisa").equals(""))
				{
					criterio.setEmail(new KscString(request.getParameter("iEmailPesquisa")));
					context.put("emailPesquisa", request.getParameter("iEmailPesquisa"));
					temCriterio = true;
				}
			}
			/*if (request.getParameter("iIdOrdemServicoPesquisa") != null)
			{
				if (!request.getParameter("iIdOrdemServicoPesquisa").equals(""))
				{
					criterio.setIdOrdemServico(new KscString(request.getParameter("iIdOrdemServicoPesquisa")));
					context.put("idOrdemServicoPesquisa", request.getParameter("iIdOrdemServicoPesquisa"));					
				}
			}*/
			if (request.getParameter("iCodigoOrdemServico") != null)
			{
				if (!request.getParameter("iCodigoOrdemServico").equals(""))
				{
					OrdemServico os = new OrdemServico();
					os.setCodigo(new KscLong(request.getParameter("iCodigoOrdemServico")));
					
					ArrayList al = new OrdemServicoService().consultarOrdemServicoCompleto(os, "", con);
					if(al.size()==1)
						throw new KscException("Não existe este codigo");
					else
						os=(OrdemServico)al.get(1);
					criterio.setIdOrdemServico(os.getIdOrdemServico());
					context.put("idOrdemServicoPesquisa", os.getIdOrdemServico().getWebValue());
					temCriterio = true;
				}
			}
			
			if (request.getParameter("iNumeroOrdemServicoPesquisa") != null)
			{
				if (!request.getParameter("iNumeroOrdemServicoPesquisa").equals(""))
				{
					OrdemServico os = new OrdemServico();
					os.setCodigo(new KscLong(request.getParameter("iNumeroOrdemServicoPesquisa")));
					
					ArrayList al = new OrdemServicoService().consultarOrdemServicoCompleto(os, "", con);
					if(al.size()==1)
						throw new KscException("Não existe este número da ordem de serviço");
					else
						os=(OrdemServico)al.get(1);
					criterio.setIdOrdemServico(os.getIdOrdemServico());
					context.put("iNumeroOrdemServicoPesquisa", os.getIdOrdemServico().getWebValue());
					temCriterio = true;
				}
			}
			
			String order = "order by NotaFiscalGF.dataEmissao desc";
			
			if(!temCriterio){
				Calendar c = GregorianCalendar.getInstance();
				c.setTime(new Date());
				c.add(Calendar.MONTH, -1);
				
				KscDateTime kd = new KscDateTime(c.getTime());
				kd.setOperator(new KscOperator(KscOperator.MAIOR_IGUAL));
				
				criterio.setDataEmissao(kd);
				// order = "where LoteRpsDinamo.idStatusLote not in ("+new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_PROCESSADO_COM_SUCESSO")).getSqlValue()+") order by NotaFiscalGF.numero desc";
			}
			
			consultaSituacaoLote(con); 
			
			if (request.getParameter("iNumeroOrdemServico") != null && !request.getParameter("iNumeroOrdemServico").equals("")){
				
					NotaxOrdemService nxos = new NotaxOrdemService(); 
				
//					OrdemServico criterioOrdem = new OrdemServico();
//					KscString codigoOs = new KscString("%" + request.getParameter("iNumeroOrdemServico") + "%");
//					codigoOs.setOperator(new KscOperator(KscOperator.CONTENDO));
//					criterioOrdem.setCodigo(codigoOs);
					
					String where = "WHERE codigo LIKE '%" + request.getParameter("iNumeroOrdemServico") + "%'";
					OrdemServicoService oss = new OrdemServicoService();
					ArrayList listaOrdem = oss.consultarOrdemServico(new OrdemServico(), where, con);
					if(listaOrdem.size() > 1){
						for(int i=1;i<listaOrdem.size();i++){
							OrdemServico ordem = new OrdemServico();
							ordem = (OrdemServico)listaOrdem.get(i);
							NotaxOrdem criterioNotaxOrdem = new NotaxOrdem();
							criterioNotaxOrdem.setIdOrdemServico(ordem.getIdOrdemServico());
							ArrayList listaNotaxOrdem = nxos.consultarNotaxOrdem(criterioNotaxOrdem, "",con);
							if(listaNotaxOrdem.size() > 1){
								criterio.setIdOrdemServico(null);
								for(int n=1;n<listaNotaxOrdem.size();n++){
									NotaxOrdem notaxordem = new NotaxOrdem();
									notaxordem = (NotaxOrdem)listaNotaxOrdem.get(n);
									criterio.setIdNotaFiscalGF(notaxordem.getIdNotaFiscalGF());
									NotaFiscalGF notaFiscal = new NotaFiscalGF();
									ArrayList listaNotaFiscalGFConsulta = new ArrayList();
									listaNotaFiscalGFConsulta = notaFiscalGFService.consultarNotaFiscalGFCompleto(criterio, "", 0, 0,con);
									
									if (listaNotaFiscalGFConsulta.size() > 1){
										
										notaFiscal = (NotaFiscalGF) listaNotaFiscalGFConsulta.get(1);
										listaNotaFiscalGF.add(notaFiscal);
									
									}
								}	
							}/*else{
								throw new KscException("Não foi encontrado registro com o parametro informado");
							}*/
						}
					}else{
						throw new KscException("Não foi encontrado registro com o parametro informado");
					}
					listaNotaFiscalGF.add(0, new Long(0));	
			}else{
			
				listaNotaFiscalGF = notaFiscalGFService.consultarNotaFiscalGFCompleto(criterio, order, Integer.parseInt(request.getParameter("iTamPagina")), Integer.parseInt(request.getParameter("iIndicePagina")),con);
			
			}
			context.put("tamPagina", request.getParameter("iTamPagina"));
			context.put("indicePagina", request.getParameter("iIndicePagina"));			
			context.put("statusPesquisa", request.getParameter("iStatusPesquisa"));										

			context.put("listaNotaFiscalGF", listaNotaFiscalGF);

	
			IClienteService clienteService = new ClienteService();
			ArrayList listaCliente = null;
			listaCliente = clienteService.consultarCliente(new Cliente(), "order by Cliente.nomeCompletoRazaoSocial",con);
			context.put("listaCliente", listaCliente);

				
			Empresa criterioEmpresa = new Empresa();
			EmpresaService es = new EmpresaService();
			ArrayList listaEmpresa = es.consultarEmpresa(criterioEmpresa, "",con);
			//String cnpj = empresa.getCnpjCpf().getWebValue().replace(".", "").replace("-", "").replace("/", "");
			context.put("listaEmpresa", listaEmpresa);
			//context.put("cnpjEmpresa", cnpj);
			
			IEstadoService estadoService = new EstadoService();
			ArrayList listaEstado = null;
			listaEstado = estadoService.consultarEstado(new Estado(), "ORDER BY Estado.Sigla",con);
			context.put("listaEstado", listaEstado);
			
			ICidadeService cidadeService = new CidadeService();
			ArrayList listaCidade = new ArrayList();
			listaCidade = cidadeService.consultarCidade(new Cidade(), "ORDER BY Cidade.descricao");
			context.put("listaCidade", listaCidade);
			con.commit();
			
			return "/notaFiscalGF/CViewNotaFiscalGF.vm";
		}
		catch(KscException ke)
		{
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			return "erro.vm";	
		}finally{
			try{
				if(con!=null){
					
					con.close();
				}	
			}catch(SQLException ke)
			{
				System.err.print(ke.getMessage());
				context.put("erro", ke.getMessage());
				return "erro.vm";	
			}
		}
	}

		/** 
	* Método que pesquisa um(a) notaFiscalGF<br>
	* Last Update: 02/04/2009<br>
	* @autor Kisner, Fabio<br>
	* @return <code> java.lang.String</code> Nome do arquivo .vm - <br>
	*/
	public final String pesquisarNotaFiscalGF()
	{
		try
		{
			INotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();
			NotaFiscalGF criterio = new NotaFiscalGF();
			ArrayList listaNotaFiscalGF = null;

			if (!request.getParameter("iNumero").equals(""))
				criterio.setNumero(new KscString(request.getParameter("iNumero")));

			if (!request.getParameter("iIdCFOP").equals(""))
				criterio.setIdCFOP(new KscString(request.getParameter("iIdCFOP")));

			if (!request.getParameter("iEntradaSaida").equals(""))
				criterio.setEntradaSaida(new KscBoolean(request.getParameter("iEntradaSaida")));

			if (!request.getParameter("iIdCliente").equals(""))
				criterio.setIdCliente(new KscString(request.getParameter("iIdCliente")));

			if (!request.getParameter("iDataEmissao").equals(""))
				criterio.setDataEmissao(new KscDateTime(request.getParameter("iDataEmissao")));

			if (!request.getParameter("iIss").equals(""))
				criterio.setIss(new KscDouble(request.getParameter("iIss")));

			if (!request.getParameter("iValorIss").equals(""))
				criterio.setValorIss(new KscDouble(request.getParameter("iValorIss")));

			if (!request.getParameter("iValorServicos").equals(""))
				criterio.setValorServicos(new KscDouble(request.getParameter("iValorServicos")));

			if (!request.getParameter("iValorTotalNota").equals(""))
				criterio.setValorTotalNota(new KscDouble(request.getParameter("iValorTotalNota")));

			if (!request.getParameter("iIdTransportadora").equals(""))
				criterio.setIdTransportadora(new KscString(request.getParameter("iIdTransportadora")));

			if (!request.getParameter("iObservacao").equals(""))
				criterio.setObservacao(new KscString(request.getParameter("iObservacao")));

			if (!request.getParameter("iCancelada").equals(""))
				criterio.setCancelada(new KscBoolean(request.getParameter("iCancelada")));

			if (!request.getParameter("iIdOrdemServico").equals(""))
				criterio.setIdOrdemServico(new KscString(request.getParameter("iIdOrdemServico")));

			if (!request.getParameter("iVencimento").equals(""))
				criterio.setVencimento(new KscString(request.getParameter("iVencimento")));

			if (!request.getParameter("iNomeCliente").equals(""))
				criterio.setNomeCliente(new KscString(request.getParameter("iNomeCliente")));

			if (!request.getParameter("iCnpjCliente").equals(""))
				criterio.setCnpjCliente(new KscString(request.getParameter("iCnpjCliente")));

			if (!request.getParameter("iEnderecoCliente").equals(""))
				criterio.setEnderecoCliente(new KscString(request.getParameter("iEnderecoCliente")));

			if (!request.getParameter("iIdCidade").equals(""))
				criterio.setIdCidade(new KscString(request.getParameter("iIdCidade")));

			if (!request.getParameter("iInscricaoEstadual").equals(""))
				criterio.setInscricaoEstadual(new KscString(request.getParameter("iInscricaoEstadual")));

			
			listaNotaFiscalGF = notaFiscalGFService.consultarNotaFiscalGFCompleto(criterio, "", Integer.parseInt(request.getParameter("iTamPagina")), Integer.parseInt(request.getParameter("iIndicePagina")));
			context.put("tamPagina", request.getParameter("iTamPagina"));
			context.put("indicePagina", request.getParameter("iIndicePagina"));			

			context.put("listaNotaFiscalGF", listaNotaFiscalGF);

			ICFOPService cFOPService = new CFOPService();
			ArrayList listaCFOP = null;
			listaCFOP = cFOPService.consultarCFOP(new CFOP(), "");
			context.put("listaCFOP", listaCFOP);

			IClienteService clienteService = new ClienteService();
			ArrayList listaCliente = null;
			listaCliente = clienteService.consultarCliente(new Cliente(), "");
			context.put("listaCliente", listaCliente);

			ITransportadoraService transportadoraService = new TransportadoraService();
			ArrayList listaTransportadora = null;
			listaTransportadora = transportadoraService.consultarTransportadora(new Transportadora(), "");
			context.put("listaTransportadora", listaTransportadora);

			IOrdemServicoService ordemServicoService = new OrdemServicoService();
			ArrayList listaOrdemServico = null;
			listaOrdemServico = ordemServicoService.consultarOrdemServico(new OrdemServico(), "");
			context.put("listaOrdemServico", listaOrdemServico);

			// Lista de impressoras
			ArrayList listaImpressora = new ImpressoraService().consultarImpressora(new Impressora(), "");
			context.put("listaImpressora", listaImpressora);

			return "/notaFiscalGF/CViewNotaFiscalGF.vm";

		}
		catch(KscException ke)
		{
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			return "erro.vm";	
		}
		
	}
	
	public final String geraNotaFiscalGFBaseadoOrdemServico() throws Exception{
		
		KscConstante.carregarConstante();
		String view = "";
		Connection con = null;
		
		LoteRpsDinamoService lrds = new LoteRpsDinamoService();
		
		try {

			try {
				con = KscConnection.getInstance();
				con.setAutoCommit(false);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new KscException(sqle);
			}
		
			Usuario user = (Usuario)request.getSession().getAttribute("usuario");
			//ArrayList<String> listaOrdemServico = new ArrayList();
			String listaOs[] = request.getParameterValues("iIdOrdemServico");
			//listaOrdemServico.add(request.getParameter("iIdOrdemServico"));
			
			
				
			//if (request.getParameter("iIdOrdemServico")==null || request.getParameter("iIdOrdemServico").equals("")){
			//	throw new KscException("Informe uma ordem de serviço");
			//}
			

			
			if(request.getParameter("iIdEmpresa")==null || request.getParameter("iIdEmpresa").equals("")){
				throw new KscException("Informe uma empresa");
			}
			
			System.out.println(request.getParameter("iDescricao"));
			
			ParametrosFaturamento criterioParametros = new ParametrosFaturamento();
			ParametrosFaturamentoService parametrosFaturamentoService = new ParametrosFaturamentoService();
			ParametrosFaturamento parametrosFaturamento = null;
			criterioParametros.setIdEmpresa(new KscString(request.getParameter("iIdEmpresa")));
			
			try{
				parametrosFaturamento = (ParametrosFaturamento) parametrosFaturamentoService.consultarParametrosFaturamentoCompleto(criterioParametros, "",con).get(1);			
			}catch(Exception e){
				throw new KscException("Parametros de Faturamento não foram encontrados");
			}
			
			System.out.println("Antes do gerarNotaService");
			
			//Processo de geracao da nota fiscal na tabela
			NotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();	
			NotaFiscalGF infoNota = new NotaFiscalGF();
			infoNota.setIdOrdemServico(new KscString(request.getParameter("iIdOrdemServico")));
			infoNota.setVencimento(new KscString(request.getParameter("iVencimento")));
			infoNota.setObservacao(new KscString(request.getParameter("iObservacao")));
			infoNota.setDescricaoServico(new KscString(request.getParameter("iDescricaoServico")));
			infoNota.setDescricaoItens(new KscString(request.getParameter("iDescricao")));
			infoNota.setProducaoEmpenho(new KscString(request.getParameter("iProducaoEmpenho")));
			infoNota.setMotivo(new KscString(request.getParameter("iMotivo")));
			infoNota.setIdTipoLogradouro(new KscString(request.getParameter("iIdTipoLogradouro")));
			infoNota.setIdContaCorrente(new KscString(request.getParameter("iIdContaCorrente")));
			infoNota.setValorServicos(new KscDouble(request.getParameter("iValor")));
			infoNota.setValorTotalNota(new KscDouble(request.getParameter("iValor")));
			infoNota.setNomeCliente(new KscString(request.getParameter("iNomeCliente")));
			infoNota.setCnpjCliente(new KscString(request.getParameter("iCnpjCliente")));
			
			TipoLogradouro tipoLogradouro = new TipoLogradouro();
			ITipoLogradouroService tipoLogradouroService = new TipoLogradouroService();
			ArrayList listaTipoLogradouro = new ArrayList();
			
			if (request.getParameter("iIdTipoLogradouro") != null && !request.getParameter("iIdTipoLogradouro").equals("")){
				
				tipoLogradouro.setIdTipoLogradouro(infoNota.getIdTipoLogradouro());
				
				listaTipoLogradouro = tipoLogradouroService.consultarTipoLogradouro(tipoLogradouro, "", con);
				
				if (listaTipoLogradouro.size() > 1){
					
					tipoLogradouro = (TipoLogradouro) listaTipoLogradouro.get(1);
					infoNota.setEnderecoCliente(new KscString(tipoLogradouro.getDescricao().getWebValue() + " " + request.getParameter("iEnderecoCliente")));
				}
				else
					infoNota.setEnderecoCliente(new KscString(request.getParameter("iEnderecoCliente")));
				
			} else
				infoNota.setEnderecoCliente(new KscString(request.getParameter("iEnderecoCliente")));
			
			infoNota.setEnderecoClienteBoleto(new KscString(request.getParameter("iEnderecoCliente")));
			infoNota.setIdCidade(new KscString(request.getParameter("iIdCidade")));
			infoNota.setInscricaoEstadual(new KscString(request.getParameter("iInscricaoEstadual")));
			infoNota.setEmail(new KscString(request.getParameter("iEmail")));
			if(request.getParameter("iNumeroEndereco")==null){
				throw new KscException("O numero do endereço é obrigatorio.");
			}
			infoNota.setNumeroEndereco(new  KscString(request.getParameter("iNumeroEndereco")));
			
			infoNota.setBairro(new  KscString(request.getParameter("iBairro")));
			if(request.getParameter("iBairro")==null){
				throw new KscException("O campo bairro é obrigatorio.");
			}
			
			infoNota.setComplemento(new  KscString(request.getParameter("iComplemento")));
			infoNota.setCep(new  KscString(request.getParameter("iCep")));
			if(request.getParameter("iIdEmpresa").equals(new KscConstante().propriedadesSistema.getProperty("ID_EMPRESA_EXP"))
					|| request.getParameter("iIdEmpresa").equals(new KscConstante().propriedadesSistema.getProperty("ID_EMPRESA_WSP"))){
				if(request.getParameter("iNumero")!=null){
					if(!request.getParameter("iNumero").equals("")){
						infoNota.setNumero(new KscString(request.getParameter("iNumero")));
						infoNota.setNumeroNfse(new KscString(request.getParameter("iNumero")));
					}else{
						throw new KscException("Para EXP o numero deve ser preenchido");
					}
				}else{
					throw new KscException("Para EXP o numero deve ser preenchido");
				}
				
			}
			
			if(request.getParameter("iIdTipoPessoa")==null){
				if(request.getParameter("iIdTipoPessoa").equals(""))
					throw new KscException("Campo Tipo Pessoa deve ser preenchido.");
			}else{
				if(request.getParameter("iIdTipoPessoa").equals(new KscConstante().propriedadesSistema.getProperty("TIPO_PESSOA_JURIDICA"))){
					 if(request.getParameter("iCnpjCliente").length()!=18)
						 throw new KscException("O tipo pessoa esta como juridica, portanto, o cnpj deve conter 18 digitos.");
				}	 
				infoNota.setIdTipoPessoa(new KscString(request.getParameter("iIdTipoPessoa")));
					 
			}	
			NotaFiscalGF nf =  notaFiscalGFService.geraNotaFiscalGFBaseadoOrdemServico(parametrosFaturamento.getIdEmpresa(),infoNota, parametrosFaturamento, listaOs, con);
			
			String in = "";
			
			for (int i = 0; i < listaOs.length; i++){
				
				if (i != 0)
					in = in + ", ";
				
				in = in + "'" + listaOs[i] + "'";
				
			}
			
			String listaParcelas[] = request.getParameterValues("iListaParcelas");
			KscDouble valorNota = new KscDouble(request.getParameter("iValor"));
			
			if (listaParcelas.length < 2){
				
				Parcelas parcela = new Parcelas();
				IParcelasService parcelasService = new ParcelasService();
				ArrayList listaParcelasPesquisa = new ArrayList();
				
				String where = "where Parcelas.idOrdemServico IN (" + in + ") and Parcelas.idNotaFiscalGF IS NULL";
				
				listaParcelasPesquisa = parcelasService.consultarParcelas(parcela, where, con);
				
				Parcelas criterioParcelas = new Parcelas();
				
				double somaParcelas = 0.0;
				
				for (int i = 1; i < listaParcelasPesquisa.size(); i++){
					
					parcela = (Parcelas)listaParcelasPesquisa.get(i);
					
					if (!parcela.getIdNotaFiscalGF().isDbNull() && !parcela.getIdNotaFiscalGF().equals(""))
						throw new KscException("Já existe nota fiscal relacionada à parcela(s) selecionada(s)");
					
					criterioParcelas.setIdParcelas(parcela.getIdParcelas());
					
					parcela.setIdNotaFiscalGF(nf.getIdNotaFiscalGF());
					
					somaParcelas = somaParcelas + parcela.getValor().doubleValue();
					
					
					parcelasService.alterarParcelas(parcela, criterioParcelas, con);
					
				}
				
				KscDouble soma = new KscDouble(somaParcelas);
				
				if (somaParcelas != valorNota.doubleValue()){
					
					if (!soma.getWebValue().equals(valorNota.getWebValue()))
						throw new KscException("Valor da Nota Fiscal (R$ " + valorNota.getWebValue() + ") é diferente da soma das parcelas (" + new KscDouble(somaParcelas).getWebValue() + ")");
					
				}
				
//				if (somaParcelas != valorNota.doubleValue())
//					throw new KscException("Valor da Nota Fiscal (R$ " + valorNota.getWebValue() + ") é diferente da soma das parcelas (" + new KscDouble(somaParcelas).getWebValue() + ")");
				
			} else {
				
				Parcelas parcela = new Parcelas();
				IParcelasService parcelasService = new ParcelasService();
				ArrayList listaParcelasPesquisa = new ArrayList();
				
				
				parcela.setIdParcelas(new KscString(listaParcelas[1]));
				
				listaParcelasPesquisa = parcelasService.consultarParcelas(parcela, "", con);
				
				if (listaParcelasPesquisa.size() < 0)
					throw new KscException("Não foi encontrada parcela com id " + parcela.getIdParcelas().getWebValue());
				
				parcela = (Parcelas) listaParcelasPesquisa.get(1);
				
				Parcelas criterioParcelas = new Parcelas();
				
				criterioParcelas.setDataVencimento(parcela.getDataVencimento());
				
				String where = "and Parcelas.idOrdemServico IN (" + in + ") and Parcelas.idNotaFiscalGF IS NULL";
				
				listaParcelasPesquisa = parcelasService.consultarParcelas(criterioParcelas, where, con);
				
				double somaParcelas = 0.0;
				
				for (int i = 1; i < listaParcelasPesquisa.size(); i++){
					
					parcela = (Parcelas)listaParcelasPesquisa.get(i);
					
					if (!parcela.getIdNotaFiscalGF().isDbNull() && !parcela.getIdNotaFiscalGF().equals(""))
						throw new KscException("Já existe nota fiscal relacionada à parcela(s) selecionada(s)");
					
					criterioParcelas.setIdParcelas(parcela.getIdParcelas());
					
					parcela.setIdNotaFiscalGF(nf.getIdNotaFiscalGF());
					
					somaParcelas = somaParcelas + parcela.getValor().doubleValue();
					
					parcelasService.alterarParcelas(parcela, criterioParcelas, con);
					
				}
				
				KscDouble soma = new KscDouble(somaParcelas);
				
				if (somaParcelas != valorNota.doubleValue()){
					
					if (!soma.getWebValue().equals(valorNota.getWebValue()))
						throw new KscException("Valor da Nota Fiscal (R$ " + valorNota.getWebValue() + ") é diferente da soma das parcelas (" + new KscDouble(somaParcelas).getWebValue() + ")");
					
				}
				
			}
			
			//Recuperacao do lote que se encontra em aberto para vincular a nota ao lote
			System.out.println("Antes de recuperar o lote aberto");
			LoteRpsDinamo loteRps = notaFiscalGFService.recuperaLoteAberto(con,new KscString(request.getParameter("iIdEmpresa")));
			
			System.out.println("Antes de vincular a nota ao lote");
			Notaxlote lotexnota = new Notaxlote();
			lotexnota.setIdLoteRpsDinamo(loteRps.getIdLoteRpsDinamo());
			lotexnota.setIdNotaFiscalGF(nf.getIdNotaFiscalGF());
			NotaxloteService nls = new NotaxloteService();
			nls.incluirNotaxlote(lotexnota, con);
			
			if(!request.getParameter("iIdEmpresa").equals(new KscConstante().propriedadesSistema.getProperty("ID_EMPRESA_EXP"))
					&& !request.getParameter("iIdEmpresa").equals(new KscConstante().propriedadesSistema.getProperty("ID_EMPRESA_WSP"))){
				loteRps.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_ENVIADO")));
				notaFiscalGFService.fechaAbreLote(loteRps, con);
				enviarNotaFiscalGF(loteRps,parametrosFaturamento,con);				

			}else{
				loteRps.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_PROCESSADO_COM_SUCESSO")));
				notaFiscalGFService.fechaAbreLote(loteRps, con);
				//atualizaNotasFiscaisLcr(nf, con);

			}
			
			
			
		//	con.commit();
			
		//	if(request.getParameter("iAprovar").equals("S")){
		//		for(int l=0;l<listaOs.length;l++){
		//			new OrdemServicoService().aprovarAprovacaoGeralFinanceiro(new StatusOrdemServicoService().CONCLUIDO_FATURAMENTO,user, request.getParameter("motivo"),request.getParameter("pendente"),listaOs, con);
		//		}
		//	}	
			
			
			con.commit();
		
			return consultarNotaFiscalGF();			
			
			//return "teste";
		}

		catch (KscException ke) {
			//context.put("notaFiscal", notaFiscal);
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			return "erro.vm";
		}
		catch (Exception ke) {
			ke.printStackTrace();
			return "erro.vm";
		}finally{
			try{
				if(con!=null)
					con.close();
			}catch(SQLException ke)
			{
				System.err.print(ke.getMessage());
				context.put("erro", ke.getMessage());
				return "erro.vm";	
			}
		}
	}
	

	public final void consultaSituacaoLote(Connection con){
		
	
		System.out.println("Consultar Situacao Lote");

		try {
	
			String retorno = "";
			//Usuario user = (Usuario)request.getSession().getAttribute("usuario");
			
			NotaxloteService nxls = new NotaxloteService();
			NotaFiscalGFPersistence nfgp = new NotaFiscalGFPersistence();
			
			Empresa criterioEmpresa = new Empresa();
		//	criterioEmpresa.setIdEmpresa(user.getEmpresa().getIdEmpresa());
			EmpresaService es = new EmpresaService();
		//	Empresa empresa = (Empresa)es.consultarEmpresa(criterioEmpresa, "", con).get(1);
				
			ParametrosFaturamento criterioP = new ParametrosFaturamento();
			ParametrosFaturamentoService pfs = new ParametrosFaturamentoService();
			//ParametrosFaturamento parametros = (ParametrosFaturamento)pfs.consultarParametrosFaturamento(criterioP, "",con).get(1);
	
			
			
			
			LoteRpsDinamo criterioLote = new LoteRpsDinamo();
			//criterioLote.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_ENVIADO")));
			
			//KscString situacao = new KscString(KscConstante.propriedadesSistema.getProperty("STATUSLOTE_ENVIADO"));
			//situacao.setOperator(new KscOperator(KscOperator.DIFERENTE));
			//criterioLote.setIdStatusLote(situacao);
			//criterioLote.setIdStatusLote(new KscString(KscConstante.propriedadesSistema.getProperty("STATUSLOTE_ENVIADO")));
			//criterioLote.setIdEmpresa(new KscString("1_16_12"));
			
			NotaxOrdemService nos = new NotaxOrdemService();
			
			LoteRpsDinamoService lrds = new LoteRpsDinamoService();
			ArrayList listaLotes = lrds.consultarLoteRps(criterioLote, " where idStatusLote in ('"+KscConstante.propriedadesSistema.getProperty("STATUSLOTE_NAO_PROCESSADO")+"','"+KscConstante.propriedadesSistema.getProperty("STATUSLOTE_ENVIADO")+"')", 0, 0, con);
			if(listaLotes.size()>1){
				for(int i=1;i<listaLotes.size();i++){
					
					LoteRpsDinamo lote = (LoteRpsDinamo)listaLotes.get(i);
					
					if(!lote.getIdEmpresa().getValue().equals(new KscConstante().propriedadesSistema.getProperty("ID_EMPRESA_EXP"))
							&& !lote.getIdEmpresa().getValue().equals(new KscConstante().propriedadesSistema.getProperty("ID_EMPRESA_WSP"))){
						criterioEmpresa.setIdEmpresa(lote.getIdEmpresa());
						Empresa empresa = (Empresa)es.consultarEmpresa(criterioEmpresa, "", con).get(1);
					
						criterioP.setIdEmpresa(lote.getIdEmpresa());
						ParametrosFaturamento parametros = (ParametrosFaturamento)pfs.consultarParametrosFaturamento(criterioP, "",con).get(1);
					
						String certificado = parametros.getArquivo();
						String pwd = null;
						char[] senha = null;
						CriptografiaHexadecimal criptSenha = new CriptografiaHexadecimal();
						if(parametros.getSenhaCertificado()!=null){
							pwd = criptSenha.cript(parametros.getSenhaCertificado().getWebValue(),"1");
							senha = pwd.toCharArray();
						}	
						//String[] desmCertificado = certificado.split(".");
						//String nomeCertificado = certificado.substring(0,certificado.length()-4);
					
						//Propriedades para comunicacao com webservice
						System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
						System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");   
						Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());   
				    
						System.out.println("Antes getAxisConfig()");
						SSLClientAxisEngineConfig axisConfig = getAxisConfig(certificado, new String(senha)); 
				    
				    
						//Classe gerada pelo wsdl da prefeitura
						Nfse servicoEnv = new NfseLocator(axisConfig);	
						NfseSoap evai = null;
						try 
						{
							evai = servicoEnv.getnfseSoap();
						} catch (ServiceException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
			
					
						KscString idLoteRpsDinamo = lote.getIdLoteRpsDinamo();
					
						String conteudo =  "<ConsultarSituacaoLoteRpsEnvio>";
							   conteudo = conteudo + "<Prestador>";
							   conteudo = conteudo + "<Cnpj>"+empresa.getCnpjCpf().getWebValue().replace(".", "").replace("/", "").replace("-", "")+"</Cnpj>";
							   conteudo = conteudo + "<InscricaoMunicipal>"+empresa.getInscricaoMunicipal().getWebValue().replace(".", "")+"</InscricaoMunicipal>";
							   conteudo = conteudo + "</Prestador>";
							   conteudo = conteudo + "<Protocolo>"+lote.getProtocolo().getWebValue()+"</Protocolo>";
							   conteudo = conteudo + "</ConsultarSituacaoLoteRpsEnvio>";
						   
						System.out.println("Protocolo"+lote.getProtocolo().getWebValue());
	   
					   retorno = evai.recepcionarXml("ConsultarSituacaoLoteRps", conteudo);
					
					   XStream x = new XStream();
					
					   System.out.println(retorno);
					
					   Class [] classes = {com.kscbrasil.notaFiscalGF.model.ConsultarSituacaoLoteRpsResposta.class,com.kscbrasil.notaFiscalGF.model.ListaMensagemRetorno.class,com.kscbrasil.notaFiscalGF.model.MensagemRetorno.class};
					   x.processAnnotations(classes);
					   com.kscbrasil.notaFiscalGF.model.ConsultarSituacaoLoteRpsResposta respostaEnvio = (com.kscbrasil.notaFiscalGF.model.ConsultarSituacaoLoteRpsResposta) x.fromXML(retorno);
					   if(respostaEnvio.getSituacao().equals("3"))
						   lote.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_PROCESSADO_COM_ERRO")));
					   else if(respostaEnvio.getSituacao().equals("4"))
						   lote.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_PROCESSADO_COM_SUCESSO")));
					   else if(respostaEnvio.getSituacao().equals("1"))
						   lote.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_NAO_ENVIADO")));
					   else if(respostaEnvio.getSituacao().equals("2"))
						   lote.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_NAO_PROCESSADO")));
					   //else
						 //  throw new KscException("Situação "+respostaEnvio.getSituacao()+" não prevista na tabela situação lote. Informe o técnico");
					
					
					   if(!respostaEnvio.getSituacao().equals("")){
						   String conteudoLoteEnvio = "<ConsultarLoteRpsEnvio>";
						   		  conteudoLoteEnvio += "<Prestador>";
						   		  conteudoLoteEnvio += "<Cnpj>"+empresa.getCnpjCpf().getWebValue().replace(".", "").replace("/", "").replace("-", "")+"</Cnpj>";
						   		  conteudoLoteEnvio += "<InscricaoMunicipal>"+empresa.getInscricaoMunicipal().getWebValue().replace(".", "")+"</InscricaoMunicipal>";
						   		  conteudoLoteEnvio += "</Prestador>";
						   		  conteudoLoteEnvio += "<Protocolo>"+lote.getProtocolo().getWebValue()+"</Protocolo>";
						   		  conteudoLoteEnvio += "</ConsultarLoteRpsEnvio>";
				   
						   		  String retornoLote = evai.recepcionarXml("ConsultarLoteRps", conteudoLoteEnvio);
					
						   		  System.out.println(retornoLote);
						   		  
						   		  boolean atualizadoLote = false;
						   		  boolean completaProcesso = true;
				    
						   		  if(respostaEnvio.getSituacao().equals("4")){
						   			  Class [] classes2 = {com.kscbrasil.notaFiscalGF.model.ConsultarLoteRpsResposta.class,com.kscbrasil.notaFiscalGF.model.ListaMensagemRetorno.class,com.kscbrasil.notaFiscalGF.model.MensagemRetorno.class, com.kscbrasil.notaFiscalGF.model.ListaNfse.class, com.kscbrasil.notaFiscalGF.model.NfseCancelamento.class};
						   			  x.processAnnotations(classes2);
					
						   			  com.kscbrasil.notaFiscalGF.model.ConsultarLoteRpsResposta respostaEnvioLote = (com.kscbrasil.notaFiscalGF.model.ConsultarLoteRpsResposta) x.fromXML(retornoLote);
				    
						   			  trataMensagemRetorno(respostaEnvioLote.getListaMensagemRetorno(),lote, "ConsultarLoteRpsResposta");
				    
						   			  List listaNfse = respostaEnvioLote.getListaNfse();
						   			  if(listaNfse!=null){
						   				  com.kscbrasil.notaFiscalGF.model.CompNfse composicaoNota = (com.kscbrasil.notaFiscalGF.model.CompNfse)listaNfse.get(0); 
				    
						   				  Notaxlote criterioNotaxlote = new Notaxlote();
						   				  criterioNotaxlote.setIdLoteRpsDinamo(lote.getIdLoteRpsDinamo());
						   				  Notaxlote notaxlote = (Notaxlote)nxls.consultarNotaxlote(criterioNotaxlote, "",con).get(1);

						   				  NotaFiscalGF nota = new NotaFiscalGF();
						   				  nota.setNumeroNfse(new KscString(composicaoNota.getTcCompNfse().getNfse().getInfNfse().getNumero()));
						   				  nota.setCodigoVerificacao(new KscString(composicaoNota.getTcCompNfse().getNfse().getInfNfse().getCodigoVerificacao()));

						   				  String[] desmenbraData = composicaoNota.getTcCompNfse().getNfse().getInfNfse().getDataEmissaoRps().split("T");
						   				  String data = desmenbraData[0];
						   				  String formataData = data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0,4);
						   				  String resto= desmenbraData[1];
						   				  String hora = resto.substring(0, 8);
						   				  String dataEmissaoNfse = formataData+" "+hora;
					
						   				  nota.setDataEmissaoNfse(new KscDateTime(dataEmissaoNfse));
					
						   				  String[] desmenbraCompetencia = composicaoNota.getTcCompNfse().getNfse().getInfNfse().getCompetencia().split("T");
						   				  String dataC = desmenbraCompetencia[0];
						   				  String formataDataC = dataC.substring(8,10)+"/"+dataC.substring(5,7)+"/"+dataC.substring(0,4);
						   				  String restoC = desmenbraCompetencia[1];
						   				  String horaC = restoC.substring(0, 8);
						   				  String dataCompetencia = formataDataC+" "+horaC;
					
						   				  nota.setCompetencia(new KscDateTime(dataCompetencia));
					
						   				  nota.setValorCredito(new KscDouble(composicaoNota.getTcCompNfse().getNfse().getInfNfse().getValorCredito().replace(".", ",")));
					
						   				  if(composicaoNota.getTcCompNfse().getNfseCancelamento()!=null){
						   					  nota.setCancelada(new KscBoolean(true));
				    			
						   					  String[] desmenbraDataCanc = composicaoNota.getTcCompNfse().getNfseCancelamento().getConfirmacao().getDataHoraCancelamento().split("T");
						   					  String dataCancela = desmenbraDataCanc[0];
						   					  String formataDataCancela = dataCancela.substring(8,10)+"/"+dataCancela.substring(5,7)+"/"+dataCancela.substring(0,4);
						   					  String restoCancela= desmenbraDataCanc[1];
						   					  String horaCancela = restoCancela.substring(0, 8);
						   					  String dataCancelamentoNfse = formataDataCancela+" "+horaCancela;
						
						   					  nota.setDataCancelamento(new KscDateTime(dataCancelamentoNfse));
					    		
						   					  lote.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_CANCELADO")));
						   				  }
				    		
						   				  NotaFiscalGF criterioNota = new NotaFiscalGF();
						   				  criterioNota.setIdNotaFiscalGF(notaxlote.getIdNotaFiscalGF());
						   				  
				    		 
				    		  
				    		  //atuyaliza contas a receber
						   				  NotaxOrdem criterioNotaxOrdem = new NotaxOrdem();
						   				  criterioNotaxOrdem.setIdNotaFiscalGF(notaxlote.getIdNotaFiscalGF());
				    		  
						   				  
						   				  //String codigo="";
						   				  ArrayList listaNotaxOrdem = nos.consultarNotaxOrdem(criterioNotaxOrdem, "",con);
						   				  if(listaNotaxOrdem.size()>1){
						   					  for(int a=1;a<listaNotaxOrdem.size();a++){	
						   						  NotaxOrdem notaxordem = (NotaxOrdem)listaNotaxOrdem.get(a);
				    				  
						   						  OrdemServico ordemServico = new OrdemServico();
						   						  IOrdemServicoService ordemServicoService = new OrdemServicoService();
						   						  
						   						  ordemServico.setIdOrdemServico(notaxordem.getIdOrdemServico());
						   						  
						   						  ArrayList listaOrdemServico = ordemServicoService.consultarOrdemServico(ordemServico, "", con);
						   						  
						   						  if (listaOrdemServico.size() < 2){
						   							  System.out.println("Não foi encontrada Ordem de Serviço com id " + ordemServico.getIdOrdemServico().getWebValue());
						   							  completaProcesso = false;
						   							  break;
						   						  }
						   						 
						   						  ordemServico = (OrdemServico) listaOrdemServico.get(1);
						   						  
						   						  if (!KscConstante.propriedadesSistema.containsKey("STATUS_OS_AGUARDANDO_FATURAMENTO")){
						   							  System.out.println("Propriedade STATUS_OS_AGUARDANDO_FATURAMENTO não encontrada");
						   							  completaProcesso = false;
						   							  break;
						   							  
						   						  }
						   						  
						   						  if (ordemServico.getIdStatusOrdemServicoFinanceiro().getWebValue().equals(KscConstante.propriedadesSistema.getProperty("STATUS_OS_AGUARDANDO_FATURAMENTO"))){
						   							  completaProcesso = false;
						   							  break;
						   						  }
						   							  
						   						  
//						   						  if(listaOrdens.size()>1){
//						   							  codigo="";
//						   							  for(int b=1;b<listaOrdens.size();b++){
//						   								  NotaxOrdem no = new NotaxOrdem();
//						   								  no = (NotaxOrdem)listaOrdens.get(b);
//						   								  //if(b==1)
//						   								  codigo = no.getNotaFiscalGF().getNumeroNfse().getWebValue() + b;
////						   								  else
////						   									  codigo = codigo+"/"+no.getNotaFiscalGF().getNumeroNfse().getWebValue();
//				    						  
//						   							  }
//						   						  }
//						   						  LancamentoContaReceberService lcrs = new LancamentoContaReceberService();
//						   						  LancamentoContaReceberPersistence lcrp = new LancamentoContaReceberPersistence();
//						   						  LancamentoContaReceber criterioLancamento = new LancamentoContaReceber();
//						   						  criterioLancamento.setNumeroDocumento(new KscString(notaxordem.getOrdemServico().getCodigo().getWebValue()));
//						   						  criterioLancamento.setIdEmpresa(notaxordem.getNotaFiscalGF().getIdEmpresa());
//						   						  ArrayList listaLancamento = lcrs.consultarLancamentoContaReceber(criterioLancamento, "",con);
//						   						  if(listaLancamento.size() > 1){
//				    		  
//						   							  LancamentoContaReceber lancamento = new LancamentoContaReceber();
//						   							 
//				    			  
//						   						  }
						   					  } //fim for da lista notaxordem	  
						   				  }	// fim do if da lista notaxordem	  
						   				
						   				if (completaProcesso){
											  System.out.println("CompletaProcesso");

						   					nfgp.alterarNotaFiscalGF(nota, criterioNota, con);
						   					NotaFiscalGF notaFiscalGF = new NotaFiscalGF();
							   				INotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();
							   				ArrayList listaNotaFiscalGF = new ArrayList();
							   				
							   				notaFiscalGF.setIdNotaFiscalGF(notaxlote.getIdNotaFiscalGF());

							   				listaNotaFiscalGF = notaFiscalGFService.consultarNotaFiscalGF(notaFiscalGF, "", con);

							   				LoteRpsDinamo criAtu = new LoteRpsDinamo();
									   		criAtu.setIdLoteRps(lote.getIdLoteRpsDinamo());
									   		lrds.alterarLoteRps(lote, criAtu,con);
									   		
									   		atualizadoLote = true;
							   				
							   				if (listaNotaFiscalGF.size() > 1){
							   					
							   					notaFiscalGF = (NotaFiscalGF) listaNotaFiscalGF.get(1);

						   						imprimirBoletosParcelas(notaFiscalGF, con); 

							   				}
							   				
							   				atualizaObservacaoComissoes(notaFiscalGF, con);

						   				}
						   			  }//fim do id listaNfse
						   		  }//fim do if da situacao 4
						   		  
								  System.out.println("Antes atualiza lote");

						   		  if (!atualizadoLote && completaProcesso){
						   		  
						   			  LoteRpsDinamo criAtu = new LoteRpsDinamo();
							   		  criAtu.setIdLoteRps(lote.getIdLoteRpsDinamo());
							   		  lrds.alterarLoteRps(lote, criAtu,con);
						   		  
						   		  }
					   } // fim do id da situacao vazio
					}
					//System.out.println("foi "+respostaEnvioLote.getListaMensagemRetorno().size());
					
						   
				}	
				
			}
			con.commit();
			
		//	return consultarNotaFiscalGF();	
			
		}catch (KscException ke) {
			//context.put("notaFiscal", notaFiscal);
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
		//	return "erro.vm";
		}
		catch (Exception ke) {
			System.out.println(ke.getMessage());
			ke.printStackTrace();
			//return "erro.vm";
		}

	
	}
	
	private final void atualizaObservacaoComissoes (NotaFiscalGF notaFiscalGF, Connection con) throws KscException, IllegalAccessException, InvocationTargetException{
		
			
		NotaxOrdem notaxOrdem = new NotaxOrdem();
		INotaxOrdemService notaxOrdemService = new NotaxOrdemService();
		ArrayList listaNotaxOrdem = new ArrayList();
		
		notaxOrdem.setIdNotaFiscalGF(notaFiscalGF.getIdNotaFiscalGF());
		
		listaNotaxOrdem = notaxOrdemService.consultarNotaxOrdem(notaxOrdem, "", con);
		
		String observacao = notaFiscalGF.getNumeroNfse().getWebValue() + " " + notaFiscalGF.getProducaoEmpenho().getWebValue();
		
		for (int i = 1; i < listaNotaxOrdem.size(); i++){
			
			LancamentoComissoesPagar lcp = new LancamentoComissoesPagar();
			LancamentoComissoesPagar lcpCriterio = new LancamentoComissoesPagar();
			ILancamentoComissoesPagarService lcps = new LancamentoComissoesPagarService();
			ArrayList listaLcp = new ArrayList();
			
			notaxOrdem = (NotaxOrdem) listaNotaxOrdem.get(i);
			
			lcpCriterio.setIdDocumentoOrigem(notaxOrdem.getIdOrdemServico());
			
			listaLcp = lcps.consultarLancamentoComissoesPagar(lcpCriterio, "");
			
			if (listaLcp.size() > 1){
				lcp = (LancamentoComissoesPagar) listaLcp.get(1);
				
				BeanUtils.copyProperties(lcpCriterio, lcp);
				
				lcp.setObservacao(new KscString(observacao));
				
				lcpCriterio = new LancamentoComissoesPagar();
				
				lcpCriterio.setIdLancamentoComissoesPagar(lcp.getIdLancamentoComissoesPagar());
				
				lcps.alterarLancamentoComissoesPagar(lcp, lcpCriterio, con);
			}
			
			LancamentoContaReceberParcela lcrp = new LancamentoContaReceberParcela();
			ILancamentoContaReceberParcelaService lcrps = new LancamentoContaReceberParcelaService();
			ArrayList listaLancamentoContaReceberParcela = new ArrayList();
			
			String where = "where Parcelas.idNotaFiscalGF = " + notaFiscalGF.getIdNotaFiscalGF().getSqlValue();
			
			listaLancamentoContaReceberParcela = lcrps.consultarLancamentoContaReceberParcelaCompleto(lcrp, where, con);
			
			if (listaLancamentoContaReceberParcela.size() > 1){
				
				String in = "";
				
				for (int j = 1; j < listaLancamentoContaReceberParcela.size(); j++){
					
					if (j != 1)
						in = in + ", ";
					
					lcrp = (LancamentoContaReceberParcela) listaLancamentoContaReceberParcela.get(j);
					
					in = in + lcrp.getIdLancamentoContaReceber().getSqlValue();
					
				}
			
				LancamentoContaReceber lcr = new LancamentoContaReceber();
				LancamentoContaReceber lcrCriterio = new LancamentoContaReceber();
				ILancamentoContaReceberService lancamentoContaReceberService = new LancamentoContaReceberService();
				ArrayList listaLcr = new ArrayList();
				
				where = "where idLancamentoContaReceber IN (" + in + ")";
				
				listaLcr = lancamentoContaReceberService.consultarLancamentoContaReceber(lcrCriterio, where, con);
				
				if (listaLcr.size() > 1){
					
					for (int x = 1; x < listaLcr.size(); x++) {
				
						lcr = (LancamentoContaReceber) listaLcr.get(x);
					
						BeanUtils.copyProperties(lcrCriterio, lcr);
						
						lcr.setObservacao(new KscString(notaFiscalGF.getProducaoEmpenho().getWebValue() + " " + notaFiscalGF.getEmail().getWebValue()));
						lcr.setIdNotaFiscalGF(notaFiscalGF.getIdNotaFiscalGF());
						
						if (lcr.getNotasFiscais().isDbNull() || lcr.getNotasFiscais().getWebValue().equals("")){
							
							lcr.setNotasFiscais(notaFiscalGF.getNumeroNfse());
							
						} else {
							
							lcr.setNotasRelacionadas(new KscString("/" + lcr.getNotasFiscais().getWebValue()));
							
						}
						
						lcrCriterio = new LancamentoContaReceber();
						
						lcrCriterio.setIdLancamentoContaReceber(lcr.getIdLancamentoContaReceber());
						
						lancamentoContaReceberService.alterarLancamentoContaReceber(lcr, lcrCriterio, con);
					}
				
				}
			
			}
			
			
		}
		
		
	}
	
	//	private final void imprimirBoletosParcelas(NotaFiscalGF notaFiscalGF, Connection con) throws IOException, KscException, MessagingException, PrinterException{
//		
////		NotaFiscalGF notaCriterio = new NotaFiscalGF();
////		INotaFiscalGFService notaFiscalGfService = new NotaFiscalGFService();
////		//ArrayList listaNotaFiscalGF = new ArrayList();
////		
////		
////		notaCriterio.setIdOrdemServico(notaFiscalGF.getIdOrdemServico());
////		notaCriterio.setCancelada(new KscBoolean(false));
//		
//		//listaNotaFiscalGF = notaFiscalGfService.consultarNotaFiscalGF(notaCriterio, "", con);
//		
//		
//		NotaxOrdem notaxOrdem = new NotaxOrdem();
//		INotaxOrdemService notaxOrdemService = new NotaxOrdemService();
//		ArrayList listaNotaxOrdem = new ArrayList();
//		
//		notaxOrdem.setIdNotaFiscalGF(notaFiscalGF.getIdNotaFiscalGF());
//		
//		listaNotaxOrdem = notaxOrdemService.consultarNotaxOrdem(notaxOrdem, "", con);
//		
//		for (int k = 1; k < listaNotaxOrdem.size(); k++){
//			
//			notaxOrdem = (NotaxOrdem) listaNotaxOrdem.get(k);
//			
//			OrdemServico ordemServico = new OrdemServico();
//			IOrdemServicoService ordemServicoService = new OrdemServicoService();
//			ArrayList listaOrdemServico = new ArrayList();
//			
//			ordemServico.setIdOrdemServico(notaxOrdem.getIdOrdemServico());
//			
//			listaOrdemServico = ordemServicoService.consultarOrdemServico(ordemServico, "", con);
//		
//			if (listaOrdemServico.size() > 1){
//			
//				ordemServico = (OrdemServico) listaOrdemServico.get(1);
//			
//				ComandoBoleto cb = new ComandoBoleto();
//				cb.setIdEmpresa(notaFiscalGF.getIdEmpresa());
//				ArrayList alCB = new ComandoBoletoService().consultarComandoBoleto(cb,"",con);
//				if(alCB.size()!=2)
//					throw new KscException("Não foi possível identificar o comando do boleto para a empresa " + notaFiscalGF.getEmpresa().getRazaoSocial().getWebValue());
//				else
//					cb=(ComandoBoleto)alCB.get(1);
//				
//				IParcelasService parcelasService = new ParcelasService();
//				//String[] listaPagamentoPedidoVenda = request.getParameterValues("iListaPagamentoPedidoVenda");
//				//JBoleto jBoleto = new JBoleto();
//				Parcelas criterioParcelas = new Parcelas();
//				ArrayList listaParcelas = new ArrayList();
//				
//				criterioParcelas.setIdOrdemServico(ordemServico.getIdOrdemServico());
//				
//				listaParcelas = parcelasService.consultarParcelas(criterioParcelas, "", con);
//				
//				Empresa empresa = new Empresa();
//				ArrayList listaEmpresa = new ArrayList();
//				IEmpresaService empresaService = new EmpresaService();
//				
//				empresa.setIdEmpresa(notaFiscalGF.getIdEmpresa());
//				
//				listaEmpresa = empresaService.consultarEmpresa(empresa, "", con);
//				
//				if (listaEmpresa.size() < 2)
//					throw new KscException("Não foi possível gerar o boleto bancário: Empresa cedenta não encontrada (id " + empresa.getIdEmpresa().getWebValue() + ")");
//				
//				empresa = (Empresa) listaEmpresa.get(1);
//				
//				boolean existeParcelas = false;
//				boolean existeBoleto = false;
//				
//				List<Boleto> boletos = new ArrayList<Boleto>();
//				
//				for (int i=1; i < listaParcelas.size(); i++)
//				{
//					
//					
//					LancamentoContaReceberParcela lancamentoContaReceberParcela = new LancamentoContaReceberParcela();
//					ILancamentoContaReceberParcelaService lancamentoContaReceberParcelaService = new LancamentoContaReceberParcelaService();
//					ArrayList listaLancamentoContaReceberParcela = new ArrayList();
//					
//					Parcelas parcela = new Parcelas();
//					
//					parcela = (Parcelas)listaParcelas.get(i);
//					
//					if (!parcela.getIdFormaPagamento().getWebValue().equals(KscConstante.propriedadesSistema.getProperty("FORMA_PAGAMENTO_DEPOSITO"))) {
//					
//						lancamentoContaReceberParcela.setIdParcela(parcela.getIdParcelas());
//						
//						listaLancamentoContaReceberParcela = lancamentoContaReceberParcelaService.consultarLancamentoContaReceberParcela(lancamentoContaReceberParcela, "", con);
//						
//						if (listaLancamentoContaReceberParcela.size() < 2)
//							throw new KscException("Não foi encontrado lançamento de conta a receber relacionado com a parcela selecionada");
//						
//						lancamentoContaReceberParcela = (LancamentoContaReceberParcela) listaLancamentoContaReceberParcela.get(1);
//						
//						LancamentoContaReceber lancamentoContaReceber = new LancamentoContaReceber();
//						ILancamentoContaReceberService lancamentoContaReceberService = new LancamentoContaReceberService();
//						ArrayList listaLancamentoContaReceber = new ArrayList();
//						
//						
//						lancamentoContaReceber.setIdLancamentoContaReceber(lancamentoContaReceberParcela.getIdLancamentoContaReceber());
//						
//						listaLancamentoContaReceber = lancamentoContaReceberService.consultarLancamentoContaReceber(lancamentoContaReceber, "", con);
//						
//						if (listaLancamentoContaReceber.size() < 2)
//							throw new KscException("Não foi encontrada nenhum relacionamento de conta à receber com id " + lancamentoContaReceberParcela.getIdLancamentoContaReceber().getWebValue());
//						
//						lancamentoContaReceber = (LancamentoContaReceber) listaLancamentoContaReceber.get(1);
//		
//		
//						Cliente criterioCliente = new Cliente();
//						criterioCliente.setIdCliente(notaFiscalGF.getIdCliente());
//						ClienteService cs = new ClienteService();
//						Cliente cli = (Cliente)cs.consultarCliente(criterioCliente, "", con).get(1);
//						
//									
//						EnderecoCliente criterioEndereco = new EnderecoCliente();
//						criterioEndereco.setIdCliente(cli.getIdCliente());
//						criterioEndereco.setFaturamento(new KscBoolean(true));
//						EnderecoClienteService ecs = new EnderecoClienteService();
//						EnderecoCliente endereco = new EnderecoCliente();
//						ArrayList listaEndereco = ecs.consultarEnderecoClienteCompleto(criterioEndereco, "", con);
//						if(listaEndereco.size()==1){
//							throw new KscException("Cliente não possui endereço de faturamento, é necessário cadastrar na ABA ENDERECO no cadastro do cliente selecionando a opção faturamento SIM.");
//						}else{
//							endereco = (EnderecoCliente)listaEndereco.get(1);
//						}
//										
//									
//	//					JBoletoBean jBoletoBean = new JBoletoBean();
//	//								
//	//					jBoletoBean.setDataDocumento(new KscDate(new java.util.Date()).getWebValue());
//	//					jBoletoBean.setDataProcessamento(new KscDate(new java.util.Date()).getWebValue());   
//	//					//jBoletoBean.setNoDocumento(lancamentoContaReceber.getDocumentoPagamento().getWebValue());
//	//					jBoletoBean.setNoDocumento("1234");
//	//						            
//	//					jBoletoBean.setCedente(cb.getCedente().getWebValue());  
//	//					jBoletoBean.setCarteira(cb.getCarteira().getWebValue());
//	//		
//	//				    jBoletoBean.setNomeSacado(cli.getNomeCompletoRazaoSocial().getWebValue());
//	//				    jBoletoBean.setEnderecoSacado(endereco.getEndereco().getWebValue()+" "+ endereco.getComplemento().getWebValue());        
//	//				    jBoletoBean.setBairroSacado(endereco.getBairro().getWebValue());
//	//				    jBoletoBean.setCidadeSacado(endereco.getCidade().getDescricao().getWebValue());
//	//					jBoletoBean.setUfSacado(endereco.getEstado().getSigla().getWebValue());
//	//					jBoletoBean.setCepSacado(endereco.getCep().getWebValue());
//	//					jBoletoBean.setCpfSacado(cli.getCnpjCpf().getWebValue());
//	//						        
//	//					Vector descricoes = new Vector();
//	//					descricoes.add("Vendedor: " + funcionario.getNomeCompleto().getWebValue());
//	//					descricoes.add("Ordem Serviço " + ordemServico.getCodigo().getWebValue());
//	//						       
//	//					jBoletoBean.setDescricoes(descricoes);
//	//							        
//	//					jBoletoBean.setDataVencimento(parcela.getDataVencimento().getWebValue());
//	//					jBoletoBean.setInstrucao1(cb.getInstrucao1().getWebValue());
//	//					jBoletoBean.setInstrucao2(cb.getInstrucao2().getWebValue());
//	//					jBoletoBean.setInstrucao3(cb.getInstrucao3().getWebValue());
//	//					jBoletoBean.setInstrucao4(cb.getInstrucao4().getWebValue());
//	//			
//	//					jBoletoBean.setAgencia(cb.getAgencia().getWebValue());
//	//					jBoletoBean.setContaCorrente(cb.getContaCorrente().getWebValue());
//	//					jBoletoBean.setDvContaCorrente(cb.getDigitoConta().getWebValue());
//	//					        
//	//					//jBoletoBean.setNossoNumero(lancamentoContaReceber.getDocumentoPagamento().getWebValue(),8);
//	//					jBoletoBean.setNossoNumero("12345678", 8);
//	//					jBoletoBean.setValorBoleto(""+parcela.getValor().doubleValue()); 
//	//					
//	//							        
//	//						        //jBoleto.addBoleto(jBoletoBean,cb.getCodigoBoleto().intValue());
//	//					jBoleto.addBoleto(jBoletoBean,JBoleto.ITAU);
//						
//						/*
//		                 * INFORMANDO DADOS SOBRE O CEDENTE.
//		                 */
//						
//						
//						
//		                Cedente cedente = new Cedente(empresa.getRazaoSocial().getWebValue(), empresa.getCnpjCpf().getWebValue());
//	
//		                /*
//		                 * INFORMANDO DADOS SOBRE O SACADO.
//		                 */
//		                Sacado sacado = new Sacado(cli.getNomeCompletoRazaoSocial().getWebValue(), cli.getCnpjCpf().getWebValue());
//		                UnidadeFederativa teste;
//		                
//		                // Informando o endereço do sacado.
//		                Endereco enderecoSac = new Endereco();
//		                enderecoSac.setUF(retornaEstado(endereco.getEstado().getSigla().getWebValue()));
//		                enderecoSac.setLocalidade(endereco.getCidade().getDescricao().getWebValue());
//		                enderecoSac.setCep(endereco.getCep().getWebValue());
//		                enderecoSac.setBairro(endereco.getBairro().getWebValue());
//		                enderecoSac.setLogradouro(endereco.getTipoLogradouro().getDescricao().getWebValue());
//		                enderecoSac.setNumero(endereco.getNumero().getWebValue());
//		                sacado.addEndereco(enderecoSac);
//	
//		                /*
//		                 * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
//		                 */
//	
//		                /*
//		                 * INFORMANDO OS DADOS SOBRE O TÍTULO.
//		                 */
//		                
//		                // Informando dados sobre a conta bancária do título.
//		                ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_ITAU.create());
//		                contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.valueOf(cb.getContaCorrente().getWebValue().trim()), cb.getDigitoConta().getWebValue()));
//		                Carteira carteira = new Carteira();
//		                carteira.setCodigo(cb.getCarteira().getValue());
//		                contaBancaria.setCarteira(carteira);
//		                contaBancaria.setAgencia(new Agencia(Integer.parseInt(cb.getAgencia().getWebValue())));
//		                
//		                int dac = gerarDacNossoNumero(cb.getAgencia().getWebValue(), cb.getContaCorrente().getWebValue(), cb.getCarteira().getWebValue(), lancamentoContaReceber.getDocumentoPagamento().getWebValue());
//		                
//		                Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
//		                titulo.setNumeroDoDocumento(notaFiscalGF.getNumero().getWebValue());
//		                titulo.setNossoNumero(lancamentoContaReceber.getDocumentoPagamento().getWebValue());
//		                titulo.setDigitoDoNossoNumero(Integer.toString(dac));
//		                titulo.setValor(BigDecimal.valueOf(parcela.getValor().doubleValue()));
//		                titulo.setDataDoDocumento(new Date());
//		                titulo.setDataDoVencimento(parcela.getDataVencimento().getValue());
//		                titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
//		                titulo.setAceite(EnumAceite.N);
//		                titulo.setValorCobrado(BigDecimal.valueOf(parcela.getValor().doubleValue()));
//		                
//		                
//		                /*
//		                 * INFORMANDO OS DADOS SOBRE O BOLETO.
//		                 */
//		                Boleto boleto = new Boleto(titulo);
//		                
//		                boleto.setLocalPagamento("Até o vencimento, preferencialmente no Itaú.  " +
//		                                "Após o vencimento, somente no Itaú.");
//		                boleto.setInstrucao1(cb.getInstrucao1().getWebValue());
//		                boleto.setInstrucao2(cb.getInstrucao2().getWebValue());
//		                boleto.setInstrucao3(cb.getInstrucao3().getWebValue());
//		                boleto.setInstrucao4(cb.getInstrucao4().getWebValue());
//		                
//		                String notasFiscais = notaFiscalGF.getNumeroNfse().getWebValue();
//		                
//	//	                for (int j = 1; j < listaNotaFiscalGF.size(); j++){
//	//	                	
//	//	                	notasFiscais = notasFiscais + "/" + ((NotaFiscalGF) listaNotaFiscalGF.get(j)).getNumeroNfse().getWebValue();
//	//	                	
//	//	                }
//		                
//		                
//		                if (notasFiscais != "")
//		                	boleto.setInstrucao5("Nota(s): " + notasFiscais);
//		                
//		                String nossoNumero = new DecimalFormat("00000000").format(Integer.parseInt(lancamentoContaReceber.getDocumentoPagamento().getValue()));
//		                
//		                boleto.addTextosExtras("txtRsNossoNumero", cb.getCarteira().getWebValue() + "/" + nossoNumero + "-" + dac);
//		                boleto.addTextosExtras("txtFcNossoNumero", cb.getCarteira().getWebValue() + "/" + nossoNumero + "-" + dac);
//		                boleto.addTextosExtras("txtRsAgenciaCodigoCedente", cb.getAgencia().getWebValue() + "/" + cb.getContaCorrente().getWebValue() + "-" + cb.getDigitoConta().getWebValue());
//		                boleto.addTextosExtras("txtFcAgenciaCodigoCedente", cb.getAgencia().getWebValue() + "/" + cb.getContaCorrente().getWebValue() + "-" + cb.getDigitoConta().getWebValue());
//		                boletos.add(boleto);
//						
//						existeBoleto = true;
//					
//					}
//							        
//						        
//							//}
//						//}
//					
//					existeParcelas = true;
//						
//				}
//	
//				
//				if(existeParcelas){
//					 if(!KscConstante.propriedadesSistema.containsKey("SMTP_PCP"))
//			         {
//			             throw new KscException("verifique se o arquivoo de propriedades do sistema cont\351m a chave SMTP_PCP");
//			         }
//			         if(!KscConstante.propriedadesSistema.containsKey("EMAIL_PCP"))
//			         {
//			             throw new KscException("verifique se o arquivoo de propriedades do sistema cont\351m a chave EMAIL_PCP");
//			         }
//			         if(!KscConstante.propriedadesSistema.containsKey("SENHA_EMAIL_PCP"))
//			         {
//			             throw new KscException("verifique se o arquivoo de propriedades do sistema cont\351m a chave SENHA_EMAIL_PCP");
//			         }
//			         
//			     	Properties props = new Properties();   
//	//		     	  props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP   
//	//		          props.put("mail.smtp.starttls.enable","true");   
//	//		          props.put("mail.smtp.host", "smtp.gmail.com"); //server SMTP do GMAIL   
//	//		          props.put("mail.smtp.auth", "true"); //ativa autenticacao   
//	//		          props.put("mail.smtp.user", "rafaelhadas@gmail.com"); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)   
//	//		          props.put("mail.debug", "true");   
//	//		          props.put("mail.smtp.port", "465"); //porta   
//	//		          props.put("mail.smtp.socketFactory.port", "465"); //mesma porta para o socket   
//	//		          props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
//	//		          props.put("mail.smtp.socketFactory.fallback", "false");   
//			          
//	//		     	 SimpleAuth auth = null;   
//	//		         //auth = new SimpleAuth ("rafael.hadas@kscbrasil.com.br","rh1701"); 
//	//		     	 auth = new SimpleAuth ("rafaelhadas@gmail.com","8809880988"); 
//	//		          Session session = Session.getDefaultInstance(props, auth);   
//	//		          session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email   
//	//		    
//	//		          //Objeto que contém a mensagem   
//	//		          Message msg = new MimeMessage(session);   
//	
//			             
//	
//	            	props.put("mail.host", "10.1.1.250");  
//			     	//p.put("mail.host","smtp.kscbrasil.com.br");  
//	            	Session session = Session.getInstance(props, null);  
//	            	
//					
//					
//					
//					//Document doc = new Document();
//					//PdfWriter writer = PdfWriter.getInstance(doc,baos);
//	//				response.setContentLength(baos.size());
//	//				response.setContentType("application/pdf");
//	//				ServletOutputStream out = response.getOutputStream();
//	//				baos.writeTo(out);
//		
//					//out.flush();
//					
//					 Multipart corpo = new MimeMultipart();   
//					 ByteArrayOutputStream baos = new ByteArrayOutputStream();
//					
//					 if (existeBoleto){
//					    InternetHeaders headers = new InternetHeaders();   
//				        headers.addHeader("Content-Type", "application/pdf");
//				        
//		            	
//						MimeBodyPart partPhoto = new MimeBodyPart();   
//			            partPhoto.setDisposition(javax.mail.Part.ATTACHMENT);
//			            partPhoto.setDataHandler(new DataHandler(new ByteArrayDataSource(BoletoViewer.groupInOnePDF(boletos), "aplication/pdf")));   
//			            partPhoto.setFileName("boleto_os_" + ordemServico.getCodigo().getWebValue() + ".pdf");   
//			            
//			            corpo.addBodyPart(partPhoto); 
//					 }
//		  
//		            MimeBodyPart texto = new MimeBodyPart();
//		            
//		            String cnpjCpf = empresa.getCnpjCpf().getWebValue().replace(".","").replace("-","").replace("/","");
//		            
//		            String textoEmail;
//		            
//		            textoEmail = "SEGUE NOTA FISCAL DE SERVIÇOS PRESTADOS DA F9 Nº " + notaFiscalGF.getNumeroNfse().getWebValue();
//		            textoEmail = textoEmail + "\n\nVENCIMENTO: " + notaFiscalGF.getVencimento().getWebValue();
//		            
//		            textoEmail = textoEmail + "\n\n\nNOTA FISCAL: " + "https://issCuritiba.curitiba.pr.gov.br/portalnfse/Default.aspx?doc=" +cnpjCpf + "&num=" + notaFiscalGF.getNumeroNfse().getWebValue() + "&cod=" + notaFiscalGF.getCodigoVerificacao().getWebValue();
//		            
//		            textoEmail = textoEmail + "\n\nAtenciosamente";
//		            
//		            texto.setText(textoEmail);
//		            
//		            
//		            //corpo.addBodyPart(texto);
//		            
//		            BodyPart messageBodyPart = new MimeBodyPart();
//	
//		            
//		            String htmlText = "<img src=\"cid:cabecalho\"> <br>";
//		            htmlText = htmlText + "<br>SEGUE NOTA FISCAL DE SERVIÇOS PRESTADOS DA F9 Nº <b>" + notaFiscalGF.getNumeroNfse().getWebValue() + "</b>";
//		            htmlText = htmlText + "<br><br>VENCIMENTO: <b>" + notaFiscalGF.getVencimento().getWebValue() + "</b>";
//		            
//		            if (existeBoleto)
//		            	htmlText = htmlText + "<br><br>FORMA DE PAGAMENTO: <b>BOLETO BANCARIO ITAU</b>";
//		            else
//		            	htmlText = htmlText + "<br><br>FORMA DE PAGAMENTO: <b>DEPOSITO BANCO ITAU AG. 3701 CONTA 02738-9</b>";
//		            
//		            htmlText = htmlText + "<br><br>NotaFiscal: " + "<a href=\"https://issCuritiba.curitiba.pr.gov.br/portalnfse/Default.aspx?doc=" +cnpjCpf + "&num=" + notaFiscalGF.getNumeroNfse().getWebValue() + "&cod=" + notaFiscalGF.getCodigoVerificacao().getWebValue();
//		            htmlText = htmlText + "\"> " + "https://issCuritiba.curitiba.pr.gov.br/portalnfse/Default.aspx?doc=" +cnpjCpf + "&num=" + notaFiscalGF.getNumeroNfse().getWebValue() + "&cod=" + notaFiscalGF.getCodigoVerificacao().getWebValue();
//		            htmlText = htmlText + "</a>";
//		            
//		            htmlText = htmlText + "<br><br><br><br>Atenciosamente,";
//		            
//		            htmlText = htmlText + "<br><br><img src=\"cid:rodape\"> <br>";
//		            
//		            messageBodyPart.setContent(htmlText, "text/html");
//		            
//		            corpo.addBodyPart(messageBodyPart);
//	 
//		            messageBodyPart = new MimeBodyPart();
//		            DataSource fds = new FileDataSource
//		              (KscConstante.DIRETORIO_PROJETO+ "/images/cabecalhoemail.jpg");
//		            messageBodyPart.setDataHandler(new DataHandler(fds));
//		            messageBodyPart.setHeader("Content-ID","<cabecalho>");
//		            
//		            corpo.addBodyPart(messageBodyPart);
//		            
//		            messageBodyPart = new MimeBodyPart();
//		            fds = new FileDataSource
//		              (KscConstante.DIRETORIO_PROJETO+ "/images/rodapeemail.jpg");
//		            messageBodyPart.setDataHandler(new DataHandler(fds));
//		            messageBodyPart.setHeader("Content-ID","<rodape>");
//		            
//		            corpo.addBodyPart(messageBodyPart);
//	 
//		  
//	            	
//	            	MimeMessage msg = new MimeMessage(session);  
//	            	msg.setFrom(new InternetAddress( "nfe@f9.com.br")); 
//	            	
//	            	if (!notaFiscalGF.getEmail().equals(null) && !notaFiscalGF.getEmail().getWebValue().equals("")){
//	            		
//	            	
//		            	String emails[] = notaFiscalGF.getEmail().getWebValue().split(";");
//		            	
//		            	for (int j = 0; j < emails.length; j++){
//		            		
//		            		InternetAddress internetAddress = new InternetAddress(emails[j]);
//		            	
//			            	msg.setRecipient(Message.RecipientType.TO, internetAddress); 
//			            	  
//			            	    // nao esqueca da data!   
//			            	    // ou ira 31/12/1969 !!!   
//			            	msg.setSentDate(new Date());   
//			            	msg.setSubject("Boleto OS Numero " + ordemServico.getCodigo().getWebValue());   
//			            	//msg.setText("Esse e-mail é apenas um teste realizado para o sistema Dinamo. Por favor, ignore-o."); 
//			            	//msg.setContent(corpo);
//			            	
//			            	msg.setContent(corpo);  
//			            	
//			            	Transport tr; 
//			            	//tr = session.getTransport("smtp"); //define smtp para transporte   
//			                 /*  
//			                  *  1 - define o servidor smtp  
//			                  *  2 - seu nome de usuario do gmail  
//			                  *  3 - sua senha do gmail  
//			                  */   
//			                //tr.connect(KscConstante.propriedadesSistema.getProperty("SMTP_PCP"), " nfe@f9.com.br", null);   
//			                //msg.saveChanges(); // don't forget this   
//			                 //envio da mensagem   
//	//		                tr.sendMessage(msg, msg.getAllRecipients());   
//	//		                tr.close(); 
//			                
//			                Transport.send(msg);
//		            	}
//	            	}
//	
//	            	//Transport.send(msg);   
//					
//	//            	if (existeBoleto)
//	//            		imprimirPdf(BoletoViewer.groupInOnePDF(boletos), nomeImpressora.getWebValue(), "Impressão boleto");
//				}
//				
//			}
//		}
//			
//			
//				
//		
//	}
	
	private final void imprimirBoletosParcelas(NotaFiscalGF notaFiscalGF, Connection con) throws IOException, KscException, MessagingException, PrinterException{
		
//		NotaFiscalGF notaCriterio = new NotaFiscalGF();
//		INotaFiscalGFService notaFiscalGfService = new NotaFiscalGFService();
//		//ArrayList listaNotaFiscalGF = new ArrayList();
//		
//		
//		notaCriterio.setIdOrdemServico(notaFiscalGF.getIdOrdemServico());
//		notaCriterio.setCancelada(new KscBoolean(false));
		
		//listaNotaFiscalGF = notaFiscalGfService.consultarNotaFiscalGF(notaCriterio, "", con);
		
		
		NotaxOrdem notaxOrdem = new NotaxOrdem();
		INotaxOrdemService notaxOrdemService = new NotaxOrdemService();
		ArrayList listaNotaxOrdem = new ArrayList();
		
		notaxOrdem.setIdNotaFiscalGF(notaFiscalGF.getIdNotaFiscalGF());
		
		listaNotaxOrdem = notaxOrdemService.consultarNotaxOrdem(notaxOrdem, "", con);
		
		String listaOrdens = "";
		
		for (int k = 1; k < listaNotaxOrdem.size(); k++){
			
			notaxOrdem = (NotaxOrdem) listaNotaxOrdem.get(k);
			
			if (k != 1)
				listaOrdens = listaOrdens + ", ";
			
			listaOrdens = listaOrdens + notaxOrdem.getIdOrdemServico().getSqlValue();
		}
		
		OrdemServico os = new OrdemServico();
		IOrdemServicoService ordemServicoService = new OrdemServicoService();
		ArrayList listaOrdemServico = new ArrayList();
		
		os.setIdOrdemServico(((NotaxOrdem)listaNotaxOrdem.get(1)).getIdOrdemServico());
		
		listaOrdemServico = ordemServicoService.consultarOrdemServico(os, "", con);
		
		if (listaOrdemServico.size() < 2)
			System.out.println("Não foi encontrada ordem de serviço com id " + os.getIdOrdemServico().getWebValue());
		
		os = (OrdemServico) listaOrdemServico.get(1);
		
		KscLong codigoOS = os.getCodigo();
		
		ComandoBoleto cb = new ComandoBoleto();
		cb.setIdEmpresa(notaFiscalGF.getIdEmpresa());
		ArrayList alCB = new ComandoBoletoService().consultarComandoBoleto(cb,"",con);
		if(alCB.size()!=2)
			System.out.println("Não foi possível identificar o comando do boleto para a empresa " + notaFiscalGF.getEmpresa().getRazaoSocial().getWebValue());
		else
			cb=(ComandoBoleto)alCB.get(1);
		
		IParcelasService parcelasService = new ParcelasService();
		//String[] listaPagamentoPedidoVenda = request.getParameterValues("iListaPagamentoPedidoVenda");
		//JBoleto jBoleto = new JBoleto();
		Parcelas criterioParcelas = new Parcelas();
		ArrayList listaParcelas = new ArrayList();
		
		Empresa empresa = new Empresa();
		ArrayList listaEmpresa = new ArrayList();
		IEmpresaService empresaService = new EmpresaService();
		
		empresa.setIdEmpresa(notaFiscalGF.getIdEmpresa());
		
		listaEmpresa = empresaService.consultarEmpresa(empresa, "", con);
		
		if (listaEmpresa.size() < 2)
			System.out.println("Não foi possível gerar o boleto bancário: Empresa cedente não encontrada (id " + empresa.getIdEmpresa().getWebValue() + ")");
		
		empresa = (Empresa) listaEmpresa.get(1);
		
		boolean existeParcelas = false;
		boolean existeBoleto = false;
		boolean existeDeposito = false;
		
		List<Boleto> boletos = new ArrayList<Boleto>();
		
		criterioParcelas.setIdNotaFiscalGF(notaFiscalGF.getIdNotaFiscalGF());
		
		listaParcelas = parcelasService.consultarParcelasCompleto(criterioParcelas, "and Parcelas.idOrdemServico IN (" + listaOrdens + ") order by dataVencimento", con);
		
		double valorBoleto = 0.0;
		int numeroParcela = 0;
		int qtdeParcelasNoBoleto = 0;
		
		ArrayList listaParcelasMesmoBoleto = new ArrayList();
		
		
		for (int i = 1; i < listaParcelas.size(); i++){
			
			Parcelas parcela = new Parcelas();
			
			parcela = (Parcelas) listaParcelas.get(i);
			
			if (parcela.getIdFormaPagamento().getWebValue().equals(KscConstante.propriedadesSistema.getProperty("FORMA_PAGAMENTO_BOLETO_BANCARIO"))) {
				
				valorBoleto = valorBoleto + parcela.getValor().doubleValue();
				existeParcelas = true;
				qtdeParcelasNoBoleto = qtdeParcelasNoBoleto + 1;
				listaParcelasMesmoBoleto.add(parcela);
				
			} else if (parcela.getIdFormaPagamento().getWebValue().equals(KscConstante.propriedadesSistema.getProperty("FORMA_PAGAMENTO_DEPOSITO"))){
				
				existeDeposito = true;
				
			}
			
					
			if ((i+1) == listaParcelas.size() || !parcela.getDataVencimento().getWebValue().equals(((Parcelas)listaParcelas.get(i + 1)).getDataVencimento().getWebValue())){
				
				if (existeParcelas) {
					
					String nossoNumero = "";
					
					existeParcelas = false;
					
					
					if (qtdeParcelasNoBoleto > 1){
						
						numeroParcela = numeroParcela + 1;
						
						nossoNumero = codigoOS.getWebValue() + numeroParcela;
						
						for (int k = 0; k < listaParcelasMesmoBoleto.size(); k++){
							
							LancamentoContaReceberParcela lancamentoContaReceberParcela = new LancamentoContaReceberParcela();
							ILancamentoContaReceberParcelaService lancamentoContaReceberParcelaService = new LancamentoContaReceberParcelaService();
							ArrayList listaLancamentoContaReceberParcela = new ArrayList();
							
							Parcelas criterioParcela = (Parcelas) listaParcelasMesmoBoleto.get(k);
							
							lancamentoContaReceberParcela.setIdParcela(criterioParcela.getIdParcelas());
							
							listaLancamentoContaReceberParcela = lancamentoContaReceberParcelaService.consultarLancamentoContaReceberParcela(lancamentoContaReceberParcela, "", con);
							
							if (listaLancamentoContaReceberParcela.size() < 2)
								System.out.println("Não foi encontrado lançamento de conta a receber parcela com id parcela " + lancamentoContaReceberParcela.getIdParcela().getWebValue());
							
							LancamentoContaReceber lancamentoContaReceber = new LancamentoContaReceber();
							LancamentoContaReceber criterioLancamentoContaReceber = new LancamentoContaReceber();
							ILancamentoContaReceberService lancamentoContaReceberService = new LancamentoContaReceberService();
							ArrayList listaLancamentoContaReceber = new ArrayList();
							
							lancamentoContaReceberParcela = (LancamentoContaReceberParcela) listaLancamentoContaReceberParcela.get(1);
							
							criterioLancamentoContaReceber.setIdLancamentoContaReceber(lancamentoContaReceberParcela.getIdLancamentoContaReceber());
							
							listaLancamentoContaReceber = lancamentoContaReceberService.consultarLancamentoContaReceber(criterioLancamentoContaReceber, "", con);
							
							lancamentoContaReceber = (LancamentoContaReceber) listaLancamentoContaReceber.get(1);
							lancamentoContaReceber.setDocumentoPagamento(new KscString(nossoNumero));
							lancamentoContaReceber.setIdEmpresa(notaFiscalGF.getIdEmpresa());
							
							lancamentoContaReceberService.alterarLancamentoContaReceber(lancamentoContaReceber, criterioLancamentoContaReceber, con);
							
						}
					
						
					} else {
						
						LancamentoContaReceberParcela lancamentoContaReceberParcela = new LancamentoContaReceberParcela();
						ILancamentoContaReceberParcelaService lancamentoContaReceberParcelaService = new LancamentoContaReceberParcelaService();
						ArrayList listaLancamentoContaReceberParcela = new ArrayList();
						
						lancamentoContaReceberParcela.setIdParcela(parcela.getIdParcelas());
						
						listaLancamentoContaReceberParcela = lancamentoContaReceberParcelaService.consultarLancamentoContaReceberParcela(lancamentoContaReceberParcela, "", con);
						
						if (listaLancamentoContaReceberParcela.size() < 2)
							System.out.println("Não foi encontrado lançamento de caonta a receber parcela com id parcela " + lancamentoContaReceberParcela.getIdParcela().getWebValue());
						
						lancamentoContaReceberParcela = (LancamentoContaReceberParcela) listaLancamentoContaReceberParcela.get(1);
						
						LancamentoContaReceber lancamentoContaReceber = new LancamentoContaReceber();
						ILancamentoContaReceberService lancamentoContaReceberService = new LancamentoContaReceberService();
						ArrayList listaLancamentoContaReceber = new ArrayList();
						
						lancamentoContaReceber.setIdLancamentoContaReceber(lancamentoContaReceberParcela.getIdLancamentoContaReceber());
						
						listaLancamentoContaReceber = lancamentoContaReceberService.consultarLancamentoContaReceber(lancamentoContaReceber, "", con);
						
						if (listaLancamentoContaReceber.size() < 2)
							System.out.println("Não foi encontrado lançamento de conta a receber relacionado ao idLancamentoContaReceberParcela " + lancamentoContaReceber.getIdLancamentoContaReceber());
						
						lancamentoContaReceber = (LancamentoContaReceber) listaLancamentoContaReceber.get(1);
						
						nossoNumero = lancamentoContaReceber.getDocumentoPagamento().getWebValue();
						
					}
					
					qtdeParcelasNoBoleto = 0;
					listaParcelasMesmoBoleto = new ArrayList();
		
					Cliente criterioCliente = new Cliente();
					criterioCliente.setIdCliente(notaFiscalGF.getIdCliente());
					ClienteService cs = new ClienteService();
					Cliente cli = (Cliente)cs.consultarCliente(criterioCliente, "", con).get(1);
						
									
//					EnderecoCliente criterioEndereco = new EnderecoCliente();
//					criterioEndereco.setIdCliente(cli.getIdCliente());
//					criterioEndereco.setFaturamento(new KscBoolean(true));
//					EnderecoClienteService ecs = new EnderecoClienteService();
//					EnderecoCliente endereco = new EnderecoCliente();
//					ArrayList listaEndereco = ecs.consultarEnderecoClienteCompleto(criterioEndereco, "", con);
//					if(listaEndereco.size()==1){
//						System.out.println("Cliente não possui endereço de faturamento, é necessário cadastrar na ABA ENDERECO no cadastro do cliente selecionando a opção faturamento SIM.");
//					}else{
//						endereco = (EnderecoCliente)listaEndereco.get(1);
//					}
										
									
						
						/*
		                 * INFORMANDO DADOS SOBRE O CEDENTE.
		                 */
						
						
						
		             Cedente cedente = new Cedente(empresa.getRazaoSocial().getWebValue(), empresa.getCnpjCpf().getWebValue());
	
		                /*
		                 * INFORMANDO DADOS SOBRE O SACADO.
		                 */
	                Sacado sacado = new Sacado(notaFiscalGF.getNomeCliente().getWebValue(), notaFiscalGF.getCnpjCliente().getWebValue());
	                UnidadeFederativa teste;
		                
	                // Informando o endereço do sacado.
	                Endereco enderecoSac = new Endereco();
	                
	                Cidade cidade = new Cidade();
	                ArrayList listaCidade = new ArrayList();
	                
	                cidade.setIdCidade(notaFiscalGF.getIdCidade());
	                
	                listaCidade = new CidadeService().consultarCidadeCompleto(cidade, "", con);
	                
	                if (listaCidade.size() < 2)
	                	System.out.println("Cidade com id " + notaFiscalGF.getIdCidade().getWebValue() + " não encontrada");
	                
	                cidade = (Cidade) listaCidade.get(1);
	                
	                enderecoSac.setUF(retornaEstado(cidade.getEstado().getSigla().getWebValue()));
	                enderecoSac.setLocalidade(cidade.getDescricao().getWebValue());
	                enderecoSac.setCep(notaFiscalGF.getCep().getWebValue());
	                enderecoSac.setBairro(notaFiscalGF.getBairro().getWebValue());
	                
	                TipoLogradouro tipoLogradouro = new TipoLogradouro();
	                ArrayList listaTipoLogradouro = new ArrayList();
	                
	                String enderecoCliente = "";
	                
	                if (!notaFiscalGF.getEnderecoClienteBoleto().isDbNull() && !notaFiscalGF.getEnderecoClienteBoleto().getWebValue().equals(""))
	                	enderecoCliente = notaFiscalGF.getEnderecoClienteBoleto().getWebValue();
	                else
	                	enderecoCliente = notaFiscalGF.getEnderecoCliente().getWebValue();
	                
	                if (!notaFiscalGF.getIdTipoLogradouro().isDbNull() && !notaFiscalGF.getIdTipoLogradouro().equals("")){
	                	
	                	tipoLogradouro.setIdTipoLogradouro(notaFiscalGF.getIdTipoLogradouro());
	                	
	                	listaTipoLogradouro = new TipoLogradouroService().consultarTipoLogradouro(tipoLogradouro, "", con);
	                	
	                	if (listaTipoLogradouro.size() > 1){
	                		
	                		tipoLogradouro = (TipoLogradouro) listaTipoLogradouro.get(1);
	                		enderecoSac.setLogradouro(tipoLogradouro.getDescricao().getWebValue() + " " + enderecoCliente);
	                		
	                	} else 
	                		enderecoSac.setLogradouro(enderecoCliente);
	                } else
	                	enderecoSac.setLogradouro(enderecoCliente);
	                
	                enderecoSac.setNumero(notaFiscalGF.getNumeroEndereco().getWebValue());
	                sacado.addEndereco(enderecoSac);
	
	                /*
	                 * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
	                 */
	
	                /*
	                 * INFORMANDO OS DADOS SOBRE O TÍTULO.
	                 */
		                
	                // Informando dados sobre a conta bancária do título.
	                ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_ITAU.create());
	                contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.valueOf(cb.getContaCorrente().getWebValue().trim()), cb.getDigitoConta().getWebValue()));
	                Carteira carteira = new Carteira();
	                carteira.setCodigo(cb.getCarteira().getValue());
	                contaBancaria.setCarteira(carteira);
	                contaBancaria.setAgencia(new Agencia(Integer.parseInt(cb.getAgencia().getWebValue())));
		                
	                int dac = gerarDacNossoNumero(cb.getAgencia().getWebValue(), cb.getContaCorrente().getWebValue(), cb.getCarteira().getWebValue(), nossoNumero);
		                
	                Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
	                //titulo.setNumeroDoDocumento(notaFiscalGF.getNumero().getWebValue());
	                titulo.setNumeroDoDocumento(nossoNumero);
	                titulo.setNossoNumero(nossoNumero);
	                titulo.setDigitoDoNossoNumero(Integer.toString(dac));
	                titulo.setValor(BigDecimal.valueOf(valorBoleto));
	                titulo.setDataDoDocumento(new Date());
	                titulo.setDataDoVencimento(parcela.getDataVencimento().getValue());
	                titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
	                titulo.setAceite(EnumAceite.N);
	                titulo.setValorCobrado(BigDecimal.valueOf(valorBoleto));
		                
		                
	                /*
	                 * INFORMANDO OS DADOS SOBRE O BOLETO.
	                 */
	                Boleto boleto = new Boleto(titulo);
		                
	                boleto.setLocalPagamento("Até o vencimento, preferencialmente no Itaú.  " +
	                                "Após o vencimento, somente no Itaú.");
	                boleto.setInstrucao1(cb.getInstrucao1().getWebValue());
	                boleto.setInstrucao2(cb.getInstrucao2().getWebValue());
	                boleto.setInstrucao3(cb.getInstrucao3().getWebValue());
	                boleto.setInstrucao4(cb.getInstrucao4().getWebValue());
		                
	                String notasFiscais = notaFiscalGF.getNumeroNfse().getWebValue();
		                
//	                for (int j = 1; j < listaNotaFiscalGF.size(); j++){
//	                	
//	                	notasFiscais = notasFiscais + "/" + ((NotaFiscalGF) listaNotaFiscalGF.get(j)).getNumeroNfse().getWebValue();
//	                	
//	                }
		                
		                
	                if (notasFiscais != "")
	                	boleto.setInstrucao5("Nota(s): " + notasFiscais);
	                
	                nossoNumero = new DecimalFormat("00000000").format(Integer.parseInt(nossoNumero));
		                
	                boleto.addTextosExtras("txtRsNossoNumero", cb.getCarteira().getWebValue() + "/" + nossoNumero + "-" + dac);
	                boleto.addTextosExtras("txtFcNossoNumero", cb.getCarteira().getWebValue() + "/" + nossoNumero + "-" + dac);
	                boleto.addTextosExtras("txtRsAgenciaCodigoCedente", cb.getAgencia().getWebValue() + "/" + cb.getContaCorrente().getWebValue() + "-" + cb.getDigitoConta().getWebValue());
	                boleto.addTextosExtras("txtFcAgenciaCodigoCedente", cb.getAgencia().getWebValue() + "/" + cb.getContaCorrente().getWebValue() + "-" + cb.getDigitoConta().getWebValue());
	                boletos.add(boleto);
						
					existeBoleto = true;
					
					valorBoleto = 0.0;
				}
					
						
			}
				
			
		}
			
			
		
			///////////////////////////////////////////////////////////////////////////////
			
			
			
//		if (enviarEmail){
	
				
				 if(!KscConstante.propriedadesSistema.containsKey("SMTP_PCP"))
		         {
		             System.out.println("verifique se o arquivoo de propriedades do sistema cont\351m a chave SMTP_PCP");
		         }
		         if(!KscConstante.propriedadesSistema.containsKey("EMAIL_PCP"))
		         {
		             System.out.println("verifique se o arquivoo de propriedades do sistema cont\351m a chave EMAIL_PCP");
		         }
		         if(!KscConstante.propriedadesSistema.containsKey("SENHA_EMAIL_PCP"))
		         {
		             System.out.println("verifique se o arquivoo de propriedades do sistema cont\351m a chave SENHA_EMAIL_PCP");
		         }
			         
		     	Properties props = new Properties();   
	//		     	  props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP   
	//		          props.put("mail.smtp.starttls.enable","true");   
	//		          props.put("mail.smtp.host", "smtp.gmail.com"); //server SMTP do GMAIL   
	//		          props.put("mail.smtp.auth", "true"); //ativa autenticacao   
	//		          props.put("mail.smtp.user", "rafaelhadas@gmail.com"); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)   
	//		          props.put("mail.debug", "true");   
	//		          props.put("mail.smtp.port", "465"); //porta   
	//		          props.put("mail.smtp.socketFactory.port", "465"); //mesma porta para o socket   
	//		          props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
	//		          props.put("mail.smtp.socketFactory.fallback", "false");   
			          
	//		     	 SimpleAuth auth = null;   
	//		         //auth = new SimpleAuth ("rafael.hadas@kscbrasil.com.br","rh1701"); 
	//		     	 auth = new SimpleAuth ("rafaelhadas@gmail.com","8809880988"); 
	//		          Session session = Session.getDefaultInstance(props, auth);   
	//		          session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email   
	//		    
	//		          //Objeto que contém a mensagem   
	//		          Message msg = new MimeMessage(session);   
	
			             
	
	           	props.put("mail.host", "10.1.1.250");  
			     	//p.put("mail.host","smtp.kscbrasil.com.br");  
	           	Session session = Session.getInstance(props, null);  
	            	
					
					
					
					//Document doc = new Document();
					//PdfWriter writer = PdfWriter.getInstance(doc,baos);
	//				response.setContentLength(baos.size());
	//				response.setContentType("application/pdf");
	//				ServletOutputStream out = response.getOutputStream();
	//				baos.writeTo(out);
		
					//out.flush();
					
				 Multipart corpo = new MimeMultipart();   
				 ByteArrayOutputStream baos = new ByteArrayOutputStream();
					
				 if (existeBoleto){
				    InternetHeaders headers = new InternetHeaders();   
			        headers.addHeader("Content-Type", "application/pdf");
				        
		            	
					MimeBodyPart partPhoto = new MimeBodyPart();   
		            partPhoto.setDisposition(javax.mail.Part.ATTACHMENT);
		            partPhoto.setDataHandler(new DataHandler(new ByteArrayDataSource(BoletoViewer.groupInOnePDF(boletos), "aplication/pdf")));   
		            partPhoto.setFileName("boleto_nf_" + notaFiscalGF.getNumeroNfse().getWebValue() + ".pdf");   
			            
		            corpo.addBodyPart(partPhoto); 
				 }
				 
				
				 
				 	MimeBodyPart texto = new MimeBodyPart();
		            
		           String cnpjCpf = empresa.getCnpjCpf().getWebValue().replace(".","").replace("-","").replace("/","");
		           
		           byte[] nota = baixarNotaFiscal("https://isscuritiba.curitiba.pr.gov.br/portalnfse/ImagemNfse.axd?doc=" +cnpjCpf + "&num=" + notaFiscalGF.getNumeroNfse().getWebValue() + "&cod=" + notaFiscalGF.getCodigoVerificacao().getWebValue());
					 if(nota != null){
					 	MimeBodyPart notaFiscal = new MimeBodyPart();   
					 	notaFiscal.setDisposition(javax.mail.Part.ATTACHMENT);
					 	notaFiscal.setDataHandler(new DataHandler(new ByteArrayDataSource(nota, "image/gif")));   
					 	notaFiscal.setFileName("notafiscal_" + notaFiscalGF.getNumeroNfse().getWebValue() + ".gif");
					 	corpo.addBodyPart(notaFiscal); 
					 }
					 
		           String textoEmail;
		            
		           textoEmail = "SEGUE NOTA FISCAL DE SERVIÇOS PRESTADOS DA F9 Nº " + notaFiscalGF.getNumeroNfse().getWebValue();
		           textoEmail = textoEmail + "\n\nVENCIMENTO: " + notaFiscalGF.getVencimento().getWebValue();
		            
		           textoEmail = textoEmail + "\n\n\nNOTA FISCAL: " + "https://issCuritiba.curitiba.pr.gov.br/portalnfse/Default.aspx?doc=" +cnpjCpf + "&num=" + notaFiscalGF.getNumeroNfse().getWebValue() + "&cod=" + notaFiscalGF.getCodigoVerificacao().getWebValue();
		            
		           textoEmail = textoEmail + "\n\nAtenciosamente";
		            
		           texto.setText(textoEmail);
		            
		            
		            //corpo.addBodyPart(texto);
		            
	            BodyPart messageBodyPart = new MimeBodyPart();
			            
	            String htmlText =  "<p style=\"margin-left: 0pt\"><span style='font-size:12t;font-family:Helvetica;color:black'>"+
	            	"<br>SEGUE NOTA FISCAL DE SERVI\u00c7OS PRESTADOS DA F9 No. <b>" + notaFiscalGF.getNumeroNfse().getWebValue() + "</b>";
	            htmlText = htmlText + "<br><br>VENCIMENTO: <b>" + notaFiscalGF.getVencimento().getWebValue() + "</b>";
		            
	            if (existeBoleto)
	            	htmlText = htmlText + "<br><br>FORMA DE PAGAMENTO: <b>BOLETO BANCARIO ITAU</b>";
	            else if(existeDeposito){
	            	
	            	ContaCorrente contaCorrente = new ContaCorrente();
	            	IContaCorrenteService contaCorrenteService = new ContaCorrenteService();
	            	ArrayList listaContaCorrente = new ArrayList();
	            	
	            	contaCorrente.setIdContaCorrente(notaFiscalGF.getIdContaCorrente());
	            	
	            	listaContaCorrente = contaCorrenteService.consultarContaCorrenteCompleto(contaCorrente, "", con);
	            	
	            	if (listaContaCorrente.size() > 1){
	            	
	            		contaCorrente = (ContaCorrente) listaContaCorrente.get(1);
	            		
	            		htmlText = htmlText + "<br><br>FORMA DE PAGAMENTO: <b>DEPOSITO " + contaCorrente.getBanco().getDescricao().getWebValue() +
	            					" AG. " + contaCorrente.getNumeroAgencia().getWebValue() + " CONTA " + contaCorrente.getNumeroConta().getWebValue() + "</b>";
	            		
	            	}
	            }
		            
	            htmlText = htmlText + "<br><br>Nota Fiscal: " + "<a href=\"https://issCuritiba.curitiba.pr.gov.br/portalnfse/Default.aspx?doc=" +cnpjCpf + "&num=" + notaFiscalGF.getNumeroNfse().getWebValue() + "&cod=" + notaFiscalGF.getCodigoVerificacao().getWebValue();
	            htmlText = htmlText + "\"> " + "https://issCuritiba.curitiba.pr.gov.br/portalnfse/Default.aspx?doc=" +cnpjCpf + "&num=" + notaFiscalGF.getNumeroNfse().getWebValue() + "&cod=" + notaFiscalGF.getCodigoVerificacao().getWebValue();
	            htmlText = htmlText + "</a>";
		            
	            htmlText = htmlText + "<br><br><br>";
	            htmlText = htmlText + "<span style='font-size:10pt;font-family:Helvetica;color:black'>";
	            htmlText = htmlText + "Atenciosamente,"+
	            "<BR><BR><b>F9 Identidade Visual</b><BR>"+
	            "<span style='font-size:9pt;font-family:Helvetica;color:black'>"+
	            "Depto. Faturamento<BR>" +
	            "[41] 3888-5000<BR>" +
	            "[41] 3888-5018<BR>" +
	            "</span>" +
	            "<a href=\"http://www.f9.com.br\">" +
	            "<a href=\"http://www.f9.com.br\"><img src=\"cid:rodape\"></a></p>";

	            		            
	            messageBodyPart.setContent(htmlText, "text/html; charset=UTF-8");
		            
	            corpo.addBodyPart(messageBodyPart);
	 
	            /*messageBodyPart = new MimeBodyPart();
	            DataSource fds = new FileDataSource
	              (KscConstante.DIRETORIO_PROJETO+ "/images/cabecalhoemail.jpg");
	            messageBodyPart.setDataHandler(new DataHandler(fds));
	            messageBodyPart.setHeader("Content-ID","<cabecalho>");*/
		            
	            //corpo.addBodyPart(messageBodyPart);
		            
	            messageBodyPart = new MimeBodyPart();
	            DataSource fds = new FileDataSource
	              (KscConstante.DIRETORIO_PROJETO+ "/images/rodapeemail.jpg");
	            messageBodyPart.setDataHandler(new DataHandler(fds));
	            messageBodyPart.setHeader("Content-ID","<rodape>");
		            
	            corpo.addBodyPart(messageBodyPart);
	 
		  
	            	
	           	MimeMessage msg = new MimeMessage(session);  
	           	msg.setFrom(new InternetAddress( "nfe@f9.com.br")); 
	            	
	           	if (!notaFiscalGF.getEmail().equals(null) && !notaFiscalGF.getEmail().getWebValue().equals("")){
	            		
	            	
	            	String emails[] = notaFiscalGF.getEmail().getWebValue().split(";");
		            	
	            	for (int j = 0; j < emails.length; j++){
		            		
	            		InternetAddress internetAddress = new InternetAddress(emails[j]);
		            	
		            	msg.setRecipient(Message.RecipientType.TO, internetAddress); 
			            	  
			            	    // nao esqueca da data!   
			            	    // ou ira 31/12/1969 !!!   
		            	msg.setSentDate(new Date());   
		            	msg.setSubject("Nota Fiscal F9 Numero " + notaFiscalGF.getNumeroNfse().getWebValue());   
			            	//msg.setText("Esse e-mail é apenas um teste realizado para o sistema Dinamo. Por favor, ignore-o."); 
			            	//msg.setContent(corpo);
			            	
		            	msg.setContent(corpo);  
			            	
		            	Transport tr; 
			            	//tr = session.getTransport("smtp"); //define smtp para transporte   
			                 /*  
			                  *  1 - define o servidor smtp  
			                  *  2 - seu nome de usuario do gmail  
			                  *  3 - sua senha do gmail  
			                  */   
			                //tr.connect(KscConstante.propriedadesSistema.getProperty("SMTP_PCP"), " nfe@f9.com.br", null);   
			                //msg.saveChanges(); // don't forget this   
			                 //envio da mensagem   
	//		                tr.sendMessage(msg, msg.getAllRecipients());   
	//		                tr.close(); 
			                
		                Transport.send(msg);
	            	}
	           	}
	
	            	//Transport.send(msg);   
					
	//            	if (existeBoleto)
	//            		imprimirPdf(BoletoViewer.groupInOnePDF(boletos), nomeImpressora.getWebValue(), "Impressão boleto");
		}
				
	//}

			
			
				
		
	
	private int gerarDacNossoNumero (String agencia, String contaCorrente, String carteira, String nossoNumero){
		
		int somaDigitos = 0;
		int multiplicador = 1;
		int dac = 0;
		
		for (int i = 0; i < agencia.length(); i++){
			
			char[] agenciaArray = agencia.toCharArray();
			
			int digito = Integer.parseInt("" + agenciaArray[i]);
			int multiplicacaoDigitos = digito * multiplicador;
			
			if (multiplicacaoDigitos >= 10){
				
				somaDigitos = somaDigitos + (1 + (multiplicacaoDigitos-10)) ;
				
			} else {
				
				somaDigitos = somaDigitos + multiplicacaoDigitos ;
				
			}
			
			
			if (multiplicador == 1)
				multiplicador = 2;
			else
				multiplicador = 1;
			
		}
		
		for (int i = 0; i < contaCorrente.length(); i++){
			
			char[] contaCorrenteArray = contaCorrente.toCharArray();
			
			int digito = Integer.parseInt("" + contaCorrenteArray[i]);
			int multiplicacaoDigitos = digito * multiplicador;
			
			if (multiplicacaoDigitos >= 10){
				
				somaDigitos = somaDigitos + 1 + (multiplicacaoDigitos-10) ;
				
			} else {
				
				somaDigitos = somaDigitos + multiplicacaoDigitos ;
				
			}
				
			if (multiplicador == 1)
				multiplicador = 2;
			else
				multiplicador = 1;
			
		}
		
		for (int i = 0; i < carteira.length(); i++){
			
			char[] carteiraArray = carteira.toCharArray();
			
			int digito = Integer.parseInt("" + carteiraArray[i]);
			int multiplicacaoDigitos = digito * multiplicador;
			
			if (multiplicacaoDigitos >= 10){
				
				somaDigitos = somaDigitos + 1 + (multiplicacaoDigitos-10) ;
				
			} else {
				
				somaDigitos = somaDigitos + multiplicacaoDigitos ;
				
			}
				
			if (multiplicador == 1)
				multiplicador = 2;
			else
				multiplicador = 1;
			
		}
		
		for (int i = 0; i < nossoNumero.length(); i++){
			
			char[] nossoNumeroArray = nossoNumero.toCharArray();
			
			int digito = Integer.parseInt("" + nossoNumeroArray[i]);
			int multiplicacaoDigitos = digito * multiplicador;
			
			if (multiplicacaoDigitos >= 10){
				
				somaDigitos = somaDigitos + 1 + (multiplicacaoDigitos-10) ;
				
			} else {
				
				somaDigitos = somaDigitos + multiplicacaoDigitos ;
				
			}
				
			if (multiplicador == 1)
				multiplicador = 2;
			else
				multiplicador = 1;
			
		}
		
		
		dac = 10 - (somaDigitos%10);
		
		System.out.println(dac);
		
		return dac;		
		
	}
	
private UnidadeFederativa retornaEstado (String sigla){
		
		if (sigla.equals("AC"))
			return UnidadeFederativa.AC;
		
		if (sigla.equals("AL"))
			return UnidadeFederativa.AL;
		
		if (sigla.equals("AP"))
			return UnidadeFederativa.AP;
		
		if (sigla.equals("AM"))
			return UnidadeFederativa.AM;
		
		if (sigla.equals("BA"))
			return UnidadeFederativa.BA;
		
		if (sigla.equals("CE"))
			return UnidadeFederativa.CE;
		
		if (sigla.equals("DF"))
			return UnidadeFederativa.DF;
		
		if (sigla.equals("ES"))
			return UnidadeFederativa.ES;
		
		if (sigla.equals("GO"))
			return UnidadeFederativa.GO;
		
		if (sigla.equals("MA"))
			return UnidadeFederativa.MA;
		
		if (sigla.equals("MT"))
			return UnidadeFederativa.MT;
		
		if (sigla.equals("MS"))
			return UnidadeFederativa.MS;
		
		if (sigla.equals("MG"))
			return UnidadeFederativa.MG;
		
		if (sigla.equals("PA"))
			return UnidadeFederativa.PA;
		
		if (sigla.equals("PB"))
			return UnidadeFederativa.PB;
		
		if (sigla.equals("PR"))
			return UnidadeFederativa.PR;
		
		if (sigla.equals("PE"))
			return UnidadeFederativa.PE;
		
		if (sigla.equals("PI"))
			return UnidadeFederativa.PI;
		
		if (sigla.equals("RJ"))
			return UnidadeFederativa.RJ;
		
		if (sigla.equals("RN"))
			return UnidadeFederativa.RN;
		
		if (sigla.equals("RS"))
			return UnidadeFederativa.RS;
		
		if (sigla.equals("RO"))
			return UnidadeFederativa.RO;
		
		if (sigla.equals("RR"))
			return UnidadeFederativa.RR;
		
		if (sigla.equals("SC"))
			return UnidadeFederativa.SC;
		
		if (sigla.equals("SP"))
			return UnidadeFederativa.SP;
		
		if (sigla.equals("SE"))
			return UnidadeFederativa.SE;
		
		else
			return UnidadeFederativa.TO;
		
		
	}
	
	/**
	 * Método que lista as empresas para gerar a nota fiscal gf com o
	 * número sequencial da empresa escolhida
	 * @author Ishida, Márcio André
	 * @return <String>
	 */
	public String confirmarEmpresa(){
		try{
			if (request.getParameter("iIdOrdemServico")==null || request.getParameter("iIdOrdemServico").equals("")){
				throw new KscException("Informe uma ordem de serviço");
			}
			
			INotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();			
			ArrayList listaEmpresas =  notaFiscalGFService.confirmarEmpresa();
			
			context.put("iIdOrdemServico", request.getParameter("iIdOrdemServico"));
			context.put("listaEmpresaSeguranca", listaEmpresas);
			
			return "/notaFiscalGF/CViewEscolhaEmpresa.vm";
		}catch(KscException ke)
		{
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			return "erro.vm";	
		}
		
	}
	
	
	/**
	 * Método que lista as empresas para gerar a nota fiscal gf com o
	 * número sequencial da empresa escolhida
	 * @author Ishida, Márcio André
	 * @return <String>
	 */
	public String confirmarEmpresaAgrupamento(){
		try{
			if (request.getParameter("iListaOrdemServico")==null || request.getParameter("iListaOrdemServico").equals("")){
				throw new KscException("Informe uma ordem de serviço");
			}
			
			INotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();			
			ArrayList listaEmpresas =  notaFiscalGFService.confirmarEmpresa();
			
			context.put("iListaOrdemServico", request.getParameter("iListaOrdemServico"));
			context.put("listaEmpresaSeguranca", listaEmpresas);
			
			return "/notaFiscalGF/CViewEscolhaEmpresaAgrupamento.vm";
		}catch(KscException ke)
		{
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			return "erro.vm";	
		}
		
	}	

	/**
	 * Método que chama a tela para a impressao do sintegra
	 * @author Ishida, Márcio André
	 * @return <String>
	 */
	public String impressaoSintegra(){
		try {
			INotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();
			ArrayList listaEmpresas =  notaFiscalGFService.confirmarEmpresa();
			context.put("listaEmpresaSeguranca", listaEmpresas);
			
			return "/notaFiscalGF/CViewFormSintegra.vm";
		} catch (KscException ke) {
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			return "erro.vm";	
		}			

	}	

	public String imprimirSintegra() throws KscException{
		Connection con = null;
		 int totalItem =0;
		 int totalServico =0;
		 double totalNota = 0.0;
		
		try{
			if (request.getParameter("idEmpresa")==null || request.getParameter("idEmpresa").equals("")){
				throw new KscException("Informe uma empresa");
			}				
			
			con = KscConnection.getInstance();			
			Usuario user = (Usuario)request.getSession().getAttribute("usuario");
			
			NotaFiscalGF nfCriterio = new NotaFiscalGF();
			//nfCriterio.setIdEmpresa(user.getIdEmpresa());
			
			
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
			String dataFormatada = formatter.format(new Date());
			String arquivo = "nota_fiscal_"+dataFormatada+".nf";
			String url ="/notas/";
			
			File file = new File(KscConstante.DIRETORIO_PROJETO + url);
			
			if(!file.exists()){
				file.mkdir();				
			}

			File f = new File(file + file.separator + arquivo);
			
			SimpleDateFormat formataDataIni = new SimpleDateFormat("dd/MM/yyyy");
			
			Date dataInicial = formataDataIni.parse(request.getParameter("iDataInicial"));
			Date dataFinal = formataDataIni.parse(request.getParameter("iDataFinal"));
			
			FileWriter fw = new FileWriter(f);
			BufferedWriter bfw = new BufferedWriter(fw);
			INotaFiscalGFService nfs = new NotaFiscalGFService();
			ICidadeService cidadeService = new CidadeService();
			IEmpresaService es  = new EmpresaService();
			Empresa empresaCriterio = new Empresa();
			Empresa empresa = new Empresa();
			
			ParametrosFaturamento criterioParametros = new ParametrosFaturamento();
			ParametrosFaturamentoService parametrosFaturamentoService = new ParametrosFaturamentoService();
			ParametrosFaturamento parametrosFaturamento = null;
			criterioParametros.setIdEmpresa(new KscString(request.getParameter("idEmpresa")));
			
			try{
				parametrosFaturamento = (ParametrosFaturamento) parametrosFaturamentoService.consultarParametrosFaturamentoCompleto(criterioParametros, "",con).get(1);
						
			}
			catch(Exception e){
			throw new KscException("Parametros de Faturamento não foram encontrados");}
			
			IItemNotaFiscalGFService infService = new ItemNotaFiscalGFService();
			ItemNotaFiscalGF item =null;
			ItemNotaFiscalGF criterioItem =null;
			
		
			
			empresaCriterio.setIdEmpresa(user.getIdEmpresa());
			
			ArrayList listaEmpresa = es.consultarEmpresaCompleto(empresaCriterio,"", con);
			if(listaEmpresa.size()>1)
				empresa = (Empresa)listaEmpresa.get(1);
			else
				throw new KscException("Empresa não encontrada");
			
			
			//nfCriterio.setEntradaSaida(new KscInteger(0));
			ArrayList listaNF = nfs.consultarNotaFiscalGFCompleto(new KscDate(request.getParameter("iDataInicial")),new KscDate(request.getParameter("iDataFinal")),nfCriterio,"order by NotaFiscalGF.dataEmissao, NotaFiscalGF.numero",null);
			
			//TotalTipo[]tts = {new TotalTipo(50,listaNF.size()-1),new TotalTipo(99,listaNF.size()-1+3)};
			
			
			EnderecoEmpresa ee= new EnderecoEmpresa();
			ee.setIdEmpresa(empresa.getIdEmpresa());
			ee.setFaturamento(new KscBoolean(true));
			ArrayList alEE = new EnderecoEmpresaService().consultarEnderecoEmpresaCompleto(ee, "",con);
			if(alEE.size()!=2)
				throw new KscException("Endreço da empresa não pode ser encontrada");
			else
				ee=(EnderecoEmpresa)alEE.get(1);
			
			
			String cep = trataString( ee.getCep().getValue());
						
			String cnpj = trataString(empresa.getCnpjCpf().getValue());		
	
			String tipo=request.getParameter("iTipo");
			
			//new Tipo10(10,"03963294000145","7300555602","KSC BRASIL TECHNOLOGIES LTDA","CURITIBA","PR",new Long("4130260688").longValue(),new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2007"),new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2007"),'3','3','1').print(bfw);
			new Tipo10(10,cnpj,empresa.getInscricaoEstadualRg().getValue(),empresa.getRazaoSocial().getValue(),ee.getCidade().getDescricao().getValue(),ee.getEstado().getSigla().getValue(),new Long("4130723190").longValue(),dataInicial,dataFinal,'3','3',tipo.charAt(0)).print(bfw);
						
			bfw.newLine();			
			
			//new Tipo11(11,"BR 116",4590,"","BAIRRO ALTO",82650500,"FABIO KISNER",new Long("4130260688").longValue()).print(bfw);
			
			new Tipo11(11,ee.getEndereco().getValue(),1523,"",ee.getBairro().getValue(),Integer.parseInt(cep),"CARLA",new Long("5137513081").longValue()).print(bfw);
			
	
			String ie="";
			String estado="";
			String cnpjCliente = "";
			char situacao ='N' ;
	        NotaFiscalGF nf = new NotaFiscalGF();
	        
	       
			for (int i=1; i < listaNF.size();i++){   
				nf = (NotaFiscalGF) listaNF.get(i);
		     	bfw.newLine();
		    
		     	//ie = nf.getCliente().getInscricaoEstadualRg().getWebValue();
		     	cnpjCliente = trataString(nf.getCliente().getCnpjCpf().getValue());
		     	if(cnpjCliente.length() < 14){
		     		 ie ="ISENTO";
		     	}else{
			     	if(nf.getCliente().getInscricaoEstadualRg().isDbNull())
		     		    ie ="ISENTO";
			     	else
			     		ie = trataString(nf.getCliente().getInscricaoEstadualRg().getValue());
	
		     	}
		     	
		     	EnderecoCliente ec= new EnderecoCliente();
				ec.setIdCliente(nf.getCliente().getIdCliente());
				ec.setFaturamento(new KscBoolean(true));
				ArrayList alEc = new EnderecoClienteService().consultarEnderecoClienteCompleto(ec, "",con);
				if(alEc.size()!=2)
					throw new KscException("Endreço do cliente "+nf.getCliente().getNomeCompletoRazaoSocial().getWebValue()+"não pode ser encontrada");
				else
					ec=(EnderecoCliente)alEc.get(1);
				
				Cidade cidade = new Cidade();
				cidade.setIdCidade(ec.getIdCidade());
				
				ArrayList listaCidade = cidadeService.consultarCidadeCompleto(cidade,"",con);
				if(alEc.size()!=2)
					throw new KscException("Cidade do endreço do cliente "+nf.getCliente().getNomeCompletoRazaoSocial().getWebValue()+"não pode ser encontrada");
				else
					cidade=(Cidade)listaCidade.get(1);
		     	
		     	
		     	estado = cidade.getEstado().getSigla().getValue();
		     	if(estado == null || estado.equalsIgnoreCase(""))
		     		estado ="RS";
		     	
		     	
		     	if(cnpjCliente.length() < 14)
		     		while(cnpjCliente.length() < 14)
		     		    cnpjCliente ="0"+cnpjCliente ;
		     		    
		     	
		     	if( nf.getCancelada().booleanValue())
		     		situacao='S';
		     	else
		     		situacao='N';
		     		
		     	
		     	CFOP cfop = new CFOP();
		     	cfop.setIdCFOP(new KscString("591_NULL_15"));
		     	
		     	CFOPService cfopService = new CFOPService();
		     	ArrayList listaCfop = cfopService.consultarCFOP(cfop, "", con);
		     	
		     	for(int m = 1 ; m  < listaCfop.size(); m++){
		     		cfop = (CFOP) listaCfop.get(m);
		     	}
		     	
		     	nf.setCFOP(cfop);
		     	
		     	new Tipo50(50,cnpjCliente,ie,nf.getDataEmissao().getValue(),estado, 01,parametrosFaturamento.getSerieNFe().getWebValue(), Long.parseLong(nf.getNumero().getWebValue().replace(".", "").replace(",", ".")),Integer.parseInt(nf.getCFOP().getCodigo().getViewValue()),"P",nf.getValorTotalNota().doubleValue(),0.0,0.0,0.00,0.0,0.00,situacao).print(bfw);
		        
		        totalNota = totalNota + nf.getValorTotalNota().doubleValue();
		        
		       
			}
				
			
			NotaFiscalGF notaFiscal = new NotaFiscalGF();
			for (int i=1; i < listaNF.size();i++){   
				notaFiscal = (NotaFiscalGF) listaNF.get(i);
				
				cnpjCliente = trataString(notaFiscal.getCliente().getCnpjCpf().getValue());
			
				if(cnpjCliente.length() < 14)
		     		while(cnpjCliente.length() < 14)
		     		    cnpjCliente ="0"+cnpjCliente ;
				
			    item  = new ItemNotaFiscalGF();    
			    criterioItem = new ItemNotaFiscalGF();
			    criterioItem.setIdNotaFiscalGF(notaFiscal.getIdNotaFiscalGF());
				
				ArrayList listaItemNF = infService.consultarItemNotaFiscalGFCompleto(criterioItem,"", con);
				for(int k=1; k < listaItemNF.size(); k++){
					item = (ItemNotaFiscalGF)listaItemNF.get(k);
				
				    bfw.newLine();
				    double valor_icms = 0.0;
				    double valorTotal = item.getValorUnitario().doubleValue()*item.getQuantidade().doubleValue();

				    new Tipo54(54,cnpjCliente,01,parametrosFaturamento.getSerieNFe().getWebValue(),Integer.parseInt(notaFiscal.getNumero().getViewValue()),Integer.parseInt(notaFiscal.getCFOP().getCodigo().getViewValue()),"051",k,item.getProduto().getIdProduto().getWebValue(),item.getQuantidade().doubleValue(),item.getValorUnitario().doubleValue(),0.00,valorTotal,0.00,0.00,valor_icms,0.00).print(bfw);
				}

				for(int k=1; k < listaItemNF.size(); k++){
					item = (ItemNotaFiscalGF)listaItemNF.get(k);
				    bfw.newLine();
				    new Tipo75(75,dataInicial,dataFinal,item.getProduto().getIdProduto().getValue(),"",item.getProduto().getProduto().getValue(),"UN",0.00,0.0,0.00,0.00).print(bfw);
				    
					totalItem++;
				}
				
			}
					
			int total54_75 = totalItem + totalServico;
			TotalTipo[]tts = {new TotalTipo(50,listaNF.size()-1), new TotalTipo(54, total54_75), new TotalTipo(75,total54_75),new TotalTipo(99,(listaNF.size()-1+3)+total54_75 *2)};
			
			bfw.newLine();
			
			new Tipo90(90,cnpj,empresa.getInscricaoEstadualRg().getValue(),tts,1).print(bfw);
	
			
			
			StringBuffer sb = new StringBuffer("");
			byte b[] = {13,10,26};
			bfw.write(new String(b));
			
			bfw.close();
			
			INotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();
			ArrayList listaEmpresas =  notaFiscalGFService.confirmarEmpresa();
			context.put("listaEmpresaSeguranca", listaEmpresas);
			
			System.out.println(totalNota);
			String strTotalNota = String.valueOf(totalNota);
			
			context.put("arquivo",arquivo);
			context.put("totalNota",strTotalNota);
			return "/notaFiscalGF/CViewFormSintegra.vm";
			
		}catch (KscException ke) {
			System.out.println(ke.getMessage());			
			context.put("erro",ke.getMessage());
			return "erro.vm";
		}
		catch(IOException ioe)
		{
			
			context.put("erro",ioe.getMessage());
			return "erro.vm";
			
		}
		catch (Exception e) {
			
			System.out.println(e.getMessage());			
			context.put("erro",e.getMessage());
			return "erro.vm";
		}
	}



	private String trataString(String texto){
		String [] textos = new String[1];
		texto = texto.replaceAll("/","");
		texto = texto.replaceAll("-","");			
		texto = texto.replaceAll("\\.","");
		texto = texto.replaceAll("_","");
		
		texto = texto.trim();
		
		return texto;
	}

	public final String imprimirNotaFiscalGF(){
		Connection con=null;
		try{
			con=KscConnection.getInstance();
			if(request.getParameter("iIdNotaFiscalGF")==null || request.getParameter("iIdNotaFiscalGF").equals(""))
				throw new KscException("Informe uam nosta fiscal");
			
			KscString idImpressora =null;
			if(request.getParameter("iIdImpressora")!=null){
				if(!request.getParameter("iIdImpressora").equals("")){
					idImpressora= new KscString(request.getParameter("iIdImpressora"));
				}else
					throw new KscException("ID da impressora é inválido!");
			}else
				throw new KscException("ID da impressora é inválido!");			
			
			
			NotaFiscalGF nf = new NotaFiscalGF();
			nf.setIdNotaFiscalGF(new KscString(request.getParameter("iIdNotaFiscalGF")));
			ArrayList al = new NotaFiscalGFService().consultarNotaFiscalGFCompleto(nf, "",con);
			
			if(al.size()!=2)
				throw new KscException("Nota fiscal não pode ser identificada");
			else
				nf=(NotaFiscalGF)al.get(1);
			
			OrdemServico os = new OrdemServico();
			os.setIdOrdemServico(nf.getIdOrdemServico());
			ArrayList alOs = new OrdemServicoService().consultarOrdemServicoCompleto(os, "",con);
			if(alOs.size()!=2)
				throw new KscException("Não foi possivel identificar a Os para gerar a nota");
			else
				os=(OrdemServico)alOs.get(1);
			
			
			ItemNotaFiscalGF inf = new ItemNotaFiscalGF();
			inf.setIdNotaFiscalGF(nf.getIdNotaFiscalGF());
			ArrayList alInf = new ItemNotaFiscalGFService().consultarItemNotaFiscalGFCompleto(inf, "",con);
			
			
			ArrayList alItensNotas = new ArrayList();
			
			for(int i=1;i<alInf.size();i++){
				inf=(ItemNotaFiscalGF)alInf.get(i);
				double total = inf.getValorUnitario().doubleValue()*inf.getQuantidade().doubleValue();
				alItensNotas.add(new ItemNotaGFImpressao(inf.getDescricaoImpressao().getWebValue(),inf.getQuantidade().getWebValue(),false,"UN",new KscDouble(total).getWebValue(),inf.getValorUnitario().getWebValue()));
				
			}
			
			//servicos
			ServicoNotaFiscalGF snf = new ServicoNotaFiscalGF();
			snf.setIdNotaFiscalGF(nf.getIdNotaFiscalGF());
			ArrayList alSnf = new ServicoNotaFiscalGFService().consultarServicoNotaFiscalGFCompleto(snf, "",con);
			for (int i = 1; i < alSnf.size(); i++) {
				snf=(ServicoNotaFiscalGF)alSnf.get(i);
				double total = snf.getValorUnitario().doubleValue()*snf.getQuantidade().doubleValue();
				alItensNotas.add(new ItemNotaGFImpressao(snf.getServicosTerceirizados().getServico().getWebValue(),snf.getQuantidade().getWebValue(),false,"UN",new KscDouble(total).getWebValue(),snf.getValorUnitario().getWebValue()));
			}
			int cont=0;
			ArrayList alFinal = new ArrayList();
			Vector vetorArrays= new Vector();
			
			for(int i=0;i<alItensNotas.size();i++){
				alFinal.add(alItensNotas.get(i));
				cont++;
				if(cont==13){
					vetorArrays.add(alFinal);
					cont=0;
					alFinal = new ArrayList();
				}
				
			}
			vetorArrays.add(alFinal);
			
			if(vetorArrays.size()>1 && request.getParameter("iPagina")==null){
				context.put("array", vetorArrays);
				context.put("notaFiscal", nf);
				return "/notaFiscalGF/CViewFormPaginasNotaFiscalGF.vm";
			}
			
			response.setContentType("text/plain"); 
			PrintStream ps = new PrintStream(response.getOutputStream(),false);
			
			
			if(request.getParameter("iPagina")==null){
				for(int k=0;k<vetorArrays.size();k++){
					ArrayList aux = (ArrayList)vetorArrays.get(k);					
					if(aux.size()>0){
						ItemNotaGFImpressao ingfi[]=(ItemNotaGFImpressao[])aux.toArray(new ItemNotaGFImpressao[aux.size()]);
						imprimirNotaFiscalGF(idImpressora,k+1,vetorArrays.size(),k==0,k==vetorArrays.size()-1,nf,os,ingfi,ps,con); 
					}
				}
			}else{
				KscInteger pagina  = new KscInteger(request.getParameter("iPagina"));
				ArrayList aux = (ArrayList)vetorArrays.get(pagina.intValue()-1);
				if(aux.size()>0){
					ItemNotaGFImpressao ingfi[]=(ItemNotaGFImpressao[])aux.toArray(new ItemNotaGFImpressao[aux.size()]);
					imprimirNotaFiscalGF(idImpressora,pagina.intValue(),vetorArrays.size(),pagina.intValue()-1==0,pagina.intValue()==vetorArrays.size(),nf,os,ingfi,ps,con); 
				}
			}
			
			
			ps.close();
			
			ps.flush();
						
			return "erro.vm";
		}catch(IOException ke)
		{
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			context.put("detalhe", ke.getStackTrace());
			return "erro.vm";	
		}catch(KscException ke)
		{
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			context.put("detalhe", ke.getStackTrace());
			return "erro.vm";	
		}finally{
			try{
				if(con!=null)
					con.close();
			}catch(SQLException ke)
			{
				System.err.print(ke.getMessage());
				context.put("erro", ke.getMessage());
				context.put("detalhe", ke.getStackTrace());
				return "erro.vm";	
			}
		}
	}
	
	/**
	 * Imprime a nota fiscal em um arquivo .nf
	 * @param pagina
	 * @param totalpagina
	 * @param imprimeParcela
	 * @param imprimeValorFinal
	 * @param nf
	 * @param os
	 * @param ingf
	 * @param ps
	 * @param con
	 * @throws KscException
	 */
	public final void imprimirNotaFiscalGF(KscString idImpressora,int pagina, int totalpagina,boolean imprimeParcela,boolean imprimeValorFinal,NotaFiscalGF nf,OrdemServico os,ItemNotaGFImpressao ingf[],PrintStream ps, Connection con)throws KscException{
		
		try{
			ArrayList listaData = new ArrayList();
			HashMap hashParcelas = new HashMap();
			if(imprimeParcela){				
				IParcelasService ips = new ParcelasService();
				Parcelas pa = new Parcelas();
				pa.setIdOrdemServico(nf.getIdOrdemServico());
				ArrayList alp = ips.consultarParcelasCompleto(pa,"order by Parcelas.dataVencimento",con);
				for(int k=1;k<alp.size();k++){
					Parcelas aux = (Parcelas)alp.get(k);
					if(hashParcelas.containsKey(aux.getDataVencimento().getWebValue())){
						Parcelas aux2 = (Parcelas)hashParcelas.get(aux.getDataVencimento().getWebValue());
						aux2.setValor(new KscDouble(aux.getValor().doubleValue()+aux2.getValor().doubleValue()));
						hashParcelas.put(aux.getDataVencimento().getWebValue(),aux2);
					}else{
						hashParcelas.put(aux.getDataVencimento().getWebValue(),aux);
						listaData.add(aux.getDataVencimento().getValue());
					}
					
				}
			
				if(listaData.size() > 0 )
						Collections.sort(listaData);
			}
			//consulta as parcelas
			/*Iterator it = hashParcelas.values().iterator();
			while(it.hasNext()){
				listaParcelas.add(it.next());
			}*/
			
			EnderecoCliente ec= new EnderecoCliente();
			ec.setIdCliente(nf.getCliente().getIdCliente());
			ec.setFaturamento(new KscBoolean(true));
			ArrayList alEc = new EnderecoClienteService().consultarEnderecoClienteCompleto(ec, "",con);
			if(alEc.size()!=2)
				throw new KscException("Endreço do cliente "+nf.getCliente().getNomeCompletoRazaoSocial().getWebValue()+"não pode ser encontrada");
			else
				ec=(EnderecoCliente)alEc.get(1);
			
			Cidade cidade = new Cidade();
			cidade.setIdCidade(ec.getIdCidade());
			
			ArrayList listaCidade = new CidadeService().consultarCidadeCompleto(cidade,"",con);
			if(alEc.size()!=2)
				throw new KscException("Cidade do endreço do cliente "+nf.getCliente().getNomeCompletoRazaoSocial().getWebValue()+"não pode ser encontrada");
			else
				cidade=(Cidade)listaCidade.get(1);

			

			PrinterMatrix printer= new PrinterMatrix(); 
			
			//KSCPrinterMatrix printer= new KSCPrinterMatrix(); 
			//Define o tamanho do papel/tela para impressão, aqui 25 linhas e 80 colunas
			printer.setOutSize( 67, 139 ); 
			
			// Data da emissao da nota
			printer.printTextWrap(6,6, 61, 73, nf.getDataEmissao().getWebValue());
			
			// Numero da nota fical
			printer.printTextWrap(11,11, 20, 34, nf.getNumero().getWebValue());
			
			// Valor Total da nota
			printer.printTextWrap(11,11, 36, 47, nf.getValorTotalNota().getWebValue());
			
			// Número da duplicata
			printer.printTextWrap( 11,11, 48, 60, nf.getNumero().getWebValue());
			
			// Vencimento
			printer.printTextWrap( 11,11, 61, 73, "");
			
			// Condições especiais
			printer.printTextWrap( 13,13, 13, 73, "");
			
			// Desconto
			printer.printTextWrap( 14,14, 18, 33, "");
			
			// Até
			printer.printTextWrap( 14,14, 37, 73, "");				
			
			
			// Cliente
			if(nf.getCliente().getNomeCompletoRazaoSocial().getWebValue().length()>48)
				printer.printTextWrap( 16,16, 13, 70, nf.getCliente().getNomeCompletoRazaoSocial().getWebValue().substring(0,48));
			else				
				printer.printTextWrap( 16,16, 13, 70, nf.getCliente().getNomeCompletoRazaoSocial().getWebValue());
			
			// Bairro
			String bairro = "";
			if(ec.getBairro().getWebValue().length()>20)
				bairro = ec.getBairro().getWebValue().substring(0,19);
			else
				bairro =  ec.getBairro().getWebValue();
			
			// Endereço cliente
			String endereco = ec.getEndereco().getWebValue()+" "+ ec.getNumero().getWebValue() +" "+ec.getComplemento().getWebValue() + " - " + bairro + " " + ec.getCep().getWebValue();

			if(endereco.length()>44)
				printer.printTextWrap( 17,17, 13, 70, endereco.substring(0,43));
			else
				printer.printTextWrap( 17,17, 13, 70, endereco);
			
			//Municipio
			printer.printTextWrap( 18,18, 13, 47, ec.getCidade().getDescricao().getWebValue());
			// Estado
			printer.printTextWrap( 18,18, 63, 67, cidade.getEstado().getSigla().getWebValue());
			
			//Praça de pagamento
			printer.printTextWrap( 19,19, 13, 57, ec.getCidade().getDescricao().getWebValue());
			
			//CNPJ ou CPF
			printer.printTextWrap( 20,20, 13, 37, nf.getCliente().getCnpjCpf().getWebValue());
			//Inscricao Esradual
			printer.printTextWrap( 20,20, 48, 67, nf.getCliente().getInscricaoEstadualRg().getWebValue());
			
			// Escreve o valor por extenso
			Extenso ex = new Extenso(nf.getValorTotalNota().doubleValue());
			printer.printTextWrap( 22,23, 13, 70, ex.toString().toUpperCase());
			
			//Numero da ordem de servico
			printer.printTextWrap( 23,24, 75, 90, nf.getIdOrdemServico().getWebValue());
			
			// MOTIVO
			printer.printTextWrap( 27,27, 5, 64, "");
			
			// Imprime a descricao dos serviços e o seu valor unitario
			int linha = 29;
			for (int i = 0; i < ingf.length; i++) {
				String desc[] = ingf[i].getDescricao().split("/N");
				for (int j = 0; j < desc.length; j++) {
					printer.printTextWrap(linha,linha,5,80, desc[j]);
					linha = linha + 1;
				}
				int lin = linha - 1;
				printer.printTextWrap(lin,lin, 83, 93, printer.alinharADireita(10,ingf[i].getValorUnitario()));
			}
			
			// Imprime a observação
			String obs[]= nf.getObservacao().getWebValue().split("/N");
			linha = 50;
			for (int i = 0; i < obs.length || i>8; i++) {
				printer.printTextWrap(linha,linha,5,80, obs[i]);
				linha = linha + 1;
			}
			
			// Valor total da nota
			printer.printTextWrap( 57,57, 83, 93, nf.getValorTotalNota().getWebValue());
			// Numero da nota
			printer.printTextWrap( 64,64, 83, 93, nf.getNumero().getWebValue());
			
			printer.toPrintStream(ps);		

			//manda pra impressora
			
			Impressora imp = new Impressora();
			imp.setIdImpressora(idImpressora);
			
			byte[] comandosEsc = {27,'!',4};
			
			ArrayList al = new ImpressoraService().consultarImpressora(imp,"");
			if(al.size()!=2)
				throw new KscException("Não foi possivel encontrar a impressora!");
			else{
				imp = (Impressora)al.get(1);
				//printer.toPrinter(imp.getCaminho().getViewValue(),comandosEsc);
				printer.toPrinterServer("10.1.1.10", 9100);
				//printer.show();
			}			
			
			
		}catch(KscException ke)
		{
			throw new KscException(ke.getMessage());
			
		}
	}
	
	public String lerArquivo(String arquivo) throws IOException
	{
		FileReader in = new FileReader("C:" + File.separator +"arquivosXML" + File.separator + arquivo);
		BufferedReader br = new BufferedReader(in);
		String xml ="";
		while(br.ready())
		{
			xml += br.readLine();
			//System.out.println(xml);
		}
		return xml;
	}
	
	public void trataMensagemRetorno(List listaMensagemRetorno,LoteRpsDinamo loteRps, String metodo) throws KscException{
		
		MensagemLoteService mls = new MensagemLoteService();
		
		//if(metodo.equals("RecepcionarLoteRps"))
			//listaMensagemRetorno = (List) listaMensagemRetorno.get(1);
		
		for(int a=0;a<listaMensagemRetorno.size();a++){
			
			//com.kscbrasil.notaFiscalGF.model.ListaMensagemRetorno retornoM = (com.kscbrasil.notaFiscalGF.model.ListaMensagemRetorno) listaMensagemRetorno.get(a);
			//com.kscbrasil.notaFiscalGF.model.ListaMensagemRetorno retornoM = respostaEnvio.getListaMensagemRetorno().get(a);
		
			com.kscbrasil.notaFiscalGF.model.MensagemRetorno retornoM = (com.kscbrasil.notaFiscalGF.model.MensagemRetorno) listaMensagemRetorno.get(a);
			
			MensagemLote mensagemLote = new MensagemLote();
			mensagemLote.setIdLoteRpsDinamo(loteRps.getIdLoteRpsDinamo());
			mensagemLote.setMensagem(new KscString(retornoM.getMensagem()));
			if(retornoM.getCorrecao()!=null)
				mensagemLote.setCorrecao(new KscString(retornoM.getCorrecao()));
			else
				mensagemLote.setCorrecao(new KscString());
		
			if(retornoM.getCodigo()!=null)
				mensagemLote.setCodigo(new KscString(retornoM.getCodigo()));
			else
				mensagemLote.setCodigo(new KscString());
		
			mensagemLote.setMetodo(new KscString(metodo));
			mensagemLote.setDataHoraMensagem(new KscDateTime(new Date()));
		
			mls.incluirMensagemLote(mensagemLote);
			
		}
	}	
		
	public final String reenviarNotaFiscalGF() throws Exception{
		
		KscConstante.carregarConstante();
		String view = "";
		Connection con = null;
		
		LoteRpsDinamoService lrds = new LoteRpsDinamoService();
		
		try {

			try {
				con = KscConnection.getInstance();
				con.setAutoCommit(false);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new KscException(sqle);
			}	
	
			KscString idNotaFiscal = new KscString(request.getParameter("iIdNotaFiscalGF"));
			KscString idLoteRpsDinamo = new KscString(request.getParameter("iIdLoteRpsDinamo"));
			
			LoteRpsDinamo lote = new LoteRpsDinamo();
			lote.setIdLoteRps(idLoteRpsDinamo);
			
			Usuario user = (Usuario)request.getSession().getAttribute("usuario");
			
			NotaFiscalGF criterioNota = new NotaFiscalGF();
			criterioNota.setIdNotaFiscalGF(idNotaFiscal);
			NotaFiscalGFService nfgs = new NotaFiscalGFService();
			NotaFiscalGF nota = (NotaFiscalGF)nfgs.consultarNotaFiscalGF(criterioNota, "",con).get(1);
			
			Notaxlote criterionotaxlote = new Notaxlote();
			criterionotaxlote.setIdNotaFiscalGF(nota.getIdNotaFiscalGF());
			NotaxloteService nxls = new NotaxloteService();
			Notaxlote notaxlote = (Notaxlote)nxls.consultarNotaxlote(criterionotaxlote, "",con).get(1);
			
			ParametrosFaturamento criterioParametros = new ParametrosFaturamento();
			ParametrosFaturamentoService parametrosFaturamentoService = new ParametrosFaturamentoService();
			ParametrosFaturamento parametrosFaturamento = null;
			criterioParametros.setIdEmpresa(nota.getIdEmpresa());
			
			try{
				parametrosFaturamento = (ParametrosFaturamento) parametrosFaturamentoService.consultarParametrosFaturamentoCompleto(criterioParametros, "",con).get(1);			
			}catch(Exception e){
				throw new KscException("Parametros de Faturamento não foram encontrados");
			}
			
			LoteRpsDinamo loteCriterio = new LoteRpsDinamo();
			loteCriterio.setIdLoteRps(notaxlote.getIdLoteRpsDinamo());
			loteCriterio.setIdEmpresa(nota.getIdEmpresa());
			lote = (LoteRpsDinamo) lrds.consultarLoteRps(loteCriterio, "", con).get(1);
			
			enviarNotaFiscalGF(lote,parametrosFaturamento,con);
			
			System.out.println(idNotaFiscal);

			con.commit();
			con.close();
			con=KscConnection.getInstance();
						
			return consultarNotaFiscalGF();			
			
		}

		catch (KscException ke) {
			//context.put("notaFiscal", notaFiscal);
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			return "erro.vm";
		}
		catch (Exception ke) {
			ke.printStackTrace();
			return "erro.vm";
		}

		finally {
			try {
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new KscException(ex);
			}
		}
	}
	
public final String cancelarNotaFiscalGF() throws Exception{
		
		KscConstante.carregarConstante();
		String view = "";
		Connection con = null;
		
		NotaxloteService lrds = new NotaxloteService();
		
		try {

			try {
				con = KscConnection.getInstance();
				con.setAutoCommit(false);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new KscException(sqle);
			}	
	
			KscString idNotaFiscal = new KscString(request.getParameter("iIdNotaFiscalGF"));
			KscString idLoteRpsDinamo = new KscString(request.getParameter("iIdLoteRpsDinamo"));
			
			Empresa criterioEmpresa = new Empresa();
			Empresa empresa = new Empresa();
			EmpresaService es = new EmpresaService();
			
			Notaxlote notaxlote = new Notaxlote();
			Notaxlote criterioNotaxlote = new Notaxlote();
			NotaFiscalGF criterioNota = new NotaFiscalGF();
			
			EnderecoCliente enderecoCliente = new EnderecoCliente();
			EnderecoCliente criterioEndereco = new EnderecoCliente();
			
			Cidade cidade = new Cidade();
			Cidade criterioCidade = new Cidade();
			CidadeService cs = new CidadeService();
			EnderecoClienteService ecs = new EnderecoClienteService();
			
			NotaxOrdem criterioNotaxordem = new NotaxOrdem();
			NotaxOrdem notaxOrdem = new NotaxOrdem();
			NotaxOrdemService nxos = new NotaxOrdemService();
			
			OrdemServico ordem = new OrdemServico();
			OrdemServico criterioOrdem = new OrdemServico();
			OrdemServicoPersistence osp = new OrdemServicoPersistence();
			
			LancamentoContaReceber criterioLancamento = new LancamentoContaReceber();
			LancamentoContaReceber lancamento = new LancamentoContaReceber();
			LancamentoContaReceberService lcrs = new LancamentoContaReceberService();
			
			LoteRpsDinamoService lrdss = new LoteRpsDinamoService();
			
			Usuario user = (Usuario)request.getSession().getAttribute("usuario");
			
			NotaFiscalGF notaCancelada = new NotaFiscalGF();
			notaCancelada.setCancelada(new KscBoolean(true));
			
			criterioNotaxlote.setIdNotaFiscalGF(idNotaFiscal);
			
			Parcelas parcela = new Parcelas();
			IParcelasService parcelasService = new ParcelasService();
			ArrayList listaParcelas = new ArrayList();
			
			parcela.setIdNotaFiscalGF(idNotaFiscal);
			
			listaParcelas = parcelasService.consultarParcelas(parcela, "", con);
			
			for (int i = 1; i < listaParcelas.size(); i++){
				
				parcela = (Parcelas)listaParcelas.get(i);
				
				Parcelas criterioParcela = new Parcelas();
				
				criterioParcela.setIdParcelas(parcela.getIdParcelas());
				
				parcela.setIdNotaFiscalGF(new KscString());
				
				parcelasService.alterarParcelas(parcela, criterioParcela, con);
				
			}
			
			ArrayList listaNotaxLote = lrds.consultarNotaxlote(criterioNotaxlote, "", con);
			if(listaNotaxLote.size()>1){
			  for(int i=1;i<listaNotaxLote.size();i++){
				  
				  notaxlote = (Notaxlote)listaNotaxLote.get(1);
				  
				  if(!notaxlote.getNotaFiscalGF().getIdEmpresa().getValue().equals(new KscConstante().propriedadesSistema.getProperty("ID_EMPRESA_EXP"))
						  && !notaxlote.getNotaFiscalGF().getIdEmpresa().getValue().equals(new KscConstante().propriedadesSistema.getProperty("ID_EMPRESA_WSP"))){
					  criterioEmpresa.setIdEmpresa(notaxlote.getNotaFiscalGF().getIdEmpresa());
					  empresa = (Empresa)es.consultarEmpresa(criterioEmpresa, "",con).get(1);
			
					  com.kscbrasil.notaFiscalGF.model.IdentificacaoNfse identificacao = new com.kscbrasil.notaFiscalGF.model.IdentificacaoNfse();
					  identificacao.setNumero(notaxlote.getNotaFiscalGF().getNumeroNfse().getWebValue());
					  identificacao.setCnpj(empresa.getCnpjCpf().getWebValue().replace(".", "").replace("-", "").replace("/", ""));
					  identificacao.setInscricaoMunicipal(empresa.getInscricaoMunicipal().getWebValue().replace(".", "").replace("-", "").replace("/", ""));
			
					  String codigoMunicipio="";
					  String codigoEstado="";
				  
					  if(notaxlote.getNotaFiscalGF().getIdCidade()!=null){
						  criterioCidade.setIdCidade(notaxlote.getNotaFiscalGF().getIdCidade());
						  cidade = (Cidade)cs.consultarCidadeCompleto(criterioCidade, "", con).get(1); 
						  codigoEstado = cidade.getEstado().getCodigo().getWebValue();
						  codigoMunicipio = cidade.getEstado().getCodigo().getWebValue();
					  }else{    
						  criterioEndereco.setEnderecoNfe(new KscBoolean(true));
						  criterioEndereco.setIdCliente(notaxlote.getNotaFiscalGF().getIdCliente());
						  ArrayList listaEndereco = ecs.consultarEnderecoClienteCompleto(criterioEndereco, "", con);
						  enderecoCliente = (EnderecoCliente)listaEndereco.get(1);
						  codigoEstado = enderecoCliente.getCidade().getEstado().getCodigo().getWebValue();
						  codigoMunicipio = enderecoCliente.getCidade().getCodigo().getWebValue();
					  }
				  
					  while (codigoMunicipio.length()<5){
						  //codigoMunicipio = enderecoCliente.getCidade().getCodigo().getWebValue();
						  codigoMunicipio = "0".concat(codigoMunicipio); 
					  }	
				
					  codigoMunicipio=codigoEstado+codigoMunicipio;
			
					  //identificacao.setCodigoMunicipio("4106902");
					  identificacao.setCodigoMunicipio(codigoMunicipio);
			
					  com.kscbrasil.notaFiscalGF.model.InfPedidoCancelamento infPedido = new com.kscbrasil.notaFiscalGF.model.InfPedidoCancelamento();
					  infPedido.setIdentificacaoNfse(identificacao);
					  infPedido.setCodigoCancelamento("2");
					  infPedido.setId(1);
			
					  com.kscbrasil.notaFiscalGF.model.Pedido pedido = new com.kscbrasil.notaFiscalGF.model.Pedido();
					  pedido.setInfPedidoCancelamento(infPedido);
			
					  com.kscbrasil.notaFiscalGF.model.CancelarNfseEnvio cancelar = new com.kscbrasil.notaFiscalGF.model.CancelarNfseEnvio();
					  cancelar.setPedido(pedido);
			
					  ParametrosFaturamento criterioParametros = new ParametrosFaturamento();
					  ParametrosFaturamentoService parametrosFaturamentoService = new ParametrosFaturamentoService();
					  ParametrosFaturamento parametrosFaturamento = null;
					  criterioParametros.setIdEmpresa(notaxlote.getNotaFiscalGF().getIdEmpresa());
			
					  try{
						  parametrosFaturamento = (ParametrosFaturamento) parametrosFaturamentoService.consultarParametrosFaturamentoCompleto(criterioParametros, "",con).get(1);			
					  }catch(Exception e){
						  throw new KscException("Parametros de Faturamento não foram encontrados");
					  }
			
					  XStream xStream = new XStream(new DomDriver());
					  xStream.alias("CancelarNfseEnvio", com.kscbrasil.notaFiscalGF.model.CancelarNfseEnvio.class);
					  xStream.processAnnotations(com.kscbrasil.notaFiscalGF.model.CancelarNfseEnvio.class);
					  String arquivo = xStream.toXML(cancelar);
			
					  String nomeArquivo = "Nfse"+notaxlote.getNotaFiscalGF().getNumeroNfse().getWebValue()+"Cancelado.xml";
					  File f = new File(KscConstante.DIRETORIO_PROJETO.concat("/RPSCANCELADA/"+nomeArquivo));   
					  FileWriter writer = new FileWriter(f); 
					  PrintWriter saida = new PrintWriter(writer);  
					  saida.write(arquivo);
					  //System.out.println(arquivo);
					  saida.close();   
					  writer.close();
			
					  AssinarNfe assinarNfe = new AssinarNfe();
					  String arquivoAssinado = assinarNfe.assinar(nomeArquivo , KscConstante.DIRETORIO_PROJETO.concat("/RPSCANCELADA/"), parametrosFaturamento,"Pedido");	
		
					  System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
					  System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");   
					  Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());   
				    
					  String certificado = parametrosFaturamento.getArquivo();
					  String pwd = null;
					  char[] senha = null;
					  CriptografiaHexadecimal criptSenha = new CriptografiaHexadecimal();
					  if(parametrosFaturamento.getSenhaCertificado()!=null){
						  pwd = criptSenha.cript(parametrosFaturamento.getSenhaCertificado().getWebValue(),"1");
						  senha = pwd.toCharArray();
					  }	
		
					  SSLClientAxisEngineConfig axisConfig = getAxisConfig(certificado, new String(senha));
			
					  //Classe gerada pelo wsdl da prefeitura
					  Nfse servicoEnv = new NfseLocator(axisConfig);	
					  NfseSoap evai = null;
					  try 
					  {
						  evai = servicoEnv.getnfseSoap();
					  } catch (ServiceException e1) {
						  // TODO Auto-generated catch block
						  e1.printStackTrace();
					  }		

					  System.out.println(arquivoAssinado);
					  String retorno = evai.recepcionarXml("CancelarNfse", arquivoAssinado);
					  System.out.println(retorno);
			
					  XStream x = new XStream();
					  Class [] classes2 = {com.kscbrasil.notaFiscalGF.model.CancelarNfseResposta.class,com.kscbrasil.notaFiscalGF.model.ListaMensagemRetorno.class,com.kscbrasil.notaFiscalGF.model.MensagemRetorno.class};
					  x.processAnnotations(classes2);
			
					  com.kscbrasil.notaFiscalGF.model.CancelarNfseResposta respostaCancelamento = (com.kscbrasil.notaFiscalGF.model.CancelarNfseResposta) x.fromXML(retorno);
		    
					  trataMensagemRetorno(respostaCancelamento.getListaMensagemRetorno(),notaxlote.getLoteRpsDinamo(), "CancelarNfse");
	
					  String[] desmenbraDataCancelamento = respostaCancelamento.getCancelamento().getConfirmacao().getDataHoraCancelamento().split("T");
					  String data = desmenbraDataCancelamento[0];
					  String formataData = data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0,4);
					  String resto = desmenbraDataCancelamento[1];
					  String hora = resto.substring(0, 8);
					  String dataCancelada = formataData+" "+hora;
			
					  notaCancelada.setDataCancelamento(new KscDateTime(dataCancelada));
				  }else{
					  notaCancelada.setDataCancelamento(new KscDateTime());
				  }
				  
				 NotaFiscalGFPersistence nfgp = new NotaFiscalGFPersistence();
				 criterioNota.setIdNotaFiscalGF(notaxlote.getIdNotaFiscalGF());
				 nfgp.alterarNotaFiscalGF(notaCancelada, criterioNota, con);
			
				 criterioNotaxordem.setIdNotaFiscalGF(notaxlote.getIdNotaFiscalGF());
				 ArrayList listaNotaxordem = nxos.consultarNotaxOrdem(criterioNotaxordem, "", con);
				 if(listaNotaxordem.size()>1){
					 
					 for(int j=1;j<listaNotaxordem.size();j++){
					 
						 notaxOrdem = (NotaxOrdem)listaNotaxordem.get(j);
						 criterioOrdem = new OrdemServico();
						 criterioOrdem.setIdOrdemServico(notaxOrdem.getIdOrdemServico());
						 
						 //osp.alterarStatusOrdemServico(criterioOrdem, IStatusOrdemServicoService.AGUARDANDO_APROVAÇÃO_FATURAMENTO, con, "Nota Fiscal Cancelada", user.getIdUsuario());
						 OrdemServico ordemAlt = new OrdemServico();
						 ordemAlt.setIdStatusOrdemServicoFinanceiro(new KscString(IStatusOrdemServicoService.AGUARDANDO_APROVAÇÃO_FATURAMENTO));
						 osp.alterarOrdemServico(ordemAlt, criterioOrdem, con, user.getIdUsuario());
						 
						 if(request.getParameter("cancelaLancamento")!=null){
							 criterioLancamento = new LancamentoContaReceber();
							 criterioLancamento.setNumeroDocumento(new KscString(notaxOrdem.getOrdemServico().getCodigo().getWebValue()));
							 ArrayList listaLancamentos = lcrs.consultarLancamentoContaReceber(criterioLancamento, "",con);
							 if(listaLancamentos.size()>1){
								 for(int a=1;a<listaLancamentos.size();a++){
									 lancamento = (LancamentoContaReceber)listaLancamentos.get(a);
									 criterioLancamento.setIdLancamentoContaReceber(lancamento.getIdLancamentoContaReceber());
									 lcrs.excluirLancamentoContaReceber(criterioLancamento,con);
								 }
							 }
						 }	
						 LoteRpsDinamo lote = new LoteRpsDinamo();
						 
						 if (!KscConstante.propriedadesSistema.containsKey("STATUSLOTE_CANCELADO"))
							 throw new KscException("Verifique se a propriedade STATUSLOTE_CANCELADO está cadastrada");
						 
						 lote.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_CANCELADO")));
						 lote.setNumero(notaxlote.getLoteRpsDinamo().getNumero());
						 lote.setIdEmpresa(notaxlote.getNotaFiscalGF().getIdEmpresa());
						 LoteRpsDinamo criAtu = new LoteRpsDinamo();
					     criAtu.setIdLoteRps(notaxlote.getIdLoteRpsDinamo());
					     lrdss.alterarLoteRps(lote, criAtu,con);
					 }	 
					 
				 }	 
				 con.commit();
				 con.close();
				// con=KscConnection.getInstance();
			  } 
			}			
			return consultarNotaFiscalGF();			
			
		}

		catch (KscException ke) {
			//context.put("notaFiscal", notaFiscal);
			System.err.print(ke.getMessage());
			context.put("erro", ke.getMessage());
			return "erro.vm";
		}
		catch (Exception ke) {
			ke.printStackTrace();
			context.put("erro", ke.getMessage());
			return "erro.vm";
		}

		finally {
			try {
			} catch (Exception ex) {
				ex.printStackTrace();
				context.put("erro", ex.getMessage());
				throw new KscException(ex);
			}
		}
	}

public final String cancelarNotaFiscalGFDinamo() throws Exception{
	
	KscConstante.carregarConstante();
	String view = "";
	Connection con = null;
	
	NotaxloteService lrds = new NotaxloteService();
	
	try {

		try {
			con = KscConnection.getInstance();
			con.setAutoCommit(false);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new KscException(sqle);
		}	

		KscString idNotaFiscal = new KscString(request.getParameter("iIdNotaFiscalGF"));
		KscString idLoteRpsDinamo = new KscString(request.getParameter("iIdLoteRpsDinamo"));
		
		Notaxlote notaxlote = new Notaxlote();
		Notaxlote criterioNotaxlote = new Notaxlote();
		NotaFiscalGF criterioNota = new NotaFiscalGF();
		
		LoteRpsDinamoService lrdss = new LoteRpsDinamoService();
		
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		
		criterioNotaxlote.setIdNotaFiscalGF(idNotaFiscal);
		
		Parcelas parcela = new Parcelas();
		IParcelasService parcelasService = new ParcelasService();
		ArrayList listaParcelas = new ArrayList();
		
		parcela.setIdNotaFiscalGF(idNotaFiscal);
		
		listaParcelas = parcelasService.consultarParcelas(parcela, "", con);
		
		for (int i = 1; i < listaParcelas.size(); i++){
			
			parcela = (Parcelas)listaParcelas.get(i);
			
			Parcelas criterioParcela = new Parcelas();
			
			criterioParcela.setIdParcelas(parcela.getIdParcelas());
			
			parcela.setIdNotaFiscalGF(new KscString());
			
			parcelasService.alterarParcelas(parcela, criterioParcela, con);
			
		}
		NotaFiscalGF notaCancelada = new NotaFiscalGF();
		
		notaCancelada.setDataCancelamento(new KscDateTime(new Date()));
		notaCancelada.setCancelada(new KscBoolean(true));

		NotaFiscalGFPersistence nfgp = new NotaFiscalGFPersistence();
		criterioNota.setIdNotaFiscalGF(idNotaFiscal);
		nfgp.alterarNotaFiscalGF(notaCancelada, criterioNota, con);

		
		ArrayList listaNotaxLote = lrds.consultarNotaxlote(criterioNotaxlote, "", con);
		
		if (listaNotaxLote.size() > 1) {

				notaxlote = (Notaxlote) listaNotaxLote.get(1);
				LoteRpsDinamo lote = new LoteRpsDinamo();

				if (!KscConstante.propriedadesSistema
						.containsKey("STATUSLOTE_CANCELADO_DINAMO"))
					throw new KscException(
							"Verifique se a propriedade STATUSLOTE_CANCELADO_DINAMO está cadastrada");

				lote.setIdStatusLote(new KscString(
						new KscConstante().propriedadesSistema
								.getProperty("STATUSLOTE_CANCELADO_DINAMO")));
				lote.setNumero(notaxlote.getLoteRpsDinamo().getNumero());
				lote.setIdEmpresa(notaxlote.getNotaFiscalGF().getIdEmpresa());
				LoteRpsDinamo criAtu = new LoteRpsDinamo();
				criAtu.setIdLoteRps(notaxlote.getIdLoteRpsDinamo());
				lrdss.alterarLoteRps(lote, criAtu, con);
			}	 
				 
			  
		 con.commit();
		 con.close();
			// con=KscConnection.getInstance();
		  
				
		return consultarNotaFiscalGF();	

		
	}

	catch (KscException ke) {
		//context.put("notaFiscal", notaFiscal);
		System.err.print(ke.getMessage());
		context.put("erro", ke.getMessage());
		return "erro.vm";
	}
	catch (Exception ke) {
		ke.printStackTrace();
		context.put("erro", ke.getMessage());
		return "erro.vm";
	}

	finally {
		try {
		} catch (Exception ex) {
			ex.printStackTrace();
			context.put("erro", ex.getMessage());
			throw new KscException(ex);
		}
	}
}
	
	public void enviarNotaFiscalGF(LoteRpsDinamo loteRps, ParametrosFaturamento parametrosFaturamento, Connection con) throws Exception{
		
		LoteRpsDinamoService lrds = new LoteRpsDinamoService();
		NotaFiscalGFService notaFiscalGFService = new NotaFiscalGFService();
		//Processo de populacao dos objetos conforme layout para geracao do arquivo xml
		System.out.println("Antes do prepara objetos");
		LoteRps lote = notaFiscalGFService.preparaObjetosXml(con, loteRps);
		loteRps.setQuantidadeRps(new KscInteger(lote.getQuantidadeRps()));
		//Geracao do xml com base nos obejtos sem assinatura
		System.out.println("Antes do gerarXml");
		String arquivoNAssinado = notaFiscalGFService.geraXmlNota(lote);
		
		System.out.println("Antes do Assinar");
		System.out.println("Antes da Instancia");
		//Processo de assinatura das tag InfRps
		AssinarNfe assinarNfe = null;
		try{
			
			assinarNfe = new AssinarNfe();
		
		}catch(Exception e){
			System.out.println(e);
		}
		
		System.out.println("Depois da instancia do Assinar");
		System.out.println("Diterrio"+KscConstante.DIRETORIO_PROJETO.concat("/RPS/"));
		String arquivo = assinarNfe.assinar(arquivoNAssinado , KscConstante.DIRETORIO_PROJETO.concat("/RPS/"), parametrosFaturamento,"Rps");	
		
		System.out.println("Antes de Assinar Lote");
		//Processo de assinatura da tag principal LoteRps
		arquivo = assinarNfe.assinarEnvioLoteRps(arquivoNAssinado, KscConstante.DIRETORIO_PROJETO.concat("/RPS/"),parametrosFaturamento);
		File xmlAssinado = new File(KscConstante.DIRETORIO_PROJETO.concat("/RPS/loteAssinado.xml"));
		File xsdValidacao = new File(KscConstante.DIRETORIO_PROJETO.concat("/RPS/nfse.xsd"));
		
		
		
		
		//Propriedades para comunicacao com webservice
		System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");   
	    Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());   
	    
	    //Certificado chave publica/privada
//	    System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");   
	    
	    String pwd = null;
		char[] senha = null;
	    String certificado = parametrosFaturamento.getArquivo();
	    CriptografiaHexadecimal criptSenha = new CriptografiaHexadecimal();
	    if(parametrosFaturamento.getSenhaCertificado()!=null){
			pwd = criptSenha.cript(parametrosFaturamento.getSenhaCertificado().getWebValue(),"1");
			senha = pwd.toCharArray();
		}	
	    
	//    System.out.println(KscConstante.DIRETORIO_PROJETO + "/certificados/cert_serv.keystore");
	    
//	    System.getProperties().remove("javax.net.ssl.keyStore");
//	    System.getProperties().remove("javax.net.ssl.keyStorePassword");
//	    
//	    System.setProperty("javax.net.ssl.keyStore",KscConstante.DIRETORIO_PROJETO + "/certificados/"+certificado);//Aqui vem o arquivo do certificado do seu cliente
//	    System.setProperty("javax.net.ssl.keyStorePassword", new String(senha));//Aqui a senha deste certificado   
//	
//	    System.out.println("Antes do PFX");
//	    //Certificado chave public keystore
//	    System.setProperty("javax.net.ssl.trustStoreType", "JKS");      
//        System.setProperty("javax.net.ssl.trustStore", KscConstante.DIRETORIO_PROJETO + "/certificados/cert_serv.keystore");//caminho do arquivo criado pelo o InstallCert no caso o jssecacerts         
//	    System.setProperty("javax.net.ssl.trustStorePassword", "formato9");                   
	
	    SSLClientAxisEngineConfig axisConfig = getAxisConfig(certificado, new String(senha));
	    
	    //Classe gerada pelo wsdl da prefeitura
	    Nfse servicoEnv = new NfseLocator(axisConfig);	
		NfseSoap evai = null;
		try 
		{
			evai = servicoEnv.getnfseSoap();
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
			
		String retorno = null;
		ListaMensagemRetorno listaMensagem = new ListaMensagemRetorno();
		
		try 
		{
			FileReader flr = new FileReader(xmlAssinado);   
			BufferedReader br = new BufferedReader(flr);  
			String conteudo = "";
			while (br.ready()) {                
				conteudo = conteudo + br.readLine();                          
			}
		
			boolean validouXml = true;
			//Metodo que valida o xml antes de enviar
			
		/*	MensagemLoteService mls = new MensagemLoteService();
			
			System.out.println("Antes de validar no ws");
			listaMensagem = evai.validarXml(conteudo);
			
			String codigo="";
			Boolean validouXml = true;
			TcMensagemRetorno retornoMensagem[] = listaMensagem.getMensagemRetorno();
			for(int i=0; i <retornoMensagem.length;i++){
				TcMensagemRetorno mensagem = retornoMensagem[i];
				
				//codigo = mensagem.getCodigo();
			
				MensagemLote mensagemLote = new MensagemLote();
				mensagemLote.setIdLoteRpsDinamo(loteRps.getIdLoteRpsDinamo());
				mensagemLote.setMensagem(new KscString(mensagem.getMensagem()));
				if(mensagem.getCorrecao()!=null)
					mensagemLote.setCorrecao(new KscString(mensagem.getCorrecao()));
				else
					mensagemLote.setCorrecao(new KscString());
				
				
				if(mensagem.getCodigo()!=null){
					mensagemLote.setCodigo(new KscString(mensagem.getCodigo()));
					codigo = mensagem.getCodigo();
				}else{
					mensagemLote.setCodigo(new KscString());
				}
				mensagemLote.setMetodo(new KscString("ValidarXml"));
				mensagemLote.setDataHoraMensagem(new KscDateTime(new Date()));
				
				mls.incluirMensagemLote(mensagemLote);
				
				if(!codigo.equals("I1"))
					validouXml = false;
				
				System.out.println("Mensagem:"+mensagem.getMensagem());
				System.out.println("Correcao:"+mensagem.getCorrecao());
				System.out.println("Codigo"+mensagem.getCodigo());
				
				
				
			}
			*/
			if(validouXml){
				System.out.println("Antes do recepcionar");
				retorno = evai.recepcionarXml("RecepcionarLoteRps", arquivo);
				
				XStream x = new XStream();
				
				Class [] classes = {com.kscbrasil.notaFiscalGF.model.EnviarLoteRpsResposta.class,com.kscbrasil.notaFiscalGF.model.ListaMensagemRetorno.class,com.kscbrasil.notaFiscalGF.model.MensagemRetorno.class};
				x.processAnnotations(classes);
				com.kscbrasil.notaFiscalGF.model.EnviarLoteRpsResposta respostaEnvio = (com.kscbrasil.notaFiscalGF.model.EnviarLoteRpsResposta) x.fromXML(retorno);
				System.out.println("foi");
				
				System.out.println(respostaEnvio);
		        
				if(respostaEnvio.getListaMensagemRetorno()!=null){
					trataMensagemRetorno(respostaEnvio.getListaMensagemRetorno(),loteRps, "RecepcionarLoteRps");
					loteRps.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_PROCESSADO_COM_ERRO")));
				}else{
					String[] desmenbraData = respostaEnvio.getDataRecebimento().split("T");
					String data = desmenbraData[0];
					String formataData = data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0,4);
					String resto= desmenbraData[1];
					String hora = resto.substring(0, 8);
					String dataRecebimento = formataData+" "+hora;
					loteRps.setDataRecebimento(new KscDateTime(dataRecebimento));
					loteRps.setProtocolo(new KscString(respostaEnvio.getProtocolo()));
					loteRps.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_ENVIADO")));
					LoteRpsDinamo criterioLote = new LoteRpsDinamo();
					criterioLote.setIdLoteRps(loteRps.getIdLoteRpsDinamo());
					lrds.alterarLoteRps(loteRps, criterioLote,con);
				}
				
			}else{
				LoteRpsDinamo criterioLote = new LoteRpsDinamo();
				criterioLote.setIdLoteRps(loteRps.getIdLoteRpsDinamo());
				criterioLote.setIdEmpresa(new KscString(request.getParameter("iIdEmpresa")));
				loteRps.setIdStatusLote(new KscString(new KscConstante().propriedadesSistema.getProperty("STATUSLOTE_NAO_VALIDADO")));
				lrds.alterarLoteRps(loteRps, criterioLote,con);
			}
			
			//notaFiscalGFService.fechaAbreLote(loteRps, con);
			
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private SSLClientAxisEngineConfig getAxisConfig(String certificado,String senha) throws KscException
	{
//	    
//	    System.setProperty("javax.net.ssl.keyStore",KscConstante.DIRETORIO_PROJETO + "/certificados/"+certificado);//Aqui vem o arquivo do certificado do seu cliente
//	    System.setProperty("javax.net.ssl.keyStorePassword", new String(senha));//Aqui a senha deste certificado   
//	
//	    System.out.println("Antes do PFX");
//	    //Certificado chave public keystore
//	    System.setProperty("javax.net.ssl.trustStoreType", "JKS");      
//        System.setProperty("javax.net.ssl.trustStore", KscConstante.DIRETORIO_PROJETO + "/certificados/cert_serv.keystore");//caminho do arquivo criado pelo o InstallCert no caso o jssecacerts         
//	    System.setProperty("javax.net.ssl.trustStorePassword", "formato9");    
		
		SSLClientAxisEngineConfig axisConfig = new SSLClientAxisEngineConfig();

		axisConfig.setProtocol("TLS");
		axisConfig.setAlgorithm("SunX509");
		axisConfig.setKeyStoreType("PKCS12");
		
	/*	File fa = new File(KscConstante.DIRETORIO_PROJETO + "/certificados/"+certificado);
		if(!fa.exists()){
			throw new KscException("Nao existe cert serv");
		}
		*/
		axisConfig.setKeyStore(KscConstante.DIRETORIO_PROJETO + "/certificados/"+certificado);
		axisConfig.setKeyStorePassword(senha);
		
	/*	File f = new File(KscConstante.DIRETORIO_PROJETO + "/certificados/cert_serv.keystore");
		if(!f.exists()){
			throw new KscException("Nao existe cert serv");
		}
		*/	
		//axisConfig.setTrustStore(KscConstante.DIRETORIO_PROJETO + "/certificados/cert_serv.keystore");
		axisConfig.setTrustStore(KscConstante.DIRETORIO_PROJETO + "/certificados/nfse2012.keystore");
		axisConfig.setTrustStoreType("JKS");
		axisConfig.setTrustStorePassword("formato9");

		System.out.println("Fim das propriedades");
		//System.out.println("AXIS"+axisConfig);
		return axisConfig;
	}
	
	private byte[] baixarNotaFiscal(String endereco){
        try {   
            URL url = new URL(endereco);   
            
            URLConnection urlC = url.openConnection();
            int tamanho = urlC.getContentLength();   
            if(tamanho > 0){
	            byte[] bytes = new byte[tamanho];   
	            
	            InputStream is = urlC.getInputStream();  
	            
	            int offset = 0;   
	            int numRead = 0;   
	            while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {   
	                offset += numRead;   
	            }   
	
	            if (offset < bytes.length) {   
	                System.out.println("Não foi possivel ler o arquivo");   
	            }  
	            
	            return bytes;
            }else
            	return null;
           
        }catch (MalformedURLException e){   
            System.out.println(e);
            return null;
        }catch (IOException e){   
            System.out.println(e);
            return null;
        }	
	}
		
}


