package HangMan;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Zach Schwartz, S03100100
 * @version2025-1-26, CSC-1061 Assignment 1, Exercise 2
 */

public class HangMan {
	public static void main(String[] args) {
		boolean newGame = true;
		while (newGame) {
			String word = getWord();
			runGame(word);
			System.out.print("Do you want to guess another word? Enter y or n > ");
			if (!(getInput().charAt(0) == 'y')) {
				newGame = false;
			}
		}
	}

	public static void runGame(String word) {
		int mistakes = 0;
		String display = "";
		String guesses = "";

		for (int i = 0; i < word.length(); i++) {
			display += "*";
		}

		boolean win = false;
		while (!win) {
			System.out.printf("(Guess) Enter a letter in word %s > ", display);
			char guess = getInput().charAt(0);

			if (display.contains(Character.toString(guess))) {
				System.out.println("\t" + guess + " is already in the word");
			} else if (guesses.contains(Character.toString(guess))) {
				System.out.println("\t" + guess + " has already been guesssed");
			} else if (word.contains(Character.toString(guess))) {
				guesses += guess;
				display = updateDisplay(word, guess, display);
			} else {
				System.out.println("\t" + guess + " is not in the word");
				guesses += guess;
				mistakes++;
			}

			if (display.equals(word)) {
				win = true;
			}
		}
		System.out.printf("The word is %s. You missed %d time(s)\n", word, mistakes);
	}

	// Updates the asterisk display to match correct guesses
	public static String updateDisplay(String word, char guess, String display) {
		char[] chars = display.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			if (guess == (word.charAt(i))) {
				chars[i] = guess;
			}
		}
		return String.valueOf(chars);
	}

	// Gets the input from the user with error handling for empty input
	public static String getInput() {
		Scanner scnObj = new Scanner(System.in);
		String input = scnObj.nextLine().toLowerCase();
		while (input.length() < 1) {
			System.out.print("Error: input empty. Please try again > ");
			input = scnObj.nextLine().toLowerCase();
		}
		return input;
	}

	// Picks and returns a random word from an array of options
	public static String getWord() {
		String[] words = { "river", "whitewater", "kayak", "rafting", "hydraulic", "anchor", "climbing", "bouldering",
				"rappelling", "belay", "camber", "snowboard", "sidehit", "carving", "tamedog", "squeeze", "caving",
				"headlamp", "stalagmites", "stalactites", "backpacking", "superlight", "tent", "jetboil", "filter",
				"fourteener", "mountain", "ascent", "thunder", "summit", };
		Random randObj = new Random();
		String word = words[randObj.nextInt(0, words.length)];
		return word;
	}

}
