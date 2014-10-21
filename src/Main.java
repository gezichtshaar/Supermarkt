import java.util.Scanner;

import Models.Department;
import Models.ProductTypes;
import Models.Route;
import Models.Aisle;
import Supermarket.Supermarket;

public class Main {
	private static final Route SUPERMARKT_LAYOUT = Route.BuildRoute(
				new Route[] {
					new Route(new Aisle()),
					new Route(new Aisle()),
					new Route(new Aisle()),
					new Route(new Department(ProductTypes.BREAD)) }, 
				new int[][] {
					{ 0, 1, 3 },
					{ 0, 2, 3 } });
	
	public static void main(String[] args) {
		Supermarket supermarket = new Supermarket(SUPERMARKT_LAYOUT);

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
