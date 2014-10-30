package Actors;

import Supermarket.Supermarket;
import Interfaces.Actor;
import Interfaces.Task;

public class Employee implements Actor {
	private Task task;

	private Employee(Task task) {
		this.task = task;
	}

	public void act(Supermarket supermarket) {
		if (task != null && task.getPriority() > 0) {
			task.update(supermarket);
		} else {
			task = supermarket.getTask();
		}
	}

	public Task getTask() {
		return task;
	}
	
	public static Employee Create(TaskManager taskManager) {
		Employee employee = new Employee(taskManager.getTask());
		taskManager.register(employee);
		return employee;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
