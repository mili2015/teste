package controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.Empresa;
import model.InfProt;
import model.Pessoa;
import model.ProtNFe;
import model.RetConsReciNFe;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class XMLControl
{
	public static void main(String[] args)
	{
////		testeParser();
		testeXtream();
		
	}
	
	//method to convert Document to String
	public static String getStringFromDocument()
	{
	    try
	    {
	    Document doc=obtemDocument("nfeRefeitaLote.xml");
	       DOMSource domSource = new DOMSource(doc);
	       StringWriter writer = new StringWriter();
	       StreamResult result = new StreamResult(writer);
	       TransformerFactory tf = TransformerFactory.newInstance();
	       Transformer transformer = tf.newTransformer();
	       transformer.transform(domSource, result);
	       return writer.toString();
	    }
	    catch(TransformerException ex)
	    {
	       ex.printStackTrace();
	       return null;
	    }
	} 

	private static Document obtemDocument(String caminhoXML) 
	{
		File xmlFile = new File(caminhoXML); //onde src é uma string
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//dbf.setValidating(true);//para validar DTD/Schema do xml
		dbf.setNamespaceAware(true);
		//parse o doc xml usando o DOM
		DocumentBuilder db;
		Document document =null;
		try
		{
			db = dbf.newDocumentBuilder();
			document = db.parse(xmlFile);
		}
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e)
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
	
	private static void testeParser()
	{
		File xmlFile = new File("ped-rec.xml"); //onde src é uma string
		String err = null ;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		dbf.setValidating(true);//para validar DTD/Schema do xml
		dbf.setNamespaceAware(true);
		DocumentBuilder db;
		try
		{
			db = dbf.newDocumentBuilder();
			
			Document d = db.parse(xmlFile);

			Object doc = d;
			
			if (doc instanceof Document)
			{
				Document new_name = (Document) doc;
				System.out.println("if: "+doc);
			}
			else if (doc instanceof String)
			{
				String new_name = (String) doc;
				System.out.println("else: "+doc);
				
			}
		}
		catch (ParserConfigurationException e)
		{
			System.out.println("erro ParserConfigurationException");
			err = e.toString();
		}
		catch(SAXParseException spe)
		{
			 StringBuffer sb = new StringBuffer( spe.toString() );
		        sb.append("\n  Line number: " + spe.getLineNumber());
		        sb.append("\nColumn number: " + spe.getColumnNumber() );
		        sb.append("\n Public ID: " + spe.getPublicId() );
		        sb.append("\n System ID: " + spe.getSystemId() + "\n");
		        err = sb.toString();

		}
		catch (SAXException e)
		{
			System.out.println("erro SAXException");
			 err = e.toString();
		       if( e.getException() != null )
		       {
		          err += " caused by: " + e.getException().toString() ;
		       }
		}
		catch (IOException e)
		{
			err = e.toString(); 
		}
		System.out.println(err);
	}
	

	private static void testeXtream()
	{
		/*Pessoa p1 = new Pessoa();
		p1.setIdade(21);
		p1.setCpf("1234");
		p1.setNome("a");
		
		Pessoa p2 = new Pessoa();
		p2.setIdade(22);
		p2.setCpf("1234");
		p2.setNome("b");
		
		Pessoa p3 = new Pessoa();
		p3.setIdade(23);
		p3.setCpf("1234");
		p3.setNome("c");
		
		Pessoa p4 = new Pessoa();
		p4.setIdade(24);
		p4.setCpf("1234");
		p4.setNome("d");
		
		Empresa empresa = new Empresa("","");
		empresa.setCidade("Curitiba");
		empresa.setEndereco("Tamoios , 457 ap 44");
		empresa.setNome("Felipe S/A");
		empresa.setTelefone("8065");
//		empresa.getFuncionarios().add(p1);
//		empresa.getFuncionarios().add(p2);
//		empresa.getFuncionarios().add(p3);
//		empresa.getFuncionarios().add(p4);
		
		Empresa empresa2 = new Empresa("","");
		empresa2.setCidade("Curitiba");
		empresa2.setEndereco("Tamoios , 457 ap 44");
		empresa2.setNome("Felipe S/A");
		empresa2.setTelefone("8065");
//		empresa2.getFuncionarios().add(p1);
//		empresa2.getFuncionarios().add(p2);
//		empresa2.getFuncionarios().add(p3);
//		empresa2.getFuncionarios().add(p4);
		
		
		
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas.add(empresa);
		empresas.add(empresa2);
		
		XStream x = new XStream();
		
		Class [] classes = {RetConsReciNFe.class,ProtNFe.class,InfProt.class};
		x.processAnnotations(classes);

//		x.alias("empresa", Empresa.class);
//		x.alias("funcionario", Pessoa.class);
		
//		x.alias("retConsReciNFe", RetConsReciNFe.class);
//		x.alias("protNfe", List.class);
//		x.alias("infProt", InfProt.class);
		
//		String empress = x.toXML(empresas);
//		System.out.println(empress);
		
		
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		try
		{
//			bStream.write(xml.getBytes());

			File file = new File("usuarios.xml");
//			bStream.writeTo(new FileOutputStream(file));
//			bStream.flush();
//			bStream.close();

			String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><retConsReciNFe versao=\"1.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><verAplic>v3313</verAplic><nRec>410000002294859</nRec><cStat>104</cStat><xMotivo>Lote processadoLote processado, nenhuma nota sera carregada</xMotivo><cUF>41</cUF><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807300\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401470000000011</chNFe><dhRecbto>2009-04-23T14:17:33</dhRecbto><nProt>141090000807300</nProt><digVal>dZuAUELQoaLcz75iH8+EWaN4gCk=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807301\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401480000000019</chNFe><dhRecbto>2009-04-23T14:17:33</dhRecbto><nProt>141090000807301</nProt><digVal>dKZl3YMGH4+pqQElnAkjpiiVpDw=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807296\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401490000000016</chNFe><dhRecbto>2009-04-23T14:17:32</dhRecbto><nProt>141090000807296</nProt><digVal>YCl27zkQN3SrZxtAaJrBIT4uTn4=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807305\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401500000000017</chNFe><dhRecbto>2009-04-23T14:17:33</dhRecbto><nProt>141090000807305</nProt><digVal>Qpu05eL1QuLPua4r3Gws12B2HQA=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807299\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401510000000014</chNFe><dhRecbto>2009-04-23T14:17:33</dhRecbto><nProt>141090000807299</nProt><digVal>ZPxgl6nQO9TPW4rJ1T5Q11V7VwM=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807306\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401520000000011</chNFe><dhRecbto>2009-04-23T14:17:33</dhRecbto><nProt>141090000807306</nProt><digVal>qlVocC0JFfIoYOpmgW1FywcBoeQ=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807297\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401530000000019</chNFe><dhRecbto>2009-04-23T14:17:32</dhRecbto><nProt>141090000807297</nProt><digVal>WP2N43uYyPHAjr/iYY68fU06Ey0=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807298\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401540000000016</chNFe><dhRecbto>2009-04-23T14:17:32</dhRecbto><nProt>141090000807298</nProt><digVal>QK1FyW1ruZ9NajEMRc7Y+znLDoA=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807302\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401550000000013</chNFe><dhRecbto>2009-04-23T14:17:33</dhRecbto><nProt>141090000807302</nProt><digVal>CFXskcpmqcyvp6eRQVg2ETeWThM=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807303\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401560000000010</chNFe><dhRecbto>2009-04-23T14:17:33</dhRecbto><nProt>141090000807303</nProt><digVal>UAVYzaoxzvE47/C+xLCmKXxK2tw=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe><protNFe versao=\"1.10\"><infProt Id=\"ID141090000807304\"><verAplic>v3313</verAplic><chNFe>41081278908266000477550000003401570000000018</chNFe><dhRecbto>2009-04-23T14:17:33</dhRecbto><nProt>141090000807304</nProt><digVal>erzARTE3EjQBR1ainmlNMGvJO70=</digVal><cStat>290</cStat><xMotivo>Rejeição: Certificado Assinatura inválido</xMotivo></infProt></protNFe></retConsReciNFe>";

			InputStream in =new FileInputStream(file);
			Object obj = x.fromXML(xml);
//			Object obj = x.fromXML(in);
			System.out.println(x.toXML(obj));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	private static void gerarXmlJAXB()
	{
		Pessoa p = null;
		p.setIdade(22);
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Pessoa.class);
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.marshal(p, new FileOutputStream(new File("XMLPessoa.xml")));
		}
		catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
