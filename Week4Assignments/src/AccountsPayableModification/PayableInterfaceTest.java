package AccountsPayableModification;

// CSC161 PayableInterfaceTest.java
// Payable interface test program processing Invoices and 
// Employees polymorphically.
public class PayableInterfaceTest {
	public static void main(String[] args) {
		// create four-element Payable array
		Payable[] payableObjects = new Payable[4];
		String emptyString = new String();

		try {
			// populate array with objects that implement Payable
			payableObjects[0] = new Invoice("01234", "seat", 2, 375.00);
			payableObjects[1] = new Invoice("56789", "tire", 4, 79.95);
			payableObjects[2] = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
			payableObjects[3] = new SalariedEmployee("Lisa", "Barnes", "888-88-8888", 1200.00);

			System.out.println("Invoices and Employees processed polymorphically:");

			// generically process each element in array payableObjects
			for (Payable currentPayable : payableObjects) {
				// output currentPayable and its appropriate payment amount
				System.out.printf("%n%s %n%s: $%,.2f%n", currentPayable.toString(), // could invoke implicitly
						"payment due", currentPayable.getPaymentAmount());
			}

			System.out.println("_________________________________________________________");
			System.out.println("** 293 --- TESTING INVALID CALLS TO Employee METHODS ---");
			System.out.println("_________________________________________________________");

			SalariedEmployee employee1 = (SalariedEmployee) payableObjects[3];
			try {
				employee1.setFirst(null);
			} catch (NullPointerException npe) {
				System.out.println("**?? Error: \"setFirst\" " + "resulted in NullPointerException");
			}
			if (employee1.getFirstName() != null)
				System.out.println("**?? Error: \"getFirst\" " + "did not return a null value");
			try {
				employee1.setLast(null);
			} catch (NullPointerException npe) {
				System.out.println("**?? Error: \"setLast\" " + "resulted in NullPointerException");
			}

			if (employee1.getLastName() != null)
				System.out.println("**?? Error: \"getLast\" " + "did not return a null value");

			System.out.println("Try to create Employee objects with non-acceptable initial values.");

			SalariedEmployee employee2 = new SalariedEmployee(emptyString, "Smith", "1234", 1024.00);

			if (employee2.getFirstName() != null)
				System.out.println("**?? After construction: \"getFirstName\" " + "did not return a correct value");
			if (!employee2.getSocialSecurityNumber().equals("1234"))
				System.out.println(
						"**?? After construction: \"getSocialSecurityNumber\" " + "did not result in correct value");

			System.out.println("**Creating <null> Baker");
			Employee employee3 = null;
			try {
				employee3 = (Employee) new SalariedEmployee(null, "Baker", "3344", 1500.00);
			} catch (NullPointerException ex) {
				System.out.println(
						"**?? Error in constructor: null " + "\"first name\" resulted in NullPointerException");
			}
			if (employee3 != null) {

				if (employee3.getFirstName() != null)
					System.out.println("**?? After construction: \"getFirstName\" " + "did not return a null value");
				if (employee3.getPaymentAmount() != 1500.0)
					System.out.println(
							"**?? After construction: \"getMonthlySalary\"  " + "did not result in the value set");
			}

		} catch (Throwable th) {
			/*
			 * The application under test should not throw any Exceptions or Errors. The use
			 * of Exceptions will not be studied until a later lesson, but they are
			 * nevertheless an inescapable part of the Java programming system.
			 */
			System.err.println("**?? An exception or error has been thrown");
			System.err.println("**?? The exception is: " + th.toString());
			System.err.println("**?? The source of the error can be found " + "in this stacktrace:");
			th.printStackTrace(System.err);

		}
	} // end main

} // end class PayableInterfaceTest

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/
