package klase;

public enum Pol {
	muskarac,
	zena;
	
	private String[] opis = {"muskarac", "zena"};
	@Override
	public String toString() {
		return opis[this.ordinal()];
	}
}
