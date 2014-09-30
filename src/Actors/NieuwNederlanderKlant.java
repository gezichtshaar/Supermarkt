package Actors;

import java.util.Random;

import Interfaces.Koopzone;
import Models.LoopRoute;
import Supermarkt.Supermarkt;

public class NieuwNederlanderKlant extends Klant {
	private int sleepyTimer;

	public NieuwNederlanderKlant(LoopRoute<Koopzone> locatie) {
		super(locatie);
		this.sleepyTimer = getNextSleepAmount();
	}

	@Override
	public void actKlant(Supermarkt supermarkt) {
		if (--sleepyTimer <= 0) {
			locatie = locatie.getRandomRoute();
			sleepyTimer = getNextSleepAmount();
		}
	}
	
	private int getNextSleepAmount() {
		return new Random().nextInt(10);
	}
}
