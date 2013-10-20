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
	List<String> sections = SectionTable.selectAllSections();
	
	private CommonDataModel() {
	
		// building order or random questions
		// this order will be kept until restart app
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

	/**
	 * return true : can generate questions
	 * return false : cannot (because to low questions)
	 */
	public boolean generateRandomQuestion(int quantity) {
		if (quantity < sections.size()) return false;

		resetFields();
		
		// generate all questions from 0 to nSections - 1
		// each category takes quantity / sections.size()
		int num = quantity / sections.size();
		List<Integer> ids;
		for (int i = 0; i < sections.size(); i++) {
			ids = getRandomOrder(sections.get(i), num);
			questionList.addAll(Helper.getAllQuestionsByIds(sections.get(i), ids));
		}
		// last category. might contains more questions
		int id = sections.size() - 1;
		num = quantity % sections.size();
		ids = getRandomOrder(sections.get(id), num);
		questionList.addAll(Helper.getAllQuestionsByIds(sections.get(sections.size()), ids));

		questionList = Helper.Shufftle(questionList);
		return true;
	}
	
	public void generateQuestionBySection(String sectionName, int num) {
		num = Math.min(num, CategoryTable.getNumberEntities(sectionName));
		resetFields();
		List<Integer> ids = getRandomOrder(sectionName, num);
		questionList = Helper.getAllQuestionsByIds(sectionName, ids);
	}
	
	/**
	 * reset again all fields
	 * maybe because start new session test
	 */
	private void resetFields() {
		questionList = new ArrayList<Question>();
		questionResult = new ArrayList<Integer>();
	}
	
	
}
