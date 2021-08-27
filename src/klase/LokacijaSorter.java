package klase;

import java.util.Comparator;

public class LokacijaSorter implements Comparator<Manifestacija> {

	@Override
	public int compare(Manifestacija o1, Manifestacija o2) {
		if((o1.getLokacija().getDrzava().compareTo(o1.getLokacija().getDrzava() ) ) == 0){
			if((o1.getLokacija().getGrad().compareTo(o1.getLokacija().getGrad() ) ) == 0){
				return o1.getLokacija().getUlicaBroj().compareTo(o1.getLokacija().getUlicaBroj());
			}else {
				return o1.getLokacija().getGrad().compareTo(o1.getLokacija().getGrad());
			}
		}else {
			return o1.getLokacija().getDrzava().compareTo(o1.getLokacija().getDrzava() );
		}
		
	}

}
