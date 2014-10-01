package Supermarkt;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import Actors.Costumer;
import Interfaces.Buyzone;
import Interfaces.Person;
import Interfaces.Task;
import Models.CashRegister;
import Models.Route;
import Models.Storage;

public class Supermarket implements Runnable {
	private volatile boolean running;
	
	private Database database;

	private Set<Person> persons;

	private Storage storage;
	private Route<Buyzone> route;

	private Queue<Costumer> kassaQueue;
	private CashRegister[] cashRegisters;

	public Supermarket(Route<Buyzone>[] nodeMap, int[][] routeMap) {
		this.running = false;
		this.database = new Database();

		this.persons = new HashSet<Person>();

		this.storage = new Storage();
		this.route = buildRoute(nodeMap, routeMap);

		this.cashRegisters = new CashRegister[] { new CashRegister(), new CashRegister(), new CashRegister(), new CashRegister() };
	}

	private Route<Buyzone> buildRoute(Route<Buyzone>[] nodeMap, int[][] routeMap) {
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
		for (Person person : persons) {
			person.act(this);
		}
		// save to database
	}

	public void run() { // maybe threading
		running = true;
		while (running) {
			tick();
		}
	}

	public void afrekenen(Costumer klant) {
		if (persons.contains(klant)) {
			persons.remove(klant);
			kassaQueue.add(klant);
		}
	}

	public Queue<Costumer> getKassaQueue() {
		return kassaQueue;
	}

	public Storage getMagazijn() {
		return storage;
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
		return String.format("Aantal klanten: %d", persons.size());
	}
}
