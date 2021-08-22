package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import klase.Kupac;

public class KupacDAO {
	private static HashMap<String, Kupac> kupci = new  HashMap<String, Kupac>();
	
	public KupacDAO() {
		/*
		try {
			ucitajKupce();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		*/
	}
	
	
	public void ucitajKupce() throws FileNotFoundException {
		Gson gson = new Gson();
		Type token = new TypeToken<HashMap<String,Kupac>>(){}.getType();
		BufferedReader br = new BufferedReader(new FileReader("../WebContent/json/kupci.json")); //putanja
		this.kupci = gson.fromJson(br, token);
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
	
	
	public static Kupac dodajKupca(Kupac kupac) {
		if (zauzetoKorisnickoIme(kupac.getKorisnickoIme())) return null;
		int noviId = kupci.size();
		kupci.put(String.valueOf(noviId), kupac);
		
		/*try {
			this.upisiKupce();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return kupac;
	}
	
	
}
