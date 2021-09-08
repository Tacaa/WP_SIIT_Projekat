function popunjavanjeTabele(lista) {
	let tabela = $("table#tabela_prodavaca tbody");
	tabela.children().remove();
    
	for (let prodavac of lista) {
		let red = $('<tr></tr>');
		let tdKorIme = $('<td>@' + prodavac.korisnickoIme + '</td>');
		let tdIme = $('<td>' + prodavac.ime + '</td>');
		let tdPrezime = $('<td>' + prodavac.prezime + '</td>');
		
		let datum = prodavac.datumRodjenja.split("-");
		let tdRodjendan = $('<td>' + datum[2] + "." + datum[1] + "." + datum[0] + "."+ '</td>');
		
		let tdPol = $('<td>Muski</td>');
		if (prodavac.pol == "ZENSKI") tdPol = $('<td>Zenski</td>');
		let tdManif = $('<td>' + prodavac.manifestacije.length + '</td>');
		let tdAktivnost = $('<td>Izbrisan</td>');
		let tdIzvrisi = "<td></td>";
		if (prodavac.aktivnost == "AKTIVAN") {
			tdIzvrisi = '<td><form class="brisanje"><input type="submit" id="' + prodavac.korisnickoIme + 
				'+bris" value="Izbrisi" class="otkazi_dugmici"></form></td>';
			tdAktivnost = $('<td>Aktivan</td>');
		}
		red.append(tdKorIme).append(tdIme).append(tdPrezime).append(tdRodjendan).append(tdPol)
			.append(tdManif).append(tdAktivnost).append(tdIzvrisi);
		tabela.append(red);
	}
	
}

$(document).ready(function() {
	let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniAdministrator"));
    if (korisnik == null) {window.location.href = "logovanje.html";}

		//***************************************************************************************
		// pocetno stanje tabele, sve moguce karte
	let listaProdavaca = [];
	$.ajax({
       url: "rest/prodavci/",
       type:"GET",
       contentType:"application/json",
       dataType:"json",
       complete: function(data) {
			console.log(data.responseText);
			listaProdavaca = JSON.parse(data.responseText);
           	popunjavanjeTabele(listaProdavaca);

			$("form.brisanje").submit(function(event) {
				event.preventDefault();
				
				let elem = event.currentTarget.innerHTML;
				let splitDeo = elem.split('id="');
				let splitKorIme = splitDeo[1].split('+bris"');		// splitKorIme[0] == korIme
				
				window.sessionStorage.setItem("zaBrisanje", splitKorIme[0]);
				$("#izabrani_prodavac").append(document.createTextNode(splitKorIme[0]));
				modal.style.display = "block";		// prikazujem formu za potvrdu
			});
       }               
   });

		//***************************************************************************************
		// podesavanje forme
    var modal = document.getElementById("modal_prodavaca");
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
	$("form#prodavacii").submit(function(event) {
		event.preventDefault();			// ovde cemo dobavljati karte za prikaz
		let otkaceno = $('input[name=izbrisi]:checked').val();
		if (otkaceno == "DA") {
			let korIme = window.sessionStorage.getItem("zaBrisanje");
			$.ajax({
		       url: "rest/prodavci/" + korIme,
		       type:"GET",
		       contentType:"application/json",
		       dataType:"json",
		       complete: function() {
					for (let p of listaProdavaca) {
						if (p.korisnickoIme == korIme) {
							p.aktivnost = "IZBRISAN";
							break;
						}
					}
					popunjavanjeTabele(listaProdavaca);
		       }               
		   });
		}
		modal.style.display = "none";		// sakrij formu kad klikne na x
	});
				//***************************************************************************************
		// podesavanje forme
    var modal_filtera = document.getElementById("modal_filtera_prodavaca");
    var filter = document.getElementById("filter");
    var span_filter = document.getElementsByClassName("close")[1];
    var span_sort = document.getElementsByClassName("close")[2];
	var modal_sort = document.getElementById("modal_sort_prodavaca");
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
	$("form#filter_prodavaca").submit(function(event) {
		event.preventDefault();
		let ime_prodavca = $('#ime_prodavca').val();
		let prez_prodavca= $('#prez_prodavca').val();
		let korIme_prodavca = $('#korIme_prodavca').val();
		
		if (ime_prodavca == "" && prez_prodavca == "" && korIme_prodavca == "") {
			$('#greska_filtera_prodavca').text("Morate uneti bar jedan parametar pretrage :D");
			$('#greska_filtera_prodavca').css("color", "#545871");
            $("#greska_filtera_prodavca").show().delay(4000).fadeOut();
			return;
		}
		
		$.ajax({
           url: "rest/prodavci/filterProdavaca",
           type:"POST",
           data: JSON.stringify({ime: ime_prodavca, prezime: prez_prodavca, korisnickoIme: korIme_prodavca}),
           contentType:"application/json",
           dataType:"json",
           complete: function(data) {
				console.log("-------------------------------");
				console.log(data.responseText);
				listaProdavaca = JSON.parse(data.responseText);
               	popunjavanjeTabele(listaProdavaca);
				modal_filtera.style.display = "none";		// sakrij filter
           }               
       });
		
	});
	
		//***************************************************************************************
		// sortiranje
	$("form#sort_prodavaca").submit(function(event) {
		event.preventDefault();	
		if (listaProdavaca.length == 0) {
			modal_sort.style.display = "none";
			return;
		}
		let sort_po_cemu = $('input[name=sta_sortiramo]:checked').val();
		let vrsta_sorta = $('input[name=vrsta_sorta]:checked').val();
		let lista = []; let x = 0;
		let sortiraniProdavci = [listaProdavaca.length]; let y = 0;
		if (sort_po_cemu == "IME") {
			for (let p of listaProdavaca) {lista[x] = p.ime + "+" + p.korisnickoIme; x = x + 1;}
			lista.sort();
			for (let s of lista) {
				let ime = s.split("+");
				for (let pro of listaProdavaca) 
					if (pro.ime == ime[0] && pro.korisnickoIme == ime[1]) {
						if (vrsta_sorta == "true") sortiraniProdavci[y] = pro;
						else sortiraniProdavci[x - y - 1] = pro;
						y = y + 1;
						break;
					}
			}
		}
		else if (sort_po_cemu == "PREZIME") {
			for (let p of listaProdavaca) {lista[x] = p.prezime + "+" + p.korisnickoIme; x = x + 1;}
			lista.sort();
			for (let s of lista) {
				let prez = s.split("+");
				for (let pro of listaProdavaca) 
					if (pro.prezime == prez[0] && pro.korisnickoIme == prez[1]) {
						if (vrsta_sorta == "true") sortiraniProdavci[y] = pro;
						else sortiraniProdavci[x - y - 1] = pro;
						y = y + 1;
						break;
					}
			}
		}
		else if (sort_po_cemu == "KOR_IME") {
			for (let p of listaProdavaca) {lista[x] = p.korisnickoIme; x = x + 1;}
			lista.sort();
			for (let s of lista)
				for (let pro of listaProdavaca) 
					if (pro.korisnickoIme == s) {
						if (vrsta_sorta == "true") sortiraniProdavci[y] = pro;
						else sortiraniProdavci[x - y - 1] = pro;
						y = y + 1;
						break;
					}
		}
		else if (sort_po_cemu == "BR_MAN") {
		for (let p of listaProdavaca) {lista[x] = p.manifestacije.length + "+" + p.korisnickoIme; x = x + 1;}
			lista.sort();
			for (let s of lista) {
				let bodovi = s.split("+");
				for (let pro of listaProdavaca) 
					if (pro.manifestacije.length == bodovi[0] && pro.korisnickoIme == bodovi[1]) {
						if (vrsta_sorta == "true") sortiraniProdavci[y] = pro;
						else sortiraniProdavci[x - y - 1] = pro;
						y = y + 1;
						break;
					}
			}
		}
		popunjavanjeTabele(sortiraniProdavci);
		modal_sort.style.display = "none";
	});
});