package klase;

public enum TipManifestacije {
	KONCERT,
	FESTIVAL,
	POZORISNA_PREDSTAVA,
	BIOSKOPSKA_PROJEKCIJA,
	SPORTSKA_NADMETANJA,
	PLESNI_NASTUP,
	STANDUP_NASTUP,
	NASTUP_CIRKUSA,
	SAJAM,
	MUZEJSKO_VECE,
	KNJIZEVNO_VECE,
	DRUGA_KATEGORIJA;
	
	private String[] opis = {"Koncert", "Festival", "Pozorisna predstava", "Bioskopska projekcija",
			"Sportska nadmetanja", "Plesni nastup", "Standup nastup", "Nastup cirkusa",
			"Sajam", "Muzejsko vece", "Knjizevno vece", "Druga kategorija"};
	@Override
	public String toString() {
		return opis[this.ordinal()];
	}
}
