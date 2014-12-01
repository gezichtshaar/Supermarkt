package com.supermarket.views;

import java.awt.Graphics2D;

abstract class Drawable {
	protected int x, y;

	public Drawable(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void draw(Graphics2D g);
}
