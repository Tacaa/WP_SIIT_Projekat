function podesi_manifestaciju(tagA) { 
	window.sessionStorage.setItem("manifestacija", tagA.id.split("+prikaz")[0])
};

function popuniSadrzaj(manifestacije) {
	$("#sadrzaj_odobravanja").children().remove();
	for(let manifestacija of manifestacije){
		let div = document.getElementById("sadrzaj_odobravanja");
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
		link.setAttribute("id", JSON.stringify(manifestacija) + "+prikaz");
		divZaSliku.appendChild(link);
		
		
		//pravimo div za text
		let divZaTekst = document.createElement("div");
		let klasaDivaZaTekst = document.createAttribute("class");
		klasaDivaZaTekst.value = "div_za_tekst";
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
		
		let odobriDugme = document.createElement("button");
		odobriDugme.appendChild(document.createTextNode("Odobri"));
		odobriDugme.setAttribute("id", manifestacija.naziv + "+" + manifestacija.vreme + "+odobri");
		odobriDugme.setAttribute("class", "rezervisi_dugmici");
		
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
		
		let divZaLinkRezervacija = document.createElement("div");
		let k5 = document.createAttribute("class");
		k5.value = "d1";
		divZaLinkRezervacija.setAttributeNode(k5);
		divZaLinkRezervacija.appendChild(odobriDugme);
		
		divZaTekst.appendChild(divZaTekstIme);
		divZaTekst.appendChild(divZaTekstMesto);
		divZaTekst.appendChild(divZaTekstVrijeme);
		divZaTekst.appendChild(divZaTekstCena);
		divZaTekst.appendChild(divZaLinkRezervacija);
		
		
		//dodavanje svega
		noviDiv.appendChild(divZaSliku);
		noviDiv.appendChild(divZaTekst);
		div.appendChild(noviDiv);
		
		let divPrazan = document.createElement("div");
		let k6 = document.createAttribute("class");
		k6.value = "prazan";
		divPrazan.setAttributeNode(k6);
		div.appendChild(divPrazan);
		
		// ***********************************************************************
		var modal = document.getElementById("modal_odob");
	    var odobri_dugme = document.getElementById(manifestacija.naziv + "+" + manifestacija.vreme + "+odobri");
		var x_rezervisi = document.getElementsByClassName("close")[0];
	
	    odobri_dugme.onclick = function(event) {
	    	modal.style.display = "block";		// prikazujem formu za rezervaciju
			window.sessionStorage.setItem("rezervisiZa", event.currentTarget.id.split("+odobri")[0]);
	    }
	    x_rezervisi.onclick = function() {
	    	modal.style.display = "none";		// sakrij formu kad klikne na x
	    }
	    window.onclick = function(event) {
		    if (event.target == modal) {		// sakrij formu kad klikne van forme
		        modal.style.display = "none";
				return;
		    }
	    }

	}
};

$(document).ready(function(){
	let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniAdministrator"));
    if (korisnik == null) {window.location.href = "logovanje.html";}
	let manifestacije = [];
	$.ajax({
		url: "rest/manifestacije/zaOdobravanje",
		type:"GET",
		dataType:"json",
		complete: function(data) {
			manifestacije = JSON.parse(data.responseText);
			popuniSadrzaj(manifestacije);
		}
	});
	
	//***************************************************************************************
		// rezervacije
	$("form#odobri_man").submit(function(event) {
		event.preventDefault();
		let nazivDatumManifestacije = window.sessionStorage.getItem("rezervisiZa");
		let odobri = $('input[name=prihvati]:checked').val();
		let parametar = nazivDatumManifestacije + "+" + odobri;
		$.ajax({
			url: "rest/manifestacije/odobri/" + parametar,
			type:"GET",
			complete: function(data, uspesno) {
				console.log(data.responseText);
				document.getElementById("modal_odob").style.display = "none";
				let novaLista = []; let ind = 0;
				for (let m of manifestacije) {
					if (nazivDatumManifestacije != (m.naziv + "+" + m.vreme)) {
						novaLista[ind] = m;
						ind = ind + 1;
					}
				}
				manifestacije = novaLista;
				popuniSadrzaj(manifestacije);
			}
		});
	});

});