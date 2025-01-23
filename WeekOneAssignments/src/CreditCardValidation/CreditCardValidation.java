package CreditCardValidation;

import java.util.Scanner;

public class CreditCardValidation {

	public static void main(String[] args) {
		Scanner scnObj = new Scanner(System.in);
		System.out.print("Please enter a credit card number: ");
		long ccNumber = scnObj.nextLong();
		
		System.out.println(isValid(ccNumber));
	}
	
	// Return true if the card number is valid
	public static boolean isValid(long number) {
		int[] prefixes = {4, 5, 6, 37}; // Array of all prefix options
		long prefix = 0;
		
		for (int pfx: prefixes) {
			if (pfx == 37) {
				prefix = getPrefix(number, 2);
			}
			else {
				prefix = getPrefix(number, 1);
			}
		}
		
		return false;
	}
	
	// Get result from step 2
	public static void sumOfDoubleEvenPlace(long number) {
		
	}
	
	// Sum of all even numbers
	public static int sumOfOddPlace(long number) {
		
	}
	
	// If number is more than one digit, call to add them
	public static int getDigit(int number) {
		
	}
	
	// Returns number of digits in d
	public static int getSize(long d) {
		return String.valueOf(d).length();
	}
	
	// Return the first k numbers in number. 
	public static long getPrefix(long number, int k) {
		return Long.parseLong(String.valueOf(number).substring(0, k));
	}
	
	// Return true if number d is the prefix of number
	public static boolean prefixMatched(long number, int d) {
		
	}
}
