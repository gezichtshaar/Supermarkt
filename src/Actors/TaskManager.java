package Actors;

import java.util.ArrayList;
import java.util.List;

import Interfaces.Actor;
import Interfaces.Task;
import Supermarket.Options;
import Supermarket.Supermarket;

public class TaskManager implements Actor {
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

	@Override
	public void act(Supermarket supermarket) {
		Employee employee;
		for(Task task: openTasks) {
			if (task.getPriority() > Options.TASKMANAGER_SWITCH_TASK_TRESHHOLD) {
				employee = getEmployeeWithLowestPrioty();
				if (employee !=null) {
					employee.setTask(task);
				}
			}
		}
	}

	private Employee getEmployeeWithLowestPrioty() {
		Employee employee = null;
		for(Employee currentemployee : employees) {
			if (currentemployee.getTask().getPriority() < employee.getTask().getPriority()) {
				employee = currentemployee;
			}
		}
		return employee;
	}
}
