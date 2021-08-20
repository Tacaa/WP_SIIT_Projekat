package klase;

public enum Pol {
	MUSKI,
	ZENSKI;
	
	private String[] opis = {"Muski", "Zenski"};
	@Override
	public String toString() {
		return opis[this.ordinal()];
	}
}
