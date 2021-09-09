function popunjavanjeTabele(lista) {
	let tabela = $("table#tabela_kupaca tbody");
	tabela.children().remove();
    
	for (let kupac of lista) {
		let red = $('<tr></tr>');
		let tdKorIme = $('<td>@' + kupac.korisnickoIme + '</td>');
		let tdIme = $('<td>' + kupac.ime + '</td>');
		let tdPrezime = $('<td>' + kupac.prezime + '</td>');
		
		let datum = kupac.datumRodjenja.split("-");
		let tdRodjendan = $('<td>' + datum[2] + "." + datum[1] + "." + datum[0] + "."+ '</td>');
		
		let tdPol = $('<td>Muski</td>');
		if (kupac.pol == "ZENSKI") tdPol = $('<td>Zenski</td>');
		let tdBodovi = $('<td>' + Math.round(kupac.brojBodova * 100) / 100 + '</td>');
		let tdTip = $('<td>Obicni</td>');
		if (kupac.tip.ime == "BRONZANI") tdTip = $('<td>Bronzani</td>');
		else if (kupac.tip.ime == "SREBRNI") tdTip = $('<td>Srebrni</td>');
		else if (kupac.tip.ime == "ZLATNI") tdTip = $('<td>Zlatni</td>');
		let tdAktivnost = $('<td>Izbrisan</td>');
		let tdIzvrisi = "<td></td>";
		if (kupac.aktivnost == "BLOKIRAN") tdAktivnost = $('<td>Blokiran</td>');
		else if (kupac.aktivnost == "AKTIVAN") {
			tdIzvrisi = '<td><form class="brisanje"><input type="submit" id="' + kupac.korisnickoIme + 
				'+bris" value="Izbrisi" class="otkazi_dugmici"></form></td>';
			tdAktivnost = $('<td>Aktivan</td>');
		}
		red.append(tdKorIme).append(tdIme).append(tdPrezime).append(tdRodjendan).append(tdPol)
			.append(tdBodovi).append(tdTip).append(tdAktivnost).append(tdIzvrisi);
		tabela.append(red);
	}
	
}

$(document).ready(function() {
	let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniAdministrator"));
    if (korisnik == null) {window.location.href = "logovanje.html";}

		//***************************************************************************************
		// pocetno stanje tabele, sve moguce karte
	let listaKupaca = [];
	$.ajax({
       url: "rest/kupci/",
       type:"GET",
       contentType:"application/json",
       dataType:"json",
       complete: function(data) {
			console.log(data.responseText);
			listaKupaca = JSON.parse(data.responseText);
           	popunjavanjeTabele(listaKupaca);

			$("form.brisanje").submit(function(event) {
				event.preventDefault();
				
				let elem = event.currentTarget.innerHTML;
				let splitDeo = elem.split('id="');
				let splitKorIme = splitDeo[1].split('+bris"');		// splitKorIme[0] == korIme
				
				window.sessionStorage.setItem("zaBrisanje", splitKorIme[0]);
				$("#izabrani_kupac").append(document.createTextNode(splitKorIme[0]));
				modal.style.display = "block";		// prikazujem formu za potvrdu
			});
       }               
   });

		//***************************************************************************************
		// podesavanje forme
    var modal = document.getElementById("modal_kupaca");
    var span = document.getElementsByClassName("close")[0];

    span.onclick = function() {
    	modal.style.display = "none";		// sakrij formu kad klikne na x
    }
    window.onclick = function(event) {
	    if (event.target == modal) {		// sakrij formu kad klikne van nje
	        modal.style.display = "none";
			return;
	    }
    }

		//***************************************************************************************
		// brisanjeee
	$("form#kupcii").submit(function(event) {
		event.preventDefault();			// ovde cemo dobavljati karte za prikaz
		let otkaceno = $('input[name=izbrisi]:checked').val();
		if (otkaceno == "DA") {
			let korIme = window.sessionStorage.getItem("zaBrisanje");
			$.ajax({
		       url: "rest/kupci/" + korIme,
		       type:"GET",
		       contentType:"application/json",
		       dataType:"json",
		       complete: function() {
					for (let k of listaKupaca) {
						if (k.korisnickoIme == korIme) {
							k.aktivnost = "IZBRISAN";
							break;
						}
					}
					popunjavanjeTabele(listaKupaca);
		       }               
		   });
		}
		modal.style.display = "none";		// sakrij formu kad klikne na x
	});
		
		//***************************************************************************************
		// podesavanje forme
    var modal_filtera = document.getElementById("modal_filtera_kupaca");
    var filter = document.getElementById("filter");
    var span_filter = document.getElementsByClassName("close")[1];
    var span_sort = document.getElementsByClassName("close")[2];
	var modal_sort = document.getElementById("modal_sort_kupaca");
    var sortiranje = document.getElementById("sortiranje");

    filter.onclick = function() {
    	modal_filtera.style.display = "block";		// prikazujem formu za filter
    }
 	sortiranje.onclick = function() {
    	modal_sort.style.display = "block";		// prikazujem formu za sort
    }
    span_filter.onclick = function() {
    	modal_filtera.style.display = "none";		// sakrij filter kad klikne na x
    }
    span_sort.onclick = function() {
    	modal_sort.style.display = "none";		// sakrij filter kad klikne na x
    }
    window.onclick = function(event) {
	    if (event.target == modal_filtera) {		// sakrij filter kad klikne van filtera
	        modal_filtera.style.display = "none";
			return;
	    }
	    if (event.target == modal_sort) {		// sakrij sort kad klikne van filtera
	        modal_sort.style.display = "none";
	    }
    }

	//***************************************************************************************
		// filtriranjee
	$("form#filter_kupaca").submit(function(event) {
		event.preventDefault();
		let ime_kupca = $('#ime_kupca').val();
		let prez_kupca= $('#prez_kupca').val();
		let korIme_kupca = $('#korIme_kupca').val();
		let tip_kupca = $('input[name=tip_kupca]:checked').val();
		
		$.ajax({
           url: "rest/kupci/filterKupaca",
           type:"POST",
           data: JSON.stringify({ime: ime_kupca, prezime: prez_kupca, korisnickoIme: korIme_kupca, 
				tip: {ime: tip_kupca}}),
           contentType:"application/json",
           dataType:"json",
           complete: function(data) {
				console.log("-------------------------------");
				console.log(data.responseText);
				listaKupaca = JSON.parse(data.responseText);
               	popunjavanjeTabele(listaKupaca);
				modal_filtera.style.display = "none";
           }               
       });
		
	});
	
		//***************************************************************************************
		// sortiranje
	$("form#sort_kupaca").submit(function(event) {
		event.preventDefault();
		if (listaKupaca.length == 0) {
			modal_sort.style.display = "none";
			return;
		}
		let sort_po_cemu = $('input[name=sta_sortiramo]:checked').val();
		let vrsta_sorta = $('input[name=vrsta_sorta]:checked').val();
		let lista = []; let x = 0;
		let sortiraniKupci = [listaKupaca.length]; let y = 0;
		if (sort_po_cemu == "IME") {
			for (let k of listaKupaca) {lista[x] = k.ime + "+" + k.korisnickoIme; x = x + 1;}
			lista.sort();
			for (let s of lista) {
				let ime = s.split("+");
				for (let kor of listaKupaca) 
					if (kor.ime == ime[0] && kor.korisnickoIme == ime[1]) {
						if (vrsta_sorta == "true") sortiraniKupci[y] = kor;
						else sortiraniKupci[x - y - 1] = kor;
						y = y + 1;
						break;
					}
			}
		}
		else if (sort_po_cemu == "PREZIME") {
			for (let k of listaKupaca) {lista[x] = k.prezime + "+" + k.korisnickoIme; x = x + 1;}
			lista.sort();
			for (let s of lista) {
				let prez = s.split("+");
				for (let kor of listaKupaca) 
					if (kor.prezime == prez[0] && kor.korisnickoIme == prez[1]) {
						if (vrsta_sorta == "true") sortiraniKupci[y] = kor;
						else sortiraniKupci[x - y - 1] = kor;
						y = y + 1;
						break;
					}
			}
		}
		else if (sort_po_cemu == "KOR_IME") {
			for (let k of listaKupaca) {lista[x] = k.korisnickoIme; x = x + 1;}
			lista.sort();
			for (let s of lista)
				for (let kor of listaKupaca) 
					if (kor.korisnickoIme == s) {
						if (vrsta_sorta == "true") sortiraniKupci[y] = kor;
						else sortiraniKupci[x - y - 1] = kor;
						y = y + 1;
						break;
					}
		}
		else if (sort_po_cemu == "BR_BODOVA") {
		for (let k of listaKupaca) {lista[x] = k.brojBodova + "+" + k.korisnickoIme; x = x + 1;}
			lista.sort();
			for (let s of lista) {
				let bodovi = s.split("+");
				for (let kor of listaKupaca) 
					if (kor.brojBodova == bodovi[0] && kor.korisnickoIme == bodovi[1]) {
						if (vrsta_sorta == "true") sortiraniKupci[y] = kor;
						else sortiraniKupci[x - y - 1] = kor;
						y = y + 1;
						break;
					}
			}
		}
		popunjavanjeTabele(sortiraniKupci);
		modal_sort.style.display = "none";
	});
});