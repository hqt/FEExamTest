package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.view.AddQuestionPanel;

import database.helper.IOUtils;
import database.model.Question;
import database.transaction.CategoryTable;
import database.transaction.SectionTable;

public class AddQuestionController {

	public AddQuestionPanel frame;
	public List<Question> questionList;
	public List<String> sectionList;

	public AddQuestionController(AddQuestionPanel frame) {
		this.frame = frame;
		questionList = new ArrayList<Question>();
		sectionList = SectionTable.selectAllSections();
	}

	public boolean addWord() {
		// textfield
		String question = frame.questiontxt.getText();
		String ansa = frame.ansatxt.getText();
		String ansb = frame.ansbtxt.getText();
		String ansc = frame.ansbtxt.getText();
		String ansd = frame.ansbtxt.getText();
		String anse = frame.ansbtxt.getText();
		String explain = frame.explaintxt.getText();
		
		// answer
		int ans = -1;
		try {
			ans = Integer.parseInt(frame.answertxt.getText());
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Answer should be an integer");
			frame.answertxt.setText("");
			return false;
		}
		if (ans < 0 || ans > 4) {
			JOptionPane.showMessageDialog(null, "Answer should be in range 1 to 4"); 
			frame.answertxt.setText("");
			return false;
		}
		
		// get image
		byte[] questionimg = IOUtils.getBinaryFromFile(frame.imgquestiontxt.getText());
		byte[] imga = IOUtils.getBinaryFromFile(frame.imgatxt.getText());
		byte[] imgb = IOUtils.getBinaryFromFile(frame.imgbtxt.getText());
		byte[] imgc = IOUtils.getBinaryFromFile(frame.imgctxt.getText());
		byte[] imgd = IOUtils.getBinaryFromFile(frame.imgdtxt.getText());
		byte[] imge = IOUtils.getBinaryFromFile(frame.imgetxt.getText());
		byte[] imgexp = IOUtils.getBinaryFromFile(frame.imgexplaintxt.getText());
		
		// get category
		int index = frame.cmbSections.getSelectedIndex();
		String section = sectionList.get(index);
		
		Question q = new Question(question, ansa, ansb, ansc, ansd, anse, 
				questionimg, imga, imgb, imgc, imgd, imge, explain, imgexp, ans, section, 5);
		
		questionList.add(q);
		resetFields();
		
		return true;
	}

	public boolean addToDatabase() {
		for (Question question : questionList) {
			System.out.println(question.getSection());
			boolean result = CategoryTable.AddEntity(question.getSection(), question);
			if (!result) {
				JOptionPane.showMessageDialog(null, "Cannot add all entities to database");
				return false;
			}
		}
		JOptionPane.showMessageDialog(null, "Successfully add all entities into database");
		return true;
	}
	
	private void resetFields() {
		frame.ansatxt.setText("");
		frame.ansbtxt.setText("");
		frame.ansctxt.setText("");
		frame.ansdtxt.setText("");
		frame.ansetxt.setText("");
		frame.questiontxt.setText("");
		frame.explaintxt.setText("");
		frame.answertxt.setText("");
		
		/////////////
		frame.imgatxt.setText("");
		frame.imgbtxt.setText("");
		frame.imgctxt.setText("");
		frame.imgdtxt.setText("");
		frame.imgetxt.setText("");
		frame.imgquestiontxt.setText("");
		frame.imgexplaintxt.setText("");
		
	}

}
