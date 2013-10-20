package com.controller;

import java.awt.EventQueue;

import com.view.ProfileView;

public class ProfileController {
	private static volatile ProfileController controller;
	ProfileView frame = new ProfileView();

	private ProfileController() {
	}

	public static ProfileController getInstance() {
		if (controller == null) {
			synchronized (ProfileController.class) {
				if (controller == null) {
					controller = new ProfileController();
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
