$(document).ready(function() {
    let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniAdministrator"));
	console.log(korisnik);
    if (korisnik == null) {window.location.href = "logovanje.html";}

    $("#korisnicko_ime").text(korisnik.korisnickoIme);
    $("#ime_prezime").text(korisnik.ime + " " + korisnik.prezime);
    $("#pol").text(korisnik.pol);
	let datum = korisnik.datumRodjenja.split("-");
    $("#datum_rodjenja").text(datum[2] + "." + datum[1] + "." + datum[0] + ".");

    $("#izmena_podataka").submit(function(event) {
		event.preventDefault();
    	window.location.href = "http://localhost:8080/Projekat/izmena_podataka.html";
    }); 

 	$("#dodaj_prodavca").submit(function(event) {
		event.preventDefault();
		window.location.href = "http://localhost:8080/Projekat/login.html";
    }); 

 	$("#svi_prodavci").submit(function(event) {
		event.preventDefault();
		window.location.href = "http://localhost:8080/Projekat/prikaz_prodavaca.html";
    }); 

 	$("#svi_kupci").submit(function(event) {
		event.preventDefault();
		window.location.href = "http://localhost:8080/Projekat/prikaz_kupaca.html";
    });  

 	$("#odobri_man").submit(function(event) {
		event.preventDefault();
		// bice
    }); 

 	$("#pregled_man").submit(function(event) {
		event.preventDefault();
		// bice
    }); 

 	$("#pregled_karata").submit(function(event) {
		event.preventDefault();
		// bice
    }); 

    $("#odjava").submit(function(event) {
		event.preventDefault();
		window.sessionStorage.removeItem("trenutniAdministrator");
    	window.location.href = "logovanje.html";
    }); 
});