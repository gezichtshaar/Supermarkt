package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoopRoute<T> {
	private List<LoopRoute<T>> vervolgRoute;
	private T locatie;
	
	public LoopRoute(T locatie) {
		vervolgRoute = new ArrayList<LoopRoute<T>>();
	}
	
	public void addRoute(LoopRoute<T> route) {
		vervolgRoute.add(route);
	}
	
	public LoopRoute<T> getRandomRoute() {
		if (!vervolgRoute.isEmpty()) {
			return vervolgRoute.get(new Random().nextInt(vervolgRoute.size()));
		}
		return null;
	}
	
	public T getLocatie() {
		return locatie;
	}
}
