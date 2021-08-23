package dao;

import java.time.LocalDate;
import java.util.HashMap;

import klase.Administrator;
import klase.AktivnostKorisnika;
import klase.Korisnik;
import klase.Kupac;
import klase.Pol;


public class AdministratorDAO {
	
	public static HashMap<String, Administrator> administratori = new  HashMap<String, Administrator>();
	
	
	public static void ucitajAdministratore() {
		//administratori
		Administrator milica = new Administrator("dumit", "dumit", "Milica", "Djumic", Pol.zena, LocalDate.of(1999, 10, 28), AktivnostKorisnika.AKTIVAN);
		Administrator tatjana = new Administrator("zevs", "zevs2207", "Tatjana", "Gavrilovic", Pol.zena, LocalDate.of(1999, 7, 22), AktivnostKorisnika.AKTIVAN);
		
		administratori.put(milica.getKorisnickoIme(), milica);
		administratori.put(tatjana.getKorisnickoIme(), tatjana);
	}
	

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
		/*try {
			this.upisiKupce();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return izabrani;
	}
	
	
	
}
