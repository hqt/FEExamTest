package com.controller;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.model.CommonDataModel;
import com.model.QuestionComponent;
import com.view.ReviewView;

import database.model.Question;

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
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void load() {
		// create information for attemp view
		Date date = new Date();
		frame.lblDate.setText((new SimpleDateFormat()).format(date));
		frame.lblUsername.setText(CommonDataModel.getInstance().profile.getName());
		frame.lblSection.setText("Review again all questions");

		List<Question> questions = CommonDataModel.getInstance().questionList;

		String[] listData = new String[questions.size()];
		for (int i = 0; i < questions.size(); i++)
			listData[i] = (i + 1) + "";
		frame.lstQuestions.removeAll();
		frame.lstQuestions.setListData(listData);

		setQuestionListView();

		showView();
	}
	
	private void setQuestionListView() {
		frame.pnlQuestion.removeAll();
		List<Question> questions = CommonDataModel.getInstance().questionList;
		
		for (int i = 0; i < questions.size(); i++) {
			// assign each question to  each component
			frame.pnlQuestion.add(new QuestionComponent(i, questions.get(i), false), "wrap");
		}
	}

}
