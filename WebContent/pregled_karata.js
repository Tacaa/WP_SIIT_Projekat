function popunjavanjeTabele(lista, tabela) {
	tabela.children().remove();
	for (let karta of lista) {
		let red = $('<tr></tr>');
		let tdID = $('<td>' + karta.id + '</td>');
		let tdManifestacija = $('<td>' + karta.manifestacija.naziv + '</td>');
		let tdkupac = $('<td>' + karta.kupac + '</td>');
		
		let vreme = karta.manifestacija.vreme.split("T");
		let splitSati = vreme[1].split(":");
		
		let tdVreme = $('<td>' + vreme[0] + "&ensp;" + splitSati[0] + ":" + splitSati[1] + '</td>');
		let tdCena = $('<td>' + Math.round(karta.konacnaCena * 100) / 100 + '</td>');
		let tdStatus = $('<td>' + karta.status + '</td>');
		let tdTip = $('<td>' + karta.tip + '</td>');
		red.append(tdID).append(tdManifestacija).append(tdkupac).append(tdVreme).append(tdCena)
			.append(tdStatus).append(tdTip);
		tabela.append(red);
	}
}

$(document).ready(function() {
	let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniKupac"));
    if (korisnik == null) {window.location.href = "logovanje.html";}

		//***************************************************************************************
		// pocetno stanje tabele, sve moguce
	let tabela = $("table#tabela_karata");
	let teloTabele = $("table#tabela_karata tbody");
	let listaKarata = korisnik.sveKarte;
	popunjavanjeTabele(listaKarata, teloTabele);

		//***************************************************************************************
		// podesavanje forme
    var modal = document.getElementById("modal_filtera");
    var filter = document.getElementById("filter");
    var span_filter = document.getElementsByClassName("close")[0];
    var span_sort = document.getElementsByClassName("close")[1];
	var modal_sort = document.getElementById("modal_sort");
    var sortiranje = document.getElementById("sortiranje");

    filter.onclick = function() {
    	modal.style.display = "block";		// prikazujem formu za filter
    }
 	sortiranje.onclick = function() {
    	modal_sort.style.display = "block";		// prikazujem formu za sort
    }
    span_filter.onclick = function() {
    	modal.style.display = "none";		// sakrij filter kad klikne na x
    }
    span_sort.onclick = function() {
    	modal_sort.style.display = "none";		// sakrij filter kad klikne na x
    }
    window.onclick = function(event) {
	    if (event.target == modal) {		// sakrij filter kad klikne van filtera
	        modal.style.display = "none";
			return;
	    }
	    if (event.target == modal_sort) {		// sakrij sort kad klikne van filtera
	        modal_sort.style.display = "none";
	    }
    }

		//***************************************************************************************
		// filtriranjee
	$("form#filter_karte").submit(function(event) {
		event.preventDefault();			// ovde cemo dobavljati karte za prikaz
		
		
		let fil_cenaMin = $('#cena_min').val();
		let fil_cenaMax = $('#cena_max').val();
		if (fil_cenaMin == "") fil_cenaMin = 0; 
		if (fil_cenaMax == "") fil_cenaMax = 0; 
		if (fil_cenaMin != 0 && fil_cenaMax != 0 && fil_cenaMin > fil_cenaMax) {
			$('#greska_filtera').text("Minimalna cena mora biti veca od maximalne :D");
			$('#greska_filtera').css("color", "#545871");
            $("#greska_filtera").show().delay(4000).fadeOut();
			return;
		} 
		
		let fil_datumMin = $('#datum_min').val();
		let fil_datumMax = $('#datum_max').val();
		if (fil_datumMin == "") fil_datumMin = null; 
		if (fil_datumMax == "") fil_datumMax = null; 
		if (fil_datumMin != null && fil_datumMax != null && fil_datumMin > fil_datumMax) {
			$("#greska_filtera").text("Minimalni datum mora biti veci od maximalnog :D");
			$('#greska_filtera').css("color", "#545871");
            $("#greska_filtera").show().delay(4000).fadeOut();
			return;
		} 
		
		let fil_naziv = $('#naziv_man').val();
		let fil_tip = $('input[name=tip_karte]:checked').val();
		let fil_status = $('input[name=status_karte]:checked').val();
		console.log(fil_status);
		
		$.ajax({
           url: "rest/karte/filterKarata",
           type:"POST",
           data: JSON.stringify({naziv: fil_naziv, cenaMin: fil_cenaMin, cenaMax: fil_cenaMax, 
				datumMin: fil_datumMin, datumMax: fil_datumMax, tip: fil_tip, status: fil_status}),
           contentType:"application/json",
           dataType:"json",
           complete: function(data, uspelo) {
				console.log(data.responseText);
				listaKarata = JSON.parse(data.responseText);
               	popunjavanjeTabele(listaKarata, teloTabele);
           }               
       });
		
	});
	
		//***************************************************************************************
		// sortiranje
	$("form#sort_karte").submit(function(event) {alert("okkkk");});
});