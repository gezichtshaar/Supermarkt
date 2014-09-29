package Actors;

import java.util.Random;

import Models.LoopRoute;

public class NieuwNederlanderKlant extends Klant {
	private boolean sleepy;

	public NieuwNederlanderKlant(LoopRoute locatie) {
		super(locatie);
		this.sleepy = true;
	}

	@Override
	public void update() {
		if (!sleepy) {
			locatie = locatie.getRandomRoute();
		}
		
		sleepy = new Random().nextBoolean();
	}
}
