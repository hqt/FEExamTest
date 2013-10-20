package com.controller;

import java.awt.EventQueue;
import java.util.List;
import java.util.Locale.Category;

import com.model.CommonDataModel;
import com.view.WelcomeView;

import database.transaction.SectionTable;

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
		CommonDataModel model = CommonDataModel.getInstance();
		
		List<String> categories = SectionTable.selectAllSections();
		frame.cbxQuestionPack.removeAllItems();
		frame.cbxQuestionPack.addItem("Random 50 General Questions");
		
		for (String category : categories) {
			frame.cbxQuestionPack.addItem(category);
		}
		
		
		setUsername(model.profile.getName());
		showView();
	}
	
	public static void main(String args[]) {
		WelcomeController wc = new WelcomeController();
		wc.load();
	}

	public void setUsername(String text) {
		frame.lblHello.setText("Hello " + text);
	}
}

