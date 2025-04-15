package MyHeapSort;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
	/** Heap sort method */
	public static <E extends Comparable> void heapSort(E[] list) {
		// Create a Heap of integers
		Heap<E> heap = new Heap<E>();

		// Add elements to the heap
		for (int i = 0; i < list.length; i++)
			heap.add(list[i]);

		heap.printHeap();

		// Remove elements from the heap
		for (int i = list.length - 1; i >= 0; i--)
			list[i] = heap.remove();
	}

	/** A test method */
	public static void main(String[] args) {
		Random rndObj = new Random();
		Integer[] list = new Integer[20];

		for (int i = 0; i < list.length; i++) {
			list[i] = rndObj.nextInt(-100, 101);
		}

		System.out.println("Unsorted List: " + Arrays.toString(list));
		heapSort(list);
		System.out.println("Sorted List: " + Arrays.toString(list));
	}
}
