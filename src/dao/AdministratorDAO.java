package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;

import klase.Administrator;
import klase.AktivnostKorisnika;
import klase.Korisnik;
import klase.Pol;


public class AdministratorDAO {
	
	public static HashMap<String, Administrator> administratori = new  HashMap<String, Administrator>();
	private static String sledeciRed = System.lineSeparator();
	private static final String putanja = "C:\\Users\\Admin\\Desktop\\VezbeWeb\\Projekat\\WP_SIIT_Projekat\\src\\podaci\\administratori.csv";

	public AdministratorDAO() {}
	
	private static Administrator getAdmina(String korisnickoIme) {
		for (Administrator a : administratori.values()) if (a.getKorisnickoIme().equals(korisnickoIme)) return a;
		return null;
	}
	
	public static Administrator izmeniAdmina(Korisnik admin) {
		Administrator izabrani = getAdmina(admin.getKorisnickoIme());
		if (izabrani == null) return null;
		izabrani.setIme(admin.getIme());
		izabrani.setPrezime(admin.getPrezime());
		izabrani.setPol(admin.getPol());
		izabrani.setDatumRodjenja(admin.getDatumRodjenja());
		izabrani.setLozinka(admin.getLozinka());
		sacuvajAdministratore();
		return izabrani;
	}
	
	public static boolean sacuvajAdministratore() {
		StringBuilder zaUpis = new StringBuilder();
		for (Administrator a : administratori.values()) zaUpis.append(a.zaCuvanje() + sledeciRed);
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
	
	public static boolean ucitajAdministratore() {
		if (!administratori.isEmpty()) return true;		// vec su ucitani
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(putanja)));
			String linija = br.readLine();
			while (!linija.equals("")) {
				// ime,prezime,kIme,pol,lozinka,rodjendan,aktivnost
				String[] splitovano = linija.split(";");
				Pol pol = Pol.MUSKI;
				if (splitovano[3].equals("ZENSKI")) pol = Pol.ZENSKI;
				
				AktivnostKorisnika aktivnost = AktivnostKorisnika.AKTIVAN;
				if (splitovano[6].equals("IZBRISAN")) aktivnost = AktivnostKorisnika.IZBRISAN;
				else if (splitovano[6].equals("BLOKIRAN")) aktivnost = AktivnostKorisnika.BLOKIRAN;
				
				LocalDate datum = LocalDate.parse(splitovano[5]);
				
				Administrator admin = new Administrator(splitovano[2], splitovano[4], splitovano[0],
						splitovano[1], pol, datum, aktivnost);
				administratori.put(admin.getKorisnickoIme(), admin);
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
