package day02;

import java.util.Arrays;

public class Q3 {
	public static void main(String[] args) {
		
		int number[] = {43, 24, 10, 18, 1, 36};
		int num[] = new int[6];
		
		// 1. 랜덤 한 숫자 만들기
		int a = (int)(Math.random()*45+1);
		//System.out.println(a);
		
		// 2. 6개 일치 숫자 중에 5개를 무작위로 선별하기
		
		// 3. 2번에서 선택된 숫자들 이외의 것으로 나머지 숫자 생성 하여 한 행 완성하기
		
		// 4. 3번에서 생성한 행과 기존의 행 간의 중복 검사하기
		
		//int exNum = (int)(Math.random()*6+1); // 6개의 자리 중 예외시킬 자리 숫자
		
		for(int i = 1; i<46; i++) { // 1~45까지 45번 돌려가면서 확인
			int exNum = (int)(Math.random()*6+1); // 예외시킬 숫자 하나 생성 이 외의 5가지 숫자에 뽑아낸 숫자를 대입
			for(int k=0; k<6; k++) { 
				if(i != number[k]) {
					
					
				}
			}
			
		}
		
		
	}
}
