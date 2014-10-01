import java.util.Scanner;

import Interfaces.Buyzone;
import Models.Department;
import Models.Route;
import Models.Aisle;
import Supermarket.Supermarket;

public class Main {
	public static void main(String[] args) {

		@SuppressWarnings("unchecked")
		Supermarket supermarket = new Supermarket(
				new Route[] {
					new Route<Buyzone>(new Aisle()),
					new Route<Buyzone>(new Aisle()),
					new Route<Buyzone>(new Aisle()),
					new Route<Buyzone>(new Department()) }, 
				new int[][] {
					{ 0, 1, 3 },
					{ 0, 2, 3 } });

		new Thread(supermarket).start();

		Scanner console = new Scanner(System.in);

		while (supermarket.isRunning() && console.hasNextLine()) {
			switch (console.nextLine()) {
			case "q":
				supermarket.stop();
				break;
			case "stats":
				System.out.println(supermarket.toString());
				break;
			}
		}
		console.close();
	}
}
