package com.kscbrasil.notaFiscalGF.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.security.Provider;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.security.cert.X509Certificate;


import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


import br.com.nfe.web.assinador.X509KeySelector;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;


import com.kscbrasil.constante.KscConstante;
import com.kscbrasil.lib.database.ConstanteDataBase;
import com.kscbrasil.lib.exception.KscException;
import com.kscbrasil.lib.type.KscString;
import com.kscbrasil.notaFiscalGF.model.LoteRps;
import com.kscbrasil.parametrosFaturamento.model.ParametrosFaturamento;
import com.kscbrasil.parametrosFaturamento.service.ParametrosFaturamentoService;
import com.kscbrasil.util.CriptografiaHexadecimal;
import com.sun.xml.internal.txw2.Document;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class AssinarNfe {

	private static final String C14N_TRANSFORM_METHOD = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
	private static final String PROVIDER_CLASS_NAME = "org.jcp.xml.dsig.internal.dom.XMLDSigRI";
	private static final String PROVIDER_NAME = "jsr105Provider";
	
	
	public AssinarNfe() throws KscException
	{

		System.out.println("teste contrutor");
	}
	
	public static String assinar(String xml, String localXMLCliente, ParametrosFaturamento parametrosFaturamento, String tagAssinatura) throws Exception { 
		
		xml = localXMLCliente + xml;
		
		System.out.println(xml);
		File arquivo = new File(xml);
		FileInputStream isX = new FileInputStream(arquivo);   
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		factory.setNamespaceAware(true); 
		DocumentBuilder builder = factory.newDocumentBuilder(); 
		//org.w3c.dom.Document doc = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		System.out.println("Antes de parsear o arquivo");
		org.w3c.dom.Document doc = factory.newDocumentBuilder().parse(isX);

		
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM"); 
		ArrayList transformList = new ArrayList(); 
		TransformParameterSpec tps = null; 
		Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, tps); 
		Transform c14NTransform = fac.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps); 

		transformList.add(envelopedTransform); 
		transformList.add(c14NTransform); 

		CriptografiaHexadecimal criptSenha = new CriptografiaHexadecimal();
		
		//System.out.println("caminho"+KscConstante.DIRETORIO_PROJETO + "/certificados/"+parametrosFaturamento.getArquivo());
		File certificado = new File( KscConstante.DIRETORIO_PROJETO + "/certificados/"+parametrosFaturamento.getArquivo());
		//File certificado = new File( KscConstante.DIRETORIO_PROJETO + "/certificados/formato9.pfx");
		//String password = criptSenha.decriptografarSenha(config.getSenhaAssDig());
		
		
		//ParametrosFaturamento criterio = new ParametrosFaturamento();
		//ParametrosFaturamento parametros = new ParametrosFaturamento();
		//criterio.setIdEmpresa(new KscString("1_16_12"));
		//ParametrosFaturamentoService pfs = new ParametrosFaturamentoService();
		//parametros = (ParametrosFaturamento)new ParametrosFaturamentoService().consultarParametrosFaturamento(criterio, "", null).get(1);
		
		String password = null;
		char[] pwd = null;
		KeyStore ks = KeyStore.getInstance("PKCS12"); 
		
		if(parametrosFaturamento.getSenhaCertificado().getValue()!=null){
			password = criptSenha.cript(parametrosFaturamento.getSenhaCertificado().getWebValue(),"1");
			pwd = password.toCharArray(); 
		}
		InputStream is = new FileInputStream( certificado ); 
		ks.load(is, pwd);

		//ChavePrivada		
		KeyStore.PrivateKeyEntry pkEntry = null; 
		Enumeration aliasesEnum = ks.aliases(); 
		PrivateKey privateKey = null; 
		String alias = "";
		while (aliasesEnum.hasMoreElements()) { 
			alias = (String) aliasesEnum.nextElement(); 
			System.out.println("Alias: " + alias); 
			if (ks.isKeyEntry(alias)) { 
				pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(password.toCharArray()));
				//pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(null));
				privateKey = pkEntry.getPrivateKey(); 
				break; 
			} 
		} 

		
		
		X509Certificate cert = (X509Certificate) pkEntry.getCertificate(); 
		System.out.println(funcStatusCertificado(cert));
		cert.getNotAfter();
		cert.getNotBefore();
		
		System.out.println("Chave publica");
		//ChavePublica
		X509Certificate certP = (X509Certificate) ks.getCertificate(alias);   
        PublicKey publicKey = certP.getPublicKey();
		
		
		KeyInfoFactory kif = fac.getKeyInfoFactory(); 
		List x509Content = new ArrayList(); 
		x509Content.add(cert.getSubjectX500Principal().getName());
		x509Content.add(cert); 
		X509Data xd = kif.newX509Data(x509Content); 
		KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd)); 

		
		//NodeList elements = doc.getElementsByTagName("InfRps");
		NodeList elements = doc.getElementsByTagName(tagAssinatura);
	//	NodeList elements2 = doc.getElementById(elementId);
		
	//	System.out.println("Quantidade de nós:"+elements2.getLength());
		
		System.out.println("AssinarNfe-Antes de montar os nos");
		for(int n=0; n< elements.getLength();n++){
		
			Element el = (Element) elements.item(n); 
		
		
			String id = el.getAttribute("id"); 
			System.out.println("ID-"+id);
			//Reference ref = fac.newReference("#".concat(id), fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);
			Reference ref = fac.newReference(id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);
			SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref)); 
			XMLSignature signature = fac.newXMLSignature(si, ki); 
			System.out.println("meio do metodo");
			//Signature clientSig = Signature.getInstance("DSA");
			//clientSig.initVerify(publicKey);
			//clientSig.update(byte el.);
			//if (clientSig.verify(assinatura)) {
				//Mensagem assinada corretamente7 
			//	} else {
					//Mensagem não pode ser validada9 
			  
			//}
			DOMSignContext dsc = new DOMSignContext( privateKey, el);
			
			signature.sign(dsc); 
			System.out.println("depois da ssinatura no for");
	
			
		}
		System.out.println("Depois dos nos");
			
		OutputStream os = new FileOutputStream( xml ); 
		TransformerFactory tf = TransformerFactory.newInstance(); 
		Transformer trans = tf.newTransformer(); 
		trans.transform(new DOMSource(doc), new StreamResult(os)); 

		
		
		ByteArrayOutputStream os2 = new ByteArrayOutputStream(); 
		TransformerFactory tf2 = TransformerFactory.newInstance(); 
		Transformer trans2 = tf2.newTransformer(); 
		trans.transform(new DOMSource(doc), new StreamResult(os2)); 
		
		System.out.println("Antes de concluir o metodo assinar.");
		return os2.toString(); 
	} 
	 
	public String assinarEnvioLoteRps(String arquivo, String caminho, ParametrosFaturamento parametrosFaturamento){
		
		
		System.out.println("Metodo assinar envio-lotes:"+caminho);
		caminho = caminho + arquivo;
		
		String idNfseS = null;
		
		String nfseDadosMsg = "";
		ByteArrayOutputStream os2 = new ByteArrayOutputStream(); 
		
		try {
			
			System.out.println("Dentro do assinar lote");
			
			String tag = "LoteRps";
			String caminhoCertificado =  KscConstante.DIRETORIO_PROJETO + "/certificados/"+parametrosFaturamento.getArquivo();
			//String caminhoCertificado =  KscConstante.DIRETORIO_PROJETO + "/certificados/formato9.pfx";
			//String senha = "123456";
			
			CriptografiaHexadecimal criptSenha = new CriptografiaHexadecimal();
			String pwd = null;
			char[] senha = null;
			KeyStore ks = KeyStore.getInstance("PKCS12"); 
			
			if(parametrosFaturamento.getSenhaCertificado()!=null){
				pwd = criptSenha.cript(parametrosFaturamento.getSenhaCertificado().getWebValue(),"1");
				senha = pwd.toCharArray();
			}	
			//InputStream is = new FileInputStream( certificado ); 
			//ks.load(is, pwd); 
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document docs = builder.parse(new File(caminho));
			
			NodeList elements = docs.getElementsByTagName(tag);
			
			Element el = (Element) elements.item(0);
			String id = el.getAttribute("id");
			//String id = idLoteRps.toString();
			
			String providerName = System.getProperty(PROVIDER_NAME, PROVIDER_CLASS_NAME);
			XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());		

			ArrayList transformList = new ArrayList(); 
			TransformParameterSpec tps = null; 
			Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED,tps); 
			Transform c14NTransform = fac.newTransform(C14N_TRANSFORM_METHOD, tps); 
			transformList.add(envelopedTransform); 
			transformList.add(c14NTransform); 

			Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null); 
			SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,(C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref)); 

			//KeyStore ks = KeyStore.getInstance("PKCS12");
			ks.load(new FileInputStream(caminhoCertificado), senha); 
			Enumeration aliasesEnum = ks.aliases(); 
			String alias = ""; 
			while (aliasesEnum.hasMoreElements()) { 
				alias = (String) aliasesEnum.nextElement(); 
				System.out.println("alias " + alias);
				if (ks.isKeyEntry(alias)) { 
					break; 
				} 
			} 

			KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(senha)); 

			//KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new String(senha));
			X509Certificate cert = (X509Certificate) keyEntry.getCertificate(); 
			KeyInfoFactory kif = fac.getKeyInfoFactory(); 
			List x509Content = new ArrayList(); 
			
			x509Content.add(cert); 
			X509Data xd = kif.newX509Data(x509Content); 
			KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd)); 

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			dbf.setNamespaceAware(true); 
			org.w3c.dom.Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(caminho)); 

			DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getFirstChild());

			XMLSignature signature = fac.newXMLSignature(si, ki); 
			signature.sign(dsc); 

			doc.setXmlStandalone(true);
			
			NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature"); 
			
			if (nl.getLength() == 0) { 
				throw new Exception("Elemento assinatura não encontrado"); 
			} 
			
			DOMValidateContext valContext = new DOMValidateContext( new X509KeySelector(ks), nl.item(nl.getLength()-1)); 
			XMLSignature signatures = fac.unmarshalXMLSignature(valContext); 
			boolean coreValidity = signatures.validate(valContext); 
			if (coreValidity == false) { 
				System.err.println("Falha na Assinatura!" + idNfseS);			
			} else { 
				System.out.println("Assinatura  da NF-e " + idNfseS + " correta.");				
			} 
						
			OutputFormat format    = new OutputFormat(doc); 
			StringWriter stringOut = new StringWriter ();    
		    XMLSerializer serial   = new XMLSerializer (stringOut,format);
		    serial.serialize(doc);
		    
		    nfseDadosMsg = stringOut.toString();

		    System.out.println("Assinatura Lote: " + nfseDadosMsg);
		    
		    System.out.println("Caminho lote assinado"+KscConstante.DIRETORIO_PROJETO+"/RPS/loteAssinado.xml");
		    FileWriter writerRet = new FileWriter(new File(KscConstante.DIRETORIO_PROJETO+"/RPS/loteAssinado.xml"));
			
		    PrintWriter saidaRet = new PrintWriter(writerRet); 
			saidaRet.print(nfseDadosMsg);
			saidaRet.close();  
			writerRet.close();
			
			TransformerFactory tf = TransformerFactory.newInstance(); 
			Transformer trans = tf.newTransformer(); 
			
			//TransformerFactory tf2 = TransformerFactory.newInstance(); 
			//Transformer trans2 = tf2.newTransformer(); 
			trans.transform(new DOMSource(doc), new StreamResult(os2)); 

			 
//			EnviarXML enviarXml = new EnviarXML();
//			enviarXml.enviarXml(Utilitario.pegaDiretorioXml2() + Utilitario.pegaPrefixoArquivosEnvio() + idRps + ".xml");
			
		} catch (Exception e){
			e.printStackTrace();	
		}
		
		return os2.toString();

	}

	public static String funcStatusCertificado(X509Certificate crtCertificado) {   
		  
        try {   
            crtCertificado.checkValidity();   
            return "Certificado válido!";   
        } catch (CertificateExpiredException E) {   
            return "Certificado expirado!";   
        } catch (CertificateNotYetValidException E) {   
            return "Certificado inválido!";   
        }   
  
    }   
	
	

	
}
