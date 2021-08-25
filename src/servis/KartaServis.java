package servis;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.KartaDAO;
import klase.Karta;

@Path("/karte")
public class KartaServis {
	
	private String napraviString(ArrayList<Karta> lista) {
		StringBuilder zaString = new StringBuilder();
		zaString.append("[");
		for (Karta k : lista) zaString.append(k + ", ");
		zaString.replace(zaString.length() - 2, zaString.length(), "");
		zaString.append("]");
		return zaString.toString();
	}
	
	@GET
	@Path("/sveKarteKupca")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getSveKarteKupca(){
		if (LogovanjeServis.ulogovaniKupac == null) return null;
		return KartaDAO.karte.toString();
	}

}
