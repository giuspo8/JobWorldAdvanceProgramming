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


$(document).ready(function(){
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
	if (dict_region[region]!= null){
		$('input[name="region"]').val(dict_region[region]);
	}
	if (dict_town[town]!= null){
		$('input[name="town"]').val(dict_town[town]);
	}
});