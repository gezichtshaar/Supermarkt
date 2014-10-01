package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Route<T> {
	private List<Route<T>> vervolgRoute;
	private T locatie;
	
	public Route(T locatie) {
		vervolgRoute = new ArrayList<Route<T>>();
	}
	
	public void addRoute(Route<T> route) {
		vervolgRoute.add(route);
	}
	
	public Route<T> getRandomRoute() {
		if (!vervolgRoute.isEmpty()) {
			return vervolgRoute.get(new Random().nextInt(vervolgRoute.size()));
		}
		return null;
	}
	
	public T getLocatie() {
		return locatie;
	}
}
