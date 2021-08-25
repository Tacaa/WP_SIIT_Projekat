$(document).ready(function() {
	console.log(window.sessionStorage.getItem("trenutniKupac"));
    let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniKupac"));
    if (korisnik == null) {window.location.href = "logovanje.html";}

    $("#korisnicko_ime").text(korisnik.korisnickoIme);
    $("#ime_prezime").text(korisnik.ime + " " + korisnik.prezime);
    $("#pol").text(korisnik.pol);
    $("#datum_rodjenja").text(korisnik.datumRodjenja);
    $("#bodovi").text(korisnik.brojBodova);

    $("#izmena_podataka").submit(function(event) {
		event.preventDefault();
    	window.location.href = "http://localhost:8080/Projekat/izmena_podataka.html";
    }); 

 	$("#pregled_karata").submit(function(event) {
		event.preventDefault();
    	window.location.href = "http://localhost:8080/Projekat/pregled_karata.html";
    }); 

    $("#odjava").submit(function(event) {
		event.preventDefault();
		window.sessionStorage.removeItem("trenutniKupac");
    	window.location.href = "logovanje.html";
    }); 
});