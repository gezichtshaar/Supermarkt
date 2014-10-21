package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Interfaces.Buyzone;

public class Route {
	private List<Route> nextRoutes;
	private Buyzone location;

	public Route(Buyzone location) {
		this.nextRoutes = new ArrayList<Route>();
		this.location = location;
	}

	public void addRoute(Route route) {
		nextRoutes.add(route);
	}

	public Route getRandomRoute() {
		if (!nextRoutes.isEmpty()) {
			return nextRoutes.get(new Random().nextInt(nextRoutes.size()));
		}
		return null;
	}

	public Buyzone getLocation() {
		return location;
	}
	
	public static Route BuildRoute(Route[] nodeMap, int[][] routeMap) {
		if (nodeMap.length > 0) {
			for (int[] path : routeMap) {
				for (int x = 0; x < path.length - 1; x++) {
					nodeMap[path[x]].addRoute(nodeMap[path[x + 1]]);
				}
			}
			return nodeMap[0];
		}
		return null;
	}
}
