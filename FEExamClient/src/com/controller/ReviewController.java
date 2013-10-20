package com.controller;

import java.awt.EventQueue;

import com.view.ReviewView;

public class ReviewController {
	private static volatile ReviewController controller;
	ReviewView frame = new ReviewView();

	private ReviewController() {
	}

	public static ReviewController getInstance() {
		if (controller == null) {
			synchronized (ReviewController.class) {
				if (controller == null) {
					controller = new ReviewController();
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
}
