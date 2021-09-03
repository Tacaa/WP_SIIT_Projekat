package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import klase.Administrator;
import klase.AktivnostKorisnika;
import klase.Korisnik;
import klase.Kupac;
import klase.Manifestacija;
import klase.Pol;
import klase.Prodavac;

public class ProdavacDAO {
	/*public Prodavac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, 
			LocalDate datumRodjenja, AktivnostKorisnika aktivnost, 
			ArrayList<Manifestacija> manifestacije) {*/
	
	public static HashMap<String, Prodavac> prodavci = new HashMap<String, Prodavac>();
	
	
	public static void ucitajProdavce() {
		//prodavci
		if (prodavci.size() != 0) return;
		
		Prodavac daca = new Prodavac("daca", "daca", "Danijela", "Djumic", Pol.ZENSKI, LocalDate.of(1997, 8, 5), AktivnostKorisnika.AKTIVAN, new ArrayList<Manifestacija>());
		Prodavac mama = new Prodavac("lidija", "lidija", "Lidija", "Gavrilovic", Pol.ZENSKI, LocalDate.of(1977, 11, 17), AktivnostKorisnika.AKTIVAN, new ArrayList<Manifestacija>());
		Prodavac nana = new Prodavac("nana", "nana", "Nana", "Nanic", Pol.ZENSKI, LocalDate.of(2001, 4, 13), AktivnostKorisnika.IZBRISAN, new ArrayList<>());
		
		prodavci.put(daca.getKorisnickoIme(), daca);
		prodavci.put(mama.getKorisnickoIme(), mama);
		prodavci.put(nana.getKorisnickoIme(), nana);
	}
	

	private static Prodavac getProdavca(String korisnickoIme) {
		for (Prodavac p : prodavci.values()) if (p.getKorisnickoIme().equals(korisnickoIme)) return p;
		return null;
	}
	
	public static Prodavac izmeniProdavca(Korisnik prodavac) {
		Prodavac izabrani = getProdavca(prodavac.getKorisnickoIme());
		if (izabrani == null) return null;
		izabrani.setIme(prodavac.getIme());
		izabrani.setPrezime(prodavac.getPrezime());
		izabrani.setPol(prodavac.getPol());
		izabrani.setDatumRodjenja(prodavac.getDatumRodjenja());
		izabrani.setLozinka(prodavac.getLozinka());
		/*try {
			this.upisiKupce();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return izabrani;
	}
	
	public static Prodavac dodajProdavca(Prodavac prodavac) {
		if (KupacDAO.zauzetoKorisnickoIme(prodavac.getKorisnickoIme())) return null;
		
		prodavci.put(prodavac.getKorisnickoIme(), prodavac);
		/*try {
			this.upisiKupce();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return prodavac;
	}
}
