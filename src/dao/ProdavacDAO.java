package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


import klase.AktivnostKorisnika;
import klase.Manifestacija;
import klase.Pol;
import klase.Prodavac;

public class ProdavacDAO {
	/*public Prodavac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, 
			LocalDate datumRodjenja, AktivnostKorisnika aktivnost, 
			ArrayList<Manifestacija> manifestacije) {*/
	
	public static HashMap<String, Prodavac> prodavci = new HashMap<String, Prodavac>();
	
	
	public static void ucitajProdavce() {
		//administratori
		Prodavac daca = new Prodavac("daca", "daca", "Danijela", "Djumic", Pol.zena, LocalDate.of(1997, 4, 13), AktivnostKorisnika.AKTIVAN, new ArrayList<Manifestacija>());
		Prodavac mama = new Prodavac("lidija", "lidija", "Lidija", "Gavrilovic", Pol.zena, LocalDate.of(1977, 11, 17), AktivnostKorisnika.AKTIVAN, new ArrayList<Manifestacija>());
		
		prodavci.put(daca.getKorisnickoIme(), daca);
		prodavci.put(mama.getKorisnickoIme(), mama);
	}
	
	
}
