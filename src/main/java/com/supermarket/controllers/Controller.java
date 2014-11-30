package com.supermarket.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Controller {
	private Observable model;
	private final List<Observer> views;

	public Controller() {
		this.views = new ArrayList<Observer>();
	}

	public final void setModel(Observable model) {
		this.model = model;
		for (Observer view : views) {
			model.addObserver(view);
		}
	}

	public void addView(Observer observer) {
		this.views.add(observer);
		if (this.model != null) {
			this.model.addObserver(observer);
		}
	}
}
