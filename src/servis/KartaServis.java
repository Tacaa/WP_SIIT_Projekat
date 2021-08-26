package servis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.KartaDAO;
import klase.FilterObjekat;
import klase.Karta;

@Path("/karte")
public class KartaServis {
	
	@GET
	@Path("/sveKarte")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getSveKarte(){
		if (KartaDAO.karte.size() == 0) KartaDAO.ucitajKarte();
		return KartaDAO.karte.toString();
	}
	
	@POST
	@Path("/filterKarata")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String filterKarataKupca(FilterObjekat filter){
		if (LogovanjeServis.ulogovaniKupac == null) return null;
		ArrayList<Karta> sveKarte = LogovanjeServis.ulogovaniKupac.getSveKarte();
		ArrayList<Karta> odabrane = new ArrayList<>();
		for (Karta k : sveKarte) {
			if (filter.getCenaMin() != 0) if (filter.getCenaMin() > k.getKonacnaCena()) continue;
			if (filter.getCenaMax() != 0) if (filter.getCenaMin() < k.getKonacnaCena()) continue;
			if (filter.getDatumMin() != null) {
				LocalDateTime pomocniMin = LocalDateTime.now();
				LocalDate filterMin = filter.getDatumMin();
				pomocniMin.withDayOfMonth(filterMin.getDayOfMonth());
				pomocniMin.withYear(filterMin.getYear());
				pomocniMin.withMonth(filterMin.getMonthValue());
				if (k.getManifestacija().getVreme().isBefore(pomocniMin)) continue;
			}
			if (filter.getDatumMax() != null) {
				LocalDateTime pomocniMax = LocalDateTime.now();
				LocalDate filterMax = filter.getDatumMax();
				pomocniMax.withDayOfMonth(filterMax.getDayOfMonth());
				pomocniMax.withYear(filterMax.getYear());
				pomocniMax.withMonth(filterMax.getMonthValue());
				if (k.getManifestacija().getVreme().isBefore(pomocniMax)) continue;
			}
			if (!k.getManifestacija().getNaziv().toLowerCase().contains(filter.getNaziv().toLowerCase()))
				continue;
			if (filter.getStatus() != k.getStatus()) continue;
			if (filter.getTip() != k.getTip()) continue;
			odabrane.add(k);
		}
		return odabrane.toString();
	}

}
