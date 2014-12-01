package com.supermarket.actors;

import com.supermarket.interfaces.Actor;
import com.supermarket.main.Supermarket;

public class Carriage implements Actor {
	private static final int WAIT_TIME = 100;
	
	private int timer;

	public Carriage() {
		timer = 0;
	}

	public boolean act(Supermarket supermarket) {
		if (++timer >= WAIT_TIME) {
			supermarket.getStorage().fill();
			timer = 0;
		}
		return false;
	}
}
