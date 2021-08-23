$(document).ready(function() {
            // pomocno od ovoga
    let objekat = {ime: "Imenko", 
        prezime: "Prezimic",
        korisnickoIme: "pera",
        datumRodjenja: "2017-07-17",
        pol: "ZENSKI",
        brojBodova: 17,
        tip: "BRONZANI",
        komentari: [],
        karte: [],
        lozinka: "Nanana"
    };
    window.sessionStorage.setItem("trenutniKupac", 
        JSON.stringify(objekat));
            // do ovoga pomocno

    let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniKupac"));
    
    if (korisnik == null) alert("Redirekcija na login");        // redirektuj ako nema

    $("#korisnicko_ime").text(korisnik.korisnickoIme);
    $("#ime_prezime").text(korisnik.ime + " " + korisnik.prezime);
    $("#pol").text(korisnik.pol);
    $("#datum_rodjenja").text(korisnik.datumRodjenja);
    $("#bodovi").text(korisnik.brojBodova);

    $("#izmena_podataka").submit(function(event) {
		event.preventDefault();
    	window.location.href = "http://localhost:8080/Projekat/izmena_podataka.html";
    }); 
});