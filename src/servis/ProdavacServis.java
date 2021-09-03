package servis;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.KupacDAO;
import dao.ProdavacDAO;
import klase.AktivnostKorisnika;
import klase.Korisnik;
import klase.Kupac;
import klase.Prodavac;

@Path("/prodavci")
public class ProdavacServis {

	@POST
	@Path("/izmeniProdavca")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String izmeniProdavca(Korisnik prodavac){
		ProdavacDAO.ucitajProdavce();
		return ProdavacDAO.izmeniProdavca(prodavac).toString();
	}
	
	@POST
	@Path("/registrujProdavca")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registrujProdavca(Korisnik noviKorisnik){
		Prodavac noviProdavac = new Prodavac(noviKorisnik.getKorisnickoIme(), 
				noviKorisnik.getLozinka(), noviKorisnik.getIme(), noviKorisnik.getPrezime(), 
				noviKorisnik.getPol(), noviKorisnik.getDatumRodjenja(), AktivnostKorisnika.AKTIVAN, 
				new ArrayList<>());
		ProdavacDAO.ucitajProdavce();
		return ProdavacDAO.dodajProdavca(noviProdavac).toString();
		
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProdavce(){
		return ProdavacDAO.prodavci.values().toString();
	}
	
	@GET
	@Path("/{korisnickoIme}")
	@Produces(MediaType.APPLICATION_JSON)
	public String izbrisiProdavca(@PathParam("korisnickoIme") String korisnickoIme){
		Prodavac prodavac = ProdavacDAO.prodavci.get(korisnickoIme);
		prodavac.setAktivnost(AktivnostKorisnika.IZBRISAN);
		return prodavac.toString();
	}

	@POST
	@Path("/filterProdavaca")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String filtriranjeProdavaca(Prodavac filter){
		ProdavacDAO.ucitajProdavce();
		ArrayList<Prodavac> povratnaLista = new ArrayList<>();
		for (Prodavac p : ProdavacDAO.prodavci.values()) {
			if (!filter.getIme().equals("")) 
				if (!p.getIme().toLowerCase().contains(filter.getIme().toLowerCase())) continue;
			if (!filter.getPrezime().equals("")) 
				if (!p.getPrezime().toLowerCase().contains(filter.getPrezime().toLowerCase())) continue;
			if (!filter.getKorisnickoIme().equals("")) 
				if (!p.getKorisnickoIme().toLowerCase().contains(filter.getKorisnickoIme().toLowerCase())) continue;
			povratnaLista.add(p);
		}
		return povratnaLista.toString();
	}
}
