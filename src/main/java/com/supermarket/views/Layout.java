package com.supermarket.views;

import java.awt.Graphics2D;
import java.util.List;

public class Layout {
	private final List<BuyZone> buyZones;

	public Layout(List<BuyZone> buyZones) {
		this.buyZones = buyZones;
	}

	public void draw(Graphics2D g) {
		for(BuyZone buyZone : buyZones) {
			buyZone.draw(g);
		}
	}
}
