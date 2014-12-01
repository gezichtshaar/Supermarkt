package com.supermarket.models;

public class Record {
	private String type;
	private final int x, y;
	
	public Record(String Type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public String getType() {
		return type;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
