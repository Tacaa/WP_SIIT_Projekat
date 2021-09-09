package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import klase.Komentar;
import klase.Kupac;
import klase.Manifestacija;
import klase.StatusKomentara;

public class KomentarDAO {

	public static ArrayList<Komentar> komentari = new ArrayList<>();
	private static String sledeciRed = System.lineSeparator();
	private static final String putanja = "C:\\Users\\Admin\\Desktop\\VezbeWeb\\Projekat\\WP_SIIT_Projekat\\src\\podaci\\komentari.csv";
	
	public KomentarDAO() {}
	public static boolean sacuvajKomentare() {
		StringBuilder zaUpis = new StringBuilder();
		for (Komentar k : komentari) zaUpis.append(k.zaCuvanje() + sledeciRed);
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
	
	public static boolean ucitajKomentare() {
		KartaDAO.ucitajKarte();
		if (!komentari.isEmpty()) return true;		// vec su ucitani
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(putanja)));
			String linija = br.readLine();
			while (!linija.equals("")) {
				// kImeKupca,nazivMan,vremeMan,tekst,ocena,status
				String[] splitovano = linija.split(";");

				Kupac kupac = KupacDAO.kupci.get(splitovano[0]);
				Manifestacija manifestacija = ManifestacijaDAO.nadjiPoNazivuVremenu(splitovano[1],
						splitovano[2]);
				int ocena = Integer.parseInt(splitovano[4]);
				
				StatusKomentara status = StatusKomentara.NA_CEKANJU;
				if (splitovano[5].equals("PRIHVACEN")) {
					status = StatusKomentara.PRIHVACEN;
					
					int suma = ocena;					// da odmah izracunamo ocenu
					for (Komentar kom : manifestacija.getKomentari()) {
						suma += kom.getOcena();
					}
					manifestacija.setOcena(suma/(manifestacija.getKomentari().size() + 1));
				}
				else if (splitovano[5].equals("ODBIJEN")) status = StatusKomentara.ODBIJEN;
				
				Komentar komentar = new Komentar(kupac, manifestacija, splitovano[3], ocena, status);
				komentari.add(komentar);
				
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
