package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.helper.Helper;

import database.model.Profile;
import database.model.Question;
import database.transaction.CategoryTable;
import database.transaction.ProfileTable;
import database.transaction.SectionTable;

public class CommonDataModel {
	
	private static volatile CommonDataModel controller;
	public Profile profile;
	public List<Question> questionList;
	public List<Integer> questionResult;
	
	public static CommonDataModel getInstance() {
		if (controller == null) {
			synchronized (CommonDataModel.class) {
				if (controller == null) {
					controller = new CommonDataModel();
				}
			}
		}
		return controller;
	}


	Map<String, List<Integer>> randomQuestions = new HashMap<String, List<Integer>>();
	Map<String, Integer> pivot = new HashMap<String, Integer>();
	
	private CommonDataModel() {
	
		// building order or random questions
		// this order will be kept until restart app
		List<String> sections = SectionTable.selectAllSections();
		for (String section : sections) {
			int rows = CategoryTable.getNumberEntities(section);
			List<Integer> shufftles = Helper.Shuffle(rows);
			randomQuestions.put(section, shufftles);
			// building pivot to store current location of each type of question
			pivot.put(section, 0);
		}
		
		if (profile == null) {
			profile = ProfileTable.getProfileByName("FPT Student");
		}
	}
	
	public List<Integer> getRandomOrder(String tblName, int num) {
		List<Integer> orders = randomQuestions.get(tblName);
		int currPivot = pivot.get(tblName);

		List<Integer> res = new ArrayList<Integer>();
		int n = orders.size();
		for (int i = 1; i <= num;i++) {
			int order = (currPivot + i) %  n; 
			res.add(order);
		}
		
		currPivot = (currPivot + num) % n;
		pivot.put(tblName, currPivot);
		
		return res;
	}

	public void generateRandomQuestion(int n) {
		
	}

	public void generateQuestionBySection(String sectionName) {
		questionList = CategoryTable.GetAllEntities(sectionName);
		
	}

	public void processResult(int questionIndex, int answerIndex) {
		
		if (questionList.get(questionIndex).getAns() == (answerIndex + 1)) {
			questionResult.set(questionIndex, 100);
		} else {
			questionResult.set(questionIndex, 0);
		}
		// TODO Auto-generated method stub
		
	}
	
	
}
