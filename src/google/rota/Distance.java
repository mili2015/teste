package google.rota;

public class Distance
{
	private String text;
	private String value;
	/**
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
	}
	/**
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public double valueToDouble()
	{
		if(value == null)
			return 0d;
		
		if(value.length() >= 3)
		{
			//add um ponto antes das 2 casas finais
			StringBuilder sb = new StringBuilder(value);
			sb.insert(value.length() - 3, ".");
			value = sb.toString();
		}
		
		return Double.parseDouble(value);
	}
	
}
