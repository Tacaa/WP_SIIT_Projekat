package servis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

import dao.KartaDAO;
import dao.ManifestacijaDAO;
import dao.ProdavacDAO;
import klase.CijenaSorter;
import klase.Karta;
import klase.Komentar;
import klase.Kupac;
import klase.Lokacija;
import klase.LokacijaSorter;
import klase.Manifestacija;
import klase.Prodavac;
import klase.StatusKarte;
import klase.StatusManifestacije;
import klase.TipManifestacije;

@Path("/manifestacije")
public class ManifestacijeServis {
	//vraca sve manifestacije koje su statusa AKTIVAN
	@GET
	@Path("/aktivne")
	@Produces(MediaType.APPLICATION_JSON)
	public String aktivne() {
		ManifestacijaDAO.ucitajManifestacije();
		ArrayList<Manifestacija> aktivne = new ArrayList<Manifestacija>();
		
		for(Manifestacija m : ManifestacijaDAO.manifestacije) {
			if(m.getStatus() == StatusManifestacije.AKTIVNA) {
				aktivne.add(m);
			}
		}
		//treba sortirati po vremenu
		Collections.sort(aktivne, (a,b)->a.getVreme().compareTo(b.getVreme()));
		return aktivne.toString();
		
	}
	
	@GET
	@Path("/pretraga/{params}")
	@Produces(MediaType.APPLICATION_JSON)
	public String pretraga(@PathParam("params") PathSegment params) {
		ManifestacijaDAO.ucitajManifestacije();
		ArrayList<Manifestacija> vrati = ManifestacijaDAO.manifestacije;
		
		
		String naziv = params.getMatrixParameters().get("naziv").toString().substring(1,  params.getMatrixParameters().get("naziv").toString().length()-1);
		String odVrijeme = params.getMatrixParameters().get("odVrijeme").toString().substring(1,  params.getMatrixParameters().get("odVrijeme").toString().length()-1);
		String doVrijeme = params.getMatrixParameters().get("doVrijeme").toString().substring(1,  params.getMatrixParameters().get("doVrijeme").toString().length()-1);
		String adresa = params.getMatrixParameters().get("adresa").toString().substring(1,  params.getMatrixParameters().get("adresa").toString().length()-1);
		String grad = params.getMatrixParameters().get("grad").toString().substring(1,  params.getMatrixParameters().get("grad").toString().length()-1);
		String drzava = params.getMatrixParameters().get("drzava").toString().substring(1,  params.getMatrixParameters().get("drzava").toString().length()-1);
		String odCijena = params.getMatrixParameters().get("odCijena").toString().substring(1,  params.getMatrixParameters().get("odCijena").toString().length()-1);
		String doCijena = params.getMatrixParameters().get("doCijena").toString().substring(1,  params.getMatrixParameters().get("doCijena").toString().length()-1);
		String sortiranje = params.getMatrixParameters().get("sortiranje").toString().substring(1,  params.getMatrixParameters().get("sortiranje").toString().length()-1);
		String filtriranje = params.getMatrixParameters().get("filtriranje").toString().substring(1,  params.getMatrixParameters().get("filtriranje").toString().length()-1);
		String filtriranje2 = params.getMatrixParameters().get("filtriranje2").toString().substring(1,  params.getMatrixParameters().get("filtriranje2").toString().length()-1);
		String kriterijum = params.getMatrixParameters().get("kriterijum").toString().substring(1,  params.getMatrixParameters().get("kriterijum").toString().length()-1);
		
		
		
		TipManifestacije tipM;
		
		if(filtriranje.equals("KONCERT")) {
			tipM = TipManifestacije.KONCERT;
		}else if(filtriranje.equals("FESTIVAL")) {
			tipM = TipManifestacije.FESTIVAL;
		}else if(filtriranje.equals("POZORISNA_PREDSTAVA")) {
			tipM = TipManifestacije.POZORISNA_PREDSTAVA;
		}else if(filtriranje.equals("BIOSKOPSKA_PROJEKCIJA")) {
			tipM = TipManifestacije.BIOSKOPSKA_PROJEKCIJA;
		}else if(filtriranje.equals("SPORTSKA_NADMETANJA")) {
			tipM = TipManifestacije.SPORTSKA_NADMETANJA;
		}else if(filtriranje.equals("PLESNI_NASTUP")) {
			tipM = TipManifestacije.PLESNI_NASTUP;
		}else if(filtriranje.equals("STANDUP_NASTUP")) {
			tipM = TipManifestacije.STANDUP_NASTUP;
		}else if(filtriranje.equals("NASTUP_CIRKUSA")) {
			tipM = TipManifestacije.NASTUP_CIRKUSA;
		}else if(filtriranje.equals("SAJAM")) {
			tipM = TipManifestacije.SAJAM;
		}else if(filtriranje.equals("MUZEJSKO_VECE")) {
			tipM = TipManifestacije.MUZEJSKO_VECE;
		}else if(filtriranje.equals("KNJIZEVNO_VECE")) {
			tipM = TipManifestacije.KNJIZEVNO_VECE;
		}else{
			tipM = TipManifestacije.DRUGA_KATEGORIJA;
		}
		
		
		//return null;
		
		
		
		 ArrayList<Manifestacija> izbaci = new ArrayList<Manifestacija>();
			
			for(int i = 0; i<vrati.size(); i++) {
				boolean flag = false;
				Manifestacija m = vrati.get(i);
				//ime
				if(!naziv.equals("nema")) {
					if(!naziv.equalsIgnoreCase(vrati.get(i).getNaziv())) {
						flag = true;
					}
				}
				
				//adresa
				if(!adresa.equals("nema") && flag==true) {
					if(!adresa.equalsIgnoreCase(vrati.get(i).getLokacija().getUlicaBroj())) {
						flag = true;
					}
				}
				
				
				//grad
				if(!grad.equals("nema") && flag==true) {
					if(!grad.equalsIgnoreCase(vrati.get(i).getLokacija().getGrad())) {
						flag = true;
					}
				}
				
				
				//drzava
				if(!drzava.equals("nema") && flag==true) {
					if(!drzava.equalsIgnoreCase(vrati.get(i).getLokacija().getDrzava())) {
						flag = true;
					}
				}
				
				
				if(odVrijeme.compareTo(vrati.get(i).getVreme().toString()) > 0 || doVrijeme.compareTo(vrati.get(i).getVreme().toString()) <0) {
					flag = true;
				}
				
				
				
				int odC = Integer.parseInt(odCijena);
				int doC = Integer.parseInt(doCijena);
				
				if(vrati.get(i).getCena() < odC || vrati.get(i).getCena() > doC) {
					flag = true;
				}
				
				
				
				if(!filtriranje.equalsIgnoreCase("bez")) {
					if(!vrati.get(i).getTip().equals(tipM))
					flag = true;
				}
				
				
				if(filtriranje2.equalsIgnoreCase("da")) {
					if(vrati.get(i).getBrojRezervisanihMesta() == vrati.get(i).getBrojMesta()) {
						flag = true;
					}
				}
				
				
				if(flag) {
					izbaci.add(m);
				}
				
			}
			
			
			//pravi razliku
			vrati.removeAll(izbaci);
			
			//sortiraj sad
			if(sortiranje.equals("sortiraj_po_nazivu")) {
				Collections.sort(vrati, (a,b)->a.getNaziv().compareTo(b.getNaziv()));
				if(kriterijum.equals("opadajucem")) {
					Collections.reverse(vrati);
				}
				
			}else if(sortiranje.equals("sortiraj_po_vremenu")) {
				Collections.sort(vrati, (a,b)->a.getVreme().compareTo(b.getVreme()));
				if(kriterijum.equals("opadajucem")) {
					Collections.reverse(vrati);
				}

			}else if(sortiranje.equals("lokaciji")) {
				Collections.sort(vrati, new LokacijaSorter());
				if(kriterijum.equals("opadajucem")) {
					Collections.reverse(vrati);
				}
				
			}else if(sortiranje.equals("cijeni")) {
				Collections.sort(vrati, new CijenaSorter());
				if(kriterijum.equals("opadajucem")) {
					Collections.reverse(vrati);
				}
			}
			
			
			return vrati.toString();
	}
	
	@GET
	@Path("/zaRezervaciju")
	@Produces(MediaType.APPLICATION_JSON)
	public String sveZaRezervaciju() {
		ManifestacijaDAO.ucitajManifestacije();
		ArrayList<Manifestacija> povratnaLista = new ArrayList<>();
		for (Manifestacija m :  ManifestacijaDAO.manifestacije) 
			if (m.getVreme().isAfter(LocalDateTime.now())
					&& m.getStatus() == StatusManifestacije.AKTIVNA) 
				povratnaLista.add(m);
		return povratnaLista.toString();
	}
	
	@GET
	@Path("/zaKomentarisanje")
	@Produces(MediaType.APPLICATION_JSON)
	public String sveZaKomentarisanje() {
		Kupac kupac = LogovanjeServis.ulogovaniKupac;
		if (kupac == null) return null;
		KartaDAO.ucitajKarte();
		ArrayList<Manifestacija> povratnaLista = new ArrayList<>();
		for (Karta karta : kupac.getSveKarte()) 
			if (karta.getManifestacija().getVreme().isBefore(LocalDateTime.now())
					&& karta.getStatus() != StatusKarte.OBUSTAVLJENA 
					&& !povratnaLista.contains(karta.getManifestacija())) 
				povratnaLista.add(karta.getManifestacija());
		return povratnaLista.toString();
	}

	@GET
	@Path("/zaOdobravanje")
	@Produces(MediaType.APPLICATION_JSON)
	public String sveZaOdobravanje() {
		ArrayList<Manifestacija> povratnaLista = new ArrayList<>();
		for (Manifestacija m : ManifestacijaDAO.manifestacije) 
			if (m.getStatus() == StatusManifestacije.NA_CEKANJU)
				povratnaLista.add(m);
		return povratnaLista.toString();
	}
	
	@GET
	@Path("/odobri/{nazivPlusVremePlusOdobri}")
	@Produces(MediaType.APPLICATION_JSON)
	public String odobri(@PathParam("nazivPlusVremePlusOdobri") String parametar) {
		String[] splitovano = parametar.split("\\+");
		Manifestacija manifestacija = ManifestacijaDAO.nadjiPoNazivuVremenu(splitovano[0], splitovano[1]);
		if (manifestacija == null) return null;
		if (splitovano[2].equals("DA")) manifestacija.setStatus(StatusManifestacije.AKTIVNA);
		else manifestacija.setStatus(StatusManifestacije.ODBIJENA);
		return manifestacija.toString();
	}
	
	
	
	
	
	@POST
	@Path("/kreiraj")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String kreiraj(Manifestacija manifestacija) {
		Lokacija lokacija = new Lokacija(0, 0, manifestacija.getAdresa(), manifestacija.getGrad(), manifestacija.getDrzava(), 0);
		Manifestacija m = new Manifestacija(manifestacija.getNaziv(), manifestacija.getTip(), manifestacija.getBrojMesta(), manifestacija.getVreme(), manifestacija.getCena(),
				StatusManifestacije.NA_CEKANJU, lokacija, manifestacija.getPoster(), 0,
				new ArrayList<Komentar>(), 0, manifestacija.getProdavac());
		
		ManifestacijaDAO.manifestacije.add(m);
		
		//dodaj u prodavcevu listu manifestacija
		Prodavac prodavac = ProdavacDAO.prodavci.get(manifestacija.getProdavac());
		if(prodavac == null) {
			return null;
		}else {
			prodavac.getManifestacije().add(m);
			Collections.sort(prodavac.getManifestacije(), (a,b)->a.getVreme().compareTo(b.getVreme()));
		}
		
		return "Dodano!";
	}
	
	
	
	@PUT
	@Path("/mijenjaj")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String mijenjaj(Manifestacija manifestacija) {
		
		for(int i = 0; i<ManifestacijaDAO.manifestacije.size(); i++) {
			if(ManifestacijaDAO.manifestacije.get(i).getNaziv().equals(manifestacija.getStariNaziv()) && ManifestacijaDAO.manifestacije.get(i).getVreme().equals(manifestacija.getStaroVrijeme())) {
				StatusManifestacije status = ManifestacijaDAO.manifestacije.get(i).getStatus();
				Lokacija lokacija = ManifestacijaDAO.manifestacije.get(i).getLokacija();
				lokacija.setDrzava(manifestacija.getDrzava());
				lokacija.setGrad(manifestacija.getGrad());
				lokacija.setUlicaBroj(manifestacija.getAdresa());
				int brojRezMjesta = ManifestacijaDAO.manifestacije.get(i).getBrojRezervisanihMesta();
				ArrayList<Komentar> komentari = ManifestacijaDAO.manifestacije.get(i).getKomentari();
				double ocena = ManifestacijaDAO.manifestacije.get(i).getOcena();
				
				
				
				Manifestacija m = new Manifestacija(manifestacija.getNaziv(), manifestacija.getTip(), manifestacija.getBrojMesta(), manifestacija.getVreme(), manifestacija.getCena(),
						status, lokacija, manifestacija.getPoster(), brojRezMjesta,
						komentari, ocena, manifestacija.getProdavac());
				
				Prodavac p = ProdavacDAO.prodavci.get(manifestacija.getProdavac());

				for(int j = 0; j<p.getManifestacije().size(); j++) {
					if(p.getManifestacije().get(j).equals(ManifestacijaDAO.manifestacije.get(i))) {
						p.getManifestacije().remove(j);
						p.getManifestacije().add(m);
						
						Collections.sort(p.getManifestacije(), (a,b)->a.getVreme().compareTo(b.getVreme()));
					}
					
					
				}
				
				ManifestacijaDAO.manifestacije.remove(i);
				ManifestacijaDAO.manifestacije.add(m);
				
				
				return m.toString();
			}
		}
		
		return null;
		
		
		
		
	}
	
	
	
	@GET
	@Path("/prodavceve_manifestacije/{prodavac}")
	@Produces(MediaType.APPLICATION_JSON)
	public String prodavceve_manifestacije(@PathParam("prodavac") String prodavac) {
		if(ProdavacDAO.prodavci.size() == 0) {
			ProdavacDAO.ucitajProdavce();
		}
		
		Prodavac pronadjenProdavac = ProdavacDAO.prodavci.get(prodavac);
		return pronadjenProdavac.getManifestacije().toString();
	}
	
	
}
