package Actors;

import Models.Locatie;

public abstract class Persoon {
	private Locatie locatie;

	public Persoon(Locatie locatie) {
		this.locatie = locatie;
	}
	
	public abstract void update();

}
