package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import klase.Lokacija;
import klase.Manifestacija;
import klase.Prodavac;
import klase.StatusManifestacije;
import klase.TipManifestacije;

public class ManifestacijaDAO {
	
	public static ArrayList<Manifestacija> manifestacije = new ArrayList<>();
	private static String sledeciRed = System.lineSeparator();
	private static final String putanja = "C:\\Users\\Admin\\Desktop\\VezbeWeb\\Projekat\\WP_SIIT_Projekat\\src\\podaci\\manifestacije.csv";

	public ManifestacijaDAO() {}

	public static Manifestacija nadjiPoNazivuVremenu(String naziv, String vreme) {
		LocalDateTime datumVreme = LocalDateTime.parse(vreme);
		for (Manifestacija m : manifestacije) 
			if (m.getNaziv().equals(naziv) && m.getVreme().equals(datumVreme)) return m;
		return null;
	}
	
	public static boolean sacuvajManifestacije() {
		StringBuilder zaUpis = new StringBuilder();
		for (Manifestacija m : ManifestacijaDAO.manifestacije) zaUpis.append(m.zaCuvanje() + sledeciRed);
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
	
	public static boolean ucitajManifestacije() {
		ProdavacDAO.ucitajProdavce();
		if (!manifestacije.isEmpty()) return true;		// vec su ucitani
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(putanja)));
			String linija = br.readLine();
			while (!linija.equals("")) {
				// naziv,tip,brMesta,vreme,cena,status,geoDuzina,geoSirina,drzava,ulicaBroj,
				//grad,postanskiBroj,poster,prodavac
				String[] splitovano = linija.split(";");
				
				TipManifestacije tip = TipManifestacije.BIOSKOPSKA_PROJEKCIJA;
				if (splitovano[1].equals("Druga kategorija")) tip = TipManifestacije.DRUGA_KATEGORIJA;
				else if (splitovano[1].equals("Koncert")) tip = TipManifestacije.KONCERT;
				else if (splitovano[1].equals("Festival")) tip = TipManifestacije.FESTIVAL;
				else if (splitovano[1].equals("Pozorisna predstava")) tip = TipManifestacije.POZORISNA_PREDSTAVA;
				else if (splitovano[1].equals("Sportska nadmetanja")) tip = TipManifestacije.SPORTSKA_NADMETANJA;
				else if (splitovano[1].equals("Plesni nastup")) tip = TipManifestacije.PLESNI_NASTUP;
				else if (splitovano[1].equals("Standup nastup")) tip = TipManifestacije.STANDUP_NASTUP;
				else if (splitovano[1].equals("Nastup cirkusa")) tip = TipManifestacije.NASTUP_CIRKUSA;
				else if (splitovano[1].equals("Sajam")) tip = TipManifestacije.SAJAM;
				else if (splitovano[1].equals("Muzejsko vece")) tip = TipManifestacije.MUZEJSKO_VECE;
				else if (splitovano[1].equals("Knjizevno vece")) tip = TipManifestacije.KNJIZEVNO_VECE;
				
				int brMesta = Integer.parseInt(splitovano[2]);
				LocalDateTime vreme = LocalDateTime.parse(splitovano[3]);
				double cena = Double.parseDouble(splitovano[4]);
				
				StatusManifestacije status = StatusManifestacije.AKTIVNA;
				if (splitovano[5].equals("NA_CEKANJU")) status = StatusManifestacije.NA_CEKANJU;
				else if (splitovano[5].equals("ODBIJENA")) status = StatusManifestacije.ODBIJENA;
				
				double geoDuzina = Double.parseDouble(splitovano[6]);
				double geoSirina = Double.parseDouble(splitovano[7]);
				int postanskiBr = Integer.parseInt(splitovano[11]);
				
				Lokacija lokacija = null;
				for (Lokacija l : LokacijaDAO.lokacije) {
					if (l.getDrzava().equals(splitovano[8]) && l.getGrad().equals(splitovano[10]) 
							&& l.getUlicaBroj().equals(splitovano[9])) {
						lokacija = l;
						break;
					}
				}
				if (lokacija == null) {
					lokacija = new Lokacija(geoDuzina, geoSirina, splitovano[9], splitovano[10], 
							splitovano[8], postanskiBr);
					LokacijaDAO.lokacije.add(lokacija);
				}
				Manifestacija manifestacija = new Manifestacija(splitovano[0], tip, brMesta, vreme, cena, status,
						lokacija, splitovano[12], 0, new ArrayList<>(), 0, splitovano[13]);
				ManifestacijaDAO.manifestacije.add(manifestacija);
				
				Prodavac prodavac = ProdavacDAO.prodavci.get(manifestacija.getProdavac());
				prodavac.getManifestacije().add(manifestacija);
				
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
