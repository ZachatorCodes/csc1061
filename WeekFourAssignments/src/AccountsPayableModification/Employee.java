package AccountsPayableModification;

// CSC161 Employee.java
// Employee abstract superclass that implements Payable.

public abstract class Employee implements Payable
{
   private final String firstNameField;
   private final String lastNameField;
   private final String socialSecurityNumberField;

   // constructor
   public Employee(String firstName, String lastName, 
      String socialSecurityNumber)
   {
      this.firstNameField = firstName;
      this.lastNameField = lastName;
      this.socialSecurityNumberField = socialSecurityNumber;
   } 

   public void setFirst(String firstName)
{
	System.out.println("setFirst not yet implemented");
}

   public void setLast(String lastName)
{
	System.out.println("setLast not yet implemented");
}

   // return first name
   public String getFirstName()
   {
      return "getFirstName not yet implemented";
   } 

   // return last name
   public String getLastName()
   {
      return "getLastName not yet implemented";
   } 

   // return social security number
   public String getSocialSecurityNumber()
   {
      return socialSecurityNumberField;
   } 

   // return String representation of Employee object
   @Override
   public String toString()
   {
      return "toString not yet implemented";
   }

   // Note: We do not implement Payable method getPaymentAmount here so 
   // this class must be declared abstract to avoid a compilation error.
   
} // end abstract class Employee



/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
