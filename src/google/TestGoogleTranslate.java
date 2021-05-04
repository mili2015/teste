package google;

import java.io.IOException;
import java.io.InputStream;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import com.gtranslate.Translator;

public class TestGoogleTranslate
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		
		Translator translate = Translator.getInstance();
		
		String cardapio = "Camaroes refogados com molho de tomates cebola e coentro acrescidos de leite de coco e azeite de dendê Acompanha arroz branco e farofa de dendê";
		String cardapio2 = "Camarões refogados com molho de tomates cebola e coentro acrescidos de leite de coco e azeite de dendê Acompanha arroz branco e farofa de dendê";
		String cardapio3 = "Felipe Camarões refogados com molho de tomates cebola e coentro acrescidos de leite de coco e azeite de dendê Acompanha arroz branco e farofa de dendê";
		
		
//		String aux = cardapio + " | " + cardapio2 + " | "+ cardapio3+" | Felipe Vieira";
		String aux = "Combinado de Sushi e Sashimi - 52 unidades - 20 sashimis - 12 sushis - 8 californias - 12 hossomakis";
		
		String text = translate.translate(aux, Language.PORTUGUESE, Language.ENGLISH);
		System.out.println(text);
		
		
//		String text1 = translate.translate(cardapio2, Language.PORTUGUESE, Language.ENGLISH);
//		System.out.println(text1);
		
		
		Audio audio = Audio.getInstance();
		InputStream sound;
		
		try
		{
//			sound = audio.getAudio(cardapio, Language.PORTUGUESE);
//			audio.play(sound);
			
			String [] palavras = cardapio2.split(" ");
			String p ="";
			
			for (int i = 0; i < palavras.length; i++)
			{
				p += palavras[i]+" ";
				if( i % 10 == 0 && i > 0 )
				{
					System.out.println(i);
//					sound = audio.getAudio(p, Language.PORTUGUESE);
//					audio.play(sound);
					p = "";
				}
			}
			
//			sound = audio.getAudio(p, Language.PORTUGUESE);
//			audio.play(sound);
			
//			System.out.println(palavras.length);
			
//			sound = audio.getAudio(cardapio2, Language.PORTUGUESE);
//			audio.play(sound);
			
//			
			
			
		/*	System.out.println("<f:selectItem itemValue=\""+Language.ARABIC+"\" itemLabel=\"Arabic\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.BULGARIAN+"\" itemLabel=\"Bulgarian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.BASQUE+"\" itemLabel=\"Basque\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.CATALAN+"\" itemLabel=\"Catalan\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.CHINESE_SIMPLIFIED+"\" itemLabel=\"Chinese Simplified\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.CHINESE_TRADITIONAL+"\" itemLabel=\"Chinese Tradittional\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.CHINESE_TRADITIONAL+"\" itemLabel=\"Chinese Tradittional\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.CROATIAN+"\" itemLabel=\"Croatian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.CZECH+"\" itemLabel=\"Czech\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.DANISH+"\" itemLabel=\"Danish\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.DUTCH+"\" itemLabel=\"Dutch\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.FILIPINO+"\" itemLabel=\"Filipino\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.GEORGIAN+"\" itemLabel=\"Georgian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.GERMAN+"\" itemLabel=\"German\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.GREEK+"\" itemLabel=\"Greek\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.HAITIAN_CREOLE+"\" itemLabel=\"Haitian Creole\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.HEBREW+"\" itemLabel=\"Hebrew\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.HINDI+"\" itemLabel=\"Hindi\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.HUNGARIAN+"\" itemLabel=\"Hungarian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.INDONESIAN+"\" itemLabel=\"Indonesian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.IRISH+"\" itemLabel=\"Irish\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.ITALIAN+"\" itemLabel=\"Italian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.JAPANESE+"\" itemLabel=\"Japanese\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.KOREAN+"\" itemLabel=\"Korean\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.LATIN+"\" itemLabel=\"Latin\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.MACEDONIAN+"\" itemLabel=\"Macedonian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.NORWEGIAN+"\" itemLabel=\"Norwegian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.PERSIAN+"\" itemLabel=\"Persian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.POLISH+"\" itemLabel=\"Polish\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.ROMANIAN+"\" itemLabel=\"Romanian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.RUSSIAN+"\" itemLabel=\"Russian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.SLOVAK+"\" itemLabel=\"Slovak\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.SLOVENIAN+"\" itemLabel=\"Slovenian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.SWEDISH+"\" itemLabel=\"Swedish\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.TURKISH+"\" itemLabel=\"Turkish\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.UKRAINIAN+"\" itemLabel=\"Ukrainian\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.VIETNAMESE+"\" itemLabel=\"Vietnamese\" />");
			System.out.println("<f:selectItem itemValue=\""+Language.WELSH+"\" itemLabel=\"Welsh\" />");*/
			
			
			
			
			
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
