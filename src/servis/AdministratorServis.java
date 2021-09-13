package servis;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.AdministratorDAO;
import klase.Administrator;
import klase.Korisnik;

@Path("/administratori")
public class AdministratorServis {

	@POST
	@Path("/izmeniAdministratora")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String izmeniAdministratora(Korisnik noviAdmin){
		AdministratorDAO.ucitajAdministratore();
		Administrator admin = AdministratorDAO.izmeniAdmina(noviAdmin);
		if (admin != null) LogovanjeServis.ulogovaniAdmin = admin;
		return admin.toString();
	}
}
