package klase;

import java.time.LocalDate;

public class Administrator extends Korisnik {
	
	public Administrator() {
		super();
	}
	
	
	public Administrator(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, 
			LocalDate datumRodjenja, AktivnostKorisnika aktivnost) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, aktivnost);
	}
	
	
	
}
