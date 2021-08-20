package klase;

public class Karta {
	private int id;
	private Manifestacija manifestacija;
	private Kupac kupac;
	private StatusKarte status;
	private TipKarte tip;
	private double konacnaCena;		// u zavisnosti na ImeTipaKupca(x1/0.97/0.95) i TipKarte (x1/2/4)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Manifestacija getManifestacija() {
		return manifestacija;
	}
	public void setManifestacija(Manifestacija manifestacija) {
		this.manifestacija = manifestacija;
	}
	
	public Kupac getKupac() {
		return kupac;
	}
	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}
	
	public StatusKarte getStatus() {
		return status;
	}
	public void setStatus(StatusKarte status) {
		this.status = status;
	}
	
	public TipKarte getTip() {
		return tip;
	}
	public void setTip(TipKarte tip) {
		this.tip = tip;
	}
	
	public double getKonacnaCena() {
		return konacnaCena;
	}
	public void setKonacnaCena(double konacnaCena) {
		this.konacnaCena = konacnaCena;
	}
	
	public Karta() {
		super();
	}
	
	public Karta(int id, Manifestacija manifestacija, Kupac kupac, StatusKarte status, TipKarte tip,
			double konacnaCena) {
		super();
		this.id = id;
		this.manifestacija = manifestacija;
		this.kupac = kupac;
		this.status = status;
		this.tip = tip;
		this.konacnaCena = konacnaCena;
	}

	@Override
	public String toString() {
		return "Karta [id=" + id + ", manifestacija=" + manifestacija + ", kupac=" + kupac + ", status=" + status
				+ ", tip=" + tip + ", konacnaCena=" + konacnaCena + "]";
	}

}
