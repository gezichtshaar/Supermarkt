package Actors;

import java.util.ArrayList;
import java.util.List;

import Models.LoopRoute;
import Models.Product;

public abstract class Klant extends Persoon {
	private List<Product> winkelwagen;
	
	public Klant(LoopRoute locatie) {
		super(locatie);
		winkelwagen = new ArrayList<Product>();
	}
}
