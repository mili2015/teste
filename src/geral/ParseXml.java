package geral;
import java.io.File;
import java.io.IOException;

import javax.mail.FetchProfile.Item;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controller.Contato;
import controller.Endereco;

public class ParseXml
{

	public static void listaElementos()
	{

		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("teste.xml");
			Node docElement = doc.getDocumentElement();
			// retorna todos os filhos de docElement   
			NodeList root = docElement.getChildNodes();
			// chama recursivamente o metodo que printa os filhos   
			recuperaFilhos(root);

		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void recuperaFilhos(NodeList root)
	{
		Node node;
		for (int i = 0; i < root.getLength(); i++)
		{
			node = root.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				String nodeName = node.getNodeName();
				System.out.print("nome:" + nodeName);

				//if (node.hasAttributes())
				if(node.hasChildNodes())
				{
					Element el = (Element) node;
					//System.out.println("size: "+el.getAttribute("size"));
					//int size = Integer.parseInt(el.getAttribute("size"));
					//String type = el.getAttribute("type");
					
					//se tem valor
					if(el.getFirstChild().getNodeValue()!=null && !el.getFirstChild().getNodeValue().trim().equals(""))
					{
						System.out.print("\tvalor:" + el.getFirstChild().getNodeValue()+"\n");
					}
					else //se não tem valor, tem filhos
					{
						System.out.println(" ===========================");
					}
					
					//if(el.hasChildNodes())
					//{
						NodeList filhos = node.getChildNodes();
						recuperaFilhos(filhos);
					//}
					///System.out.print(" size:" + size);
					//System.out.println(" type:" + type);
				}
				else
				{
					System.out.println(" tem mais filhos");
					NodeList filhos = node.getChildNodes();
					recuperaFilhos(filhos);
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
//		ParseXml.listaElementos();

		try
		{
//			JAXBContext jaxbContext = JAXBContext.newInstance("teste.geral");
//			Unmarshaller unm =  jaxbContext.createUnmarshaller();
//			File file = new File("C:\\PL_DPEC_101aHom\\envDPEC_v1.01.xsd");
//			Item item = (Item) unm.unmarshal(file);
			
			Endereco endereco = new Endereco("Rua Moraes", 40, "Centro",
					"Rio de Janeiro", "20000-000");
					Contato contato = new Contato("Marco Maciel", "M",
					34, endereco);

					JAXBContext context = JAXBContext.newInstance("control");

					// saída 1 – console

					Marshaller m = context.createMarshaller();
			//
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//
					m.marshal(contato, System.out);
		}
		catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
