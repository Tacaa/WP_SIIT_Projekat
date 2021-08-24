package klase;

public enum ImeTipaKupca {
	NEMA,
	ZLATNI,
	SREBRNI,
	BRONZANI;
	
	private String[] opis = {"NEMA", "ZLATNI", "SREBRNI", "BRONZANI"};
	@Override
	public String toString() {
		return opis[this.ordinal()];
	}
}
