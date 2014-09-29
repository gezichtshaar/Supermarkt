package Actors;

import Supermarkt.Supermarkt;
import Interfaces.Persoon;
import Interfaces.Task;

public class Medewerker implements Persoon {
	private Task task;
	
	public Medewerker(Task task) {
		this.task = task;
	}

	public void act(Supermarkt supermarkt) {
		task.update(supermarkt);
	}
}
