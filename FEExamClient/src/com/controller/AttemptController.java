package com.controller;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import com.model.CommonDataModel;
import com.view.AttemptView;

import database.model.Question;

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

	public void load(String sectionName) {
		if (sectionName.equals("Random 50 General Questions")) {
			CommonDataModel.getInstance().generateRandomQuestion(50);
		}
		else {
			CommonDataModel.getInstance().generateQuestionBySection(sectionName);
		}
		
		// create information for attemp view
		Date date = new Date();
		frame.lblDate.setText((new SimpleDateFormat()).format(date));
		frame.lblUsername.setText(CommonDataModel.getInstance().profile.getName());
		frame.lblSection.setText(sectionName);

		List<Question> questions = CommonDataModel.getInstance().questionList;
		String[] listData = new String[questions.size()];
		for (int i = 0; i < questions.size(); i++) listData[i] = (i+1) +  "";
		frame.lstQuestions.setListData(listData);
		
		showView();
		
	}

	public void processUserInput() {
		// TODO Auto-generated method stub
		
	}
}
