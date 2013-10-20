package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.helper.Helper;

import database.transaction.CategoryTable;
import database.transaction.SectionTable;

public class CommonDataModel {

	Map<String, List<Integer>> randomQuestions = new HashMap<String, List<Integer>>();
	Map<String, Integer> pivot = new HashMap<String, Integer>();
	
	public CommonDataModel() {
	
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
	
}
