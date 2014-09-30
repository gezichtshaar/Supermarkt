package Supermarkt;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import Actors.Klant;
import Interfaces.Koopzone;
import Interfaces.Persoon;
import Interfaces.Task;
import Models.Kassa;
import Models.LoopRoute;
import Models.Magazijn;

public class Supermarkt implements Runnable {
	private volatile boolean running;
	private Database database;

	private List<Persoon> personen;

	private Magazijn magazijn;
	private LoopRoute<Koopzone> loopRoute;

	private Queue<Klant> kassaQueue;
	private Kassa[] kassas;

	public Supermarkt(LoopRoute<Koopzone>[] nodeMap, int[][] routeMap) {
		this.running = false;
		this.database = new Database();

		this.personen = new ArrayList<Persoon>();

		this.magazijn = new Magazijn();
		this.loopRoute = buildRoute(nodeMap, routeMap);

		this.kassas = new Kassa[] { new Kassa(), new Kassa(), new Kassa(), new Kassa() };
	}

	private LoopRoute<Koopzone> buildRoute(LoopRoute<Koopzone>[] nodeMap, int[][] routeMap) {
		if (nodeMap.length > 0) {
			for (int[] path : routeMap) {
				for (int x = 0; x < path.length - 1; x++) {
					nodeMap[path[x]].addRoute(nodeMap[path[x + 1]]);
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
		running = true;
		while (running) {
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

	public Database getDatabase() {
		return database;
	}

	public void stop() {
		this.running = false;
	}

	public boolean isRunning() {
		return running;
	}
	
	public Task getTask() { //Geef een taak terug die gedaan moet worden
		return null;
	}

	@Override
	public synchronized String toString() {
		return String.format("Aantal klanten: %d", personen.size());
	}
}
