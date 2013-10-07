package com.controller;

import java.util.List;

import database.model.Question;

public class TestController {
	List<Question> questionList;
	
	public TestController(List<Question> questionList) {
		this.questionList = questionList;
	}
	
	public List<Question> getQuestionList() { return questionList; }
}
