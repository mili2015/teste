package geral;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;


public class BoletoTeste
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		JBoletoBean jBoletoBean = new JBoletoBean();
        
		jBoletoBean.setDataDocumento("04/06/2010");//DT EMISSAO CT
        jBoletoBean.setDataProcessamento("15/06/2010");      //DATA ATUAL
            
        jBoletoBean.setCedente("MILI S/A");  

        jBoletoBean.setNomeSacado("AIRTON");
        jBoletoBean.setEnderecoSacado("Rua EUCLIDES 951");        
        jBoletoBean.setBairroSacado("SAO SEBASTIAO");
        jBoletoBean.setCidadeSacado("SAO JOSE DOS PINAIS");
        jBoletoBean.setUfSacado("PR");
        jBoletoBean.setCepSacado("83075288");
        jBoletoBean.setCpfSacado("08714112000116");
        
        
        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO ITAU");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO ITAU");                
        //informacao boleto
        Vector descricoes = new Vector();
        descricoes.add("Protestar após 4 dias úteis do vencimento");
        descricoes.add("Juros por dia de atraso:   R$  0,43");
        descricoes.add("REF. NF Nº 141648   Não receber valor inferior ao registrado nesse documento");
        jBoletoBean.setDescricoes(descricoes);
        
        jBoletoBean.setInstrucao1("Protestar após 4 dias úteis do vencimento");
        jBoletoBean.setInstrucao2("Juros por dia de atraso:   R$  0,43");
        jBoletoBean.setInstrucao3("REF. NF Nº 141648   Não receber valor inferior ao registrado nesse documento");

        jBoletoBean.setCarteira("109");
        
      //entradas_cr
        jBoletoBean.setDataVencimento("24/06/2010");
        
//        inf. boleto - conta
        jBoletoBean.setAgencia("3306-5");
        jBoletoBean.setContaCorrente("00023436");
        jBoletoBean.setDvContaCorrente("2");
        
        jBoletoBean.setNumConvenio("123456");
        jBoletoBean.setNossoNumero("16142020000040840");
        jBoletoBean.setNoDocumento("1948588");
        jBoletoBean.setValorBoleto("264.85");             
//        jBoletoBean.setCodigoBarras("00193464000001081300000001614202000004084017");
//        jBoletoBean.setLinhaDigitavel("00190.00009.01614.202008.00040.840175.3.46400000108130");
        //código do cliente fornecido pelo hsbc
//        jBoletoBean.setCodCliente("1122334");
        
        Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BANCO_DO_BRASIL);
        JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BANCO_DO_BRASIL);
//
        jBoleto.addBoleto();
        jBoleto.closeBoleto("boleto1.pdf");
        
        File arq=new File("boleto1.pdf");
        try
        {
        	byte[] b = fileToByte(arq);
//        	System.out.println(b.length);
        }
        catch (Exception e)
        {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
        
//        PDFGenerator generator = new PDFGenerator(jBoletoBean, JBoleto.ITAU);
//        
//        Banco banco  = new Itau(jBoletoBean);
////        
//        jBoletoBean.setCodigoBarras(banco.getCodigoBarras());
//        jBoletoBean.setLinhaDigitavel(banco.getLinhaDigitavel());
////        
//        generator.addBoleto(jBoletoBean,banco);
//        
//        byte[] b = generator.closeBoleto();
//        
//        int i;
//        while(i = b.)
//        
//        System.out.println(b.length);
        
//        JBoleto jBoleto = new JBoleto(generator,jBoletoBean,JBoleto.ITAU);
//        jBoleto.addBoleto();
//        jBoleto.closeBoleto("itau2.pdf");	
		
		/*JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento("05/07/2009");
        jBoletoBean.setDataProcessamento("05/07/2009");

        jBoletoBean.setCedente("Notícias Geek");

        jBoletoBean.setNomeSacado("Fabio Souza");
        jBoletoBean.setEnderecoSacado("Rua Geek 010101");
        jBoletoBean.setBairroSacado("Freguesia");
        jBoletoBean.setCidadeSacado("Rio de Janeiro");
        jBoletoBean.setUfSacado("RJ");
        jBoletoBean.setCepSacado("22750-000");
        jBoletoBean.setCpfSacado("00000000000");

        //jBoletoBean.setImagemMarketing("original_template_logo.gif");

        jBoletoBean.setDataVencimento("10/08/2009");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setCarteira("175");
        jBoletoBean.setAgencia("1131");
        jBoletoBean.setContaCorrente("00002");
        jBoletoBean.setDvContaCorrente("68");

        jBoletoBean.setNossoNumero("7708536477",11);
        jBoletoBean.setNoDocumento("34556");
        jBoletoBean.setValorBoleto("300.00");

        Generator generator = new PDFGenerator(jBoletoBean, JBoleto.HSBC);
        JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.HSBC);

        jBoleto.addBoleto();
        jBoleto.closeBoleto("hsbc.pdf");*/

//        File arq=new File(s);
//        byte[] b = fileToByte(arq);
	}
	
	public static byte[] fileToByte(File arquivo) throws Exception
	{
		System.out.println(arquivo.getPath());
		FileInputStream fis = new FileInputStream(arquivo);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int bytesRead = 0;
		while ((bytesRead = fis.read(buffer, 0, 8192)) != -1)
		{
			baos.write(buffer, 0, bytesRead);
		}
		return baos.toByteArray();
	}

}
