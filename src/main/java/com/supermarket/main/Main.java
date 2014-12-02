package com.supermarket.main;

import com.supermarket.controllers.Controller;
import com.supermarket.models.Aisle;
import com.supermarket.models.CashRegister;
import com.supermarket.models.Department;
import com.supermarket.models.Location;
import com.supermarket.models.Product;
import com.supermarket.views.MainView;

public class Main {
	private static final Location LAYOUT = Location.CreateLayout(new Location[] {
			new Location(new Department(Product.Types.BREAD), 0, 0),
			new Location(new Aisle(new Product.Types[] {Product.Types.PINDABOTER, Product.Types.JELMERTBAMMETJE}), 1, 0),
			new Location(new CashRegister(), 1, 1)}, new int[][]{
			{0, 1, 2},
			{0, 2}});
	
	public static void main(String[] args) {
		final Controller controller = new Controller();
		
		new Thread(new Runnable() {
			public void run() {
				Supermarket supermarket = new Supermarket(LAYOUT);
				controller.setModel(supermarket);
				try {
					supermarket.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				MainView window = new MainView(controller);
				controller.addView(window);
			}
		}).start();
	}
}
