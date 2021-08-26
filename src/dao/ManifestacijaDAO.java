package dao;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import klase.Manifestacija;
import klase.StatusManifestacije;
import klase.TipManifestacije;

public class ManifestacijaDAO {
	
	public static ArrayList<Manifestacija> manifestacije = new ArrayList<>();
	

	public ManifestacijaDAO() {
		ucitajManifestacije();
	}
	
	public static void ucitajManifestacije(){
		if (LokacijaDAO.lokacije.size() == 0) {LokacijaDAO.ucitajLokacije();}
				// prosle manifestacije
		Manifestacija m1 = new Manifestacija("Makarena ponovo bruji", TipManifestacije.PLESNI_NASTUP, 
				52, LocalDateTime.now().minusDays(12), 240, StatusManifestacije.AKTIVNA, 
				LokacijaDAO.lokacije.get(0), "nema_linka.jpg", 32, new ArrayList<>(), 4.7);

		Manifestacija m2 = new Manifestacija("Kuvari u akciji", TipManifestacije.DRUGA_KATEGORIJA, 
				34, LocalDateTime.now().minusDays(3), 222, StatusManifestacije.AKTIVNA, 
				LokacijaDAO.lokacije.get(1), "nema_linka.jpg", 22, new ArrayList<>(), 3.4);

				// buduce manifestacije
		Manifestacija m3 = new Manifestacija("Zenska odbojka", TipManifestacije.SPORTSKA_NADMETANJA, 
				66, LocalDateTime.now().plusDays(12), 324, StatusManifestacije.AKTIVNA, 
				LokacijaDAO.lokacije.get(3), "nema_linka.jpg", 22, new ArrayList<>(), 0);

		Manifestacija m4 = new Manifestacija("Dragana Kostic nastupa", TipManifestacije.STANDUP_NASTUP, 
				47, LocalDateTime.now().plusDays(7), 127, StatusManifestacije.AKTIVNA, 
				LokacijaDAO.lokacije.get(4), "nema_linka.jpg", 17, new ArrayList<>(), 0);


		Manifestacija m5 = new Manifestacija("Mawka", TipManifestacije.BIOSKOPSKA_PROJEKCIJA, 
				122, LocalDateTime.now().plusDays(44), 345, StatusManifestacije.AKTIVNA, 
				LokacijaDAO.lokacije.get(2), "nema_linka.jpg", 31, new ArrayList<>(), 0);
		
		manifestacije.add(m1);
		manifestacije.add(m2);
		manifestacije.add(m3);
		manifestacije.add(m4);
		manifestacije.add(m5);
	}
}
