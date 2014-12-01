package com.supermarket.interfaces;

import com.supermarket.main.Supermarket;

public interface Task {
	public int getPriority();
	public void doTask(Supermarket supermarket);
	public int getMaxEmployees();
}
