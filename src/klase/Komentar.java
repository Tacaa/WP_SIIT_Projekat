package klase;

public class Komentar {
	private Kupac kupac;
	private Manifestacija manifestacija;
	private String tekst;
	private int ocena;						// od 1 do 5
	private StatusKomentara status;
	
	public Kupac getKupac() {
		return kupac;
	}
	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}
	
	public Manifestacija getManifestacija() {
		return manifestacija;
	}
	public void setManifestacija(Manifestacija manifestacija) {
		this.manifestacija = manifestacija;
	}
	
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	public StatusKomentara getStatus() {
		return status;
	}
	public void setStatus(StatusKomentara status) {
		this.status = status;
	}
	
	public Komentar() {
		super();
	}
	
	public Komentar(Kupac kupac, Manifestacija manifestacija, String tekst, int ocena, StatusKomentara status) {
		super();
		this.kupac = kupac;
		this.manifestacija = manifestacija;
		this.tekst = tekst;
		this.ocena = ocena;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "{\"kupca\": \""+ this.kupac + "\", \"manifestacija\": \""+ this.manifestacija 
				+ "\", \"tekst\": \""+ this.tekst + "\", \"ocena\": \""+ this.ocena
				+ "\", \"status\": \""+ this.status +"\"}";
	}
	
	
}
