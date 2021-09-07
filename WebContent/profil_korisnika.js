$(document).ready(function() {
    let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniKupac"));
	console.log(korisnik);
    if (korisnik == null) {window.location.href = "logovanje.html";}

    $("#korisnicko_ime").text("@" + korisnik.korisnickoIme);
    $("#ime_prezime").text(korisnik.ime + " " + korisnik.prezime);
    $("#pol").text(korisnik.pol.toLowerCase());
	let datum = korisnik.datumRodjenja.split("-");
    $("#datum_rodjenja").text(datum[2] + "." + datum[1] + "." + datum[0] + ".");
    $("#bodovi").text(Math.round(korisnik.brojBodova * 100) / 100);

    $("#izmena_podataka").submit(function(event) {
		event.preventDefault();
    	window.location.href = "http://localhost:8080/Projekat/izmena_podataka.html";
    }); 

 	$("#pregled_karata").submit(function(event) {
		event.preventDefault();
    	window.location.href = "http://localhost:8080/Projekat/pregled_karata.html";
    }); 

 	$("#rezervacija").submit(function(event) {
		event.preventDefault();
    	window.location.href = "http://localhost:8080/Projekat/rezervacija_karte.html";
    }); 

 	$("#komentarisanje").submit(function(event) {
		event.preventDefault();
    	window.location.href = "http://localhost:8080/Projekat/komentarisanje.html";
    }); 

    $("#odjava").submit(function(event) {
		event.preventDefault();
		window.sessionStorage.removeItem("trenutniKupac");
    	window.location.href = "logovanje.html";
    }); 
});