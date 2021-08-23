package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import klase.AktivnostKorisnika;
import klase.ImeTipaKupca;
import klase.Karta;
import klase.Korisnik;
import klase.Kupac;
import klase.Pol;
import klase.TipKupca;

public class KupacDAO {
	public static HashMap<String, Kupac> kupci = new  HashMap<String, Kupac>();
	
	public KupacDAO() {
		/*
		try {
			ucitajKupce();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		*/
	}
	
	
	//kad se bude fajl dodavao staviti throws FileNotFoundException 
	public static void ucitajKupce(){
		/*
		Gson gson = new Gson();
		Type token = new TypeToken<HashMap<String,Kupac>>(){}.getType();
		BufferedReader br = new BufferedReader(new FileReader("../WebContent/json/kupci.json")); //putanja
		this.kupci = gson.fromJson(br, token);
		*/
		
		TipKupca tipKupca = new TipKupca(ImeTipaKupca.NEMA, 0, 0);
		Kupac kupac1 = new Kupac("pera", "pera", "Pera", "Peric", Pol.muskarac, LocalDate.of(2000, 1, 12), AktivnostKorisnika.AKTIVAN, 0, new ArrayList<Karta>(), tipKupca);
		Kupac kupac2 = new Kupac("mika", "mika", "Mika", "Mikic", Pol.muskarac, LocalDate.of(1998, 4, 13), AktivnostKorisnika.AKTIVAN, 0, new ArrayList<Karta>(), tipKupca);
		Kupac kupac3 = new Kupac("ana", "ana", "Ana", "Anic", Pol.zena, LocalDate.of(1999, 6, 6), AktivnostKorisnika.AKTIVAN, 0, new ArrayList<Karta>(), tipKupca);
		Kupac kupac4 = new Kupac("ema", "ema", "Ema", "Emic", Pol.zena, LocalDate.of(2000, 9, 10), AktivnostKorisnika.AKTIVAN, 0, new ArrayList<Karta>(), tipKupca);
		Kupac kupac5 = new Kupac("ilma", "ilma", "Ilma", "Ilmic", Pol.zena, LocalDate.of(2001, 3, 19), AktivnostKorisnika.AKTIVAN, 0, new ArrayList<Karta>(), tipKupca);
		
		kupci.put(kupac1.getKorisnickoIme(), kupac1);
		kupci.put(kupac2.getKorisnickoIme(), kupac2);
		kupci.put(kupac3.getKorisnickoIme(), kupac3);
		kupci.put(kupac4.getKorisnickoIme(), kupac4);
		kupci.put(kupac5.getKorisnickoIme(), kupac5);
		
	}
	
	
	
	public void upisiKupce() throws IOException{
		Gson gson = new Gson();
		FileWriter fw = new FileWriter("../WebContent/json/kupci.json"); //putanja
		gson.toJson(this.kupci, fw);
		fw.flush();
		fw.close();
	}
	
	private static boolean zauzetoKorisnickoIme(String novoKorIme) {
		for (Kupac k : kupci.values()) if (k.getKorisnickoIme().equals(novoKorIme)) return true;
		return false;
	}

	private static Kupac getKupca(String korisnickoIme) {
		for (Kupac k : kupci.values()) if (k.getKorisnickoIme().equals(korisnickoIme)) return k;
		return null;
	}
	
	
	public static Kupac dodajKupca(Kupac kupac) {
		if (zauzetoKorisnickoIme(kupac.getKorisnickoIme())) return null;
		
		kupci.put(kupac.getKorisnickoIme(), kupac);
		
		/*try {
			this.upisiKupce();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return kupac;
	}

	public static Kupac izmeniKupca(Korisnik kupac) {
		Kupac izabrani = getKupca(kupac.getKorisnickoIme());
		if (izabrani == null) return null;
		izabrani.setIme(kupac.getIme());
		izabrani.setPrezime(kupac.getPrezime());
		izabrani.setPol(kupac.getPol());
		izabrani.setDatumRodjenja(kupac.getDatumRodjenja());
		izabrani.setLozinka(kupac.getLozinka());
		/*try {
			this.upisiKupce();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return izabrani;
	}
	
	
}
