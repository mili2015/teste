package google;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gdata.client.AuthTokenFactory.AuthToken;
import com.google.gdata.client.GoogleAuthTokenFactory.UserToken;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.TagEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class TestPicasa
{

	/**
	 * @param args
	 */
	static String token = "";

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		PicasawebService service = new PicasawebService("Felipe");
		try
		{
			String user = autenticar(service);
			
			PicasawebService pToken = new PicasawebService("Felipe");
			pToken.setUserToken(token);
			
			AlbumEntry albumReleased = criarNovoAlbum(service,user);
			
//			AlbumEntry album = pesquisarAlbum(service, user, "Novo álbum");
//			alterarAlbum(album);
//			excluirAlbum(album);
//			listarPhotosAlbum(service, user, "5689406770721685169");
//			listarTagsAlbum(service, user, "5689406770721685169");
//			listarRecentPhotos(service, user);
			
			insertPhoto(service, user, "c:\\NFE\\Lighthouse.jpg",albumReleased.getGphotoId());
			
			listarAlbuns(pToken, user);
			
			
			
			
		}
		catch (AuthenticationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @param album
	 * @throws IOException
	 * @throws ServiceException
	 */
	private static void alterarAlbum(AlbumEntry album) throws IOException, ServiceException
	{
		if(album != null)
		{
			album.setTitle(new PlainTextConstruct("Novo álbum alterado!"));
			
			album.update();
		}
	}
	
	private static void excluirAlbum(AlbumEntry album) throws IOException, ServiceException
	{
		if(album != null)
		{			
			album.delete();
		}
	}

	/**
	 * @param service
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	private static AlbumEntry criarNovoAlbum(PicasawebService service,String user) throws MalformedURLException, IOException, ServiceException
	{
		AlbumEntry myAlbum = new AlbumEntry();
		myAlbum.setTitle(new PlainTextConstruct("Novo álbum UserName"));
		myAlbum.setDescription(new PlainTextConstruct("Novo álbum criado pelo Picasa Web Albums Data API"));
		
		URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/"+user);
		AlbumEntry albumReleased = service.insert(feedUrl, myAlbum);
		return albumReleased;
	}

	/**
	 * @param service
	 * @param user
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	private static void listarAlbuns(PicasawebService service, String user) throws MalformedURLException, IOException, ServiceException
	{
		URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/"+user+"?kind=album");
		UserFeed feed = service.getFeed(feedUrl, UserFeed.class);
		
		for(AlbumEntry ae : feed.getAlbumEntries())
		{
			System.out.println(ae.getTitle().getPlainText()+" [ "+ae.getPhotosUsed()+" | "+ae.getGphotoId() +" | "+ae.getUsername()+" | "+ae.getDescription().getPlainText()+" | " +ae.getMediaContents().get(0).getUrl()+" ]");
			System.out.println();
//			listarPhotosAlbum(service, user, ae.getId());
		}
	}
	
	
	private static void listarPhotosAlbum(PicasawebService service, String user,String albumId) throws MalformedURLException, IOException, ServiceException
	{
		URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/"+user+"/albumid/"+albumId);
		AlbumFeed feed = service.getFeed(feedUrl, AlbumFeed.class);
		
		for(PhotoEntry ae : feed.getPhotoEntries())
		{
			System.out.println( ae.getGphotoId()+" - "+ae.getTitle().getPlainText()+" - "+ae.getMediaContents().get(0).getUrl());
//			System.out.println( ae.getMediaThumbnails().get(0).getUrl());
		}
	}
	
	private static void listarTagsAlbum(PicasawebService service, String user,String albumId) throws MalformedURLException, IOException, ServiceException
	{
		URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/"+user+"/albumid/"+albumId+"?kind=tag");
		AlbumFeed feed = service.getFeed(feedUrl, AlbumFeed.class);
		
		System.out.println(feed.getTagEntries());
		
		for(TagEntry ae : feed.getTagEntries())
		{
			System.out.println(ae.getTitle().getPlainText());
		}
	}
	
	private static void listarRecentPhotos(PicasawebService service, String user) throws MalformedURLException, IOException, ServiceException
	{
		URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/"+user+"?kind=photo");
		AlbumFeed feed = service.getFeed(feedUrl, AlbumFeed.class);
		
		System.out.println(feed.getPhotosLeft());
		
		for(PhotoEntry ae : feed.getPhotoEntries())
		{
			System.out.println(ae.getTitle().getPlainText()+" - "+ae.getMediaContents().get(0).getUrl());
		}
	}
	
	
	private static AlbumEntry pesquisarAlbum(PicasawebService service, String user,String name) throws MalformedURLException, IOException, ServiceException
	{
		URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/"+user+"?kind=album");
		UserFeed feed = service.getFeed(feedUrl, UserFeed.class);
		
		AlbumEntry entry = null;
		
		for(AlbumEntry ae : feed.getAlbumEntries())
		{
			
			if(ae.getTitle().getPlainText().contains(name))
			{
				return ae;
			}
			entry = ae;
		}
		return entry;
	}


	//5689406770721685169
	private static void insertPhoto(PicasawebService service, String user,String caminho,String albumId) throws MalformedURLException, IOException, ServiceException
	{
		URL postUrl = new URL("http://picasaweb.google.com/data/feed/api/user/"+user+"/albumid/"+albumId);
		
//		PhotoEntry photo = new PhotoEntry();
		
		MediaFileSource media = new MediaFileSource(new File(caminho), "image/jpeg");
		
		service.insert(postUrl, PhotoEntry.class,media);
	}
	
	
	
	/**
	 * @param service
	 * @return
	 * @throws AuthenticationException
	 */
	private static String autenticar(PicasawebService service) throws AuthenticationException
	{
		String user = "meuportaldocondominio";
		service.setUserCredentials(user, "mpdc2013");
		
		UserToken userToken = (UserToken) service.getAuthTokenFactory().getAuthToken();
		
		System.out.println("Token "+userToken.getValue());

		token = userToken.getValue();
		
		return user;
	}

}
