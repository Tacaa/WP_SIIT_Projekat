package servis;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.KomentarDAO;
import dao.ManifestacijaDAO;
import klase.Komentar;
import klase.Kupac;
import klase.Manifestacija;
import klase.StatusKomentara;

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
		KomentarDAO.sacuvajKomentare();
		return komentar.toString();
	}
	
	@GET
	@Path("/odobri/{parametri}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String odobriKomentar(@PathParam("parametri") String parametri){
		// korIme+tekst+ocena+DA   ili     korIme+tekst+ocena+NE
		String[] splitovano = parametri.split("\\+");
		for (Komentar k : KomentarDAO.komentari) {
			if (!k.getKupac().getKorisnickoIme().equals(splitovano[0])) continue;
			if (!k.getTekst().equals(splitovano[1])) continue;
			if (k.getOcena() != Double.parseDouble(splitovano[2])) continue;
			if (splitovano[3].equals("DA")) k.setStatus(StatusKomentara.PRIHVACEN);
			else k.setStatus(StatusKomentara.ODBIJEN);
			KomentarDAO.sacuvajKomentare();
			return k.toString();
		}
		return null;
		
	}
}
