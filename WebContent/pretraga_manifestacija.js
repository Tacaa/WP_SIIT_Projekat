

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
	
	var danas = godina + '-' + mjesec + '-' + dan;
	document.getElementById("od_datuma").setAttribute("min", danas);
	document.getElementById("do_datuma").setAttribute("min", danas); 
 
  	
  	
  	$('form#forma').submit(function(event){
  		const manifestacije = JSON.parse(window.sessionStorage.getItem("manifestacije"));
  		console.log(manifestacije);
  		event.preventDefault();
  		//uzimamo podatke
  		let naziv = $('input[name="naziv"]').val();
 		let od_datuma = $('input[name="od_datuma"]').val();
 		let do_datuma = $('input[name="do_datuma"]').val();
 		let vrijeme_pocetak = $('input[name="vrijeme_pocetak"]').val();
 		let vrijeme_kraj = $('input[name="vrijeme_kraj"]').val();
 		let adresa = $('input[name="adresa"]').val();
 		
 		let grad = $('input[name="grad"]').val();
 		let drzava = $('input[name="drzava"]').val();
 		let od_cijena = $('input[name="od_cijena"]').val();
 		let do_cijena = $('input[name="do_cijena"]').val();
 		
 		let sortiraj = $('input[name="sortiraj"]:checked').val();
 		let filter = $('input[name="filter"]:checked').val();
 		let filter2 = $('input[name="filter2"]:checked').val();
 		
 		let kriterijum =  $('input[name="kriterijum"]:checked').val();
 		
 		
 		
 		let vrijeme_za_unos_od;
 		let vrijeme_za_unos_do;
 		
 		
 		if(od_datuma == "" && vrijeme_pocetak != ""){
 			vrijeme_za_unos_od = "1800-01-01" + "T" + vrijeme_pocetak + ":00";
 		}else if(od_datuma != "" && vrijeme_pocetak == ""){
 			vrijeme_za_unos_od = od_datuma + "T" + "00:00" + ":00";
 		}else if(od_datuma == "" && vrijeme_pocetak == ""){
 			vrijeme_za_unos_od = "1800-01-01" + "T" + "00:00" + ":00";
 		}else{
 			vrijeme_za_unos_od = od_datuma + "T" + vrijeme_pocetak + ":00";
 		}
 		
 		
 		if(od_cijena == ""){
 			od_cijena = 0;
 		}
 		
 		if(do_cijena == ""){
 			do_cijena = 2147483646;
 		}
 		
 		
 		if(do_datuma == "" && vrijeme_kraj != ""){
 			vrijeme_za_unos_do = "2800-01-01" + "T" + vrijeme_pocetak + ":00";
 		}else if(do_datuma != "" && vrijeme_kraj == ""){
 			vrijeme_za_unos_do = od_datuma + "T" + "00:00" + ":00";
 		}else if(do_datuma == "" && vrijeme_kraj == ""){
 			vrijeme_za_unos_do = "2800-01-01" + "T" + "00:00" + ":00";
 		}else{
 			vrijeme_za_unos_do = do_datuma + "T" + vrijeme_kraj + ":00";
 		}
 		
 		if(naziv == ""){
 			naziv = "nema";
 		}
 		
 		if(adresa == ""){
 			adresa = "nema";
 		}
 		
 		if(grad == ""){
 			grad = "nema";
 		}
 		
 		if(drzava == ""){
 			drzava = "nema";
 		}
 		
 		
 		//let h = JSON.stringify({naziv: naziv, odVrijeme: vrijeme_za_unos_od, doVrijeme: vrijeme_za_unos_do, adresa: adresa, grad: grad, drzava: drzava, odCijena: od_cijena, doCijena: do_cijena, sortiranje: sortiraj, filtriranje: filter, filtriranje2: filter2, kriterijum: kriterijum});
 		//console.log(h);
 		
 		//101-456-890;name=Sabrana dela;author=Pera Perić"
 		
 		$.ajax({
			url: "rest/manifestacije/pretraga/1;naziv=" + naziv + ";odVrijeme=" + vrijeme_za_unos_od + ";doVrijeme=" + vrijeme_za_unos_do +";adresa=" + adresa + ";grad=" + grad +";drzava=" + drzava + ";odCijena=" + od_cijena + ";doCijena=" + do_cijena + ";sortiranje=" + sortiraj + ";filtriranje=" + filter + ";filtriranje2=" + filter2 + ";kriterijum=" + kriterijum,
			type:"GET",
			dataType:"json",
			complete: function(vraceno) {
				console.log(vraceno);
			}
							
		});
 		
 		
  	})
  	
  	
  	
  	
  })