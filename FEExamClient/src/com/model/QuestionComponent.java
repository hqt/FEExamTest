package com.model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

import database.model.Question;

public class QuestionComponent extends JComponent {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public QuestionComponent(final int index, Question question, boolean isTest) {
		setLayout(new MigLayout());

		JLabel lblQuestionText = new JLabel(question.getQuestion());
		add(lblQuestionText, "wrap");

		// display question image if exist
		if (question.getImg() != null && question.getImg().length != 0) {

		}

		// display all answers that user can select
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

			// display image if exist
			if (question.imgSelections.get(i) != null && question.imgSelections.get(i).length != 0) {
				JLabel lblAnswerimage = new JLabel("AnswerImage");
				add(lblAnswerimage, "wrap");
			}
		}

		// if this is a review. show solution
		if (!isTest) {
			add(new JLabel("Answer"), "wrap");
			add(new JLabel("Explain"), "wrap");
			String explain = question.getExplanation();
			if (explain != null && explain.length() != 0) {
				add(new JLabel(explain), "wrap");
			}
			else {
				add(new JLabel("View again reference book for more detail"), "wrap");
			}
			if (question.getImgexp() != null && question.getImgexp().length != 0) {
				add(new JLabel("Explain Image"), "wrap");
			}
		}

		// decorator by seperate line
		add(new JSeparator(SwingConstants.HORIZONTAL), "growx, wrap");
	}
}
