$(document).ready(function() {
	let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniKupac"));
    if (korisnik == null) {window.location.href = "logovanje.html";}

	let teloTabele = $("table#tabela_karata tbody");
	for (let karta of korisnik.sveKarte) {
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
		teloTabele.append(red);
	}

    // Get the modal
    var modal = document.getElementById("modal_filtera");
    var filter = document.getElementById("filter");
    var span = document.getElementsByClassName("close")[0];

    filter.onclick = function() {
    	modal.style.display = "block";		// prikazujem filter
    }
    span.onclick = function() {
    	modal.style.display = "none";		// sakrij filter kad klikne na x
    }
    window.onclick = function(event) {
	    if (event.target == modal) {		// sakrij filter kad klikne van filtera
	        modal.style.display = "none";
	    }
    }

	$("form#filter_karte").submit(function(event) {
		event.preventDefault();			// ovde cemo dobavljati karte za prikaz
		alert("top");
	});
});