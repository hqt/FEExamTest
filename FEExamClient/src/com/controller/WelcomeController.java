package com.controller;

import java.awt.EventQueue;

import com.view.WelcomeView;

public class WelcomeController {
	private static volatile WelcomeController controller;
	private WelcomeView frame = new WelcomeView();
	private WelcomeController() {
	}

	public static WelcomeController getInstance() {
		if (controller == null) {
			synchronized (WelcomeController.class) {
				if (controller == null) {
					controller = new WelcomeController();
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

	public void load() {
		frame.cbxQuestionPack.addItem("hallu");
		frame.cbxQuestionPack.addItem("hello");
		frame.cbxQuestionPack.addItem("holla");
		
		frame.lblHello.setText("Hello TrungDQ");
		
		showView();
	}
	
	public static void main(String args[]) {
		WelcomeController wc = new WelcomeController();
		wc.load();
	}
}

