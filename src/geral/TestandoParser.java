package geral;
import java.io.File;
import java.util.Iterator;

import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.filter.ContentFilter;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


public class TestandoParser
{
	public void parseando(String xml) 
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse(new File(xml));
			
			SAXBuilder sb = new SAXBuilder();
			org.jdom.Document doc = sb.build(new File(xml));
			limparTags(doc.getRootElement());
			
			
			Format format = new XMLOutputter().getFormat(); 
			 //Imptrimindo o XML   
//			XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
			XMLOutputter xout = new XMLOutputter();
			
			xout.output(doc, System.out);  

			
			@SuppressWarnings("unused")
			NodeList elements=document.getElementsByTagName("ide");
			
			
			for(int a=0;a< elements.getLength();a++)
			{
				Element element = (Element)elements.item(a);
				NamedNodeMap map = element.getAttributes();
				//System.out.println("nó: "+element.getNodeName());
				for(int b=0;b<map.getLength();b++)
				{
				//	System.out.println("Atributos: "+(map.item(b)).getNodeName()+" valor: "+(map.item(b)).getNodeValue());
				}
			}
			
			XMLSignatureFactory sigantureFactory = XMLSignatureFactory.getInstance("DOM");
			TransformParameterSpec tps = null;
			Transform envelopedTransform = sigantureFactory.newTransform(Transform.ENVELOPED,tps);
			Transform c14NTransform = sigantureFactory.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps);
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private org.jdom.Element limparTags(org.jdom.Element element)
	{

		org.jdom.Element removeElement = element;

		ContentFilter filter = new ContentFilter();
		filter.setTextVisible(false);

		Iterator<Element> iterator = element.getDescendants(filter);

		while (iterator.hasNext())
		{
			org.jdom.Element el = (org.jdom.Element) iterator.next();

			if (el.getChildren().size() > 0)
			{
				removeElement = el;
			}
			else
			{
				int valor = 99;
				try
				{
					valor = Integer.parseInt(el.getTextTrim());
				}
				catch(Exception e){}
				
				//elemina os elementos que não tiver valor
				if (el.getTextTrim().equals("") || valor == 0)
				{
					iterator.remove();
					removeElement.removeContent(el);
				}
			}
		}
		System.out.println("element: "+element);
		return element;
	}  

	
	public static void main(String[] args)
	{
		TestandoParser tp = new TestandoParser();
		tp.parseando("C:\\Documents and Settings\\Juliano\\Desktop\\NFe\\4306039266561101315555099000006994000533424-nfe.xml");
	}
	
}
