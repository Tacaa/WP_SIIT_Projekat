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

import klase.Administrator;
import klase.AktivnostKorisnika;
import klase.ImeTipaKupca;
import klase.Korisnik;
import klase.Kupac;
import klase.Pol;
import klase.Prodavac;
import klase.TipKupca;

public class KupacDAO {
	public static HashMap<String, Kupac> kupci = new  HashMap<String, Kupac>();
	public static TipKupca zlatni = new TipKupca(ImeTipaKupca.ZLATNI, 0.7, 5000);
	public static TipKupca srebrni = new TipKupca(ImeTipaKupca.SREBRNI, 0.8, 3000);
	public static TipKupca bronzani = new TipKupca(ImeTipaKupca.BRONZANI, 0.9, 1000);
	public static TipKupca obicni = new TipKupca(ImeTipaKupca.OBICNI, 1, 0);
	
	private static String sledeciRed = System.lineSeparator();
	private static final String putanja = "C:\\Users\\Admin\\Desktop\\VezbeWeb\\Projekat\\WP_SIIT_Projekat\\src\\podaci\\kupci.csv";
	
	public KupacDAO() {}
	
	public static boolean zauzetoKorisnickoIme(String novoKorIme) {
		for (Kupac k : kupci.values()) if (k.getKorisnickoIme().equals(novoKorIme)) return true;
		for (Prodavac p : ProdavacDAO.prodavci.values()) if (p.getKorisnickoIme().equals(novoKorIme))
				return true;
		for (Administrator a : AdministratorDAO.administratori.values()) 
			if (a.getKorisnickoIme().equals(novoKorIme)) return true;
		return false;
	}

	private static Kupac getKupca(String korisnickoIme) {
		for (Kupac k : kupci.values()) if (k.getKorisnickoIme().equals(korisnickoIme)) return k;
		return null;
	}
	
	
	public static Kupac dodajKupca(Kupac kupac) {
		if (zauzetoKorisnickoIme(kupac.getKorisnickoIme())) return null;
		
		kupci.put(kupac.getKorisnickoIme(), kupac);
		sacuvajKupce();
		return kupac;
	}

	public static Kupac izmeniKupca(Korisnik kupac) {
		Kupac izabrani = getKupca(kupac.getKorisnickoIme());
		if (izabrani == null) return null;
		izabrani.setIme(kupac.getIme());
		izabrani.setPrezime(kupac.getPrezime());
		izabrani.setPol(kupac.getPol());
		izabrani.setDatumRodjenja(kupac.getDatumRodjenja());
		izabrani.setLozinka(kupac.getLozinka());
		sacuvajKupce();
		return izabrani;
	}
	
	public static boolean sacuvajKupce() {
		StringBuilder zaUpis = new StringBuilder();
		for (Kupac k : kupci.values()) zaUpis.append(k.zaCuvanje() + sledeciRed);
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
	
	public static boolean ucitajKupce() {
		if (!kupci.isEmpty()) return true;		// vec su ucitani
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(putanja)));
			String linija = br.readLine();
			while (!linija.equals("")) {
				// ime,prezime,kIme,pol,lozinka,rodjendan,aktivnost,tip,brBodova
				String[] splitovano = linija.split(";");
				Pol pol = Pol.MUSKI;
				if (splitovano[3].equals("ZENSKI")) pol = Pol.ZENSKI;
				
				TipKupca tip = KupacDAO.obicni;
				if (splitovano[7].equals("BRONZANI")) tip = KupacDAO.bronzani;
				else if (splitovano[7].equals("SREBRNI")) tip = KupacDAO.srebrni;
				else if (splitovano[7].equals("ZLATNI")) tip = KupacDAO.zlatni;
				
				AktivnostKorisnika aktivnost = AktivnostKorisnika.AKTIVAN;
				if (splitovano[6].equals("IZBRISAN")) aktivnost = AktivnostKorisnika.IZBRISAN;
				else if (splitovano[6].equals("BLOKIRAN")) aktivnost = AktivnostKorisnika.BLOKIRAN;
				
				LocalDate datum = LocalDate.parse(splitovano[5]);
				double bodovi = Double.parseDouble(splitovano[8]); 
				
				Kupac kupac = new Kupac(splitovano[2], splitovano[4], splitovano[0], splitovano[1], 
						pol, datum, aktivnost, bodovi, new ArrayList<>(), tip);
				kupci.put(kupac.getKorisnickoIme(), kupac);
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
