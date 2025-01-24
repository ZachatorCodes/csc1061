package CreditCardValidation;

import java.util.Scanner;

public class CreditCardValidation {

	public static void main(String[] args) {
		// Create Scanner object and capture credit card number
		Scanner scnObj = new Scanner(System.in);
		System.out.print("Please enter a credit card number: ");
		long ccNumber = scnObj.nextLong();
		
		// Check if the card is valid through the isValid() method, print if card if true
		if (isValid(ccNumber)) {
			System.out.println("Card Valid");
		}
	}
	
	// Return true if the card number is valid
	public static boolean isValid(long number) {
		// Check validity of length
		if (Long.toString(number).length() < 13 || Long.toString(number).length() > 16) {
			System.out.println("Error: credit card length not valid");
			return false;
		}
		
		// Check validity of prefixes
		int[] prefixes = {4, 5, 6, 37};
		boolean prefixValid = false;
		for (int prefix : prefixes) {
			if (prefixMatched(number, prefix)) {
				prefixValid = true;
			}
		}
		if (prefixValid == false) {
			System.out.println("Error: credit card prefix not valid");
			return false;
		}
		
		// Check validity of digits
		int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
		if (sum % 10 != 0) {
			System.out.println("Error: credit card numbers not valid");
			return false;
		}
	
		// Return true if all tests pass;
		return true;
	}
	
	// Get result from step 2
	public static int sumOfDoubleEvenPlace(long number) {
		int total = 0;
		String[] numArray = Long.toString(number).split("");
		
		for (int i = numArray.length - 2; i >= 0; i -= 2){
			total += getDigit(Integer.parseInt(numArray[i]) * 2);
		}
		
		return total;
	}
	
	// Sum of all even numbers
	public static int sumOfOddPlace(long number) {
		int total = 0;
		String[] numArray = Long.toString(number).split("");
		
		for (int i = numArray.length - 1; i >= 0; i -= 2){
			total += Integer.parseInt(numArray[i]);
		}
		
		return total;
	}
	
	// If number is more than one digit, call to add them
	public static int getDigit(int number) {
		if (number < 10) {
			return number;
		}
		
		String[] digits = Integer.toString(number).split("");
		int total = 0;
		
		for (String digit: digits) {
			total += Integer.parseInt(digit);
		}
		
		return getDigit(total);
	}
	
	// Returns the length in digits of long d
	public static int getSize(long d) {
		return Long.toString(d).length();
	}
	
	// Return the first k digits in number. 
	public static long getPrefix(long number, int k) {
		String prefix = Long.toString(number).substring(0, k);
		return Long.parseLong(prefix);
	}
	
	// Return true if prefix d matches the prefix of number
	public static boolean prefixMatched(long number, int d) {
		return (getPrefix(number, getSize(d)) == d);
	}
}
