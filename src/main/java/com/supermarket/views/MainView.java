package com.supermarket.views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import com.supermarket.controllers.Controller;

public class MainView implements Observer {
	private Controller controller;
	private JFrame frame;
	
	/**
	 * Create the application.
	 */
	public MainView(Controller controller) {
		this.controller = controller;
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 450, 300);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void update(Observable o, Object arg) {
	}
}
