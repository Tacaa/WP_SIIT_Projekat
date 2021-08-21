package klase;

public enum Pol {
	MUSKI,
	ZENSKI;
	
	private String[] opis = {"MUSKARAC", "ZENA"};
	@Override
	public String toString() {
		return opis[this.ordinal()];
	}
}
