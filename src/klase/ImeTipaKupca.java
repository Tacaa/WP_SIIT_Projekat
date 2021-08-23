package klase;

public enum ImeTipaKupca {
	NEMA,
	ZLATNI,
	SREBRNI,
	BRONZANI;
	
	private String[] opis = {"Nema", "Zlatni", "Srebrni", "Bronzani"};
	@Override
	public String toString() {
		return opis[this.ordinal()];
	}
}
