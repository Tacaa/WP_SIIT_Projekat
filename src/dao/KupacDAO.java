package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import klase.Kupac;

public class KupacDAO {
	private HashMap<String, Kupac> kupci;
	
	public KupacDAO() {
		kupci = new  HashMap<String, Kupac>();
		
		try {
			ucitajKupce();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
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
	
	
	public Kupac dodajKupca(Kupac kupac) {
		System.out.println("TACA");
		System.out.println("Uslo u dodajKupca, sad treba upisati!");
		int noviId = kupci.size();
		kupci.put(String.valueOf(noviId), kupac);
		
		try {
			this.upisiKupce();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kupac;
	}
	
	
}
