package MyStack;

// StackCompositionTest.java
// Class StackCompositionTest.

public class StackCompositionTest 
{
   public static void main(String[] args)
   {
      MyStack<Integer> stack = new MyStack<>();

      // use push method
      stack.push(-1);
      stack.print();
      stack.push(0);
      stack.print();
      stack.push(1);
      stack.print();
      stack.push(5);
      stack.print();

      // remove items from stack
         int removedItem;

         while (!stack.isEmpty()) 
         {
            removedItem = stack.pop(); // use pop method
            System.out.printf("%n%d popped%n", removedItem);
            stack.print();
         } 
      
   } 
} // end class StackCompositionTest



