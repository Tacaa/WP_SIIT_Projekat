package klase;

public class TipKupca {
	private ImeTipaKupca ime;
	private double popust;		// 1 ili 0.97 ili 0.95 ili 0 ako nema
	private int potrebihBodova;		// 0 ili 3000 ili 5000
	
	public ImeTipaKupca getIme() {
		return ime;
	}
	public void setIme(ImeTipaKupca ime) {
		this.ime = ime;
	}
	
	public double getPopust() {
		return popust;
	}
	public void setPopust(double popust) {
		this.popust = popust;
	}
	
	public int getPotrebihBodova() {
		return potrebihBodova;
	}
	public void setPotrebihBodova(int potrebihBodova) {
		this.potrebihBodova = potrebihBodova;
	}
	
	public TipKupca() {
		super();
	}
	
	public TipKupca(ImeTipaKupca ime, double popust, int potrebihBodova) {
		super();
		this.ime = ime;
		this.popust = popust;
		this.potrebihBodova = potrebihBodova;
	}
	
	@Override
	public String toString() {
		return "TipKupca [ime=" + ime + ", popust=" + popust + ", potrebihBodova=" + potrebihBodova + "]";
	}
}
