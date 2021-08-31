package servis;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.KupacDAO;
import klase.AktivnostKorisnika;
import klase.ImeTipaKupca;
import klase.Karta;
import klase.Korisnik;
import klase.Kupac;
import klase.TipKupca;

@Path("/kupci")
public class KupacServis {
	
	@POST
	@Path("/registrujKupca")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Kupac registrujKupca(Korisnik noviKorisnik){
		Kupac noviKupac = new Kupac(noviKorisnik.getKorisnickoIme(), noviKorisnik.getLozinka(), noviKorisnik.getIme(), 
				noviKorisnik.getPrezime(), noviKorisnik.getPol(), noviKorisnik.getDatumRodjenja(), 
				AktivnostKorisnika.AKTIVAN, 0, new ArrayList<Karta>(), KupacDAO.obicni);
		
		if(KupacDAO.kupci.size() == 0) {
			KupacDAO.ucitajKupce();
		}
		return KupacDAO.dodajKupca(noviKupac);
		
	}
	
	@POST
	@Path("/izmeniKupca")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String izmeniKupca(Korisnik kupac){
		if(KupacDAO.kupci.size() == 0) KupacDAO.ucitajKupce();
		return KupacDAO.izmeniKupca(kupac).toString();
	}
	
}
