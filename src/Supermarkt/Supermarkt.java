package Supermarkt;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import Actors.Klant;
import Interfaces.Persoon;
import Models.Kassa;
import Models.LoopRoute;
import Models.Magazijn;

public class Supermarkt {
	private Database database;

	private List<Persoon> personen;

	private Magazijn magazijn;
	private LoopRoute loopRoute;

	private Queue<Klant> kassaQueue;
	private Kassa[] kassas;

	public Supermarkt(LoopRoute[] nodeMap, int[][] routeMap) {
		this.database = new Database();

		this.personen = new ArrayList<Persoon>();

		this.magazijn = new Magazijn();
		this.loopRoute = buildRoute(nodeMap, routeMap);

		this.kassas = new Kassa[] { new Kassa(), new Kassa(), new Kassa(),
				new Kassa() };
	}

	private LoopRoute buildRoute(LoopRoute[] nodeMap, int[][] routeMap) {
		if (nodeMap.length > 0) {
			for (int y = 0; y < routeMap.length; y++) {
				for (int x = 0; x < routeMap[y].length - 1; x++) {
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
			persoon.act(this);
		}
		// save to database
	}

	public void run() { // maybe threading
		while (true) {
			tick();
		}
	}

	public void afrekenen(Klant klant) {
		if (personen.contains(klant)) {
			personen.remove(klant);
			kassaQueue.add(klant);
		}
	}

	public Queue<Klant> getKassaQueue() {
		return kassaQueue;
	}

	public Magazijn getMagazijn() {
		return magazijn;
	}
}
