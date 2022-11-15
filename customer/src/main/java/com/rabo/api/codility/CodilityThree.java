package com.rabo.api.codility;

public class CodilityThree {

	
	public static int getNumberOfIntegersWhichAreProductOfTwoCOnsIntegers(int A, int B) {
		
		int count = 0;
		
		for (int index = A; index <= B ; index++) {
			
			for ( int innerIndex = 1 ; innerIndex <= index ; innerIndex++) {
				
				if ( innerIndex * ( innerIndex + 1) == index  ) {
					count++;
					break;
				}
			}
		}
		return count;
	}
	
	
	public static void main(String[] x) {
		System.out.println(getNumberOfIntegersWhichAreProductOfTwoCOnsIntegers(100, 10000));
	}
}
