package com.view;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;

import com.controller.ProfileController;
import com.model.CommonDataModel;

import database.model.Question;

public class QuestionComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public QuestionComponent(final int index, Question question) {
		setLayout(new MigLayout());
		
		JLabel lblQuestionText = new JLabel(question.getQuestion());
		add(lblQuestionText, "wrap");

		JLabel lblQuestionImage = new JLabel("Image");
		add(lblQuestionImage, "wrap");

		ButtonGroup gb = new ButtonGroup();
		for (int i = 0; i < question.numOfSelections; i++) {
			final int answerIndex = i;
			JRadioButton rdbtnRdanswer = new JRadioButton(question.selections[i]);
			add(rdbtnRdanswer, "wrap");
			rdbtnRdanswer.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					CommonDataModel.getInstance().processResult(index, answerIndex);
				}
			});
			
			gb.add(rdbtnRdanswer);
			
			if (question.imgSelections.get(i) != null && question.imgSelections.get(i).length != 0) {
				JLabel lblAnswerimage = new JLabel("AnswerImage");
				add(lblAnswerimage, "wrap");
			}
			
			
		}
	}
}
