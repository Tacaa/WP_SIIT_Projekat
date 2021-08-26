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
	
	public static Administrator ulogovaniAdmin = null;
	public static Kupac ulogovaniKupac = null;
	public static Prodavac ulogovaniProdavac = null;
	public static String koJeUlogovan = "niko";
	
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
		
		
		//provjeriti da li postoji korisnik u nekoj od malih baza i ako postoji saznajemo i koji tip korisnika je
		if(KupacDAO.kupci.containsKey(korisnik.getKorisnickoIme())) {
			koJeUlogovan = "kupac";
			if(!KupacDAO.kupci.get(korisnik.getKorisnickoIme()).getLozinka().equals(korisnik.getLozinka())) {
				return null;
			}
		}else if(AdministratorDAO.administratori.containsKey(korisnik.getKorisnickoIme())) {
			koJeUlogovan = "administrator";
			if(!AdministratorDAO.administratori.get(korisnik.getKorisnickoIme()).getLozinka().equals(korisnik.getLozinka())) {
				return null;
			}
		}else if(ProdavacDAO.prodavci.containsKey(korisnik.getKorisnickoIme())) {
			koJeUlogovan = "prodavac";
			if(!ProdavacDAO.prodavci.get(korisnik.getKorisnickoIme()).getLozinka().equals(korisnik.getLozinka())) {
				return null;
			}
		}else {
			return null; //ako ne postoji vrati null za ponovno logovanje
		}
		
		
		//u zavisnosti od uloge provjeriti da li je na sesiji
		if(koJeUlogovan.equals("kupac")) {
			ulogovaniKupac = KupacDAO.kupci.get(korisnik.getKorisnickoIme());
			Kupac kupacNaSesiji = null;
			kupacNaSesiji = (Kupac) zahtjev.getSession().getAttribute("trenutniKupac");
			if (kupacNaSesiji == null) {
				zahtjev.getSession().setAttribute("kupacNaSesiji", ulogovaniKupac);
				kupacNaSesiji = KupacDAO.kupci.get(korisnik.getKorisnickoIme());
			}
		}
		else if(koJeUlogovan.equals("prodavac")) {
			ulogovaniProdavac = ProdavacDAO.prodavci.get(korisnik.getKorisnickoIme());
			Prodavac prodavacNaSesiji = null;
			prodavacNaSesiji = (Prodavac) zahtjev.getSession().getAttribute("trenutniProdavac");
			if (prodavacNaSesiji == null) {
				zahtjev.getSession().setAttribute("trenutniProdavac", ulogovaniProdavac);
				prodavacNaSesiji = ProdavacDAO.prodavci.get(korisnik.getKorisnickoIme());
			}
		}
		else if(koJeUlogovan.equals("administrator")) {
			ulogovaniAdmin = AdministratorDAO.administratori.get(korisnik.getKorisnickoIme());
			Administrator administratorNaSesiji = null;
			administratorNaSesiji = (Administrator) zahtjev.getSession().getAttribute("trenutniAdministrator");
			if (administratorNaSesiji == null) {
				zahtjev.getSession().setAttribute("trenutniAdministrator", ulogovaniAdmin);
				administratorNaSesiji = AdministratorDAO.administratori.get(korisnik.getKorisnickoIme());
			}
		}
		return koJeUlogovan;
	}
	
	
	
	
	@GET
	@Path("/trenutniKupac/{korisnickoIme}")
	public String trenutniKupac(@PathParam("korisnickoIme") String korisnickoIme) {
		return KupacDAO.kupci.get(korisnickoIme).toString();
	}
	
	

	@GET
	@Path("/trenutniProdavac/{korisnickoIme}")
	public String trenutniProdavac(@PathParam("korisnickoIme") String korisnickoIme) {
		return ProdavacDAO.prodavci.get(korisnickoIme).toString();
		
	}
	
	
	

	@GET
	@Path("/trenutniAdministrator/{korisnickoIme}")
	public String trenutniAdministrator(@PathParam("korisnickoIme") String korisnickoIme) {
		return AdministratorDAO.administratori.get(korisnickoIme).toString();
		
	}
	
	
	
	
	
}