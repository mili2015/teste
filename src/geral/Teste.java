package geral;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.brazilutils.br.uf.UF;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import br.com.caelum.stella.MessageProducer;
import br.com.caelum.stella.ResourceBundleMessageProducer;
import br.com.caelum.stella.type.Estado;
import model.Pessoa;




/**
 * @author felipe
 *
 */
public class Teste extends ParseXml
{
	private String data ="data";
	private Date dataForm;
	
	private boolean passou=false;

	{
		Calendar cHj = Calendar.getInstance();
		cHj.add(Calendar.DAY_OF_MONTH, 2);
		System.out.println("bloco anonimo");
	}
	
	private Teste()
	{
		super();
		System.out.println("private Teste()");
	}

	
	public Date getDataForm()
	{
		
		return dataForm;
	}

	public void setDataForm(Date dataForm)
	{
		this.dataForm = dataForm;
	}

	public String getData()
	{
		return data;
	}


	public void setData(String data)
	{
		this.data = data;
	}

	public java.sql.Date getDataFormatada()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");

		java.sql.Date date=null;
		try
		{
			date = new java.sql.Date(format.parse(data).getTime());
		}
		catch (ParseException e)
		{
			System.out.println("Erro br.com.sigma.modelo.SolicitacaoManutencao getDataFormatada(): "+e);
		}

		return date;
	}

	public void setDataFormatada(java.sql.Date data)	
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");

		this.data = sdf.format(data);
		this.dataForm = data;
		System.out.println("setDataFormatada: "+sdf.format(data));
	}

	public static java.sql.Date converterStringToDate(String dataStr) throws Exception 
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(dataStr!=null && dataStr.trim().length()>0)
			return new java.sql.Date(df.parse(dataStr).getTime());
		else
			return null;
	}
	
	public static DateTime converterStringToJodaDate(String dataStr) throws Exception 
	{
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime dt = formatter.parseDateTime(dataStr);
		return dt;
	}
	
	public static String converterJodaTimeToString(DateTime dateJoda)
	{
		return dateJoda.toString(DateTimeFormat.forPattern("dd/MM/yyyy"));
	}
	
	public static LocalDate converterStringToJodaLocalDate(String dataStr) 
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		LocalDate dt = formatter.parseLocalDate(dataStr);
		return dt;
	}
	
	public static String converterJodaLocalDateToString(LocalDate dateJoda)
	{
		return dateJoda.toString(DateTimeFormat.forPattern("dd/MM/yyyy"));
	}
	
	
	public String getDataSolicitacao(String dataSolicitacao) 
	{
		String retorno=dataSolicitacao;
		try
		{
			Calendar cal = Calendar.getInstance();   
			DateFormat df = new SimpleDateFormat ("dd-MMM-yyyy");   

			java.util.Date hoje = new java.util.Date();
			Calendar calHoje = Calendar.getInstance();
			calHoje.setTime(hoje);

			java.util.Date dtSolic=df.parse (dataSolicitacao);
			cal.setTime (dtSolic); 
			cal.add (Calendar.DAY_OF_MONTH, +1); 

			System.out.println("HOJE: "+calHoje.getTime()+" ontem: "+cal.getTime());

			String data = df.format(cal.getTime());

			System.out.println("data: "+data);

			//se depois
			if(calHoje.after(cal))
			{
				retorno= "<font color=red>"+dataSolicitacao+"</font>";
			}
			else
			{
				System.out.println("dt "+dataSolicitacao);
			}
		}
		catch(ParseException e){}   
		return retorno;
	}

	public static String decodificarPass(String passwd) {
		passwd.toUpperCase();

		String sTextoCrip = "";
		String sChave1 = "8G6A79B5EQFKH01JNLMDCO3RPZWSIT4VXUY2 ";
		String sChave2 = " 0123456789ABCDEFGHIJKLMNOPQRSTUVXWYZ";
		int nTam;
		int nPos = 0;
		int nIndice;
		nTam = passwd.length();
		while (nPos < nTam) {
			nIndice = sChave1.indexOf(passwd.toUpperCase().substring(nPos, nPos + 1));
			sTextoCrip = sTextoCrip + sChave2.substring(nIndice, nIndice + 1);
			nPos++;
		}

		return sTextoCrip.toUpperCase();
	}

	public static String codificarPass(String passwd) {

		passwd.toUpperCase();
		String sTextoCrip = "";
		String sChave2 = "8G6A79B5EQFKH01JNLMDCO3RPZWSIT4VXUY2 _%";
		String sChave1 = " 0123456789ABCDEFGHIJKLMNOPQRSTUVXWYZ_%";
		int nTam;
		int nPos = 0;
		int nIndice;
		nTam = passwd.length();
		while (nPos < nTam) 
		{
			nIndice = sChave1.indexOf(passwd.toUpperCase().substring(nPos,nPos + 1));
			sTextoCrip = sTextoCrip + sChave2.substring(nIndice, nIndice + 1);
			// System.out.println(sTextoCrip);
			nPos++;
		}
		return sTextoCrip.toUpperCase();
	}
	
	public static Document obtemDocumentString(String xml)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//dbf.setValidating(true);//para validar DTD/Schema do xml
		dbf.setNamespaceAware(true);
		//parse o doc xml usando o DOM
		DocumentBuilder db;
		Document document = null;
		try
		{
			db = dbf.newDocumentBuilder();
			document = db.parse(new InputSource(new StringReader(xml)));
		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	public static Document obtemDocument(String caminhoXML)
	{
		File xmlFile = new File(caminhoXML); //onde src � uma string
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//dbf.setValidating(true);//para validar DTD/Schema do xml
		dbf.setNamespaceAware(true);
		//parse o doc xml usando o DOM
		DocumentBuilder db;
		Document document = null;
		try
		{
			db = dbf.newDocumentBuilder();
			document = db.parse(xmlFile);
		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Erro obtemDocument(String). Arquivo n�o encontrado. "+e);
		}
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * M�todo que transforma um Document para String
	 * @param doc
	 * @return
	 */
	public static String convert(Document doc,Element ele)
	{
		String xml = "";
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try
		{
			OutputFormat outputformat = new OutputFormat();
			// outputformat.setIndent(4);
			outputformat.setIndenting(false);
			// outputformat.setPreserveSpace(false);
			XMLSerializer serializer = new XMLSerializer();
			serializer.setOutputFormat(outputformat);
			serializer.setOutputByteStream(stream);
			// serializer.asDOMSerializer();
			if(doc!=null)
				serializer.serialize(doc.getDocumentElement());
			else
				serializer.serialize(ele);
			xml = stream.toString();
		}
		catch (Exception except)
		{
			except.getMessage();
		}
		return xml != null ? xml.replaceAll("\n", "") : xml;
	}
	
	@SuppressWarnings("unused")
	private String lerArquivo(String caminho)
	{
		File f = new File(caminho);
		String xml = "";
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(f));
			System.out.println("Lendo arquivo...");
			while (in.ready())
			{
				xml += in.readLine();
			}
			in.close();
		}
		catch (Exception e)
		{
			System.out.println("Erro lerXmlGerado: " + e);
		}
		return xml;
	}
	
	
	
	private static String lerArquivoBanco(String caminho)
	{
		File f = new File(caminho);
		String linha = "";
		String value = "";
		StringBuilder texto = new StringBuilder();
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(f));
			System.out.println("Lendo arquivo...");
			while (in.ready())
			{
				linha = in.readLine() +"\n";
				
				if(linha != null && linha.contains("TRNAMT"))
				{
					value = extrairTagXml(linha, "<TRNAMT>");
					if(value != null && !value.isEmpty())
					{
						double dValue = Double.parseDouble(value);
						//dValue = Math.ceil(dValue * 10);
						dValue = (dValue * 10);
						
						linha = "<TRNAMT>"+dValue+"</TRNAMT>";
					}
				}
				
				texto.append(linha);
			}
			in.close();
		}
		catch (Exception e)
		{
			System.out.println("Erro lerXmlGerado: " + e);
		}
		
		return texto.toString();
	}
	
	
	private static void gravarExtrato(String arquivo, String dest)
	{
		File f = new File("C:\\arquivosXML\\extrato\\"+ dest);
		
		FileOutputStream fos;
		try
		{
			fos = new FileOutputStream(f);
			fos.write(arquivo.getBytes());
			
			fos.flush();
			fos.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static void gravar(String destino, byte[] arquivo)
	{
		File f = new File(destino);
		
		FileOutputStream fos;
		try
		{
			fos = new FileOutputStream(f);
			fos.write(arquivo);
			
			fos.flush();
			fos.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	 Document doc=null;
		Element rootNfe=null;
		Element root=null;
		
	public Element gerarXmlCliente() 
	{
		String xmlLote =null ;
//		NodeList lista = null;

		doc = obtemDocument("C:\\NFE\\000000000000059-env-lot.xml");
		Element rootNfeproc;
		try
		{
			org.w3c.dom.NodeList lista =  doc.getElementsByTagName("NFe");
			
			rootNfe = (Element) lista.item(0);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//lista = rootNfe.getElementsByTagName("NFe");
		
		

		/*String xmlCliente=null;
		for(int a=0;a<lista.getLength();a++)
		{
			rootNfe = (Element) lista.item(a);
			Element infNFe = (Element) rootNfe.getFirstChild();
			String chaveAcesso = infNFe.getAttribute("Id"); 

			chaveAcesso = chaveAcesso.replace("NFe", "").trim();

			if("41090678908266000477550010000001480628933727".equals(chaveAcesso))
			{
				//remove a tag da assinatura
				//nfe.removeChild(nfe.getLastChild());
				String x = convert(null,rootNfe);
				break;
			}
		}*/
		
		return rootNfe;
	}
	
	
	

	public void geraXMLNfe()
	{
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			dbf.setNamespaceAware(true);
			doc = db.newDocument();
			
		}
		catch (ParserConfigurationException eX)
		{
			eX.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Element getRootNfeProc()
	{
		root = doc.createElement("nfeProc");
		root.setAttribute("versao", "1.10");

		return root;
	}
	
	public Element geraXml() throws Exception
	{
		Element rootNfeproc = null;
		
		rootNfeproc = getRootNfeProc();
		doc.appendChild(rootNfeproc);
//		doc.adoptNode(rootNfeproc);
		
		//Element rootNfe = gerarXmlCliente(rootNfeproc);
		//gerarXmlCliente(rootNfeproc);
//		
//		rootNfeproc.appendChild(rootNfe);
		return rootNfeproc;
	}
	
	

	public boolean isPassou()
	{
		return passou;
	}

	public void setPassou(boolean passou)
	{
		this.passou = passou;
	}
	
	public static void gravarXmlNfe(String caminho, Document doc) throws FileNotFoundException, IOException, URISyntaxException
	{
		OutputFormat out = new OutputFormat(doc);
//		XMLSerializer xmlSer = new XMLSerializer(new FileOutputStream(new File(caminho)), out);
		XMLSerializer xmlSer = new XMLSerializer(new FileOutputStream(new File(caminho)), out);
		xmlSer.serialize(doc);
	}
	
	public static boolean validaEmail(String email)
	{
		Pattern p = Pattern.compile(".+@.+\\.[a-z A-Z]+");
				
		Matcher m = p.matcher(email.trim());
		
		return m.matches();
	}
	
	public static boolean validaTelefone(String fone)
	{
		Pattern p = Pattern.compile("[0-9]{10,11}");
				
		Matcher m = p.matcher(fone.trim());
		
		return m.matches();
	}
	
	public static boolean isCelular(String fone)
	{
		Pattern p = Pattern.compile("[0-9]{2}[7-8-9]{1}[0-9]{7,8}");
				
		Matcher m = p.matcher(fone.trim());
		
		return m.matches();
	}
	
	public static boolean validaMaskData(String data)
	{
		Pattern p = Pattern.compile("[0-9]{2}+/+[0-9]{2}+/+[0-9]{4}");
				
		Matcher m = p.matcher(data.trim());
		
		return m.matches();
	}
	
	public static boolean validaMaskTelefone(String fone)
	{
		Pattern p = Pattern.compile("\\([0-9]{2}\\)[0-9]{4}-[0-9]{4,5}");
				
		Matcher m = p.matcher(fone.trim());
		
		return m.matches();
	}
	
	public static boolean validaCampoNumerico(String campo)
	{
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(campo.trim());
		return m.matches();
	}
	
	public static boolean isCampoLetraNumero(String texto){     
        Pattern p = Pattern.compile("[a-zA-Z0-9]{5,10}");     
        Matcher m = p.matcher(texto);     
        return m.matches();     
    }    
	
	
	public static void validaIEporUF(String estado,String ie)
	{

		UF uf = UF.valueOf(estado);
		
		uf.getInscricaoEstadual().setNumber(ie);
		
//		System.out.println("IE "+uf.getInscricaoEstadual().getNumber()+" � v�lida? "+uf.getInscricaoEstadual().isValid());
		
		try
		{
			ResourceBundle resourceBundle = ResourceBundle.getBundle("StellaValidationMessages", new Locale("pt", "BR"));
			MessageProducer messageProducer = new ResourceBundleMessageProducer(resourceBundle);
			
			if(estado.equals("BA"))
			{
				validaIEBahia(ie);
				System.out.println("validou pelo validaIEBahia ie "+ie);
			}
			else
			{
				Estado es =Estado.valueOf(estado);
				es.getIEValidator(messageProducer, false).assertValid(ie);
				System.out.println("validou pelo Stella "+es.name()+" ie "+ie);
			}
			
		}
		catch (Exception ex){System.out.println("NAO validou pelo Stella  ie "+ie+" "+ex.getMessage());}
	}
	
	private static void validaIEBahia(String ie) throws Exception{
		//valida quantida de d&#65533;gitos
		if (ie.length() != 8  && ie.length() != 9){
			throw new Exception("Quantidade de digitos inv�lidas." + ie);
		}

		//C&#65533;lculo do m&#65533;dulo de acordo com o primeiro d&#65533;gito da inscri&#65533;&#65533;o Estadual
		int  modulo = 10;
		int firstDigit = Integer.parseInt(String.valueOf(ie.charAt(ie.length()==8 ? 0 : 1 ))) ; 
		if(firstDigit == 6 || firstDigit == 7 || firstDigit == 9)
			modulo = 11;

		//C&#65533;lculo do segundo d&#65533;gito
		int d2 = -1; //segundo d&#65533;gito verificador
		int soma = 0;
		int peso = ie.length()==8 ? 7 : 8;
		for(int i = 0; i < ie.length() - 2; i++){
			soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
			peso--;
		}

		int resto = soma % modulo;

		if (resto == 0 || (modulo == 11 && resto == 1)){
			d2 = 0;
		} else {
			d2 = modulo - resto;
		}

		//C&#65533;lculo do primeiro d&#65533;gito
		int d1 = -1; //primeiro d&#65533;gito verificador
		soma = d2 * 2;
		peso = ie.length()==8 ? 8 : 9;
		for(int i = 0; i < ie.length() - 2; i++){
			soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
			peso--;
		}

		resto = soma % modulo; 

		if (resto == 0 || (modulo == 11 && resto == 1)){
			d1 = 0;
		} else {
			d1 = modulo - resto;
		}

		//valida os digitos verificadores
		String dv = d1 + "" + d2;
		if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))){
			throw new Exception("Digito verificador inv�lido." + ie);
		}
	}
	
	public static double getValorDoubleFormatado(String valor)
	{
		if(valor!=null && valor.length()>0)
		{
			valor = valor.replaceAll(",", ".");
			double vlr = Double.valueOf(valor);
				
			return  Double.parseDouble(String.format("%.2f", vlr).replaceAll(",", "."));
		}	
		else
			return 0;
	}	
	
	public static String getValorDoubleFormatadoString(String valor)
	{
		if(valor!=null && valor.length()>0)
		{
			valor = valor.replaceAll(",", ".");
			double vlr = Double.valueOf(valor);
				
			return  String.format("%.2f", vlr).replaceAll(",", ".");
		}	
		else
			return "0.00";
	}	
	
	public static String gerarSenha(int tamanhoSenha)
	{
		String[] caracteresPossiveis ={"0","1","2","3","4","5","6","7","8","9","E","I","L","M","P","R"};
		String senha="";
		
		for (int x=0; x<tamanhoSenha; x++)
		{
			while(true)
			{
				int j = (int) (Math.random()*caracteresPossiveis.length);
				if(!senha.contains(caracteresPossiveis [j]))
				{
					senha += caracteresPossiveis [j];
					break;
				}	
			}
		}
		
		System.out.println("Senha gerada: "+senha+" "+senha.toLowerCase());
		System.out.println("Senha criptografada: "+codificarPass(senha));
		return senha;
	}
	public static List<Integer> getTeclas()
	{
//		List<Integer> caracteres = new ArrayList<Integer>();
//		caracteres.add(1);
//		caracteres.add(2);
//		caracteres.add(3);
//		caracteres.add(4);
//		caracteres.add(5);
//		caracteres.add(6);
//		caracteres.add(7);
//		caracteres.add(8);
//		caracteres.add(9);
//		caracteres.add(0);
		
		List<Integer> teclas = new ArrayList<Integer>();
		
		for(int t=0;t<10;t++)
			teclas.add(t);
		
		System.out.println("antes");
		for(Integer i:teclas)
			System.out.print(i);
		
		Collections.shuffle(teclas);  

		System.out.println("depois");
		for(Integer i:teclas)
			System.out.print(i);
		
		
		return teclas;
	}
	
	public static String formatarDataHr(java.util.Date date) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(date);
	}
	
	public static String formataDataString(java.util.Date data) throws ParseException
	{
		SimpleDateFormat sdfIn = new SimpleDateFormat("dd/MM/yyyy");
		return sdfIn.format(data.getTime());
	}
	
	public static String formatarData(Date date) throws Exception 
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String resultado = "";
		resultado = df.format(date);
		return resultado;
	}
	
	public static java.sql.Date converterStringToSqlDate(String dataStr,String formato)throws Exception
	{
		SimpleDateFormat format = new SimpleDateFormat(formato);

		if(dataStr!=null && dataStr.trim().length()>0)
			return  new java.sql.Date(format.parse(dataStr).getTime());
		else
			return null;
	}
	
	public void alteraCalendar(Calendar calendar,int campo,int valor)
	{
		if(campo == Calendar.MONTH)
		{
			calendar.add(campo, valor);
		}
		else
		{
			calendar.set(campo, valor);
		}
	}
	
	public static String getValorFormatado(double valor)
	{
		if(valor>0)
			return (valor > 0) ? String.format("%.2f", valor) : String.format("%.1s", valor);
		else
			return "0,00";
	}
	
	 public void tratarNull(String erro)
	    {
	    	if(erro!=null)
	    	{
	    		String[] parte_1 = erro.split("ORA-");
	    		
	    		if(parte_1.length>=1 && parte_1[1]!=null)
	    		{
	    			erro = parte_1[1];
	    		}
	    		
	    		if(erro.contains("("))
	    		{
	    			String[] err = erro.split("\\(");
	    			if(err.length>1)
	    			{
	    				erro = err[0];
//	    				erro += err[1]
	    				String [] campos = err[1].split("\\.");
	    				
	    				erro += campos[campos.length-1].replaceAll("\\)", "");
	    				
	    				System.out.println(erro);
	    			}
	    		}
	    	}
	    }
	 
	 public static double getValorDoubleFormatado(double valor)
		{
			if(valor>0)
				return (valor > 0) ? Double.parseDouble(String.format("%.2f", valor).replaceAll(",", ".")) : Double.parseDouble(String.format("%.1s", valor));
			else
				return 0;
		}
	 
	 public static String tratarCaracteresEspeciais(String palavra)
		{
			Map<String,String> mapCaracteres = new HashMap<String, String>();
			mapCaracteres.put("<", "&lt;");
			mapCaracteres.put(">", "&gt;");
			mapCaracteres.put("&", "&amp;");
			mapCaracteres.put("\"", "&quot;");
			mapCaracteres.put("'", "&#39;");
			mapCaracteres.put("�", " ");
			mapCaracteres.put("\\*", "-");
			
			Iterator it = mapCaracteres.entrySet().iterator();
			
			while(it.hasNext())
			{
				Map.Entry<String,String> temp = (Entry<String, String>) it.next();  
				palavra = palavra.replaceAll(temp.getKey(), temp.getValue());
			}
			return formatString(palavra);
//			return palavra;
		}
		
		public static String formatString(String s)
		{
//			String temp = Normalizer.normalize(s, java.text.Normalizer.Form.NFD, 0);
//			return temp.replaceAll("[^\\p{ASCII}]", "");
			return null;
		}  
		
		public static ByteBuffer toByteBuffer(String content, String encode) {  
			Charset charset = Charset.forName(encode);  
			ByteBuffer bb = charset.encode(content);  
			return bb;  
		}  
		
		public static void redimensionaImagem2(int tamanhoMax,String caminho,String novoCaminho,String formatoImagem) throws IOException
		{
			BufferedImage originalImage = ImageIO.read(new File(caminho));
			
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

			int altura=0;
			int largura=0;
			
			
			if(originalImage.getWidth()<tamanhoMax && originalImage.getHeight()<tamanhoMax)
			{
				altura =originalImage.getHeight();
				largura = originalImage.getWidth();
			}
			else
			{
				if(originalImage.getWidth() == originalImage.getHeight())
				{
					altura = tamanhoMax;
					largura = tamanhoMax;
				}
				else if(originalImage.getWidth() > originalImage.getHeight())
				{
					float x = (originalImage.getHeight()*100)/originalImage.getWidth();
					float diferenca = (100-x)/100;
					
					altura = (int) (tamanhoMax - (tamanhoMax*diferenca));
					largura = tamanhoMax;
				}
				else if(originalImage.getWidth() < originalImage.getHeight())
				{
					float x = (originalImage.getWidth()*100)/originalImage.getHeight();
					float diferenca = (100-x)/100;
					
					largura = (int) (tamanhoMax - (tamanhoMax*diferenca));
					altura = tamanhoMax;
				}
			}
			
			BufferedImage resizedImage = new BufferedImage(largura, altura, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, largura, altura, null);
			g.dispose();
			g.setComposite(AlphaComposite.Src);

			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
			RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

			
			ImageIO.write(resizedImage, formatoImagem, new File(novoCaminho));
		}
		
		
		/**
		 * 
		 * @param tamanhoMax tamanho m�ximo que vai ficar a imagem
		 * @param caminho caminho da imagem original
		 * @param novoCaminho caminho da imagem depois de alterada
		 * @param formatoImagem jpg,pgn,gif
		 * @throws IOException
		 */
		public static void redimensionaImagem(int tamanhoMax,String caminho,String novoCaminho,String formatoImagem) throws IOException
		{
			BufferedImage image = ImageIO.read(new File(caminho));
			
			System.out.println(image.getWidth()+" x "+image.getHeight());
			
			int altura=0;
			int largura=0;
			
			
			if(image.getWidth()<tamanhoMax && image.getHeight()<tamanhoMax)
			{
				altura =image.getHeight();
				largura = image.getWidth();
			}
			else
			{
				if(image.getWidth() == image.getHeight())
				{
					altura = tamanhoMax;
					largura = tamanhoMax;
				}
				else if(image.getWidth() > image.getHeight())
				{
					float x = (image.getHeight()*100)/image.getWidth();
					float diferenca = (100-x)/100;
					
					altura = (int) (tamanhoMax - (tamanhoMax*diferenca));
					largura = tamanhoMax;
				}
				else if(image.getWidth() < image.getHeight())
				{
					float x = (image.getWidth()*100)/image.getHeight();
					float diferenca = (100-x)/100;
					
					largura = (int) (tamanhoMax - (tamanhoMax*diferenca));
					altura = tamanhoMax;
				}
			}
			
			BufferedImage imageCopy = new BufferedImage(largura, altura, image.getType());
			
			
			//AffineTransform identity = new AffineTransform();

			//AffineTransform trans = new AffineTransform();
			
			Graphics2D g2d = imageCopy.createGraphics();  
			g2d.drawImage(image, 0, 0, largura, altura, null);  
			
			
//			g2d.rotate(Math.toRadians(90));
			
			//trans.setTransform(identity);
			//trans.rotate( Math.toRadians(135) );
			//g2d.drawImage(image, trans, null);
			
			
			g2d.dispose();  
			
			
			ImageIO.write(imageCopy, formatoImagem, new File(novoCaminho));
		}
		
		public static byte[] redimensionaImagem(String caminho, float quality,String formatoImagem, int size) throws IOException
		{
			
			BufferedImage image = ImageIO.read(new File(caminho));
			
				Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix(formatoImagem);
		       if (!writers.hasNext()) throw new IllegalStateException("No writers found");
		       ImageWriter writer = (ImageWriter) writers.next();
		       
		       // Create the ImageWriteParam to compress the image.
		       ImageWriteParam param = writer.getDefaultWriteParam();
		       if(!formatoImagem.equalsIgnoreCase("png"))
		       {
		    	   param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		    	   param.setCompressionQuality(quality);
		       }
		       
		       // The output will be a ByteArrayOutputStream (in memory)
		       ByteArrayOutputStream bos = new ByteArrayOutputStream(size);
		       ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
		       writer.setOutput(ios);
		       writer.write(null, new IIOImage(image, null, null), param);
		       ios.flush(); // otherwise the buffer size will be zero!
		       
		       return bos.toByteArray();
		       
		}
	 
		public static String getNumeroFormatado(int numero, String decimal) {
			NumberFormat formatter = new DecimalFormat(decimal);
			String s = formatter.format(numero);
			return s;
		}
		
		 public static String getNovoNome(String caminhoArquivo)
		    {
		        String caminhoOriginal = caminhoArquivo;
		        String caminho = caminhoArquivo;//novo nome
		        String formato = "";
		        StringTokenizer strk = new StringTokenizer(caminho, ".");
		        while (strk.hasMoreElements())
		        {
		            Object o = strk.nextElement();
		            formato = o.toString();
		        }
		        caminho = caminho.substring(0, caminho.indexOf(formato) - 1);
		        caminho = caminho + "Peq." + formato;
		        String novoCaminho = caminhoOriginal.replace(caminhoArquivo, caminho);
		        
		        return novoCaminho;
		        
		    }
	
		 public static boolean validaPlaca(String placa)
			{
				//Pattern p = Pattern.compile(".+@.+\\.[a-z A-Z]+");
			 Pattern p = Pattern.compile("[a-z A-Z]{3}-[0-9]{4}");
				Matcher m = p.matcher(placa.trim());
				return m.matches();
			}
		 
		 public static boolean validaIE(String valor)
		 {
			 Pattern p = Pattern.compile("(\\d{6,7})(\\d{2})");
			 Matcher m = p.matcher(valor);
			 
			 return m.matches();			 
		 }
		 
		 public static String extraiTextoDoPDF(String caminho) {
			    PDDocument pdfDocument = null;
			    try {
			      pdfDocument = PDDocument.load(caminho);
			      PDFTextStripper stripper = new PDFTextStripper();
			      String texto = stripper.getText(pdfDocument);
			      return texto;
			    } catch (IOException e) {
			      throw new RuntimeException(e);
			    } finally {
			      if (pdfDocument != null) try {
			        pdfDocument.close();
			      } catch (IOException e) {
			        throw new RuntimeException(e);
			      }
			    }
			  }
		 
		 public static void lerPdfItExt(String caminho)
		 {
			 try{
				 
		            PdfReader reader = new PdfReader(caminho);
		            int n = reader.getNumberOfPages();
		            System.out.println("Total de p�ginas para este pdf: "+n);
		 
		            //extraindo o conte�do de uma p�gina espec�fica
		            String str=PdfTextExtractor.getTextFromPage(reader, 1);
		            System.out.println("Conteudo: "+str);
		 
		        }catch(Exception x){
		            x.printStackTrace();
		        }
		 }
		 
		public static String returnHex(byte[] inBytes) throws Exception {
		        String hexString = "";
		        for (int i=0; i < inBytes.length; i++) { //for loop ID:1
		            hexString +=
		            Integer.toString( ( inBytes[i] & 0xff ) + 0x100, 16).substring( 1 );
		        }                                   // Belongs to for loop ID:1
		    return hexString;
		  }                       
		 
	public void hashImage(String caminhoImg,String formatoImg) throws NoSuchAlgorithmException, Exception 
	{
		File input = new File(caminhoImg);
	       
        BufferedImage buffImg = ImageIO.read(input);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(buffImg, formatoImg, outputStream);
        byte[] data = outputStream.toByteArray();

        System.out.println("Start MD5 Digest");
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data);
        byte[] hash = md.digest();
        System.out.println("hash: "+returnHex(hash));
	}
		 
    public static String extrairTagXml(String xml, String tag) {  
        String valor = "";  
        String endTag = "</" + tag.substring(1);  
      
        Pattern p = Pattern.compile(tag + "(.*)" + endTag);  
        Matcher m = p.matcher(xml);  
      
        if (m.find()) {  
            valor = m.group(1);  
        }  
      
        return valor;  
    }  
    
    public static void calculaTotal(Collection c)
    {
    	
    	for (Object object : c)
		{
    		if (object instanceof Pessoa)
    		{
    			Pessoa new_name = (Pessoa) object;
    			System.out.println("lista de pessoas");
    		}
    		else
    		{
    			System.out.println("lista de produtos");
    		}
			
		}
    	
    }
	
    public static String mascaraDinheiro(double valor)
	{
    	
    	Locale BRAZIL = new Locale("pt","BR");   
    	/**  
    	 * S�mbolos especificos do Real Brasileiro  
    	 */  
    	DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);   
    	/**  
    	 * Mascara de dinheiro para Real Brasileiro  
    	 */  
    	DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00",REAL);
    	
    	
		return DINHEIRO_REAL.format(valor);
	}
    
    public static String mascaraDinheiroSimbolo(double valor)
	{
    	
    	Locale BRAZIL = new Locale("pt","BR");   
    	
    	
		return NumberFormat.getCurrencyInstance(BRAZIL).format(valor);
	}
    
    
    public String formatAddress(String address)
	{
    	address = address.toUpperCase();
		address = address.replaceAll("-", " ").replaceAll("\\.", " ").replaceAll(",", " ");
		address = formatString(address);		
		
		String addressAux = "";
		Character c = null;
		for(int i=0; i < address.length(); i++)
		{
			c = address.charAt(i);
			
			if(!c.isDigit(c))
				addressAux += address.charAt(i);
		}
		
		return addressAux.toUpperCase().trim();
	}
    
    public enum Const {ADICIONAR,REMOVER};
    
    public static boolean matchRatingApproach()
	{
		String[] keyWords  = {"COMERCIO", "FARMACIA", "DROGARIA", "MERCADO", "ATACADO"};
		
		String[] aFantasia = "TESTE SA ALTERADO".split(" ");
		String[] aRazao = "SUPERMERCADO CLIENTE DE -TESTE - SA".split(" ");
		
		for (int i = 0; i < keyWords.length; i++)
		{
			if("SUPERMERCADO CLIENTE DE -TESTE - SA".contains(keyWords[i]))
			{
				System.out.println(keyWords[i]);
			}
		}
		return false;
	}
	
    public static java.util.Date converterDateTimeToDate(String dataStr) throws Exception 
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(dataStr!=null && dataStr.trim().length()>0)
			return df.parse(dataStr);
		else
			return null;
	}
    private static int seconds;
    private static int minute;
    private static int totalTime;
    private static Timer timer;
    
    public static void countdown(int m)
    {
    	int delay = 1000;
    	int period = 1000;

    	timer = new Timer();
    	minute = m;
    	totalTime = minute * 60;

    	System.out.printf("Iniciou em: %1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS:%1$tL teste \n", Calendar.getInstance());
    	System.out.println(minute+":00");
    	seconds = 60;

    	timer.scheduleAtFixedRate(new TimerTask() {

    		public void run() 
    		{
    			System.out.println(setInterval());
    		}
    	}, delay, period);
    }
    
    private static final String setInterval() 
    {
    	String hours = "";
    	
    	if(seconds == 60)
			--minute;
    	
//    	hours = (minute)+":"+(--seconds);
    	hours = String.format("%02d:%02d", minute,--seconds);
    	
    	-- totalTime;
    	
	    if (totalTime == 0 )
	    {
	    		timer.cancel();
	    		System.out.printf("terminou em: %1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS:%1$tL teste \n", Calendar.getInstance());
	    }
	    else if(seconds == 0)
	    {
	    	seconds = 60;
	    }
	    
	    return hours;
	}
    
    /**
     * Funcao recursiva
     * @param n
     * @return
     */
    public int calculaSomaN(int n)
    {
    	if( n <= 0)
    		return 0;
//    	System.out.println(n+ " + ");
    	return n + calculaSomaN(n - 1);
    }
    
    /**
     * Funcao com loop
     * @param n
     * @return
     */
    public int calculaSomaLoop(int n)
    {
    	int soma = 0;
    	for (int i = 0; i <= n; i++)
		{
    		//System.out.print(soma + " + "+ i +" = " );
			soma = soma + i;
			//System.out.println( soma);
		}
    	
    	return soma;
    }
    /**
     * Calcula soma dos naturais �mpares MENORES que n
     * @param n
     * @return
     */
    public int calculaSomaImpar(int n)
    {
    	//System.out.println("N = "+ n);
    	if(n <= 0)
    		return 0;
    	
    	//somente menor que n
    	n = n -1;
    	
    	//verifica se � �mpar
    	//divide por 2, se o resto for > 0 ent�o � �mpar
    	if (n % 2 > 0)
    	{
    		//System.out.println("N Impar "+ n);
    		return n + calculaSomaImpar(n);
    	}
    	return calculaSomaImpar(n);
    }
    
    
    public double calculaPotenciaEuler(double potencia)
    {
    	System.out.print("calculaPotenciaEuler ( "+potencia +" ) -> ");
    	
    	double euler = 5 ;
    	
    	if(potencia < 0 )
    		return 0;
    	
    	if(potencia == 0)
    	{
    		System.out.println("");
    		System.out.println("P("+potencia +") = "+1);
    		return 1;
    	}
    	
    	double subPotencia = potencia - 0.5;
    	double result = (calculaPotenciaEuler(subPotencia));
    	double calculo = 0;
    	if(result == 1)
    		calculo = result;
    	else if( result % 2 == 0 )
    		calculo = euler * result;
    	else	
    		calculo = euler * (result);
//    	double calculo = euler * (calculaPotenciaEuler(potencia));
    	
    	System.out.println("P("+potencia +") =  euler * result ("+result+") = "+calculo);
    	
    	return calculo;
    }
    
    public boolean isThisDateValid(String dateToValidate, String dateFromat){

		if(dateToValidate == null){
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			//if not valid, it will throw ParseException
			java.util.Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}
    
	public static double divideWithBigDecimal(double val01, double val02)
	{
		BigDecimal bigVal01 = new BigDecimal(val01);
		
		return bigVal01.divide(new BigDecimal(val02), 5, RoundingMode.HALF_UP)
			.setScale(2, RoundingMode.HALF_UP)
			.doubleValue();
	}
	
	public static double multiplyWithBigDecimal(double val01, double val02, int scala)
	{
		BigDecimal bigVal01 = BigDecimal.valueOf(val01);
		
		return bigVal01.multiply(BigDecimal.valueOf(val02))
			.setScale(scala, RoundingMode.HALF_UP)
			.doubleValue();
	}
	
	
	public void sleep()
	{
		try
		{
			Thread.sleep(1 * 1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean testExpressao(String valor)
	{
		Pattern p = Pattern.compile("[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{1,4}-[a-z A-Z 0-9]+");
		Matcher m = p.matcher(valor.trim());
		return m.matches();
	}
	
	 private static List<String> getAlphabet()
	  {
	      List<String> alphabet = new ArrayList();
	      
	      for (char c = 'a'; c <= 'z'; c++)
	      {
	    	  alphabet.add(String.valueOf(c));
	      }
			
	      return alphabet;
	  }
	 
	 
	private static String orderAlphabet(String str)
	{
		if (str.equals(""))
			return "";

		char[] aAlphabet = str.toCharArray();
		
		char aux = 1000;
		for (char c : aAlphabet)
		{
			if (c <= aux)
				aux = c;
		}

		str = str.replaceFirst(String.valueOf(aux), "");

		return String.valueOf(aux) + orderAlphabet(str);
	}
	
	private static String orderAlphabet2(String str)
	{
		char[] aAlphabet = str.toCharArray();
		
		Arrays.sort(aAlphabet);
		
		return String.valueOf(aAlphabet);
	}
    
	public static boolean validaPedidoCliente(String pedido)
	{
		Pattern p = Pattern.compile("[- / \\. 0-9 a-z A-Z]+");
				
		Matcher m = p.matcher(pedido.trim());
		
		return m.matches();
	}
	
	public static void main(String rgs[]) 
	{
		Teste t = new Teste();
		
		
		System.out.printf("Iniciou em: %1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS:%1$tL teste \n", Calendar.getInstance());
		
		System.out.printf("Iniciou em: %1$tD %1$tT %2$s\n", Calendar.getInstance(),"Felipe");
		
		System.out.printf("Iniciou em: %1$tm%1$ty \n", Calendar.getInstance());
		
		String s = String.format("%1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS:%1$tL TemperaturaServlet alarm "+1, Calendar.getInstance());
		
		System.out.println(s);
		
		
		String din = mascaraDinheiro(200000.50);
		System.out.println("DINHEIRO: " + din);
		
		String dinSimboloa = mascaraDinheiroSimbolo(200000.50);
		System.out.println("DINHEIRO com simbolo: " + dinSimboloa);
		
		
		Integer myNum = new Integer(1);
		Integer myNumPrimitivo = 1;
		
		String nrPedido = ".felipe/Alves - 3.5/0 E xtz.0";
		System.out.println(" ------------------->>> Nr pedido " + nrPedido +" is valid " + validaPedidoCliente(nrPedido)); 
		
		
		//if (myNum.equals(myNumPrimitivo))
		if (myNum == (myNumPrimitivo))
			System.out.println("myNum igual a myNumPrimitivo");
		else
			System.out.println("myNum diferente myNumPrimitivo");
		
		
		System.out.println("Order Alphabet: " + orderAlphabet("abelha")+ " 2 - " + orderAlphabet2("abelha"));
		
		double valor = 100;
		double valor2 = ((5d / 100d) + 1d);
		
		System.out.println("Result valor2: " + valor2);
		
		double rBig = t.divideWithBigDecimal(valor, valor2);
		
		System.out.println("Result Divide: " + rBig);
		
		double rBig2 = t.multiplyWithBigDecimal(valor, valor2, 5);
		
		System.out.println("Result Multiple: " + rBig2);
		
		
		String rel = String.valueOf(Calendar.getInstance().getTimeInMillis());
		
		
		try 
		{
			System.out.printf("Teste sleep: %1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS:%1$tL teste \n", Calendar.getInstance());
			t.sleep();
			System.out.printf("Teste sleep 2: %1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS:%1$tL teste \n", Calendar.getInstance());
			
			String dataNfe = "2017-06-02T11:49:20-03:00";
			String dataNfe2 = "2017-06-02";
			
			
			java.util.Date sdfParse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dataNfe);
			
			String sdfFormat = new SimpleDateFormat("HH:mm:ss").format(sdfParse);
			
			System.out.printf("Data NFe %s \n ", sdfFormat);
			
			
			LocalDate jodaDate = converterStringToJodaLocalDate("05/08/2017");
			jodaDate = jodaDate.withDayOfMonth(1);
			System.out.println("LocalDate : " + converterJodaLocalDateToString(jodaDate));
			
			
			DateTime dateTime = new DateTime();
//			dateTime = dateTime.minusDays(1);
			if (dateTime.getDayOfWeek() == DateTimeConstants.MONDAY)
			{
				
			}
			System.out.println("joda date Format: " + dateTime.toString("dd/MM/YYYY"));
			dateTime = dateTime.withDayOfWeek(DateTimeConstants.MONDAY);
			
			System.out.println("joda date Format: " + dateTime.toString("dd/MM/YYYY"));
			
			dateTime = dateTime.withDayOfWeek(DateTimeConstants.MONDAY).plusWeeks(1);
			System.out.println("joda date Format: " + dateTime.toString("dd/MM/YYYY"));

			org.joda.time.LocalDateTime localDateTime = org.joda.time.LocalDateTime.now();
			
			String dateTimeFormatter = localDateTime.toString("yyyyMMddHHmmss");

			System.out.println("dateTimeFormatter: " + dateTimeFormatter);
			
			System.out.println(System.getProperty("os.name"));
			
			
			System.out.println("MES:: "+ LocalDate.now().toString("MMM"));
			
			boolean valido = testExpressao("97.4.242-LIMA");
			System.out.println("Teste ER: " + valido);
			
			System.out.print(getAlphabet());
			List<String> alphabet = getAlphabet();
			alphabet.contains("c");
			System.out.println("Last index: " + alphabet.lastIndexOf("xx"));
			
//			File[] files = new File("C:\\arquivosXML\\extrato\\banco").listFiles();
//			
//			for (File file : files)
//			{
//				String arquivo = lerArquivoBanco(file.getAbsolutePath());
//				gravarExtrato(arquivo, file.getName());
//			}
			
			
			DecimalFormat mNF = (DecimalFormat) NumberFormat.getCurrencyInstance();
			DecimalFormatSymbols symbols = ( mNF).getDecimalFormatSymbols();
			symbols.setCurrencySymbol(""); // Don't use null.
			mNF.setDecimalFormatSymbols(symbols);
			String ss = "190";
			
						
			double value = (Double.parseDouble(ss) / 100);
	        String formattedValue = mNF.format(value);
		
	        System.out.println("sb2:" + formattedValue);
//			String format = String.format("%05d", m);
//			System.out.println("Format1: "+ format);
			
			
			
			
//			
			Random r = new Random();
			for(int i = 0 ; i< 6 ; i++)
			{
				//System.out.println(r.nextInt(60));
			}
		
			
			int choose = 100;
			int total = 101;
			int middle = total / 2;
			int max = total;
			int min = 0;
			
			boolean go = true;
			int tried = 1;
			while(go)
			{
				//System.out.println("Tentativa = "+tried);
				if(choose == middle)
				{
					//System.out.println("Number is: "+choose);
					go = false;
				}
				else if(choose > middle)
				{
					min = middle;
					max = total;
					middle = middle + ((total - middle) / 2);
				}
				else if(choose < middle)
				{					
					max = middle;
					middle = min + ((middle - min) / 2) ;
				}
			
				//System.out.println("min: "+min+" max: "+max+" mid: "+middle);
				
				tried++;
				if(tried == 100)
				{
					System.out.println("Error: "+choose);
					go = false;
				}
			}
			
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("id", "1");
			param.put("name", "Felipe");
			param.put("age", "33");
			param.put("city", "Curitiba");
			
			System.out.println(param.toString());
			
			
			String cidade = "palho�a";
						
			cidade = cidade.replaceAll("[^\\p{ASCII}]", "");
			System.out.println("Cidade: "+cidade);

			List<Integer> listAdd = new ArrayList<Integer>();
			listAdd.add(1);
			listAdd.add(2);
			listAdd.add(3);
			listAdd.add(4);
			
			
			List<Integer> listDel = Arrays.asList(1);
			listAdd.removeAll(listDel);
			
//			myList2.retainAll(myList);
			
			System.out.println("listAdd: " + listAdd);
			System.out.println("listDel: " + listDel);
			
			String myCell = "419915462";
			
			System.out.println(myCell + " is cell " + isCelular(myCell));
			
			
//			System.out.println(t.extraiTextoDoPDF("c:\\NFE\\gnre-288792.pdf"));
			
//			Teste.lerPdfItExt("c:\\NFE\\gnre-288792.pdf");
			
			
			int a = 012;
			int b = 12;
			int c = Integer.parseInt("012", 10);
			
			System.out.println("a: "+ a);
			System.out.println(b == c );
			System.out.println(a == c );
			
			
			List<Pessoa> listPessoa = new ArrayList<Pessoa>();
			listPessoa.add(new Pessoa("Felipe"));
			listPessoa.add(new Pessoa("Vieira"));
			
			String[] aPessoa = new String[listPessoa.size()];
			for (int i = 0; i < listPessoa.size(); i++)
			{
				String string = listPessoa.get(i).toString();
				aPessoa[i] = string;
				
			}
			
			String word = "A+d+=3=+s+";
			
			char[] myAlphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			word = word.toLowerCase();
		    
		    for (int i = 0; i < word.length(); i++)
		    {
		    	for (int a1 = 0; a1 < myAlphabet.length; a1++)
		    	{
		    		if (i == 0 || i == word.length()-1)
		    		{
		    			if (word.charAt(i) == myAlphabet[a1])
		    			{
		    				System.out.println("word False");
		    				break;
		    			}
		    		}
		    		if (word.charAt(i) == myAlphabet[a1])
	    			{
		    			if (word.charAt(i - 1) != '+' || word.charAt(i + 1) != '+')
		    			{
		    				System.out.println(" word False");
		    				break;
		    			}
	    			}
		    	}
		    }
			
			
			System.out.println("Array Convertido: " + aPessoa.length);
			
			gerarSenha(5);
			
			List<Integer> clientes = Arrays.asList(123456,852369,366335,5556);
			
			System.out.println(clientes.toString());
			
			String[] myArray = new String[] {"1","3","5"};
				
			
			System.out.println("myArray: " + myArray + " myArray.toString: " + Arrays.asList(myArray));
			
			
			List<String> listString = Arrays.asList("a", "b", "c", "d");
			
			String[] myArray2 = new String[0];
			
//			listString.toArray(new String[0]).length;
			
			System.out.println("list to myArray: " + listString.toArray(new String[0]).length);
			
//			String periodoAtual = String.format("%1$tm%1$ty", Calendar.getInstance());
//			System.out.println(periodoAtual);
			
			
			//String texto = "<infNFe versao=\"2.00\" Id=\"NFe35121013261046000610550010000003621000019785\">abc</infNFe>";  
		    /*Pattern p = Pattern.compile("NFe\\d+");  
		    Matcher m = p.matcher(t.lerXmlGerado("C:\\arquivosXML\\xml\\NFe35121013261046000610550010000003621000019785_procNFe.xml"));  
		    if(m.find())
		        System.out.println(m.group());  
			
			
			Pattern regex = Pattern.compile("<infNFe>(.*?)</infNFe>");
			Matcher matcher = regex.matcher(t.lerXmlGerado("C:\\arquivosXML\\xml\\NFe35121013261046000610550010000003621000019785_procNFe.xml"));
			Pattern regex2 = Pattern.compile("<([^<>]+)>([^<>]+)</\\1>");
			if (matcher.find()) {
			    String DataElements = matcher.group(1);
			    Matcher matcher2 = regex2.matcher(DataElements);
			    while (matcher2.find()) {
			        System.out.println(matcher2.group());
			    } 
			}
			
			String emit = t.extrairTagXml(t.lerXmlGerado("C:\\arquivosXML\\xml\\NFe35121013261046000610550010000003621000019785_procNFe.xml"), "<emit>");
			
			System.out.println(t.extrairTagXml(emit,"<CNPJ>"));*/
			
			//t.validaIEporUF("BA", "077381387");
//			t.validaIE("078414983");
			
			
			
			
			//t.redimensionaImagem(1000, "c:\\doc\\IMG_7772.JPG", "c:\\doc\\PAPEL-formatado-1.jpg", "jpg");
//			t.redimensionaImagem(500, "c:\\doc\\messi.jpg", "c:\\doc\\messi-formatado-2.jpg", "jpg");
			
			
		/*	t.hashImage("c:\\doc\\tela.jpg", "jpg");
			t.hashImage("c:\\doc\\messi.jpg", "jpg");
			t.hashImage("c:\\doc\\messi-formatado-1.jpg", "jpg");
			t.hashImage("c:\\doc\\messi-formatado-2.jpg", "jpg");*/
			
//			Pattern p = Pattern.compile(".+@.+\\.[a-z A-Z]+");
//			
//			
//			
//			String nome = "FelipeVieiraalves@mili7";
//			
//			System.out.println(nome.matches(".+@.+\\.[a-z A-Z]+"));
//			System.out.println(Teste.validaEmail(nome));
			
			
			//t.validaIEporUF("BA", "100485859PP");
			
//			System.out.println(String.valueOf(a));
//			System.out.println(gerarSenha(6));

			
//			redimensionaImagem(500, "C:\\doc\\produto\\protetor_diario_15unidades.jpg", "C:\\doc\\produto\\500.jpg", "jpg");
			
			//System.out.println(t.validaPlaca("asp-207"));
			
//			String cabecalho = conteudo.substring(0, 27);
//			cabecalho +="\r";
//			System.out.println("cabecalho "+cabecalho);
//			
//			conteudo = conteudo.substring(27, conteudo.length());
//			
//			String corpo ="";
//			while(conteudo.length() >= 1224)
//			{
//				corpo += conteudo.substring(0, 1224);
//				corpo +="\r";
//				System.out.println("corpo "+corpo);
//				conteudo = conteudo.substring(1224, conteudo.length());
//			}
//			String rodape = conteudo.substring(0, conteudo.length());
//			System.out.println("rodape: "+rodape);
//			
//			
			/*File files = new File("C:\\arquivosXML\\\\");
			
			for (File f : files.listFiles())
			{
				String xml="";
				
				System.out.println(f.getName());
				
				BufferedReader br = new BufferedReader(new FileReader(f));
						
				while (br.ready())
				{
					xml = br.readLine();
				}
				
				br.close();
				
				FileWriter fw = new FileWriter(f);
				fw.write(xml);
				fw.flush();
				fw.close();
			}*/
			

			DecimalFormat df = new DecimalFormat("###,###,###,###,###.00");
			System.out.println("Format: " + df.format(new Double(1105980099.59)));
			
			
			String n = "1.90";
			System.out.println(n.replaceAll("\\.", ","));
			
			Integer i = Double.valueOf("1456856.2").intValue();
			
			/*String completo = "teste 5 \n";
			File f = new File("C:\\arquivosXML\\1301805413-ok.txt");
			FileOutputStream fos = new FileOutputStream(f,true);
			fos.write(completo.getBytes());
			fos.flush();
			fos.close();*/
//			System.out.println(formatarData(converterStringToSqlDate("18/08/2009","dd/MM/yyyy")));
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("senha decodificada:"+t.decodificarPass("HZRA5G9G"));
		
		
		System.out.println("senha codificada:"+t.codificarPass("815530"));
		System.out.printf("terminou em: %1$tH:%1$tM:%1$tS \n", Calendar.getInstance());
		
		//countdown(2);
	}
	

	

	
}


