package com.supermarket.views;

import java.awt.Graphics2D;

class Customer extends Drawable {
	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;
	
	public Customer(int x, int y) {
		super(x, y);
	}

	public void draw(Graphics2D g) {
		g.drawRect(x - WIDTH/2, y - HEIGHT/2, WIDTH, HEIGHT);
	}

}
