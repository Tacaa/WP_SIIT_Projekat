function popunjavanjeTabele(lista) {
	let tabela = $("table#tabela_prodavaca tbody");
	tabela.children().remove();
    
	for (let prodavac of lista) {
		let red = $('<tr></tr>');
		let tdKorIme = $('<td>' + prodavac.korisnickoIme + '</td>');
		let tdIme = $('<td>' + prodavac.ime + '</td>');
		let tdPrezime = $('<td>' + prodavac.prezime + '</td>');
		
		let datum = prodavac.datumRodjenja.split("-");
		let tdRodjendan = $('<td>' + datum[2] + "." + datum[1] + "." + datum[0] + "."+ '</td>');
		
		let tdPol = $('<td>' + prodavac.pol + '</td>');
		let tdManif = $('<td>' + prodavac.manifestacije.length + '</td>');
		let tdAktivnost = $('<td>' + prodavac.aktivnost + '</td>');
		let tdIzvrisi = "<td></td>";
		if (prodavac.aktivnost == "AKTIVAN")  
			tdIzvrisi = '<td><form class="brisanje"><input type="submit" id="' + prodavac.korisnickoIme + 
				'+bris" value="Izbrisi" class="otkazi_dugmici"></form></td>';
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
       complete: function(data, uspelo) {
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
		       complete: function(data, uspelo) {
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
});