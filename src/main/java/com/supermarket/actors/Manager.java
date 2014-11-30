package com.supermarket.actors;

import java.util.ArrayList;
import java.util.List;

import com.supermarket.interfaces.BuyZone;

public class Manager {
	private final List<Employee> employees;
	private final List<BuyZone> buyZones;

	public Manager() {
		this.employees = new ArrayList<Employee>();
		this.buyZones = new ArrayList<BuyZone>();
	}
	
	public Employee CreateEmployee() {
		 Employee employee = new Employee();
		 employees.add(employee);
		 return employee;
	}

}
