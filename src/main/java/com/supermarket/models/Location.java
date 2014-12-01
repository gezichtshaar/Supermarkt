package com.supermarket.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.supermarket.interfaces.BuyZone;

public class Location {
	private final BuyZone buyZone;
	private final List<Location> nextLocations;
	private final int x, y;

	public Location(BuyZone buyZone, int x, int y) {
		this.buyZone = buyZone;
		this.nextLocations = new ArrayList<Location>();
		this.x = x;
		this.y = y;
	}
	
	public void addLocation(Location location) {
		this.nextLocations.add(location);
	}
	
	public BuyZone getLocation() {
		return buyZone;
	}

	public static Location CreateLayout(Location[] locations, int[][] connections) {
		if (locations.length > 0) {
			for (int[] path : connections) {
				for(int n = 0; n < path.length -1; n++) {
					locations[n].addLocation(locations[n+1]);
				}
			}
			return locations[0];
		}
		return null;
	}

	public Location next(Set<Product.Types> wantsTypes) {
		if (nextLocations.size() > 0) {
			return nextLocations.get(new Random().nextInt(nextLocations.size()));
		}
		return null;
	}

	public List<BuyZone> toList() {
		List<BuyZone> buyZones = new ArrayList<BuyZone>();
		buyZones.add(buyZone);
		this.toList(buyZones);
		return buyZones;
	}
	
	private void toList(List<BuyZone> buyZones) {
		for(Location buyZone : nextLocations) {
			if (!buyZones.contains(buyZone.getLocation())) {
				buyZones.add(buyZone.getLocation());
				buyZone.toList(buyZones);
			}
		}
	}
}
