$(document).ready(function() {
	//console.log(window.sessionStorage.getItem("trenutniProdavac"));
    let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniProdavac"));
    if (korisnik == null) {window.location.href = "logovanje.html";}

    $("#korisnicko_ime").text(korisnik.korisnickoIme);
    $("#ime_prezime").text(korisnik.ime + " " + korisnik.prezime);
    $("#pol").text(korisnik.pol);
	let datum = korisnik.datumRodjenja.split("-");
    $("#datum_rodjenja").text(datum[2] + "." + datum[1] + "." + datum[0] + ".");
    
    

    $("#izmena_podataka").submit(function(event) {
		event.preventDefault();
    	window.location.href = "izmena_podataka.html";
    }); 


 	$("#napravi_manifestaciju").submit(function(event) {
		event.preventDefault();
    	window.location.href = "";
    }); 


 	$("#izmjena_manifestacije").submit(function(event) {
		event.preventDefault();
    	window.location.href = "";
    }); 
    

 	$("#pregled_manifestacija").submit(function(event) {
		event.preventDefault();
    	window.location.href = "";
    }); 


	 $("#pregled_kupaca").submit(function(event) {
		event.preventDefault();
    	window.location.href = "izmjena_prodavcevih_podataka.html";
    }); 


 	$("#pregled_karata").submit(function(event) {
		event.preventDefault();
    	window.location.href = "";
    }); 
    
    

 	$("#odobrenje_komentara").submit(function(event) {
		event.preventDefault();
    	window.location.href = "http://localhost:8080/Projekat/rezervacija_karte.html";
    }); 

 	$("#vidi_komentare").submit(function(event) {
		event.preventDefault();
    	window.location.href = "http://localhost:8080/Projekat/komentarisanje.html";
    }); 
    

    $("#odjava").submit(function(event) {
		event.preventDefault();
		window.sessionStorage.removeItem("trenutniProdavac");
    	window.location.href = "logovanje.html";
    }); 
});