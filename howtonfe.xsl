<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:template match="/">
		<html>
			<head>
				<title>nf-e nota fiscal eletrônica</title>
				<meta content="text/html; charset=iso-8859-1" http-equiv="content-type"/>
					<link rel="stylesheet" type="text/css" href="portal.css"/>
						<script language="javascript" src="validacao.js"></script>

						<script language="javascript" src="utils.js"></script>
			</head>
			<body leftmargin="0" topmargin="0" bgcolor="#ffffff" marginheight="0" marginwidth="0">
				<form onsubmit="return false" method="post" name="form1">

					<table border="0" cellspacing="0" cellpadding="0" width="650" align="center">
						<tbody>
							<tr>
								<td class="conteudo">

									<script language="javascript">
										var ultlink=null, ultsessao=null;

										function voltarorigem(){
										if(jacarregou()) voltar();
										else alert("aguarde até que a página esteja carregada.");
										}

										//mostra uma sessão (aba)
										// a->aba objeto
										
											// n->número da aba
											function mostrasessao(n){
											var estelink=document.getelementbyid("linkaba"+n);
											if(estelink != null){
											if(ultlink != null){
											ultlink.style.fontweight="normal";
											ultlink.style.color=estelink.style.color;
											}
											estelink.style.color="#000000";
											estelink.style.fontweight="bold";
											ultlink = estelink;

											if(ultsessao != null) ultsessao.style.display="none";
											ultsessao = document.getelementbyid("sessao"+n);
											ultsessao.style.display = "block";
											}
											}

											function mostraitem(n){
											var disp = "block";
											objitem = document.getelementbyid("detitem"+n);
											if(objitem != null){
											if(objitem.style.display == "block") disp="none";
											}
											objitem.style.display = disp;
											}
											
											function impautorizacao(){
											window.open("/nfen/impressao/autorizacao.asp?tpamb="+1,"_imp_aut_","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=740,height=600,top=0,left=0");
											//window.open("impressao/autorizacao.asp");
											}
									</script>

									<table border="0" cellspacing="1" cellpadding="4" width="100%">
										<tbody>
											<tr>
												<td class="cabecalho" colspan="2">
													<table border="0" cellspacing="0" cellpadding="0" width="100%">
														<tbody>
															<tr>
																<td class="cabecalho" align="left">consulta de nf-e</td>
																<td class="cabecalho" align="right">
																	<span id="_carregando"></span>
																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td class="rotulo" valign="top" align="left">
													<b>chave de acesso</b>
												</td>
												<td class="rotulo" valign="top" align="left">
													<b>versão do xml</b>
												</td>
											</tr>
											<tr>
												<td class="conteudo" valign="top" align="left"><xsl:value-of select="nfeProc/protNFe/infProt/chNFe"/></td>
												<td class="conteudo" valign="top" align="left"><xsl:value-of select="nfeProc/protNFe/@versao"/>1.10</td>
											</tr>
										</tbody>
									</table>
																			<!--
											<table width="100%" border="0" cellspacing="1"
											cellpadding="4"> <tr> <td class="realce" colspan="3"
											align="center"><b>observações do fisco?????<b></td> </tr>
											</table>
										-->
									<table border="0" cellspacing="1" cellpadding="2" width="100%">
										<tbody>
											<tr>
												<td class="botoes">
													<input onclick="impautorizacao()" value="imprimir autorização de uso"
														type="button"/>
														<input onclick="window.close()" value="fechar" type="button"/>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>