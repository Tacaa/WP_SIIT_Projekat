package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import klase.Karta;
import klase.Kupac;
import klase.Manifestacija;
import klase.StatusKarte;
import klase.TipKarte;

public class KartaDAO {
	
	public static ArrayList<Karta> karte = new ArrayList<>();
	private static String sledeciRed = System.lineSeparator();
	private static final String putanja = "C:\\Users\\Admin\\Desktop\\VezbeWeb\\Projekat\\WP_SIIT_Projekat\\src\\podaci\\karte.csv";
	
	public KartaDAO() {}
	
	private static Karta nadjiPoId(String id) {
		ucitajKarte();
		for (Karta k : karte) if (k.getId().equals(id)) return k;
		return null;
	} 

	public static Karta otkaziKartu(String id) {
		Karta kartica = nadjiPoId(id);
		if (kartica == null) return null;
		kartica.setStatus(StatusKarte.OBUSTAVLJENA);
		sacuvajKarte();
		return kartica;
	}
	
	public static String generisiId() {
		boolean provera = true;
		String povratnaVr = "";
	    while (provera) {
	    	int slovoA = 97; 				// od slova 'a'
		    int slovoZ = 122; 				// do slova 'z'
		    int duzina = 10;				// duzina mora biti 10
		    Random random = new Random();
		    StringBuilder zaString = new StringBuilder(duzina);
		    for (int i = 0; i < duzina; i++) {
		        int randomBroj = slovoA + (int) (random.nextFloat() * (slovoZ - slovoA + 1));
		        zaString.append((char) randomBroj);
		    }
		    povratnaVr = zaString.toString();
		    provera = nadjiPoId(povratnaVr) != null;		// true da bi nastavilo, kada je zauzeto ime
	    }
	    return povratnaVr;
	}
	
	public static boolean sacuvajKarte() {
		StringBuilder zaUpis = new StringBuilder();
		for (Karta k : karte) zaUpis.append(k.zaCuvanje() + sledeciRed);
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
	
	public static boolean ucitajKarte() {
		KupacDAO.ucitajKupce();
		ManifestacijaDAO.ucitajManifestacije();
		if (!karte.isEmpty()) return true;		// vec su ucitani
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(putanja)));
			String linija = br.readLine();
			while (!linija.equals("")) {
				// id,nazivMan,vremeMan,kImeKupca,status,tip
				String[] splitovano = linija.split(";");
				
				Manifestacija manifestacija = ManifestacijaDAO.nadjiPoNazivuVremenu(splitovano[1],
						splitovano[2]);
				Kupac kupac = KupacDAO.kupci.get(splitovano[3]);
				
				StatusKarte status = StatusKarte.REZERVISANA;
				if (splitovano[4].equals("OBUSTAVLJENA")) status = StatusKarte.OBUSTAVLJENA;
				
				TipKarte tip = TipKarte.REGULARNA;
				if (splitovano[5].equals("VIP")) tip = TipKarte.VIP;
				if (splitovano[5].equals("FAN_PIT")) tip = TipKarte.FAN_PIT;
				
				Karta karta = new Karta(splitovano[0], manifestacija, kupac, status, tip);
				karte.add(karta);
				
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
