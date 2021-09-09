$(document).ready(function() {
	let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniAdministrator"));
	let korisnik2 = JSON.parse(window.sessionStorage.getItem("trenutniProdavac"));
  	if (korisnik == null && korisnik2==null) {window.location.href = "logovanje.html";}

		//***************************************************************************************
		// pocetno stanje tabele, sve moguce karte
	let manifestacija = JSON.parse(window.sessionStorage.getItem("manifestacija"));
	let listaKarata = manifestacija.karte;
	
	$("#naz_manif").append(document.createTextNode(manifestacija.naziv));
	let tabela = $("table#tabela_karata tbody");
	
	for (let karta of listaKarata) {
		let red = $('<tr></tr>');
		let tdkupac = $('<td>@' + karta.kupac + '</td>');
		let tdCena = $('<td>' + Math.round(karta.konacnaCena * 100) / 100 + '</td>');
		let tdTip = $('<td>Regularna</td>');
		if (karta.tip == "FAN_PIT") tdTip = $('<td>Fan pit</td>');
		else if (karta.tip == "VIP") tdTip = $('<td>Vip</td>');
		let tdStatus = $('<td>Rezervisana</td>');
		if (karta.status == "OBUSTAVLJENA") tdStatus = $('<td>Obustavljena</td>');
		
		red.append(tdkupac).append(tdCena).append(tdStatus).append(tdTip);
		tabela.append(red);
	}
});