package com.supermarket.views;

import java.awt.Color;
import java.awt.Graphics2D;

public class BuyZone extends Drawable {
	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;
	
	private final Types type;

	public BuyZone(int x, int y, Types type) {
		super(x, y);
		this.type = type;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(type.getColor());
		g.fillRect(x - WIDTH/2, y - HEIGHT/2, WIDTH, HEIGHT);
	}

	enum Types {
		AISLE(Color.GREEN);
		
		private final Color color;
		private Types(Color color) {
			this.color = color;
		}
		
		public Color getColor() {
			return color;
		}
	}
}
