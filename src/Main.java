import javax.swing.SwingUtilities;

import Controllers.MainController;
import Graphics.MainWindow;
import Models.Department;
import Models.Product;
import Models.Route;
import Models.Aisle;
import Supermarket.Supermarket;

public class Main {
	private static final Route SUPERMARKT_LAYOUT = Route.BuildRoute(
				new Route[] {
					new Route(new Aisle()),
					new Route(new Aisle()),
					new Route(new Aisle()),
					new Route(new Department(Product.Types.BREAD)) }, 
				new int[][] {
					{ 0, 1, 3 },
					{ 0, 2, 3 } });
	
	public static void main(String[] args) {
		Supermarket supermarket = new Supermarket(SUPERMARKT_LAYOUT);
		MainController controller = new MainController(supermarket);
		MainWindow mainWindow = new MainWindow(controller);
		controller.addView(mainWindow);
		
		new Thread(mainWindow).start();
		new Thread(supermarket).start();
	}
}
