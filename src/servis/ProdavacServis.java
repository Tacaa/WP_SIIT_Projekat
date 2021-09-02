package servis;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.ProdavacDAO;
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

}
