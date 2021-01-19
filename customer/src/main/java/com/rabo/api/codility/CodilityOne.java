package com.rabo.api.codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CodilityOne {

	
	public static int getLargestPossibleEvenSumOfKElements(int[] A, int K) {
		int length = A.length;
		
		if ( K > length) {
			return -1;
		}
		
		
		List<Integer> allIntegers = Arrays.stream(A).boxed().collect(Collectors.toList());
		List<Integer> evenIntegers = new ArrayList<Integer>();
		List<Integer> oddIntegers = new ArrayList<Integer>();
		
		Map<Integer, Integer> evenMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> oddMap = new HashMap<Integer, Integer>();
		
		int lindexOfArray = 0 ;
		for ( int element : A) {
			
			if(element % 2 == 0 ) {
				evenIntegers.add(element);
				evenMap.put(lindexOfArray, element);
			}
			
			else {
				oddIntegers.add(element);
				oddMap.put(lindexOfArray, element);
			}
		}
		Collections.sort(allIntegers);
		Collections.sort(evenIntegers);
		Collections.sort(oddIntegers);
		
		Collections.reverse(allIntegers);
		Collections.reverse(evenIntegers);
		Collections.reverse(oddIntegers);
		
		System.out.println(allIntegers);
		System.out.println(evenIntegers);
		System.out.println(oddIntegers);
		
		int sum = 0 ;
		int index = 0;
				
		for (  ; index < K ; index++) {
			sum = sum + allIntegers.get(index);			
		}
		
		int newIndex = 0;
		
		while ( (sum % 2 != 0 ) && ( newIndex < length ) && evenIntegers.size() > 0  ) {
			
			
			if ( allIntegers.get(newIndex) % 2 == 0 ) {
				continue;
			}
			
			sum = sum - allIntegers.get(newIndex);
			sum =  sum + (( evenMap.get(newIndex) == null) ? 0 : evenMap.get(newIndex));
			newIndex++;
		}

		
		
		return ( sum == 0 || sum % 2 != 0 )? -1 : sum;
	}
	
	
	public static void main(String[] x) {
		System.out.println(getLargestPossibleEvenSumOfKElements(new int[]{4,9,8,2,6}, 3));
		System.out.println(getLargestPossibleEvenSumOfKElements(new int[]{5,6,3,4,2}, 5));
		System.out.println(getLargestPossibleEvenSumOfKElements(new int[]{7,7,7,7,7}, 1));
		System.out.println(getLargestPossibleEvenSumOfKElements(new int[]{10000}, 2));
		System.out.println(getLargestPossibleEvenSumOfKElements(new int[]{2,3,3,5,5}, 3));
	}
}
