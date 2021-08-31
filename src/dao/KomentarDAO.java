package dao;

import java.util.ArrayList;

import klase.Komentar;

public class KomentarDAO {

	public static ArrayList<Komentar> komentari = new ArrayList<>();
	
	public KomentarDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static void ucitajKomentare() {
		if (KartaDAO.karte.size() == 0) KartaDAO.ucitajKarte();
		
		
	}
	
}
