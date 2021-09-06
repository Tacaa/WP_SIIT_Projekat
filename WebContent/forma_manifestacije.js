

$(document).ready(function(){
	//POSTAVKE OGRANICENJA
 	var danas = new Date();
	var dan = danas.getDate() + 1;
	var mjesec = danas.getMonth()+1; //januar je 0
	var godina = danas.getFullYear();
	
	if(dan<10){
		dan='0'+dan
	} 
	if(mjesec<10){
	    mjesec='0'+mjesec
	} 
	
	danas = godina + '-' + mjesec + '-' + dan;
	document.getElementById("datum").setAttribute("min", danas);

	console.log(dan);

	$('form#forma').submit(function(event){
	
		event.preventDefault();
	
		//POKUPI PODATKE
		let naziv = $('input[name="naziv"]').val();
		let datum = $('input[name="datum"]').val();
		let vrijeme = $('input[name="vrijeme"]').val();
		let adresa = $('input[name="adresa"]').val();
		let grad = $('input[name="grad"]').val();
		let drzava = $('input[name="drzava"]').val();
		let cena = $('input[name="cijena"]').val();
		let brojMesta = $('input[name="broj_mjesta"]').val();	
	 	let poster = $('input[name="slika"]').val();	
	 	let tip = $('input[name="filter"]:checked').val();
	 	
	 
	 	
	 	
	 	let vreme = datum + "T" + vrijeme + ":00";
	 	
	 	//PROVJERE
 		//1. SVE MORA BITI UNESENO
 		if(!naziv || !datum || !vrijeme || !adresa || !grad || !drzava || !cena || !brojMesta || !poster || !tip){
 			$('#greska_unosa').text('Popunite sva polja :)');
 			$('#greska_unosa').css("color", "#fbc2c0");
 			$("#greska_unosa").show().delay(4000).fadeOut();
 			return;
 		}
 		
 		const objekat = JSON.parse(window.sessionStorage.getItem("trenutniProdavac"));
 		let prodavac = objekat.korisnickoIme;
 		
 		$.ajax({
				url: "rest/manifestacije/kreiraj",
				type:"POST",
				data: JSON.stringify({naziv: naziv, tip: tip, brojMesta: brojMesta, vreme: vreme, cena: cena, adresa: adresa, grad: grad, drzava: drzava, poster: poster, prodavac:prodavac}),
				contentType:"application/json",
				dataType:"text",
				complete: function(data, uspelo) {
					if (uspelo == "success"){
						$('#greska_unosa').text('Uspesno ste kreirali manifestaciju!!! :)');
						$('#greska_unosa').css("color", "#fbc2c0");
						$("#greska_unosa").show().delay(3000).fadeOut().hide(1000, function(){window.location.href = "profil_prodavca.html";});
						
					} 
					else {
						$('#greska_unosa').text(':O O ne, nesto je krenulo po zlu. Molimo Vas pokusajte ponovo :)');
						$('#greska_unosa').css("color", "#fbc2c0");
						$("#greska_unosa").show().delay(4000).fadeOut();
					}
					
			
				}
								
			});
 	
 	
	
	})

	
 	
 	
 	
 	
 })