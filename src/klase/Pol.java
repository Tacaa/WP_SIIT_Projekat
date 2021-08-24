package klase;

public enum Pol {
	MUSKI,
	ZENSKI;
	
	private String[] opis = {"MUSKI", "ZENSKI"};
	@Override
	public String toString() {
		return opis[this.ordinal()];
	}
}
