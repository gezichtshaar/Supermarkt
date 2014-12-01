package com.supermarket.actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.INITIALIZE;

import com.supermarket.interfaces.BuyZone;
import com.supermarket.interfaces.Task;

public class Manager {
	private final Map<BuyZone, List<Employee>> taskMap;

	public Manager(List<BuyZone> buyZones) {
		this.taskMap = new HashMap<BuyZone, List<Employee>>();
		initialize(buyZones);
	}

	private void initialize(List<BuyZone> buyZones) {
		for (BuyZone buyZone : buyZones) {
			this.taskMap.put(buyZone, new ArrayList<Employee>());
		}
	}

	private void removeFromTaskMap(Employee employee) {
		this.taskMap.values().remove(employee);
	}

	private BuyZone getBuyZoneFromEmplyee(Employee employee) {
		for (BuyZone buyZone : this.taskMap.keySet()) {
			if (this.taskMap.get(buyZone).contains(employee)) {
				return buyZone;
			}
		}
		return null;
	}

	public Employee CreateEmployee() {
		Employee employee = new Employee(this);
		return employee;
	}

	public Task getTask(Employee employee) {
		BuyZone taskToDoBuyZone = getBuyZoneFromEmplyee(employee);
		for (BuyZone buyZone : taskMap.keySet()) {
			if ((taskToDoBuyZone == null || buyZone.getPriority() > taskToDoBuyZone.getPriority()) && taskMap.get(buyZone).size() < buyZone.getMaxEmployees()) {
				taskToDoBuyZone = buyZone;
			}
		}
		if (taskToDoBuyZone != null) {
			taskMap.get(taskToDoBuyZone).add(employee);
		} else {
			removeFromTaskMap(employee);
		}
		return taskToDoBuyZone;
	}
}
