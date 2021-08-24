package klase;

import java.time.LocalDate;
import java.util.ArrayList;

public class Prodavac extends Korisnik {

	private ArrayList<Manifestacija> manifestacije;

	public ArrayList<Manifestacija> getManifestacije() {
		return manifestacije;
	}
	public void setManifestacije(ArrayList<Manifestacija> manifestacije) {
		this.manifestacije = manifestacije;
	}
	
	public Prodavac() {
		super();
	}
	
	public Prodavac(ArrayList<Manifestacija> manifestacije) {
		super();
		this.manifestacije = manifestacije;
	}
	
	public Prodavac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, 
			LocalDate datumRodjenja, AktivnostKorisnika aktivnost, 
			ArrayList<Manifestacija> manifestacije) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, aktivnost);
		this.manifestacije = manifestacije;
	}
	@Override
	public String toString() {
		return "{\"ime\": \""+ this.getIme() +"\", \"prezime\": \""+ this.getPrezime() +
				"\", \"korisnickoIme\": \""+ this.getKorisnickoIme() + "\", \"pol\": \""+ this.getPol() +
				"\", \"lozinka\": \""+ this.getLozinka() +"\", \"datumRodjenja\": \""+ this.getDatumRodjenja() +
				"\", \"manifestacije\": "+ this.manifestacije + "}";
	}
	
}
