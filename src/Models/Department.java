package Models;

import Interfaces.Buyzone;
import Interfaces.Task;
import Supermarket.Supermarket;

public class Department implements Task, Buyzone {

	public Department() {
	}

	@Override
	public void update(Supermarket supermarket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product takeProduct(String productName) {
		// TODO Auto-generated method stub
		return null;
	}
}
