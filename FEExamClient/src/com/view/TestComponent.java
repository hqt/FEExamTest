package com.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import com.controller.TestController;

import database.model.Question;

public class TestComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	TestController controller;
	
	
	public TestComponent(TestController controller) {
		this.controller = controller;
		List<Question> lst = controller.getQuestionList();
		setLayout(new MigLayout());
		setOpaque(false);
		
		// building list of questions into frame
		for (int i = 1; i <= lst.size(); i++) {
			Question question = lst.get(i-1);
			JLabel jlbl1 = new JLabel("Question " + i);
			JLabel questionlbl = new JLabel(question.getQuestion());
			JButton[] ansBtns;
		}
		
	}
}
