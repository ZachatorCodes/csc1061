package AVLTree;

import java.util.Arrays;
import java.util.Random;

public class AVLPerformanceTest {
	private static Random rndObj = new Random();

	public static void main(String[] args) {
		// Create Arrays of different lengths
		Integer[] power1 = new Integer[10];
		Integer[] power2 = new Integer[100];
		Integer[] power3 = new Integer[1000];
		Integer[] power4 = new Integer[10000];
		Integer[] power5 = new Integer[100000];
		Integer[] power6 = new Integer[1000000];

		// Populate Arrays
		populateArray(power1);
		populateArray(power2);
		populateArray(power3);
		populateArray(power4);
		populateArray(power5);
		populateArray(power6);

		// Sort Arrays
		Arrays.sort(power1);
		Arrays.sort(power2);
		Arrays.sort(power3);
		Arrays.sort(power4);
		Arrays.sort(power5);
		Arrays.sort(power6);

		long start = System.nanoTime();

		test(power1);
		test(power2);
		test(power3);
		test(power4);
		test(power5);
		test(power6);

		long end = System.nanoTime();
		long totalTime = end - start;

		System.out.printf("BUILD SUCESSFUL (total execution time: %d nanoseconds", totalTime);
	}

	public static void test(Integer[] array) {
		System.out.println("Tree creation through constructor(E[] objects)");
		BST<Integer> bst = constructBST(array);
		AVLTree<Integer> avl = constructAVL(array);

		System.out.println();

		System.out.println("Average element search time comparison");
		searchTree(bst, array);
		searchTree(avl, array);

		System.out.println();
	}

	public static void searchTree(Tree<Integer> tree, Integer[] arr) {
		long totalSearchTime = 0;
		if (tree instanceof AVLTree) {
			AVLTree<Integer> avl = (AVLTree<Integer>) tree;
			for (int i = 0; i < avl.size(); i++) {
				long startTime = System.nanoTime();
				avl.search(arr[i]);
				long endTime = System.nanoTime();
				totalSearchTime += (endTime - startTime);
			}
			System.out.printf("AVL average search execution time for %d elements is %d nanoseconds\n", tree.size(),
					totalSearchTime / arr.length);
		} else {
			BST<Integer> bst = (BST<Integer>) tree;
			for (int i = 0; i < bst.size(); i++) {
				long startTime = System.nanoTime();
				bst.search(arr[i]);
				long endTime = System.nanoTime();
				totalSearchTime += (endTime - startTime);
			}
			System.out.printf("BST average search execution time for %d elements is %d nanoseconds\n", tree.size(),
					totalSearchTime / arr.length);
		}
	}

	public static BST<Integer> constructBST(Integer[] arr) {
		long populateStart = System.nanoTime();
		BST<Integer> bst = new BST<Integer>(arr);
		long populateEnd = System.nanoTime();
		System.out.printf("BST constructor() execution time for %d elements is %d nanoseconds\n", arr.length,
				populateEnd - populateStart);
		return bst;
	}

	public static AVLTree<Integer> constructAVL(Integer[] arr) {
		long populateStart = System.nanoTime();
		AVLTree<Integer> avl = new AVLTree<Integer>(arr);
		long populateEnd = System.nanoTime();
		System.out.printf("AVL Tree constructor() execution time for %d elements is %d nanoseconds\n", arr.length,
				populateEnd - populateStart);
		return avl;
	}

	public static void populateArray(Integer[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rndObj.nextInt();
		}
	}

}
