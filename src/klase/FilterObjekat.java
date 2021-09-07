package klase;

import java.time.LocalDate;

public class FilterObjekat {
	
	private String naziv;
	private double cenaMin;
	private double cenaMax;
	private LocalDate datumMin;
	private LocalDate datumMax;
	private TipKarte tip;
	private StatusKarte status;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public double getCenaMin() {
		return cenaMin;
	}
	public void setCenaMin(double cenaMin) {
		this.cenaMin = cenaMin;
	}
	
	public double getCenaMax() {
		return cenaMax;
	}
	public void setCenaMax(double cenaMax) {
		this.cenaMax = cenaMax;
	}
	
	public LocalDate getDatumMin() {
		return datumMin;
	}
	public void setDatumMin(LocalDate datumMin) {
		this.datumMin = datumMin;
	}
	
	public LocalDate getDatumMax() {
		return datumMax;
	}
	public void setDatumMax(LocalDate datumMax) {
		this.datumMax = datumMax;
	}
	
	public TipKarte getTip() {
		return tip;
	}
	public void setTip(TipKarte tip) {
		this.tip = tip;
	}
	
	public StatusKarte getStatus() {
		return status;
	}
	public void setStatus(StatusKarte status) {
		this.status = status;
	}
	
	public FilterObjekat() {
		super();
	}
	
	public FilterObjekat(String naziv, double cenaMin, double cenaMax, LocalDate datumMin, LocalDate datumMax,
			TipKarte tip, StatusKarte status) {
		super();
		this.naziv = naziv;
		this.cenaMin = cenaMin;
		this.cenaMax = cenaMax;
		this.datumMin = datumMin;
		this.datumMax = datumMax;
		this.tip = tip;
		this.status = status;
	}
	
}
