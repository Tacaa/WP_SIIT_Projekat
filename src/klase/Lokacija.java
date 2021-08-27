package klase;

public class Lokacija {
	
	private double geografskaDuzina;
	private double geografskaSirina;
	private String ulicaBroj;	// ulica i broj jer moze biti "Nikole Pasica 12a"
	private String grad;
	private String drzava;
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	private int postanskiBroj;
	
	public double getGeografskaDuzina() {
		return geografskaDuzina;
	}
	public void setGeografskaDuzina(double geografskaDuzina) {
		this.geografskaDuzina = geografskaDuzina;
	}
	
	public double getGeografskaSirina() {
		return geografskaSirina;
	}
	public void setGeografskaSirina(double geografskaSirina) {
		this.geografskaSirina = geografskaSirina;
	}
	
	public String getUlicaBroj() {
		return ulicaBroj;
	}
	public void setUlicaBroj(String ulicaBroj) {
		this.ulicaBroj = ulicaBroj;
	}
	
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	
	public int getPostanskiBroj() {
		return postanskiBroj;
	}
	public void setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}
	
	public Lokacija() {
		super();
	}
	
	
	
	public Lokacija(double geografskaDuzina, double geografskaSirina, String ulicaBroj, String grad, String drzava,
			int postanskiBroj) {
		super();
		this.geografskaDuzina = geografskaDuzina;
		this.geografskaSirina = geografskaSirina;
		this.ulicaBroj = ulicaBroj;
		this.grad = grad;
		this.drzava = drzava;
		this.postanskiBroj = postanskiBroj;
	}
	@Override
	public String toString() {
		return "{\"geografskaDuzina\": \"" + this.geografskaDuzina + 
				"\", \"geografskaSirina\": \""+ this.geografskaSirina +
				"\", \"ulicaBroj\": \""+ this.ulicaBroj + "\", \"grad\": \""+ this.grad +
				"\", \"postanskiBroj\": \""+ this.postanskiBroj  + "\"}";
	}
	
}
