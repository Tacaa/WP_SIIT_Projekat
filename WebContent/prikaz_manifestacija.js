function podesi_manifestaciju(tagA) { 
	window.sessionStorage.setItem("manifestacija", tagA.id.split("+pm")[0])
};

function popuniSadrzaj(manifestacije) {
	for(let manifestacija of manifestacije){
			let div = document.getElementById("sadrzaj_manifestacija");
		
			//napravljen novi div za smjestanje elementa sa class="element_sadrzaja"
			let noviDiv = document.createElement("div");
			let klasaDiva = document.createAttribute("class");
			klasaDiva.value = "element_sadrzaja";
			noviDiv.setAttributeNode(klasaDiva);

			//pravimo div za sliku
			let divZaSliku = document.createElement("div");
			let klasaDivaZaSliku = document.createAttribute("class");
			klasaDivaZaSliku.value = "div_za_sliku";
			divZaSliku.setAttributeNode(klasaDivaZaSliku);
			
			//tu cemo smjestiti slike u zavisnosti od tipa dogadjaja
			let slika = document.createElement("img");
			let putanjaSlike = document.createAttribute("src");
			putanjaSlike.value = manifestacija.poster;
			slika.setAttributeNode(putanjaSlike);
			let link = document.createElement("a");
			let putanjaLinka = document.createAttribute("href");
			putanjaLinka.value = "jedna_manifestacija.html";
			link.setAttributeNode(putanjaLinka);
			link.appendChild(slika);
				// da bi podesilo manifestaciju
			link.setAttribute("onclick", "return podesi_manifestaciju(this);");	
			link.setAttribute("id", JSON.stringify(manifestacija) + "+pm");
			divZaSliku.appendChild(link);
			
			//pravimo div za text
			let divZaTekst = document.createElement("div");
			let klasaDivaZaTekst = document.createAttribute("class");
			klasaDivaZaTekst.value = "div_teksta";
			divZaTekst.setAttributeNode(klasaDivaZaTekst);

			//dodati tekst u div za tekst
			let imeManifestacije = document.createTextNode(manifestacija.naziv);
			let datumVrijeme = manifestacija.vreme.split('T');
			let datum = datumVrijeme[0].split('-');
			let vrijeme = datumVrijeme[1].split(':');
			let mesto = manifestacija.lokacija.drzava + ", " + manifestacija.lokacija.grad + ", " + manifestacija.lokacija.ulicaBroj;
			
			let vremeZaUpis =  document.createTextNode(datum[2] + "." + datum[1] + "." + datum[0]  + ".  "
				 + vrijeme[0] + ":" + vrijeme[1]);
			let mestoOdrzavanja = document.createTextNode(mesto);
			let cena = document.createTextNode("Cena: " + manifestacija.cena);
			
			//dodali smo tekst u div
			let divZaTekstIme = document.createElement("div");
			let k1 = document.createAttribute("class");
			k1.value = "d1";
			divZaTekstIme.setAttributeNode(k1);
			divZaTekstIme.appendChild(imeManifestacije);
			
			let divZaTekstMesto= document.createElement("div");
			let k2 = document.createAttribute("class");
			k2.value = "d1";
			divZaTekstMesto.setAttributeNode(k2);
			divZaTekstMesto.appendChild(mestoOdrzavanja);
			
			let divZaTekstVrijeme = document.createElement("div");
			let k3 = document.createAttribute("class");
			k3.value = "d1";
			divZaTekstVrijeme.setAttributeNode(k3);
			divZaTekstVrijeme.appendChild(vremeZaUpis);
			
			let divZaTekstCena = document.createElement("div");
			let k4 = document.createAttribute("class");
			k4.value = "d1";
			divZaTekstCena.setAttributeNode(k4);
			divZaTekstCena.appendChild(cena);
			
			divZaTekst.appendChild(divZaTekstIme);
			divZaTekst.appendChild(divZaTekstMesto);
			divZaTekst.appendChild(divZaTekstVrijeme);
			divZaTekst.appendChild(divZaTekstCena);
	
			//dodavanje svega
			noviDiv.appendChild(divZaSliku);
			noviDiv.appendChild(divZaTekst);
			div.appendChild(noviDiv);
			
			let divPrazan = document.createElement("div");
			let k6 = document.createAttribute("class");
			k6.value = "prazan";
			divPrazan.setAttributeNode(k6);
			div.appendChild(divPrazan);
	}
};

$(document).ready(function(){
	let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniAdministrator"));
    if (korisnik == null) {window.location.href = "logovanje.html";}
	$.ajax({
		url: "rest/manifestacije/sveAktivne",
		type:"GET",
		dataType:"json",
		complete: function(data) {
			console.log(data.responseText);
			let manifestacije = JSON.parse(data.responseText);
			popuniSadrzaj(manifestacije);
		}
	});

});
