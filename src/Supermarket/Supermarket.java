package Supermarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import Actors.Carriage;
import Actors.Costumer;
import Actors.Employee;
import Actors.NonBinaryGenderCostumer;
import Actors.StudentCostumer;
import Actors.TaskManager;
import Interfaces.Actor;
import Interfaces.Task;
import Models.CashRegister;
import Models.Route;
import Models.Storage;

public class Supermarket extends Observable implements Runnable {
	private volatile boolean running;
	private volatile boolean paused;

	private final Database database;

	private final Storage storage;
	private Route route;

	private final CashRegister[] cashRegisters;
	

	private volatile Set<Actor> actors;
	private TaskManager taskManager;

	public Supermarket(Route route) {
		this.running = false;
		this.paused = false;
		this.database = new Database();

		this.storage = new Storage();
		this.route = route;

		this.cashRegisters = new CashRegister[] { new CashRegister(),
				new CashRegister(), new CashRegister(), new CashRegister() };
		
		this.actors = new CopyOnWriteArraySet<Actor>();
		this.taskManager = new TaskManager(new ArrayList<Employee>(),
				getTasks());
		
		init();
		inviteCostumers();
	}

	private List<Task> getTasks() {
		List<Task> tasks = route.<Task>toList();
		tasks.add(cashRegisters[0]);
		return tasks;
	}

	private void init() {
		actors.add(taskManager);
		actors.add(new Carriage());
		for (int n = 0; n < Options.SUPERMARKET_EMPLOYEES; n++) {
			actors.add(new Employee(taskManager));
		}
		this.running = true;
	}

	private void inviteCostumers() {
		Costumer costumer;
		for (int n = 0; n < new Random().nextInt(Options.SUPERMARKET_MAX_COSTUMER_INVITE); n++) {
			switch (new Random().nextInt(1)) {
			case 0:
				costumer = new StudentCostumer(route);
				break;
			case 1:
				costumer = new NonBinaryGenderCostumer(route);
				break;
			default:
				costumer = null;
			}
			actors.add(costumer);
		}
	}

	private void tick() {
		database.initCommit();

		//inviteCostumers();
		for (Actor actor : actors) {
			actor.act(this);
		}

		database.doCommit();
	}

	public void run() {
		//init();
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
