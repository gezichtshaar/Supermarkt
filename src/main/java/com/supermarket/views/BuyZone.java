package com.supermarket.views;

import java.awt.Color;
import java.awt.Graphics2D;

import com.supermarket.models.Record;

public class BuyZone extends Drawable {
	private static final int RADIUS = 10;
	
	private final Types type;

	public BuyZone(Record record) {
		super(record);
		this.type = Types.valueOf(record.getType());
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(type.getColor());
		g.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS * 2);
	}

	enum Types {
		AISLE(Color.GREEN),
		DISCOUNTAISLE(Color.LIGHT_GRAY),
		DEPARTMENT(Color.BLUE),
		CASHREGISTER(Color.MAGENTA);
		
		private final Color color;
		private Types(Color color) {
			this.color = color;
		}
		
		public Color getColor() {
			return color;
		}
	}
}
