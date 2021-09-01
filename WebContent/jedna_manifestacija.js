

//MAIN
$(document).ready(function(){
	const manifestacija = JSON.parse(window.sessionStorage.getItem("manifestacija"));
	console.log(manifestacija);

	var danas = new Date();
    var dan = danas.getDate();
    var mesec = danas.getMonth()+1; //januar je 0
    var godina = danas.getFullYear();
    if(dan<10) dan='0'+dan;
    if(mesec<10) mesec='0'+mesec;
    danas = godina + '-' + mesec + '-' + dan;
	
	let div_za_sliku = document.getElementById("div_za_sliku");
	let slika = document.createElement("img");
	let putanjaSlike = document.createAttribute("src");
	//putanjaSlike.value = manifestacija.poster;
	putanjaSlike.value = manifestacija.poster;
	slika.setAttributeNode(putanjaSlike);
	div_za_sliku.appendChild(slika);
	
	
	//naziv
	let div1 = document.createElement("div");
	let klasa1 = document.createAttribute("class");
	klasa1.value = "div_input";
	div1.setAttributeNode(klasa1);
	
	let labela1 = document.createElement("label");
	let ime = document.createTextNode("NAZIV: " + manifestacija.naziv);
	labela1.appendChild(ime);
	div1.appendChild(labela1);
	
	
	
	//tip
	let div2 = document.createElement("div");
	let klasa2 = document.createAttribute("class");
	klasa2.value = "div_input";
	div2.setAttributeNode(klasa2);
	
	let labela2 = document.createElement("label");
	let tip = document.createTextNode("TIP MANIFESTACIJE: " + manifestacija.tip);
	labela2.appendChild(tip);
	div2.appendChild(labela2);
	
	
	
	//broj mjesta
	let div3 = document.createElement("div");
	let klasa3 = document.createAttribute("class");
	klasa3.value = "div_input";
	div3.setAttributeNode(klasa3);
	
	let labela3 = document.createElement("label");
	let broj_mjesta = document.createTextNode("BROJ MJESTA: " + manifestacija.brojMesta);
	labela3.appendChild(broj_mjesta);
	div3.appendChild(labela3);
	
	
	
	//broj rezervisanih mjesta
	let div4 = document.createElement("div");
	let klasa4 = document.createAttribute("class");
	klasa4.value = "div_input";
	div4.setAttributeNode(klasa4);
	
	let labela4 = document.createElement("label");
	let broj_rezervisanih_mjesta = document.createTextNode("REZERVISANO: " + manifestacija.brojRezervisanihMesta);
	labela4.appendChild(broj_rezervisanih_mjesta);
	div4.appendChild(labela4);
	
	//status
	let div5 = document.createElement("div");
	let klasa5 = document.createAttribute("class");
	klasa5.value = "div_input";
	div5.setAttributeNode(klasa5);
	
	let labela5 = document.createElement("label");
	let status = document.createTextNode("STATUS: " + manifestacija.status);
	labela5.appendChild(status);
	div5.appendChild(labela5);
	
	
	
	//vrijeme
	let datumVrijeme = manifestacija.vreme.split('T');
	let datum = datumVrijeme[0].split('-');
	let vrijeme = datumVrijeme[1].split(':');
	
	let div6 = document.createElement("div");
	let klasa6 = document.createAttribute("class");
	klasa6.value = "div_input";
	div6.setAttributeNode(klasa6);
	
	let labela6 = document.createElement("label");
	let datum1 = document.createTextNode("DATUM: " + datum[2] + "." + datum[1] + "." + datum[0] + ".");
	labela6.appendChild(datum1);
	div6.appendChild(labela6);
	
	
	let div7 = document.createElement("div");
	let klasa7 = document.createAttribute("class");
	klasa7.value = "div_input";
	div7.setAttributeNode(klasa7);
	
	let labela7 = document.createElement("label");
	let vrijeme1 = document.createTextNode("VRIJEME: " + vrijeme[0] + ":" + vrijeme[1]);
	labela7.appendChild(vrijeme1);
	div7.appendChild(labela7);
	
	//lokacija
	
	let grad = manifestacija.lokacija.grad;
	let drzava = manifestacija.lokacija.drzava;
	
	let div8 = document.createElement("div");
	let klasa8 = document.createAttribute("class");
	klasa8.value = "div_input";
	div8.setAttributeNode(klasa8);
	
	let labela8 = document.createElement("label");
	let adresa1 = document.createTextNode("ADRESA: " + manifestacija.lokacija.ulicaBroj);
	labela8.appendChild(adresa1);
	div8.appendChild(labela8);
	
	
	let div9 = document.createElement("div");
	let klasa9 = document.createAttribute("class");
	klasa9.value = "div_input";
	div9.setAttributeNode(klasa9);
	
	let labela9 = document.createElement("label");
	let grad1 = document.createTextNode("GRAD: " + grad);
	labela9.appendChild(grad1);
	div9.appendChild(labela9);
	
	
	let div10 = document.createElement("div");
	let klasa10 = document.createAttribute("class");
	klasa10.value = "div_input";
	div10.setAttributeNode(klasa10);
	
	let labela10 = document.createElement("label");
	let drzava1 = document.createTextNode("DRZAVA: " + drzava);
	labela10.appendChild(drzava1);
	div10.appendChild(labela10);
	
	
	
	//cijena 
	let div11 = document.createElement("div");
	let klasa11 = document.createAttribute("class");
	klasa11.value = "div_input";
	div11.setAttributeNode(klasa11);
	
	let labela11 = document.createElement("label");
	let cijena = document.createTextNode("CIJENA: " + manifestacija.cena);
	labela11.appendChild(cijena);
	div11.appendChild(labela11);
	
	
	//ocjena
	let div12 = document.createElement("div");
	let klasa12 = document.createAttribute("class");
	klasa12.value = "div_input";
	div12.setAttributeNode(klasa12);
	
	let labela12 = document.createElement("label");
	let ocjena = document.createTextNode("OCJENA: " + manifestacija.ocena);
	labela12.appendChild(ocjena);
	div12.appendChild(labela12);
	
	//komentar ako ih ima
	let div13 = document.createElement("div");
	let klasa13 = document.createAttribute("class");
	klasa13.value = "div_input";
	div13.setAttributeNode(klasa13);

	let dugme = document.createElement("button");
	dugme.setAttribute("class", "svetli_dugmici");
	dugme.appendChild(document.createTextNode("KOMENTARI"));
	if (manifestacija.vreme.split("T")[0] > danas) dugme.setAttribute("disabled", "true");
	div13.appendChild(dugme);
	
	//manje divove stavi u formin div
	let formin_div = document.getElementById("formin_div");
	formin_div.appendChild(div1);
	formin_div.appendChild(div2);
	formin_div.appendChild(div3);
	formin_div.appendChild(div4);
	formin_div.appendChild(div5);
	formin_div.appendChild(div6);
	formin_div.appendChild(div7);
	formin_div.appendChild(div8);
	formin_div.appendChild(div9);
	formin_div.appendChild(div10);
	formin_div.appendChild(div11);
	formin_div.appendChild(div12);
	formin_div.appendChild(div13);

	$("button").click(function(event) {
		event.preventDefault();
		window.location.href = "http://localhost:8080/Projekat/prikaz_komentara.html";
	});
})