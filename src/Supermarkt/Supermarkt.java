package Supermarkt;

import java.util.ArrayList;
import java.util.List;

import Actors.Klant;
import Actors.Persoon;
import Models.LoopRoute;
import Models.Magazijn;

public class Supermarkt {
	private Database database;

	private List<Persoon> personen;

	private Magazijn magazijn;
	private LoopRoute loopRoute;

	public Supermarkt(LoopRoute[] nodeMap, int[][] routeMap) {
		this.database = new Database();

		this.personen = new ArrayList<Persoon>();

		this.magazijn = new Magazijn();
		this.loopRoute = buildRoute(nodeMap, routeMap);
	}

	private LoopRoute buildRoute(LoopRoute[] nodeMap, int[][] routeMap) {
		if (nodeMap.length > 0) {
			for (int y = 0; y < routeMap.length; y++) {
				for (int x = 0; x < routeMap[x].length - 1; x++) {
					nodeMap[routeMap[y][x]]
							.addRoute(nodeMap[routeMap[y][x + 1]]);
				}
			}
			return nodeMap[0];
		}
		return null;
	}

	private void tick() {
		for (Persoon persoon : personen) {
			persoon.update(this);
		}
		// save to database
	}

	public void run() { // maybe threading
		while (true) {
			tick();
		}
	}

	public void leave(Klant klant) {
		personen.remove(klant);
	}
}
