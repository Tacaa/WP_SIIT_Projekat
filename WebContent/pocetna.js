
//FUNKCIJE ....

function popuniSadrzaj(manifestacije) {

	//console.log(manifestacije);
	for(let manifestacija of manifestacije){
			let div = document.getElementById("sadrzaj");
		
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
			if(manifestacija.tip == "KONCERT"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "koncert.png";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else if(manifestacija.tip == "FESTIVAL"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "festival.jpg";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else if(manifestacija.tip == "POZORISNA_PREDSTAVA"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "pozoriste.jpg";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else if(manifestacija.tip == "BIOSKOPSKA_PROJEKCIJA"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "kino.jpg";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else if(manifestacija.tip == "SPORTSKA_NADMETANJA"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "utakmica.jpg";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else if(manifestacija.tip == "PLESNI_NASTUP"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "ples.png";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else if(manifestacija.tip == "STANDUP_NASTUP"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "standup.jpg";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else if(manifestacija.tip == "NASTUP_CIRKUSA"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "cirkus.jpg";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else if(manifestacija.tip == "SAJAM"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "sajam.jpg";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else if(manifestacija.tip == "MUZEJSKO_VECE"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "muzej.jpg";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else if(manifestacija.tip == "KNJIZEVNO_VECE"){
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "knjizevno_vece.png";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}else{
				let slika = document.createElement("img");
				let putanjaSlike = document.createAttribute("src");
				putanjaSlike.value = "drugo.jpg";
				slika.setAttributeNode(putanjaSlike);
				let link = document.createElement("a");
				let putanjaLinka = document.createAttribute("href");
				putanjaLinka.value = "jedna_manifestacija.html";
				link.setAttributeNode(putanjaLinka);
				link.appendChild(slika);
				divZaSliku.appendChild(link);
				window.sessionStorage.setItem("manifestacija", JSON.stringify(manifestacija));
			}
			
			
	

			//pravimo div za text
			let divZaTekst = document.createElement("div");
			let klasaDivaZaTekst = document.createAttribute("class");
			klasaDivaZaTekst.value = "div_za_tekst";
			divZaTekst.setAttributeNode(klasaDivaZaTekst);

			
			//dodati tekst u div za tekst
			let imeManifestacije = document.createTextNode("NAZIV: " +  manifestacija.naziv);
			let datumVrijeme = manifestacija.vreme.split('T');
			let datum = datumVrijeme[0].split('-');
			let vrijeme = datumVrijeme[1].split(':');
			let datumZaUpis = document.createTextNode("DATUM: " + datum[2] + "." + datum[1] + "." + datum[0]  + ".");
			let vrijemeZaUpis =  document.createTextNode("VRIJEME: " + vrijeme[0] + ":" + vrijeme[1]);
			let mjestoZaUpis = document.createTextNode("GRAD: " + manifestacija.lokacija.grad);
			let drzavaZaUpis = document.createTextNode("DRZAVA: " +manifestacija.lokacija.drzava);
			
			let adresaZaUpis = document.createTextNode(manifestacija.lokacija.adresa);
			let tipManif = document.createTextNode("TIP: " + manifestacija.tip)
			let cijena = document.createTextNode("CIJENA: " + manifestacija.cena);
			let ocjena = document.createTextNode("OCJENA: " + manifestacija.ocena)
			
			//dodali smo tekst u div
			let divZaTekstIme = document.createElement("div");
			let k1 = document.createAttribute("class");
			k1.value = "d1";
			divZaTekstIme.setAttributeNode(k1);
			divZaTekstIme.appendChild(imeManifestacije);
			
			let divZaTekstDatum = document.createElement("div");
			let k2 = document.createAttribute("class");
			k2.value = "d1";
			divZaTekstDatum.setAttributeNode(k2);
			divZaTekstDatum.appendChild(datumZaUpis);
			
			let divZaTekstVrijeme = document.createElement("div");
			let k3 = document.createAttribute("class");
			k3.value = "d1";
			divZaTekstVrijeme.setAttributeNode(k3);
			divZaTekstVrijeme.appendChild(vrijemeZaUpis);
			
			
			let divZaTekstMjesto = document.createElement("div");
			let k44 = document.createAttribute("class");
			k44.value = "d1";
			divZaTekstMjesto.setAttributeNode(k44);
			divZaTekstMjesto.appendChild(mjestoZaUpis);
			
			let divZaTekstDrzava = document.createElement("div");
			let k55 = document.createAttribute("class");
			k55.value = "d1";
			divZaTekstDrzava.setAttributeNode(k55);
			divZaTekstDrzava.appendChild(drzavaZaUpis);
			
			
			let divic1 = document.createElement("div");
			let klasic1 = document.createAttribute("class");
			klasic1.value = "d1";
			divic1.setAttributeNode(klasic1);
			divic1.appendChild(adresaZaUpis);
			
			let divic2 = document.createElement("div");
			let klasic2 = document.createAttribute("class");
			klasic2.value = "d1";
			divic2.setAttributeNode(klasic2);
			divic2.appendChild(tipManif);
			
			let divic3 = document.createElement("div");
			let klasic3 = document.createAttribute("class");
			klasic3.value = "d1";
			divic3.setAttributeNode(klasic3);
			divic3.appendChild(cijena);
			
			let divic4 = document.createElement("div");
			let klasic4 = document.createAttribute("class");
			klasic4.value = "d1";
			divic4.setAttributeNode(klasic4);
			divic4.appendChild(ocjena);
			
			
			
			divZaTekst.appendChild(divZaTekstIme);
			divZaTekst.appendChild(divic2);
			divZaTekst.appendChild(divZaTekstDatum);
			divZaTekst.appendChild(divZaTekstVrijeme);
			divZaTekst.appendChild(divZaTekstMjesto);
			divZaTekst.appendChild(divZaTekstDrzava);
			divZaTekst.appendChild(divic3);
			divZaTekst.appendChild(divic4);
			
			
			
			//dodavanje svega
			noviDiv.appendChild(divZaSliku);
			noviDiv.appendChild(divZaTekst);
			div.appendChild(noviDiv);
			
			let divPrazan = document.createElement("div");
			let k4 = document.createAttribute("class");
			k4.value = "prazan";
			divPrazan.setAttributeNode(k4);
			div.appendChild(divPrazan);
			
		
	}
}





//MAIN
$(document).ready(function(){
	

	if(JSON.parse(window.sessionStorage.getItem("manifestacije")) == null){
		$.ajax({
		url: "rest/manifestacije/aktivne",
		type:"GET",
		dataType:"json",
		complete: function(manifestacije_lista) {
			manifestacije = JSON.parse(JSON.stringify(manifestacije_lista.responseJSON));
			popuniSadrzaj(manifestacije);
			window.sessionStorage.setItem("manifestacije", JSON.stringify(manifestacije_lista.responseJSON));
		}
	});
	
	console.log(event);
	
	$('button#pretrazi').click(function() {
		window.location.href = "pretraga_manifestacija.html";
	});
	
	}else{
		popuniSadrzaj(JSON.parse(window.sessionStorage.getItem("manifestacije")));
		window.sessionStorage.setItem("manifestacije", JSON.stringify(manifestacije_lista.responseJSON));
		
		$('button#pretrazi').click(function() {
		window.location.href = "pretraga_manifestacija.html";
	});
	}
	
	
	
	
	

})