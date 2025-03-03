package PatternMatching;

import java.util.Scanner;

public class PatternMatching {

	public static void main(String[] args) {
		// Create new Scanner Object
		Scanner scnObj = new Scanner(System.in);

		// Prompt for and capture string 1
		System.out.print("Enter a string s1: ");
		String s1 = scnObj.nextLine().toLowerCase();

		// Prompt for and capture string 2
		System.out.print("Enter a string s2: ");
		String s2 = scnObj.nextLine().toLowerCase();

		// Move longer string to s1 if needed
		if (s2.length() > s1.length()) {
			String placeholder = s1;
			s1 = s2;
			s2 = placeholder;
		}

		// Solution is O(n) because it only uses a single for loop
		boolean found = false;
		for (int i = 0; i < s1.length() - s2.length() + 1; i++) {
			if (s1.substring(i, i + s2.length()).equals(s2)) {
				found = true;
				System.out.printf("matched at character %d", i + 1);
				break;
			}
		}

		if (!found)
			System.out.println("no match found");

	}

}
