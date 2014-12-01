package com.supermarket.views;

import java.awt.Graphics2D;

import com.supermarket.models.Record;

abstract class Drawable {
	protected int x, y;

	public Drawable(Record record) {
		this.x = record.getX();
		this.y = record.getY();
	}

	public abstract void draw(Graphics2D g);
}
