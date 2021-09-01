package dao;

import java.util.ArrayList;

import klase.Lokacija;

public class LokacijaDAO {
	
	public static ArrayList<Lokacija> lokacije = new ArrayList();
	
	public LokacijaDAO() {}

	public static void ucitajLokacije() {
		Lokacija lokacija1 = new Lokacija(44.4, 77.7, "Zmaj Jovina 17", "Novi Sad", "Srbija", 21000);
		Lokacija lokacija2 = new Lokacija(44.4, 77.7, "Knez Mihajlova 17", "Beograd", "Srbija", 11000);
		Lokacija lokacija3 = new Lokacija(44.4, 77.7, "Dinavska 27", "Novi Sad", "Srbija", 21000);
		Lokacija lokacija4 = new Lokacija(44.4, 77.7, "Bulevar Oslobodjenja 47", "Novi Sad", "Srbija", 21000);
		Lokacija lokacija5 = new Lokacija(44.4, 77.7, "Ulica Modene 37", "Novi Sad", "Srbija", 21000);
		
		lokacije.add(lokacija1);
		lokacije.add(lokacija2);
		lokacije.add(lokacija3);
		lokacije.add(lokacija4);
		lokacije.add(lokacija5);
	}
}
