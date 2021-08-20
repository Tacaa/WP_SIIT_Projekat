package klase;

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
	
}
