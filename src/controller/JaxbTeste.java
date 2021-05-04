package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class JaxbTeste
{
	public static void main(String args[]) throws JAXBException, SAXException, ParserConfigurationException, IOException
	{

		/*Endereco endereco = new Endereco("Rua Moraes", 40, "Centro",
		"Rio de Janeiro", "20000-000");
		Contato contato = new Contato("Marco Maciel", "M",
		34, endereco);

		JAXBContext context = JAXBContext.newInstance("control");

		// saída 1 – console

		Marshaller m = context.createMarshaller();*/
		
		
		
//
//		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
//		m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "http://www.w3.org/2001/XMLSchema");
		
		
//
//		m.marshal(contato, System.out);
//
//		// saída 2 – arquivo
//
		JAXBContext jaxbContext = JAXBContext.newInstance(Contato.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		unmarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "http://www.w3.org/2001/XMLSchema");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       dbf.setNamespaceAware(true);
       DocumentBuilder db = dbf.newDocumentBuilder();
       Document doc = db.parse(new File("src/contato2.xml"));
		
       Contato c = (Contato) unmarshaller.unmarshal(doc);
		
		System.out.println(c.getNome());
//
		//
//				Contato contato2 = (Contato) obj;      
		//
//				System.out.println(contato2.getNome()+" "+contato2.getSexo());
		
//      SAXParserFactory parserFactory;   
//      parserFactory = SAXParserFactory.newInstance();   
//      parserFactory.setNamespaceAware(false);   
//      XMLReader reader = parserFactory.newSAXParser().getXMLReader();
//      SAXSource
//      Source er = new SAXSource(reader, new InputSource(new StringReader("")));   
//      Source er = new SAXSource();
//      return getUnmarshaller(contextClazz).unmarshal(er, contextClazz);  

//		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//		Schema dataSchema = sf.newSchema(new File("C:\\PL_DPEC_101aHom\\envDPEC_v1.01.xsd"));
//		
		/*Unmarshaller um = context.createUnmarshaller();
//
		um.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,"http://www.w3.org/2001/XMLSchema envDPEC_v1.01.xsd");*/
//		um.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

//		um.setSchema(dataSchema);
//		Object obj = um.unmarshal(new File("C:\\PL_DPEC_101aHom\\envDPEC_v1.01.xsd"));

		
		/**/

//		dataMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
//				"http://www.example.com/2007/message data1.xsd");
//		dataMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
//				Boolean.TRUE);
//		dataMarshaller.setSchema(dataSchema);
//		dataMarshaller.marshal(dataElement1, doc);

		
		
//		

//		

		

	}

}
