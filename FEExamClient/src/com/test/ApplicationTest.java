package com.test;

import database.transaction.ProfileTable;
import database.transaction.SessionQuestionTable;
import database.transaction.SesstionTestTable;

public class ApplicationTest {
	
	public static void main(String[] args) {
	
		
		//ProfileTable.createProfileTable();
		//ProfileTable.CreateNewProfile("FPT Student");
		
		SessionQuestionTable.createSesstionQuestionTable();
		SesstionTestTable.createTestSesstionTable();
	}
	

}
