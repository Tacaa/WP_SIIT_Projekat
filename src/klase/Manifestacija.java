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
	private ArrayList<Karta> karte;
	private double ocena;
	
	private String prodavac;
	
	private String stariNaziv;
	private LocalDateTime staroVrijeme;
	private String adresa;
	private String drzava;
	private String grad;
	
	
	
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
	
	public ArrayList<Karta> getKarte() {
		return karte;
	}
	public void setKarte(ArrayList<Karta> karte) {
		this.karte = karte;
	}

	public String getProdavac() {
		return prodavac;
	}
	public void setProdavac(String prodavac) {
		this.prodavac = prodavac;
	}
	

	
	public String getStariNaziv() {
		return stariNaziv;
	}
	public void setStariNaziv(String stariNaziv) {
		this.stariNaziv = stariNaziv;
	}
	public LocalDateTime getStaroVrijeme() {
		return staroVrijeme;
	}
	public void setStaroVrijeme(LocalDateTime staroVrijeme) {
		this.staroVrijeme = staroVrijeme;
	}
	
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	
  
  
	public Manifestacija() {
		super();
	}
	
	public Manifestacija(String naziv, TipManifestacije tip, int brojMesta, LocalDateTime vreme, double cena,
			StatusManifestacije status, Lokacija lokacija, String poster, String prodavac) {
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
		this.karte = new ArrayList<>();
		this.ocena = 0;
		this.prodavac = prodavac;
	}
	
	public Manifestacija(String naziv, TipManifestacije tip, int brojMesta, LocalDateTime vreme, double cena,
			StatusManifestacije status, Lokacija lokacija, String poster, int brojRezervisanihMesta,
			ArrayList<Komentar> komentari, double ocena, String prodavac) {
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
		this.karte = new ArrayList<>();
		this.ocena = ocena;
		this.prodavac = prodavac;
		
	}
	
	
	

	public Manifestacija(String naziv, TipManifestacije tip, int brojMesta, LocalDateTime vreme, double cena,
			String adresa, String grad, String drzava, String poster, String prodavac) {
		super();
		this.naziv = naziv;
		this.tip = tip;
		this.brojMesta = brojMesta;
		this.vreme = vreme;
		this.cena = cena;
		this.poster = poster;
		this.prodavac = prodavac;
		this.lokacija = new Lokacija(0, 0, adresa, grad, drzava, 0);
		
	}
	
	
	
	public Manifestacija(String stariNaziv, LocalDateTime staroVrijeme, String naziv, TipManifestacije tip, int brojMesta, LocalDateTime vreme, double cena,
			String adresa, String grad, String drzava, String poster, String prodavac) {
		super();
		this.stariNaziv = stariNaziv;
		this.staroVrijeme = staroVrijeme;
		this.naziv = naziv;
		this.tip = tip;
		this.brojMesta = brojMesta;
		this.vreme = vreme;
		this.cena = cena;
		this.poster = poster;
		this.prodavac = prodavac;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder zaKarte = new StringBuilder();
		zaKarte.append("[");
		for (Karta k : this.karte) zaKarte.append(k.jednostavanString() + ",");
		if (this.karte.size() != 0) zaKarte.deleteCharAt(zaKarte.length() - 1);
		zaKarte.append("]");
		return "{\"naziv\": \"" + this.naziv + "\", \"tip\": \""+ this.tip + "\", \"brojMesta\": \"" + 
				this.brojMesta + "\", \"vreme\": \""+ this.vreme + "\", \"cena\": \""+ this.cena + 
				"\", \"status\": \""+ this.status + "\", \"lokacija\": "+ this.lokacija + 
				", \"poster\": \""+ this.poster + "\", \"ocena\": \""+ this.ocena +
				"\", \"brojRezervisanihMesta\": \""+ this.brojRezervisanihMesta + 
				"\", \"komentari\": "+ this.komentari + ", \"karte\": "+ zaKarte.toString() +
        ", \"prodavac\": \""+ this.prodavac + "\"}";
	}
	
	
	
}
