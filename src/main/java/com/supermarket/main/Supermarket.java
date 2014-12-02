package com.supermarket.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

import org.omg.CORBA.INITIALIZE;

import com.supermarket.actors.Carriage;
import com.supermarket.actors.Customer;
import com.supermarket.actors.Manager;
import com.supermarket.interfaces.Actor;
import com.supermarket.models.Location;
import com.supermarket.models.Record;
import com.supermarket.models.Storage;

public class Supermarket extends Observable {
	private static final int TICK_PER_SECOND = 1;
	private static final int EMPLOYEE_COUNT = 10;
	private static final int MAX_CUSTOMER_INVITE = 3;
	
	private final Location layout;
	
	private final Database database;
	private final Manager manager;
	private final List<Actor> actors;
	private final Storage storage;
	
	private volatile boolean running;

	public Supermarket(Location layout) {
		this.layout = layout;
		
		this.database = new Database();
		this.manager = new Manager(layout.toList());
		this.actors = new ArrayList<Actor>();
		this.storage = new Storage();
		
		this.running = false;
		
		initialize();
	}
	
	private void initialize() {
		this.actors.add(new Carriage());
		for(int n = 0; n < EMPLOYEE_COUNT; n++) {
			this.actors.add(this.manager.CreateEmployee());
		}
	}
	
	private void inviteCustomers(int amount) {
		while(amount --> 0) {
			this.actors.add(Customer.Create(layout));
		}
	}
	
	private void tick() {
		database.startCommit();
		this.inviteCustomers(new Random().nextInt(MAX_CUSTOMER_INVITE));
		
		Iterator<Actor> actorIterator = actors.iterator();
		while(actorIterator.hasNext()) {
			if (actorIterator.next().act(this)) {
				actorIterator.remove();
			}
		}
		database.doCommit();
	}
	
	public void run() throws InterruptedException {
		this.running = true;
		while(running) {
			setChanged();
			tick();
			notifyObservers(this);
			
			Thread.sleep(1000/TICK_PER_SECOND);
		}
		database.destroy();
	}

	public Storage getStorage() {
		return this.storage;
	}
	
	public Database getDatabase() {
		return database;
	}
}
