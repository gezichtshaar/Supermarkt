package Actors;

import Models.LoopRoute;
import Supermarkt.Supermarkt;

public abstract class Persoon {
	protected LoopRoute locatie;

	public Persoon(LoopRoute locatie) {
		this.locatie = locatie;
	}
	
	public abstract void update(Supermarkt supermarkt);

}
