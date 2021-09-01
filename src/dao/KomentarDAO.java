package dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import klase.Karta;
import klase.Komentar;
import klase.Manifestacija;
import klase.StatusKarte;
import klase.StatusKomentara;

public class KomentarDAO {

	public static ArrayList<Komentar> komentari = new ArrayList<>();
	
	public KomentarDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static void ucitajKomentare() {
		if (komentari.size() != 0) return;
		if (KartaDAO.karte.size() == 0) KartaDAO.ucitajKarte();
		
		// provereni komentari
		Karta peraKuvar = KartaDAO.karte.get(0);
		Komentar prihvacenKom = new Komentar(peraKuvar.getKupac(), peraKuvar.getManifestacija(), 
				"Obozavam kuvanje", 5, StatusKomentara.PRIHVACEN);
		Komentar odbijenKom = new Komentar(peraKuvar.getKupac(), peraKuvar.getManifestacija(), 
				"Devojke vole kuvare ;)", 5, StatusKomentara.ODBIJEN);
		Komentar naCekanjuKom = new Komentar(peraKuvar.getKupac(), peraKuvar.getManifestacija(), 
				"Kuvam od 7. godine", 5, StatusKomentara.NA_CEKANJU);
		peraKuvar.getManifestacija().setOcena(5);
		peraKuvar.getManifestacija().getKomentari().add(prihvacenKom);
		peraKuvar.getManifestacija().getKomentari().add(odbijenKom);
		peraKuvar.getManifestacija().getKomentari().add(naCekanjuKom);
		
		// nasumicni komentari
		Random zaRacunanje = new Random();
		for (Karta k : KartaDAO.karte) {
			if (k.getStatus() == StatusKarte.OBUSTAVLJENA) continue;
			if (k.getManifestacija().getVreme().isBefore(LocalDateTime.now())) {
				Manifestacija manifestacija = k.getManifestacija();
				int ocena = zaRacunanje.nextInt() % 5 + 1;		// ocena od 1 do 5
				int zaStatus = zaRacunanje.nextInt() % 3;		// ocena od 0 do 2
				StatusKomentara status = StatusKomentara.NA_CEKANJU;
				if (zaStatus == 1) {
					status = StatusKomentara.PRIHVACEN;
					
					// odmah izracunam ocenu da ne radim if opet
					int suma = ocena;
					for (Komentar kom : manifestacija.getKomentari()) {
						suma += kom.getOcena();
					}
					manifestacija.setOcena(suma/(manifestacija.getKomentari().size() + 1));
				}
				else if (zaStatus == 2) status = StatusKomentara.ODBIJEN;
				Komentar komentar = new Komentar(k.getKupac(), manifestacija, "Sve je bilo super",
						ocena, status);
				
				manifestacija.getKomentari().add(komentar);
			}
		}
	}
	
}
