/**
 * Javascript per la funzione mostra di più/meno
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
$(document).ready(function() {
	$('input[name=type]:radio').change(function() {
	    if (this.value == 'person') {
	        $("#regform_div").html("<table style='text-align: center; width: 500px; margin: auto;'><tr><td><label class='searchsub_lbl'>E-mail</label></td></tr><tr><td><input placeholder='E-mail' name='email' class='searchsub_input'></td></tr></table><table  style='text-align: center; width: 500px; margin: auto;'><tr><td><label class='searchsub_lbl'>Password</label></td><td><label class='searchsub_lbl'>Ripeti Password</label></td></tr><tr><td><input type='password' id='p' placeholder='Password' name='password' class='searchsub_input'></td><td><input type='password' id='rp' oninput='passvalid()' placeholder='Ripeti Password' class='searchsub_input'></td></tr><tr><td><label class='searchsub_lbl'>Nome</label></td><td><label class='searchsub_lbl'>Cognome</label></td></tr><tr><td><input placeholder='Nome' name='firstName' class='searchsub_input'></td><td><input placeholder='Cognome' name='secondName' class='searchsub_input'></td></tr><tr><td><label class='searchsub_lbl'>Data di nascita</label></td><td><label class='searchsub_lbl'>Numero di telefono</label></td><tr><td><input placeholder='Data di nascita' name='birthDate' class='searchsub_input'></td> <td><input placeholder='Numero di telefono' name='number' class='searchsub_input'></td></tr></table><table style='text-align: center; width: 500px; margin: auto;'><tr><td><label class='searchsub_lbl'>Interessi</label><select multiple name='interests' class='searchsub_input'><option value='' selected>Interessi</option><option value='INFORMATICA'>Informatica</option><option value='ECONOMIA'>Economia</option></select></td></tr></table><div style='text-align:center' id='searchsub'><input disabled id='submit' type='submit' value='Invio'></div>");
	    }
	    else if (this.value == 'company') {
	    	$("#regform_div").html("<table style='text-align: center; width: 500px; margin: auto;'><tr><td><label class='searchsub_lbl'>E-mail</label></td></tr><tr><td><input placeholder='E-mail' name='email' class='searchsub_input'></td></tr></table><table style='text-align: center; width: 500px; margin: auto;'><tr><td><label class='searchsub_lbl'>Password</label></td><td><label class='searchsub_lbl'>Ripeti Password</label></td></tr><tr><td><input type='password' id='p' placeholder='Password' name='password' class='searchsub_input'></td><td><input type='password' id='rp' oninput='passvalid()' placeholder='Ripeti Password' class='searchsub_input'></td></tr></table><table style='text-align: center; width: 500px; margin: auto;'><tr><td><label class='searchsub_lbl'>Nome Azienda</label></td></tr><tr><td><input placeholder='Nome Azienda' name='name' class='searchsub_input'></td></tr></table><div style='text-align:center' id='searchsub'><input disabled id='submit' type='submit' value='Invio'></div>");
	    }
	});
    var char_lim = 400;
    var dots = "...";
    var more_t = "Mostra di più";
    var less_t = "Mostra di meno";
    $('.more').each(function() {
        var content = $(this).html();
        if(content.length > char_lim) {
            var c = content.substr(0, char_lim);
            var h = content.substr(char_lim, content.length - char_lim);
 
            var html = c + '<span>' + dots + '&nbsp;</span><span class="morecontent"><span>' + h +
            			   '</span>&nbsp;&nbsp;<a href="" class="morelink">' + more_t + '</a></span>';
            $(this).html(html);
        } 
    }); 
    $(".morelink").click(function(){
        if($(this).hasClass("less")) {
            $(this).removeClass("less");
            $(this).html(more_t);
        } else {
            $(this).addClass("less");
            $(this).html(less_t);
        }
        $(this).parent().prev().toggle();
        $(this).prev().toggle();
        return false;
    });
});


function form_filter (data){
	var dict_region= {
			"Latium" : "Lazio",
			"Lombardy" : "Lombardia",
			"Piedmont" : "Piemonte",
			"Sicily" : "Sicilia",
			"Tuscany" : "Toscana",
			"Sardinia": "Sardegna",
			"Molise" : "Molise",
			"Apulia" : "Puglia",
	};
	var dict_town= {
			"Rome": "Roma",
			"Milan" : "Milano",
			"Truin" : "Torino",
			"Genoa" : "Genova",
			"Florence" : "Firenze",
			"Venice":"Venezia",
			"Naples":"Napoli",
	}
	var region=$('input[name="region"]').val();
	var town=$('input[name="town"]').val();
	var provincia;
	if (dict_region[region]!= null){
		$('input[name="region"]').val(dict_region[region]);
	}
	if (dict_town[town]!= null){
		$('input[name="town"]').val(dict_town[town]);
	}
	$.each(data, function(index, element){
		if (element.nome==$('input[name="town"]').val()){
			provincia = element.provincia.nome;
		}
	});
	if (provincia != null) {
		$('input[name="province"]').val(provincia);
	} else {
		temp=$('input[name="town"]').val();
		$('input[name="province"]').val(temp);
	}
};

$.ajax({
    url : "https://raw.githubusercontent.com/matteocontrini/comuni-json/master/comuni.json",
    dataType : 'json', //restituisce un oggetto JSON
    success : function (data,stato) {
    	form_filter(data);
    },
    error : function (richiesta,stato,errori) {
        alert("E' evvenuto un errore. Il stato della chiamata: "+stato);
    },
});


function passvalid() {
	if($('#rp').val() != $('#p').val()) {
		$("#submit").attr("disabled", true).addClass("searchsub_hover");
		$('#rp').addClass("error searchsub_input_"); $('#p').addClass("error searchsub_input_");
	} else {
		$("#submit").attr("disabled", false).removeClass("searchsub_hover");
		$('#rp').removeClass("error searchsub_input_"); $('#p').removeClass("error searchsub_input_");
	}
}


