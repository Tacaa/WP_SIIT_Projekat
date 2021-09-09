package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import klase.AktivnostKorisnika;
import klase.Korisnik;
import klase.Manifestacija;
import klase.Pol;
import klase.Prodavac;

public class ProdavacDAO {
	
	private static String sledeciRed = System.lineSeparator();
	private static final String putanja = "C:\\Users\\Admin\\Desktop\\VezbeWeb\\Projekat\\WP_SIIT_Projekat\\src\\podaci\\prodavci.csv";
	
	public static HashMap<String, Prodavac> prodavci = new HashMap<String, Prodavac>();
	
	public ProdavacDAO() {}

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
		sacuvajProdavce();
		return izabrani;
	}
	
	public static Prodavac dodajProdavca(Prodavac prodavac) {
		if (KupacDAO.zauzetoKorisnickoIme(prodavac.getKorisnickoIme())) return null;
		
		prodavci.put(prodavac.getKorisnickoIme(), prodavac);
		sacuvajProdavce();
		return prodavac;
	}
	
	public static boolean sacuvajProdavce() {
		StringBuilder zaUpis = new StringBuilder();
		for (Prodavac p : prodavci.values()) zaUpis.append(p.zaCuvanje() + sledeciRed);
		try {
			PrintWriter pw = new PrintWriter(new File(putanja));
			pw.print(zaUpis.toString());
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}
	
	public static boolean ucitajProdavce() {
		if (!prodavci.isEmpty()) return true;		// vec su ucitani
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(putanja)));
			String linija = br.readLine();
			while (!linija.equals("")) {
				// ime,prezime,kIme,pol,lozinka,rodjendan,aktivnost
				String[] splitovano = linija.split(";");
				Pol pol = Pol.MUSKI;
				if (splitovano[3].equals("ZENSKI")) pol = Pol.ZENSKI;
				
				AktivnostKorisnika aktivnost = AktivnostKorisnika.AKTIVAN;
				if (splitovano[6].equals("IZBRISAN")) aktivnost = AktivnostKorisnika.IZBRISAN;
				else if (splitovano[6].equals("BLOKIRAN")) aktivnost = AktivnostKorisnika.BLOKIRAN;
				
				LocalDate datum = LocalDate.parse(splitovano[5]);
				
				Prodavac prodavac = new Prodavac(splitovano[2], splitovano[4], splitovano[0], 
						splitovano[1], pol, datum, aktivnost, new ArrayList<Manifestacija>());
				prodavci.put(prodavac.getKorisnickoIme(), prodavac);
				linija = br.readLine();
				if (linija == null) break;
			}
			br.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
