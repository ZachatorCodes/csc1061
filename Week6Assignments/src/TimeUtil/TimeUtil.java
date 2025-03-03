package TimeUtil;

import java.util.Random;

// Program to calculate execution time or elapsed time in Java
class Main {
	public static void main(String[] args) throws InterruptedException {
		Random rndObj = new Random();
		int[] arr100 = new int[100];
		int[] arr1000 = new int[1000];
		int[] arr5000 = new int[5000];
		int[] arr10000 = new int[10000];

		for (int i = 0; i < arr10000.length; i++) {
			int num = rndObj.nextInt(0, 1000) + 1;
			arr10000[i] = num;
			if (i < 5000) {
				arr5000[i] = num;
				if (i < 1000) {
					arr1000[i] = num;
					if (i < 100) {
						arr100[i] = num;
					}
				}
			}
		}
		
		testTime(arr100);
		testTime(arr1000);
		testTime(arr5000);
		testTime(arr10000);
		
	}

	public static void testTime(int[] arr) {
		long startTime = System.nanoTime();
		// ... the code being measured starts ...
		BubbleSort.bubbleSort(arr);
		// ... the code being measured ends ...
		long endTime = System.nanoTime();

		long timeElapsed = endTime - startTime;

		System.out.printf("Testing %d item array\n", arr.length);
		System.out.println("Execution time in nanoseconds: " + timeElapsed + "ns");
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000.0 + "ms");
		System.out.println();
	}
}