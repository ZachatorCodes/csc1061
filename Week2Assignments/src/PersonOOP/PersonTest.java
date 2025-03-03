package PersonOOP;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.System.out;

/**
 * Class to test class Person.
 */
public class PersonTest
{
private static final double PERSON1_AGE = 30;
private static final double PERSON2_AGE = 12;

private static final String ERROR_MSG_PREFIX = "**!! Error:";
/**
 * Check that the given person has the given attributes. Used to check that
 * displayValues does not have unexpected side effects.
 * <p>
 * @param given			  the given Person object (which is not null)
 * @param firstName     the value expected to be returned by getFirst
 * @param lastName		the value expected to be returned by getLast
 * @param age		  the value expected to be returned by method getAge()
 */
private void checkPerson(Person given, String firstName, String lastName,
		double age)
{
	boolean OK = true;

	/* Accumulate the start of the toString value expected. */
	String str1 = given.getClass().getName();

	/* Identify what can be identified of the person. */
	out.print("**Checking the nominal person ");
	out.print(firstName == null ? "<null>" :
			firstName.isEmpty() ? "<empty>" : firstName);
	out.print(" ");
	out.print(lastName == null ? "<null>" :
			lastName.isEmpty() ? "<empty>" : lastName);
	out.println();

	/* If both the actual and the expected first name are null, then OK,
	   otherwise if the expected value is null (and thus the actual found value
	   was not) report this, otherwise check to see if they are equal, and if
	   not, report that. */
	String firstFromObject = null;
	try
	{
		firstFromObject = this.<String>getField(given, "firstNameField");
	} catch( NoSuchFieldException | IllegalArgumentException |
			IllegalAccessException | SecurityException ex ) {}

	if( firstFromObject != null || firstName != null )
		if( firstName == null )
		{
			OK = false;
			out.printf(ERROR_MSG_PREFIX + " First name expected to be null," +
					" found to be \"%s\"%n", firstFromObject);
		} else if( !firstName.equals(firstFromObject) )
		{
			OK = false;
			out.printf(ERROR_MSG_PREFIX + " First name expected to be \"%s\"," +
					" found to be \"%s\"%n", firstName, firstFromObject);
		}

	String lastFromObject = null;
	try
	{
		lastFromObject = this.<String>getField(given, "lastNameField");
	} catch( NoSuchFieldException | IllegalArgumentException |
			IllegalAccessException | SecurityException ex )
	{
	}

	/* If both the actual and the expected last name are null, then OK,
	   otherwise if the expected value is null (and thus the actual found value
	   was not) report this, otherwise check to see if they are equal, and if
	   not, report that. */
	if( lastFromObject != null || lastName != null )
		if( lastName == null )
		{
			OK = false;
			out.printf(ERROR_MSG_PREFIX + " Last name expected to be null," +
					" found to be \"%s\"%n", lastFromObject);
		} else if( !lastName.equals(lastFromObject) )
		{
			OK = false;
			out.printf(ERROR_MSG_PREFIX + " Last name expected to be \"%s\"," +
					" found to be \"%s\"%n", lastName, lastFromObject);
		}
	String str2 = null;
	try
	{
		/* Accumulate more of the toString value expected. */
		Method method = given.getClass().getMethod("hashCode");
		if( method.getDeclaringClass() == Object.class )
			str2 = method.invoke(given).toString();
	} catch( NoSuchMethodException | IllegalArgumentException |
			InvocationTargetException | IllegalAccessException |
			SecurityException | ClassCastException ex ) {}


	if( !(str1 == null || str2 == null) )
		str1 += "\u0040" + Integer.toHexString(Integer.parseInt(str2));

	if( OK )
	{
		out.print("**");
		out.print(firstName == null ? "<null>" :
				firstName.isEmpty() ? "<empty>" : firstName);
		out.print(" ");
		out.print(lastName == null ? "<null>" :
				lastName.isEmpty() ? "<empty>" : lastName);
		out.println(" is OK");
	}

	/* Check that displayValue gives an acceptable result. */
	out.println("**The result of \"displayValues\" for that person");
	given.displayValues();

	/* Print message only if the toString values to not match. */
	if( str2 != null &&
			!(str1 + "\u0020" + firstName +
			" " + lastName).equals(given.toString()) )
				out.println("**?? toString value \"" + given.toString() +
						"\" incorrect");
}

/**
 * Get the value of a field of the object without using the defined getter. Use
 * reflection to reach straight into the object.
 *
 * @param <T>		the type of the field
 * @param object	the object containing the field
 * @param name		the name of the field
 * @return			the value found
 * @throws NoSuchFieldException     if there is no such field
 * @throws IllegalArgumentException	should not happen
 * @throws IllegalAccessException   should not happen
 * @throws SecurityException        should not happen
 */
@SuppressWarnings("unchecked")
private <T> T getField(Object object, String name) throws NoSuchFieldException,
		IllegalArgumentException, IllegalAccessException
{
	/* Get the class object for the instance, and create the variable to hold
	   the result of accessing the field. */
	Class<?> clazz = object.getClass();
	T value = null;
	try
	{
		/* Get the field object for the declared field, make sure it is
		   accessible, and then grab the value of the field. */
		Field field = clazz.getDeclaredField(name);
		field.setAccessible(true);
		value = (T) field.get(object);
	} catch( IllegalArgumentException | IllegalAccessException |
			NoSuchFieldException | SecurityException ex )
	{
		/* The only exception expected to happen is that the field does not have
		   the correct name. */
		out.println(
				"**?? Cannot find field: " + ex.getMessage());
		throw ex;
	}

	/* Return the value found. */
	return value;
}

/**
 * Do the work for the PersonTest.
 */
private void run()
{
	/* Create some empty strings which are not the constant "". */
	String emptyString1 = new String();
	String emptyString2 = new String();
	String emptyString3 = new String();

	/* Check the compiler has not done too much optimization. */
	assert !((Object) emptyString1 == (Object) emptyString2 ||
			(Object) emptyString1 == (Object) "" ||
			(Object) emptyString2 == (Object) emptyString3 ||
			(Object) emptyString2 == (Object) "" ||
			(Object) emptyString3 == (Object) emptyString1 ||
			(Object) emptyString3 == (Object) "") :
			"**?? Empty test strings not unique!";

	/* This try block is intended to catch any exceptions that may occur. Of
	   course, there should not be any. */
	try
	{
		/* This is required to ensure that the output directed to the err file
		   appears in the correct sequence with the output directed to the out
		   file. Otherwise the two files are independent, and the output does
		   not necessarily appear in the correct time order. */
		System.setErr(out);

		out.println("**CSC-1061 Homework 2");
		out.println("_________________________________________________________");
		out.println("** 231 --- TESTING CREATING VALID Person OBJECTS ---");
		out.println("_________________________________________________________");

		/* Instantiate two valid Person objects.  */
		out.println("**Creating two valid persons");
		Person person1 =
				new Person("Bob", "Jones", PERSON1_AGE);
		checkPerson(person1, "Bob", "Jones", PERSON1_AGE);

		Person person2 =
				new Person("Susan", "Bakker", PERSON2_AGE);
		checkPerson(person2, "Susan", "Bakker", PERSON2_AGE);

		out.println("**Changing first name of Bob Jones to Robert");
		try
		{
			person1.setFirst("Robert");
		} catch( NullPointerException npe )
		{
			out.println("**?? Erroneous \"setFirst\": " +
					"resulted in NullPointerException");
		}
		if( person1.getFirst() == null ||
				!person1.getFirst().equals("Robert") )
			out.println("**?? Erroneous \"getFirst\": " +
					"did not result in returning expected value");
		checkPerson(person1, "Robert", "Jones", PERSON1_AGE);

		out.println("**Changing last name of Susan Bakker to Baker");
		try
		{
			person2.setLast("Baker");
		} catch( NullPointerException npe )
		{
			out.println("**?? Erroneous \"setLast\": " +
					"resulted in NullPointerException");
		}
		if( person2.getLast() == null ||
				!person2.getLast().equals("Baker") )
			out.println("**?? Erroneous \"getLast\": " +
					"did not result in returning expected value");
		checkPerson(person2, "Susan", "Baker", PERSON2_AGE);

		/* Increase person salaries by 10%. */
		out.println();
		out.println("** --- INCREASING Person SALARIES BY 10% ---");
		out.println();

		/* Test that bad Person values cannot be set. */
		out.println("_________________________________________________________");
		out.println("** 293 --- TESTING INVALID CALLS TO Person METHODS ---");
		out.println("_________________________________________________________");

		/* Try to set some non-acceptable values. */
		out.println("**Setting last name of Robert Jones to \"\"");
		person1.setLast(emptyString1);
		checkPerson(person1, "Robert", null, PERSON1_AGE);
		if( person1.getLast() != null )
			out.println(ERROR_MSG_PREFIX + " \"getLast\" " +
					"did not return a null value");

		out.println("**Setting first name of Robert Jones to null");
		try
		{
			person1.setFirst(null);
		} catch( NullPointerException npe )
		{
			out.println(ERROR_MSG_PREFIX + " \"setFirst\" " +
					"resulted in NullPointerException");
		}
		checkPerson(person1, null, null, PERSON1_AGE);
		if( person1.getFirst() != null )
			out.println(ERROR_MSG_PREFIX + " \"getFirst\" " +
					"did not return a null value");

		out.println("**Setting first name of Susan Baker to \"\"");
		person2.setFirst(emptyString2);
		checkPerson(person2, null, "Baker", PERSON2_AGE);
		if( person2.getFirst() != null )
			out.println(ERROR_MSG_PREFIX + " \"getFirst\" " +
					"did not return a null value");

		out.println("**Setting last name of Susan Baker to null");
		try
		{
			person2.setLast(null);
		} catch( NullPointerException npe )
		{
			out.println(ERROR_MSG_PREFIX + " \"setLast\" " +
					"resulted in NullPointerException");
		}
		checkPerson(person2, null, null, PERSON2_AGE);
		if( person2.getLast() != null )
			out.println(ERROR_MSG_PREFIX + " \"getLast\" " +  "did not return a null value");

		out.println("**Setting age of Susan Baker to -10.0");
		person2.setAge(-10.0);
		checkPerson(person2, null, null, 0.0);
		if( person2.getAge() != 0.0 )
			System.out.println(ERROR_MSG_PREFIX + " \"getAge\"  " + 	"did not return a zero value");

		/* Try to create Person objects with non-acceptable initial values. */
		out.println("**Creating \"\" Smith, with age of -500.0");
		Person person3 = new Person(emptyString3, "Smith", -500.0);
		checkPerson(person3, null, "Smith", 0.0);
		if( person3.getFirst() != null )
			out.println("**?? After construction: \"getFirst\" " +
					"did not return a null value");
		if( person3.getAge() != 0.0 )
			out.println(
					"**?? After construction: \"getAge\" " +
					"did not result in zero value");

		out.println("**Creating <null> Baker");
		Person person4 = null;
		try
		{
			person4 = new Person(null, "Baker", 50.0);
		} catch( NullPointerException ex )
		{
			out.println("**?? Error in constructor: null " +
					"\"first name\" resulted in NullPointerException");
		}
		if( person4 != null )
		{
			checkPerson(person4, null, "Baker", 50.0);
			if( person4.getFirst() != null )
				out.println("**?? After construction: \"getFirst\" " +
						"did not return a null value");
			if( person4.getAge() != 50.0 )
				out.println(
						"**?? After construction: \"getAge\"  " +
						"did not result in the correct value set");
		}
	} catch( Throwable th )
	{
		/* The application under test should not throw any Exceptions or Errors.
		   The use of Exceptions will not be studied until a later lesson, but
		   they are nevertheless an inescapable part of the Java programming
		   system. */
		out.println("**?? An exception or error has been thrown");
		out.println("**?? The exception is: " + th.toString());
		out.println("**?? The source of the error can be found " +
				"in this stacktrace:");
		th.printStackTrace(out);
	}
}

/**
 * Main entry point.
 * <p>
 * Execute:</p>
 * <pre>java edu.frontrange.csc240.a2.PersonTest</pre>
 *
 * @param args	unused
 */
public static void main(String[] args)
{
	new PersonTest().run();		// Run the test
}
}
