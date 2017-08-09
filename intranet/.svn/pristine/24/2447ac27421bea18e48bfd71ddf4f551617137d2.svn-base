var AgntUsr	= navigator.userAgent.toLowerCase();
var NavYes	= AgntUsr.indexOf('mozilla') != -1 && AgntUsr.indexOf('compatible') == -1 ? 1:0;
var ExpYes	= AgntUsr.indexOf('msie') != -1 ? 1:0;
var Opr		= AgntUsr.indexOf('opera')!= -1 ? 1:0;

function CheckNumerico(campo,tammax,teclapres)
{
	var teclaChar = String.fromCharCode(getKeyCode(teclapres));

	// retirando caracteres especiais	
	var caracteresEspeciais = new Array("!","@","#","$","%","^","&","*","(",")",
										"_","-","+","=","{","}","|","[","]","\\",
										"\"",":",";","'","<",">","?","/");
	for (var i = 0;i<caracteresEspeciais.length; i++){
		if (campo.value.indexOf (caracteresEspeciais[i]) > -1){
			campo.value = campo.value.replace(caracteresEspeciais[i], "");
		}	
	}
	
 	if (teclaChar != 0) {
		if (("0123456789").indexOf(teclaChar) > -1)
			return;
		else{
			while (campo.value.indexOf(teclaChar) > -1 || campo.value.indexOf(teclaChar.toLowerCase()) > -1){
				campo.value = campo.value.replace(teclaChar, "");
				campo.value = campo.value.replace(teclaChar.toLowerCase(), "");
			}
		}
	}
	return true;
}

function FormataValorSemEvento(campo,tammax,prec)
{
	//pegar tecla e definir valor de virgula
	var virgula = ',';

	//pegar valor do campo atual e remover todas virgulas, pontos, barras etc...
	vr = campo.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );

	//se precisao for 0 definir virgula como inexistente para n?o aparecer
	if (prec==0)
		virgula='';

	//antes de checar tamanho do campo remover 0s da frente do campo
	for (k=0;k<prec;k++)
	{
		if (vr.substr(0,1) == '0')
			vr=vr.substr(1,prec+1);
	}

	//pegar tamanho dos valores j? limpos
	tam = vr.length;
	
	//se tamanho for zero n?o fazer nada
	if (tam==0)
		return
	
		//if para campos de valores fracionais ate 0,999
		if ( tam <= prec + 1)
		{
			campo.value = '0' + virgula;
			for (k=1;k<=prec-tam;k++)
			{	
				campo.value += '0' ;
			}	
			campo.value+=vr;
		}

		//if para campos com valores at? 999,999
		if ( (tam > prec) && (tam <= prec + 3) )
		{
			campo.value = vr.substr(0,tam-prec) + virgula + vr.substr(tam-prec,prec+1); 
		}

		//if para campos com valores at? 999.999,999
		if ( (tam > prec + 3) && (tam <= prec + 6) )
		{
			campo.value = vr.substr(0, tam-(prec+3)) + '.' + vr.substr(tam-(prec+3), 3) + virgula + vr.substr(tam-prec, prec+1) ; 
		}

		//if para campos com valores at? 999.999.999,999
		if ( (tam > prec + 6) && (tam <= prec + 9) ){
			campo.value = vr.substr(0, tam-(prec+6) ) + '.' + vr.substr(tam-(prec+6), 3) + '.' + vr.substr(tam-(prec+3),3 ) + virgula + vr.substr(tam-prec, prec+1); 
		}

		//if para campos com valores at? 999.999.999.999,999
		if ( (tam > prec + 9) && (tam <= prec + 12) )
		{
			campo.value = vr.substr(0, tam-(prec+9)) + '.' + vr.substr(tam-(prec+9), 3) + '.' + vr.substr(tam-(prec+6), 3) + '.' + vr.substr(tam-(prec+3), 3) + virgula + vr.substr(tam-prec,prec+1) ; 
		}

		//if para campos com valores at? 999.999.999.999.999,999
		if ( (tam > prec + 12) && (tam <= prec + 15) )
		{
			campo.value = vr.substr(0, tam-(prec+12)) + '.' + vr.substr(tam-(prec+12), 3) + '.' + vr.substr(tam-(prec+9), 3) + '.' + vr.substr(tam-(prec+6), 3) + '.' + vr.substr(tam-(prec+3), 3) + virgula + vr.substr(tam-prec,prec+1) ; 
		}
	return;
}

function FormataValor(campo,tammax,teclapres,prec)
{
	//pegar tecla e definir valor de virgula
	var tecla = teclapres.keyCode;
	var virgula = ',';

	//pegar valor do campo atual e remover todas virgulas, pontos, barras etc...
	vr = campo.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );

	//se precisao for 0 definir virgula como inexistente para n?o aparecer
	if (prec==0)
		virgula='';

	//antes de checar tamanho do campo remover 0s da frente do campo
	for (k=0;k<prec;k++)
	{
		if (vr.substr(0,1) == '0')
			vr=vr.substr(1,prec+1);
	}

	//pegar tamanho dos valores j? limpos
	tam = vr.length;
	
	//se tamanho for zero n?o fazer nada
	if (tam==0)
		return

	//se teclas apertadas forem numericas, backspace, del etc.... entrar em if
	if (!tecla || tecla==8 || tecla==46 || ((tecla <= 57 && tecla >= 48) || (tecla <=105 && tecla >= 96)))
	{
		//if para campos de valores fracionais ate 0,999
		if ( tam <= prec + 1)
		{
			campo.value = '0' + virgula;
			for (k=1;k<=prec-tam;k++)
			{	
				campo.value += '0' ;
			}	
			campo.value+=vr;
		}

		//if para campos com valores at? 999,999
		if ( (tam > prec) && (tam <= prec + 3) )
		{
			campo.value = vr.substr(0,tam-prec) + virgula + vr.substr(tam-prec,prec+1); 
		}

		//if para campos com valores at? 999.999,999
		if ( (tam > prec + 3) && (tam <= prec + 6) )
		{
			campo.value = vr.substr(0, tam-(prec+3)) + '.' + vr.substr(tam-(prec+3), 3) + virgula + vr.substr(tam-prec, prec+1) ; 
		}

		//if para campos com valores at? 999.999.999,999
		if ( (tam > prec + 6) && (tam <= prec + 9) ){
			campo.value = vr.substr(0, tam-(prec+6) ) + '.' + vr.substr(tam-(prec+6), 3) + '.' + vr.substr(tam-(prec+3),3 ) + virgula + vr.substr(tam-prec, prec+1); 
		}

		//if para campos com valores at? 999.999.999.999,999
		if ( (tam > prec + 9) && (tam <= prec + 12) )
		{
			campo.value = vr.substr(0, tam-(prec+9)) + '.' + vr.substr(tam-(prec+9), 3) + '.' + vr.substr(tam-(prec+6), 3) + '.' + vr.substr(tam-(prec+3), 3) + virgula + vr.substr(tam-prec,prec+1) ; 
		}

		//if para campos com valores at? 999.999.999.999.999,999
		if ( (tam > prec + 12) && (tam <= prec + 15) )
		{
			campo.value = vr.substr(0, tam-(prec+12)) + '.' + vr.substr(tam-(prec+12), 3) + '.' + vr.substr(tam-(prec+9), 3) + '.' + vr.substr(tam-(prec+6), 3) + '.' + vr.substr(tam-(prec+3), 3) + virgula + vr.substr(tam-prec,prec+1) ; 
		}

//if ( (tam >= 15) && (tam <= 17) ){
// campo.value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + virgula + vr.substr( tam - 2, tam ) ;}
//
	}

	return;
}

function getKeyCode(evt){
	var key;
	if (ExpYes)
		key = (window.external) ? event.keyCode : evt.keyCode;
	else
		key = evt.which;
	return key;
}

function selectAll(checkAll, namePattern) {
    var checked = checkAll.checked; 

    $(':checkbox[id*="' + namePattern + '"]').attr('checked', checked);

    if (checked) {
        $('div[id*="' + namePattern + '"] > div').each(function() {
            $(this).addClass('ui-state-active');
            $(this).children('span').addClass('ui-icon ui-icon-check');
        });
    } else {
        $('div[id*="' + namePattern + '"] > div').each(function() {
            $(this).removeClass('ui-state-active');
            $(this).children('span').removeClass('ui-icon ui-icon-check');
        });
    }
}


//<![CDATA[
var numericRegex = /\d+\?\d*/;
function validateNumericInput(evt, text) {
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode(key);
	if (key === '') {
		theEvent.returnValue = (text.indexOf(key) < 0);
	} else {
		var regex = /\d/;
		if (!regex.test(key)) {
			theEvent.returnValue = false;
			if (theEvent.preventDefault)
				theEvent.preventDefault();
		}
	}
}

//]]>


