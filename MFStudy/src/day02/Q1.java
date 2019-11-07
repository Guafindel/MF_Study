package day02;

import java.util.Arrays;

public class Q1 {
	public static void main(String[] args) {
		// 6개 일치 숫자 고정
		// 43, 24, 10, 18, 1, 36 (고정)
		// 6개 숫자를 배열로 지정
		// 숫자의 범위는 1~45
		// 5개 일치 숫자는 3개(생성할 숫자들) 3개 배열을 더 뽑아내라는 의미

		// 예시) 1행은 고정 나머지 3개 행은 변경되서 나오게.
		// 1행 => [43, 24, 10, 18, 1, 36]
		// 2행 => [36, 24, 15, 18, 1, 43]
		// 3행 => [1, 30, 18, 10, 43, 36]
		// 4행 => [43, 36, 10, 18, 8, 24]

		int number[] = { 43, 24, 10, 18, 1, 36 };
		int num[] = new int[6]; // 6개 숫자 배열 생성
		int exNum = (int)(Math.random()*6+1);
		
			for (int i = 0; i < num.length; i++) {
				num[i] = (int) (Math.random() * 45 + 1);
				for (int j = 0; j < i; j++) {
					if (num[i] == num[j]) {
						i--;
						break;
					}
				}
				while (num[i] != number[i]) {
					num[i] = (int) (Math.random() * 45 + 1);
					
				}
				
			}
			for (int i = 0; i < 4; i++) {
				System.out.println(Arrays.toString(num));
//				if(Arrays.equals(num, number)) {
//					shuffle(num);
//				}
				shuffle(num);
			}
	}
	
	public static int[] shuffle(int[] array) {

		for (int i = 0; i < array.length; i++) {

			int a = (int) (Math.random() * array.length); // 0~5
			int b = (int) (Math.random() * array.length); // 0~5
					
			int tmp = array[a]; // tmp는 { 43, 24, 10, 18, 1, 36 } 중 하나의 수를 가진다
			array[a] = array[b]; // array[b]를 array[a]에 대입한다 즉 array[a] == array[b]가 된다. array[a] 는 { 43, 24, 10, 18, 1, 36 } 중 하나의 값을 가진다.
			array[b] = tmp; // tmp는 기존의 array[a]의 값을 가졌던 것. 즉 결과적으로 array[a]와 array[b]의 값을 바꿔준 것.
				
		}
		return array;
	}
}
