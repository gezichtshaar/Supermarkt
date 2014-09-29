package Actors;

import java.util.ArrayList;
import java.util.List;

import Models.Locatie;
import Models.Product;

public abstract class Klant extends Persoon {
	private List<Product> winkelwagen;
	
	public Klant(Locatie locatie) {
		super(locatie);
		winkelwagen = new ArrayList<Product>();
	}
}
