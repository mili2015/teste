package geral;
import java.io.File; 
import java.io.IOException; 
import java.util.*; 
import javax.xml.parsers.*; 
import org.w3c.dom.*; 
import org.xml.sax.SAXException; 

public class XmlReader 
{ 

    public XmlReader(String arqXml) 
    { 
        tagsParaDownload = new ArrayList(); 
        files = new ArrayList(); 
        String tags[] = "feature,plugin".split(","); 
        for(int i = 0; i < tags.length; i++) 
            tagsParaDownload.add(tags[i]); 

        File f = new File(arqXml); 
        loadFiles(readDoc(f)); 
    } 

    private Document readDoc(File file) 
    { 
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); 
        DocumentBuilder docBuilder = null; 
        Document doc = null; 
        try 
        { 
            docBuilder = docFactory.newDocumentBuilder(); 
            doc = docBuilder.parse(file); 
        } 
        catch(ParserConfigurationException e) 
        { 
            e.printStackTrace(); 
        } 
        catch(SAXException e) 
        { 
            e.printStackTrace(); 
        } 
        catch(IOException e) 
        { 
            e.printStackTrace(); 
        } 
        return doc; 
    } 

    private void loadFiles(Document doc) 
    { 
        Node raiz = doc.getFirstChild(); 
        NodeList features = raiz.getChildNodes(); 
        for(Iterator it = tagsParaDownload.iterator(); it.hasNext();) 
        { 
            String nomeTag = (String)it.next();
            
            for(int i = 0; i < features.getLength(); i++)
            {
            	System.out.println("name: "+features.item(i).getNodeName());
                if(nomeTag.equals(features.item(i).getNodeName()))
                {
                    files.add(features.item(i).getAttributes().getNamedItem("url").getNodeValue());
                    System.out.println("algo: "+features.item(i).getAttributes().getNamedItem("url").getNodeValue());
                }
            }
        } 

    } 

    public List getFiles() 
    { 
        return files; 
    } 

    List tagsParaDownload; 
    List files; 
    
    public static void main(String[] args)
    {
    	new XmlReader("teste.xml");
    }
} 