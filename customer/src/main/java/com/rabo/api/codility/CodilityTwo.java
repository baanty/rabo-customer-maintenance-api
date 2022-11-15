package com.rabo.api.codility;

import java.util.HashMap;

public class CodilityTwo {

	static int solution(int[] A) {
		int N = A.length;
		int result = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (A[i] == A[j])
					result = Math.max(result, Math.abs(i - j));
		return result;
	}

	static int maxDistance(int[] A) {

		HashMap<Integer, Integer> elementAndDistanceMapping = new HashMap<>();
		int result = 0;
		int arrayLength = A.length;
		
		for (int i = 0; i < arrayLength ; i++) {

			if (!elementAndDistanceMapping.containsKey(A[i])) {
				elementAndDistanceMapping.put(A[i], i);
			}

			else {
				result = Math.max(result, Math.abs(i - elementAndDistanceMapping.get(A[i])));
			}
		}

		return result;
	}

	public static void main(String[] x) {
		/*
		 * A[0] = 4 A[1] = 6 A[2] = 2 A[3] = 2 A[4] = 6 A[5] = 6 A[6] = 1
		 */
		System.out.println(solution(new int[] { 4, 6, 2, 2, 6, 6, 1 }));
		System.out.println(maxDistance(new int[] { 4, 6, 2, 2, 6, 6, 1 }));
	}
}
