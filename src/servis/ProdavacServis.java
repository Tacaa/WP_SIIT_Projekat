package servis;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.ProdavacDAO;
import klase.AktivnostKorisnika;
import klase.Korisnik;
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
}
