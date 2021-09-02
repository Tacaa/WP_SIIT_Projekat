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
			let tdKorIme = $('<td>' + kom.kupac + '</td>');
			let tdText = $('<td>' + kom.tekst + '</td>');
			let tdOcena = $('<td>' + kom.ocena + '</td>');
			red.append(tdKorIme).append(tdText).append(tdOcena);
			tabela.append(red);
		}
	}
	else {
		let zaglavlja = $("table#tabela_komentara thead tr");
		let zagStatus = $('<td>Status</td>');
		let zagDugme = $('<td></td>');
		zaglavlja.append(zagStatus).append(zagDugme);
		
		for (let komi of manifestacija.komentari) {
			let red = $('<tr></tr>');
			let tdKorIme = $('<td>' + komi.kupac + '</td>');
			let tdText = $('<td>' + komi.tekst + '</td>');
			let tdOcena = $('<td>' + komi.ocena + '</td>');
			let tdStatus= $('<td>' + komi.status + '</td>');
			let tdDugme = $('<td></td>');
			if (komi.status == "NA_CEKANJU")
				tdDugme = $('<td><input type="submit" class="dugmici" value="Odobri"/></td>');
			red.append(tdKorIme).append(tdText).append(tdOcena).append(tdStatus).append(tdDugme);
			tabela.append(red);
		}
	}
	
});