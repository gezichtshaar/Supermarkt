package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoopRoute<T> {
	private List<LoopRoute> vervolgRoute;
	private T locatie;
	
	public LoopRoute(T locatie) {
		vervolgRoute = new ArrayList<LoopRoute>();
	}
	
	public void addRoute(LoopRoute route) {
		vervolgRoute.add(route);
	}
	
	public LoopRoute getRandomRoute() {
		if (!vervolgRoute.isEmpty()) {
			return vervolgRoute.get(new Random().nextInt(vervolgRoute.size()));
		}
		return null;
	}
}
