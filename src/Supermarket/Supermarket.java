package Supermarket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Observable;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

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

	private Queue<Costumer> cashRegisterQueue;
	private CashRegister[] cashRegisters;

	public Supermarket(Route route) {
		this.running = false;
		this.database = new Database();

		this.actors = Collections.synchronizedSet(new HashSet<Actor>());

		this.storage = new Storage();
		this.route = route;

		this.cashRegisterQueue = new ConcurrentLinkedQueue<Costumer>();
		this.cashRegisters = new CashRegister[] { new CashRegister(this.cashRegisterQueue),
				new CashRegister(this.cashRegisterQueue), new CashRegister(this.cashRegisterQueue), new CashRegister(this.cashRegisterQueue) };
	}

	private void tick() {
		database.initCommit();
		for (Actor actor : actors) {
			actor.act(this);
		}
		database.doCommit();
	}

	public void run() {
		running = true;
		while (running) {
			setChanged();
			tick();
			notifyObservers();
		}
		database.close();
	}

	public Storage getStorage() {
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
	
	public void checkout(Costumer costumer) {
		if (!cashRegisterQueue.contains(costumer)) {
			actors.remove(costumer);
			cashRegisterQueue.add(costumer);
		}
	}

	@Override
	public String toString() {
		synchronized (actors) {
			return String.format("Aantal klanten: %d", actors.size());
		}
	}
}
