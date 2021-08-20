package klase;

public enum ImeTipaKupca {
	ZLATNI,
	SREBRNI,
	BRONZANI;
	
	private String[] opis = {"Zlatni", "Srebrni", "Bronzani"};
	@Override
	public String toString() {
		return opis[this.ordinal()];
	}
}
