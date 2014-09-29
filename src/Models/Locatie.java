package Models;

import java.util.ArrayList;
import java.util.List;

public abstract class Locatie {
	protected List<Locatie> vervolgLocaties;
	
	public Locatie() {
		vervolgLocaties = new ArrayList<Locatie>();
	}
	
	public Locatie addLocatie(Locatie locatie) {
		vervolgLocaties.add(locatie);
		return this;
	}
}
