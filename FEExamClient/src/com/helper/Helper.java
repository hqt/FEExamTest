package com.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helper {

	/**
	 * using Knuth Shufftle algorithm random numbers of range [0,n)
	 */
	public static List<Integer> Shuffle(int n) {
		int[] shufftle = new int[n];
		for (int i = 0; i < n; i++)	shufftle[i] = i;

		// using Knuth Shufftle algorithm
		Random random = new Random();	// not good. but optimize
		List<Integer> res = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++) {
			// random = new Random();
			int k = random.nextInt(i + 1);
			res.add(k);
		}
		return res;
	}
}
