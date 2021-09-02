function popunjavanjeTabele(lista) {
	let tabela = $("table#tabela_kupaca tbody");
	tabela.children().remove();
    
	for (let kupac of lista) {
		let red = $('<tr></tr>');
		let tdKorIme = $('<td>' + kupac.korisnickoIme + '</td>');
		let tdIme = $('<td>' + kupac.ime + '</td>');
		let tdPrezime = $('<td>' + kupac.prezime + '</td>');
		
		let datum = kupac.datumRodjenja.split("-");
		let tdRodjendan = $('<td>' + datum[2] + "." + datum[1] + "." + datum[0] + "."+ '</td>');
		
		let tdPol = $('<td>' + kupac.pol + '</td>');
		let tdBodovi = $('<td>' + Math.round(kupac.brojBodova * 100) / 100 + '</td>');
		let tdAktivnost = $('<td>' + kupac.aktivnost + '</td>');
		let tdIzvrisi = "<td></td>";
		if (kupac.aktivnost == "AKTIVAN")  
			tdIzvrisi = '<td><form class="brisanje"><input type="submit" id="' + kupac.korisnickoIme + 
				'+bris" value="Izbrisi" class="otkazi_dugmici"></form></td>';
		red.append(tdKorIme).append(tdIme).append(tdPrezime).append(tdRodjendan).append(tdPol)
			.append(tdBodovi).append(tdAktivnost).append(tdIzvrisi);
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
       complete: function(data, uspelo) {
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
		       complete: function(data, uspelo) {
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
});