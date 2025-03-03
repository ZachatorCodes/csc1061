package AccountsPayableModification;

// CSC161 Employee.java
// Employee abstract superclass that implements Payable.

public abstract class Employee implements Payable {
	// NOTE: I removed final variable to make code work
	private /* final */ String firstNameField;
	private /* final */ String lastNameField;
	private /* final */ String socialSecurityNumberField;

	// constructor
	public Employee(String firstName, String lastName, String socialSecurityNumber) {
		setFirst(firstName);
		setLast(lastName);
		this.socialSecurityNumberField = socialSecurityNumber;
	}

	public void setFirst(String firstName) {
		if (firstName == null) {
			this.firstNameField = null;
		}
		else if (firstName.isEmpty()) {
			this.firstNameField = null;
		}
		else {
			this.firstNameField = firstName;
		}
	}

	public void setLast(String lastName) {
		if (lastName == null) {
			this.lastNameField = null;
		}
		else if (lastName.length() <= 0) {
			this.lastNameField = null;
		}
		else {
			this.lastNameField = lastName;
		}
	}

	// return first name
	public String getFirstName() {
		return this.firstNameField;
	}

	// return last name
	public String getLastName() {
		return this.lastNameField;
	}

	// return social security number
	public String getSocialSecurityNumber() {
		return socialSecurityNumberField;
	}

	// return String representation of Employee object
	@Override
	public String toString() {
		return String.format("%s %s | %s", firstNameField, lastNameField, socialSecurityNumberField);
	}
	
	public double getPaymentAmount() {
		return earnings();
	}
	
	// Note: We do not implement Payable method getPaymentAmount here so
	// this class must be declared abstract to avoid a compilation error.
	abstract double earnings();

} // end abstract class Employee

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
