package Interfaces;

import Supermarket.Supermarket;

public interface Task {
	public void update(Supermarket supermarket);
	public int getPriority();
	public int getMaxEmployeeCount();
}
