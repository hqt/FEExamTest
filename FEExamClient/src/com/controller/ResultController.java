package com.controller;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import com.model.CommonDataModel;
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
		//List<Integer> questionResult = CommonDataModel.getInstance().questionResult;
		List<Integer> questionResult = new ArrayList<Integer>();
		questionResult.add(100);
		questionResult.add(0);
		questionResult.add(0);
		questionResult.add(100);
		questionResult.add(100);
		
		
		int total = 0;
		for (Integer mark : questionResult) {
			total += mark;
		}
		frame.lblYourScore.setText("Your score: " + (total / 100) + "/" + questionResult.size());
		frame.lblUsername.setText(CommonDataModel.getInstance().profile.getName());
		
		showView();
		
	}
}
