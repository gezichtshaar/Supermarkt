package Actors;

import Interfaces.Koopzone;
import Models.LoopRoute;
import Supermarkt.Supermarkt;

public class FaggotKlant extends Klant {

	public FaggotKlant(LoopRoute<Koopzone> locatie) {
		super(locatie);
	}

	@Override
	public void actKlant(Supermarkt supermarkt) {
		// TODO Auto-generated method stub
	}
}
