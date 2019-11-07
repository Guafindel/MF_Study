package day02;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Lotto {
	static int number[] = { 43, 24, 10, 18, 1, 36 };

	public static void main(String args[]) {
		int num[] = new int[6]; // 6개 숫자 배열 생성
		int n = num.length;
		int nb = num.length;
		for (int i = 0; i < n; i++) {
			num[i] = (int) (Math.random() * 45 + 1);
			while (!(num[i] == number[i])) {
				num[i] = (int) (Math.random() * 45 + 1);
			}
		}
		for (int i = 0; i < 4; i++) {
			System.out.println(Arrays.toString(num));
			System.arraycopy(number, 0, num, 0, nb);
			getRandom(num);
			shuffle(num);
		}
	}

	public static int[] getRandom(int[] array) {
		int a = (int) (Math.random() * 6);
		int b = (int) (Math.random() * 45 + 1);
		for (int i = 0; i < 6; i++) {
			while (b == number[i]) {
				b = (int) (Math.random() * 45 + 1);
			}
			array[a] = b;
		}
		return array;
	}

	public static int[] shuffle(int[] array) {
		int arr = array.length;
		for (int i = 0; i < arr; i++) {
			int a = (int) (Math.random() * arr);
			int b = (int) (Math.random() * arr);
			int tmp = array[a];
			array[a] = array[b];
			array[b] = tmp;
		}
		return array;
	}
}
