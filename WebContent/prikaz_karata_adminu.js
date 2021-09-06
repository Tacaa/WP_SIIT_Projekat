$(document).ready(function() {
	let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniAdministrator"));
    if (korisnik == null) {window.location.href = "logovanje.html";}

		//***************************************************************************************
		// pocetno stanje tabele, sve moguce karte
	let manifestacija = JSON.parse(window.sessionStorage.getItem("manifestacija"));
	let listaKarata = manifestacija.karte;
	
	$("#naz_manif").append(document.createTextNode(manifestacija.naziv));
	let tabela = $("table#tabela_karata tbody");
	
	for (let karta of listaKarata) {
		let red = $('<tr></tr>');
		let tdkupac = $('<td>' + karta.kupac + '</td>');
		let tdCena = $('<td>' + Math.round(karta.konacnaCena * 100) / 100 + '</td>');
		let tdTip = $('<td>' + karta.tip + '</td>');
		let tdStatus = $('<td>' + karta.status + '</td>');
		
		red.append(tdkupac).append(tdCena).append(tdStatus).append(tdTip);
		tabela.append(red);
	}
});