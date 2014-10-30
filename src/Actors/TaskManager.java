package Actors;

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
	}
	
	public void update() {
		for(Employee employee : employees) {
			if (tasks.containsKey(employee.getTask()) && employee.getTask().getMaxEmployeeCount() < tasks.get(employee.getTask()).size()) {
				tasks.get(employee.getTask()).add(employee);
			}
		}
	}
	
	public Task getTask() {
		Task task = null;
		for(Task currentTask : tasks.keySet()) {
			if (task == null || task.getMaxEmployeeCount() < tasks.get(task).size() && currentTask.getPriority() > task.getPriority()) {
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
			if (currentemployee.getTask().getPriority() < employee.getTask().getPriority()) {
				employee = currentemployee;
			}
		}
		return employee;
	}
}
