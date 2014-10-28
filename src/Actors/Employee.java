package Actors;

import Supermarket.Supermarket;
import Interfaces.Actor;
import Interfaces.Task;

public class Employee implements Actor {
	private Task task;

	public Employee(Task task) {
		this.task = task;
	}

	public void act(Supermarket supermarket) {
		if (task != null) {
			task.update(supermarket);
		} else {
			//task = supermarket.getTask();
		}
	}
}
