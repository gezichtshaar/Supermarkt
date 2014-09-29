package Supermarkt;

import java.util.ArrayList;
import java.util.List;

import Actors.Persoon;
import Models.Kassa;
import Models.Locatie;
import Models.Magazijn;
import Models.Pad;

public class Supermarkt {
	private Database database;
	
	private List<Persoon> personen;
	
	private Magazijn magazijn;
	private Locatie looppad;
	
	public Supermarkt () {
		this.database = new Database();
		
		this.personen = new ArrayList<Persoon>();
		
		this.magazijn = new Magazijn();
		this.looppad = new Pad().addLocatie(new Pad()).addLocatie(new Pad()).addLocatie(new Kassa());
	}
	
	private void tick () {
		for (Persoon persoon : personen) {
			persoon.update();
		}
		//save to database
	}
	
	public void run () { //maybe threading
		while (true) {
			tick();
		}
	}
}
