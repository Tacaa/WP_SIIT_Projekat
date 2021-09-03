package klase;

public class Karta {
	private String id;
	private Manifestacija manifestacija;
	private Kupac kupac;
	private StatusKarte status;
	private TipKarte tip;
	private double konacnaCena;		// u zavisnosti na ImeTipaKupca(x1/0.9/0.8/0.7) i TipKarte (x1/2/4)
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
		
		// moze samo da otkaze
		double bodovi = this.kupac.getBrojBodova() - this.manifestacija.getCena()/1000 * 133 * 4; 
		this.kupac.setBrojBodova(bodovi >= 0 ? bodovi : 0);
		
		this.manifestacija.setBrojMesta(this.manifestacija.getBrojMesta() + 1);
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
	
	public Karta(String id, Manifestacija manifestacija, Kupac kupac, StatusKarte status, TipKarte tip) {
		super();
		this.id = id;
		this.manifestacija = manifestacija;
		this.kupac = kupac;
		this.status = status;
		this.tip = tip;
		
		if (this.tip == TipKarte.REGULARNA) this.konacnaCena = 
				this.manifestacija.getCena() * this.kupac.getTip().getPopust();
		else if (this.tip == TipKarte.FAN_PIT) this.konacnaCena = 
				2 * this.manifestacija.getCena() * this.kupac.getTip().getPopust();
		else this.konacnaCena = 
				4* this.manifestacija.getCena() * this.kupac.getTip().getPopust();
		
		double bodovi = this.kupac.getBrojBodova() + this.manifestacija.getCena()/1000 * 133; 
		this.kupac.setBrojBodova(bodovi);
		
		this.kupac.getSveKarte().add(this);
	}
	
	public Karta(String id, Manifestacija manifestacija, Kupac kupac, StatusKarte status, TipKarte tip,
			double konacnaCena) {
		super();
		this.id = id;
		this.manifestacija = manifestacija;
		this.kupac = kupac;
		this.status = status;
		this.tip = tip;
		this.konacnaCena = konacnaCena;
		
		double bodovi = this.kupac.getBrojBodova() + this.manifestacija.getCena()/1000 * 133; 
		this.kupac.setBrojBodova(bodovi);

		this.kupac.getSveKarte().add(this);
	}

	@Override
	public String toString() {
		return "{\"id\": \""+ this.id + "\", \"manifestacija\": "+ this.manifestacija  +
				", \"kupac\": \""+ this.kupac.getKorisnickoIme()+ "\", \"status\": \""+ this.status +
				"\", \"tip\": \""+ this.tip + "\", \"konacnaCena\": \""+ this.konacnaCena  +"\"}";
	}

	public String jednostavanString() {
		return "{\"id\": \""+ this.id + "\", \"kupac\": \""+ this.kupac.getKorisnickoIme()+ 
				"\", \"status\": \""+ this.status + "\", \"tip\": \""+ this.tip + 
				"\", \"konacnaCena\": \""+ this.konacnaCena  +"\"}";
	}

}
