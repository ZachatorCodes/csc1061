package StringConverter;

public class StringConverter {
	public static int convert(String numString) {
		try {
			return Integer.parseInt(numString);
		} catch (Exception e) {
			throw new NumberFormatException(String.format(
					"? Invalid numeric string detected starting at %s%nValue of \"%s\" is 0", numString, numString));
		}
	}
}
