package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.model.Question;
import database.transaction.CategoryTable;
import database.transaction.SectionTable;


public class MainController {

	Map<String, List<Question>> database = new HashMap<String, List<Question>>();
	List<String> tblNames;
	int[] tblNamesIndex;
	
	public MainController() {
		// load all questions into memory
		tblNames =  SectionTable.selectAllSections();
		tblNamesIndex = new int[tblNames.size()];	// all values both start from zero
		for (String tblName : tblNames) {
			List<Question> questions = CategoryTable.GetAllEntities(tblName);
			database.put(tblName, questions);
		}
		
		/**
		 * Load data.
		 * View a = new View();
		 * a.listbox.add(assadasdasd);
		 * a.va = mn;
		 * 
		 * View -> va ViewA, vb ViewB
		 * 
		 */
	}
	
}
