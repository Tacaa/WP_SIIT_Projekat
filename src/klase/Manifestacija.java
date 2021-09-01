package klase;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Manifestacija {
	private String naziv;
	private TipManifestacije tip;
	private int brojMesta;
	private LocalDateTime vreme;
	private double cena;				// osnovna cena
	private StatusManifestacije status;
	private Lokacija lokacija;
	private String poster;
	
	private int brojRezervisanihMesta;
	private ArrayList<Komentar> komentari;
	private double ocena;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public TipManifestacije getTip() {
		return tip;
	}
	public void setTip(TipManifestacije tip) {
		this.tip = tip;
	}
	
	public int getBrojMesta() {
		return brojMesta;
	}
	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}
	
	public LocalDateTime getVreme() {
		return vreme;
	}
	public void setVreme(LocalDateTime vreme) {
		this.vreme = vreme;
	}
	
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public StatusManifestacije getStatus() {
		return status;
	}
	public void setStatus(StatusManifestacije status) {
		this.status = status;
	}
	
	public Lokacija getLokacija() {
		return lokacija;
	}
	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}
	
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public int getBrojRezervisanihMesta() {
		return brojRezervisanihMesta;
	}
	public void setBrojRezervisanihMesta(int brojRezervisanihMesta) {
		this.brojRezervisanihMesta = brojRezervisanihMesta;
	}
	
	public ArrayList<Komentar> getKomentari() {
		return komentari;
	}
	public void setKomentari(ArrayList<Komentar> komentari) {
		this.komentari = komentari;
	}
	
	public double getOcena() {
		return ocena;
	}
	public void setOcena(double ocena) {
		this.ocena = ocena;
	}
	
	public Manifestacija() {
		super();
	}
	
	public Manifestacija(String naziv, TipManifestacije tip, int brojMesta, LocalDateTime vreme, double cena,
			StatusManifestacije status, Lokacija lokacija, String poster) {
		super();
		this.naziv = naziv;
		this.tip = tip;
		this.brojMesta = brojMesta;
		this.vreme = vreme;
		this.cena = cena;
		this.status = status;
		this.lokacija = lokacija;
		this.poster = poster;
		this.brojRezervisanihMesta = 0;
		this.komentari = new ArrayList<>();
		this.ocena = 0;
	}
	
	public Manifestacija(String naziv, TipManifestacije tip, int brojMesta, LocalDateTime vreme, double cena,
			StatusManifestacije status, Lokacija lokacija, String poster, int brojRezervisanihMesta,
			ArrayList<Komentar> komentari, double ocena) {
		super();
		this.naziv = naziv;
		this.tip = tip;
		this.brojMesta = brojMesta;
		this.vreme = vreme;
		this.cena = cena;
		this.status = status;
		this.lokacija = lokacija;
		this.poster = poster;
		this.brojRezervisanihMesta = brojRezervisanihMesta;
		this.komentari = komentari;
		this.ocena = ocena;
	}
	
	@Override
	public String toString() {
		return "{\"naziv\": \"" + this.naziv + "\", \"tip\": \""+ this.tip + "\", \"brojMesta\": \"" + 
				this.brojMesta + "\", \"vreme\": \""+ this.vreme + "\", \"cena\": \""+ this.cena + 
				"\", \"status\": \""+ this.status + "\", \"lokacija\": "+ this.lokacija + 
				", \"poster\": \""+ this.poster + "\", \"ocena\": \""+ this.ocena +
				"\", \"brojRezervisanihMesta\": \""+ this.brojRezervisanihMesta + 
				"\", \"komentari\": "+ this.komentari +"}";
	}
	
	
}
