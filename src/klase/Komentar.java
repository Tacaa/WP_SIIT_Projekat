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
		
		// moze ili da prihvati ili da odbije
		if (this.status == StatusKomentara.PRIHVACEN) {
			int suma = this.ocena;
			for (Komentar kom : this.manifestacija.getKomentari()) {
				suma += kom.getOcena();
			}
			this.manifestacija.setOcena(suma/(this.manifestacija.getKomentari().size()));
		}
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
		
		this.manifestacija.getKomentari().add(this);
	}
	
	@Override
	public String toString() {
		return "{\"kupac\": \""+ this.kupac.getKorisnickoIme() + "\", \"manifestacija\": \""
				+ this.manifestacija.getNaziv() + "\", \"tekst\": \""+ this.tekst + "\", \"ocena\": \""
				+ this.ocena + "\", \"status\": \""+ this.status +"\"}";
	}
	
	
}
