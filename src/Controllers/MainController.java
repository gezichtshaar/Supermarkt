package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import Supermarket.Supermarket;

public class MainController {
	private Supermarket supermarket;
	private List<Observer> viewsList;

	public MainController(Supermarket supermarket) {
		this.supermarket = supermarket;
		this.viewsList = new ArrayList<>();
	}

	public void addView(Observer view) {
		supermarket.addObserver(view);
		this.viewsList.add(view);
	}

	public void addCostumer() {
		supermarket.newCostumer();
	}
}
