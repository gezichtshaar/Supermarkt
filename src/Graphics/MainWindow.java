package Graphics;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import Controllers.MainController;
import Supermarket.Supermarket;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow implements Observer {
	private MainController controller;
	private JFrame frame;
	private JTextArea textArea;

	/**
	 * Create the application.
	 */
	public MainWindow(MainController controller) {
		this.controller = controller;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 269, 99);
		frame.getContentPane().add(textArea);

		JButton btnNewCost = new JButton("new Cost");
		btnNewCost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.addCostumer();
			}
		});
		btnNewCost.setBounds(10, 121, 91, 23);
		frame.getContentPane().add(btnNewCost);
	}

	public void show() {
		frame.setVisible(true);
	}

	@Override
	public void update(Observable model, Object arg) {
		if (model.getClass() == Supermarket.class) {
			textArea.setText(model.toString());
		}
	}
}
