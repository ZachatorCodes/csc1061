package StringConverter;

public class StringConverter {
	public static int convert(String numString) {
		if (numString.charAt(0) == '-') {
			return (-1 * convertHelper(numString.substring(1), 0));
		}
		if (!Character.isDigit(numString.charAt(0))) {
			throw new NumberFormatException(
				String.format("? Invalid numeric string detected starting at %s%nValue of \"%s\" is 0", numString, numString)
			);
		}
		return (convertHelper(numString, 0));
	}

	public static int convertHelper(String numString, int power) {
		if (numString.length() == 0) {
			return 0;
		}
		int num = Integer.parseInt(String.valueOf(numString.charAt(numString.length() - 1)));
		int value = num * (int) Math.pow(10, power);
		return value + convertHelper(numString.substring(0, numString.length() - 1), power + 1);
	}
}
