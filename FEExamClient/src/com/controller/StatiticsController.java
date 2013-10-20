package com.controller;

import java.awt.EventQueue;

import com.view.StatiticsView;

public class StatiticsController {
	private static volatile StatiticsController controller;
	StatiticsView frame = new StatiticsView();

	private StatiticsController() {
	}

	public static StatiticsController getInstance() {
		if (controller == null) {
			synchronized (StatiticsController.class) {
				if (controller == null) {
					controller = new StatiticsController();
				}
			}
		}
		return controller;
	}

	public void showView() {
		// The correct way to call a view.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void main(String args[]) {
	}

	public void load() {
		showView();
	}
}
