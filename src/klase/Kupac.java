package klase;

import java.time.LocalDate;
import java.util.ArrayList;

public class Kupac extends Korisnik {
	private double brojBodova;
	private ArrayList<Karta> sveKarte;
	private TipKupca tip;
	
	public double getBrojBodova() {
		return brojBodova;
	}
	public void setBrojBodova(double brojBodova) {
		this.brojBodova = brojBodova;
	}
	
	public ArrayList<Karta> getSveKarte() {
		return sveKarte;
	}
	public void setSveKarte(ArrayList<Karta> sveKarte) {
		this.sveKarte = sveKarte;
	}
	
	public TipKupca getTip() {
		return tip;
	}
	public void setTip(TipKupca tip) {
		this.tip = tip;
	}
	
	public Kupac() {
		super();
	}
	
	public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, 
			LocalDate datumRodjenja, AktivnostKorisnika aktivnost, double brojBodova, 
			ArrayList<Karta> sveKarte, TipKupca tip) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, aktivnost);
		this.brojBodova = brojBodova;
		this.sveKarte = sveKarte;
		this.tip = tip;
	}
	
	public Kupac(double brojBodova, ArrayList<Karta> sveKarte, TipKupca tip) {
		super();
		this.brojBodova = brojBodova;
		this.sveKarte = sveKarte;
		this.tip = tip;
	}
	
	@Override
	public String toString() {
		return "{ime: \""+ this.getIme() +"\", prezime: \""+ this.getPrezime() +
				"\", korisnickoIme: \""+ this.getKorisnickoIme() + "\", pol: \""+ this.getPol() +
				"\", lozinka: \""+ this.getLozinka() +"\", datumRodjenja: \""+ this.getDatumRodjenja() +
				"\", brojBodova: \""+ this.getBrojBodova() +"\", sveKarte: \""+ this.getSveKarte() +
				"\", tip: \""+ this.getTip() +"\"}";
	}
	
}
