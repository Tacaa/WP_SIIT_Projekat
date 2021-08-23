package klase;

import java.time.LocalDate;

public class Korisnik {
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private Pol pol;
	private LocalDate datumRodjenja;
	private AktivnostKorisnika aktivnost;
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
	public AktivnostKorisnika getAktivnost() {
		return aktivnost;
	}
	public void setAktivnost(AktivnostKorisnika aktivnost) {
		this.aktivnost = aktivnost;
	}
	
	public Korisnik() {
		super();
	}
	
	public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, 
			LocalDate datumRodjenja, AktivnostKorisnika aktivnost) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.aktivnost = aktivnost;
	}
	
	@Override
	public String toString() {
		return "{ime: \""+ this.getIme() +"\", prezime: \""+ this.getPrezime() +
				"\", korisnickoIme: \""+ this.getKorisnickoIme() + "\", pol: \""+ this.getPol() +
				"\", lozinka: \""+ this.getLozinka() +"\", datumRodjenja: \""
				+ this.getDatumRodjenja() + "\"}";
	}
	
	
}
