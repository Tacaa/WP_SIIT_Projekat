package klase;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.KupacDAO;

public class Kupac extends Korisnik {
	private double brojBodova;
	private ArrayList<Karta> sveKarte;
	private TipKupca tip;
	
	public double getBrojBodova() {
		return brojBodova;
	}
	public void setBrojBodova(double brojBodova) {
		this.brojBodova = brojBodova;
		
		TipKupca obicni = KupacDAO.obicni;
		TipKupca bronzani = KupacDAO.bronzani;
		TipKupca srebrni = KupacDAO.srebrni;
		TipKupca zlatni = KupacDAO.zlatni;
		
		if (this.brojBodova < bronzani.getPotrebihBodova()) this.tip = obicni;
			// vise od 1000 i manje od 3000 => bronzani
		else if (this.brojBodova >= bronzani.getPotrebihBodova() && 
				this.brojBodova < srebrni.getPotrebihBodova()) this.tip = bronzani;
			// vise od 3000 manje od 5000 => srebrni
		else if (this.brojBodova >= srebrni.getPotrebihBodova()&& 
				this.brojBodova < zlatni.getPotrebihBodova()) this.tip = srebrni;
			// vise od 5000 => zlatni
		else if (this.brojBodova >= zlatni.getPotrebihBodova()) this.tip = zlatni;
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
		return "{\"ime\": \""+ this.getIme() +"\", \"prezime\": \""+ this.getPrezime() +
				"\", \"korisnickoIme\": \""+ this.getKorisnickoIme() + "\", \"pol\": \""+ this.getPol() +
				"\", \"lozinka\": \""+ this.getLozinka() +"\", \"datumRodjenja\": \""+ this.getDatumRodjenja() +
				"\", \"brojBodova\": \""+ this.brojBodova +"\", \"sveKarte\": "+ this.sveKarte +
				", \"tip\": "+ this.tip + "}";
	}
	
}
