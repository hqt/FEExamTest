package com.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.CommonDataModel;

import database.config.Config;
import database.helper.DBHelper;
import database.model.Question;
import database.model.SessionQuestion;
import database.model.TestSession;
import database.transaction.CategoryTable;
import database.transaction.SessionQuestionTable;
import database.transaction.SesstionTestTable;

public class Helper {

	public static List<Integer> Shufftle(int n) {

		List<Integer> shufftle = new ArrayList<Integer>();
		for (int i = 0; i < n; i++)	{
			shufftle.add(i);
		}
		return Shufftle(shufftle);
	}

	/**
	 * using Knuth Shufftle algorithm random numbers of range [0,n)
	 * using Java Generic
	 */
	public static <T> List<T> Shufftle(List<T> list) {
		int n = list.size();

		for (int i = 0; i < n; i++) {
			// random number between i and N-1
			int k = i + (int) (Math.random() * (n - i));

			// swap k and i
			T tmp = list.get(i);
			list.set(i, list.get(k));
			list.set(k, tmp);
		}

		return list;
	}

	public static List<Question> getAllQuestionsByIds(String tblName, List<Integer> ids) {
		List<Question> res = new ArrayList<Question>();
		for (Integer id : ids) {
			Question question = CategoryTable.getQuestionById(tblName, id);
			res.add(question);
		}
		return res;
	}
	
	public static void SaveCurrentSessionToDatabase() {
		// create session
		TestSession session = new TestSession(0, new Date(), CommonDataModel.getInstance().profile.getIdProfile());
		SesstionTestTable.CreateTestSesstion(session);
		int sessionId = DBHelper.getLastIndexOfTable(Config.DATABASE_TESTSESSION_TBL);
		
		for (int i = 0; i < CommonDataModel.getInstance().questionList.size(); i++) {
			Question q = CommonDataModel.getInstance().questionList.get(i);
			SessionQuestion sessionQuestion = new SessionQuestion(0, sessionId, q.getSection(),
					q.getId(), CommonDataModel.getInstance().questionResult.get(i));
			SessionQuestionTable.CreateNewSesstionQuestion(sessionQuestion);
		}
	}
}
