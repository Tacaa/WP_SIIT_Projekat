package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import klase.Karta;
import klase.Kupac;
import klase.Manifestacija;
import klase.StatusKarte;
import klase.TipKarte;

public class KartaDAO {
	
	public static ArrayList<Karta> karte = new ArrayList<>();
	
	public KartaDAO() {
		ucitajKarte();
	}
	
	public static void ucitajKarte() {
		if (ManifestacijaDAO.manifestacije.size() == 0) ManifestacijaDAO.ucitajManifestacije();
		if (KupacDAO.kupci.size() == 0) KupacDAO.ucitajKupce();
		
		ArrayList<Manifestacija> manifestacije = ManifestacijaDAO.manifestacije;
		ArrayList<Kupac> kupci = new ArrayList<>();
		for (Kupac k : KupacDAO.kupci.values()) kupci.add(k);
		
		Karta karta1 = new Karta("1234567891", manifestacije.get(1), kupci.get(0), 
				StatusKarte.REZERVISANA, TipKarte.REGULARNA);
		Karta karta2 = new Karta("1111111111", manifestacije.get(0), kupci.get(0), 
				StatusKarte.ODUSTANAK, TipKarte.VIP);
		Karta karta3 = new Karta("2222222222", manifestacije.get(4), kupci.get(1), 
				StatusKarte.REZERVISANA, TipKarte.FAN_PIT);
		Karta karta4 = new Karta("3131313131", manifestacije.get(0), kupci.get(1), 
				StatusKarte.ODUSTANAK, TipKarte.REGULARNA);
		Karta karta5 = new Karta("1515151515", manifestacije.get(3), kupci.get(2), 
				StatusKarte.REZERVISANA, TipKarte.REGULARNA);
		Karta karta6 = new Karta("1112224448", manifestacije.get(2), kupci.get(3), 
				StatusKarte.REZERVISANA, TipKarte.VIP);
		Karta karta7 = new Karta("aaa999aaa9", manifestacije.get(4), kupci.get(4), 
				StatusKarte.ODUSTANAK, TipKarte.VIP);
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

}
