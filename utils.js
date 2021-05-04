function voltar(){
	var f=document.forms["form1"];
	//alert("Passo Antes: "+f.cdPasso.value);
	//alert("Parse: "+parseInt(f.cdPasso.value));
	f.taVoltando.value = "true";
	f.cdPasso.value = parseInt(f.cdPasso.value)-1;
	//alert("Passo Depois: "+f.cdPasso.value);
	f.submit();
}
function proximoPasso(pCdPasso){
	var f=document.forms["form1"];
	f.btnContinuar.value = "Aguarde...";
	f.btnContinuar.disabled = true;
	f.cdPasso.value=pCdPasso;
	f.taVoltando.value="false";
	//alert(f.cdPasso.value);
	f.submit();
}
function detalhesNota(pId){
	f = document.forms["form1"];
	f.idNota.value = pId;
	//alert(f.idNota.value);
	f.cdPasso.value=parseInt(f.cdPasso.value) + 1;
	f.submit();
}
function limpar(pCampo){
	var f = document.forms["form1"];
	if(pCampo != null && pCampo != "")
        f.elements[pCampo].value="";
	else{
		for(var i=0;i<f.length;i++){
			var e=f.elements[i];
			if( e.type != "hidden" &&
				e.type != "button" &&
				e.type != "submit" &&
				e.type != "reset" &&
				e.name != "cdPasso" &&
				e.name != "taVoltando" &&
				e.name != "isPopup")e.value = "";
		}
	}
}
function abreJanelaAR(pAcao){
	var largura=700, altura=600,x=0,y=0;
	if (screen){
		//centraliza a tela
		x=screen.width/2;
		x=x-(largura/2);
		y=screen.height/2;
		y=y-(altura/2);
		y=y/2;
	}      
	window.open(pAcao,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width='+ largura +',height='+ altura +',left='+x+',top='+y);
}
