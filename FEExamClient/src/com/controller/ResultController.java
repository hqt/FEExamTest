package com.controller;

import java.awt.EventQueue;

import com.view.ResultView;

public class ResultController {
	private static volatile ResultController controller;
	ResultView frame = new ResultView();

	private ResultController() {
	}

	public static ResultController getInstance() {
		if (controller == null) {
			synchronized (ResultController.class) {
				if (controller == null) {
					controller = new ResultController();
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
