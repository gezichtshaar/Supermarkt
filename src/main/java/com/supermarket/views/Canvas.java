package com.supermarket.views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Canvas extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private final List<Drawable> drawables;

	public Canvas() {
		this.drawables = new ArrayList<Drawable>();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(Drawable drawable : drawables) {
			drawable.draw((Graphics2D)g);
		}
	}
}
