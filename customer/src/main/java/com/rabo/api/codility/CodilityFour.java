package com.rabo.api.codility;

import java.util.*;

class GFG {

	static int evenSumK(int[] A, int K) {

		int N = A.length;

		if (K > N) {
			return -1;
		}
		int maximumSumPossible = 0;
		ArrayList<Integer> evenNumbers = new ArrayList<Integer>();

		ArrayList<Integer> oddNumbers = new ArrayList<Integer>();

		for (int i = 0; i < N; i++) {
			if (A[i] % 2 == 1) {
				oddNumbers.add(A[i]);
			} else {
				evenNumbers.add(A[i]);
			}
		}

		Collections.sort(oddNumbers);
		Collections.sort(evenNumbers);

		int index = evenNumbers.size() - 1;
		int jIndex = oddNumbers.size() - 1;

		while (K > 0) {

			if (K % 2 == 1) {

				if (index >= 0) {
					maximumSumPossible += evenNumbers.get(index);
					index--;
				} else {
					return -1;
				}
				K--;
			} else if (index >= 1 && jIndex >= 1) {

				if (evenNumbers.get(index) + evenNumbers.get(index - 1) <= oddNumbers.get(jIndex)
						+ oddNumbers.get(jIndex - 1)) {
					maximumSumPossible += oddNumbers.get(jIndex) + oddNumbers.get(jIndex - 1);
					jIndex -= 2;
				} else {
					maximumSumPossible += evenNumbers.get(index) + evenNumbers.get(index - 1);
					index -= 2;
				}
				K -= 2;
			} else if (index >= 1) {
				maximumSumPossible += evenNumbers.get(index) + evenNumbers.get(index - 1);
				index -= 2;
				K -= 2;
			} else if (jIndex >= 2) {
				maximumSumPossible += oddNumbers.get(jIndex) + oddNumbers.get(jIndex - 1);
				jIndex -= 2;
				K -= 2;
			}
		}
		return maximumSumPossible;
	}

	// Driver code
	public static void main(String[] args) {
		int arr[] = { 2, 4, 10, 3, 5 };

		int K = 3;

		System.out.println(evenSumK(arr, K));
	}
}

// This code is contributed by offbeat
