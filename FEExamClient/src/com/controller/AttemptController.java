package com.controller;

import java.awt.EventQueue;

import com.view.AttemptView;

public class AttemptController {
	private static volatile AttemptController controller;
	AttemptView frame = new AttemptView();

	private AttemptController() {
	}

	public static AttemptController getInstance() {
		if (controller == null) {
			synchronized (AttemptController.class) {
				if (controller == null) {
					controller = new AttemptController();
				}
			}
		}
		return controller;
	}

	public void showView() {
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
