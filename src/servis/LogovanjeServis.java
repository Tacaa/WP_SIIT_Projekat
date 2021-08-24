package servis;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

import dao.AdministratorDAO;
import dao.KupacDAO;
import dao.ProdavacDAO;
import klase.Administrator;
import klase.Korisnik;
import klase.Kupac;
import klase.Prodavac;



@Path("/login_out")
public class LogovanjeServis {
	
	@POST
	@Path("/logovanje")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String logovanje(@Context HttpServletRequest zahtjev, Korisnik korisnik) {
		//prvo ucitaj sve iz male baze
		if(KupacDAO.kupci.size() == 0) {
			KupacDAO.ucitajKupce();
		}
		if(AdministratorDAO.administratori.size() == 0) {
			AdministratorDAO.ucitajAdministratore();
		}
		if(ProdavacDAO.prodavci.size() == 0) {
			ProdavacDAO.ucitajProdavce();
		}
		
		//tip uloge 
		boolean kupacJe = false;
		boolean administratorJe = false;
		boolean prodavacJe = false;
		
		
		//provjeriti da li postoji korisnik u nekoj od malih baza i ako postoji saznajemo i koji tip korisnika je
		if(KupacDAO.kupci.containsKey(korisnik.getKorisnickoIme())) {
			kupacJe = true;
			if(!KupacDAO.kupci.get(korisnik.getKorisnickoIme()).getLozinka().equals(korisnik.getLozinka())) {
				return null;
			}
		}else if(AdministratorDAO.administratori.containsKey(korisnik.getKorisnickoIme())) {
			administratorJe = true;
			if(!AdministratorDAO.administratori.get(korisnik.getKorisnickoIme()).getLozinka().equals(korisnik.getLozinka())) {
				return null;
			}
		}else if(ProdavacDAO.prodavci.containsKey(korisnik.getKorisnickoIme())) {
			prodavacJe = true;
			if(!ProdavacDAO.prodavci.get(korisnik.getKorisnickoIme()).getLozinka().equals(korisnik.getLozinka())) {
				return null;
			}
		}else {
			return null; //ako ne postoji vrati null za ponovno logovanje
		}
		
		
		//u zavisnosti od uloge provjeriti da li je na sesiji
		if(kupacJe) {
			Kupac kupacNaSesiji = null;
			kupacNaSesiji = (Kupac) zahtjev.getSession().getAttribute("trenutniKupac");
			if (kupacNaSesiji == null) {
				zahtjev.getSession().setAttribute("kupacNaSesiji", KupacDAO.kupci.get(korisnik.getKorisnickoIme()));
				kupacNaSesiji = KupacDAO.kupci.get(korisnik.getKorisnickoIme());
			}
			return "kupac";
		}
		else if(prodavacJe) {
			Prodavac prodavacNaSesiji = null;
			prodavacNaSesiji = (Prodavac) zahtjev.getSession().getAttribute("trenutniProdavac");
			if (prodavacNaSesiji == null) {
				zahtjev.getSession().setAttribute("trenutniProdavac", ProdavacDAO.prodavci.get(korisnik.getKorisnickoIme()));
				prodavacNaSesiji = ProdavacDAO.prodavci.get(korisnik.getKorisnickoIme());
			}
			return "prodavac";
		}
		else if(administratorJe) {
			Administrator administratorNaSesiji = null;
			administratorNaSesiji = (Administrator) zahtjev.getSession().getAttribute("trenutniAdministrator");
			if (administratorNaSesiji == null) {
				zahtjev.getSession().setAttribute("trenutniAdministrator", AdministratorDAO.administratori.get(korisnik.getKorisnickoIme()));
				administratorNaSesiji = AdministratorDAO.administratori.get(korisnik.getKorisnickoIme());
			}
			return "administrator";
		}
		
		//ne bi trebalo doci ovdje nikad
		return null;
		
	}
	
	
	
	
	@GET
	@Path("/trenutniKupac/{korisnickoIme}")
	public Kupac trenutniKupac(@PathParam("korisnickoIme") String korisnickoIme) {
		return KupacDAO.kupci.get(korisnickoIme);
		
	}
	
	

	@GET
	@Path("/trenutniProdavac/{korisnickoIme}")
	public Prodavac trenutniProdavac(@PathParam("korisnickoIme") String korisnickoIme) {
		return ProdavacDAO.prodavci.get(korisnickoIme);
		
	}
	
	
	

	@GET
	@Path("/trenutniAdministrator/{korisnickoIme}")
	public Administrator trenutniAdministrator(@PathParam("korisnickoIme") String korisnickoIme) {
		return AdministratorDAO.administratori.get(korisnickoIme);
		
	}
	
	
	
	
	
}
