$(document).ready(function(){
    let korisnik = JSON.parse(window.sessionStorage.getItem("trenutniKupac"));
    let prodavac = JSON.parse(window.sessionStorage.getItem("trenutniProdavac"));
    let administrator = JSON.parse(window.sessionStorage.getItem("trenutniAdministrator"));
    
    
    
    
    if(korisnik != null){
    	let vrsta = "kupac";
    	
	    let imeElem = $('input[name="ime"]');
	    imeElem.val(korisnik.ime);
	
	    let prezimeElem = $('input[name="prezime"]');
	    prezimeElem.val(korisnik.prezime);
	
	    let datumRodjenjaElem = $('input[name="datum_rodjenja"]');
	    datumRodjenjaElem.val(korisnik.datumRodjenja);
	    
	
	    if (korisnik.pol == "ZENSKI") document.getElementById("polZ").setAttribute("checked", true); 
	    else document.getElementById("polM").setAttribute("checked", true); 
	
	    let korisnickoIme = $('#korisnicko_ime');
	    korisnickoIme.text(korisnik.korisnickoIme);     // jer je label
	
	    let lozinkaElem = $('input[name="lozinka"]');
	    lozinkaElem.val(korisnik.lozinka);
	    
	    let potvrdaLozinkeElem = $('input[name="potrda_lozinke"]');
	    potvrdaLozinkeElem.val(korisnik.lozinka);
	
	    //POSTAVKE OGRANICENJA
	    var danas = new Date();
	    var dan = danas.getDate();
	    var mjesec = danas.getMonth()+1; //januar je 0
	    var godina = danas.getFullYear();
	   
	    if(dan<10){
	       dan='0'+dan
	    } 
	    if(mjesec<10){
	       mjesec='0'+mjesec
	    } 
	   
	    danas = godina + '-' + mjesec + '-' + dan;
	    document.getElementById("datum_rodjenja").setAttribute("max", danas);
	    
	    //KLIK NA DUGME "SACUVAJ PROMENE"
	    $('form#forma').submit(function(event){
	        
	        event.preventDefault();
	        //UZMI SVE PODATKE
	        let ime = imeElem.val();
	        let prezime = prezimeElem.val();
	        let datumRodjenja = datumRodjenjaElem.val();
	        let pol = $('input[name="pol"]:checked').val();
	        let lozinka = lozinkaElem.val();
	        let potvrdaLozinke = potvrdaLozinkeElem.val();
	        
	        
	        //PROVJERE
	        //1. SVE MORA BITI UNESENO
	        if(!ime || !prezime || !datumRodjenja || !pol || !lozinka || !potvrdaLozinke){
	            $('#greska_unosa').text('Popunite sva polja :)');
	            $('#greska_unosa').css("color", "#fbc2c0");
	            $("#greska_unosa").show().delay(4000).fadeOut();
	            return;
	        }
	        
	        //2. LOZINKE SE MORAJU POKLAPATI
	        if(lozinka !== potvrdaLozinke){
	            $('#greska_unosa').text('Lozinke se moraju poklapati :)');
	            $('#greska_unosa').css("color", "#fbc2c0");
	            $("#greska_unosa").show().delay(4000).fadeOut();
	            return;
	        }
	
	        
	        $.ajax({
	           url: "rest/kupci/izmeniKupca",
	           type:"POST",
	           data: JSON.stringify({korisnickoIme: korisnik.korisnickoIme, lozinka: lozinka, ime: ime, prezime: prezime, pol:pol, datumRodjenja:datumRodjenja, aktivnost:"AKTIVAN"}),
	           contentType:"application/json",
	           dataType:"json",
	           complete: function(data, uspelo) {
					console.log(data.responseText);
					window.sessionStorage.setItem("trenutniKupac", data.responseText);
	               $('#greska_unosa').text('Uspesno cuvanje izmena!!!! :)');
	               $('#greska_unosa').css("color", "#fbc2c0");
	               $("#greska_unosa").show().delay(4000).fadeOut();
	           }          
	       });
		
	    });
    	
    	
    }
    
    
	
	if (administrator != null) {
		vrsta = "administrator"; 
		korisnik = JSON.parse(window.sessionStorage.getItem("trenutniAdministrator"));
	}
	
	
	
	
	if (prodavac != null) {
		vrsta = "prodavac";
		korisnik = JSON.parse(window.sessionStorage.getItem("trenutniProdavac"));
		
		let imeElem = $('input[name="ime"]');
	    imeElem.val(korisnik.ime);
	
	    let prezimeElem = $('input[name="prezime"]');
	    prezimeElem.val(korisnik.prezime);
	
	    let datumRodjenjaElem = $('input[name="datum_rodjenja"]');
	    datumRodjenjaElem.val(korisnik.datumRodjenja);
	    
	
	    if (korisnik.pol == "ZENSKI") document.getElementById("polZ").setAttribute("checked", true); 
	    else document.getElementById("polM").setAttribute("checked", true); 
	
	    let korisnickoIme = $('#korisnicko_ime');
	    korisnickoIme.text(korisnik.korisnickoIme);     // jer je label
	
	    let lozinkaElem = $('input[name="lozinka"]');
	    lozinkaElem.val(korisnik.lozinka);
	    
	    let potvrdaLozinkeElem = $('input[name="potrda_lozinke"]');
	    potvrdaLozinkeElem.val(korisnik.lozinka);
	
	    //POSTAVKE OGRANICENJA
	    var danas = new Date();
	    var dan = danas.getDate();
	    var mjesec = danas.getMonth()+1; //januar je 0
	    var godina = danas.getFullYear();
	   
	    if(dan<10){
	       dan='0'+dan
	    } 
	    if(mjesec<10){
	       mjesec='0'+mjesec
	    } 
	   
	    danas = godina + '-' + mjesec + '-' + dan;
	    document.getElementById("datum_rodjenja").setAttribute("max", danas);
	    
	    //KLIK NA DUGME "SACUVAJ PROMENE"
	    $('form#forma').submit(function(event){
	        
	        event.preventDefault();
	        //UZMI SVE PODATKE
	        let ime = imeElem.val();
	        let prezime = prezimeElem.val();
	        let datumRodjenja = datumRodjenjaElem.val();
	        let pol = $('input[name="pol"]:checked').val();
	        let lozinka = lozinkaElem.val();
	        let potvrdaLozinke = potvrdaLozinkeElem.val();
	        
	        
	        //PROVJERE
	        //1. SVE MORA BITI UNESENO
	        if(!ime || !prezime || !datumRodjenja || !pol || !lozinka || !potvrdaLozinke){
	            $('#greska_unosa').text('Popunite sva polja :)');
	            $('#greska_unosa').css("color", "#fbc2c0");
	            $("#greska_unosa").show().delay(4000).fadeOut();
	            return;
	        }
	        
	        //2. LOZINKE SE MORAJU POKLAPATI
	        if(lozinka !== potvrdaLozinke){
	            $('#greska_unosa').text('Lozinke se moraju poklapati :)');
	            $('#greska_unosa').css("color", "#fbc2c0");
	            $("#greska_unosa").show().delay(4000).fadeOut();
	            return;
	        }
	
	
	        $.ajax({
	           url: "rest/prodavci/izmeniProdavca",
	           type:"POST",
	           data: JSON.stringify({korisnickoIme: korisnik.korisnickoIme, lozinka: lozinka, ime: ime, prezime: prezime, pol:pol, datumRodjenja:datumRodjenja, aktivnost:"AKTIVAN"}),
	           contentType:"application/json",
	           dataType:"json",
	           complete: function(data, uspelo) {
					console.log(data.responseText);
					window.sessionStorage.setItem("trenutniProdavac", data.responseText);
	               $('#greska_unosa').text('Uspesno cuvanje izmena!!!! :)');
	               $('#greska_unosa').css("color", "#fbc2c0");
	               $("#greska_unosa").show().delay(4000).fadeOut();
	               window.location.href = "profil_kupca.html";
	               
	           }               
	       });
		
   		});

		
	} 
    
    
    
    
    
    
    
    
   

})