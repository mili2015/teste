package xsl;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

public class HowToXSLT
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		 try {

			    TransformerFactory tFactory = TransformerFactory.newInstance();

			    Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource("howtonfe.xsl"));

			    transformer.transform
			      (new javax.xml.transform.stream.StreamSource
			            ("NFe.xml"),
			       new javax.xml.transform.stream.StreamResult
			            ( new FileOutputStream("howtoNFe.html")));
			    }
			  catch (Exception e) {
			    e.printStackTrace( );
			    }


	}

}
