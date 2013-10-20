package com.controller;

import java.awt.EventQueue;

import com.model.CommonDataModel;
import com.view.ProfileView;

import database.model.Profile;
import database.transaction.ProfileTable;

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

	/**
	 * true : registered user : welcome back
	 * false : new user : welcome to
	 */
	public boolean changeProfile(String text) {
		Profile profile = ProfileTable.getProfileByName(text);
		
		if (profile != null) return true;
		
		if (ProfileTable.CreateNewProfile(text)) {
			CommonDataModel.getInstance().profile = ProfileTable.getProfileByName(text);
			WelcomeController.getInstance().setUsername(text);
			return false;
		}
		else {
			return false;
		}
		
		
	}
}
