package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import klase.Karta;
import klase.Kupac;
import klase.Manifestacija;
import klase.StatusKarte;
import klase.TipKarte;

public class KartaDAO {
	
	public static ArrayList<Karta> karte = new ArrayList<>();
	
	public KartaDAO() {
	}
	
	public static void ucitajKarte() {
		if (karte.size() != 0) return; 
		ManifestacijaDAO.ucitajManifestacije();
		KupacDAO.ucitajKupce();
		
		ArrayList<Manifestacija> manifestacije = ManifestacijaDAO.manifestacije;
		ArrayList<Kupac> kupci = new ArrayList<>();
		for (Kupac k : KupacDAO.kupci.values()) kupci.add(k);
		
		Karta karta1 = new Karta("1234567891", manifestacije.get(1), kupci.get(0), 
				StatusKarte.REZERVISANA, TipKarte.REGULARNA);
		Karta karta2 = new Karta("1111111111", manifestacije.get(0), kupci.get(0), 
				StatusKarte.OBUSTAVLJENA, TipKarte.VIP);
		Karta karta3 = new Karta("2222222222", manifestacije.get(4), kupci.get(1), 
				StatusKarte.REZERVISANA, TipKarte.FAN_PIT);
		Karta karta4 = new Karta("3131313131", manifestacije.get(0), kupci.get(1), 
				StatusKarte.OBUSTAVLJENA, TipKarte.REGULARNA);
		Karta karta5 = new Karta("1515151515", manifestacije.get(3), kupci.get(2), 
				StatusKarte.REZERVISANA, TipKarte.REGULARNA);
		Karta karta6 = new Karta("1112224448", manifestacije.get(2), kupci.get(3), 
				StatusKarte.REZERVISANA, TipKarte.VIP);
		Karta karta7 = new Karta("aaa999aaa9", manifestacije.get(4), kupci.get(4), 
				StatusKarte.OBUSTAVLJENA, TipKarte.VIP);
		Karta karta8 = new Karta("q5q5q5q5q5", manifestacije.get(3), kupci.get(0), 
				StatusKarte.REZERVISANA, TipKarte.FAN_PIT);
		Karta karta9 = new Karta("9999988888", manifestacije.get(2), kupci.get(0), 
				StatusKarte.REZERVISANA, TipKarte.FAN_PIT);
		
		karte.add(karta1);	
		karte.add(karta2);	
		karte.add(karta3);	
		karte.add(karta4);	
		karte.add(karta5);	
		karte.add(karta6);	
		karte.add(karta7);	
		karte.add(karta8);	
		karte.add(karta9);
	}
	
	private static Karta nadjiPoId(String id) {
		ucitajKarte();
		for (Karta k : karte) if (k.getId().equals(id)) return k;
		return null;
	} 

	public static Karta otkaziKartu(String id) {
		Karta kartica = nadjiPoId(id);
		if (kartica == null) return null;
		kartica.setStatus(StatusKarte.OBUSTAVLJENA);
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
}
