function popuniTabelu(komentari, tabela, modal) {
	tabela.children().remove();
	for (let komi of komentari) {
			let red = $('<tr></tr>');
			let tdKorIme = $('<td>@' + komi.kupac + '</td>');
			let tdText = $('<td>' + komi.tekst + '</td>');
			let tdOcena = $('<td>' + komi.ocena + '</td>');
			let tdStatus= $('<td>Prihvacen</td>');
			let tdDugme = $('<td></td>');
			let id = komi.kupac + "+" + komi.tekst + "+" + komi.ocena;
			if (komi.status == "ODBIJEN") tdStatus= $('<td>Odbijen</td>');
			else if (komi.status == "NA_CEKANJU") {
				tdDugme = $('<td><button id="' + id + '+odobri" class="dugmici">Odobri</button></td>');
				tdStatus= $('<td>Na cekanju</td>');
			}
			red.append(tdKorIme).append(tdText).append(tdOcena).append(tdStatus).append(tdDugme);
			tabela.append(red);
			
			let pomocnoDugme = document.getElementById(id + "+odobri");
			if (pomocnoDugme != null) pomocnoDugme.onclick = function(event) {
				event.preventDefault();
				window.sessionStorage.setItem("odobriKom",  event.currentTarget.id.split("+odobri")[0]);
				modal.style.display = "block";	
			};
		}
};

$(document).ready(function() {
	let prodavac = window.sessionStorage.getItem("trenutniProdavac");
	let admin = window.sessionStorage.getItem("trenutniAdministrator");
	
	let manifestacija = JSON.parse(window.sessionStorage.getItem("manifestacija"));
	$("span#naz_manif").append(document.createTextNode(manifestacija.naziv));
	let tabela = $("table#tabela_komentara tbody");

		// za neregistrovanog korisnika i kupca samo prihvaceni komentari
	if (prodavac == null && admin == null) {
		let lista = []; let ind = 0;
		for (let k of manifestacija.komentari) {
			if (k.status == "PRIHVACEN") {
				lista[ind] = k;
				ind = ind + 1;
			}
		}
		for (let kom of lista) {
			let red = $('<tr></tr>');
			let tdKorIme = $('<td>@' + kom.kupac + '</td>');
			let tdText = $('<td>' + kom.tekst + '</td>');
			let tdOcena = $('<td>' + kom.ocena + '</td>');
			red.append(tdKorIme).append(tdText).append(tdOcena);
			tabela.append(red);
		}
	}
	else {
		let zaglavlja = $("table#tabela_komentara thead tr");
		let zagStatus = $('<td>Status</td>');
		let zagDugme = $('<td>Odobri</td>');
		zaglavlja.append(zagStatus).append(zagDugme);
		
		var modal = document.getElementById("modal_odob");
		var x_rezervisi = document.getElementsByClassName("close")[0];
	    x_rezervisi.onclick = function() {
	    	modal.style.display = "none";		// sakrij formu kad klikne na x
	    }
	    window.onclick = function(event) {
		    if (event.target == modal) {		// sakrij formu kad klikne van forme
		        modal.style.display = "none";
				return;
		    }
	    }

		popuniTabelu(manifestacija.komentari, tabela, modal);
		
		$("form#odobri_kom").submit(function(event) {
			event.preventDefault();
			let korisnikTextOCena = window.sessionStorage.getItem("odobriKom");
			let odobri = $('input[name=prihvati]:checked').val();
			let parametar = korisnikTextOCena + "+" + odobri;
			$.ajax({
				url: "rest/komentari/odobri/" + parametar,
				type:"GET",
				complete: function(data) {
					console.log(data.responseText);
					document.getElementById("modal_odob").style.display = "none";
					let ind = 0;
					for (let k of manifestacija.komentari) {
						if (korisnikTextOCena == (k.kupac + "+" + k.tekst + "+" + k.ocena) 
								&& k.status == "NA_CEKANJU") {
							if (odobri == "DA") manifestacija.komentari[ind].status = "PRIHVACEN";
							else manifestacija.komentari[ind].status= "ODBIJEN";
							break;
						}
						ind = ind + 1;
					}
					popuniTabelu(manifestacija.komentari, tabela, modal);
					window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
				}
			});
		});
	}
	
});