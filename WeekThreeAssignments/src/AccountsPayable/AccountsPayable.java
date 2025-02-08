package AccountsPayable;

// AccountsPayable.java
// Tests class Payable class hierarchy.

public class AccountsPayable {
	public static void main(String[] args) {
		// create six-element Payable array
		Payable[] payableObjects = new Payable[4];

		// populate array with objects that implement Payable
		payableObjects[0] = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
		payableObjects[1] = new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
		payableObjects[2] = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);
		payableObjects[3] = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);

		System.out.println("Invoices and Employees processed polymorphically:\n");

		// generically process each element in array payableObjects
		for (Payable currentPayable : payableObjects) {
			// output currentPayable and its appropriate payment amount
			System.out.printf("%s \n", currentPayable.toString());

			// special processing for instance of class BasePlusCommissionEmployee
			if (currentPayable instanceof BasePlusCommissionEmployee) {
				// remember: need to downcast Payable reference to BasePlusCommissionEmployee
				// reference
				// print new base salary with 10%% increase
				System.out.printf("new base salary with 10%% increase is: ");
			} // end if

			System.out.printf("%s: $%,.2f\n\n", "payment due", currentPayable.getPaymentAmount());
		} // end for
	} // end main
} // end class AccountsPayable
