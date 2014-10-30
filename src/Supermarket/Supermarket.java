package Supermarket;

import java.util.Observable;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import Actors.Costumer;
import Actors.StudentCostumer;
import Interfaces.Actor;
import Interfaces.Task;
import Models.CashRegister;
import Models.Route;
import Models.Storage;

public class Supermarket extends Observable implements Runnable {
	private volatile boolean running;
	private volatile boolean paused;

	private final Database database;

	private volatile Set<Actor> actors;
	private TaskManager taskManager;

	private final Storage storage;
	private Route route;

	private final CashRegister[] cashRegisters;

	public Supermarket(Route route) {
		this.running = false;
		this.paused = false;
		this.database = new Database();

		this.actors = new CopyOnWriteArraySet<Actor>();

		this.storage = new Storage();
		this.route = route;

		this.cashRegisters = new CashRegister[] { new CashRegister(),
				new CashRegister(), new CashRegister(), new CashRegister() };
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
			if (!paused) {
				tick();
			}
			notifyObservers();
			
			try {
				Thread.sleep(Options.SUPERMARKET_TICK_SLEEP);
			} catch (InterruptedException e) {
			}
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

	public void switchPause() {
		this.paused = !paused;
	}

	public boolean isRunning() {
		return running;
	}

	public void checkout(Costumer costumer) {
		actors.remove(costumer);
		cashRegisters[0].addCostumer(costumer);
	}

	public Task getTask() {
		return taskManager.getTask();
	}

	public void newCostumer() {
		actors.add(new StudentCostumer(route));
	}

	public boolean isPaused() {
		return paused;
	}

	@Override
	public String toString() {
		return String.format("Aantal klanten: %d", actors.size());
	}
}
