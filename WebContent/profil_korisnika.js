$(document).ready(function() {
            // pomocno od ovoga
    let objekat = {ime: "Imenko", 
        prezime: "Prezimic",
        korisnickoIme: "imenk0",
        datumRodjenja: "2017-17-17",
        pol: "MUSKI",
        brojBodova: 17,
        tip: "BRONZANI",
        komentari: [],
        karte: []
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


});