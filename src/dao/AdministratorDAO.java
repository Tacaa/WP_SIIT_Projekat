package dao;

import java.time.LocalDate;
import java.util.HashMap;

import klase.Administrator;
import klase.AktivnostKorisnika;
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
	
	
	
	
	
}
