package com.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import database.model.Question;
import database.transaction.CategoryTable;

public class Helper {

	public static List<Integer> Shuffle(int n) {

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

		// using Knuth Shufftle algorithm
		Random random;
		for (int i = 0; i < n; i++) {
			random = new Random();
			int k = random.nextInt(i + 1);

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
}
