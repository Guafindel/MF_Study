package algorithm;

public class Lesson1 {
	public static void main(String args[]) {
		int i;
		int j;
		int min;
		int index;
		int temp;
		int array[] = { 1, 10, 5, 8, 7, 6, 4, 3, 2, 9 };

		for (i = 0; i < 10; i++) {
			min = 9999;
			for (j = i; j < 10; j++) {
				if (min > array[j]) {
					min = array[j];
					index = j;

					temp = array[i];
					array[i] = array[index];
					array[index] = temp;
				}

			}

		}
		for(i = 0; i<array.length; i++) {
			System.out.printf(" " + array[i]);
		}
	}
}
