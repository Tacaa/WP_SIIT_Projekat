function popunjavanjeTabele(lista) {
	let tabela = $("table#tabela_karata tbody");
	tabela.children().remove();
	
    var danas = new Date();
    var dan = danas.getDate();
    var mesec = danas.getMonth()+1; //januar je 0
    var godina = danas.getFullYear();
    if(dan<10) dan='0'+dan;
    if(mesec<10) mesec='0'+mesec;
    danas = godina + '-' + mesec + '-' + dan;
    
	for (let karta of lista) {
		let red = $('<tr></tr>');
		let tdManifestacija = $('<td>' + karta.manifestacija.naziv + '</td>');
		let tdAdresa = $('<td>' + karta.manifestacija.lokacija.drzava + ", " + 
			karta.manifestacija.lokacija.grad + ", " +
			karta.manifestacija.lokacija.ulicaBroj + '</td>');
		let tdkupac = $('<td>' + karta.kupac + '</td>');
		
		let vreme = karta.manifestacija.vreme.split("T");
		let datum = vreme[0].split("-");
		let splitSati = vreme[1].split(":");
		
		let tdVreme = $('<td>' + datum[2] + "." + datum[1] + "." + datum[0] + "."
			 + "&ensp;" + splitSati[0] + ":" + splitSati[1] + '</td>');
		let tdCena = $('<td>' + Math.round(karta.konacnaCena * 100) / 100 + '</td>');
		let tdStatus = $('<td>' + karta.status + '</td>');
		let tdTip = $('<td>' + karta.tip + '</td>');
		let tdOtkazi = "<td></td>";
		if (danas <= vreme[0] && karta.status == "REZERVISANA")  
			tdOtkazi = '<td><form class="otkazivanje"><input type="submit" id="' + karta.id + 
				'" value="Otkazi" class="otkazi_dugmici"></form></td>';
		red.append(tdManifestacija).append(tdAdresa).append(tdkupac).append(tdVreme).append(tdCena)
			.append(tdStatus).append(tdTip).append(tdOtkazi);
		tabela.append(red);
	}
	
}

$(document).ready(function() {
	let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniKupac"));
    if (korisnik == null) {window.location.href = "logovanje.html";}

		//***************************************************************************************
		// pocetno stanje tabele, sve moguce karte
	let listaKarata = korisnik.sveKarte;
	popunjavanjeTabele(listaKarata);

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
               	popunjavanjeTabele(listaKarata);
				modal.style.display = "none";
           }               
       });
		
	});
	
		//***************************************************************************************
		// sortiranje
	$("form#sort_karte").submit(function(event) {
		event.preventDefault();			// ovde cemo dobavljati karte za prikaz
		if (listaKarata.length == 0) {
			modal_sort.style.display = "none";
			return;
		}
		let sort_po_cemu = $('input[name=sta_sortiramo]:checked').val();
		let vrsta_sorta = $('input[name=vrsta_sorta]:checked').val();
		let lista = []; let x = 0;
		let sortiraneKarte = [listaKarata.length]; let y = 0;
		if (sort_po_cemu == "NAZIV") {
			for (let k of listaKarata) {lista[x] = k.manifestacija.naziv + "+" + k.id; x = x + 1;}
			lista.sort();
			for (let s of lista) {
				let naz = s.split("+");
				for (let ka of listaKarata) 
					if (ka.manifestacija.naziv == naz[0] && ka.id == naz[1]) {
						if (vrsta_sorta == "true") sortiraneKarte[y] = ka;
						else sortiraneKarte[x - y - 1] = ka;
						y = y + 1;
						break;
					}
			}
		}
		else if (sort_po_cemu == "VREME") {
			for (let k of listaKarata) {lista[x] = k.manifestacija.vreme + "+" + k.id; x = x + 1;}
			lista.sort();
			for (let s of lista)  {
				let vr = s.split("+");
				for (let ka of listaKarata)
					if (ka.manifestacija.vreme == vr[0] && ka.id == vr[1]) {
						if (vrsta_sorta == "true") sortiraneKarte[y] = ka;
						else sortiraneKarte[x - y - 1] = ka;
						y = y + 1;
						break;
					}
			}
		}
		else if (sort_po_cemu == "CENA") {
			for (let k of listaKarata) {lista[x] = k.konacnaCena + "+" + k.id; x = x + 1;}
			lista.sort();
			for (let s of lista)
				for (let ka of listaKarata) {
					let cenica = s.split("+");
					if (ka.konacnaCena == cenica[0] && ka.id == cenica[1]) {
						if (vrsta_sorta == "true") sortiraneKarte[y] = ka;
						else sortiraneKarte[x - y - 1] = ka;
						y = y + 1;
						break;
					}
				}
					
		}
		else if (sort_po_cemu == "LOKACIJA") {
			for (let k of listaKarata) {
				lista[x] = k.manifestacija.lokacija.grad + "+" + k.manifestacija.lokacija.ulicaBroj
					+ "+" + k.id;
				x = x + 1;
			}
			lista.sort();
			for (let s of lista)
				for (let ka of listaKarata) {
					let adr = s.split("+");
					if (ka.manifestacija.lokacija.grad == adr[0] && 
							ka.manifestacija.lokacija.ulicaBroj == adr[1] &&
							ka.id == adr[2]) {
						if (vrsta_sorta == "true") sortiraneKarte[y] = ka;
						else sortiraneKarte[x - y - 1] = ka;
						y = y + 1;
						break;
					}
				}
		}
		popunjavanjeTabele(sortiraneKarte);
		modal_sort.style.display = "none";
	});
	
	$("form.otkazivanje").submit(function(event) {
		let elem = event.currentTarget.innerHTML;
		let splitId = elem.split('id="');
		let splitvalue = splitId[1].split('" type=');		// splitvalue[0] == id
		event.preventDefault();
		
		$.ajax({
           url: "rest/karte/otkaziKartu/" + splitvalue[0],
           type:"GET",
		   dataType:"json",
           complete: function(data, uspelo) {
				window.sessionStorage.setItem("trenutniKupac", data.responseText)
				for (let k of listaKarata) {
					if (k.id == splitvalue[0]) k.status = "OBUSTAVLJENA";
				}
				popunjavanjeTabele(listaKarata);
			}
		});            
	});
});