package klase;

import java.util.ArrayList;

public class Kupac extends Korisnik {
	private double brojBodova;
	private ArrayList<Karta> sveKarte;
	private TipKupca tip;
	
	public double getBrojBodova() {
		return brojBodova;
	}
	public void setBrojBodova(double brojBodova) {
		this.brojBodova = brojBodova;
	}
	
	public ArrayList<Karta> getSveKarte() {
		return sveKarte;
	}
	public void setSveKarte(ArrayList<Karta> sveKarte) {
		this.sveKarte = sveKarte;
	}
	
	public TipKupca getTip() {
		return tip;
	}
	public void setTip(TipKupca tip) {
		this.tip = tip;
	}
	
	public Kupac() {
		super();
	}
	
	public Kupac(double brojBodova, ArrayList<Karta> sveKarte, TipKupca tip) {
		super();
		this.brojBodova = brojBodova;
		this.sveKarte = sveKarte;
		this.tip = tip;
	}
	
	@Override
	public String toString() {
		return "Kupac [brojBodova=" + brojBodova + ", sveKarte=" + sveKarte + ", tip=" + tip + "]";
	}
	
}
