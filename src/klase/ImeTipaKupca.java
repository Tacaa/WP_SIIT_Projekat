package klase;

public enum ImeTipaKupca {
	OBICNI,
	ZLATNI,
	SREBRNI,
	BRONZANI;
	
	private String[] opis = {"OBICNI", "ZLATNI", "SREBRNI", "BRONZANI"};
	@Override
	public String toString() {
		return opis[this.ordinal()];
	}
}
