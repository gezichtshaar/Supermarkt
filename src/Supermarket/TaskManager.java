package Supermarket;

import java.util.ArrayList;
import java.util.List;

import Actors.Employee;
import Interfaces.Task;

public class TaskManager {
	private List<Employee> employees;
	
	private final List<Task> allTasks;
	private List<Task> openTasks;

	public TaskManager(List<Employee> employees, List<Task> tasks) {
		this.employees = employees;
		this.allTasks = tasks;
		this.openTasks = new ArrayList<>();
	}
	
	public void update(List<Employee> employees) {
		resetOpenTasks();
		for(Employee employee : employees) {
			this.openTasks.remove(employee.getTask());
		}
	}
	
	private void resetOpenTasks() {
		this.openTasks.clear();
		this.openTasks.addAll(allTasks);
	}
	
	public Task getTask() {
		Task task = null;
		for(Task currentTask : openTasks) {
			if (task == null || currentTask.getPriority() > task.getPriority()) {
				task = currentTask;
			}
		}
		return task;
	}

	public void register(Employee employee) {
		employees.add(employee);
	}
}
