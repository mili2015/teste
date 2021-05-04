package model;

public class HtmlEmail
{
	private String conteudoEmail;
	private String emailUsuario;
	
	public String getEmailUsuario() 
	{
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) 
	{
		this.emailUsuario = emailUsuario;
	}

	public String getConteudoEmail() 
	{
		return conteudoEmail;
	}

	public void setDeletadoEmail(String funcionarios, String nomeCurso) 
	{
		this.conteudoEmail = "" +
				" <html>" +
				"	<head>" +
				"		<h3><u>SISTEMA DE GERENCIAMENTO DE CAPACITA��ES T�CNICAS</u></h3>" +
				"	</head>" +
				"	<body>" +
				"		<p>" +
				"		<br>O funcion�rio <b>"+funcionarios+"</b> n�o foi matriculado para o treinamento <b>"+nomeCurso+"</b></br>" +
				"		</p>" +
				"	</body>" +
				"</html>";
	}
	
	public void setDeletadosEmail(String funcionarios, String nomeCurso) 
	{
		this.conteudoEmail = "" +
				" <html>" +
				"	<head>" +
				"		<h3><u>SISTEMA DE GERENCIAMENTO DE CAPACITA��ES T�CNICAS</u></h3>" +
				"	</head>" +
				"	<body>" +
				"		<p>" +
				"		<br>Os funcion�rios <b>"+funcionarios+"</b> n�o foram matriculados para o treinamento <b>"+nomeCurso+"</b></br>" +
				"		</p>" +
				"	</body>" +
				"</html>";
	}
	
	public void setMatriculadoEmail(String funcionarios, String nomeCurso) 
	{
		this.conteudoEmail = "" +
				" <html>" +
				"	<head>" +
				"		<h3><u>SISTEMA DE GERENCIAMENTO DE CAPACITA��ES T�CNICAS</u></h3>" +
				"	</head>" +
				"	<body>" +
				"		<p>" +
				"		<br>Foi confirmado a matricula do funcion�rio <b>"+funcionarios+"</b> para o treinamento <b>"+nomeCurso+"</b></br>" +
				"		</p>" +
				"	</body>" +
				"</html>";
	}
	
	public void setMatriculadosEmail(String funcionarios, String nomeCurso) 
	{
		this.conteudoEmail = "" +
				" <html>" +
				"	<head>" +
				"		<h3><u>SISTEMA DE GERENCIAMENTO DE CAPACITA��ES T�CNICAS</u></h3>" +
				"	</head>" +
				"	<body>" +
				"		<p>" +
				"		<br>Foram confirmadas as matriculas dos funcion�rios <b>"+funcionarios+"</b> para o treinamento <b>"+nomeCurso+"</b></br>" +
				"		</p>" +
				"	</body>" +
				"</html>";
	}
	
	public void setCanceladoEmail(String funcionarios, String nomeCurso) 
	{
		this.conteudoEmail = "" +
				" <html>" +
				"	<head>" +
				"		<h3><u>SISTEMA DE GERENCIAMENTO DE CAPACITA��ES T�CNICAS</u></h3>" +
				"	</head>" +
				"	<body>" +
				"		<p>" +
				"		<br>O funcion�rio <b>"+funcionarios+"</b> n�o foi matriculado, pois o treinamento <b>"+nomeCurso+"</b> foi cancelado.</br>" +
				"		</p>" +
				"	</body>" +
				"</html>";
	}
	
	public void setCanceladosEmail(String funcionarios, String nomeCurso) 
	{
		this.conteudoEmail = "" +
				" <html>" +
				"	<head>" +
				"		<h3><u>SISTEMA DE GERENCIAMENTO DE CAPACITA��ES T�CNICAS</u></h3>" +
				"	</head>" +
				"	<body>" +
				"		<p>" +
				"		<br>Os funcion�rios <b>"+funcionarios+"</b> n�o foram matriculados, pois o treinamento <b>"+nomeCurso+"</b> foi cancelado.</br>" +
				"		</p>" +
				"	</body>" +
				"</html>";
	}
	
	public void getAvisoTurmaAberta(String nomeCurso)
	{
		this.conteudoEmail = "" +
		" <html>" +
		"	<head>" +
		"		<h3><u>SISTEMA DE GERENCIAMENTO DE CAPACITA��ES T�CNICAS</u></h3>" +
		"	</head>" +
		"	<body>" +
		"		<p>" +
		"		<br>Est� aberta as inscri��es para o treinamento <b>"+nomeCurso+"</b>.</br>" +
		"		<br>Para mais informa��es acesse o sistema <b>SGCT</b> atrav�s da <a href=www.pesa.com.br\\intranet target=_blank>Intranet PESA</a>.</br> " +		
		"		</p>" +
		"	</body>" +
		"</html>";		
	}
	
	
}
