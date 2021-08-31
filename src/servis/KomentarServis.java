package servis;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.KomentarDAO;
import dao.ManifestacijaDAO;
import klase.Komentar;
import klase.Kupac;
import klase.Manifestacija;

@Path("komentari")
public class KomentarServis {


	@POST
	@Path("/napraviNovi")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String napraviNoviKomentar(Komentar komentar){
		Kupac kupac = LogovanjeServis.ulogovaniKupac;
		if (kupac == null) return null;					// tehnicki nemoguce
		komentar.setKupac(kupac);
		Manifestacija manifestacija = ManifestacijaDAO.nadjiPoNazivuVremenu(
				komentar.getManifestacija().getNaziv(), 
				komentar.getManifestacija().getVreme().toString());
		if (manifestacija == null) return null;			// tehnicki nikad
		komentar.setManifestacija(manifestacija);
		
		KomentarDAO.komentari.add(komentar);
		manifestacija.getKomentari().add(komentar);		// ako se odobri onda se racuna nova ocena man
		return komentar.toString();
	}
}
