package Actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Interfaces.Actor;
import Interfaces.Task;
import Supermarket.Options;
import Supermarket.Supermarket;

public class TaskManager implements Actor {
	private List<Employee> employees;
	
	private final HashMap<Task, List<Employee>> tasks;

	public TaskManager(List<Employee> employees, List<Task> tasks) {
		this.employees = employees;
		this.tasks = new HashMap<Task, List<Employee>>();
		
		for (Task task : tasks) {
			addTask(task);
		}
	}
	
	public void update() {
		for(Employee employee : employees) {
			if (tasks.containsKey(employee.getTask()) && employee.getTask().getMaxEmployeeCount() < tasks.get(employee.getTask()).size()) {
				tasks.get(employee.getTask()).add(employee);
			}
		}
	}
	
	private void addTask(Task task) {
		if (!this.tasks.containsKey(task)) {
			this.tasks.put(task, new ArrayList<Employee>());
		}
	}
	
	public Task getTask() {
		Task task = null;
		for(Task currentTask : tasks.keySet()) {
			if (task == null ||  tasks.get(task).size() < task.getMaxEmployeeCount() && currentTask.getPriority() > task.getPriority()) {
				task = currentTask;
			}
		}
		return task;
	}

	public void register(Employee employee) {
		if (!this.employees.contains(employee)) {
			employees.add(employee);
		}
	}

	@Override
	public void act(Supermarket supermarket) {
		update();
		Employee employee;
		for(Task task: tasks.keySet()) {
			if (tasks.get(task).size() < task.getMaxEmployeeCount() &&  task.getPriority() > Options.TASKMANAGER_SWITCH_TASK_TRESHOLD) {
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
			if (employee != null && currentemployee.getTask().getPriority() < employee.getTask().getPriority()) {
				employee = currentemployee;
			}
		}
		return employee;
	}
}
