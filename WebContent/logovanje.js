function redirektujkupca(korisnickoIme) {
	$.ajax({
		url: "rest/login_out/trenutniKupac/"+ korisnickoIme,
		type:"GET",
		dataType:"json",
		complete: function(kupac) {
			window.sessionStorage.setItem("trenutniKupac", JSON.stringify(kupac.responseJSON));
			window.location.href = "profil_kupca.html";
		}
	});
}


function redirektujprodavca(korisnickoIme) {
	$.ajax({
		url: "rest/login_out/trenutniProdavac/"+ korisnickoIme,
		type:"GET",
		dataType:"json",
		complete: function(prodavac) {
			window.sessionStorage.setItem("trenutniProdavac", JSON.stringify(prodavac.responseJSON));
			window.location.href = "profil_prodavca.html";
		}
	});
}


function redirektujadministratora(korisnickoIme) {
	$.ajax({
		url: "rest/login_out/trenutniAdministrator/"+ korisnickoIme,
		type:"GET",
		dataType:"json",
		complete: function(administrator) {
			window.sessionStorage.setItem("trenutniAdministrator", JSON.stringify(administrator.responseJSON));
			window.location.href = "proba.html";
		}
	});
}


$(document).ready(function(){
 	
 	
 	//KLIK NA DUGME "REGISTRUJTE SE"
 	$('form#forma').submit(function(event){
 		
 		event.preventDefault();
 		//UZMI SVE PODATKE
 		let korisnickoIme = $('input[name="korisnicko_ime"]').val();
 		let lozinka = $('input[name="lozinka"]').val();
 		
 		
 		
 		//PROVJERE
 		//SVE MORA BITI UNESENO
 		if(!korisnickoIme || !lozinka){
 			$('#greska_unosa').text('Popunite sva polja :)');
 			$('#greska_unosa').css("color", "#fbc2c0");
 			$("#greska_unosa").show().delay(4000).fadeOut();
 			return;
 		}
 		
 		
 		
 		
 		$.ajax({
			url: "rest/login_out/logovanje",
			type:"POST",
			data: JSON.stringify({korisnickoIme: korisnickoIme, lozinka: lozinka}),
			contentType:"application/json",
			dataType:"text",
			complete: function(data, uspjelo) {
				
				if (uspjelo == "success") {
 					//redirektovati
 					if(data.responseText == "kupac"){
 						redirektujkupca(korisnickoIme);
 					}else if(data.responseText == "prodavac"){
 						redirektujprodavca(korisnickoIme)
 					}else if(data.responseText == "administrator"){
 						redirektujadministratora(korisnickoIme)
 					}else{
 						$('#greska_unosa').text('Nesto je poslo po zlu!!!!!!! :O');
 						$('#greska_unosa').css("color", "#fbc2c0");
 						$("#greska_unosa").show().delay(4000).fadeOut();
 					}
 					
				}else{
					$('#greska_unosa').text('Uneseno pogresno korisnicko ime ili lozinka. Pokusajte ponovo :)');
 					$('#greska_unosa').css("color", "#fbc2c0");
 					$("#greska_unosa").show().delay(4000).fadeOut();
				}
					
			
				
			}
		});
		
 		
 	});
 
 
 })