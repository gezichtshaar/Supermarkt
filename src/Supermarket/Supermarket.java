package Supermarket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Observable;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import sun.misc.JavaAWTAccess;
import Actors.Costumer;
import Actors.NewDutchCostumer;
import Interfaces.Actor;
import Interfaces.Task;
import Models.CashRegister;
import Models.Route;
import Models.Storage;

public class Supermarket extends Observable implements Runnable {
	private volatile boolean running;

	private Database database;

	private volatile Set<Actor> actors;

	private Storage storage;
	private Route route;

	private Queue<Costumer> kassaQueue;
	private CashRegister[] cashRegisters;

	public Supermarket(Route route) {
		this.running = false;
		this.database = new Database();

		this.actors = Collections.synchronizedSet(new HashSet<Actor>());

		this.storage = new Storage();
		this.route = route;

		this.kassaQueue = new ConcurrentLinkedQueue<Costumer>();
		this.cashRegisters = new CashRegister[] { new CashRegister(),
				new CashRegister(), new CashRegister(), new CashRegister() };
	}

	private void tick() {
		setChanged();
		for (Actor actor : actors) {
			actor.act(this);
		}
		// save to database
		notifyObservers();
	}

	public void run() {
		running = true;
		while (running) {
			tick();
		}
	}

	public void checkout(Costumer costumer) {
		if (actors.contains(costumer)) {
			actors.remove(costumer);
			kassaQueue.add(costumer);
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

	public Task getTask() { // Geef een taak terug die gedaan moet worden
		return null;
	}

	@Override
	public synchronized String toString() {
		return String.format("Aantal klanten: %d", actors.size());
	}
	
	public synchronized void newCostumer() {
		actors.add(new NewDutchCostumer(route));
	}
}
