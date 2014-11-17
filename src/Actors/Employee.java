package Actors;

import Supermarket.Supermarket;
import Interfaces.Actor;
import Interfaces.Task;

public class Employee implements Actor {
	private TaskManager taskManager;
	private Task task;

	public Employee(TaskManager taskManager) {
		this.taskManager = taskManager;
		this.taskManager.register(this);
		this.task = taskManager.getTask();
	}

	public void act(Supermarket supermarket) {
		System.out.println(task);
		if (task != null && task.getPriority() > 0) {
			task.update(supermarket);
		} else {
			task = taskManager.getTask();
		}
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
