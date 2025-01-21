//package chapter7;
/*
 * Suppose the following code is written to reverse the elements in an array.
 *  Explain why it is wrong.  How do you fix it?
 */

/**
 *
 * @author bobgils  Spring 2019
 */
public class Reverse {
    
    private static void reverse(int[] list) {
    	// i < j
    	int reps = 0;
        for (int i = 0, j = list.length - 1; i < list.length / 2; i++, j--) {
            // swap list[i] with list[j]
            int temp = list[i];
            list[i] = list[j];		
            list[j] = temp;
            reps++;
        }
        System.out.println(reps);
    }
    
    public static void main(String[] args){
        int[] list1 = {1, 2, 3, 4, 5, 6,7};

        for (int i : list1)
            System.out.print(i + " ");
        System.out.println();
        
        reverse(list1);
        for (int i : list1)
            System.out.print(i + " ");
        System.out.println();
    }
}

/*
It swaps the elements twice. For example, the first element is swapped with the last element once 
and then the last element is swapped with the first element another time. In effect, 
the elements are not swapped. Here is the fix:

int[] list = {1, 2, 3, 5, 4};

for (int i = 0, j = list.length - 1; i < list.length / 2; i++, j--) {
  // Swap list[i] with list[j]
  int temp = list[i];
  list[i] = list[j];
  list[j] = temp;
}

*/