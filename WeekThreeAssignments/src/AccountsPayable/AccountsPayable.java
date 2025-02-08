package AccountsPayable;

// AccountsPayable.java
// Tests class Payable class hierarchy.

public class AccountsPayable 
{
   public static void main(String[] args)
   {
      // create six-element Payable array
      Payable[] payableObjects = new Payable[4];
      
      // populate array with objects that implement Payable


      System.out.println(
         "Invoices and Employees processed polymorphically:\n"); 

      // generically process each element in array payableObjects
      for (Payable currentPayable : payableObjects)
      {
         // output currentPayable and its appropriate payment amount
         System.out.printf("%s \n", currentPayable.toString()); 
         
         // special processing for instance of class BasePlusCommissionEmployee
         if (currentPayable instanceof BasePlusCommissionEmployee)
         {
            // remember: need to downcast Payable reference to BasePlusCommissionEmployee reference
            // print new base salary with 10%% increase
            System.out.printf("new base salary with 10%% increase is: " );
         } // end if

         System.out.printf("%s: $%,.2f\n\n",
            "payment due", currentPayable.getPaymentAmount()); 
      } // end for
   } // end main
} // end class AccountsPayable

