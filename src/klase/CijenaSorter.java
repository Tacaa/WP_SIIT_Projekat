package klase;

import java.util.Comparator;

public class CijenaSorter implements Comparator<Manifestacija> {

	@Override
	public int compare(Manifestacija o1, Manifestacija o2) {
		return (int) (o1.getCena() - o2.getCena());
	}

}
