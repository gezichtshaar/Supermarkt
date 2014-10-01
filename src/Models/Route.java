package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Route<T> {
	private List<Route<T>> nextRoutes;
	private T location;

	public Route(T location) {
		this.nextRoutes = new ArrayList<Route<T>>();
		this.location = location;
	}

	public void addRoute(Route<T> route) {
		nextRoutes.add(route);
	}

	public Route<T> getRandomRoute() {
		if (!nextRoutes.isEmpty()) {
			return nextRoutes.get(new Random().nextInt(nextRoutes.size()));
		}
		return null;
	}

	public T getLocation() {
		return location;
	}
}
