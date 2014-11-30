package com.supermarket.main;

import java.util.Observable;

import com.supermarket.models.Location;
import com.supermarket.models.Storage;

public class Supermarket extends Observable {
	private static final int TICK_PER_SECOND = 1;
	
	private final Location layout;
	private final Storage storage;
	private volatile boolean running;

	public Supermarket(Location layout) {
		this.layout = layout;
		this.storage = new Storage();
		this.running = false;
	}
	
	private void tick() {
		System.out.println("Doing it");
	}
	
	public void run() throws InterruptedException {
		this.running = true;
		while(running) {
			setChanged();
			tick();
			notifyObservers();
			
			Thread.sleep(1000/TICK_PER_SECOND);
		}
	}

	public Storage getStorage() {
		return this.storage;
	}
}
