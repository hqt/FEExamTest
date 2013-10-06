package com.test;

import java.util.List;

import database.model.Question;
import database.transaction.CategoryTable;


public class SimpleTest {
	
	public static void main(String[] args) {
		List<Question> list = CategoryTable.GetAllEntities("database");
		for (Question question : list) {
			System.out.println(question);
		}
		
	}

}
