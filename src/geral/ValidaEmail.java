package geral;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ValidaEmail
{
	public void validar(String email)
	{
		Pattern p = Pattern.compile("[^0-9]");    
		
		Matcher m = p.matcher(email.trim());
		
		boolean validado = m.matches();
		
		if(validado)
		{
			System.out.println("validaddo");
		}
		else
		{
			System.out.println("não validado");
		}
	}
	
	public static void main(String[] args)
	{
		ValidaEmail v = new ValidaEmail();
		v.validar("1231");
	}
}
