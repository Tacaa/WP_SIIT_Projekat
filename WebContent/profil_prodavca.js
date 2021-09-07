$(document).ready(function() {
	//console.log(window.sessionStorage.getItem("trenutniProdavac"));
    let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniProdavac"));
    if (korisnik == null) {window.location.href = "logovanje.html";}

    $("#korisnicko_ime").text("@" + korisnik.korisnickoIme);
    $("#ime_prezime").text(korisnik.ime + " " + korisnik.prezime);
    $("#pol").text(korisnik.pol.toLowerCase());
	let datum = korisnik.datumRodjenja.split("-");
    $("#datum_rodjenja").text(datum[2] + "." + datum[1] + "." + datum[0] + ".");
    
    

    $("#izmena_podataka").submit(function(event) {
		event.preventDefault();
    	window.location.href = "izmena_podataka.html";
    }); 


 	$("#napravi_manifestaciju").submit(function(event) {
		event.preventDefault();
    	window.location.href = "forma_manifestacije.html";
    }); 


 	$("#pregled_manifestacija").submit(function(event) {
		event.preventDefault();
    	window.location.href = "prikaz_prodavcevih_manifestacija.html";
    }); 


 	$("#pregled_karata").submit(function(event) {
		event.preventDefault();
    	window.location.href = "prodavcev_pregled_karata_i_komentara.html";
    }); 
    

    $("#odjava").submit(function(event) {
		event.preventDefault();
		window.sessionStorage.removeItem("trenutniProdavac");
    	window.location.href = "pocetna.html";
    }); 
});