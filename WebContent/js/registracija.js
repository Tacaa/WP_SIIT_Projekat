//MAIN 
 $(document).ready(function(){
 	
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
	 
 
 
 
 
 	//KLIK NA DUGME "REGISTRUJTE SE"
 	$('form#forma').submit(function(event){
 		//event.preventDefault();
 		
 		//UZMI SVE PODATKE
 		let ime = $('input[name="ime"]').val();
 		let prezime = $('input[name="prezime"]').val();
 		let datumRodjenja = $('input[name="datum_rodjenja"]').val();
 		let pol = $('input[name="pol"]:checked').val();
 		let korisnickoIme = $('input[name="korisnicko_ime"]').val();
 		let lozinka = $('input[name="lozinka"]').val();
 		let potvrdaLozinke = $('input[name="potrda_lozinke"]').val();
 		
 		
 		console.log(ime); 
 		console.log(prezime);
 		console.log(datumRodjenja); //2020-07-21
 		console.log(pol); //muskarac
 		console.log(korisnickoIme);
 		console.log(lozinka);
 		console.log(potvrdaLozinke);
 		
 		
 		
 		//PROVJERE
 		//1. SVE MORA BITI UNESENO
 		if(!ime || !prezime || !datumRodjenja || !pol || !korisnickoIme || !lozinka || !potvrdaLozinke){
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
 		
 		
 		
 		
 		
 		
 		//SVE U REDU --> SALJI NA SERVER
 		$.post({
			url: 'rest/kupci/registrujKupca',
			data: JSON.stringify({korisnickoIme: korisnickoIme, lozinka: lozinka, ime: ime, prezime: prezime, pol:pol, datumRodjenja:datumRodjenja, aktivnost:"AKTIVAN"}),
			contentType: 'application/json',
			success: function(product) {
				//OVDJEEEEEEE NASTAVAK STA TREBA DA RADIII!!!!!!!!!!!!
				$('#greska_unosa').text('USPJELOOOO!!!! :)');
			}
		});
 	});
 
 
 })