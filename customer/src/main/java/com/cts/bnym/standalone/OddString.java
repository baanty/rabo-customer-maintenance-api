package com.cts.bnym.standalone;

import java.util.ArrayList;
import java.util.List;

class OddString {
	/*
	 * Complete the 'solve' function below.
	 *
	 * The function is expected to return a STRING. The function accepts following
	 * parameters: 1. INTEGER m 2. STRING_ARRAY s
	 */
	public static String solve(int m, List<String> s) {
		// fill this function
		List<Boolean> isStringEven = new ArrayList<Boolean>();
		int temp = 0;
		int oddCount = 0;
		Boolean isEven = false;

		for (int i = 0; i < s.size(); i++) {
			isEven = false;
			
			for (int j = 0; j < s.get(i).length(); j++) {
				temp = (int) s.get(i).charAt(j);
				
				if (temp % 2 == 0) {
					isEven = true;
				}
			}
			isStringEven.add(isEven);
		}
		
		for (Boolean b : isStringEven) {
			
			if (!b)
				oddCount++;
		}
		
		if (oddCount % 2 != 0)
			return "ODD";
		else
			return "EVEN";
	}
}