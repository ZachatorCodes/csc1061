package MyBST;

// BinarySearchTest.java
// Use binary search to locate an item in an array.
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchTest {
	// perform a binary search on the data
	public static int binarySearch(int[] data, int low, int high, int key) {
		int middle = (low + high + 1) / 2; // middle element
		int location = -1; // return value; -1 if not found

		if (high >= low) {
			if (data[middle] == key) {
				return middle;
			}
			if (data[middle] > key) {
				return binarySearch(data, 0, middle - 1, key);
			} else if (data[middle] < key) {
				return binarySearch(data, middle + 1, high, key);
			}
		}
		
		return -1;

	} // end method binarySearch

	// method to output certain values in array
	private static String remainingElements(int[] data, int low, int high) {
		StringBuilder temporary = new StringBuilder();

		// append spaces for alignment
		for (int i = 0; i < low; i++)
			temporary.append("   ");

		// append elements left in array
		for (int i = low; i <= high; i++)
			temporary.append(data[i] + " ");

		return String.format("%s%n", temporary);
	} // end method remainingElements

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		SecureRandom generator = new SecureRandom();

		int[] data = new int[15]; // create array

		for (int i = 0; i < data.length; i++) // populate array
			data[i] = 10 + generator.nextInt(90);

		Arrays.sort(data); // binarySearch requires sorted array
		System.out.printf("%s%n%n", Arrays.toString(data)); // display array

		// get input from user
		System.out.print("Please enter an integer value (-1 to quit): ");
		int searchInt = input.nextInt();

		// repeatedly input an integer; -1 terminates the program
		while (searchInt != -1) {
			// perform search
			int location = binarySearch(data, 0, data.length - 1, searchInt);

			if (location == -1) // not found
				System.out.printf("%d was not found%n%n", searchInt);
			else // found
				System.out.printf("%d was found in position %d%n%n", searchInt, location);

			// get input from user
			System.out.print("Please enter an integer value (-1 to quit): ");
			searchInt = input.nextInt();
		}
	} // end main
} // end class BinarySearchTest
