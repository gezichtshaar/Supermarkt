package Actors;

import Interfaces.Actor;
import Models.Storage;
import Supermarket.Options;
import Supermarket.Supermarket;

public class Carriage implements Actor {
	private int waitTime;
	
	public Carriage() {
		this.waitTime = 0;
	}

	@Override
	public void act(Supermarket supermarket) {
		if (waitTime++ >= Options.CARRIAGE_WAIT_TIME) {
			fillStorage(supermarket.getStorage());
			this.waitTime = 0;
		}
	}
	
	private void fillStorage(Storage storage) {	
	}
}
