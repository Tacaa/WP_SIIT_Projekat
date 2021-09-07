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
	 
 	if (window.sessionStorage.getItem("trenutniAdministrator") == null) 
		$(".div_za_naslov")[0].append(document.createTextNode("REGISTRACIJA"));
	else $(".div_za_naslov")[0].append(document.createTextNode("REGISTRACIJA KUPCA"));
  
 	//KLIK NA DUGME "REGISTRUJTE SE"
 	$('form#forma').submit(function(event){
 		
 		event.preventDefault();
 		//UZMI SVE PODATKE
 		let ime = $('input[name="ime"]').val();
 		let prezime = $('input[name="prezime"]').val();
 		let datumRodjenja = $('input[name="datum_rodjenja"]').val();
 		let pol = $('input[name="pol"]:checked').val();
 		let korisnickoIme = $('input[name="korisnicko_ime"]').val();
 		let lozinka = $('input[name="lozinka"]').val();
 		let potvrdaLozinke = $('input[name="potrda_lozinke"]').val();
 		
 		
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
 		
 		
 		if (window.sessionStorage.getItem("trenutniAdministrator") == null) {
	 		$.ajax({
				url: "rest/kupci/registrujKupca",
				type:"POST",
				data: JSON.stringify({korisnickoIme: korisnickoIme, lozinka: lozinka, ime: ime, prezime: prezime, pol:pol, datumRodjenja:datumRodjenja, aktivnost:"AKTIVAN"}),
				contentType:"application/json",
				dataType:"json",
				complete: function(data, uspelo) {
					if (uspelo == "success") {
						console.log(data.responseText);
						$('#greska_unosa').text('Uspesno ste registrovani! :)')
						$('#greska_unosa').css("color", "#fbc2c0");
						$("#greska_unosa").show().delay(1500).fadeOut(0, function(){
							trenutniKupac = JSON.parse(data.responseText);
							window.sessionStorage.setItem("trenutniKupac", JSON.stringify(trenutniKupac));
							window.location.href = "profil_kupca.html";});
						
					}
					else {
						$('#greska_unosa').text('Korisnicko ime je vec zauzeto :( Probajte da unesete neko drugo :)');
						$('#greska_unosa').css("color", "#fbc2c0");
						$("#greska_unosa").show().delay(4000).fadeOut();
					};
				}
								
			});
		}
 		else {
	 		$.ajax({
				url: "rest/prodavci/registrujProdavca",
				type:"POST",
				data: JSON.stringify({korisnickoIme: korisnickoIme, lozinka: lozinka, ime: ime, prezime: prezime, pol:pol, datumRodjenja:datumRodjenja, aktivnost:"AKTIVAN"}),
				contentType:"application/json",
				dataType:"json",
				complete: function(data, uspelo) {
					if (uspelo == "success") {
						console.log(data.responseText);
						$('#greska_unosa').text('Uspesno registrovanje prodavca!!!! :)')
						$('#greska_unosa').css("color", "#fbc2c0");
						$("#greska_unosa").show().delay(1500).fadeOut(0, function(){
							trenutniProdavac = JSON.parse(data.responseText);
							window.sessionStorage.setItem("trenutniProdavac", JSON.stringify(trenutniProdavac));
							window.location.href = "profil_prodavca.html";});
						
					}
					else {
						$('#greska_unosa').text('Korisnicko ime je vec zauzeto :( Probajte da unesete neko drugo :)');
						$('#greska_unosa').css("color", "#fbc2c0");
						$("#greska_unosa").show().delay(4000).fadeOut();
					}
				}				
			})
		}
 		
 	});
 
 
 })