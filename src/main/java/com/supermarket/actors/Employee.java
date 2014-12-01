package com.supermarket.actors;

import com.supermarket.interfaces.Actor;
import com.supermarket.interfaces.Task;
import com.supermarket.main.Supermarket;

public class Employee implements Actor {
	private final Manager manager;

	Employee(Manager manager) {
		this.manager = manager;
	}

	public boolean act(Supermarket supermarket) {
		Task task = manager.getTask(this);
		if (task != null) {
			task.doTask(supermarket);
		}
		return false;
	}
}
