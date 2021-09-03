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
import klase.AktivnostKorisnika;
import klase.Karta;
import klase.Korisnik;
import klase.Kupac;

@Path("/kupci")
public class KupacServis {
	
	@POST
	@Path("/registrujKupca")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registrujKupca(Korisnik noviKorisnik){
		Kupac noviKupac = new Kupac(noviKorisnik.getKorisnickoIme(), noviKorisnik.getLozinka(), noviKorisnik.getIme(), 
				noviKorisnik.getPrezime(), noviKorisnik.getPol(), noviKorisnik.getDatumRodjenja(), 
				AktivnostKorisnika.AKTIVAN, 0, new ArrayList<Karta>(), KupacDAO.obicni);
		
		KupacDAO.ucitajKupce();
		return KupacDAO.dodajKupca(noviKupac).toString();
		
	}
	
	@POST
	@Path("/izmeniKupca")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String izmeniKupca(Korisnik kupac){
		KupacDAO.ucitajKupce();
		return KupacDAO.izmeniKupca(kupac).toString();
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProdavce(){
		return KupacDAO.kupci.values().toString();
	}
	
	@GET
	@Path("/{korisnickoIme}")
	@Produces(MediaType.APPLICATION_JSON)
	public String izbrisiKupca(@PathParam("korisnickoIme") String korisnickoIme){
		Kupac kupac = KupacDAO.kupci.get(korisnickoIme);
		kupac.setAktivnost(AktivnostKorisnika.IZBRISAN);
		return kupac.toString();
	}
}
