package day02;

import java.util.Arrays;
import java.util.Scanner;

public class Lotto2 {

	public static int[] lotto = { 43, 3, 9, 33, 2, 1 };

	public static void main(String[] args) {

		int[] num1 = new int[6]; // 1개
		int[] num2 = new int[6]; // 3개
		int[] num3 = new int[6]; // 10개
		int[] num4 = new int[6]; // 50개
		int[] num5 = new int[6]; // 100개
		int[] num6 = new int[6]; // 500개

		Scanner scan = new Scanner(System.in);

		int[] check = new int[6];

		int c = check.length;

		for (int i = 0; i < c; i++) {
			check[i] = (int) (Math.random() * 45 + 1);
			while (!(check[i] == lotto[i])) {
				check[i] = (int) (Math.random() * 45 + 1);
			}
		}

		// System.out.println(Arrays.toString(check));
		
		//getSecond(check);
		getThird(check);
		getFourth(check);
		getFifth(check);
		getFail(check);

		
//		System.out.println("명령을 입력하세요.");
//		if (scan.nextLine() == "TRUE") {
//
//		} else {
//
//		}

	}

	public static int[] getSecond(int[] array) {
		int a = (int) (Math.random() * 6);
		int rnum = (int) (Math.random() * 45 + 1);
		for (int i = 0; i < 6; i++) {
			while (rnum == lotto[i]) {
				rnum = (int) (Math.random() * 45 + 1);
			}
			array[a] = rnum;
		}
		return array;
	}

	public static int[] getThird(int[] array) {

		int num[] = new int[2];
		int rnum[] = new int[2];
		int p = num.length;
		
		for (int i = 0; i < p; i++) {
			num[i] = (int) (Math.random() * 6);
			for (int j = 0; j < i; j++) {
				if (num[i] == num[j]) {
					i--;
					break;
				}
			}
		}

		System.out.println(Arrays.toString(num));

		
		for(int i = 0; i < p; i++) {
			rnum[i] = (int) (Math.random() * 45 + 1);
			for (int j = 0; j < i; j++) {
				if(rnum[i] == rnum[j]) {
					i--;
					break;
				} 
			}
		}
		
		for(int i = 0; i < 2; i++) {
			for( int j = 0; j < lotto.length; j++) {
				if(rnum[i] != lotto[j]) {
					
				}
			}
		}
		
		

		return array;
	}

	public static int[] getFourth(int[] array) {
		int num[] = new int[3];
		
		int p = num.length;
		for (int i = 0; i < p; i++) {
			num[i] = (int) (Math.random() * 6);
			for (int j = 0; j < i; j++) {
				if (num[i] == num[j]) {
					i--;
					break;
				}
			}
		}
		
		System.out.println(Arrays.toString(num));
		
		int rnum = (int) (Math.random() * 45 + 1);

		return array;
	}

	public static int[] getFifth(int[] array) {
		int num[] = new int[4];
		int p = num.length;
		for (int i = 0; i < p; i++) {
			num[i] = (int) (Math.random() * 6);
			for (int j = 0; j < i; j++) {
				if (num[i] == num[j]) {
					i--;
					break;
				}
			}
		}
		
		System.out.println(Arrays.toString(num));
		int rnum = (int) (Math.random() * 45 + 1);

		return array;
	}

	public static int[] getFail(int[] array) {
		int num[] = new int[5];
		int p = num.length;
		for (int i = 0; i < p; i++) {
			num[i] = (int) (Math.random() * 6);
			for (int j = 0; j < i; j++) {
				if (num[i] == num[j]) {
					i--;
					break;
				}
			}
		}
		
		System.out.println(Arrays.toString(num));
		int rnum = (int) (Math.random() * 45 + 1);

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
