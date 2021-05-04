/******************************************************************************
/******************************************************************************
** Funcoes genericas para validacao                                          **
** Com VerificaENTER e VerificaLimpar inclusos                               **
******************************************************************************/

/******************************************************************************
** Funcoes auxiliares                                                        **
******************************************************************************/
// Retorna so numeros da string
function SoNumeros( Campo )
{
   return Campo.value.replace( /\D/g, "" );
}

// Retorna so letras e numeros da string
function SoLetrasNumeros( Campo )
{
   return Campo.value.replace( /\W|\_/g, "" );
}

// Retorna so letras da string
function SoLetras( Campo )
{
   return Campo.value.replace( /[^A-Za-z]/g, "" );
}

// Coloca pontos de milhar nos valores numericos
function ColocaPontos( Valor )
{
   if( Valor.length <= 3 )
      return Valor;
   else
      return( ColocaPontos( Valor.substr( 0, Valor.length - 3 ) ) + "." + Valor.substr( Valor.length - 3, 3 ) );
}

// Verificando se o campo so tem zeros
function CampoZerado( Valor )
{
   if( parseFloat( Valor ) == 0 ) return true;
   return false;
}

function VerificaTamanho( Campo, DescCampo, tamMax )
{
//	if(Campo.type == "textarea"){
//		alert("Tamanho: "+String(Campo.value).length);		
//		return false;
//	}
	if( Campo.value.length > tamMax )
	{
//		alert(Campo.value.length);
//		return false;

      alert( DescCampo + " permite no máximo " + tamMax + " caracteres. \nOs caracteres excedentes serão truncados." );
      Campo.value = Campo.value.substr( 0, tamMax );
      return false;
   }
   return true;
}

// Funcao para jogar o foco
function Foco( Campo )
{
   if( !Campo.disabled && Campo.type != "hidden" )
      Campo.focus();
}

// Funcao que joga o foco para o outro campo caso a string seja do tamanho desejado
// Colocar no evento onKeyUp
var verTab = true;
function SoltaTecla( Valor, Tamanho, CampoJogarFoco )
{
   if( Valor.length == Tamanho && verTab )
   {
      Foco( CampoJogarFoco );
      verTab = false;
   }
   return true;
}

// Funcao para retornar o tab
function PressionaTecla()
{
   verTab = true;
}

// Funcao que calcula o modulo 11
function Modulo11( Valor, PesoInicio, PesoFim )
{
	var i, soma, mult, aux;
	soma = 0;
	mult = PesoInicio;
	for( i = Valor.length - 1; i >= 0; i-- )
	{
		soma += parseInt( Valor.substr( i, 1 ), 10 ) * mult;
		if( PesoInicio < PesoFim )
		{
			if( mult == PesoFim )
				mult = PesoInicio - 1;
			mult++;
		}else{
			if( mult == PesoFim )
				mult = PesoInicio + 1;
			mult--;
		}
	}
	aux = 11 - ( soma % 11 );
	return ( ( aux >= 10 ) ? 0 : aux );
}

// Verifica se so tem numeros
function EhNumero( Campo, DescCampo, NaoTirarStrings )
{
   var aux = Campo.value.replace( /\s/g, "" );

   if( aux == "" )
   {
      Campo.value = "";
      return true;
   }

   if( typeof NaoTirarStrings == "undefined" || !NaoTirarStrings )
      aux = aux.replace( /\s|\/|\.|\,|\-/g, "" );

   if( isNaN( aux ) )
   {
      alert( DescCampo );
      Foco( Campo );
      return false;
   }
   return true;
}

function ColocaZeros( Valor, Qtde )
{
   var aux, i;
   aux = "";
   for( i = 1; i <= Qtde; i++ )
      aux += "0";
   return aux + Valor;
}

// Funcao para testar se o campo eh vazio
function CampoPreenchido( Campo, Mensagem )
{
   if( Trim( Campo.value ) == "" )
   {
      alert( Mensagem );
      Foco( Campo );
      return false;
   }
   return true;
}

// Funcao para fazer o trim de um valor
function Trim( Valor )
{
	//alert(Valor);
   return Valor.replace( /^\s*/, "" ).replace( /\s*$/, "" );
}

// Funcao que joga o foco para o outro campo caso a string seja do tamanho desejado
// Colocar no evento onKeyUp
var verTab = true;
function SoltaTecla( Valor, Tamanho, CampoJogarFoco )
{
   if( Valor.length == Tamanho && verTab )
   {
      Foco( CampoJogarFoco );
      verTab = false;
   }
   return true;
}

// Funcao para retornar o tab
function PressionaTecla()
{
   verTab = true;
}

// Funcao para jogar o foco para o primeiro elemento do formulario naum disabled
function JogaFocoPrimeiro( Formulario )
{
   for( var i = 0; i < Formulario.elements.length; i++ )
      if( !Formulario.elements[ i ].disabled && ( Formulario.elements[ i ].type == "text" || Formulario.elements[ i ].type == "textarea" || Formulario.elements[ i ].type == "select-one" ) )
      {
         Formulario.elements[ i ].focus();
         Formulario.elements[ i ].select();
         break;
      }
}

// Funcao para ver se o campo existe no formulario
function ExisteCampo( Campo )
{
   return ( Campo == null ) ? false : true;
}

// Funcao para ver se o campo esta vazio
function EstaVazio( Campo, Mensagem ){
   if( Trim( Campo.value ) == "" )
   {
      alert( Mensagem );
      Foco( Campo );
      return true;
   }
   return false;
}


function RetornaTipoCampo( Valor )
{
   var SemNum = Array( "Est", "Cor", "Ins", "Cont" );
   var ComNum = Array( "Soc", "Proc" );
}

// Funcao para verificar qdo Enter eh pressionado
function VerificaENTER()
{
   if( window.event.keyCode == 13 )
   {
      if( document.forms[ "CIF" ] != null )
         Foco( document.CIF.b1 );
      window.event.cancelBubble = true;
      window.event.returnValue  = false;
   }
}

// Funcao para verificar qdo ESC eh pressionado
function VerificaLimpar()
{
   if( window.event.srcElement.name == "b2" )
   {
      if( document.forms[ "CIF" ] != null )
         document.CIF.reset();
      window.event.cancelBubble = true;
      window.event.returnValue = false;
   }
}

// Funcao para dar submit em um janela
function AbrirNaJanela( formulario, nomeJanela, largura, altura )
{
   // Pegando todos os estados dos campos
   var vet = new Array( formulario.length );
   var aux, aux2, i;
   for( i = 0; i < formulario.length; i++ )
   {
      vet[ i ] = formulario[ i ].disabled;
      formulario[ i ].disabled = false;
   }

   window.open( 'Carga.html', nomeJanela,'toolbar=yes,location=no,directories=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=' + largura + ',height=' + altura + ',top=40,left=40');
   aux = formulario.target;
   formulario.target = nomeJanela;
   formulario.submit();

   // Recuperando os estados dos campos
   for( i = 0; i < formulario.length; i++ )
      formulario[ i ].disabled = vet[ i ];

   formulario.target = aux;
}

// Funcao para habilitar os campos e dar o submit
function SubmitForm( formulario, nomeJanela )
{
   // Pegando todos os estados dos campos
   var vet = new Array( formulario.length );
   for( i = 0; i < formulario.length; i++ )
   {
      vet[ i ] = formulario[ i ].disabled;
      formulario[ i ].disabled = false;
   }

   if( typeof nomeJanela != "undefined" && nomeJanela != "" ) formulario.target = nomeJanela;
   formulario.submit();

   // Recuperando os estados dos campos
   for( i = 0; i < formulario.length; i++ )
      formulario[ i ].disabled = vet[ i ];
}

/**
 * Função que permite a entrada de apenas números no campo, usada no evento onKeyPress
 * Exemplo: onKeyPress='return digitaNumeros(this, event, true);'
 * @author Paula Maiumi Rubel
 * @version 1.0.0 2005-10-11
 * @param campo referencia, evento, permiteCaracteres boolean (indica se permite a 
 *        entrada de caracteres como / * . , )
 * @return true ou false
 */
function digitaNumeros(campo, e, permiteCaracteres)
{
   var key;
   var keychar;

   if (window.event)
      key = window.event.keyCode;
   else if (e)
      key = e.which;
   else
      return true;

   keychar = String.fromCharCode(key);

   // teclas de controle
   if ((key==null) || (key==0) || (key==8) ||
      (key==9) || (key==13) || (key==27) )
      return true;

   // numeros e caracteres / * . , 
   else if (permiteCaracteres)
         if ((("0123456789/+-*.,").indexOf(keychar) > -1))
            return true;
         else
            return false;
   
   // numeros
   else
      if ((("0123456789").indexOf(keychar) > -1))
         return true;
      else
         return false;

}
/******************************************************************************
** Funcoes de formatacao                                                     **
******************************************************************************/
// Formata cnpj xx.xxx.xxx/xxxx-xx
function FormataCNPJ( Campo )
{
   var aux     = SoNumeros( Campo );
   Campo.value = aux.substr( 0, 2 ) + "." + aux.substr( 2, 3 ) + "." + aux.substr( 5, 3 ) + "/" + aux.substr( 8, 4 ) + "-" + aux.substr( 12, 2 );
}
function FormataHorario( Campo )
{
   var aux = ZerosDireita(SoNumeros( Campo ), 4);
   Campo.value = aux.substr( 0, 2 ) + ":" + aux.substr( 2, 2 )
}
// Formata cnpj raiz xx.xxx.xxx
function FormataCNPJRaiz(Campo)
{
	var aux = SoNumeros(Campo);
	aux = ColocaZeros(aux, 8 - aux.length);
	Campo.value = aux.substr( 0, 2 ) + "." + aux.substr( 2, 3 ) + "." + aux.substr( 5, 3 );
}
// Formata cpf xxx.xxx.xxx-xx
function FormataCPF( Campo )
{
   var aux     = SoNumeros( Campo );
   Campo.value = aux.substr( 0, 3 ) + "."
      + aux.substr( 3, 3 ) + "."
      + aux.substr( 6, 3 ) + "-"
      + aux.substr( 9, 2 );
}

// Formata data xx/xx/xxxx
function FormataData( Campo )
{
   var aux     = SoNumeros( Campo );
   if (aux != "")	
	Campo.value = aux.substr( 0, 2 ) + "/" + aux.substr( 2, 2 ) + "/" + aux.substr( 4, 4 );
}

// Formata data parcial xx/xxxx
function FormataDataParcial( Campo )
{
   var aux     = SoNumeros( Campo );
   Campo.value = aux.substr( 0, 2 ) + "/" + aux.substr( 2, 4 );
}

// Formata CadICMS xxxxxxxx-xx
function FormataCadICMS( Campo )
{
   var aux     = SoNumeros( Campo );
   Campo.value = aux.substr( 0, 8 ) + "-" + aux.substr( 8, 2 );
}

// Formata valores xx.xxx,xx
function FormataValor( Campo, TirarDecimal, DecimalZerado, TirarPontos )
{
   var decimal, inteira;
   var valorAux = Campo.value.replace( /\.|\s/g, "" ).replace( /\,/g, "." );
   var pos = valorAux.indexOf( "." );

   // Guardando as casa decimais e a parte inteira
   if( pos != -1 )
   {
      inteira = valorAux.substr( 0, pos );
      decimal = valorAux.substr( pos + 1, valorAux.length - pos - 1 );

      inteira = ( inteira == "" ) ? "0" : inteira;

      if( decimal.length > 2 )
      {
         if( decimal.charAt( 2 ) > 5 )
         {
            decimal = parseInt( decimal.substr( 0, 2 ), 10 ) + 1;

            if( decimal == 100 )
            {
               inteira = ( parseInt( inteira ) + 1 ).toString();
               decimal = "00";
            }

            if( decimal.toString().length == 1 )
               decimal = "0" + decimal;
         }
         else
            decimal = decimal.substr( 0, 2 );
      }

      if( decimal.length == 1 ) decimal += "0";
      if( decimal.length == 0 ) decimal = "00";
   }
   else
   {
      decimal = "00";
      inteira = valorAux;
   }

   if( DecimalZerado )
      decimal = "00";

   if( !TirarPontos )
      inteira = ColocaPontos( inteira );

   valorAux = inteira;
   if( !TirarDecimal )
      valorAux += "," + decimal;

   Campo.value = valorAux;
}

function FormataCred( Campo )
{
   var aux     = SoNumeros( Campo );
   Campo.value = aux.substr( 0, 7 ) + "-" + aux.substr( 7, 2 );
}

function FormataGeral( Campo, Casas )
{
   var aux     = SoNumeros( Campo );
   Campo.value = aux.substr( 0, aux.length - Casas ) + "-" + aux.substr( aux.length - Casas, Casas );
}

// Formata CNAEF xxxx-x/xx
function FormataCNAEF( Campo )
{
	var aux = SoNumeros( Campo );
	Campo.value = aux.substr( 0, 4 ) + "-" + aux.substr( 4, 1 ) + "/" + aux.substr( 5, 2 );
}

// Formata SID xxx.xxx.xxx-xx
function FormataSID( n )
{
	var aux = n.replace(/\D/g,"");
	if(aux.length > 0){
	//	aux= aux.substr( 0, 3 ) + "." + aux.substr( 3, 3 ) + "." + aux.substr( 6, 3 ) + "-" + aux.substr( 9, 1 );
		aux= aux.substr(0,1) + "." + aux.substr( 1, 3 ) + "." + aux.substr( 4, 3 ) + "-" + aux.substr( 7, 1 );
		return aux;
	}else return(n);
}

// Formata Nº ANAF xxxxx/aaaa
function FormataNrANAF( Campo )
{
   var aux = SoNumeros( Campo );
   aux     = ColocaZeros( aux, 9-aux.length );
   Campo.value = aux.substr( 0, 5 ) + "/" + aux.substr( 5, 4 );
}
// Formata Fone/Fax xxxx-xxxx
function FormataFoneFax(c){
   var aux = SoNumeros(c);
   c.value = aux.substring(0,aux.length-4)+ "-" +aux.substring(aux.length-4,aux.length);
}

/******************************************************************************
** Funcoes de validacao                                                      **
******************************************************************************/
// Valida CNPJ
function ValidaInscricaoCNPJ( Campo )
{
   if( Campo.value == "" ) return true;
   
   if( !EhNumero( Campo, 'Inscrição CNPJ Inválida.' ) ) return false;

   var aux = SoNumeros( Campo );

   if( aux != "" )
   {
      if( !CNPJOk( aux ) )
      {
         alert( "Inscrição CNPJ Inválida." );
         Foco( Campo );
         return false;
      }
      FormataCNPJ( Campo );
      return true;
   }
   return false;
}

// Valida CNPJ Raiz (00.000.000)
// Parâmetros: 
//		c -> Campo
//		a -> mudo : boolean Sem o alerta de invalidade
//		f -> formatacao : boolena Sem formatar o campo
function ValidaInscricaoCNPJRaiz(c,a,f)
{
	var re;
	if(c.value=="")return true;
	if(c.value.match("[.]"))
		re = new RegExp("^([0-9]{2})[.]{1}([0-9]{3})[.]{1}([0-9]{3})$","g");
	else 
		re = new RegExp("^[0-9]*$","g");
	if (re.test(c.value)){
		if(f){}else	FormataCNPJRaiz(c);//FormataCNPJ(ColocaZeros(c.value, 8 - c.value.length));
		return true;
	}else{
		if(a){}else alert( "CNPJ Raiz Inválido." );
		Foco(c);
		return false;
	}

}
function ValidaInscricaoCPF( Campo )
{
   if( Campo.value == "" ) return true;
   
   if( !EhNumero( Campo, 'Inscrição CPF Inválida.' ) ) return false;

   var aux = SoNumeros( Campo );

   if( aux != "" )
   {
      if( CampoZerado( aux ) || !CPFOk( aux ) )
      {
         alert( "Inscrição CPF Inválida." );
         Foco( Campo );
         return false;
      }
      FormataCPF( Campo );
      return true;
   }
   return false;
}


// Valida nosso numero

// Valida data
function ValidaData( Campo, Referencia, Igual )
{
   /* Referencia
      0 ou nada -> naum valida
      1         -> Data maior que a data atual
      2         -> Data menor que a data atual
      3         -> Data naum pode ser superior a um mes da data atual
      4         -> Data naum pode ser maior que o ultimo dia do mes
      5         -> Data naum pode ser maior que a data atual e menor que 5 anos atras
      6         -> Data no formato ddmmaa
   */

   if( !EhNumero( Campo, 'Data Inválida.' ) ) return false;

   var DataAtual, auxData, aux2;
   var aux = SoNumeros( Campo );
   var Operador = ( Igual ) ? "=" : "";

   if( aux != "" )
   {
      if( !DataOk( aux, Referencia ) )
      {
         alert( "Data Inválida." );
         Foco( Campo );
         return false;
      }

      DataAtual = new Date();
      DataAtual.setHours( 0, 0, 0, 0 );

      if( Referencia == 6 )
      {
         aux2 = parseInt( aux.substr( 4, 2 ), 10 );
         if( aux2 > 80 )
            aux2 = "19" + aux2;
         else
            if( aux2 < 10 )
               aux2 = "200" + aux2;
            else
               aux2 = "20" + aux2;
      }
      else
         aux2 = aux.substr( 4, 4 );

      aux2 = parseInt( aux2, 10 );
      auxData = new Date( aux2, aux.substr( 2, 2 ) - 1, aux.substr( 0, 2 ), 0, 0, 0 );

      if( Referencia == 1 && eval( "auxData <" + Operador + " DataAtual" ) )
      {
         alert( "Data deve ser posterior à Data Atual." );
         Foco( Campo );
         return false;
      }

      if( Referencia == 2 && eval( "auxData >" + Operador + "DataAtual" ) )
      {
         alert( "Data não deve ser posterior à Data Atual." );
         Foco( Campo );
         return false;
      }

      if( Referencia == 4 )
      {
         aux  = DataAtual.getMonth() + 1;
         aux2 = "31";

         if( aux == 2 )
            aux2 = ( DataAtual.getFullYear() % 4 == 0 ) ? "29" : "28";

         if( aux == 4 || aux == 6 || aux == 9 || aux == 11 )
            aux2 = "30";

         DataAtual.setDate( aux2 );

         if( DataAtual < auxData )
         {
            alert( "Data não pode ser maior que o último dia do mês atual" );
            Foco( Campo );
            return false;
         }
      }

      if( Referencia == 5 )
      {
         if( eval( "auxData >" + Operador + "DataAtual" ) )
         {
            alert( "Data não deve ser posterior à Data Atual." );
            Foco( Campo );
            return false;
         }

         if( auxData.getFullYear() < DataAtual.getFullYear() - 5 )
         {
            alert( "Data não deve ser inferior a 5 anos do ano atual." );
            Foco( Campo );
            return false;
         }
      } // if( Referencia == 5 )

      if( Referencia != 6 )
         FormataData( Campo );
   }
   return true;
}

// Valida a data no formato mmaaaa
function ValidaDataParcial( Campo, Referencia )
{
   /* Referencia
      0 ou nada -> naum valida
      1         -> Data maior que a data atual
      2         -> Data menor que a data atual
      3         -> Data naum pode ser superior a um mes da data atual
      4         -> Data naum pode ser maior que o ultimo dia do mes
      5         -> Data naum pode ser maior que o mes atual e menor que 5 anos atras
   */

   if( !EhNumero( Campo, 'Data Inválida.' ) ) return false;

   var DataAtual, auxData;
   var aux = SoNumeros( Campo );

   if( aux.length == 5 )
      aux = "0" + aux;

   if( aux != "" )
   {
      if( !DataParcialOk( aux ) )
      {
         alert( "Data Inválida." );
         Foco( Campo );
         return false;
      }

      DataAtual = new Date();

      // Criando a data no formato de 01mmaaaa
      auxData = new Date( aux.substr( 2, 4 ), aux.substr( 0, 2 ) - 1, 1, 0, 0, 0 );
      // Agora para a data atual
      DataAtual.setDate( 1 );
      // Zerando as horas
      DataAtual.setHours( 0, 0, 0, 0 );

      if( Referencia == 1 && DataAtual > auxData )
      {
         alert( "Data deve ser maior que a Data Atual." );
         Foco( Campo );
         return false;
      }

      if( Referencia == 2 && DataAtual < auxData )
      {
         alert( "Data deve ser menor que a Data Atual." );
         Foco( Campo );
         return false;
      }

      // Dividindo por 1000ms * 60s * 60m * 24h = 86400000 teremos em dias
      if( Referencia == 3 && ( ( auxData - DataAtual ) / 86400000 > 31 ) )
      {
         alert( "Data não pode ser superior a um mês da Data Atual" );
         Foco( Campo );
         return false;
      }

      if( Referencia == 5 )
      {
         if( DataAtual < auxData )
         {
            alert( "Data não pode ser superior ao mês atual." );
            Foco( Campo );
            return false;
         }

         DataAtual.setFullYear( DataAtual.getFullYear() - 5 );
         DataAtual.setMonth( 0 );
         if( DataAtual > auxData )
         {
            alert( "Data não pode ser menor que 5 anos do ano atual" );
            Foco( Campo );
            return false;
         }
      }
      FormataDataParcial( Campo );
      return true;
   }
}
// Valida Data Final com a Inicial
// tipo = 1 dd/mm/aaaa
// tipo = 2 mm/aaaa
// difmes = diferenca maxima entre as datas passadas
function ValidaDataFinal(tipo, inicio, fim, difMes, msg, dataMin, dataMax)
{
  var dataInicio  = SoNumeros(inicio);
   var dataFim    = SoNumeros(fim);
   if (dataMin != '')
  var dataMinima  = SoNumeros(dataMin);   
   if (dataMax != '')
   var dataMaxima  = SoNumeros(dataMax);   
  
   if (tipo == 1){
  var dataInicio1  = new Date( dataInicio.substr(4,4), dataInicio.substr( 2, 2 )-1, dataInicio.substr( 0, 2 ), 0, 0, 0 );
  var dataFim1  = new Date( dataFim.substr(4,4), dataFim.substr( 2, 2 )-1, dataFim.substr( 0, 2 ), 0, 0, 0 );
   if (dataMin != '')
    var dataMinima1  = new Date( dataMinima.substr(4,4), dataMinima.substr( 2, 2 )-1, dataMinima.substr( 0, 2 ), 0, 0, 0 );
   if (dataMax != '')
     var dataMaxima1  = new Date( dataMaxima.substr(4,4), dataMaxima.substr( 2, 2 )-1, dataMaxima.substr( 0, 2 ), 0, 0, 0 );
  } else {
  var dataInicio1  = new Date( dataInicio.substr(2,4)-1, dataInicio.substr( 0, 2 ), 0, 0, 0, 0);
  var dataFim1  = new Date( dataFim.substr(2,4)-1, dataFim.substr( 0, 2 ), 0, 0, 0, 0);
     if (dataMin != '')
    var dataMinima1  = new Date( dataMinima.substr(2,4)-1, dataMinima.substr( 0, 2 ), 0, 0, 0, 0);
   if (dataMax != '')
    var dataMaxima1  = new Date( dataMaxima.substr(2,4)-1, dataMaxima.substr( 0, 2 ), 0, 0, 0, 0);
  }
   
   if (dataMin != '')
  if (dataMinima1 > dataInicio1) {
    alert("A Data Inicial deve ser Superior a " + dataMin.value)
    Foco(inicio);
    return false;
  } 
   if (dataMax != '')
   if (dataMaxima1 < dataFim1) {
    alert("A Data Final deve ser Inferior a " + dataMax.value)
    Foco(fim);
    return false;
  } 
  
  if (dataFim1 < dataInicio1) {
  alert(msg)
  Foco(fim);
  return false;
  } 
  if (difMes != '') {
  aux = dataInicio1.getMonth() + difMes
  dataInicio1.setUTCMonth(aux)

  if (dataFim1 > dataInicio1) {
    alert("Diferença entre as Datas do Período não pode exceder " + difMes + " Mês(es).")
    Foco(fim);
    return false;
  }
  }
  return true;
}


// Valida valores
function ValidaValor( Campo, DescCampo, TirarDecimal, DecimalZerado, TirarPontos )
{
   if( !EhNumero( Campo, DescCampo + ' Inválido.' ) ) return false;

   var aux = Campo.value.replace( /\.|\,/g, "" );

   aux = SoNumeros( Campo );
   if( aux != "" )
   {
      if( aux <= 0 )
      {
         alert( DescCampo + " Informado deve ser diferente de 0 (zero)." );
         Foco( Campo );
         return false;
      }
      FormataValor( Campo, TirarDecimal, DecimalZerado, TirarPontos );
      return true;
   }
   return false;
}

// Valida Numeros
function ValidaNumero( Campo, DescCampo )
{
   if( !EhNumero( Campo, DescCampo + ' Inválido.' ) ) return false;

   var aux = Campo.value;

   aux = SoNumeros( Campo );
   if( aux != "" )
   {
      return true;
   }
   return false;
}

// Valida Horario
function ValidaHorario( Campo )
{
//   if( !EhNumero( Campo, DescCampo + ' Inválido.' ) ) return false;
   var aux = SoNumeros( Campo );
   if( aux != "" ) {
		FormataHorario(Campo);
	    return true;
   }
   alert( "Formato de Horário inválido." );
   return false;
}
// Valida Cad.ICMS
function ValidaInscricaoCadICMS( Campo )
{
   if( !EhNumero( Campo, 'Inscrição Cad.ICMS/PR Inválida.' ) ) return false;

   var aux = SoNumeros( Campo );

   if( aux != "" )
   {
      if( CampoZerado( aux ) || !CadICMSOk( aux ) )
      {
         alert( "Inscrição Cad.ICMS/PR Inválida." );
         Foco( Campo );
         return false;
      }
      FormataCadICMS( Campo );
      return true;
   }
   return false;
}

// Funcao para validar a cnaef
// Parâmetros: 
//		c -> Campo
//		a -> mudo : boolean Sem o alerta de invalidade
//		f -> formatacao : boolena Sem formatar o campo
function ValidaCNAEF(c,a,f)
{
	if(!EhNumero(c,'CNAE inválida.'))return false;
	var aux = SoNumeros(c);
	if( aux != "" )
	{
		if( CampoZerado( aux ) || !CNAEFOk( aux ) )
		{
			if(a){}else alert( "CNAE inválida." );
			//Foco(c);
			return false;
		}
		if(f){}else FormataCNAEF(c);
		return true;
	}
	return false;
}

//Valida O campo do tipo SID
function ValidaSID(c){
	c.value = SoNumeros(c);
	if(SIDOk(c.value))
		c.value=FormataSID(c.value);
	else if(c.value!=""){
		alert("SID Inválido.");
		c.value='';
	}
}

// ValidaDDD
// Parâmetros: 
//		c -> Campo testas
//		a -> mudo : boolean Sem o alerta de invalidade
function ValidaDDD(c,a)
{
	if(c.value=="")return true;
	var re = new RegExp("^([0-9]{2})$","g");
	if (re.test(c.value)){
		marcaValido(c);
		return true;
	}else{
		if(a){}else{alert( "DDD  Inválido." );marcaInvalido(c);}
		return false;
	}
}
// Funcao para validar email
function ValidaEmail( Campo ){
   var aux = Campo.value.replace( /\s/g, "" );
   if( aux != "" )   {
      // Valida aux ate do tipo joao.bla-@ddd-abc.com.br
      expr = /^(\w|\-)(\w|\.\w|\-)*@\w(\w|\-\w)*(\.\w(\w|\-\w)*)+$/
      if( !aux.match( expr ) )
      {
         alert( "E-Mail Inválido." );
         marcaInvalido(Campo);
         return false;
      }
      Campo.value = aux;
      marcaValido(Campo);
      return true;
   }
   return true;
}
// Valida Nº ANAF
// Parâmetros: 
//		c -> Campo
//		a -> mudo : boolean Sem o alerta de invalidade
//		f -> formatacao : boolena Sem formatar o campo
function ValidaNrANAF(c,a,f){
	if(c.value=="")return true;
	var re = new RegExp("^([0-9]{1,5})[/]{0,1}([0-9]{4})$","g");
	if (re.test(c.value)){
		if(f){}else FormataNrANAF(c);
		return true;
	}else{
		if(a){}else alert( "Nº ANAF  Inválida." );
		return false;
	}
}
// Parâmetros: 
//		c -> Campo
//		a -> mudo : boolean Sem o alerta de invalidade
//		f -> formatacao : boolena Sem formatar o campo
function ValidaFoneFax(c,a,f){
	if(c.value=="")return true;
	var re = new RegExp("^([0-9]{3,4})[-]{0,1}([0-9]{4})$","g");
	if (re.test(c.value)){
		if(f){}else{FormataFoneFax(c);marcaValido(c);}
		return true;
	}else{
		if(a){}else{alert( "Número de Telefone/Fax inválido." );marcaInvalido(c);}
		return false;
	}
}
/******************************************************************************
** Funcoes de verificacao de consistencia                                    **
******************************************************************************/
// Valida CNPJ
function CNPJOk( Valor )
{
   if( Valor.length != 14 )
      return false;

   if( Valor.substr( 8, 4 ) == "0000" || Valor.substr( 8, 4 ) == "9999" )
      return false;

   // Verificando o digito verificador
   if( Modulo11( Valor.substr( 0, 12 ), 2, 9 ) != Valor.substr( 12, 1 ) || Modulo11( Valor.substr( 0, 13 ), 2, 9 ) != Valor.substr( 13, 1 ) )
      return false;

   return true;
}

// Valida CPF
function CPFOk( Valor )
{
   if( Valor.length != 11 )
      return false;

 //  expr = /0{11}|1{11}|2{11}|3{11}|4{11}|5{11}|6{11}|7{11}|8{11}|9{11}/
 //  if( Valor.match( expr ) )
 //     return false;

   // Verificando o digito verificador
   if( Modulo11( Valor.substr( 0, 9 ), 2, 11 ) != Valor.substr( 9, 1 ) || Modulo11( Valor.substr( 0, 10 ), 2, 11 ) != Valor.substr( 10, 1 ) )
      return false;

   return true;
}

// Valida CadICMS
function CadICMSOk( Valor )
{
   if( Valor.length != 10 )
      return false;

   // Verificando o digito verificador
   if( Modulo11( Valor.substr( 0, 8 ), 2, 7 ) != Valor.substr( 8, 1 ) || Modulo11( Valor.substr( 0, 9 ), 2, 7 ) != Valor.substr( 9, 1 ) )
      return false;

   return true;
}

// Valida data
function DataOk( Valor, Referencia )
{
   // Referencia = 6 -> Data no formato ddmmaa
   var dataAtual = new Date();
   var dia, mes, ano;

   if( Valor.length == 8 || ( Referencia == 6 && Valor.length == 6 ) )
   {
      dia = parseInt( Valor.substr( 0, 2 ), 10 );
      mes = parseInt( Valor.substr( 2, 2 ), 10 );

      if( Referencia == 6 )
      {
         aux = parseInt( Valor.substr( 4, 2 ), 10 );
         if( aux > 80 )
            aux = "19" + aux;
         else
            if( aux < 10 )
               aux = "200" + aux;
            else
               aux = "20" + aux;
      }
      else
         aux = Valor.substr( 4, 4 );

      ano = parseInt( aux, 10 );

      if( dia < 1 || dia > 31 )
         return false;

      if( mes < 1 || mes > 12 )
         return false;

      if( ( mes == 4 || mes == 6 || mes == 9 || mes == 11 ) && dia > 30 )
         return false;

      if( mes == 2 )
      {
         if( ano % 4 == 0 && dia > 29 )
            return false;

         if( ano % 4 != 0 && dia > 28 )
            return false;
      }

      dataAtual.setHours( 0, 0, 0, 0 );
      var dataInformada = new Date( ano, mes - 1, dia, 0, 0, 0, 0 );

      // Cem anos antes
      if( dataInformada < dataAtual.setFullYear( dataAtual.getFullYear() - 100 ) )
         return false;

      // Cem anos depois
      if( dataInformada > dataAtual.setFullYear( dataAtual.getFullYear() + 200 ) )
         return false;

      return true;
   } // length
   return false;
}

// Valida a data parcial
function DataParcialOk( Valor )
{
   var mes, ano;

   if( Valor.length == 6 )
   {
      mes = parseInt( Valor.substr( 0, 2 ), 10 );
      ano = parseInt( Valor.substr( 2, 4 ), 10 );

      if( mes < 1 || mes > 12 )
         return false;

      if( ano < 1901 || ano > 2100 )
         return false;

      return true;
   } // length
   return false;
}


// Valida CNAE 2.0
function CNAEFOk(val)
{
	if(val.length != 7 )
		return false;
	// Com a CNAE 2.0 o dígito verificador deve ser calculado 
	// através do retorno da função Modulo11 acrescido de 1(um)
	var dv = Modulo11(val.substr(0,4),2,9) + 1;
	dv = ((dv >= 10) ? 0 : dv)
	if(dv != val.substr(4,1))
		return false;
	return true;
}

// Valida SID
function SIDOk(n){
	var aux=n.replace(/\D/g,"");
	if(parseInt(aux) == 0)return false;
	if(aux.length != 8 )return false;
	//if(Modulo11(aux.substr( 0, 9 ), 2, 7 ) != aux.substr( 9, 1 ))return false;
	if(Modulo11(aux.substr(0,7), 2, 7) != aux.substr(7,1))return false;
	return true;
}

/** Valida Chave de Acesso NFe
@autor Jonathan F.
@data 09/07/2010
@tag [vNFEN]
**/

function chaveAcessoOK(pChave){
	var chave = pChave.value.toUpperCase();
	
	// remove a marca 'nfe' no começo da chave
	var chaveAux = chave;
	if ( chaveAux.substr(0,3) == "NFE"){
		chave = chaveAux.replace("NFE","");
	}
	
	//alert("valida 1: " + chave);

	// verifica se está vazia
	if(EstaVazio( pChave, "O campo \"Chave de acesso\" é obrigatório."))
		return false;
	
	//alert("valida 2: " + chave);
	
	// verifica se possui a quantidade correta
	if (chave.length != 44){
		alert("Quantidade de caracteres (" + chave.length + ") inválida no campo \"Chave de acesso\"!");
		return false;
	}
	
	//alert("valida 3: " + chave);
	
	// valida a chave no modulo11
	if( Modulo11( chave.substr( 0, 43 ), 2, 9 ) != chave.substr( 43, 1 ) ){
		alert ("Chave de acesso inválida!");
		//alert ("Debug: <br> Modulo: " + Modulo11(chave.substr( 0, 43 ), 2, 9 ) + "<br> Inform: " + chave.substr( 43, 1 ));
		return false;
	}
	return true;
}



/**
 * Script para permitir validacao do formulario no mozilla.
 * Serao executados todos os "onblur" dos campos.
 *
 * @author Pablo Jose Almeida da Guia
 * @version 1.0.0 2004-12-29
 * @param form referencia para o formulario
 * @return true ou false
 */
function runBlurs(form) {
  var i, bOut = true;
  for (i=0; i<form.length; i++) {
    var element = form.elements[i];
    if (!element.disabled && element.onchange != null) {
      var str = "" + element.onchange;
      var bReturn = true;
      if (str.indexOf("function") > -1) {
        str = str.substr(str.indexOf("{") + 2,
            str.length - str.indexOf("{")
            - (str.length - str.lastIndexOf("}")) - 2);
        while (str.charAt(0) == " " || str.charAt(0) == "\r"
            || str.charAt(0) == "\n" || str.charAt(0) == "\t") {
          str = str.substr(1);
        }
        if (str.indexOf("return") == 0)
          str = str.substr(6);

      }
      str = "bReturn = " + str;
      while (/this/.test(str)) {
        str = str.replace("this", "element");
      }
      eval(str);
      if (bReturn == false) {
        return false;
      }
    }
  }
  return true;
}

//Converte moeda formato monetario americado para o formato Brasileito (Exemplo: 1500.60 para 1.500,60)
function formatCurrency(number){
  var num = new String (number);

  if (num.indexOf (".") == -1){
    intLen = num.length;
    toEnd  = intLen;

    var strLeft = new String (num.substring (0, toEnd));
    var strRight = new String ("00");

  }else{

    pos = eval (num.indexOf ("."));

    var strLeft = new String (num.substring (0, pos));

    intToEnd = num.length;
    intThing = pos + 1;

    var strRight = new String (num.substring (intThing, intToEnd));

    if (strRight.length > 2){

      nextInt = strRight.charAt(2);

      if (nextInt >= 5){

        strRight = new String (strRight.substring (0, 2));
        strRight = new String (eval ((strRight * 1) + 1));

        if((strRight * 1) >= 100){
          strRight = "00";
          strLeft  = new String (eval ((strLeft * 1) + 1));
        }

        if (strRight.length <= 1){
          strRight = new String ("0" + strRight);
        }

      }else{
        strRight = new String (strRight.substring (0, 2));
      }

    }else{
      if (strRight.length != 2){
        strRight = strRight + "0";
      }
    }
  }

  if (strLeft.length > 3){

    var curPos = (strLeft.length - 3);

    while (curPos > 0){
      var remainingLeft = new String (strLeft.substring (0, curPos));
      var strLeftLeft   = new String (strLeft.substring (0, curPos));
      var strLeftRight   = new String (strLeft.substring (curPos, strLeft.length));

      strLeft = new String (strLeftLeft + "." + strLeftRight);
      curPos  = (remainingLeft.length - 3);
    }

  }

  strWhole   = strLeft + "," + strRight;
  finalValue = strWhole;

  return (finalValue);
}

function FormataValor2( Campo )
{
   var valor   = SoNumeros( Campo );
   var retorno = "";
   var decimal, inteira;

   if( valor != "" )
      valor = parseFloat( valor ).toString();
   else
   {
      Campo.value = "";
      return;
   }

   if( valor.length > 2 )
   {
      retorno = ColocaPontos( valor.substr( 0, valor.length - 2 ) ) + "," + valor.substr( valor.length - 2, 2 );
   }
   else
   {
      if( valor.length == 2 ) retorno = "0" + "," + valor;
      if( valor.length == 1 && valor != "0" ) retorno = "0,0" + valor;
      if( valor.length == 0 || valor == "0" ) retorno = "";
   }
   Campo.value = retorno;
}


//************************************************************************
// Funcao para validar data no formato DD/MM/AAAA        
// Parametros: Data                                      
// Retorna: true se data válida e false caso contrario   
//************************************************************************

function validaformatodata(campo)
{
    var err = 0
    string = campo.value;
    var valid = "0123456789/"
    var ok = "yes";
    var temp;
    for (var i=0; i< string.length; i++) {
    temp = "" + string.substring(i, i+1);
    if (valid.indexOf(temp) == "-1") err = 1;
    }
    if (string.length != 10) err=1
    dia    = string.substring(0, 2)  // dia
    barra1 = string.substring(2, 3)  // '/'
    mes    = string.substring(3, 5)  // mês
    barra2 = string.substring(5, 6)  // '/'
    ano    = string.substring(6, 10) // ano
    if (dia<1 || dia>31) err = 1
    if (barra1 != '/') err = 1
    if (mes<1 || mes>12) err = 1
    if (barra2 != '/') err = 1
    if (ano<0) err = 1
    if (mes==4 || mes==6 || mes==9 || mes==11)
    {
       if (dia==31) err=1	
    }
    if (mes==2)
    {
       var g=parseInt(ano/4)
       if (isNaN(g)) {
       err=1
       }
       if (dia>29) err=1
       if (dia==29 && ((ano/4)!=parseInt(ano/4))) err=1 //Verifica ano bissexto
    }
    if (err==1)
    {
	alert("Data inválida. Preencha com o formato 'DD/MM/YYYY'.");
	campo.focus();
	return(false);
    } 
    else
    {
       return(true);       
    }
}



//************************************************************************
// Funcao para validar duas datas, se data inicial < data fim       
// Parametros: Data inicial, Data final (formato DD/MM/AAAA válido) 
// Retorna: true se data inicial < data fim e false caso contrario  
//************************************************************************

function comparadata(campodataini, campodatafim)
{
   var dataini = campodataini.value;
   var datafim = campodatafim.value;
   var Dt_inicial = dataini.substr(6,4) + dataini.substr(3,2) + dataini.substr(0,2);
   var Dt_final   = datafim.substr(6,4) + datafim.substr(3,2) + datafim.substr(0,2);
   if (Dt_inicial > Dt_final) 
   {
	alert("Período Final deve ser maior que o Período Inicial!");
	campodatafim.focus();
	return (false);
   }
   else
    	return (true);
}
//Controla o tamanho máximo de caracteres em um textarea
function maxLength(textAreaField, limit)
{
    var ta = textAreaField;
 		
 	if (ta.value.length >= limit) {
 	    ta.value = ta.value.substring(0, limit-1);
    }
}
// Pula de um Campo a Outro Automaticamente
function PulaCampo( Campo ) {
	if (Campo.value.length == Campo.maxLength) {
		for (var i = 0; i < Campo.form.length; i++) {
			if (Campo.form[i] == Campo && Campo.form[(i + 1)] && Campo.form[(i + 1)].type != "hidden") {
				Campo.form[(i + 1)].focus();
				break;
			}
		}
	}
}
function marcaValido(c){c.style.color="#000000";}
function marcaInvalido(c){c.style.color="#ff0000";}
