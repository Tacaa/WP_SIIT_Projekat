package dao;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import klase.Lokacija;
import klase.Manifestacija;
import klase.StatusManifestacije;
import klase.TipManifestacije;
import klase.Komentar;

public class ManifestacijaDAO {
	
	public static ArrayList<Manifestacija> manifestacije = new ArrayList<>();
	

	public ManifestacijaDAO() {
	}
	
	public static void ucitajManifestacije(){
		if (manifestacije.size() != 0) return;
		LokacijaDAO.ucitajLokacije();
		
				// prosle manifestacije
		Manifestacija m1 = new Manifestacija("Makarena ponovo bruji", TipManifestacije.PLESNI_NASTUP, 
				52, LocalDateTime.now().minusDays(12), 240, StatusManifestacije.AKTIVNA, 
				LokacijaDAO.lokacije.get(0), "ples.png", 32, new ArrayList<>(), 4.7);

		Manifestacija m2 = new Manifestacija("Kuvari u akciji", TipManifestacije.DRUGA_KATEGORIJA, 
				34, LocalDateTime.now().minusDays(3), 222, StatusManifestacije.AKTIVNA, 
				LokacijaDAO.lokacije.get(1), "drugo.jpg", 22, new ArrayList<>(), 3.4);

				// buduce manifestacije
		Manifestacija m3 = new Manifestacija("Zenska odbojka", TipManifestacije.SPORTSKA_NADMETANJA, 
				66, LocalDateTime.now().plusDays(12), 324, StatusManifestacije.AKTIVNA, 
				LokacijaDAO.lokacije.get(3), "utakmica.jpg", 22, new ArrayList<>(), 0);

		Manifestacija m4 = new Manifestacija("Dragana Kostic nastupa", TipManifestacije.STANDUP_NASTUP, 
				47, LocalDateTime.now().plusDays(7), 127, StatusManifestacije.AKTIVNA, 
				LokacijaDAO.lokacije.get(4), "standup.jpg", 17, new ArrayList<>(), 0);


		Manifestacija m5 = new Manifestacija("Mawka", TipManifestacije.BIOSKOPSKA_PROJEKCIJA, 
				122, LocalDateTime.now().plusDays(44), 345, StatusManifestacije.AKTIVNA, 
				LokacijaDAO.lokacije.get(2), "kino.jpg", 31, new ArrayList<>(), 0);
		
		
				// Tacine manifestacije
		Manifestacija m6 = new Manifestacija("Koncert Nine Badric", TipManifestacije.KONCERT, 
				5000, LocalDateTime.of(2021, 11, 12, 20, 0), 3000, StatusManifestacije.AKTIVNA, 
				new Lokacija(45.2889, 45.2889, "SPENS, Sutjeska 2", "Novi Sad", "Srbija", 21000), 
				"koncert.png", 0, new ArrayList<Komentar>(), 0);
		
		Manifestacija m7 = new Manifestacija("Koncert Zdravka Colica", TipManifestacije.KONCERT, 
				10000, LocalDateTime.of(2021, 9, 25, 20, 0), 5000, StatusManifestacije.AKTIVNA, 
				new Lokacija(45.2889, 45.2889, "SPENS, Sutjeska 2", "Novi Sad", "Srbija", 21000), 
				"koncert.png", 0, new ArrayList<Komentar>(), 0);
		
		Manifestacija m8 = new Manifestacija("Knjizevno vece sa Neletom Karajlicem - 'Fajront u Sarajevu'", 
				TipManifestacije.KNJIZEVNO_VECE, 50, LocalDateTime.of(2021, 10, 10, 18, 0), 230, 
				StatusManifestacije.AKTIVNA, new Lokacija(45.2889, 45.2889, "Matice srpske 1", 
				"Novi Sad", "Srbija", 21000), "knjizevno_vece.png", 0, new ArrayList<Komentar>(), 0);
		
		Manifestacija m9 = new Manifestacija("Odbojka Srbija - Poljska", TipManifestacije.SPORTSKA_NADMETANJA, 
				10000, LocalDateTime.of(2021, 9, 15, 20, 0), 500, StatusManifestacije.AKTIVNA, 
				new Lokacija(44.8125, 20.4612, "Stark Arena, Bulevar Arsenija Čarnojevica 58,", 
				"Beograd", "Srbija", 10401), "utakmica.jpg", 0, new ArrayList<Komentar>(), 0);
		
		Manifestacija m10 = new Manifestacija("Smijeh lijeci sve", TipManifestacije.STANDUP_NASTUP, 
				50, LocalDateTime.of(2021, 9, 24, 21, 0), 00, StatusManifestacije.AKTIVNA, 
				new Lokacija(44.8125, 20.4612, "Braće Krsmanovic 6", "Beograd", "Srbija", 21000),
				"standup.jpg", 0, new ArrayList<Komentar>(), 0);
		
		Manifestacija m11 = new Manifestacija("Sajam knjiga u Beogradu", TipManifestacije.SAJAM, 
				100000, LocalDateTime.of(2021, 9, 19, 20, 0), 370, StatusManifestacije.AKTIVNA, 
				new Lokacija(44.8125, 20.4612, "", "Beograd", "Srbija", 21000), "sajam.jpg", 0, 
				new ArrayList<Komentar>(), 0);
		
		Manifestacija m12 = new Manifestacija("ARSENAL", TipManifestacije.FESTIVAL, 30000, 
				LocalDateTime.of(2022, 6, 23, 20, 0), 000, StatusManifestacije.AKTIVNA, 
				new Lokacija(45.2889, 45.2889, "", "Kragujevac", "Srbija", 34000), "koncert.png",
				0, new ArrayList<Komentar>(), 0);
		
		
		manifestacije.add(m1);
		manifestacije.add(m2);
		manifestacije.add(m3);
		manifestacije.add(m4);
		manifestacije.add(m5);
		
		manifestacije.add(m6);
		manifestacije.add(m7);
		manifestacije.add(m8);
		manifestacije.add(m9);
		manifestacije.add(m10);
		
		manifestacije.add(m11);
		manifestacije.add(m12);
	}
	
	public static Manifestacija nadjiPoNazivuVremenu(String naziv, String vreme) {
		LocalDateTime datumVreme = LocalDateTime.parse(vreme);
		for (Manifestacija m : manifestacije) 
			if (m.getNaziv().equals(naziv) && m.getVreme().equals(datumVreme)) return m;
		return null;
	}
}
