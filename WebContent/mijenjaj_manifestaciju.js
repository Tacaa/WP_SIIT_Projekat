/**
 * 
 */
 
 
 
 $(document).ready(function(){
	const manifestacija = JSON.parse(window.sessionStorage.getItem("mijenjaj_manifestaciju"));
	console.log(manifestacija);
	
	
	let vrijeme = manifestacija.vreme.split("T")[1].split(":");
	
	
	
	$('input[name="naziv"]').val(manifestacija.naziv);
	$('input[name="datum"]').val(manifestacija.vreme.split("T")[0]);
	$('input[name="vrijeme"]').val(vrijeme[0] + ":" + vrijeme[1]);
	$('input[name="adresa"]').val(manifestacija.lokacija.ulicaBroj);
	$('input[name="grad"]').val(manifestacija.lokacija.grad);
	$('input[name="drzava"]').val(manifestacija.lokacija.drzava);
	$('input[name="cijena"]').val(manifestacija.cena);
	$('input[name="broj_mjesta"]').val(manifestacija.brojMesta);	
 	$('input[name="slika"]').val(manifestacija.poster);	
 	
 	let tip = manifestacija.tip.toUpperCase();
 	console.log(tip);
 	$("input:radio[value=\"" + tip + "\"]").prop('checked',true);
 	
 	
 	
 	
 	
 	$('form#forma').submit(function(event){
	
		event.preventDefault();
		
		let stariNaziv = manifestacija.naziv;
		let staroVrijeme = manifestacija.vreme;
	
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
	 	let tipic = $('input[name="filter"]:checked').val();
	 	
	 
	 	
	 	
	 	let vreme = datum + "T" + vrijeme + ":00";
	 	
	 	//PROVJERE
 		//1. SVE MORA BITI UNESENO
 		if(!naziv || !datum || !vrijeme || !adresa || !grad || !drzava || !cena || !brojMesta || !poster || !tipic){
 			$('#greska_unosa').text('Popunite sva polja :)');
 			$('#greska_unosa').css("color", "#fbc2c0");
 			$("#greska_unosa").show().delay(4000).fadeOut();
 			return;
 		}
 		
 		
 		let tip;
 		if(tipic == "POZORISNA PREDSTAVA"){
 			tip = "POZORISNA_PREDSTAVA";
 		}else if(tipic == "BIOSKOPSKA PROJEKCIJA"){
 			tip = "BIOSKOPSKA_PROJEKCIJA";
 		}else if(tipic == "KONCERT"){
 			tip = "KONCERT";
 		}else if(tipic == "FESTIVAL"){
 			tip = "FESTIVAL";
 		}else if(tipic == "SPORTSKA NADMETANJA"){
 			tip = "SPORTSKA_NADMETANJA";
 		}else if(tipic == "PLESNI NASTUP"){
 			tip = "PLESNI_NASTUP";
 		}else if(tipic == "STANDUP NASTUP"){
 			tip = "STANDUP_NASTUP";
 		}else if(tipic == "NASTUP CIRKUSA"){
 			tip = "NASTUP_CIRKUSA";
 		}else if(tipic == "SAJAM"){
 			tip = "SAJAM";
 		}else if(tipic == "MUZEJSKO VECE"){
 			tip = "MUZEJSKO_VECE";
 		}else if(tipic == "KNJIZEVNO VECE"){
 			tip = "KNJIZEVNO_VECE";
 		}else if(tipic == "DRUGA KATEGORIJA"){
 			tip = "DRUGA_KATEGORIJA";
 		}
 		
 		
 		const objekat = JSON.parse(window.sessionStorage.getItem("trenutniProdavac"));
 		let prodavac = objekat.korisnickoIme;
 		
 		console.log(JSON.stringify({stariNaziv: stariNaziv, staroVrijeme: staroVrijeme, naziv: naziv, tip: tip, brojMesta: brojMesta, vreme: vreme, cena: cena, adresa: adresa, grad: grad, drzava: drzava, poster: poster, prodavac:prodavac}));
 		
 		
 		$.ajax({
				url: "rest/manifestacije/mijenjaj",
				type:"PUT",
				data: JSON.stringify({stariNaziv: stariNaziv, staroVrijeme: staroVrijeme, naziv: naziv, tip: tip, brojMesta: brojMesta, vreme: vreme, cena: cena, adresa: adresa, grad: grad, drzava: drzava, poster: poster, prodavac:prodavac}),
				contentType:"application/json",
				dataType:"text",
				complete: function(data, uspelo) {
					if (uspelo == "success"){
					
						$('#greska_unosa').text('Uspesno ste promenili manifestaciju!!! :)');
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